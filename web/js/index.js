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
    /*空值判断*/
    if (userId.length == 0 || userPsd.length == 0) {
        alert("账号或密码不能为空!");
        $("#LoginUserPsd").val("");
        return;
    }
    if (userId.length > 15 || userPsd.length > 15) {
        alert("请输入正确的用户名或密码!");
        $("#LoginUserID").val("");
        $("#LoginUserPsd").val("");
    }else {
        /*通过serv判断用户名密码是否正确*/
        $.ajax(
            {
                url:"/UserServlet",
                type:"POST",
                dateType:"html",
                data:{
                    "action":"UserLogin",
                    "UserID":userId,
                    "Psd":userPsd
                },
                /*请求成功判断服务器返回值*/
                success:function (data) {
                    if (data=="true") {
                        window.location.href = "homePage.html";
                    }else {
                        alert("登录失败,用户名或密码错误！");
                    }

                },
                /*请求失败*/
                error:function () {
                    alert("登录失败,请稍后重试！")
                }
            }
        );
    }
}
/*忘记密码表单提交*/
function forgetSubmit() {
    /*清空输入*/
    $("#forgetPsd").find("input").val("");
    alert("重置申请已发送,请留意管理员短信通知！")
}
