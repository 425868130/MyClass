/**
 * Created by Dream Sky on 2017/3/4.
 */

/*动态调整iframe的高度*/
function iframeAdjust() {
    var iframe = document.getElementById("contentFrame");
    var bHeight = iframe.contentWindow.document.documentElement.scrollHeight + 50;
    iframe.height = bHeight;

}
window.onresize = function () {
    iframeAdjust();
}
/*跳转到主页面时显示部分元素*/
function displayToolBars() {
    document.getElementById("publish").style.display = "block";
    document.getElementById("notice").style.display = "block";
    document.getElementById("contentFrame").style.width = "80%";
}
/*跳转到其他页面时隐藏部分元素*/
function hideToolBars() {
    document.getElementById("publish").style.display = "none";
    document.getElementById("notice").style.display = "none";
    /*跳转其他页面时恢复主体内容框的宽度*/
    document.getElementById("contentFrame").style.width = "100%";
}