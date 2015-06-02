<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title>微官网</title>
    <meta name="decorator" content="mobile"/>
    <style type="text/css">
	.row div{padding: 10px;}
	img{width: 100%;}
	a:HOVER { text-decoration: none; }
    </style>
</head>
<body>
<div class="container-fluid">
<c:if test="${!empty topImgs }">
	<div id="mycarousel" class="carousel slide" data="carousel">
		<ol class="carousel-indicators">
		<c:forEach items="${topImgs }" var="bg" varStatus="index">
			<li data-target="#mycarousel" data-slide-to="${index.index }" class="${index.index eq 0 ?'active':''}"></li>
		</c:forEach>
		</ol>
		<div class="carousel-inner" role="listbox">
			<c:forEach items="${topImgs }" var="bg" varStatus="index">
			<div class="item ${index.index eq 0 ?'active':''}">
				<img src="${ctxStatic }${bg.imgPath}" alt="校园图集">
				<div class="carousel-caption">${bg.title }</div>
			</div>
			</c:forEach>
		</div>
		<a class="left carousel-control" href="#mycarousel" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">前一个</span>
		</a>
		<a class="right carousel-control" href="#mycarousel" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">下一个</span>
		</a>
	</div>
</c:if>	
<!-- items config -->
	<div class="container-fluid" style="padding: 10px;">
		<div class="row">
			<c:forEach items="${itemImgs }" var="item" varStatus="num">
			<div class="col-xs-4">
  				<a href="${item.url }">
		  			<div class="img-circle" style="background-color: ${item.color};padding: 10%;">
		  				<img alt="" src="${ctxStatic }${item.imgPath}"> </div>
		  			<div class="container-fluid" style="text-align: center;">${item.title }</div>
	  			</a>
	  		</div>
	  		<c:if test="${num.count %3 eq 0 }"></div><div class="row"></c:if>
			</c:forEach>
	  		<%-- <div class="col-xs-4 col-sm-2">
	  			<a href="${ctx }/business/weixin/query/">
		  			<div class="img-circle" style="background-color: orange;padding: 10%;">
		  				<img alt="" src="${ctxStatic }/images/read.png"> </div>
		  			<div class="container-fluid" style="text-align: center;">录取查询</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-4 col-sm-2">
	  			<a href="#">
		  			<div class="img-circle" style="background-color: #06c ;padding: 10%;">
		  				<img alt="" src="${ctxStatic }/images/letter.png"></div>
		  			<div class="container-fluid" style="text-align: center;">联系我们</div>
	  			</a>
	  		</div> --%>
	  	<%-- 	<div class="col-xs-4 col-sm-2">
	  			<a >
		  			<div class="img-circle" style="background-color: green ;padding: 10%;">
		  				<img alt="" src="${ctxStatic }/images/book.png"> </div>
		  			<div class="container-fluid" style="text-align: center;">book</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-4 col-sm-2">
	  			<a >
		  			<div class="img-circle" style="background-color: blue ;padding: 10%;">
		  				<img alt="" src="${ctxStatic }/images/read.png"> </div>
		  			<div class="container-fluid" style="text-align: center;">read</div>
	  			</a>
	  		</div>
	  		<div class="col-xs-4 col-sm-2">
	  			<a >
		  			<div class="img-circle" style="background-color:indigo;padding: 10%;">
		  				<img alt="" src="${ctxStatic }/images/path.png"> </div>
		  			<div class="container-fluid" style="text-align: center;">path</div>
	  			</a>
	  		</div> --%>
  	</div>
</div>
</body>
</html>