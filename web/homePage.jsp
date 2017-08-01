<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/10
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>班级主页</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/homePage.css">
</head>
<body>
<div>
    <jsp:include page="pageHeader.jsp"/>
    <!--快速发布框-->
    <div id="publish" style="display:block;margin-top: -15px">
        <jsp:include page="QuickDynamicTools.jsp"/>
    </div>
    <!--公告面板-->
    <div id="notice" style="width: 340px ;float: right;margin-right: 20px;margin-top: 100px" height="400px">
        <jsp:include page="HomeNotice.jsp"/>
    </div>

    <!--主体内容框-->
    <div id="content" style="width:85%;">
        <jsp:include page="Dynamic.jsp"/>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/homePage.js"></script>
</body>
</html>
