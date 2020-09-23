<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>图书信息更新</title>
    <link rel="stylesheet" href="../css/student-update.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="updatelist">
        <form action="/BookManage?chls=5&bookbno=${book.bno}" method="post">
            <span>编号:</span><input type="text" name="bno"  value="${book.bno}" disabled><br/>
            <span>书名:</span><input type="text" name="name"  value="${book.name}" ><br/>
            <span>作者:</span><input type="text" name="author"  value="${book.author}"><br/>
            <span>出版社:</span><input type="text" name="publish"  value="${book.publish}"><br/>
            <span>载体信息:</span><input type="text" name="carrier"  value="${book.carrier}"><br/>
            <span>ISBN与定价:</span><input type="text" name="ISBN_price"  value="${book.ISBN_price}" disabled><br/>
            <span>中图法编号:</span><input type="text" name="CLCN"  value="${book.CLCN}" disabled><br/>
            <span>图片</span><input type="file" name="pic"  ><span id="waitmessage"></span><br/>
            <input type="hidden" id="url" name="url" value="${book.image}">
            <input type="submit"  value="提交" id="updatebtn">
        </form>
        <a href="${pageContext.request.contextPath}/BookManage?chls=0" class="back">返回</a>
    </div>
</div>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/book-update.js"></script>
</body>
</html>
