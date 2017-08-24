package com.kilogate.hello.java.javase.jdkapi.network.Interruptible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 可中断服务器
 *
 * @author fengquanwei
 * @create 2017/7/24 17:29
 **/
public class InterruptibleServer {
    public static Logger logger = LoggerFactory.getLogger(InterruptibleServer.class);

    public static void main(String[] args) {
        try {
            int i = 1;
            ServerSocket serverSocket = new ServerSocket(8081);
            while (true) {
                Socket socket = serverSocket.accept();

                logger.info("Spawning " + i);

                new Thread(new InterruptibleHandler(socket)).start();
                i++;
            }
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}

