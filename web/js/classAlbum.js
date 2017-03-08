/**
 * Created by Dream Sky on 2017/3/5.
 */
/*用于模态窗口区分当前是修改操作还是新建相册操作*/
var submitFlag = 1;
/*设置模态窗口的标题栏为编辑*/
function setEdit() {
    submitFlag = 0;
    var mTitle = document.getElementById("modalTitle");
    mTitle.innerHTML = "编辑相册信息";
}
/*设置模态窗口标题栏为新建*/
function setCreate() {
    submitFlag = 1;
    var mTitle = document.getElementById("modalTitle");
    mTitle.innerHTML = "新建相册";
}

/*模态窗口中的确定按钮点击事件*/
function onSubmit() {
    if (submitFlag == 1) {
        createAlbum();
        /*创建完成后清除输入框*/
        $("#modalAlbum").find("input").val("");
        $("#modalAlbum").find("textarea").val("");
    } else if (submitFlag == 0) {
        editAlbum();
    }
    /*手动隐藏模态窗口*/
    $('#modalAlbum').modal('hide');
}

/*执行修改操作*/
function editAlbum() {
    alert("修改成功！");
}
/*执行创建操作*/
function createAlbum() {
    createAlbumCell();
    $('#albumModifyForm').submit();
    alert("创建成功！");
}

/*创建新的相册单元格*/
function createAlbumCell() {
    $("#albumContainer").append($("#albumCell").html())
}
/*删除单页格*/
function removeAlbum(parentNode) {

}