package com.kilogate.hello.tomcat.servletcontainer1;

import com.kilogate.hello.tomcat.constant.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 静态资源处理器
 *
 * @author fengquanwei
 * @create 2017/8/28 14:11
 **/
public class StaticResourceProcessor {
    private static final int BUFFER_SIZE = 1024;

    public void process(Request request, Response response) {
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            File file = new File(Constants.WEB_ROOT, request.getUri());
            if (file.exists()) {
                try (FileInputStream inputStream = new FileInputStream(file)) {
                    int read = inputStream.read(buffer, 0, BUFFER_SIZE);
                    while (read != -1) {
                        response.output.write(buffer, 0, read);
                        read = inputStream.read(buffer, 0, BUFFER_SIZE);
                    }
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Conent-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                response.output.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
