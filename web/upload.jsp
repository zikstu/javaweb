<%--
  Created by IntelliJ IDEA.
  User: xuezhang
  Date: 2021/2/27
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="/fileUpload" method="post" enctype="multipart/form-data">
    <input type="text" name="name"><br>
    <input type="file" name="file"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
