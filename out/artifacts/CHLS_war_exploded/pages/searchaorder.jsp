<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>查询与预约</title>
    <link rel="stylesheet" href="../css/searchaorder.css">
</head>
<body>
<jsp:include page="headside-student.jsp"></jsp:include>
<div class="main">
    <div class="searchbanner">
        <form action="/BookManage?chls=7" method="post">
            <span>图书名称：</span>
            <input type="text" name="keyword" placeholder="请输入关键字">
            <input type="hidden" name="chls" value="7"/>
            <input type="submit" name="submit" value="搜索" id="searchbtn">
        </form>
    </div>
    <div class="detail">
        <c:forEach items="${result}" var="book">
        <div class="booklist">
            <div class="bookpic"><img src="/img/${book.image}" alt="${book.name}"></div>
            <div class="bookmessage">
                <h2>${book.bno}.${book.name}</h2>
                <div class="partmessage">
                    <div class="author">${book.author}著</div>
                    <div class="publisher">${book.publish}</div>
                    <div class="carrier">载体形态：${book.carrier}</div>
                    <div class="isbn">ISBN及定价：${book.ISBN_price}</div>
                    <div class="clcn">中图法分类号：${book.CLCN}</div>
                    <a class="more" href="javascript:void (0)">详情</a>
                </div>
            </div>
            <div class="bookborrow">借阅信息</div>
            <div class="borrowmessage">
                <table id="table">
                    <tr>
                        <th>借阅编号</th>
                        <th>图书编号</th>
                        <th>馆藏地</th>
                        <th>图书状态</th>
                        <th>应归还日期</th>
                        <th>还书点</th>
                        <th>预约</th>
                    </tr>
                    <c:forEach items="${book.borrows}" var="borrow">
                        <tr>
                            <td>${borrow.borno}</td>
                            <td>${borrow.bno}</td>
                            <td>${borrow.place}</td>
                            <td>${borrow.statusStr}</td>
                            <td>${borrow.backtimeStr}</td>
                            <td>${borrow.backplace}</td>
                            <td><a href="/BorrowManage?chls=9&borno=${borrow.borno}" id="order">预约</a></td>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
        </div>
        </c:forEach>
        </div>
    </div>
</div>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/searchaorder.js"></script>
</body>
</html>
