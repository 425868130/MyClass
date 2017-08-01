/**
 * Created by Dream Sky on 2017/3/9.
 */

var SelectedAlbum_id;
$(function () {
    loadUserInfo();
});

/*获取相册列表*/
function selectAlbum() {
    /*先清空相册列表*/
    $("#AlbumList").html("");
    /*再向服务器请求列表*/
    $.ajax({
        url: "/AlbumServlet",
        type: "POST",
        dataType: "json",
        data: {
            action: "AlbumSimpleList",
        },
        success: function (data) {
            var AlbumList = eval(data);
            var listCell = $("#listCell");
            /*查询列表项目单元*/
            var listLink = listCell.find("a");
            /*隐藏的记录相册id的输入框*/
            var Album_id = listCell.find("span");
            var li = listCell.find("li");
            $.each(AlbumList, function (i) {
                //alert(AlbumList[i].Album_name);
                /*替换单元内容*/
                listLink.html(AlbumList[i].Album_name);
                Album_id.html(AlbumList[i].Album_id);
                /*添加li的id*/
                li.attr("id", "albumLi" + i);
                /*分别给每个列表元素添加点击事件*/
                $("#AlbumList").delegate("#albumLi" + i, "click", function () {
                    /*将列表自身的内容替换到已选相册里*/
                    $("#selectedAlbum").html($(this).html().trim());
                    /*获取选择的相册id*/
                    SelectedAlbum_id = $(this).find("span").html();
                });
                /*追加元素到下拉列表*/
                $("#AlbumList").append(listCell.html());
            })
        },
        error: function () {
            alert("相册列表加载失败...");
        }
    });
}

/*获取个人简单信息*/
function loadUserInfo() {
    $.ajax({
        url: "/UserServlet",
        type: "POST",
        dataType: "json",
        data: {
            "action": "UserSimpleInfo",
            "nocache": new Date().getTime()
        },
        success: function (data) {
            var uerInfo = eval(data);
            /*替换页面元素值*/
            $(".simpleUName").html(uerInfo.nickname);
            $(".simpleSign").html(uerInfo.signature);
            $(".simpleUerImg").attr("src", uerInfo.head_Img);
        },
        error: function () {
            alert("用户信息加载失败...");
        }
    });
}


/*发表动态*/
function publishDynamic() {
    /*判断是否选择了文件*/
    var flie = $("#homePageFileLoad");
    var fileName = flie.val();
    if (fileName.length == 0) {
        alert("请添加照片文件");
        return;
    }
    /*判断相册是否选取*/
    var dynamicContent = $("#InputDynamicContent");
    var homePageFileLoad = $("#homePageFileLoad");
    if (SelectedAlbum_id == "" || SelectedAlbum_id == null) {
        alert("请选择相册");
        return;
    }
    $.ajax({
        url: "/DynamicServlet",
        type: "POST",
        dataType: "text",
        async:"false",
        data: {
            "action": "SendDynamic",
            "AlbumID": SelectedAlbum_id,
            "DynamicContent": dynamicContent.val()
        },
        success: function (data) {
            var returnVal = data.trim();
            if (returnVal == "true") {
                alert("发表成功！");
                /*发布成功后开始上传图片*/
            } else if (returnVal == "offline") {
                alert("登录超时,请重新登录");
                location.replace("index.jsp");
            } else {
                alert("动态发表失败,请稍后重试");
                location.reload();
            }
        },
        error: function () {
            alert("网络异常,请刷新重试！");
            location.reload();
        }
    });
}


/*文件上传*/
function uploadImg() {
    var ajax_option = {
        url: "/UploadServlet?AlbumID=" + SelectedAlbum_id,
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
    $("#ImgFileFrom").ajaxSubmit(ajax_option);
}


function upload() {
    var ajax_option = {
        url: "/upPhotoServlet?Album="+SelectedAlbum_id,
        type: "POST",
        dataType: "text",
        success: function (data) {
            var result = data.trim();
            if (result == "true") {
                alert("上传成功");
                location.reload();
            } else {
                alert("上传失败");
            }
        }
    };
    $("#ImgFileFrom").ajaxSubmit(ajax_option);
}