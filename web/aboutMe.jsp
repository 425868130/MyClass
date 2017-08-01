<%@ page import="Entities.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.User_Dao" %>
<%@ page import="Entities.User" %>
<%@ page import="DAO.Message_Dao" %><%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Message> messageList = null;
    messageList = (List<Message>) request.getAttribute("PersonalMessage");
    while (messageList == null) {
        request.setAttribute("action", "PersonalMessage");
        request.setAttribute("backURL", "/aboutMe.jsp");
        request.getRequestDispatcher("/MessageServlet").forward(request, response);
        messageList = (List<Message>) request.getAttribute("PersonalMessage");
    }
    System.out.println(messageList.size());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>与我相关</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/aboutMe.css">
</head>
<body>
<jsp:include page="pageHeader.jsp"/>
<div class="container">
    <!--消息表-->
    <div class="col-lg-5">
        <div class="row" style="text-align: center">
            <span class="title"><span class="glyphicon glyphicon-comment"></span>&nbsp;&nbsp;评论和回复</span>
        </div>
        <!--第二行为消息主体-->
        <div class="row" style="margin-top: 30px">
            <ul id="MessageList" class="messageCell">
                <%
                    /*循环输出个人消息*/
                    for (Message msg : messageList) {
                        if (msg.getMessage_type().equals("USER")) {%>
                <!--消息列表单元-->
                <li>
                    <!--消息盒子-->
                    <div class="messageBox well" style="min-height: 120px;padding: 15px 20px 20px 25px;">
                        <p class="Mtime"><%=msg.getMessage_time()%>
                        </p>
                        <span class="userName"><%=User_Dao.getUserName(msg.getsend_id())%></span>
                        <span class="messageText">评论了你的动态 :</span>
                        <span class="messageContent messageText"><%=msg.getMessage_content()%></span>
                        <a href="#" class="jumpDetails"><span
                                class="glyphicon glyphicon-chevron-right"></span></a>
                    </div>
                </li>
                <%
                        }
                    }
                %>
            </ul>
        </div>

    </div>
    <!--竖直分割线-->
    <div class="col-lg-1">
        <hr style="width: 1px; height: 600px;background-color: black;margin-top: 70px"/>
    </div>

    <!--系统通知表-->
    <div class="col-lg-5">
        <div class="row" style="text-align: center">
            <span class="title"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;系统通知</span>
        </div>
        <!--系统通知主体内容-->
        <div class="row" style="margin-top: 30px">
            <ul style="list-style: none;margin-top: 10px;">
                <% //输出待处理的消息
                    User onlineUser = (User) request.getSession().getAttribute("UserSession");
                    /*判断在线用户是否为管理员*/
                    if (onlineUser.getlevel()) {
                        List<Message> AdminMsg = Message_Dao.getAdminMessage();
                        for (Message message : AdminMsg) {
                              /*如果为用户注册申请,则显示带按钮的消息列表*/
                            if (message.getMessage_type().equals("SYSTEM_ADMIN_R")) {%>
                <li class="well"><%=message.getMessage_content()%>
                    <div style="margin-left:270px;margin-top: 20px">
                        <%--存储待审核的用户ID--%>
                        <input class="HandlUerID" type="text" value="<%=message.getsend_id()%>" style="display: none">
                        <button class="btn btn-primary" onclick="ReguserPass(<%=message.getMessage_id()%>,this)">同意
                        </button>
                        <button class="btn btn-danger" onclick="ReguserRefuse(<%=message.getMessage_id()%>,this)">拒绝
                        </button>
                    </div>
                </li>
                <%
                            }/*if*/
                        }/*for*/
                    }/*if(onlineUser)*/
                    /*输出普通系统消息*/
                    for (Message msg : messageList) {
                        if (msg.getMessage_type().equals("SYSTEM")) {
                %>
                <li class="well"><%=msg.getMessage_content()%>
                </li>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/aboutMe.js"></script>
</body>
</html>