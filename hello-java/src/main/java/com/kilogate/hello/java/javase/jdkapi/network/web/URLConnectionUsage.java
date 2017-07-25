package com.kilogate.hello.java.javase.jdkapi.network.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * URLConnection 用法
 *
 * @author fengquanwei
 * @create 2017/7/25 10:31
 **/
public class URLConnectionUsage {
    public static Logger logger = LoggerFactory.getLogger(URLConnectionUsage.class);

    public static void main(String[] args) {
        try {
            URL url = new URL("http://horstmann.com");
            URLConnection connection = url.openConnection();

            // 访问有密码保护的资源需要设置用户名和密码
            String username = "username";
            String passpord = "passport";
            String input = username + ":" + passpord;
            String encode = new BASE64Encoder().encode(input.getBytes());
            connection.setRequestProperty("Authorization", "Basic" + encode);

            connection.connect();

            Map<String, List<String>> header = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : header.entrySet()) {
                String key = entry.getKey();
                for (String value : entry.getValue()) {
                    logger.info(key + ": " + value);
                }
            }

            String contentType = connection.getContentType();
            int contentLength = connection.getContentLength();
            String contentEncoding = connection.getContentEncoding();
            long date = connection.getDate();
            long expiration = connection.getExpiration();
            long lastModified = connection.getLastModified();

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                logger.info(scanner.nextLine());
            }
        } catch (MalformedURLException e) {
            logger.error("Something wrong. ", e);
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}
