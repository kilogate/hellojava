<%@ page contentType="text/html;charset=UTF-8" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
很抱歉，发生异常了。
<%
    out.println(exception.toString());
%>
</body>
</html>
