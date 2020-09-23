
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆系统</title>
    <link rel="stylesheet" href="../css/headside-student.css">
</head>
<body>
   <div class="banner">
      <div class="space">
          <span>${name}(${sno})同学，欢迎您使用
          <span id="out">
              <form action="/StudentManage" method="post">
                <input type="hidden" name="chls" value="7"/>
                <input type="submit" name="submit" value="退出">
              </form>
          </span>
          </span>
      </div>
   </div>
   <div class="side">
       <ul class="menu">
           <li class="bigslide" name="backmain">
               <a href="${pageContext.request.contextPath}/StudentManage?chls=8">我的信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
           </li>
           <li class="bigslide">
               <a href="${pageContext.request.contextPath}/BookManage?chls=6">查询与预约&nbsp;&nbsp;</a>
           </li>
           <li class="bigslide">
               <a href="${pageContext.request.contextPath}/BorrowManage?chls=10">我的借阅&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
           </li>
       </ul>
   </div>
</body>
</html>
