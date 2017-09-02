package com.kilogate.hello.tomcat.httpserver0;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求
 *
 * @author fengquanwei
 * @create 2017/8/24 14:32
 **/
public class Request {
    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    public void parse() {
        StringBuffer request = new StringBuffer(2048);

        byte[] buffer = new byte[2048];
        int lenth;
        try {
            lenth = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            lenth = -1;
        }

        for (int i = 0; i < lenth; i++) {
            request.append((char) buffer[i]);
        }

        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String request) {
        int index1, index2;
        index1 = request.indexOf(' ');
        if (index1 != -1) {
            index2 = request.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return request.substring(index1 + 1, index2);
            }
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
