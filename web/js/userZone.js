/**
 * Created by Dream Sky on 2017/3/3.
 */
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

/*确定按钮*/
function confirmEdit() {
    buttonDisplay(false);
    var userNick = getById("userNick");
    var userSex = getById("userSex");
    var userTel = getById("userTel");
    var userSign = getById("userSign");
    /*将新用户信息替换到网页*/
    userNick.innerHTML = getById("InputNick").value;
    userSex.innerHTML = getById("InputSex").value;
    userTel.innerHTML = getById("InputTel").value;
    userSign.innerHTML = getById("InputSign").value;
    alert("修改成功！");
    editFlag = 0;
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
    var originalPwd = getById("originalPwd").value;
    var newPwd = getById("newPwd").value;
    var confirmPwd = getById("confirmPwd").value;
    if (originalPwd.length == 0 || newPwd.length == 0 || confirmPwd.length == 0) {
        alert("请输入正确的密码!");
        return;
    } else if (newPwd != confirmPwd) {
        alert("新密码和确认密码不一致!");
        return;
    }

    alert("密码修改成功,下次登录请使用新密码!");
    /*隐藏模态窗口*/
    $('#userZoneModal').modal('hide');
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
    /*头像修改处理逻辑*/
    alert("头像修改成功!");
    /*隐藏模态窗口*/
    $('#userZoneModal').modal('hide');
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