<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>学生注册更新</title>
    <link rel="stylesheet" href="../css/student-update.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="updatelist">
        <form action="/StudentManage?chls=5&studentid=${student.id}" method="post">
            <span>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</span><input type="text" name="sno" value="${student.sno}" disabled><br/>
            <span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span><input type="text" name="name" value="${student.name}"><br/>
            <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span><input type="text" name="sex" value="${student.sexStr}" disabled><br/>
            <span>生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：</span><input type="text" name="birth" value="${student.birthday}" disabled><br/>
            <span>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</span><input type="text" name="speciality" value="${student.speciality}"><br/>
            <span>最多可借数：</span><input type="number" name="booknum" value="${student.booknum}"><br/>
            <span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input type="text" name="pass" value="${student.pass}" ><br/>
            <input type="submit"  value="提交" id="updatebtn">
        </form>
        <a href="${pageContext.request.contextPath}/StudentManage?chls=0" class="back">返回</a>
    </div>
</div>
</body>
</html>
