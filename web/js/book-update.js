//与book-add.js功能相同，仅改变input标签出现位置


var pic=$("input")[9];
var waitmessage=$("#waitmessage");
pic.onclick=function () {
    waitmessage[0].innerHTML="图片上传需要时间，请稍等";
    setTimeout(function(){
        if (pic.value!=null) {
            var str=pic.value.slice(12);
            $("input")[10].value =str;
            console.log($("input")[10].value);
            waitmessage[0].innerHTML="图片已加载完毕";
        }
    },15000)
};