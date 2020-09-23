// 将book-add.jsp中type=file的input的value
// 取出切割后赋予type=hidden的value
// 并设置定时器进行检测

var pic=$("input")[10];
var waitmessage=$("#waitmessage");
pic.onclick=function () {
    waitmessage[0].innerHTML="图片上传需要时间，请稍等";
    setTimeout(function(){
        if (pic.value!=null) {
            var str=pic.value.slice(12);
            $("input")[11].value =str;
            console.log($("input")[11].value);
            waitmessage[0].innerHTML="图片已加载完毕";
        }
    },15000)
};