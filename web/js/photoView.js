/**
 * Created by Dream Sky on 2017/3/5.
 */

$(function () {
    loadPhoto(6);
    /*修改父容器iframe的窗口高度*/
    window.parent.document.getElementById("contentFrame").height = 1200;
})

function loadPhoto(number) {
    var photoContainer = $("#photoContainer");
    var photoCell = $("#photoCell");
    var photoDIV = $("#photoCell").find(".photoDIV");
    var photoRemoveBtn = photoCell.find(".remove");

    /*处理并装配数据*/
    for (var i = 0; i < number; i++) {
        if (i == 4) {
            photoDIV.find("img").attr("src", "images/login2.jpg");
        }
        photoRemoveBtn.attr("id", "remove" + i);
        photoContainer.delegate("#remove" + i, "click", function (event) {
            if(confirm("删除是不可恢复的，你确认要删除吗？")) {
                $(this).parent().parent().remove();
            }
            /*阻止事件冒泡*/
            event.stopPropagation();
        })

        photoContainer.append(photoCell.html());
    }
}
/*照片预览*/
function preViewPhoto(obj) {
    //DOM对象转换为jQuery对象
    var ImgSrc = $(obj).find("img").attr("src");
    /*获取被点击图片的src替换到模态窗口上*/
    $("#Preview").attr("src", ImgSrc);
}

/*返回上一页*/
function returnAlbum(obj) {
    obj.style.color = "blue";
    window.location.href = "classAlbum.html";
}

/*上传照片*/
function updatePhotos() {
    alert("上传成功！");
    alert($("#photoFiles").toString());
    $("#updatePhotoModal").modal('hide');
}