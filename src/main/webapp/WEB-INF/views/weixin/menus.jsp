<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>微信菜单管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
    function createmenu(){
    	$.ajax({url:'${ctx}/weixin/menu/async',asnyc:true,success:function(data){
    		if(data)alert('同步成功！');else alert('同步失败');
    	}})
    }
    function deletemenu(){
    	$.ajax({url:'${ctx}/weixin/menu/asyncdelete',asnyc:true,success:function(data){
    		if(data)alert('删除成功！');else alert('删除失败');
    	}})
    }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a>菜单列表</a></li>
</ul>
<div class="breadcrumb form-search">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">添加</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary btn-lg" onclick="createmenu()">同步到微信</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary btn-lg" onclick="deletemenu()">删除微信菜单</button>
</div>
<tags:message content="${message}"/>

<table class="table table-striped table-bordered">
<tr>
	<th colspan="2">名称</th><th>类型</th><th>值</th><th>操作</th>
</tr>
<c:forEach items="${entitys}" var="menu" varStatus="status">
<tr>
	<td colspan="2">${menu.name }</td>
	<td>${menu.type eq 'click'?'点击':'超链接' }</td>
	<td>${menu.key }${menu.url }</td>
	<td><a href="${ctx }/weixin/menu/delete/${menu.id}" 
			onclick="return confirmx('确认要删除吗？', this.href)">删除</a></td>
</tr>
	<c:forEach items="${menu.child }" var="sub">
	<tr>
		<td style="border-right: 1px solid #ccc;text-align: right;">&nbsp;&nbsp;${menu.name }  》 </td>
		<td>${sub.name }</td>
		<td>${sub.type eq 'click'?'点击':'超链接' }</td>
		<td>${sub.key }${sub.url }</td>
		<td><a href="${ctx }/weixin/menu/delete/${sub.id}" 
			onclick="return confirmx('确认要删除吗？', this.href)">删除</a></td>
	</tr>
	</c:forEach>
</c:forEach>
</table>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">微信按钮</h4>
      </div>
      <form action="${ctx }/weixin/menu/save" method="post">
	      <div class="modal-body">
	         <div class="control-group">
	         	<span class="control-label">上级菜单:</span>
	         	<div class="controls">
	        	<select name="parent.id">
		        	<option value="">== 无 ==</option>
		        	<c:forEach items="${entitys}" var="menu"><c:if test="${menu.type eq 'click' }">
		        		<option value="${menu.id }">${menu.name }</option></c:if>
		        	</c:forEach>
	        	</select>
	         	</div>
	        </div>
	        <div class="control-group">
	         	<label class="control-label">名称:</label>
	         	<div class="controls">
	         		<input name="name" class="required" value=""/>
	         	</div>
	        </div>
	        <div class="control-group">
	         	<label class="control-label">类型:</label>
	         	<div class="controls">
	         		<select name="type">
	         			<option value="click">点击</option>
	         			<option value="view">超链接</option>
	         		</select>
	         	</div>
	        </div>
	        <div class="control-group">
	         	<label class="control-label">值:</label>
	         	<div class="controls">
	         		<input name="key" class="required" value=""/>
	         	</div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="submit" class="btn btn-primary">保存</button>
	      </div>
	   </form>   
    </div>
  </div>
</div>
</body>
</html>