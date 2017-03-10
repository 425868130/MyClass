<%@ page import="Utils.JDBCUtils" %>
<%@ page import="Entities.User" %>
<%@ page import="DAO.User_Dao" %><%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/8
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><%=User_Dao.HasUser("xjwasdasdad")
%>
</p>
</body>
</html>
