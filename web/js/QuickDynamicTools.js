/**
 * Created by Dream Sky on 2017/3/9.
 */
/*获取相册列表*/
function selectAlbum() {
    $.ajax(
        {
            url:"/AlbumServlet"
        }
    )

    $("#AlbumList")
}