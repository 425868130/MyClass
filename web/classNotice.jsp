<%--
  Created by IntelliJ IDEA.
  User: Dream Sky
  Date: 2017/3/12
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <title>班级公告</title>
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="css/UI/classNotice.css">
</head>
<body>
<jsp:include page="pageHeader.jsp"/>

<!--公告发布按钮，仅管理员可见-->
<div style="float: right;margin-right: 50px;margin-top: 20px; display: none" id="BtnNotice">
    <a data-toggle="modal" data-target="#NoticeModal" style="outline: none;">
        <span class="glyphicon glyphicon-edit" style="font-size: 30px">发布公告</span></a>
</div>
<!--分割线-->
<div class="col-lg-11">
    <h1 id="NoticeTitle">全部公告</h1>
    <hr/>
</div>
<div id="NoticeList">

</div>
<div id="pageSelect" class="col-lg-4">
    <ul class="pager">
        <li class="previous" id="previousPage"><a onclick="prePage()">上一页</a></li>
        <li class="next" id="nextPage"><a onclick="nextPage()">下一页</a></li>
    </ul>
</div>
<!--公告主体-->
<div id="NoticeCell" style="display: none">
    <div class="jumbotron col-lg-5 NoticeJumbotron">
        <h3 class="h3Title"></h3>
        <hr/>
        <span class="NoticeContent"></span>
        <br>
        <span class="NoticeTime"></span>
        <span class="Publisher"></span>
    </div>

</div>
<!--发布公告模态窗口-->
<div class="modal fade" id="NoticeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-bullhorn"></span>&nbsp;发布公告
                </h4>
            </div>
            <!--窗口主体-->
            <div class="modal-body">
                <form>
                    <div class="input-group">
                        <span class="input-group-addon">公告标题:</span>
                        <input class="form-control" type="text" id="InputATitle">
                    </div>

                    <div class="input-group" style="margin-top: 20px">
                        <span class="input-group-addon">公告内容:</span>
                        <textarea class="form-control" type="text" id="InputAContent"></textarea>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="publishNotice()">立即发布</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/classNotice.js"></script>
</body>
</html>
