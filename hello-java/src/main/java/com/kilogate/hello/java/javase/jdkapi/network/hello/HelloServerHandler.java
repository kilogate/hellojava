package com.kilogate.hello.java.javase.jdkapi.network.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello 服务器处理器
 *
 * @author fengquanwei
 * @create 2017/7/24 19:31
 **/
public class HelloServerHandler implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Socket socket;

    public HelloServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            try {
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                printWriter.println("Hello! Enter BYE to exit.\n");
                boolean done = false;

                while (!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    logger.info("Receive '" + line + "'.");
                    if (line != null && !line.equals("")) {
                        if (line.trim().equals("BYE")) {
                            printWriter.println("Bye! Bye! \n");
                            done = true;
                            logger.info("Exit.");
                        } else {
                            printWriter.println("Hello! " + line + "\n");
                        }
                    }
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            logger.error("Something wrong. ", e);
        }
    }
}
