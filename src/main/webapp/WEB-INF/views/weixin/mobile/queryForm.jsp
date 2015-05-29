<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>录取查询</title>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style type="text/css">
    .myform-group { margin-bottom: 15px;text-align: center;}
    .myform-group >input {width: 90%;padding-left: 0px;padding-right: 0px;}
    .mydiv{background-image: url("${ctxStatic }/images/kegan_bg.jpg");background-repeat: no-repeat;
    background-size:100% 100%;}
    </style>
</head>
<body>
<div class="panel panel-default">
  <div class="panel-heading">
  </div>
  <div class="panel-body" ><br>
<form action="${ctx}${baseMapper}passquery" method="post">
  <div class="mydiv" style="width: 90%;margin-left: auto;margin-right: auto;" >
  	<img alt="logo" src="${ctxStatic }/images/kegan_logo.jpg" width="100%" style="width: 100%;">
	<div class="myform-group"><br>
	<input type="text" name="key" placeholder="请输入考生号" class="required"/>
	</div><div class="myform-group">
	<input type="text" name="name" placeholder="请输入身份证号码" class="required"/>
	</div><div class="myform-group">
	<button type="submit" class="btn btn-primary" style="width: 90%;">查   询</button>
	</div><div class="myform-group">
  	<font color="red">${message }</font>
	</div>
  </div>
</form>
   
  </div>
</div>
</body>
</html>