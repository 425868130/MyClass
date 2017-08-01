/**
 * Created by Dream Sky on 2017/3/3.
 */

/*加载用户详细信息*/
$(function () {
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
                /*加载用户信息到网页*/
                $("#userCount").html(user.User_id);
                $("#userNick").html(user.nickname);
                $("#userSex").html(user.sex);
                $("#userTel").html(user.telephone);
                $("#userLoginTime").html(user.onlineTime);
                $("#userSign").html(user.signature);
                $("#userImg").attr("src", user.head_portiait);
            },
            error: function () {
                alert("网络错误!");
            }
        }
    );
});

function getById(ElementID) {
    return document.getElementById(ElementID);
}
var editFlag = 0;
var modalFlag = 0;
/*编辑用户信息*/
function editInform() {
    buttonDisplay(true);
    /*获取界面文本信息*/
    if (editFlag == 0) {
        var userNick = getById("userNick");
        var userSex = getById("userSex");
        var userTel = getById("userTel");
        var userSign = getById("userSign");
    }
    /*替换成对应的输入框*/
    userNick.innerHTML = "<input type='text' value='" + userNick.innerHTML + "'name='userNick' id='InputNick'>";
    userSex.innerHTML = " <select id='InputSex'name='userSex'><option value='男'>男</option><option value='女'>女</option></select>"
    userTel.innerHTML = "<input type='text' value='" + userTel.innerHTML + "' name='userTel' id='InputTel'>";
    userSign.innerHTML = "<input type='text' value='" + userSign.innerHTML + "' name='userSign' id='InputSign'>";
    editFlag = 1;
}

/*信息修改确定按钮*/
function confirmEdit() {
    var nickname = document.getElementById("InputNick").value;
    var telephone = document.getElementById("InputTel").value;
    var re = /^[1-9]+[0-9]*]*$/;
    if (nickname.length == 0 && telephone.length == 0) {
        alert("请填写完整的个人信息！");
    }
    else if (nickname.length == 0) {
        alert("用户昵称不能为空")
    }
    else if (telephone.length == 0) {
        alert("联系方式不能为空！");
    }
    else if (!re.test(telephone)) {
        alert("输入的手机号码只能为数字！");
    }
    else if (nickname.length < 13 && telephone.length < 13) {
        /*输入合法*/
        var userNick = getById("userNick");
        var userSex = getById("userSex");
        var userTel = getById("userTel");
        var userSign = getById("userSign");
        editFlag = 0;
        /*将新的用户信息上传到服务器*/
        $.ajax(
            {
                url: "/UserServlet",
                type: "POST",
                dataType: "text",
                data: {
                    "action": "UserChangeIfo",
                    "nocache": new Date().getTime(),
                    "Nickname": $("#InputNick").val(),
                    "Telephone": $("#InputTel").val(),
                    "Signature": $("#InputSign").val(),
                    "Sex": $("#InputSex").val()
                },
                success: function (data) {
                    var reVal = data.trim();
                    if (reVal == "true") {
                        alert("修改成功");
                        /*将新用户信息替换到网页*/
                        userNick.innerHTML = getById("InputNick").value;
                        userSex.innerHTML = getById("InputSex").value;
                        userTel.innerHTML = getById("InputTel").value;
                        userSign.innerHTML = getById("InputSign").value;
                        buttonDisplay(false);
                    } else {
                        alert("修改失败，请重试！");
                    }
                },
                error: function () {
                    alert("网络异常,请稍后重试！");
                }
            }
        );
    }
    else {
        alert("用户信息过长或输入错误！");
    }

}

/*控制按钮的隐藏和显示*/
function buttonDisplay(isdDisplay) {
    if (isdDisplay) {
        getById("changeImg").style.display = "inline";
        getById("changePsd").style.display = "inline";
        getById("confirm").style.display = "block";
    } else {
        getById("changeImg").style.display = "none";
        getById("changePsd").style.display = "none";
        getById("confirm").style.display = "none";
    }
}

/*修改密码*/
function ChangePwd() {
    /*隐藏模态窗口*/
    $('#userZoneModal').modal('hide');
    var form = $("#ModalForm");
    var originalPwd = form.find(".originalPwd").val();
    var newPwd = form.find(".newPwd").val();
    var confirmPwd = form.find(".confirmPwd").val();
    if (originalPwd.length == 0 || newPwd.length == 0 || confirmPwd.length == 0) {
        alert("请输入正确的密码!");
        return;
    } else if (originalPwd.length > 12 || newPwd.length > 12 || confirmPwd.length > 12) {
        alert("密码不得超过12位");
        return;
    } else if (newPwd != confirmPwd) {
        alert("新密码和确认密码不一致!");
        return;
    }
    /*输入合法则向服务器传递数据并修改密码*/
    $.ajax({
        url: "/UserServlet",
        type: "POST",
        dataType: "text",
        data: {
            "action": "ChangePsd",
            "nocache": new Date().getTime(),
            "oldPwd": originalPwd,
            "newPwd": newPwd
        },
        success: function (data) {
            var result = data.trim();
            if (result == "true") {
                alert("密码修改成功,下次登录请使用新密码!");
                location.reload();
            } else if (result == "offline") {
                alert("登录超时,请重新登录！");
                window.location.href = "../html/index.html";
            } else {
                alert("原密码错误,修改失败！");
                location.reload();
            }
        },
        error: function () {
            alert("网络异常,请重试");
        }
    });
}


/*按下修改密码按钮时触发*/
function onBtnChangePwd() {
    /*修改标题*/
    getById("ModalLabel").innerHTML = "修改密码";
    /*替换form表单中的内容*/
    getById("ModalForm").innerHTML = getById("modalStatus1").innerHTML;
    modalFlag = 1;
}

/*修改头像事件*/
function ChangeImg() {
    /*隐藏模态窗口*/
    $('#userZoneModal').modal('hide');
    var ajax_option = {
        url: "/UserImgUpload",
        type: "POST",
        dataType: "text",
        success: function (data) {
            var result = data.trim();
            if (result == "true") {
                alert("头像修改成功!");
                location.reload();
            } else {
                alert("上传失败");
            }
        }
    };
    $("#ModalForm").find("form").ajaxSubmit(ajax_option);
}
/*按下修改头像按钮时触发*/
function onBtnChangeImg() {
    getById("ModalLabel").innerHTML = "上传头像";
    getById("ModalForm").innerHTML = getById("modalStatus2").innerHTML;
    modalFlag = 0;
}

/*模态窗口的按钮事件,通过modalFlag判断模态表单的提交动作*/
function onModalSubmit() {
    if (modalFlag == 0) {
        ChangeImg();
    } else {
        ChangePwd();
    }
}