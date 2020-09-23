<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书添加</title>
    <link rel="stylesheet" href="../css/student-add.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="addlist">
        <form action="/BookManage" method="post">
            <input type="hidden" name="chls" value="2">
            <span>编号:</span><input type="text" name="bno" id="bnoadd" ><br/>
            <span>书名:</span><input type="text" name="name" id="nameadd" ><br/>
            <span>作者:</span><input type="text" name="author" id="authoradd"><br/>
            <span>出版社:</span><input type="text" name="publish" id="publishadd"><br/>
            <span>载体信息:</span><input type="text" name="carrier" id="carrieradd"><br/>
            <span>ISBN与定价:</span><input type="text" name="ISBN_price" id="isbnpriceadd"><br/>
            <span>中图法编号:</span><input type="text" name="CLCN" id="clcnadd" ><br/>
            <span>图片</span><input type="file" name="pic" id="picadd"><span id="waitmessage"></span><br/>
            <input type="hidden" id="url" name="url">
            <input type="submit"  value="添加" id="addbtn">
        </form>
        <a href="${pageContext.request.contextPath}/BookManage?chls=0" class="back">返回</a>
    </div>
</div>
</body>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/book-add.js"></script>
</html>
