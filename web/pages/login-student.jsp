<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用图书馆管理系统！请登录</title>
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/login-student.css">
</head>
<body>
<div class="logindiv" id="logindiv">
    <div class="banner">
        <h2>图书馆管理系统</h2>
    </div>
    <form action="/StudentManage?chls=6" method="post" id="login">
        <span class="user"></span><input type="text" placeholder="请输入账号" id="sno" name="sno">
        <span id="usermessage"></span>
        <span class="pass"></span><input type="password" placeholder="请输入密码" id="pass" name="pass">
        <span id="passmessage"></span>
        <input type="text" id="code" name="code">
        <span><img src = "/Check" id = "codeImage" alt="验证码图片"/></span>
        <span id="codemessage"></span>
        <input type="submit" value="登录" id="loginbtn">
        <input type="hidden" name="chls" value="0">
    </form>
    <a href="${pageContext.request.contextPath}/pages/login.jsp" id="toother">管理端&gt;</a>
</div><!--登录框-->
<div class="foot"></div><!--底部框-->
<script src="../js/login-student.js"></script>
</body>
</html>
