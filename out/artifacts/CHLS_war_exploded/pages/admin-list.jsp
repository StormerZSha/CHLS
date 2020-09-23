<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>管理员列表</title>
    <link rel="stylesheet" href="../css/admin-list.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="search">
        <form action="/AdminManage" method="post">
            <input type="text" name="keyword" placeholder="请输入关键字">
            <input type="hidden" name="chls" value="4"/>
            <input type="submit" name="submit" value="搜索" id="searchbtn">
        </form>
        <a href="${pageContext.request.contextPath}/pages/admin-add.jsp" id="add">添加管理员</a>
    </div>
    <div class="searchdetail">
        <table id="table">
            <tr>
                <th>用户名</th>
                <th>用户邮箱</th>
                <th>注册时间</th>
                <th>用户类型</th>
                <th>权限控制</th>
                <th>删除用户</th>
            </tr>
                <c:forEach items="${requestScope.result}" var="admin">
                <tr>
                    <td>${admin.username}</td>
                    <td>${admin.email}</td>
                    <td>${admin.timeStr}</td>
                    <td>${admin.typeStr}</td>
                    <td><a href="/AdminManage?chls=2&username=${admin.username}" id="griant">授权</a>
                    </td>
                    <td><a href="/AdminManage?chls=5&username=${admin.username}">删除</a></td>
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
