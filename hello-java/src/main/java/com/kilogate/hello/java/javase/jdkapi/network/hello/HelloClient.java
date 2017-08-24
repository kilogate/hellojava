package com.kilogate.hello.java.javase.jdkapi.network.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            // 发送字符流
            printWriter.println("英雄");
            printWriter.println("美人");

            // 接收字符流
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}
