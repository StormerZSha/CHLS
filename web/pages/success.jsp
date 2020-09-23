
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>操作成功</title>
    <style type="text/css">
        img{
            width: 200px;
            height: 200px;
            margin-left: 40%;
        }
        h1{
            margin-left: 28%;
        }
    </style>
</head>
<body>
<div >
    <h1>${requestScope.message}<a href="#" onclick="self.location=document.referrer;">返回</a></h1>
    <img src="../img/success.jpg" >
</div>
</body>
</html>
