<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 0:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/QuickDynamicTools.css">
</head>
<!--本页面为动态的快速发表工具-->

<div class="jumbotron jumbotronDyTools">
    <div class="row">
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">
            <img class="img-responsive img-circle simpleUerImg" style="width: 160px;height: 160px;border-radius: 160px;margin-top: -40px">
        </div>
        <div class="col-lg-5">
            <!--用户昵称-->
            <div class="row" style="margin-top: 2%">
                <span class="simpleUName" style="font-size:30px;"></span>
            </div>
            <div class="row">
                <!--用户签名-->
                <p class="ellipsisText simpleSign" style="margin-top: 2%;color: gray;"></p>
            </div>
        </div>
    </div>

    <div class="row" style="padding: 5px">
        <!--输入框组-->
        <div class="input-group">
            <input type="text" class="form-control input-lg" placeholder="说点什么吧......" id="InputDynamicContent">
            <span class="input-group-btn">
                <!--添加照片的按钮-->
                <button class="btn btn-default btn-lg" data-toggle="collapse" href="#UploadImg"
                        data-parent="#accordion">
                    <span class="glyphicon glyphicon-camera" style="padding-top: 0px"></span>&nbsp;添加照片
                </button>
                <!--选择相册的按钮,附加一个下拉菜单用于选择相册-->
                <button class="btn btn-default btn-lg dropdown-toggle" type="button" data-toggle="dropdown"
                        onclick="selectAlbum()">
                    <span class="glyphicon glyphicon-paperclip" style="padding-top: 0px"></span>&nbsp;选择相册&nbsp;<span
                        class="caret"></span>
                </button>
                <ul class="dropdown dropdown-menu pull-right" id="AlbumList">
                </ul>
            </span>
        </div>

        <p id="AlbumTips">已选相册:&nbsp;<span
                style="color: blue;min-width: 200px" id="selectedAlbum"></span></p>
        <button class="btn btn-primary btn-lg submit pull-right" onclick="publishDynamic();upload();">发表
        </button>

        <!--照片预览面板-->
        <div class="panel panel-default" id="selectFile">
            <div id="UploadImg" class="collapse panel-collapse">
                <!--面板内容区-->
                <div class="panel-body">
                    <form method="post" enctype="multipart/form-data" id="ImgFileFrom">
                        <input type="file" multiple="multiple" id="homePageFileLoad" accept="image/*" name="uploadFile">
                    </form>
                </div>
            </div>
        </div><!--panel-->
    </div>
</div>

<%--隐藏的用于动态生成相册列表的元素--%>
<div style="display: none" id="listCell">
    <li><a href="#"></a><span style="display: none"></span></li>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/QuickDynamicTools.js"></script>
