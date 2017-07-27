package com.kilogate.hello.java.javase.jdkapi.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logback 用法
 * 在 Maven project 里，logback.xml 文件必须放在 $PROJECT_HOME/src/main/resources 目录中
 *
 * @author fengquanwei
 * @create 2017/7/20 11:46
 **/
public class LogbackUsage {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger userLogger = LoggerFactory.getLogger("user");

    private void testLogger() {
        logger.trace("===== logger.trace =====");
        logger.debug("===== logger.debug =====");
        logger.info("===== logger.info =====");
        logger.warn("===== logger.warn =====");
        logger.error("===== logger.error =====");
    }

    private void testUserLogger(){
        userLogger.info("12345670");
        userLogger.info("12345671");
        userLogger.info("12345672");
        userLogger.info("12345673");
        userLogger.info("12345674");
        userLogger.info("12345675");
        userLogger.info("12345676");
        userLogger.info("12345677");
        userLogger.info("12345678");
        userLogger.info("12345679");
    }

    public static void main(String[] args) {
        LogbackUsage usage = new LogbackUsage();
        usage.testUserLogger();
    }

}
