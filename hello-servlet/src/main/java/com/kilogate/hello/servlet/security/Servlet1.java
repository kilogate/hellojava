package com.kilogate.hello.servlet.security;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet1
 * <p>
 * vim tomcat-users.xml
 * <?xml version='1.0' encoding='utf-8'?>
 * <p>
 * <tomcat-users>
 * <role rolename="member"/>
 * <role rolename="manager"/>
 * <user username="Lask" password="kilogate" roles="member"/>
 * <user username="Lynn" password="kilogate" roles="member,manager"/>
 * </tomcat-users>
 * <p>
 * vim web.xml 安全性约束：避免 JSP 页面被直接访问
 *
 * @author fengquanwei
 * @create 2017/11/19 12:56
 **/
@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
