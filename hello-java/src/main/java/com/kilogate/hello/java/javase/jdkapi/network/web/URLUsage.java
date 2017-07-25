package com.kilogate.hello.java.javase.jdkapi.network.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * URL 用法
 *
 * @author fengquanwei
 * @create 2017/7/24 22:28
 **/
public class URLUsage {
    public static Logger logger = LoggerFactory.getLogger(URLUsage.class);

    public static void main(String[] args) {
        try {
            URL url = new URL("http://horstmann.com/index.html");
            InputStream inputStream = url.openStream();
            Scanner scanner = new Scanner(inputStream);

            StringBuffer html = new StringBuffer();
            while (scanner.hasNextLine()) {
                html.append(scanner.nextLine());
            }
            logger.info(html.toString());
        } catch (MalformedURLException e) {
            logger.error("Something wrong. ", e);
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}
