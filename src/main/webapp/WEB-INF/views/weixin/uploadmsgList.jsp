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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}${baseMapper}">消息列表</a></li>
		<shiro:hasPermission name="wxmsg:edit"><li><a href="${ctx}${baseMapper}update/0">添加消息</a></li></shiro:hasPermission>
	</ul>
	<form id="searchForm" action="${ctx}${baseMapper}" method="post" class="breadcrumb form-search">
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
            <th></th>
            <th>标题</th>
            <th>作者</th>
            <th>创建日期</th>
            <th>操作</th>
        </tr>
        </thead>
		<tbody>
        <c:forEach items="${page.content}" var="msg" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td><img src="${ctx }${msg.picPath}" style="width: 200px;height: 100px;"/></td>
                <td>${msg.title}&nbsp;</td>
                <td>${msg.author }&nbsp;</td>
                <td><fmt:formatDate value="${msg.createTime}" pattern="yyyy年MM月dd日"/></td>
                <td>
                <shiro:hasPermission name="wxmsg:edit">
                	<a href="${ctx}${baseMapper}update/${msg.id}">详情</a>
                    <a href="${ctx}${baseMapper}delete/${msg.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)" >删除</a>
                </shiro:hasPermission></td>
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