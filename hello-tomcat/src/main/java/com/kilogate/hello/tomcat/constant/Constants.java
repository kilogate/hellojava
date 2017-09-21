package com.kilogate.hello.tomcat.constant;

import java.io.File;

/**
 * 常量
 *
 * @author fengquanwei
 * @create 2017/8/28 14:15
 **/
public class Constants {
    /**
     * Working Directory 即 user.dir /Users/fengquanwei/IdeaProjects/hello/hello-tomcat
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static final String PACKAGE = "com.kilogate.hello.tomcat";

    public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
}
