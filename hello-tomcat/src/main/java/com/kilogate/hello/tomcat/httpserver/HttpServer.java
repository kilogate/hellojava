package com.kilogate.hello.tomcat.httpserver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Web 服务器
 *
 * @author fengquanwei
 * @create 2017/8/24 14:19
 **/
public class HttpServer {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    /**
     * 浏览器访问 http://127.0.0.1:8081/hello.html
     */
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        try (ServerSocket serverSocket = new ServerSocket(8081, 1, InetAddress.getByName("127.0.0.1"))) {
            while (!shutdown) {
                Socket socket = serverSocket.accept();

                // 创建一个请求
                Request request = new Request(socket.getInputStream());
                request.parse();

                // 创建一个响应
                Response response = new Response(socket.getOutputStream());
                response.setRequest(request);

                // 响应静态资源
                response.sendStaticResource();

                socket.close();

                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
