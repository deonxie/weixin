<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>消息管理</title>
    <meta name="decorator" content="default"/>
    <link href="${ctxStatic}/ckeditor/ckeditor.js" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="${ctxStatic}/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${ctxStatic}/ckeditor/config.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-validation/1.11.1/lib/jquery.form.js"></script>
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
    <li><a href="${ctx}${baseMapper}">消息列表</a></li>
    <li class="active"><a href="#">用户
	    <shiro:hasPermission name="user:edit">${entity.id > 0 ? '修改':'添加'}</shiro:hasPermission>
	    <shiro:lacksPermission name="user:edit">查看</shiro:lacksPermission>
    </a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">标题:</label>
        <div class="controls">
            <input type="text" class="required" maxlength="64"
                   name="title" value="${entity.title}" placeholder="请输入标题">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">描述:</label>
        <div class="controls">
            <input type="text" class="required" name="digest" value="${entity.digest }"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">作者:</label>
        <div class="controls">
            <input type="text" class="required" name="author" value="${entity.author }" maxlength="8"/>
        </div>
    </div>
	<div class="control-group">
		<label class="control-label">封面图片:</label>
		<div class="controls">
			<input type="hidden" name="picPath" id="picPath" value="${entity.picPath }"/>
			<input type="button" value="选择图片" onclick="choosefile()" class="btn btn-info"/>
			<input type="button" value="上传" onclick="ajaxSumbit()" class="btn btn-info"/>
			<font color="red">注意：IE、360浏览器不支持，请使用Firefox、Safari、Google chrome浏览器操作</font>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">封面图片预览:</label>
		<div class="controls">
			<c:if test="${!empty entity.picPath }"><c:set var="imgpath" value="${ctx }${entity.picPath }"></c:set></c:if>
			<img alt="暂无" src="${imgpath }" id="previewimg" width="100"/>
		</div>
	</div>
    <div class="control-group">
        <label class="control-label">图文消息内容:</label>
        <div class="controls">
            <textarea class="ckeditor" name="content">${entity.content }</textarea>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="user:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
<form id="ajaxupload" action="${ctx }/file/updload/" enctype='multipart/form-data'>
	<input type="file" name="file" id="ajaxfile" style="display:none;"/>
</form>
<script type="text/javascript">
	function choosefile(){
		$("#ajaxfile").click();
	}
	function ajaxSumbit(){
		if($("#ajaxfile").val().length==0)
			return;
		$("#ajaxupload").ajaxSubmit({success:function(data){
			if(data[0]=='true'){
				$("#previewimg").attr("src",'${ctx}/tmp/'+data[1]);
				$("#picPath").val("/tmp/"+data[1]);
			}
		}});
     }
</script>
</body>
</html>