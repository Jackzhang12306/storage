<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 17-9-26
  Time: 上午8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${tip}
<form action="/user/checkLogin.do" method="post">
    帐号：<input type="text" name="username"><br>
    口令：<input type="password" name="password"><br>
    <input type="submit" value="登录">
</form>

</body>
</html>
