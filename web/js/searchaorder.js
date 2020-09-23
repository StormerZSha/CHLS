//详情
var more=$(".more");
var carrier=$(".carrier");
var isbn=$(".isbn");
var clcn=$(".clcn");
for (var i=0;i<more.length;i++){
    (function (n) {
        more[n].onclick=function () {
            carrier[n].style.display="block";
            isbn[n].style.display="block";
            clcn[n].style.display="blobk";
        };
    })(i)
}

//借阅模块出现与消失
var bookborrow=$(".bookborrow");
var borrowmessage=$(".borrowmessage");
var timecount=null;
for (var i=0;i<bookborrow.length;i++){
    (function(n){//使用自执行函数闭包
        bookborrow[n].onmouseover=function () {
            if (timecount!=null) {
                clearTimeout(timecount);
            }
            borrowmessage[n].style.display="block";
        };
        borrowmessage[n].onmouseout=function () {
            var that=this;
            timecount=setTimeout(function () {
                that.style.display="none";
            },10000);
        };
    })(i)
}
//调整因数据嵌套产生的标签嵌套
var detail=$(".detail")[0];
var booklist=$(".booklist");
for (var j=0;j<booklist.length;j++){
    detail.appendChild(booklist[j]);
}

var td=$("#table td");
for (var k=0;k<td.length;k++){
    if (td[k].innerHTML=="可借阅"){
        td[k].style.color="#0a0";
    }
}