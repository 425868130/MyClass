<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--用户个人中心-->
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <style type="text/css">
        .InformText {
            font-size: 20px;
        }

        .InformRow {
            margin-top: 20px;
        }

        .userImg {
            width: 230px;
            height: 230px;
            border-radius: 230px;
        }

        input {
            margin-left: 10px;
        }

        .panel {
            padding-bottom: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="pageHeader.jsp"/>
<div class="panel panel-default">
    <!--面板头部-->
    <div class="panel-heading">
        <a class="pull-right" onclick="editInform()" style="cursor: pointer">
            <span class="glyphicon glyphicon-edit"
                  style="color: blue;font-size: 18px;margin-top: 10px">&nbsp;编辑个人信息</span>
        </a>
        <p class="panel-title" style="font-size: 25px"><span class="glyphicon glyphicon-user"></span>&nbsp;个人信息</p>
    </div>

    <!--面板主体-->
    <div class="panel-body col-lg-offset-1" style="padding: 0px">
        <div class="row" style="margin-left: 20px">
            <!--左侧头像区-->
            <div class="col-lg-2 col-md-2 col-sm-3 col-xs-4">
                <div class="row" style="margin-top: 30px">
                    <img src="images/Default/DefaultUserImg.jpg" class="userImg" id="userImg">
                </div>
                <div class="row" style="margin-top: 10px;margin-left: 30px;">
                    <button class="btn btn-default" id="changeImg" onclick="onBtnChangeImg()" style="display:none;"
                            data-toggle="modal" data-target="#userZoneModal">
                        <span class="glyphicon glyphicon-picture"></span>&nbsp;更换头像
                    </button>
                    <button class="btn btn-warning" id="changePsd" style="margin-left: 20px;display: none"
                            data-toggle="modal" data-target="#userZoneModal" onclick="onBtnChangePwd()">
                        <span class="glyphicon glyphicon-lock"></span>&nbsp;修改密码
                    </button>
                </div>
            </div>
            <!--用户基本信息单元-->
            <div class="col-lg-4" style="margin-left: 20px;">
                <!--编码时替换为form标签-->
                <div id="userInform">
                    <!--用户账号-->
                    <div class="row InformRow">
                    <span class="InformText">用户账号:&nbsp;&nbsp;
                        <span id="userCount"></span></span>
                    </div>
                    <!--用户昵称-->
                    <div class="row InformRow">
                    <span class="InformText">昵&nbsp;&nbsp;称:&nbsp;&nbsp;
                        <span id="userNick"></span></span>
                    </div>
                    <div class="row InformRow">
                    <span class="InformText">性&nbsp;&nbsp;别:&nbsp;&nbsp;
                        <span id="userSex"></span></span>
                    </div>
                    <div class="row InformRow">
                    <span class="InformText">联系方式:&nbsp;&nbsp;
                        <span id="userTel"></span></span>
                    </div>
                    <div class="row InformRow">
                    <span class="InformText">在线时长:&nbsp;&nbsp;
                        <span id="userLoginTime"></span></span>
                    </div>
                    <div class="row InformRow">

                    <span class="InformText">个性签名:&nbsp;&nbsp;
                        <span id="userSign"></span></span>
                        <!--默认隐藏的信息修改确认按钮-->
                        <button id="confirm" class="btn btn-success pull-right" style="display: none"
                                onclick="confirmEdit()">确定
                        </button>
                    </div>
                </div>
            </div>
        </div><!--row-->
    </div><!--panel-body-->

</div><!--panel-->

<!--密码修改模态框-->
<div class="modal fade" id="userZoneModal" tabindex="-1" aria-hidden="true" aria-labelledby="ModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 id="ModalLabel">密码修改</h3>
            </div>
            <div class="modal-body">
                <div id="ModalForm">

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnChangePwd" onclick="onModalSubmit()">提交更改</button>
            </div>
        </div>
    </div>

    <!--隐藏的模态窗口内容,用于js动态切换-->
    <!--状态1-->
    <div id="modalStatus1" style="display: none;">
        <div class="input-group" style="margin-top: 10px">
            <span class="input-group-addon">原密码:</span>
            <input class="form-control originalPwd" type="text" name="originalPwd" style="margin-left: 0px">
        </div>
        <div class="input-group" style="margin-top: 10px">
            <span class="input-group-addon">新密码:</span>
            <input class="form-control newPwd" type="text" name="newPwd" style="margin-left: 0px">
        </div>
        <div class="input-group" style="margin-top: 10px">
            <span class="input-group-addon">确认密码:</span>
            <input class="form-control confirmPwd" type="text" name="confirmPwd" style="margin-left: 0px">
        </div>
    </div>
    <!--状态2-->
    <div id="modalStatus2" style="display:none;">
        <form method="post" action="/UserImgUpload" enctype="multipart/form-data" class="userImgUpdate">
            <input type="file" name="uploadFile" accept="image/*"/>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/userZone.js"></script>
</body>
</html>
