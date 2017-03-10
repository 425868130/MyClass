function cambiar_login() {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
    document.querySelector('.cont_form_login').style.display = "block";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_login').style.opacity = "1";
    }, 400);

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
    }, 200);

}

function cambiar_sign_up() {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
    document.querySelector('.cont_form_sign_up').style.display = "block";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
    }, 100);

    setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
    }, 400);

}


function ocultar_login_sign_up() {

    document.querySelector('.cont_forms').className = "cont_forms";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
    }, 500);

}


/*登录*/
function login() {
    var userId = $("#LoginUserID").val();
    var userPsd = $("#LoginUserPsd").val();
    if (userId.length == 0 && userPsd.length == 0) {
        alert("请输入用户账号和密码！");
    }
    else if (userId.length == 0) {
        alert("用户账号不能为空！");
        $("#LoginUserPsd").val("");
    }
    else if (userPsd.length == 0) {
        alert("密码不能为空！");
    }
    else if (userId.length < 13 && userPsd.length < 11) {
        /*通过serv判断用户名密码是否正确*/
        $.ajax(
            {
                url: "/UserServlet",
                type: "POST",
                dataType: "html",
                data: {
                    "action": "UserLogin",
                    "UserID": userId,
                    "Psd": userPsd
                },
                /*请求成功判断服务器返回值*/
                success: function (data) {
                    if (data.toString().trim() == "true") {
                        window.location.href = "homePage.html";
                    } else {
                        $("#LoginUserPsd").val("");
                        alert("登录失败,用户名或密码错误！");
                    }
                },
                /*请求失败*/
                error: function () {
                    alert("登录失败,请稍后重试！")
                }
            }
        );
    }
    else {
        alert("请输入正确的用户账号和密码！");
        $("#LoginUserID").val("");
        $("#LoginUserPsd").val("");
    }
}
/*忘记密码表单提交*/
function forgetSubmit() {
    var username = document.getElementsByName("fgUserName");
    var nick = document.getElementsByName("fgNick");
    var telephone = document.getElementsByName("fgTell");
    var psd = document.getElementsByName("fgPsd");
    var sign = document.getElementsByName("fgSign");
    var re = /^[1-9]+[0-9]*]*$/;
    if (username[0].value.length == 0 && telephone[0].value.length == 0) {
        alert("用户账号和联系方式不能为空！");
    }
    else if (username[0].value.length == 0) {
        alert("用户账号不能为空！");
    }
    else if (telephone[0].value.length == 0) {
        alert("联系方式不能为空！");
    }
    else if (!re.test(telephone[0].value)) {
        alert("输入的手机号码只能为数字！");
    }
    else if (username[0].value.length < 13 && telephone[0].value.length < 13 && nick[0].value.length < 11 && psd[0].value.length < 11) {
        $.ajax(
            {
                url: "/UserServlet",
                type: "POST",
                dataType: "text",
                data: {
                    "action": "ForgetPsd",
                    "userName": username[0].value,
                    "userTell": telephone[0].value
                },
                success: function (data) {
                    var returnVal = "";
                    returnVal += data;
                    if (returnVal.trim() == "true") {
                        alert("重置申请已发送,请留意管理员短信通知！");
                    } else if (returnVal.trim() == "same") {
                        alert("用户账号不存在")
                    }
                },
                error: function () {
                    alert("网络出错,请稍后重试！");
                }
            }
        );
    }
    else {
        alert("请填写正确的个人信息！");
    }

    /*清空输入*/
    $("#forgetPsd").find("input").val("");

}

/*用户注册*/
function regCount() {
    cambiar_sign_up();
    var userid = $("#RegCount").val();
    var psd = $("#RegPsd");
    var secpsd = $("#RegPsdConfirm");
    var telephone = $("#Regtell");
    var re = /^[1-9]+[0-9]*]*$/;
    if (userid.length == 0 || psd.val().length == 0 || secpsd.val().length == 0 || telephone.val().length == 0) {
        alert("请填写完整的注册信息！");
    }
    else if (psd.val() != secpsd.val()) {
        alert("输入密码和确认密码不一致！");
    }
    else if (!re.test(telephone.val())) {
        alert("输入的手机号码只能为数字！");
    }
    else if (userid.length < 13 && psd.val().length < 11 && telephone.val().length < 13) {
        $.ajax(
            {
                url: "/UserServlet",
                type: "POST",
                dataType: "text",
                data: {
                    "action": "UserReg",
                    "nocache": new Date().getTime(),
                    "UserID": userid,
                    "Psd": psd.val(),
                    "Telephone": telephone.val()
                },
                success: function (data) {
                    var msg = "";
                    msg += data;
                    if (msg.trim() == "true") {
                        alert("注册申请已提交,请等待管理员审核通知！")
                    } else if (msg.trim() == "same") {
                        alert("用户名" + userid + "已经存在！");
                    } else {
                        alert("注册失败,请检查输入信息是否正确！");
                    }
                },
                error: function () {
                    alert("网络出错,请稍后重试！")
                }
            }
        );
    }
    else {
        alert("请输入正确的注册信息！");
    }
}
