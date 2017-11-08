/**
 * Created by Dream Sky on 2017/3/7.
 */
$(function () {
    $.ajax({
        url: "/UserServlet",
        type: "POST",
        dataType: "json",
        data: {
            "action": "UserSimpleInfo",
            "nocache": new Date().getTime()
        },
        success: function (data) {
            var uerInfo = eval(data);
            /*替换页面元素值*/
            $(".simpleUserName").html(uerInfo.nickname);
            $(".simpleSign").html(uerInfo.signature);
            $(".simpleUerImg").attr("src", uerInfo.head_Img);
        },
        error: function () {
            alert("用户信息加载失败...");
        }
    });
});

/*获取个人简单信息*/
function loadUserInfo() {

}
