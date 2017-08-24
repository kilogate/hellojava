package com.kilogate.hello.java.javase.jdkapi.network.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello 服务器
 *
 * @author fengquanwei
 * @create 2017/7/23 18:18
 **/
public class HelloServer {
    public static Logger logger = LoggerFactory.getLogger(HelloServer.class);

    public static void main(String[] args) {
        try {
            int i = 1;
            ServerSocket serverSocket = new ServerSocket(8189);
            while (true) {
                Socket socket = serverSocket.accept();

                logger.info("Spawning " + i);

                new Thread(new HelloServerHandler(socket)).start();
                i++;
            }
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}