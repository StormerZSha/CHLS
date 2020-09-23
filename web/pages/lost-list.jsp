<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>挂失列表</title>
    <link rel="stylesheet" href="../css/admin-list.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="search">
        <form action="/LostManage" method="post">
            <input type="text" name="keyword" placeholder="根据书名查挂失信息">
            <input type="hidden" name="chls" value="1"/>
            <input type="submit" name="submit" value="搜索" id="searchbtn">
        </form>
    </div>
    <div class="searchdetail">
        <table id="table">
            <tr>
                <th>借阅编号</th>
                <th>图书编号</th>
                <th>图书名称</th>
                <th>馆藏地</th>
                <th>挂失人学号</th>
                <th>挂失人姓名</th>
                <th>挂失日期</th>
                <th>是否处理</th>
                <th>处理日期</th>
                <th>处理</th>
            </tr>
            <c:forEach items="${requestScope.result}" var="lost">
                <tr>
                    <td>${lost.borno}</td>
                    <td>${lost.bno}</td>
                    <td>${lost.book.name}</td>
                    <td>${lost.place}</td>
                    <td>${lost.sno}</td>
                    <td>${lost.student.name}</td>
                    <td>${lost.lostimeStr}</td>
                    <td>${lost.statusStr}</td>
                    <td>${lost.optimeStr}</td>
                    <td><a href="/LostManage?chls=2&borno=${lost.borno}" >处理</a></td>
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
