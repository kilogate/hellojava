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

    private void testLogger() {
        logger.trace("===== logger.trace =====");
        logger.debug("===== logger.debug =====");
        logger.info("===== logger.info =====");
        logger.warn("===== logger.warn =====");
        logger.error("===== logger.error =====");
    }

    public static void main(String[] args) {
        LogbackUsage usage = new LogbackUsage();
        usage.testLogger();
    }

}
