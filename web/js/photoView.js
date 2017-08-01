/**
 * Created by Dream Sky on 2017/3/5.
 */

$(function () {
    //loadPhoto(6);
    /*修改父容器iframe的窗口高度*/
    //window.parent.document.getElementById("contentFrame").height = 1200;
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
            if (confirm("删除是不可恢复的，你确认要删除吗？")) {
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
    window.location.href = "classAlbum.jsp";
}

/*上传照片*/
function updatePhotos(albumID) {
    var ajax_option = {
        url: "/UploadServlet?action=UpToAlbum&&AlbumID=" + albumID,
        type: "POST",
        dataType: "text",
        success: function (data) {
            var result = data.trim();
            if (result == "true") {
                alert("上传成功");
            } else {
                alert("上传失败");
            }
        }
    };
    $("#updatePhoto").ajaxSubmit(ajax_option);
    $("#updatePhotoModal").modal('hide');
}

/*删除照片*/
function removePhoto(photoID, btnThis) {
    /*获取整个照片框*/
    var photoDiv = $(btnThis).parent().parent();

    if (confirm("删除是不可恢复的，你确认要删除吗？")) {
        $.ajax({
            url: "/PhotoServlet",
            type: "POST",
            dataType: "text",
            nocache: "false",
            data: {
                "action": "DeletePhoto",
                "PhotoID": photoID
            },
            success: function (data) {
                var returnVal = data.trim();
                if (returnVal == "true") {
                    photoDiv.remove();
                    alert("删除成功！");

                } else if (returnVal == "offline") {
                    alert("登录超时,请重新登录!");
                    location.replace("index.jsp");
                } else if (returnVal == "NoPermission") {
                    alert("权限不足,该照片不是你上传的！");

                } else {
                    alert("操作失败,请重试！");
                }
            },
            error: function () {
            }
        });
    }
    /*阻止事件冒泡*/
    event.stopPropagation();
}