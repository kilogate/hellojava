<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>

<form action="upload" enctype="multipart/form-data" method="post">
    Author: <input type="text" name="author"/><br/>
    Select a file <input type="file" name="uploadFile"/><br/>
    <input type="submit" value="Upload"/>
</form>

</body>
</html>
