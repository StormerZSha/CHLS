

var sno=document.getElementById("sno");
var usermessage=document.getElementById("usermessage");
var pass=document.getElementById("pass");
var passmessage=document.getElementById("passmessage");
var code=document.getElementById("code");
var codemessage=document.getElementById("codemessage");
var loginbtn=document.getElementById("loginbtn");
loginbtn.onclick=function () {//登录验证
    var flag=true;
    if (!checkuser()) flag=false;
    if (!checkpass()) flag=false;
    if (!checkcode()) flag=false;
    return flag;
};
function checkuser() {
    if (sno.value==""){
        usermessage.innerText="用户名不能为空";
        usermessage.style.color="#d00";
        usermessage.style.fontSize="14px";
        usermessage.style.lineHeight="40px";
        return false;
    } else{
        usermessage.innerText="";
        return true;
    }
}
function checkpass() {
    if (pass.value==""){
        passmessage.innerText="密码不能为空";
        passmessage.style.color="#d00";
        passmessage.style.fontSize="14px";
        passmessage.style.lineHeight="40px";
        return false;
    } else {
        passmessage.innerText="";
        return true;
    }
}
function checkcode() {
    if (code.value==""){
        codemessage.innerText="验证码不能为空";
        codemessage.style.color="#d00";
        codemessage.style.fontSize="14px";
        codemessage.style.lineHeight="40px";
        return false;
    } else {
        codemessage.innerText="";
        return true;
    }
}
var codeImage=document.getElementById("codeImage");
codeImage.onclick=function () {
    this.src=this.src+"?"+Math.random();
};