/**
 * Created by Dream Sky on 2017/3/4.
 */
/*点赞标识*/

$(function () {
    //loadDynamicContent();
    //window.parent.iframeAdjust();
})
/*加载动态内容*/
function loadDynamicContent() {
    /*获取动态容器*/
    var Container = $("#contentContainer");
    /*获取要处理的动态单元*/
    var dynamicCell = $("#dynamicCell");

    /*获取要处理的元素,通过父元素的find方法往下查找*/
    var replace_thUpImg = dynamicCell.find(".thUpImg"); //点赞图标
    var replace_isThumbs = dynamicCell.find(".isThumbs");   //是否点赞
    var replace_btnComment = dynamicCell.find(".btnComment");   //评论按钮
    var replace_commentPanel = dynamicCell.find(".commentPanel");//评论面板
    var replace_btnSend = dynamicCell.find(".btnSend");//发送按钮

    /*获取内容替换位置*/
    var userImage = dynamicCell.find(".userImage");
    var dynamicUserName = dynamicCell.find(".dynamicUserName");
    var dynamicAlbum = dynamicCell.find(".dynamicAlbum");
    var dynamicTime = dynamicCell.find(".dynamicTime");
    var dynamicText = dynamicCell.find(".dynamicText");
    var thumbsNum = dynamicCell.find(".thumbsNum");
    var dynamicId = dynamicCell.find(".dynamicId");//动态id

    /*从服务器获取数据*/
    $.ajax(
        {
            url: "/DynamicServlet",
            type: "POST",
            dataType: "json",
            cache: "false",
            async: "false",
            data: {
                "action": "LoadDynamic"
            },
            success: function (data) {
                var ArryAlbum_id = [];
                var returnVal = eval(data);
                $.each(returnVal, function (i) {
                    /*修改属性*/
                    replace_thUpImg.attr("id", "thUpImg" + i);
                    replace_isThumbs.val("false");
                    replace_btnComment.attr("href", "#commentCollapse" + i);
                    replace_commentPanel.attr("id", "commentCollapse" + i);
                    /*装填数据*/
                    //dynamicId.val(returnVal[i].Dynamic_id);
                    dynamicId.attr("value", returnVal[i].Dynamic_id);
                    dynamicUserName.html(returnVal[i].User_id);
                    dynamicText.html(returnVal[i].Dynamic_content);
                    /*记录相册ID*/
                    ArryAlbum_id.push(returnVal[i].Album_id);
                    dynamicTime.html(returnVal[i].Dynamic_time);
                    thumbsNum.html(returnVal[i].like);
                    /*绑定处理事件*/
                    /*动态修改id*/
                    dynamicAlbum.attr("id", "dynamicAlbum" + i);
                    Container.delegate("#thUpImg" + i, "click", thumbsUp);
                    /*追加装载动态单元*/
                    Container.append(dynamicCell.html());
                });
                /*加载相册名称*/
                getAlbumName(ArryAlbum_id);
            },
            error: function () {
                alert("网络出错,加载失败");
            }
        }
    );
}

/*获取相册名称*/
function getAlbumName(ArryAlbum_id) {
    $.each(ArryAlbum_id, function (i) {
        $.ajax({
            url: "/AlbumServlet",
            type: "POST",
            dataType: "text",
            cache: "false",
            data: {
                "action": "GetAlbumName",
                "AlbumID": ArryAlbum_id[i]
            },
            success: function (data) {
                $("#dynamicAlbum" + i).html(data);
                return data.trim();
            },
            error: function () {
                return "";
            }
        });
    });
}

/*照片放大预览*/
function photoView(photo) {
    $("#Preview").attr("src", $(photo).attr("src"));
}

/*处理动态单元*/
function HandlerDyCell(Num) {
    /*修改属性*/
    replace_thUpImg.attr("id", "thUpImg" + Num);
    replace_isThumbs.val("false");
    replace_btnComment.attr("href", "#commentCollapse" + Num);
    replace_commentPanel.attr("id", "commentCollapse" + Num);
    /*绑定处理事件*/
    //点赞
    Container.delegate("#thUpImg" + Num, "click", thumbsUp);
    /*追加装载动态单元*/
    Container.append(dynamicCell.html());
}

/*加载评论*/
function loadComment() {
    $.ajax({
        url: "/MessageServlet",
        type: "POST",
        dataType: "json",
        nocache: "false",
        data: {
            "action": "getDynamicComment"
        },
        success: function (data) {
            var returnVal = eval(data);
            $.each(returnVal, function (i) {

            });
        },
        error: function () {
        }
    });
}

/*加载动态图片*/
function loadDynamicImg() {
}


/*发送评论*/
function sendComment(dynamicID, btn) {
    /*查找输入框*/
    var CommentText = $(btn).parent().find(".commentInput");
    $.ajax({
        url: "/MessageServlet",
        type: "POST",
        dataType: "text",
        nocache: "false",
        data: {
            "action": "SendMessage",
            "MessageContent": CommentText.val(),
            "DynamicID": dynamicID,
            "MessageType": "USER"
        },
        success: function (data) {
            var returnVal = data.trim();
            if (returnVal == "true") {
                alert("发送成功");
            } else if (returnVal == "offline") {
                alert("登录超时请重新登录！");
                window.location.href = "index.jsp";
            } else {
                alert("评论失败，请稍后重试!");
            }
        },
        error: function () {
            alert("网络异常！");
        }
    });
}
/*点赞*/
function thumbsUp() {
    /*向上找到容器元素*/
    var jumbotron = $(this).parents(".jumbotron").first();
    /*由容器元素向下找到动态id*/
    var dynamicId = jumbotron.find(".dynamicId").val();
    /*获取点赞数*/
    var thumbsCount = $(this).find(".thumbsNum");
    /*获取点赞状态*/
    var isThumbs = $(this).find(".isThumbs");
    $(this).css("color", "red");
    var btnThis = $(this);
    if (isThumbs.val() == "false") {
        /*点赞*/
        $.ajax({
            url: "/DynamicServlet",
            type: "POST",
            dataType: "text",
            data: {
                "action": "ThumbsUp",
                "DynamicID": dynamicId
            },
            success: function (data) {
                var returnVal = data.trim();
                if (returnVal == "true") {
                    thumbsCount.html(parseInt(thumbsCount.html()) + 1);
                    isThumbs.val("true");
                } else if (returnVal == "false") {
                    alert("已经点过赞了!");
                    isThumbs.val("true");
                } else {
                    alert("登录超时,请重新登录!");
                }
            },
            error: function () {
                alert("网络异常，请稍后再试");
                isThumbs.val("false");
                btnThis.css("color", "gray");
            }
        });
    } else {
        alert("已经点过赞了!");
    }
}

/*jsp版本的点赞*/

function thumbsUp_JSP(dynamicID, thumpBtn) {
    /*由容器元素向下找到动态id*/
    var dynamicId = dynamicID;
    /*获取点赞数*/
    var thumbsCount = $(thumpBtn).find(".thumbsNum");
    /*获取点赞状态*/
    var isThumbs = $(thumpBtn).find(".isThumbs");
    $(thumpBtn).css("color", "red");

    if (isThumbs.val() == "false") {
        /*点赞*/
        $.ajax({
            url: "/DynamicServlet",
            type: "POST",
            dataType: "text",
            data: {
                "action": "ThumbsUp",
                "DynamicID": dynamicId
            },
            success: function (data) {
                var returnVal = data.trim();
                if (returnVal == "true") {
                    thumbsCount.html(parseInt(thumbsCount.html()) + 1);
                    isThumbs.val("true");
                    alert("点赞成功")
                } else if (returnVal == "false") {
                    alert("已经点过赞了!");
                    isThumbs.val("true");
                } else {
                    alert("登录超时,请重新登录!");
                }
            },
            error: function () {
                alert("网络异常，请稍后再试");
                isThumbs.val("false");
                $(thumpBtn).css("color", "gray");
            }
        });
    } else {
        $(thumpBtn).css("color", "red");
    }
}