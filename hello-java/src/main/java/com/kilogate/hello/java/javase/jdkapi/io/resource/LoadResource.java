package com.kilogate.hello.java.javase.jdkapi.io.resource;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 加载资源文件（图片，音频，xml 等）
 * 一、Class
 * 二、ClassLoader
 *
 * @author fengquanwei
 * @create 2017/4/14 13:24
 **/
public class LoadResource {
    /**
     * getResource：返回资源
     * getResourceAsStream：返回流
     */
    public void testGetResource() throws IOException {
        Class<? extends LoadResource> clazz = this.getClass();
        ClassLoader classLoader = clazz.getClassLoader();

        // 一、Class.getResource(path); 相对路径：相对类所在包路径
        URL clzPackageUri = clazz.getResource(""); // 类所在包路径
        URL classpathUri1 = clazz.getResource("/"); // classpath

        // 二、ClassLoader.getResource(path); 相对路径：相对 classpath，注意：path 不能以 "/" 开头
        URL classpathUri2 = classLoader.getResource(""); // classpath
        URL classpathUri3 = classLoader.getResource("/"); // 错误！！！

        String userDir = System.getProperty("user.dir"); // JVM 启动路径

        // 三、获取资源
        URL resource1 = clazz.getResource("../../../../../../../../resource.txt"); // 相对类所在包
        URL resource2 = clazz.getResource("/resource.txt"); // 绝对路径（以 classpath 为根目录）
        URL resource3 = classLoader.getResource("resource.txt"); // 相对 classpath
        URL resource4 = ClassLoader.getSystemResource("resource.txt"); // 相对 classpath
        URL resource5 = ClassLoader.getSystemClassLoader().getResource("resource.txt"); // 相对 classpath

        Properties properties = new Properties();
        properties.load(clazz.getResourceAsStream("/resource.txt"));
        System.out.println(properties.getProperty("name"));

        // 四、系统资源
        URL classpathUri4 = ClassLoader.getSystemResource(""); // classpath
        // 五、当前线程
        URL classpathUri5 = Thread.currentThread().getContextClassLoader().getResource(""); // classpath

    }

    public static void main(String[] args) throws IOException {
        LoadResource test = new LoadResource();
        test.testGetResource();
    }
}
