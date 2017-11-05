<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@page errorPage="error.jsp" %>
<html>
<head>
    <title>内置对象</title>
</head>
<body>
<%
    Integer.valueOf("Error");
    for (Enumeration<String> headerNames = request.getHeaderNames(); headerNames.hasMoreElements(); ) {
        String header = headerNames.nextElement();
        out.println(header + ": " + request.getHeader(header) + "<br/>");
    }
%>
<hr/>
<%
    out.println("Buffer size: " + response.getBufferSize() + "<br/>");
    out.println("Session id: " + session.getId() + "<br/>");
    out.println("Servlet name: " + config.getServletName() + "<br/>");
    out.println("Server ino: " + application.getServerInfo() + "<br/>");
%>
</body>
</html>
