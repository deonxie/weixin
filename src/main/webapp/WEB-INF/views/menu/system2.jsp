<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<html>
<head>
    <title>菜单导航</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".accordion-heading a").click(function () {
                $('.accordion-toggle i').removeClass('icon-chevron-down');
                $('.accordion-toggle i').addClass('icon-chevron-right');
                if (!$($(this).attr('href')).hasClass('in')) {
                    $(this).children('i').removeClass('icon-chevron-right');
                    $(this).children('i').addClass('icon-chevron-down');
                }
            });
            $(".accordion-body a").click(function () {
                $("#menu li").removeClass("active");
                $("#menu li i").removeClass("icon-white");
                $(this).parent().addClass("active");
                $(this).children("i").addClass("icon-white");
            });
            $(".accordion-body a:first i").click();
        });
    </script>
</head>
<body>
<div class="accordion" id="menu">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse1"><i
                    class="icon-chevron-down"></i>&nbsp;学生信息管理</a>
        </div>
        <div id="collapse1" class="accordion-body collapse in">
            <div class="accordion-inner">
                <ul class="nav nav-list">
                    <li><a href="${ctx}/business/stu/" target="mainFrame"><i class="icon-heart icon-white"></i>&nbsp;学生信息</a></li>
                   <%--  <li><a href="${ctx}/business/stu/passlist" target="mainFrame"><i class="icon-heart icon-white"></i>学生审核信息</a></li> --%>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
