package com.kilogate.hello.java.javase.jdkapi.network.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello 客户端
 *
 * @author fengquanwei
 * @create 2017/7/24 16:56
 **/
public class HelloClient {
    public static Logger logger = LoggerFactory.getLogger(HelloClient.class);

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            printWriter.println("英雄");
            printWriter.println("美人");

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}
