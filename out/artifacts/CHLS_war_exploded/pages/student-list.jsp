<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>学生查询</title>
    <link rel="stylesheet" href="../css/student-list.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="search">
        <form action="/StudentManage" method="post">
            <input type="text" name="keyword" placeholder="请输入关键字">
            <input type="hidden" name="chls" value="1"/>
            <input type="submit" name="submit" value="搜索" id="searchbtn">
        </form>
        <a href="${pageContext.request.contextPath}/pages/student-add.jsp" id="add">添加学生</a>
    </div>
    <div class="searchdetail">
        <table id="table">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>生日</th>
                <th>专业</th>
                <th>最多可借数</th>
                <th>密码</th>
                <th>修改</th>
                <th>删除用户</th>
            </tr>
            <c:forEach items="${requestScope.result}" var="student">
                <tr>
                    <td>${student.sno}</td>
                    <td>${student.name}</td>
                    <td>${student.sexStr}</td>
                    <td>${student.birthday}</td>
                    <td>${student.speciality}</td>
                    <td>${student.booknum}</td>
                    <td>${student.pass}</td>
                    <td><a href="/StudentManage?chls=4&studentid=${student.id}" id="update">修改</a></td>
                    <td><a href="${pageContext.request.contextPath}/StudentManage?chls=3&studentid=${student.id}">删除</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="#" id="btn0"></a>
        <a href="#" id="btn1">首页</a>
        <a href="#" id="btn2">上一页</a>
        <a href="#" id="btn3">下一页</a>
        <a href="#" id="btn4">尾页</a>
        <a href="#" id="btn5"></a>
    </div>
</div>
</div>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/admin-list.js"></script>
</body>
</html>
