<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>图书列表</title>
    <link rel="stylesheet" href="../css/student-list.css">
    <link rel="stylesheet" href="../css/book-list.css">   <!--引用多个css，冲突的样式会使用后者-->
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="search">
        <form action="/BookManage" method="post">
            <input type="text" name="keyword" placeholder="请输入关键字">
            <input type="hidden" name="chls" value="1"/>
            <input type="submit" name="submit" value="搜索" id="searchbtn">
        </form>
        <a href="${pageContext.request.contextPath}/pages/book-add.jsp" id="add">添加图书</a>
        <h2 class="warn">删除图书后，对应的借阅信息会自动删除，请谨慎操作</h2>
    </div>
    <div class="searchdetail">
        <table id="table">
            <tr>
                <th>编号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>载体信息</th>
                <th>ISBN与定价</th>
                <th>中图法编号</th>
                <th>图片</th>
                <th>修改</th>
                <th>删除图书</th>
            </tr>
            <c:forEach items="${requestScope.result}" var="book">
                <tr>
                    <td>${book.bno}</td>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.publish}</td>
                    <td>${book.carrier}</td>
                    <td>${book.ISBN_price}</td>
                    <td>${book.CLCN}</td>
                    <td><img src="/img/${book.image}" alt="${book.name}"></td>
                    <td><a href="/BookManage?chls=4&bookbno=${book.bno}" id="update">修改</a></td>
                    <td><a href="${pageContext.request.contextPath}/BookManage?chls=3&bookbno=${book.bno}">删除</a></td>
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
