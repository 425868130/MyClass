/**
 * Created by Dream Sky on 2017/3/7.
 */
$(function () {
    $.ajax({
            url: "/AnnounceServlet",
            type: "POST",
            dataType: "json",
            data: {
                "action": "NewAnnounce",
                "nocache": new Date().getTime()
            },
            success: function (data) {
                var announce = eval(data);
                $(".NoticeTitle").html(announce.theme);
                $(".homeNoticeContent").html(announce.content);
                $(".NoticeTime").html(announce.Announce_time);
            },
            error:function () {
                $(".NoticeTitle").html("加载失败");
                $(".homeNoticeContent").html();
                $(".NoticeTime").html();
            }
        }
    );
})