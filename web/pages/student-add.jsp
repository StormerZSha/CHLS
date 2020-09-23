<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生添加</title>
    <link rel="stylesheet" href="../css/student-add.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="addlist">
        <form action="/StudentManage" method="post">
            <input type="hidden" name="chls" value="2">
            <span>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</span><input type="text" name="sno" id="snoadd" ><br/>
            <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text" name="name" id="nameadd" ><br/>
            <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</span><input type="radio" name="sex" value="1" class="radioinput">男
            <input type="radio" name="sex" value="0" class="radioinput" checked>女<br/>
            <span>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日:</span><input type="date" name="birth" id="birthadd"><br/>
            <span>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:</span><input type="text" name="speciality" id="speadd"><br/>
            <span>最多可借数:</span><input type="text" name="booknum" id="booknumadd"><br/>
            <span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input type="password" name="pass" id="passid" >
            <input type="submit"  value="添加" id="addbtn">
        </form>
        <a href="${pageContext.request.contextPath}/StudentManage?chls=0" class="back">返回</a>
    </div>
</div>
</body>
</html>
