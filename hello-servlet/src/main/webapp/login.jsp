<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<h1>登录表单</h1>
<form action="j_security_check" method="post">
    <div>
        用户：<input type="text" name="j_username"/>
        <br/>
        密码：<input type="password" name="j_password"/>
    </div>
    <div>
        <input type="submit" value="登录"/>
    </div>
</form>

</body>
</html>
