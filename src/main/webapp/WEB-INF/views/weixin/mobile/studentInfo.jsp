<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>录取查询</title>
    <meta name="decorator" content="default"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
   <style type="text/css">
      .myform-group { margin-bottom: 10px;text-align: center;}
    .myform-group >input {
	width: 100%;padding-left: 0px;padding-right: 0px;
}
   </style>
</head>
<body><br>
<div class="panel panel-primary">
  <div class="panel-heading"></div>
  <div class="panel-body" >
  	<div style="width: 90%;margin-left: auto;margin-right: auto;">
    <table class="table table-broder">
    	<tr><td width="35%"><b>考生姓名:</b></td><td width="65%">${entity.name}</td></tr>
    	<tr><td><b>考&nbsp; 生&nbsp; 号:</b></td><td>${entity.inspectNum}</td></tr>
    	<tr><td><b>身份证号:</b></td><td>${entity.icdNum}</td></tr>
    	<tr><td><b>录取院系:</b></td><td>${entity.type}</td></tr>
    	<tr><td><b>录取专业:</b></td><td>${entity.major}</td></tr>
    	<tr><td colspan="2" align="center"><b>录取通知书邮寄信息</b></td></tr>
    	<tr><td><b>收&nbsp; 件&nbsp; 人:</b></td><td>${entity.receiveName}</td></tr>
    	<tr><td><b>联系电话:</b></td><td>${entity.telNum}</td></tr>
    	<tr><td><b>邮寄地址:</b></td><td>${entity.address}</td></tr>
    	<tr><td><b>邮编:</b></td><td>${entity.postcode}</td></tr>
    	<tr><td colspan="2">
    	<input type="button" class="btn btn-primary" onclick="showupdate(this)" value="修正联系方式" 
    		style="width: 100%;margin-top: 15px;"/>
    	</td></tr>
    </table>
	</div>
  </div>
</div>
<tags:message content="${message}"/>
<div id="icdupdate" tag="0" class="panel panel-default" style="display: none;">
	<div class="panel-heading"></div>
	<div class="panel-body" >
		<form action="${ctx}${baseMapper}updateicd" method="post">
			<div style="width: 90%;margin-left: auto;margin-right: auto;">
				<div class="myform-group"><br>
					<input type="hidden" name="key" value="${entity.inspectNum }" />
					<input type="hidden" name="name" value="${entity.icdNum }" />
					<input type="text" name="receive" placeholder="请输入收件人" value="${entity.receiveName }" class="required"/>
				</div><div class="myform-group">
					<input type="text" name="telNum" placeholder="请输入联系电话" value="${entity.telNum}" class="required"/>
				</div><div class="myform-group">
					<input type="text" name="address" placeholder="请输入联系地址" value="${entity.address }" class="required"/>
				</div><div class="myform-group">
					<button type="submit" class="btn btn-primary" style="width: 100%;">保    存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	function showupdate(obj){
		$(obj).remove();
		$("#icdupdate").show();
	}
</script>
</body>
</html>