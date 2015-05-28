<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
	<title>微信消息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<style type="text/css">
	.itemdiv{width: 319px;height: 119px;border-bottom: 1px solid #ccc;position: relative;}
	.itemh4{width:199px; overflow: hidden;position: absolute;left: 14px;top:25px;text-align: left;}
	.itemimg{width: 78px;height: 78px;position: absolute;top: 20px;right: 14px;}
	.itemspan{position: absolute;left:14px;bottom: 21px;}
	</style>
	<script type="text/javascript">
		var cover,items= new Array();
		function setItmes(id,pic,title){
			if(checkExist(id)){
				alert("已添加请勿重复添加！");
				return;
			}
			if(items.length ==10){
				alert('每次最多发布10条消息');
				return;
			}
			items.push(id);
			if(cover== null){
				$("#coverImg").attr('src',"${ctx}"+pic);
				$("#coverTitle").text(title);
				cover = id;
				return ;
			}
			createDiv('name'+id,id,title,pic);
		}
		
		function checkExist(id){
			for(var i=0;i<items.length;i++){
				if(items[i]==id)
					return true;
			}
			return false;	
		}
		function createDiv(name,id,title,pic){
			$("#sendItems").append('<div id="'+name+'" class="itemdiv"><h4 class="itemh4">'+
					title+'</h4><span class="itemspan">'
					+'<button onclick="changeCover(this)">设为封面</button>'
					+'<button onclick="removeDiv(this)">移除</button></span>'
					+'<img alt="" src="${ctx }'+pic+'" class="itemimg"/></div>');
		}
		function changeCover(obj){
			var id = $(obj).parent().parent().attr('id').replace('name','');
			var coverTitle = $("#coverTitle").text();
			var coverImg = $("#coverImg").attr('src');
			var itemTitle = $("#name"+id).find('> h4');
			var itemImg = $("#name"+id).find('> img');
			$("#coverTitle").text(itemTitle.text());
			$("#coverImg").attr('src',itemImg.attr('src'));
			itemImg.attr('src',coverImg);
			itemTitle.text(coverTitle);
			
			$(obj).parent().parent().attr('id','name'+cover);
			$('#name'+id).attr('id','name'+cover);
			cover = id;
		}
		function removeDiv(obj){
			var id = $(obj).parent().parent().attr('id');
			alert(id);
			$("#"+id).remove();
			for(var i=0;i<items.length;i++){
				if('name'+items[i]==id){
					items.splice(i,1);
					return;
				}
			}
		}
		function submitform(){
			if(cover ==null){
				alert('请选择封面图片');
				return;
			}
			$("#coverinput").val(cover);
			var ids='';
			for(var i=0;i<items.length;i++)
				ids +=','+items[i];
			
			$("#itemsinput").val(ids);
			$("#sendFrm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs"></ul>
	<tags:message content="${message}"/>
	<table class="table" style="width: 100%;">
		<tr>
			<td width="40%">
				<div id="sendItems" style="border: 1px solid #ccc;clear: both;width: 319px;text-align: center;">
					<br>
					<div style="width: 319px;height: 184px;text-align: center;border-bottom: 1px solid #ccc;">
						<div style="width: 291px;height: 160px;position: relative;margin: 0px 14px 14px;">
							<img id="coverImg" alt="" src="${ctxStatic }/images/cover.png" 
							style="width: 100%;height: 100%;"/>
						<h4 id="coverTitle" style="width: 100%;background-color: black;position: absolute;left:0px;bottom: 0px;text-align: left; ">
						标题</h4>
						</div>
					</div>
				</div>
				<form id="sendFrm" action="${ctx }/uploadmsg/sendMsgItem" method="post">
					<input type="hidden" name="cover" id="coverinput"/>
					<input type="hidden" name="items" id="itemsinput"/><br>
					<input type="button" class="btn btn-primary" style="width: 319px;"
					 value="发  送" onclick="submitform()"/>
				</form>
				<div><font color="red">注意：公众号一天只能发送一次</font></div>
			</td>
			<td width="60%">
				<iframe src="${ctx }/uploadmsg/msgItemList" width="300px" height="600px"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>