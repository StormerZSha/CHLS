
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../css/headside.css">
</head>
<body>
<div class="banner">
    <h2>图书馆管理系统</h2>
    <span>${username}，欢迎您使用
       <span id="out">
           <form action="/AdminManage" method="post">
               <input type="hidden" name="chls" value="6"/>
               <input type="submit" name="submit" value="退出">
           </form>
       </span>
    </span>
</div>
<div class="side">
    <ul class="menu">
        <li id="backmain" class="bigslide" name="backmain">
            <a href="${pageContext.request.contextPath}/pages/main.jsp">首页</a>
        </li>
        <li id="adminmanner" class="bigslide">
            <a href="${pageContext.request.contextPath}/AdminManage?chls=1">管理员管理</a>
        </li>
        <li id="stumanner" class="bigslide">
            <a href="${pageContext.request.contextPath}/StudentManage?chls=0">学生管理</a>
        </li>
        <li id="bookmanner" class="bigslide">
            <a href="${pageContext.request.contextPath}/BookManage?chls=0">图书管理</a>
        </li>
        <li id="borrowmanner" class="bigslide">
            <a href="${pageContext.request.contextPath}/BorrowManage?chls=0">借阅管理</a>
        </li>
        <li id="lostmanner" class="bigslide">
            <a href="${pageContext.request.contextPath}/LostManage?chls=0">挂失管理</a>
        </li>
    </ul>
</div>
<div class="space"></div>
</body>
</html>
