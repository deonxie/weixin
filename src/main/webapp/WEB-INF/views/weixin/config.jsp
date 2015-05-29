<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>微信配置管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a>配置信息</a></li>
 
</ul>
<br/>

<form id="inputForm" action="${ctx}${baseMapper}save" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${entity.id}"/>
    <tags:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">令牌(token):</label>
        <div class="controls">
            <input type="text" class="required"
                   name="token" value="${entity.token}">
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">应用ID:</label>

        <div class="controls">
            <input type="text" class="required" 
                   name="appid" value="${entity.appid}">
        </div>
    </div>
	<div class="control-group">
        <label class="control-label">应用密钥:</label>

        <div class="controls">
            <input type="password" class="required" 
                   name="secret" value="${entity.secret}">
        </div>
    </div>
    
    <div class="form-actions"><shiro:hasPermission name="wxconfig:edit">
       <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
    </shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>