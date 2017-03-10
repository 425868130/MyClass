/**
 * Created by Dream Sky on 2017/3/4.
 */
/*点赞标识*/

$(function () {
    loadDynamic(4);
    window.parent.iframeAdjust();
})
/*加载动态*/
function loadDynamic(MaxDynamic) {
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
    var replce_dynamicId = dynamicCell.find(".dynamicId");//动态id


    /*循环处理多条动态元素的生成*/
    for (var Num = 0; Num < MaxDynamic; Num++) {
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

    //装载完成
}
/*发送评论*/
function sendComment() {
    alert("发送成功！");
}
/*点赞*/
function thumbsUp() {
    /*获取点赞数*/
    var thumbsCount = $(this).find(".thumbsNum");
    /*获取点赞状态*/
    var isThumbs = $(this).find(".isThumbs");
    if (isThumbs.val() == "false") {
        thumbsCount.html(parseInt(thumbsCount.html()) + 1);
        $(this).css("color", "red");
        isThumbs.val("true");
    } else {
        alert("已经点过赞了!")
    }
}