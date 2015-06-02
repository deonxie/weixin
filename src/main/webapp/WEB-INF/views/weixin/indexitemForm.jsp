<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>微信端首页配置管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic }/ajaxupload/ajaxfileupload.js"></script>
    <style type="text/css">
    #tableImg td{background-color: black;border: 1px solid white;}
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                }
            });
            $("#tableImg").find("img").click(function(){
            	var color = $("#color").val();
            	color = color==''?'#000000':color;
            	$("#showimg").attr('src',$(this).attr('src')).parent().css('background-color',color);
            	$("#picUrl").val($(this).attr('src'));
            });
            $("#tableColor").find("td").click(function(){
            	var rgb = $(this).css('background-color');
            	if(rgb.indexOf('rgb')==0){
            		rgb = rgb.substring(4,rgb.length-1).split(",");
            		function hex(x) {return ("0" + parseInt(x).toString(16)).slice(-2);}
            		var color= "#" + hex(rgb[0]) + hex(rgb[1]) + hex(rgb[2]); 
            		$("#color").val(color);
            		$("#showimg").parent().css('background-color',color);
            	}else{
            		$("#color").val(rgb);
            		$("#showimg").parent().css('background-color',rgb);
            	}
            });
        });
        
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}">配置列表</a></li>
    <li class="active"><a href="#">
	    <shiro:hasPermission name="wxconfig:edit">${entity.id > 0 ? '修改':'添加'}</shiro:hasPermission>
    配置信息</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">标题:</label>
        <div class="controls">
            <input type="text" maxlength="64" name="title" value="${entity.title}" placeholder="请输入标题">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">连接地址:</label>
        <div class="controls">
            <input type="text" name="url" value="${entity.url }" placeholder="请输入连接地址" maxlength="255"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">类型:</label>
        <div class="controls">
        	<select name="type">
        		<option value="0" ${entity.type eq 0?'selected="selected"':''}>校园图片展示</option>
        		<option value="1" ${entity.type eq 1?'selected="selected"':''}>功能项</option>
        	</select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">排序:</label>
        <div class="controls">
        	<input type="number" name="index" value="${entity.index }" placeholder="请输入整数"/>
        </div>
    </div>
	<div class="control-group">
        <label class="control-label">背景色:</label>
        <div class="controls">
        	<input type="text" name="color" value="${entity.color }" id="color" maxlength="7" placeholder="格式如#ff00ff，的16进制"/>
        	<span>点击选择颜色</span><table id="tableColor">
	        	<tr><td style="background-color: #0000FF;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #FFFF00;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #FF0000;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #FFA500;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #FFC0CB;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #FF00FF;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #A020F0;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #90EE90;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #00FF00;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #000000;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #bf08c4;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #08c489;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        		<td style="background-color: #7970c2;">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	        	</tr>
        	</table>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">图片:</label>
        <div class="controls">
        	<input type="hidden" name="tempImg" id="picUrl"/>
        	<input type="file" name="file" id="ajaxUploadFfile" class="btn btn-primary" onchange="ajaxSaveImage()"/>
        	<span>点击选择模板图片</span>
        	<table><tr><td style="background-color: ${entity.color};">
        	<img alt="无图片" src="${ctxStatic }${entity.imgPath }" id="showimg" style="width: 100px;"/></td></tr></table>
        	<table id="tableImg">
	        	<tr>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/book.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/guide.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/hat.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/infomation.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/letter.png" style="width: 50px;"/></td>
	        	</tr><tr>	
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/message.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/news.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/path.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/phone.png" style="width: 50px;"/></td>
	        		<td><img alt="" src="${ctxStatic }/images/indexItem/example/read.png" style="width: 50px;"/></td>
	        	</tr>
        	</table>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="wxconfig:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
<shiro:hasPermission name="wxconfig:edit">
<script type="text/javascript">
function ajaxSaveImage() {
	if($("#ajaxUploadFfile").val()=='')
		return ;
    $.ajaxFileUpload({
        url: '${ctx}/file/updload/', 
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: 'ajaxUploadFfile', // 上传文件的id、name属性名
        dataType: 'text', 
        success: function(result, status){
        	var json = jQuery.parseJSON(result.substring(result.indexOf("["),result.indexOf("]")+1));
        	if(json[0]){
        		$("#showimg").attr('src','${ctx}/tmp/'+json[1]);
        		$("#picUrl").val(json[1]);
        	}
        }
    });
}
</script>
</shiro:hasPermission>
</body>
</html>