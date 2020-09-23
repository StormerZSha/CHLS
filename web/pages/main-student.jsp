<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="../css/main-student.css">
</head>
<body>
<jsp:include page="headside-student.jsp"></jsp:include>
<div class="main">
    <div class="mymessage">
        <h2>我的信息</h2>
        <div class="detail">
            <ul>
                <li>可查询自己的个人信息</li>
                <li>修改自己的密码</li>
            </ul>
        </div>
    </div>
    <div class="searchorder">
        <h2>查询与预约</h2>
        <div class="detail">
            <ul>
                <li>可根据图书名称查阅图书信息</li>
                <li>可查询到图书的编号，名称，作者，出版社，图书状态等信息</li>
                <li>可对自己即将借阅的图书进行预约</li>
            </ul>
        </div>
    </div>
    <div class="lost">
        <h2>图书挂失</h2>
        <div class="detail">
            <ul>
                <li>可对丢失的图书进行挂失，及时让图书馆了解</li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
