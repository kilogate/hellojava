package com.kilogate.hello.servlet.security;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 编程式安全
 * 仍然需要在部署描述符中使用 login-config 配置验证方式
 *
 * @author fengquanwei
 * @create 2017/11/19 15:41
 **/
@WebServlet("/ps")
public class ProgrammaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.authenticate(response)) { // 如果已验证过
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("Welcome");
        } else {
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("Not Auth");
        }

    }
}
