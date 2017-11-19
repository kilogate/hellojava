package com.kilogate.hello.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 发送进程更新的异步 Servlet
 *
 * @author fengquanwei
 * @create 2017/11/18 21:27
 **/
@WebServlet(name = "AsyncCompleteServlet", urlPatterns = "/asyncComplete", asyncSupported = true)
public class AsyncCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Async Servlet</title></head>");
        writer.println("<body><div id='progress'></div>");
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(60000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("new Thread: " + Thread.currentThread());
                for (int i = 0; i < 10; i++) {
                    writer.println("<script>");
                    writer.println("document.getElementById('progress').innerHTML = '" + (i * 10) + "% complete'");
                    writer.println("</script>");
                    writer.flush();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                writer.println("<script>");
                writer.println("document.getElementById('progress').innerHTML = 'DONE'");
                writer.println("</script>");
                writer.println("</body></html>");
                asyncContext.complete();
            }
        });

    }
}
