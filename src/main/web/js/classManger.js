/**
 * Created by Dream Sky on 2017/3/10.
 */
/*加载班级在线状态*/
$(function () {
    $.ajax(
        {
            url: "/UserServlet",
            type: "POST",
            dataType: "json",
            data: {
                "action": "UserStatus",
                "nocache": new Date().getTime()
            },
            success: function (data) {
                var userTable = eval(data);
                $.each(userTable, function (i) {
                    var cell = "<tr><td>" + userTable[i].User_id + "</td><td>" + userTable[i].nickname + "</td><td>"
                        + userTable[i].login_time + "</td><td>" + userTable[i].login_num + "</td><td>" + userTable[i].online + "</td>"
                    $("tbody").append(cell);
                })
            },
            error: function () {
                alert("列表加载失败...");
            }
        }
    );
});
