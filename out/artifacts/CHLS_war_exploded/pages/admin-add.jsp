<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员添加</title>
    <link rel="stylesheet" href="../css/admin-add.css">
</head>
<body>
<jsp:include page="headerside.jsp"></jsp:include>
<div class="main">
    <div class="mainbg"></div>
    <div class="mainshadow"></div>
    <div class="addlist">
        <form action="/AdminManage" method="post">
            <input type="hidden" name="chls" value="3">
            <span>用户名:</span><input type="text" name="username" id="usernameadd" ><br/>
            <span>密      码:</span><input type="text" name="password" id="passwordadd"><br/>
            <span>邮&nbsp;&nbsp;&nbsp;箱:</span><input type="text" name="email" id="emailadd"><br/>
            <span>用户类型:</span><input type="radio" name="type" checked="checked" id="admin" value="0">普通管理员<br/>
            <input type="submit"  value="添加" id="addbtn">
        </form>
        <a href="${pageContext.request.contextPath}/AdminManage?chls=1" class="back">返回</a>
    </div>
</div>
</body>
</html>
