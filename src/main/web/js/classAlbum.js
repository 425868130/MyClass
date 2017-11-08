/**
 * Created by Dream Sky on 2017/3/5.
 */
/*用于模态窗口区分当前是修改操作还是新建相册操作*/
var submitFlag = 1;
var AlbumId;
var inputAlbumName = $("#Input_AlbumName");
var inputAlbumText = $("#Input_AlbumText");
/*设置模态窗口的标题栏为编辑*/
function setEditFlag(albumId) {
    AlbumId = albumId;
    submitFlag = 0;
    var mTitle = document.getElementById("modalTitle");
    mTitle.innerHTML = "编辑相册信息";
    /*获取对应相册信息并替换到输入框上*/
    $.ajax({
        url: "/AlbumServlet",
        type: "POST",
        dataType: "json",
        data: {
            "action": "LoadAlbum",
            "AlbumID": AlbumId
        },
        success: function (data) {
            var returnVal = eval(data);
            inputAlbumName.val(returnVal.Album_name);
            inputAlbumText.val(returnVal.Album_description);
        },
        error: function () {
            alert("相册信息加载失败");
        }
    });


}
/*设置模态窗口标题栏为新建*/
function setCreateFlag() {
    submitFlag = 1;
    var mTitle = document.getElementById("modalTitle");
    mTitle.innerHTML = "新建相册";
}

/*模态窗口中的确定按钮点击事件*/
function onSubmit() {
    if (!inputCheck()) {
        return
    }
    if (submitFlag == 1) {
        /*执行创建相册*/
        createAlbum();
        /*创建完成后清除输入框*/
        inputAlbumName.val("");
        inputAlbumText.val("");
    } else if (submitFlag == 0) {
        /*执行修改相册信息*/
        editAlbum();
    }
    /*手动隐藏模态窗口*/
    $('#modalAlbum').modal('hide');
    location.reload();
}

/*执行具体的修改操作*/
function editAlbum() {
    $.ajax({
        url: "/AlbumServlet",
        type: "POST",
        dataType: "text",
        data: {
            "action": "AlbumChange",
            "AlbumID": AlbumId,
            "NewAlbumName": inputAlbumName.val(),
            "AlbumText": inputAlbumText.val(),
        },
        success: function (data) {
            var returnVal = data.trim();
            if (returnVal == "true") {
                alert("修改成功");
            }
        },
        error: function () {
            alert("网络错误,请重试！")
        }
    });
}
/*执行创建操作*/
function createAlbum() {
    $.ajax({
        url: "/AlbumServlet",
        type: "POST",
        dataType: "text",
        data: {
            "action": "NewAlbum",
            "AlbumName": inputAlbumName.val(),
            "AlbumDescription": inputAlbumText.val()
        },
        success: function (data) {
            var returnVal = data.trim();
            if (returnVal == "true") {
                alert("创建成功!");
                location.reload();
            } else if (returnVal == "offline") {
                alert("登录超时，请重新登录！");
                location.replace("index.jsp");
            } else {
                alert("创建失败，请稍后重试!");
            }
        },
        error: function () {
            alert("网络错误,创建失败！");
        }
    });
}

/*删除单页格*/
function removeAlbum(Album_id, btnRemove) {
    var div = $(btnRemove).parent().parent();
    if (confirm("删除是不可恢复的,你确认要删除吗？")) {
        div.remove();
        /*确认之后去数据库删除对应数据*/
        $.ajax({
            url: "/AlbumServlet",
            type: "POST",
            dataType: "text",
            nocache: "false",
            data: {
                "action": "DeleteAlbum",
                "AlbumID": Album_id
            },
            success: function (data) {
                var returnVal = data.trim();
                if (returnVal == "true") {
                    alert("相册删除成功!");
                    location.reload();
                } else {
                    alert("删除失败,请重试！");
                }
            },
            error: function () {
                alert("网络异常");
            }
        });
    }
}

function inputCheck() {
    if (inputAlbumName.val().length == 0) {
        alert("相册名不能为空");
        return false;
    }
    return true;
}