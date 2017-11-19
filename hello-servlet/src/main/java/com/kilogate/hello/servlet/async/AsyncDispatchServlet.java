package com.kilogate.hello.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步转发
 *
 * @author fengquanwei
 * @create 2017/11/18 21:12
 **/
@WebServlet(name = "AsyncDispatchServlet", urlPatterns = "/asyncDispatch", asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AsyncContext asyncContext = request.startAsync();
        request.setAttribute("mainThread", Thread.currentThread().getName());
        asyncContext.setTimeout(5000);
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                System.out.println("工作线程开始处理");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request.setAttribute("workerThread", Thread.currentThread().getName());
                asyncContext.dispatch("/threadNames.jsp");
                System.out.println("工作线程处理完毕");
            }
        });
    }
}
