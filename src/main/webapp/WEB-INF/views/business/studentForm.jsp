<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
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
    <li><a href="${ctx}${baseMapper}">学生信息列表</a></li>
	<li class="active"><a>学生信息添加</a></li>
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <input type="text" class="required" name="name" value="${entity.name}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">考生号:</label>
        <div class="controls">
            <input type="text" class="required" name="inspectNum" value="${entity.inspectNum}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">身份证号:</label>
        <div class="controls">
            <input type="text" class="required" name="icdNum" value="${entity.icdNum}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">录取院系:</label>

        <div class="controls">
            <input type="text" class="required" name="type" value="${entity.type}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">录取专业:</label>
        <div class="controls">
            <input type="text" class="required" name="major" value="${entity.major}"/>
        </div>
    </div>
	<div class="control-group">
        <label class="control-label">联系电话:</label>
        <div class="controls">
            <input type="text" class="required" name="telNum" value="${entity.telNum}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">地址:</label>
        <div class="controls">
            <input type="text" class="required" name="address" value="${entity.address}"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">邮编:</label>
        <div class="controls">
            <input type="text" class="required" name="postcode" value="${entity.postcode}"/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="user:edit">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>