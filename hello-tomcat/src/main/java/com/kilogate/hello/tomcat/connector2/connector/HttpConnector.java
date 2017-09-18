package com.kilogate.hello.tomcat.connector2.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 连接器
 *
 * @author fengquanwei
 * @create 2017/8/30 14:46
 **/
public class HttpConnector implements Runnable {
    boolean stopped;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8081, 1, InetAddress.getByName("127.0.0.1"))) {
            while (!stopped) {
                Socket socket = serverSocket.accept();

                HttpProcessor processor = new HttpProcessor();
                processor.process(socket);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }
}
