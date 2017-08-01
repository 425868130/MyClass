<%@ page import="Entities.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.Album_Dao" %><%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*获取相册列表*/
    List<Album> albumList = Album_Dao.AllAlbum();
%>
<!DOCTYPE html>
<html lang="en">
<!--本页面为班级相册页面-->
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>班级相册</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/classAlbum.css">
</head>
<body>
<%@include file="pageHeader.jsp" %>
<div class="container" style="min-width: 80%;margin-left: 15%">
    <!--新建相册按钮-->
    <div class="row col-lg-11">
        <button class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalAlbum" onclick="setCreateFlag()">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;新建相册
        </button>
    </div>
    <div class="row" class="albumContainer">
        <%
            for (int i = 0; i < albumList.size(); i++) {
                Album album = albumList.get(i);
        %>
        <!--单个相册-->
        <div class="col-lg-2 albumDIV">
            <!--相册上的浮动菜单-->
            <div class="albumMenu">
                <span class="glyphicon glyphicon-remove pull-right floatMenu"
                      onclick="removeAlbum(<%=album.getAlbum_id()%>,this);"></span>
                <span class="glyphicon glyphicon-edit pull-left floatMenu"
                      data-toggle="modal" data-target="#modalAlbum"
                      onclick="setEditFlag(<%=album.getAlbum_id()%>);"></span>
            </div>
            <!--相册缩略图-->
            <a href="photoView.jsp?openAlbumID=<%=album.getAlbum_id()%>" class="albumImg">
                <img src="<%=album.getCover_photo()%>" class="img-thumbnail img-responsive"
                     style="padding: 0px;margin: 0px">
            </a>
            <!--相册名称-->
            <span style="font-size: 15px"><%=album.getAlbum_name()%></span><br>
            <!--相册创建时间-->
            <span style="color: gray">创建时间:&nbsp;<%=album.getAlbum_time()%></span>
        </div><!--albumDIV-->
        <%
            }//for i
        %>
    </div>
</div><!-- container -->

<!-- 新建相册模态框（Modal） -->
<div class="modal fade" id="modalAlbum" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="margin-top: 200px">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-picture">&nbsp;<span
                        id="modalTitle">新建相册</span></span>
                </h4>
            </div>
            <!--窗口主体-->
            <form class="modal-body">
                <!--相册标题输入框-->
                <div class="input-group" style="margin-left: 30px">
                    <span class="input-group-addon">相册名称:&nbsp;&nbsp;</span>
                    <input type="text" class="form-control" style="max-width: 400px;" id="Input_AlbumName">
                </div>

                <!--相册描述输入框-->
                <div class="input-group" style="margin-top: 20px;margin-left: 30px">
                    <span class="input-group-addon">相册描述:&nbsp;&nbsp;</span>
                    <textarea type="text" class="form-control" id="Input_AlbumText"
                              style="max-width: 400px;max-height: 150px;height: 50px;"></textarea>
                </div>
            </form>
            <!--窗体底部-->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button onclick="onSubmit()" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<jsp:include page="footer.jsp"/>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/classAlbum.js"></script>
</body>
</html>