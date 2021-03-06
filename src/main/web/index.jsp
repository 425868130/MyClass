<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>请先登录</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/homePage.css">
    <link rel="stylesheet" href="css/UI/style.css">
    <style>
        .input-group-addon {
            width: 130px;
        }

        .input-group {
            padding: 0px;
        }
    </style>
</head>

<body>


<div class="cont_login">

    <div class="cont_info_log_sign_up">

        <!--卡片未展开时的登录框-->
        <div class="col_md_login">
            <div class="cont_ba_opcitiy">
                <h2>登录</h2>
                <p>我是班级成员,立即登录.</p>
                <button class="btn_login" onClick="cambiar_login()">登录</button>
            </div>
        </div>

        <!--卡片未展开时的注册框-->
        <div class="col_md_sign_up">
            <div class="cont_ba_opcitiy">
                <h2>注册</h2>
                <p>我要加入班级,立即注册.</p>
                <button class="btn_sign_up" onClick="cambiar_sign_up()">注册</button>
            </div>
        </div>

    </div>


    <div class="cont_back_info">
        <!--登录及注册卡片的的背景图-->
        <div class="cont_img_back_grey"><img src="images/login2.jpg" alt=""/></div>

    </div>
    <!--卡片展开后的样式-->
    <div class="cont_forms">
        <div class="cont_img_back_"><img src="images/login2.jpg" alt=""/></div>
        <!--卡片展开后的登录框-->
        <div class="cont_form_login"><a href="#" onClick="ocultar_login_sign_up()"><i class="material-icons">
            &#xE5C4;</i></a>
            <h2>登录</h2>
            <input type="text" id="LoginUserID" placeholder="账号"/>
            <input type="password" id="LoginUserPsd" placeholder="密码"/>
            <!--登录按钮，后续添加账号登录判断-->
            <input type="button" class="btn_login" onClick="login()" value="登录" style="text-align: center;color: white">
            <br/>
            <!--指向密码找回页面-->
            <a href="#forgetPsd" style="float: right" data-toggle="modal">忘记密码？</a>
        </div>

        <!--卡片展开后的注册框-->
        <div class="cont_form_sign_up"><a href="#" onclick="ocultar_login_sign_up()"><i class="material-icons">
            &#xE5C4;</i></a>
            <h2>注册</h2>
            <input type="text" placeholder="账号" id="RegCount">
            <input type="password" placeholder="密码" id="RegPsd">
            <input type="password" placeholder="确认密码" id="RegPsdConfirm">
            <input type="tel" placeholder="手机号码" id="Regtell">
            <!--账号注册功能实现-->
            <button class="btn_sign_up" onclick="regCount()">注册</button>
        </div>

    </div>
</div>

<!--忘记密码模态窗口-->
<div class="modal fade" id="forgetPsd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     style="margin-left: 45%;margin-top: 5%">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title pull-left" id="myModalLabel" style="color: red">密码重置<span
                        class="glyphicon glyphicon-question-sign"></span></h4>
            </div>
            <div class="modal-body">
                <p id="two" align="center">请如实填写一下信息帮助我们判断您的身份</p>
                <form id="forgetForm">
                    <div class="input-group">
                        <span class="input-group-addon">账号:</span>
                        <input type="text" class="form-control" name="fgUserName"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">昵称:</span>
                        <input type="text" class="form-control" name="fgNick"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">联系方式:</span>
                        <input type="text" class="form-control" name="fgTell"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">用过的其他密码:</span>
                        <input type="text" class="form-control" name="fgPsd"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">个人签名:</span>
                        <input type="text" class="form-control" name="fgSign"/>
                    </div>
                    <p id="three" align="center" style="margin-top: 20px">*部分信息如有遗忘可以留空.[用户名][联系方式必须填]</p>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="forgetSubmit()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>