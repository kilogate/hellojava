package com.kilogate.hello.java.javase.other.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * 测试日志
 *
 * @author fengquanwei
 * @create 2017/4/6 23:24
 **/
public class LoggerUsage {
    /**
     * 测试日志打印
     */
    public String testLog(String param) throws IOException {
        // 重新读取配置文件
        InputStream configuration = ClassLoader.getSystemResourceAsStream("logging.properties");
        LogManager logManager = LogManager.getLogManager(); // 获取日志管理器
        logManager.readConfiguration(configuration);

        // 获取日志记录器
        Logger logger = Logger.getLogger(this.getClass().getName());

        // 添加日志处理器
        FileHandler fileHandler = new FileHandler("/opt/log/tmp%g.log", true);
        fileHandler.setFormatter(new SimpleFormatter()); // 设置日志处理器的日志格式化器
        logger.addHandler(fileHandler);

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

    /**
     * 测试日志本地化
     */
    public void testocalization(){
        Logger logger = Logger.getLogger("test.local", "local");

        logger.log(Level.INFO, "sayHi", "Lask");
    }

    public static void main(String[] args) throws IOException {
        Logger.getGlobal().info("测试默认日志记录器");

        LoggerUsage test = new LoggerUsage();
//        test.testLog("Lask");
        test.testocalization();

    }
}
