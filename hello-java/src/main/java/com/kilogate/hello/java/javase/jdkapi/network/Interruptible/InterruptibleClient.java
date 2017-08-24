package com.kilogate.hello.java.javase.jdkapi.network.Interruptible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 可中断客户端
 *
 * @author fengquanwei
 * @create 2017/7/24 17:42
 **/
public class InterruptibleClient {
    public static Logger logger = LoggerFactory.getLogger(InterruptibleClient.class);

    public static void main(String[] args) {
        // 阻塞套接字
//        try {
//            try (Socket socket = new Socket("localhost", 8081)) {
//                Scanner scanner = new Scanner(socket.getInputStream());
//
//                while (!Thread.currentThread().isInterrupted()) {
//                    while (scanner.hasNextLine()) {
//                        String line = scanner.nextLine();
//                        logger.info(line);
//                        if (line != null && "6".equals(line)) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            logger.error("Something wrong. ", e);
//        }

        // 可中断套接字
        try {
            try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8081))) {
                Scanner scanner = new Scanner(socketChannel);

                while (!Thread.currentThread().isInterrupted()) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        logger.info(line);
                        if (line != null && "6".equals(line)) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}
