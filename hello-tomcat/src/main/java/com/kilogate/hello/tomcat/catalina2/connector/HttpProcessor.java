package com.kilogate.hello.tomcat.catalina2.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 处理器
 *
 * @author fengquanwei
 * @create 2017/8/30 14:54
 **/
public class HttpProcessor {
    public void process(Socket socket) {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
