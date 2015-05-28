<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>自动回复信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${ctxStatic }/ajaxupload/ajaxfileupload.js"></script>
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
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}${baseMapper}">自动回复列表</a></li>
    <li class="active"><a href="#">回复信息
	    <shiro:hasPermission name="user:edit">${entity.id > 0 ? '修改':'添加'}</shiro:hasPermission>
    </a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">关键字:</label>
        <div class="controls">
            <input type="text" class="required" maxlength="64"
                   name="key" value="${entity.key}" placeholder="请输入关键字">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">信息内容:</label>
        <div class="controls">
            <input type="text" class="required" name="content" value="${entity.content }"
            placeholder="请输入消息内容" maxlength="64"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">规则:</label>
        <div class="controls">
        	<input type="radio" name="option" value="1" ${entity.option eq 1?'checked="checked"':'' } class="required"/>以该关键字开头
        	<input type="radio" name="option" value="2" ${entity.option eq 2?'checked="checked"':'' } class="required"/>以该关键字结束
        	<input type="radio" name="option" value="3" ${entity.option eq 3?'checked="checked"':'' } class="required"/>包含关键字
        	<input type="radio" name="option" value="4" ${entity.option eq 4?'checked="checked"':'' } class="required"/>等于关键字
        </div>
    </div>
	<div class="control-group">
        <label class="control-label">封面图片:</label>
        <div class="controls">
        	<input type="hidden" name="picUrl" value="${entity.picUrl }" id="picUrl" class="required"/>
        	<input type="file" id="ajaxUploadFfile" name="file" class="btn btn-primary" onchange="ajaxSaveImage()" />
        	<img alt="暂无" src="" style="width: 200px;height: 160px;" id="showimg">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">原文URL:</label>
        <div class="controls">
        	<input type="text" name="url" value="${entity.url }" class="required"/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="user:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
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
/*         	alert(json[0]+ json[1]); */
        	}
        }
    });
}
</script>
</body>
</html>