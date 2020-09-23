//切换选择卡
var mymessage=$(".selectbanner li:eq(0)")[0];
var pass=$(".selectbanner li:eq(1)")[0];
var detail=$(".detail")[0];
var modfiy=$(".modifypass")[0];
mymessage.onclick=function () {
   this.style.backgroundColor="#fff";
   this.style.backgroundImage="none"
   pass.style.backgroundImage="linear-gradient(to bottom,#ddd,#eee,#ddd)";
   detail.style.display="block";
   modfiy.style.display="none";
};
pass.onclick=function () {
    this.style.backgroundColor="#fff";
    this.style.backgroundImage="none"
    mymessage.style.backgroundImage="linear-gradient(to bottom,#ddd,#eee,#ddd)";
    modfiy.style.display="block";
    detail.style.display="none";
};
//修改密码---验证码刷新
var codeImage=document.getElementById("codeImage");
codeImage.onclick=function () {
    this.src=this.src+"?"+Math.random();
};
//修改成功提示
var success=$(".success")[0];
var successmessage=$(".success h2")[0];
var closebtn=$(".success i")[0];
var modfiybtn=$("#modifybtn")[0];
if (successmessage.innerHTML=="修改成功"){
    success.style.display="block";
}
closebtn.onclick=function () {
  success.style.display="none";
};


