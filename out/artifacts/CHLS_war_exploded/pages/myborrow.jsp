<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的借阅</title>
    <link rel="stylesheet" href="../css/myborrow.css">
</head>
<body>
<jsp:include page="headside-student.jsp"></jsp:include>
<div class="main">
    <div class="titlebanner">
        <h2>我的借阅信息</h2>
    </div>
    <div class="detail">
        <table id="table"><!--展示预约借阅情况-->
            <tr>
                <th>借阅编号</th>
                <th>图书编号</th>
                <th>图书名称</th>
                <th>图书状态</th>
                <th>预约/借阅日期</th>
                <th>应归还日期</th>
                <th>还书点</th>
                <th>取消预约</th>
                <th>挂失</th>
            </tr>
            <c:forEach items="${requestScope.result}" var="borrow">
                <tr>
                    <td>${borrow.borno}</td>
                    <td>${borrow.bno}</td>
                    <td>${borrow.book.name}</td>
                    <td>${borrow.statusStr}</td>
                    <td>${borrow.optimeStr}</td>
                    <td>${borrow.backtimeStr}</td>
                    <td>${borrow.backplace}</td>
                    <td><a href="/BorrowManage?chls=11&borno=${borrow.borno}" id="order">取消预约</a></td>
                    <td><a href="/LostManage?chls=3&borno=${borrow.borno}">挂失</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="titlebanner">
        <h2>我的挂失信息信息</h2>
    </div>
    <div class="detail">
        <table id="table2"><!--展示挂失情况-->
            <tr>
                <th>借阅编号</th>
                <th>图书编号</th>
                <th>图书名称</th>
                <th>图书状态</th>
                <th>挂失日期</th>
                <th>是否处理</th>
                <th>处理日期</th>
            </tr>
            <c:forEach items="${requestScope.result2}" var="lost">
                <tr>
                    <td>${lost.borno}</td>
                    <td>${lost.bno}</td>
                    <td>${lost.book.name}</td>
                    <td>${lost.statusStr}</td>
                    <td>${lost.lostimeStr}</td>
                    <td>${lost.statusStr}</td>
                    <td>${lost.optimeStr}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
