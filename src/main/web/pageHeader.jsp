<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>班级主页</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/homePage.css">
</head>

<div>
    <!--首页顶部图片及标题-->
    <div id="headerImage">
        <img src="images/logo_white.png" class="img-responsive">
        <span style="float: right;font-size: 30px; margin-top:-1%;color: white"><i>We Are Family</i></span>
    </div>
    <!--导航栏-->
    <div class="navbar navbar-inverse navbar-default" role="navigation" style="background:#7aafbf; border: none">
        <div class="container ">
            <!--此处为网页缩小后右侧的汉堡按钮-->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="homePage.jsp"
                   onclick="displayToolBars()">
                    <span class="glyphicon glyphicon-home headerGlyphicon">&nbsp;首页</span></a>
            </div>

            <div class="collapse navbar-collapse">
                <!--组件列表-->
                <ul class="nav navbar-nav navbar-left nav-list">
                    <li><a href="classAlbum.jsp">
                        <span class="glyphicon glyphicon-th headerGlyphicon">&nbsp;班级相册</span>
                    </a></li>
                    <li><a href="classNotice.jsp">
                        <span class="glyphicon glyphicon-bullhorn headerGlyphicon">&nbsp;班级公告</span>
                    </a></li>
                    <li><a href="aboutMe.jsp">
                        <span class="glyphicon glyphicon-flag headerGlyphicon">&nbsp;与我相关</span>
                    </a></li>
                    <li><a href="UserZone.jsp">
                        <span class="glyphicon glyphicon-user headerGlyphicon">&nbsp;个人中心</span>
                    </a></li>
                    <!--班级管理只有管理员可见-->
                    <li><a href="classManger.jsp">
                        <span class="glyphicon glyphicon-leaf headerGlyphicon"
                              onclick="hideToolBars()">&nbsp;我的班级</span>
                    </a></li>
                    <li><a onclick="UserLogout()" href="index.jsp" style="cursor: pointer">
                        <span class="glyphicon glyphicon-log-out headerGlyphicon">&nbsp;账号注销</span>
                    </a></li>
                </ul>
            </div><!-- /.nav-collapse -->

        </div><!-- /.container -->

    </div><!--/.nvbar -->
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/homePage.js"></script>
