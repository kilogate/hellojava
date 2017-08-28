package com.kilogate.hello.tomcat.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 一个简单的 Servlet
 *
 * @author fengquanwei
 * @create 2017/8/24 21:02
 **/
public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("PrimitiveServlet.init()");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("PrimitiveServlet.service()");

        PrintWriter writer = servletResponse.getWriter();
        writer.println("HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n" +
                "Conent-Length: 22\n" +
                "\n" +
                "<h1>Hello Servlet</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("PrimitiveServlet.destory()");
    }
}
