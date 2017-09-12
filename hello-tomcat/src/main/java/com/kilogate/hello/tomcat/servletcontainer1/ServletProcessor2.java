package com.kilogate.hello.tomcat.servletcontainer1;

import com.kilogate.hello.tomcat.constant.Constants;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Servlet 处理器
 *
 * @author fengquanwei
 * @create 2017/8/28 14:12
 **/
public class ServletProcessor2 {
    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);

        try {
            // 在 Servlet 容器里边，一个类加载器可以找到 Servlet 的地方被称为资源库（repository）
            String repository = new URL("file", null, new File(Constants.WEB_ROOT).getCanonicalPath() + File.separator).toString();
            URL[] urls = new URL[]{new URL(null, repository, (URLStreamHandler) null)};
            try (URLClassLoader loader = new URLClassLoader(urls)) {
                Class myClass = loader.loadClass(servletName);
                Servlet servlet = (Servlet) myClass.newInstance();
                servlet.service(new RequestFacade(request), new ResponseFacade(response));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
