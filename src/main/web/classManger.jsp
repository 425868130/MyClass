<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <style>
        th, td {
            text-align: center;
            font-size: 18px;
        }
    </style>
</head>
<body>
<jsp:include page="pageHeader.jsp"/>
<div class="container">

    <table class="table table-striped" style="text-align: right">
        <caption style="font-size: 20px">在线情况</caption>
        <thead>
        <tr>
            <th style="text-align: center">用户ID</th>
            <th  style="text-align: center">昵称</th>
            <th  style="text-align: center">最近登录</th>
            <th  style="text-align: center">登录次数</th>
            <th  style="text-align: center">当前在线</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

</div>
<jsp:include page="footer.jsp"/>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/classManger.js"></script>
</body>
</html>