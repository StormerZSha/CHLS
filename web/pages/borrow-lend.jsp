<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>借书信息填写</title>
    <link rel="stylesheet" href="../css/student-update.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="updatelist">
        <form action="/BorrowManage?chls=8&borno=${borrow.borno}" method="post">
            <span>借阅编号:</span><input type="text" name="borno"  value="${borrow.borno}" disabled><br/>
            <span>图书编号:</span><input type="text" name="bno"  value="${borrow.bno}" disabled><br/>
            <span>图书名称:</span><input type="text" name="bookname"  value="${borrow.book.name}"disabled><br/>
            <span>学号:</span><input type="text" name="sno" value="${borrow.sno}"><br/>
            <span>姓名:</span><input type="text" name="stuname"  value="${borrow.student.name}"><br/>
            <span>还书时间:</span><input type="date" name="backtime"><br/>
            <input type="submit"  value="提交" id="updatebtn">
        </form>
        <a href="${pageContext.request.contextPath}/BorrowManage?chls=0" class="back">返回</a>
    </div>
</div>
</body>
</html>
