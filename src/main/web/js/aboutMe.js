/**
 * Created by Dream Sky on 2017/3/10.
 */
/*同意申请的处理逻辑*/
function ReguserPass(messageID, btnThis) {
    RegHandler(messageID,btnThis,"PASS");
    alert("已通过该用户申请");
}

function RegHandler(messageID, btnThis,HandleResult) {
    /*找到消息列表项*/
    var MessageLi = $(btnThis).parent().parent();
    var HandUserID = MessageLi.find("input");
    $.ajax({
        url: "/UserServlet",
        type: "POST",
        dataType: "text",
        nocache: "false",
        data: {
            "action": "UserCheck",
            "MissageID": messageID,
            "HandUserID": HandUserID.val(),
            "HandleResult":HandleResult
        },
        success: function (data) {
            var returnVal = data.trim();
            if (returnVal == "true") {
                MessageLi.remove();
            } else if (returnVal == "offline") {
                alert("登录超时");
                location.replace("index.jsp");
            }
        },
        error: function () {
            location.reload();
        }
    });
}


/*拒绝申请的处理逻辑*/
function ReguserRefuse(messageID, btnThis) {
    RegHandler(messageID,btnThis,"REFUSE");
    alert("已拒绝该用户申请");
}

/*密码重置的申请*/
function PsdResetPass(messageID, btnThis) {
    var HandUserID = MessageLi.find("input");
}

function PsdRestRefuse(messageID, btnThis) {
    var HandUserID = MessageLi.find("input");
}

function loadMessage() {
    $.ajax(
        {
            url: "/UserServlet",
            type: "POST",
            dataType: "json",
            data: {
                "action": "UserInformation",
                "nocache": new Date().getTime()
            },
            success: function (data) {
                var user = eval(data);
                alert(user.nickname);

            },
            error: function () {
                alert("网络错误!");
            }
        }
    );
}