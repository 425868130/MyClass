<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/4
  Time: 23:31
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
    <!--首页顶部图片及标题-->
    <div class="headerImage">
        <img src="images/logo_white.png" class="img-responsive">
        <span style="float: right;font-size: 30px; margin-top:-1%;color: white"><i>We Are Family</i></span>
    </div>
    <!--导航栏-->
    <div class="navbar navbar-inverse navbar-default" role="navigation">
        <div class="container ">
            <!--此处为网页缩小后右侧的汉堡按钮-->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="Dynamic.html" target="contentFrame" onclick="displayToolBars()">
                    <span class="glyphicon glyphicon-home">&nbsp;首页</span></a>
            </div>

            <div class="collapse navbar-collapse">
                <!--组件列表-->
                <ul class="nav navbar-nav navbar-left nav-list">
                    <li><a href="classAlbum.html" target="contentFrame" onclick="hideToolBars()">
                        <span class="glyphicon glyphicon-th">&nbsp;班级相册</span>
                    </a></li>
                    <li><a href="Dynamic.html" target="contentFrame" onclick="hideToolBars()">
                        <span class="glyphicon glyphicon-fire">&nbsp;热门动态</span>
                    </a></li>
                    <li><a href="classNotice.html" target="contentFrame" onclick="hideToolBars()">
                        <span class="glyphicon glyphicon-bullhorn">&nbsp;班级公告</span>
                    </a></li>
                    <li><a href="aboutMe.html" target="contentFrame" onclick="hideToolBars()">
                        <span class="glyphicon glyphicon-flag">&nbsp;与我相关</span>
                    </a></li>
                    <li><a href="UserZone.html" target="contentFrame" onclick="hideToolBars()">
                        <span class="glyphicon glyphicon-user">&nbsp;个人中心</span>
                    </a></li>
                    <!--班级管理只有管理员可见-->
                    <li><a href="classManger.html" target="contentFrame">
                        <span class="glyphicon glyphicon-leaf" onclick="hideToolBars()">&nbsp;我的班级</span>
                    </a></li>
                    <li><a href="index.html">
                        <span class="glyphicon glyphicon-log-out">&nbsp;账号注销</span>
                    </a></li>
                </ul>
            </div><!-- /.nav-collapse -->

        </div><!-- /.container -->

    </div><!--/.nvbar -->

    <!--快速发布框-->
    <div id="publish" style="display:block;">
        <iframe src="QuickDynamicTools.html" frameborder="0" seamless scrolling="no" id="publishFrame"
                name="publishFrame" height="300px">
        </iframe>
    </div>
    <!--公告面板-->
    <div id="notice">
        <iframe src="HomeNotice.html" frameborder="0" seamless scrolling="no" id="noticeFrame"
                style="width: 340px ;float: right;margin-right: 20px;margin-top: 70px" height="400px">
        </iframe>
    </div>

    <!--主体内容框-->
    <div id="content">
        <iframe src="Dynamic.jsp" frameborder="0" seamless scrolling="no" id="contentFrame"
                name="contentFrame" style="width:80%;" onload="iframeAdjust()">
        </iframe>
    </div>

    <!--页脚-->
    <div id="footer">
        <iframe src="footer.html" frameborder="0" seamless scrolling="no">
        </iframe>
    </div>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/homePage.js"></script>
</body>
</html>
