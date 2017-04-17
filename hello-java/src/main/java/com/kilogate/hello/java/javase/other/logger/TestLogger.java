package com.kilogate.hello.java.javase.other.logger;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 测试日志
 *
 * @author fengquanwei
 * @create 2017/4/6 23:24
 **/
public class TestLogger {
    /**
     * 测试日志打印
     */
    public String testLog(String param) {
        System.setProperty("java.util.logging.config.file", "logging.properites");

        Logger logger = Logger.getLogger(this.getClass().getName());

        // 跟踪执行流
        logger.entering(this.getClass().getName(), "testLog", new Object[]{param});

        // 测试打印
        logger.info("测试 logger.info");
        logger.config("测试 logger.config");

        // 测试指定级别的打印
        logger.log(Level.SEVERE, "测试指定 SEVERE 级别的日志");
        logger.log(Level.WARNING, "测试指定 WARNING 级别的日志");
        logger.log(Level.FINEST, "测试指定 FINEST 级别的日志"); // 为什么打不出来

        IOException ioException = new IOException("生成IO异常");
        logger.throwing(this.getClass().getName(), "testLog", ioException);
        logger.log(Level.WARNING, "产生异常了", ioException);

        String result = "Hello, " + param;

        // 跟踪执行流
        logger.exiting(this.getClass().getName(), "testLog", result);

        return result;
    }

    public static void main(String[] args) {
        Logger.getGlobal().info("测试默认日志记录器");

        TestLogger test = new TestLogger();
        test.testLog("Lask");

    }
}
