<%@ page import="com.kilogate.hello.servlet.listener.HttpSessionActivationListenerJavaBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hello-Servlet</title>
</head>
<body>
${applicationScope.welcome}

<%
    session.setAttribute("activationObject", new HttpSessionActivationListenerJavaBean());
%>
</body>
</html>