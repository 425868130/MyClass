/**
 * Created by Dream Sky on 2017/3/11.
 */
var PagePosition = 0;
var hasNext = true;
$(
    function () {
        /*判断用户是否为管理员，如果是管理员则显示发布公告按钮*/
        $.ajax(
            {
                url: "/UserServlet",
                type: "POST",
                dataType: "text",
                async: "false",
                data: {
                    "action": "isAdmin",
                    "nocache": new Date().getTime(),
                },
                success: function (data) {
                    var returnVal = data.trim();
                    if (returnVal == "true") {
                        $("#BtnNotice").show();
                    } else if (returnVal == "offline") {
                        alert("登录超时,请重新登录！");
                        parent.window.location.href = "../html/index.html";
                        return;
                    } else {
                        $("#BtnNotice").hide();
                    }
                }
            }
        );
        /*加载公告列表*/
        loadNotice(PagePosition);
    }
);

/*加载公告*/
function loadNotice(page) {
    var NoticeList = $("#NoticeList");
    var NoticeCell = $("#NoticeCell");
    var NoticeContent = NoticeCell.find(".NoticeContent");
    var NoticeTime = NoticeCell.find(".NoticeTime");
    var Publisher = NoticeCell.find(".Publisher");
    var NoticeTitle = NoticeCell.find("h3");
    if (page == 0) {
        $("#previousPage").addClass("disabled");
    }
    if (page > 0) {
        $("#previousPage").removeClass("disabled");
    }
    $.ajax(
        {
            url: "/AnnounceServlet",
            type: "POST",
            dataType: "json",
            async: "false",
            cache: "false",
            data: {
                "action": "LoadAnnounce",
                "nocache": new Date().getTime(),
                "NoticePage": page
            },
            success: function (data) {
                var noticeList = eval(data);
                if (noticeList.length == 0) {
                    alert("没有更多公告了!");
                    $("#nextPage").addClass("disabled");
                    hasNext = false;
                    PagePosition = PagePosition - 1;
                    return;
                }
                /*若数组小于6条说明已经是最后一页*/
                if (noticeList.length < 6) {
                    $("#nextPage").addClass("disabled");
                    hasNext = false;
                }
                /*加载动态前先清空容器*/
                NoticeList.html("");
                $.each(noticeList, function (i) {
                    NoticeTitle.html(noticeList[i].theme);
                    NoticeContent.html(noticeList[i].AnnounceContent);
                    NoticeTime.html("发布时间:" + noticeList[i].Announce_time);
                    Publisher.html("发布人:" + noticeList[i].User_id);
                    NoticeList.prepend(NoticeCell.html());
                })
            },
            error: function () {
                alert("公告加载失败,请刷新重试！");
            }
        }
    );
}


/*发布公告,验证公告内容是否为空*/
function publishNotice() {
    var title = $("#InputATitle");
    var content = $("#InputAContent");
    if (title.val().length == 0 || content.val().length == 0) {
        alert("公告标题和内容不能为空!");
        return;
    }
    /*向服务器传送公告内容*/
    $.ajax({
        url: "/AnnounceServlet",
        type: "POST",
        dataType: "text",
        data: {
            "action": "publishAnnounce",
            "nocache": new Date().getTime(),
            "Theme": title.val(),
            "AnnounceContent": content.val()
        },
        success: function (data) {
            var returnVal = data.trim();
            if (returnVal == "true") {
                loadNotice(0);
                alert("公告发布成功!");
                /*隐藏发布窗口并清空输入框*/
                $('#NoticeModal').modal('hide');
                title.val("");
                content.val("");
            }
            else if (returnVal == "offline") {
                alert("登录超时,请重新登录！");
                parent.window.location.href = "../html/index.html";
                return;
            } else {
                alert("非管理员无法布公告！");
                /*隐藏发布窗口并清空输入框*/
                $('#NoticeModal').modal('hide');
                title.val("");
                content.val("");
            }
        },
        error: function () {
            alert("网络错误,发布失败，请重试！");
        }
    });
}

/*上一页*/

function prePage() {
    if (PagePosition == 0) {
        $("#previousPage").addClass("disabled");
        return;
    } else {
        PagePosition = PagePosition - 1;
        loadNotice(PagePosition);
        hasNext = true;
        $("#nextPage").removeClass("disabled");
    }
}

/*下一页*/
function nextPage() {
    if (!hasNext) {
        return;
    }
    PagePosition = PagePosition + 1;
    loadNotice(PagePosition);
}