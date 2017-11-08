<%@ page import="Entities.Dynamic" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.User_Dao" %>
<%@ page import="DAO.Album_Dao" %>
<%@ page import="DAO.Dynamic_Dao" %>
<%@ page import="Entities.Message" %>
<%@ page import="Entities.Photo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Dynamic> dynamicList;
    dynamicList = (List<Dynamic>) request.getAttribute("DynamicList");
    while (dynamicList == null) {
        request.setAttribute("action", "LoadDynamic");
        request.setAttribute("backURL", "/Dynamic.jsp");
        request.getRequestDispatcher("/DynamicServlet").forward(request, response);
        dynamicList = (List<Dynamic>) request.getAttribute("DynamicList");
    }

%>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/DynamicTest.css"/>
</head>
<!--动态列表容器-->
<div class="container" style="margin: auto;" id="contentContainer">
    <%
        for (int i = dynamicList.size() - 1; i >= 0; i--) {
            Dynamic dynamic = dynamicList.get(i);
    %>
    <!--动态面板容器-->
    <div class="jumbotron col-lg-11 col-lg-offset-1">
        <!--隐藏的用于获取动态id的标签-->
        <input name="dynamicId" class="dynamicId" type="text" value="<%=dynamic.getAlbum_id()%>"
               style="display: none">
        <!--第一行显示用户头像、用户昵称、照片上传的相册-->
        <div class="row">
            <!--用户头像-->
            <span class="col-lg-2"> <img src="<%=User_Dao.getUser(dynamic.getUser_id()).gethead_portiait()%>"
                                         class="img-circle img-responsive userImage"></span>
            <!--动态相关信息-->
            <span class="col-lg-8" style="padding-left: 0px;">
                <p class="DynamicInfo dynamicUserName"><%=User_Dao.getUserName(dynamic.getUser_id())%></p>
                <p class="DynamicInfo">上传照片到《<span style="color: blue"
                                                   class="dynamicAlbum"><%=Album_Dao.getAlbum(dynamic.getAlbum_id()).getAlbum_name()%></span>》相册</p>
                <span class="dynamicTime"><%=dynamic.getDynamic_time()%></span>
            </span>
        </div><!--row 1-->

        <!--第二行显示动态文本-->
        <div class="row">
            <span class="col-lg-9 col-lg-offset-1 DynamicContents">
                <p class="dynamicText"><%=dynamic.getDynamic_content()%></p>
            </span>
        </div><!--row 2-->
        <div class="row">
            <%
                /*获取动态照片,每行输出3张照片*/
                List<Photo> photoList = Dynamic_Dao.DynamicPhoto(dynamic.getDynamic_id());
            /*最多显示6张照片*/
                int max = (photoList.size() > 6) ? 6 : photoList.size();
                for (int p = 0; p < max; p++) {
                    Photo photo = photoList.get(p);
            %>
            <!--第三行显示照片列表-->
            <div class="col-lg-3 col-lg-offset-1 SinglePhoto" data-toggle="modal" data-target="#photoView">
                <img src="<%=photo.getsave()%>" onclick="photoView(this)"
                     class="img-responsive img-rounded dynamicPhotos"
                     style="cursor: pointer">
            </div>
            <%
                }//for p
            %></div>
        <!--第五行显示动态的交互按钮-->
        <div class="row" style="margin-top: 10px">
            <!--点赞按钮-->
            <span class="glyphicon glyphicon-thumbs-up col-lg-offset-9 thUpImg" id="thumpBtnID<%=i%>"
                  style="color: gray;cursor: pointer; font-size: 20px;"
                  onclick="thumbsUp_JSP(<%=dynamic.getDynamic_id()%>,this);"><span
                    style="font-size: 16px;color: black;">赞</span>
                <span class="thumbsNum"
                      style="font-size: 16px;color: black;"><%=Dynamic_Dao.getDynamicLik(dynamic.getDynamic_id())%></span>
                <!--默认隐藏的输入框,用于取值判断该动态是否已赞-->
                <input class="isThumbs" value="false" style="display: none">
            </span>

            <!--评论按钮-->
            <button style="background-color: transparent;" class="noneGround btnComment"
                    data-toggle="collapse" data-target="#commentPanel<%=i%>">
                <span class="glyphicon glyphicon-comment" style="color: gray; font-size: 20px;"></span>&nbsp;评论
            </button>
        </div>
        <!--默认隐藏的评论输入面板,由评论按钮触发显示-->
        <div class="panel panel-default"
             style="background-color: transparent; border:none;border-top: 0.3px solid #d6d6d6;">
            <!--collapse类可将面板默认设为折叠-->
            <div class="panel-collapse collapse commentPanel" id="commentPanel<%=i%>">
                <div class="panel-body col-lg-offset-1">
                    <input type="text" class="input-lg col-lg-9 commentInput" style="outline: none;"
                           placeholder="说点什么吧....">
                    <!--发送按钮-->
                    <button class="btn btn-primary btn-lg noneGround btnSend" style="margin-left: 10px;"
                            onclick="sendComment(<%=dynamic.getDynamic_id()%>,this)">
                        <span class="glyphicon glyphicon-send" style="color: white; font-size: 20px;"></span>
                        &nbsp;发送
                    </button>
                </div>
            </div>
        </div><!--评论面板结束-->

        <!--第七行显示用户评论内容-->
        <div class="row commentList">
            <%
                /*加载动态评论*/
                List<Message> commentList = Dynamic_Dao.DynamicMessage(dynamic.getDynamic_id());
                for (Message comment : commentList) {
                    /*如果消息接受人id等于动态发送者的id则使用隐藏接受人id*/
                    if (comment.getreceive_id().trim().equals(dynamic.getUser_id().trim())) {
            %>
            <!--评论-->
            <div class="col-lg-offset-1">
                <span class="userName"><%=User_Dao.getUserName(comment.getsend_id())%></span>&nbsp;:&nbsp;
                <span class="commentText"><%=comment.getMessage_content()%></span>
            </div>
            <%
                /*否则以回复的形式显示*/
            } else {
            %>
            <div class="col-lg-offset-1">
                <span class="userName"><%=User_Dao.getUserName(comment.getsend_id())%></span>&nbsp;回复&nbsp;
                <span class="userName"><%=User_Dao.getUserName(comment.getreceive_id())%></span>&nbsp;:&nbsp;
                <span class="commentText"><%=comment.getMessage_content()%></span>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div><!--jumbotron-->
    <%
        }
    %>
</div>

<!--点击照片后的放大预览框-->
<div class="modal fade" id="photoView" tabindex="-1" aria-hidden="true" aria-labelledby="title">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="title">照片预览</h4>
            </div>
            <!--主体窗-->
            <div class="modal-body">
                <img src="#" class="img-responsive img-rounded" id="Preview">
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/Dynamic.js"></script>