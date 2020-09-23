<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>借阅列表</title>
    <link rel="stylesheet" href="../css/admin-list.css">
    <link rel="stylesheet" href="../css/borrow-lend.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="search">
        <form action="/BorrowManage" method="post">
            <input type="text" name="keyword" placeholder="根据书名查借阅信息">
            <input type="hidden" name="chls" value="1"/>
            <input type="submit" name="submit" value="搜索" id="searchbtn">
        </form>
        <a href="/BorrowManage?chls=2" id="add">添加新条目</a>
    </div>
    <div class="searchdetail">
        <table id="table">
            <tr>
                <th>借阅编号</th>
                <th>图书编号</th>
                <th>图书名称</th>
                <th>馆藏地</th>
                <th>图书状态</th>
                <th>预约/借阅人学号</th>
                <th>预约/借阅人姓名</th>
                <th>预约/借阅日期</th>
                <th>应归还日期</th>
                <th>还书点</th>
                <th>取消预约</th>
                <th>借/还书</th>
                <th>下架</th>
            </tr>
            <c:forEach items="${requestScope.result}" var="borrow">
                <tr>
                    <td>${borrow.borno}</td>
                    <td>${borrow.bno}</td>
                    <td>${borrow.book.name}</td>
                    <td>${borrow.place}</td>
                    <td>${borrow.statusStr}</td>
                    <td>${borrow.sno}</td>
                    <td>${borrow.student.name}</td>
                    <td>${borrow.optimeStr}</td>
                    <td>${borrow.backtimeStr}</td>
                    <td>${borrow.backplace}</td>
                    <td><a href="/BorrowManage?chls=5&borno=${borrow.borno}" id="order">取消预约</a>
                    </td>
                    <td><a href="/BorrowManage?chls=7&borno=${borrow.borno}" id="lend">借书</a>
                        <a href="/BorrowManage?chls=6&borno=${borrow.borno}" id="back">还书</a>
                    </td>
                    <td><a href="/BorrowManage?chls=4&borno=${borrow.borno}">下架</a></td>
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
