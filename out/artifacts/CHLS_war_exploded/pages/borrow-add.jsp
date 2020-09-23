<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>借阅条目添加</title>
    <link rel="stylesheet" href="../css/student-add.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="addlist">
        <form action="/BorrowManage" method="post">
            <input type="hidden" name="chls" value="3">
            <span>借阅编号:</span><input type="text" name="borno" id="boradd" ><br/>
            <span>图书编号:</span><select class="bnoselect" name="bno">
                                     <c:forEach items="${bookbno}" var="bookbno">
                                         <option value="${bookbno.bno}">${bookbno.name}</option>
                                     </c:forEach>
                                   </select><br/>
            <span>图书状态:</span><input type="radio" name="status" value="0" class="radioinput">可借阅
            <input type="radio" name="status" value="3" class="radioinput" checked>不可借<br/>
            <span>馆藏地:</span><input type="text" name="place" id="placeadd"><br/>
            <span>还书点:</span><input type="text" name="backplace" id="backplacedd"><br/>
            <input type="submit"  value="添加" id="addbtn">
        </form>
        <a href="${pageContext.request.contextPath}/BorrowManage?chls=0" class="back">返回</a>
    </div>
</div>
</body>
</html>
