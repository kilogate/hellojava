package com.kilogate.hello.servlet.test;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HelloServlet
 *
 * @author fengquanwei
 * @create 2017/10/23 23:19
 **/
@WebServlet(name = "HelloSrvlet'sName",
        urlPatterns = "/hello",
        initParams = {@WebInitParam(name = "abc", value = "ABC"), @WebInitParam(name = "def", value = "DEF")})
public class HelloServlet implements Servlet {
    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String servletName = servletConfig.getServletName();
        String contextPath = servletConfig.getServletContext().getContextPath();
        String abc = servletConfig.getInitParameter("abc");
        String def = servletConfig.getInitParameter("def");

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.print("<html><head></head><body>");

        writer.print("<h1>servletName: " + servletName + "</h1>");
        writer.print("<h1>contextPath: " + contextPath + "</h1>");
        writer.print("<h1>abc: " + abc + "</h1>");
        writer.print("<h1>def: " + def + "</h1>");

        writer.print("</body></html>");
    }

    @Override
    public String getServletInfo() {
        return "My Servlet";
    }

    @Override
    public void destroy() {

    }
}
