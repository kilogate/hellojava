package com.kilogate.hello.java.javase.jdkapi.network.Interruptible;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 可中断处理器
 *
 * @author fengquanwei
 * @create 2017/7/24 19:31
 **/
class InterruptibleHandler implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Socket socket;
    private Integer counter;

    public InterruptibleHandler(Socket socket) {
        this.socket = socket;
        this.counter = 0;
    }

    @Override
    public void run() {
        try {
            try {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

                while (counter < 17) {
                    counter++;
                    printWriter.println(counter);
                    Thread.sleep(1000);
                }
            } finally {
                socket.close();
                logger.info("socket closed.");
            }
        } catch (Exception e) {
            logger.error("Something wrong. ", e);
        }
    }
}
