package com.kilogate.hello.servlet.dynReg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 没有使用 @WebServlet 标注，也没有在部署描述中声明。
 *
 * @author fengquanwei
 * @create 2017/11/19 20:57
 **/
public class FirstServlet extends HttpServlet {
    private String name;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        writer.print("<html><head></head><body>");

        writer.print("<h1>First Servlet</h1>");
        writer.print("<h1>" + name + "</h1>");

        writer.print("</body></html>");
    }

    public void setName(String name) {
        this.name = name;
    }
}
