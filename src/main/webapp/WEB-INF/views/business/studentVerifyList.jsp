<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>学生信息审核</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/lib/jquery.form.js"></script>
	<script type="text/javascript">
		function page(n){
			$("#pageNo").val(n);
			$("#searchForm").submit();
	    	return false;
	    }
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a>学生审核信息</a></li>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
            <label>姓名：</label><input type="text" name="search_LIKE_name" class="input-small" value="${param.search_LIKE_name}"/>
			<label>考号：</label><input type="text" name="search_LIKE_inspectNum" class="input-small" value="${param.search_LIKE_inspectNum}"/>
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</form>

	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>姓名</th>
            <th>身份证号</th>
            <th>考生号</th>
            <th>录取院系</th>
            <th>录取专业</th>
            <th>收件人</th>
            <th>联系电话</th>
            <th>地址</th>
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
                <td><fmt:formatDate value="${stud.createDate}" pattern="yyyy年MM月dd日"/></td> 
                <td>
                <shiro:hasPermission name="studen:edit">
                    <a class="btn btn-primary" href="${ctx}${baseMapper}pass?id=${stud.id }">审核通过</a>
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
    
    
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
	      <div class="modal-body">
	      </div>
	      <div class="modal-footer">
	      </div>
    </div>
  </div>
</div>
</body>
</html>