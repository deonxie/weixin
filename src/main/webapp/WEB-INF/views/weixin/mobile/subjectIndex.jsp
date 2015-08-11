<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp" %>
<html>
<head>
    <title><%=request.getParameter("subject") %></title>
    <meta name="decorator" content="mobile"/>
    <meta name="viewport" content="width=device-width"/>
   <style type="text/css">
   .row div{padding: 10px;}
   </style>
</head>
<body>
<div class="container-fluid">

	 <% if("计算机工程技术学院".equals(request.getParameter("subject"))){ %>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院.jpg"/>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院1.jpg"/>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院2.jpg"/>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院3.jpg"/>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院4.jpg"/>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院5.jpg"/>
			<img alt="" src="${ctxStatic }/kegan/计算机工程技术学院6.jpg"/>
	 <%}else if("经济管理学院".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/经济管理学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/经济管理学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/经济管理学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/经济管理学院3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/经济管理学院4.jpg"/>
	 <%}else if("外国语学院".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/外国语学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/外国语学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/外国语学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/外国语学院3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/外国语学院4.jpg"/>
	 <%}else if("人文与社会科学学院".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/人文与社会科学学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/人文与社会科学学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/人文与社会科学学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/人文与社会科学学院3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/人文与社会科学学院4.jpg"/>
	 <%}else if("机械与电子工程学院".equals(request.getParameter("subject"))){ %>
	  	<img alt="" src="${ctxStatic }/kegan/机械与电子工程学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/机械与电子工程学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/机械与电子工程学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/机械与电子工程学院3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/机械与电子工程学院4.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/机械与电子工程学院5.jpg"/>
	 <%}else if("建筑工程学院".equals(request.getParameter("subject"))){ %>
	   	<img alt="" src="${ctxStatic }/kegan/建筑工程学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/建筑工程学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/建筑工程学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/建筑工程学院3.jpg"/>
	 <%}else if("艺术设计学院".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院4.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院6.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院7.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院8.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/艺术设计学院9.jpg"/>
	 <%}else if("财会与金融学院".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/财会与金融学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/财会与金融学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/财会与金融学院3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/财会与金融学院4.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/财会与金融学院5.jpg"/>
	 <%}else if("体育系".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/体育系.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/体育系1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/体育系3.jpg"/>
	 <%}else if("广州学院".equals(request.getParameter("subject"))){ %>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院4.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院5.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/广州学院6.jpg"/>
	<%}else if("计算机应用嵌入式".equals(request.getParameter("subject"))){%>
		<img alt="" src="${ctxStatic }/kegan/计算机应用嵌入式3+2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/计算机应用嵌入式3+21.jpg"/>
	<%}else if("招生计划".equals(request.getParameter("subject"))){%> 
		<img alt="" src="${ctxStatic }/kegan/招生计划.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/招生计划1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/招生计划2.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/招生计划3.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/招生计划4.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/招生计划5.jpg"/>
	<%}else if("2015招生章程".equals(request.getParameter("subject"))){%>
		<img alt="" src="${ctxStatic }/kegan/2015招生章程.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/2015招生章程1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/2015招生章程2.jpg"/>
	 <%}else if("2014各科录取分数".equals(request.getParameter("subject"))){%>
		<img alt="" src="${ctxStatic }/kegan/2014各科录取分数.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/2014各科录取分数1.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/2014各科录取分数2.jpg"/>	
	 	<img alt="" src="${ctxStatic }/kegan/2014各科录取分数3.jpg"/>	
	 <%}else if("2014毕业生就业".equals(request.getParameter("subject"))){%>
	 	<img alt="" src="${ctxStatic }/kegan/2014毕业生就业.jpg"/>
	 	<img alt="" src="${ctxStatic }/kegan/2014毕业生就业2.jpg"/>
	 <%}else if("联系我们".equals(request.getParameter("subject"))){%>
	 	<img alt="" src="${ctxStatic }/kegan/联系我们.jpg"/>
	 <%}else{ %> 
	 <div class="panel panel-default" style="background-color: #7bc841;">
		<img alt="" src="${ctxStatic }/images/kegan_logo.jpg" style="width: 100%;"/>
	 	<div class="panel-heading">专业介绍</div>
	 	<div class="panel-body">
			 <div class="row"><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=计算机工程技术学院">计算机工程技术学院</a>
			 	</div><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=经济管理学院">经济管理学院</a>
			 </div></div><div class="row"><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=外国语学院">外国语学院</a>
			 	</div><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=人文与社会科学学院">人文与社会科学学院</a>
			</div></div><div class="row"><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=机械与电子工程学院">机械与电子工程学院</a>
			 	</div><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=建筑工程学院">建筑工程学院</a>
			</div></div><div class="row"><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=艺术设计学院">艺术设计学院</a>
			 	</div><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=财会与金融学院">财会与金融学院</a>
			</div></div><div class="row"><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=体育系">体育系</a>
			 	</div><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=广州学院">广州学院</a>
			</div></div><div class="row"><div class="col-xs-6">
			 		<a href="${ctx }/mobile/menu?name=subjectIndex&subject=计算机应用嵌入式">计算机应用嵌入式</a>
			 	</div>
			</div>
		</div>
		<div class="panel-heading">招生与就业</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-6">
					<a href="${ctx }/mobile/menu?name=subjectIndex&subject=招生计划">招生计划</a>
				</div><div class="col-xs-6">
					<a href="${ctx }/mobile/menu?name=subjectIndex&subject=2015招生章程">2015招生章程</a>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6">
					<a href="${ctx }/mobile/menu?name=subjectIndex&subject=2014各科录取分数">2014各科录取分数</a>
				</div><div class="col-xs-6">
					<a href="${ctx }/mobile/menu?name=subjectIndex&subject=2014毕业生就业">2014毕业生就业</a>
				</div>
			</div>
		</div>
	 </div>
	 
	 <%} %>
</div>
</body>
</html>