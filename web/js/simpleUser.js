/**
 * Created by Dream Sky on 2017/3/7.
 */
$(function () {
        loadUerInfo();
    }
)

function loadUerInfo() {
    $.ajax({
        url: "/UserServlet",
        type: "POST",
        dateType: "json",
        data: {
            "action": "get",
            "nocache": new Date().getTime()
        },
        success: function () {

        },
        error: function () {

        }
    });
}
