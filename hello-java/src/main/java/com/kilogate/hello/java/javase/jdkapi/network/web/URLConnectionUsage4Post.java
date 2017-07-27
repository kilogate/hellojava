package com.kilogate.hello.java.javase.jdkapi.network.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 使用 URLConnection 发 POST 命令
 *
 * @author fengquanwei
 * @create 2017/7/25 20:07
 **/
public class URLConnectionUsage4Post {
    public static Logger logger = LoggerFactory.getLogger(URLConnectionUsage4Post.class);

    public static void main(String[] args) {
        URLConnection connection = null;
        StringBuffer response = new StringBuffer();
        try {
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("name", "Lask");
            urlParams.put("age", "123");

            URL url = new URL("http://localhost:8087/test/info");
            connection = url.openConnection();

            connection.setDoOutput(true);

            try (PrintWriter printWriter = new PrintWriter(connection.getOutputStream())) {
                boolean first = true;
                for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                    if (first) {
                        first = false;
                    } else {
                        printWriter.print("&");
                    }

                    String key = entry.getKey();
                    String value = entry.getValue();
                    printWriter.print(key);
                    printWriter.print('=');
                    printWriter.print(URLEncoder.encode(value, "UTF-8"));
                }
            }

            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine()).append("\n");
                }
            }

            logger.info("Response: " + response);
        } catch (MalformedURLException e) {
            logger.error("Something wrong. ", e);
        } catch (IOException e) {
            if (!(connection instanceof HttpURLConnection)) {
                try {
                    throw e;
                } catch (IOException e1) {
                    logger.error("Something wrong. ", e1);
                }
            }

            InputStream errorStream = ((HttpURLConnection) connection).getErrorStream();
            try (Scanner scanner = new Scanner(errorStream)) {
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine()).append("\n");
                }
            }

            logger.error("Something wrong. Response: {}", response, e);
        }
    }
}