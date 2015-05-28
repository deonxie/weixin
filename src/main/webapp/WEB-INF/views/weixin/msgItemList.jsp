<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>微信消息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<script type="text/javascript">
		function page(n){
			$("#pageNo").val(n);
			$("#searchForm").submit();
	    	return false;
	    }
		function chooseChange(obj,id,pic,title){
			//if(obj.checked){
				window.parent.setItmes(id,pic,title);
			//}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs"></ul>
	<form id="searchForm" action="${ctx}${baseMapper}msgItemList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.number+1}"/>
		<input id="orderDir" name="orderDir" type="hidden" value="${pageRequest.orderDir}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${pageRequest.orderBy}"/>
		<div>
			<label>标题：</label><input type="text" name="search_LIKE_title" class="input-small" value="${param.search_LIKE_title}">
            &nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>#</th>
            <th>标题</th>
        </tr>
        </thead>
		<tbody>
        <c:forEach items="${page.content}" var="msg" varStatus="status">
            <tr>
                <td>${status.count}<input type="button" onclick="chooseChange(this,${msg.id},'${msg.picPath}','${msg.title}')"
                value="添加"/></td>
                <td><img src="${ctx }${msg.picPath}" style="width: 200px;height: 100px;"/><br>
                	${msg.title}
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
</body>
</html>