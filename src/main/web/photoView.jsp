<%@ page import="java.util.List" %>
<%@ page import="Entities.Photo" %>
<%@ page import="DAO.Album_Dao" %><%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*获取相册页面传递的相册ID参数*/
    int albumId = Integer.parseInt(request.getParameter("openAlbumID"));
    /*获取相册内的全部照片*/
    List<Photo> almPhotoList = Album_Dao.AlbumPhoto(albumId);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>照片列表</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/photoView.css">
</head>
<body>
<jsp:include page="pageHeader.jsp"/>
<!--面板容器-->
<div class="panel panel-default">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-arrow-left returnImg" onclick="returnAlbum(this)">&nbsp;返回</span>
        <span class="glyphicon glyphicon-plus addPhoto" data-toggle="modal"
              data-target="#updatePhotoModal">&nbsp;上传</span>
        <!--面板标题-->
        <h3 class="panel-title"><%=Album_Dao.getAlbum(albumId).getAlbum_name()%>
        </h3>
    </div>
    <div class="panel-body">
        <!--照片列表容器-->
        <div class="col-lg-offset-1" id="photoContainer">
            <%
                if (almPhotoList.size() == 0) {%>
            <div style="text-align: center;min-height: 300px;margin-top: 90px;max-width: 800px;margin-left: 20%">
                <span class="glyphicon glyphicon-exclamation-sign"
                      style="color:#fac255;font-size: 120px;"></span><br>
                <p style="margin-top: 40px;font-size: 20px;color: gray">暂无照片,快点击右上角&nbsp;<span
                        style="font-size: 30px">+</span>&nbsp;上传照片吧</p>
            </div>
            <%
                }

                for (int i = 0; i < almPhotoList.size(); i++) {
                    Photo photo = almPhotoList.get(i);
            %>
            <div class="col-lg-6 photoDIV" data-toggle="modal" data-target="#photoView"
                 onclick="preViewPhoto(this)">
                <!--显示上传日期及删除操作的蒙板-->
                <div class="floatPanel">
                    <span class="glyphicon glyphicon-trash remove"
                          onclick="removePhoto(<%=photo.getPhoto_id()%>,this)"></span>
                    <p class="Uptime"><%=photo.getPhoto_time()%>
                    </p>
                </div>
                <!--隐藏的照片id-->
                <input class="photoID" style="display: none" value="">
                <img src="<%=photo.getsave()%>" class="img-rounded img-responsive photo" style="max-height: 230px;max-width: 400px">
            </div>
            <%

                }
            %>
        </div>
    </div>
</div>

<!--点击照片后的放大预览框-->
<div class="modal fade" id="photoView" tabindex="-1" aria-hidden="true" aria-labelledby="title">
    <div class="modal-dialog" id="modal-dialog">
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

<!--上传照片的模态框-->
<div class="modal fade" id="updatePhotoModal" tabindex="-1" aria-hidden="true" aria-labelledby="title">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="titleUpdate">上传照片</h4>
            </div>
            <!--主体窗-->
            <div class="modal-body">
                <form method="post" action="/UploadServlet?Album=<%=albumId%>" enctype="multipart/form-data">
                    选择一个文件:
                    <input type="file" name="uploadFile"/>
                    <br/><br/>
                    <input type="submit" value="上传"/>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/photoView.js"></script>
</body>
</html>
