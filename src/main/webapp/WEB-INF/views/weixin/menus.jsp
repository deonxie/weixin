<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>微信菜单管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/dialog.jsp" %>
    <script type="text/javascript">
    function asyncmenu(){
    	$.ajax({url:'${ctx}/weixin/menu/async',asnyc:true,success:function(data){
    		if(data)alert('同步成功！');else alert('同步失败');
    	}})
    }
    function deleteasyncmenu(){
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
<shiro:hasPermission name="wxmenu:edit">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary btn-lg" onclick="menucreate()">添加</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary btn-lg" onclick="asyncmenu()">同步到微信</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" class="btn btn-primary btn-lg" onclick="deleteasyncmenu()">删除微信菜单</button>
</shiro:hasPermission>
</div>
<tags:message content="${message}"/>

<table class="table table-striped table-bordered">
<tr>
	<th colspan="2">名称</th><th>类型</th><th>值</th><th>排序</th><th>操作</th>
</tr>
<c:forEach items="${entitys}" var="menu" varStatus="status">
<tr>
	<td colspan="2">${menu.name }</td>
	<td>${menu.type eq 'click'?'点击':'超链接' }</td>
	<td>${menu.key }</td>
	<td>${menu.index }</td>
	<td><shiro:hasPermission name="wxmenu:edit">
	<a onclick="menuupdate(${menu.id},0,'${menu.name }','${menu.type }','${menu.key }',${menu.index })">修改</a>
	<a href="${ctx }/weixin/menu/delete/${menu.id}" 
			onclick="return confirmx('确认要删除吗？', this.href)">删除</a></shiro:hasPermission>
	</td>
</tr>
	<c:forEach items="${menu.child }" var="sub">
	<tr>
		<td style="border-right: 1px solid #ccc;text-align: right;">&nbsp;&nbsp;${menu.name }  》 </td>
		<td>${sub.name }</td>
		<td>${sub.type eq 'click'?'点击':'超链接' }</td>
		<td>${sub.key }</td>
		<td>${sub.index }</td>
		<td><shiro:hasPermission name="wxmenu:edit">
		<a onclick="menuupdate(${sub.id},${menu.id },'${sub.name }','${sub.type }','${sub.key }',${sub.index })">修改</a>
		<a href="${ctx }/weixin/menu/delete/${sub.id}" 
			onclick="return confirmx('确认要删除吗？', this.href)">删除</a></shiro:hasPermission>
		</td>
	</tr>
	</c:forEach>
</c:forEach>
</table>
<shiro:hasPermission name="wxmenu:edit">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">微信按钮</h4>
      </div>
      <form action="${ctx }/weixin/menu/save" method="post">
	      <div class="modal-body">
	      	<input type="hidden" name="id" id="oldid"/>
	         <div class="control-group">
	         	<span>上级:</span>
	         	<select name="parent.id" id="oldparentid">
		        	<option value="">== 无 ==</option>
		        	<c:forEach items="${entitys}" var="menu"><c:if test="${menu.type eq 'click' }">
		        		<option value="${menu.id }">${menu.name }</option></c:if>
		        	</c:forEach>
	        	</select>
	        </div> <div class="control-group">
	         	<span>名称:</span>
	         	<input name="name" class="required" id="oldname" placeholder="请输入按钮名称"/>
	        </div> <div class="control-group">
	         	<span>类型:</span> 
	         	<select name="type" id="oldtype">
         			<option value="click">点击</option>
         			<option value="view">超链接</option>
         		</select>
	        </div> <div class="control-group">
	         	<span>&nbsp;&nbsp;&nbsp;值 :</span>
	         	<input name="key" class="required" id="oldkey" placeholder="超链接类型 请输入正确的网址"/>
	        </div> <div class="control-group">
	         	<span>排序:</span>
	         	<input name="index" class="required" id="oldindex" placeholder="请输入正整数排序"/>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="reset" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="submit" class="btn btn-primary">保存</button>
	      </div>
	   </form>   
    </div>
  </div>
</div>
<script type="text/javascript">
	function menucreate(){
		menuupdate(0,'','','','',1);
	}
	function menuupdate(id,parentid,name,type,key,index){
		$("#oldid").val(id);
		$("#oldparentid").find("option[value='"+parentid+"']").attr("selected",true);
		$("#oldname").val(name);
		$("#oldtype").val(type);
		$("#oldkey").val(key);
		$("#oldindex").val(index);
		$("#myModal").modal();
	}
</script>
</shiro:hasPermission>
</body>
</html>