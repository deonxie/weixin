<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/lib/jquery.form.js"></script>
	<script type="text/javascript">
		function page(n){
			$("#pageNo").val(n);
			$("#searchForm").submit();
	    	return false;
	    }
		
		function importexcel(){
			var file = $("#choosefile").val();
			if(file ==null || file=='')
				return;
			var type = file.substring(file.lastIndexOf("."));
			if( type=='.xls' ||type=='.xlsx'){
				$("#uploadForm").ajaxSubmit({success:function(data){
					if(data[0]=='true'){
						$("#importfile").val(data[1]);
						$("#importForm").submit();
					}else alert(data[1]);
				}});
			}else{
				alert("请上传excel文件");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}${baseMapper}">学生信息列表</a></li>
		<li><a href="${ctx}${baseMapper}update/0">学生信息添加</a></li>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
            <label>姓名：</label><input type="text" name="search_LIKE_name" class="input-small" value="${param.search_LIKE_name}"/>
			<label>考号：</label><input type="text" name="search_LIKE_inspectNum" class="input-small" value="${param.search_LIKE_inspectNum}"/>
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">导入</button>
		</div>
	</form>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>姓名</th>
            <td>身份证号</td>
            <th>考生号</th>
            <th>录取院系</th>
            <th>录取专业</th>
            <th>收件人</th>
            <th>联系电话</th>
            <th>地址</th>
            <!-- <th>预报到登记</th>-->
            <th>创建时间</th> 
            <th>操作</th>
        </tr>
        </thead>
		<tbody>
        <c:forEach items="${page.content}" var="stud" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${stud.name}</td>
                <td>${stud.icdNum}</td>
                <td>${stud.inspectNum}</td>
                <td>${stud.type}</td>
                <td>${stud.major}</td>
                <td>${stud.receiveName }</td>
                <td>${stud.telNum}</td>
                <td>${stud.address}</td>
               <%--  <td>${stud.register eq 0 ?'未报到':'已报到'}</td>--%>
                <td><fmt:formatDate value="${stud.createDate}" pattern="yyyy年MM月dd日"/></td> 
                <td>
                	<a href="${ctx}${baseMapper}update/${stud.id}">详情</a>
                    <shiro:hasPermission name="studen:edit">
                    <a href="${ctx}${baseMapper}delete/${stud.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
                	</shiro:hasPermission>
                </td>
            </tr>
        </c:forEach>
		</tbody>
	</table>
    <div class="row">
        <ul class="pager">
            <c:if test="${!pageRequest.prePage}">
                <li class=" disabled"><a href="#">上一页</a></li>
            </c:if>
            <c:if test="${pageRequest.prePage}">
                <li class=""><a href="#" id="prePage"
                                onClick="page(${page.number-1})">上一页</a></li>
            </c:if>
            <li class="controls"><strong>总页数${page.totalPages},当前第${page.number
                    + 1}页 ${page[hasNextPage]}</strong></li>
            <c:if test="${!pageRequest.nextPage}">
                <li class="disabled"><a href="#">下一页 </a></li>
            </c:if>
            <c:if test="${pageRequest.nextPage}">
                <li class=""><a href="#" id="nextPage"
                                onclick="page(${page.number+2})">下一页</a></li>
            </c:if>
        </ul>
    </div>
    <form id="importForm" action="${ctx}${baseMapper}import" method="post" >
		<input type="hidden" name="filepath" id="importfile"/> 
	</form>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">导入信息</h4>
      </div>
	      <div class="modal-body">
	        	<form id="uploadForm" action="${ctx }/file/updload/" method="post" enctype='multipart/form-data'>
					<table class="table">
					<tr><td>
					<input type="file" name="file" class="btn btn-primary" id="choosefile"/> 
					</td><td align="right">
					<input type="button" class="btn btn-primary" value="导入" onclick="importexcel()"/>
					</td></tr>
					</table>
				</form>
	      </div>
	      <div class="modal-footer">
	      	<a href="${ctxStatic }/download/学生信息导入模板.xlsx">学生信息导入模板下载</a>
	      </div>
    </div>
  </div>
</div>
</body>
</html>