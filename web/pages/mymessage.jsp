<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的信息</title>
    <link rel="stylesheet" href="../css/mymessage.css">
</head>
<body>
<jsp:include page="headside-student.jsp"></jsp:include>
<div class="main">
    <div class="selectbanner">
        <ul>
            <li>个人信息</li>
            <li>修改密码</li>
        </ul>
    </div>
    <div class="detail">
        <table id="table">
            <tr>
                <th colspan="4">我的信息</th>
            </tr>
            <tr>
                <th>学号</th>
                <td>${student.sno}</td>
                <th>姓名</th>
                <td>${student.name}</td>
            </tr>
            <tr>
                <th>密码</th>
                <td>*********</td>
                <th>性别</th>
                <td>${student.sexStr}</td>
            </tr>
            <tr>
                <th>出生年月</th>
                <td>${student.birthday}</td>
                <th>专业</th>
                <td>${student.speciality}</td>
            </tr>
            <tr>
                <th>最多可借数量</th>
                <td>${student.booknum}</td>
                <th></th>
                <td></td>
            </tr>
        </table>
        <div class="success">
            <h2>${requestScope.message}</h2>
            <i></i>
        </div>
    </div>
    <div class="modifypass">
        <h2>密码修改</h2>
        <form action="/StudentManage?chls=9" method="post">
            <span class="old">旧密码:</span><input type="text" name="oldpass" id="oldpass" ><br/>
            <span class="new">新密码:</span><input type="text" name="newpass" id="newpass"><br/>
            <span class="double">确认密码:</span><input type="text" name="doublepass" id="doublepass" ><br/>
            <img src = "/Check" id = "codeImage" alt="验证码图片"/>
            <span class="cod">验证码:</span><input type="text" name="code" id="code" ><br/>
            <input type="submit"  value="提交" id="modifybtn">
        </form>
    </div>
</div>
<script src="../js/jquery-1.9.1.min.js"></script>
<script src="../js/mymessage.js"></script>
</body>
</html>
