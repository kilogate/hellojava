package com.kilogate.hello.tomcat.servletcontainer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Web 服务器（Servlet 容器）
 *
 * @author fengquanwei
 * @create 2017/8/24 14:19
 **/
public class HttpServer {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    /**
     * Debug Configuration
     * Main class: com.kilogate.hello.tomcat.servletcontainer.HttpServer
     * VM options: -classpath /Users/kilogate/.m2/repository/javax/servlet/servlet-api/3.0-alpha-1/servlet-api-3.0-alpha-1.jar:./
     * Working directory: /Users/kilogate/IdeaProjects/hello/hello-tomcat/target/classes
     * Use classpath of module: hello-tomcat
     * <p>
     * 浏览器访问 http://localhost:8081/hello.html
     * 浏览器访问 http://localhost:8081/servlet/com.kilogate.hello.tomcat.servlet.PrimitiveServlet
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

                // 检查 URI
                String uri = request.getUri();
                System.out.println("请求 URI：" + uri);
                if (uri == null || uri.equals("")) {
                    System.out.println("==================== 请求结束 ====================");
                    continue;
                }

                // 创建一个响应
                Response response = new Response(socket.getOutputStream());
                response.setRequest(request);

                // 响应
                if (uri.startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                // 关闭连接
                socket.close();

                System.out.println("==================== 请求结束 ====================\n\n\n");

                shutdown = uri.equals(SHUTDOWN_COMMAND);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
