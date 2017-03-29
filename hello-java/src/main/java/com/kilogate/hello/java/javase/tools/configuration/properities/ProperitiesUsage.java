package com.kilogate.hello.java.javase.tools.configuration.properities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性映射用法
 *
 * @author kilogate
 * @create 2017/3/29 17:47
 **/
public class ProperitiesUsage {
    public static void main(String[] args) throws IOException {
        // 一、创建属性映射
        Properties properties = new Properties();
        properties.put("name", "Lask");
        properties.put("sex", "Man");

//        // 二、保存属性映射到文件
//        String filename = "program.properties";
//        FileOutputStream out = new FileOutputStream(new File(filename));
//        properties.store(out, "comment");
//
//        // 三、从文件加载属性映射
//        FileInputStream in = new FileInputStream(filename);
//        Properties newProperties = new Properties();
//        newProperties.load(in);

        // 四、系统配置
        Properties systemProperities = System.getProperties();
        String userHome = systemProperities.getProperty("user.home", "defaultValue"); // 获取属性并设置默认值
        String userHome2 = System.getProperty("user.home", "defaultValue"); // 获取单个系统属性

        // 五、将默认值放入二级属性映射，用二级属性映射构造主属性映射
        Properties defaultProperities = new Properties();
        defaultProperities.put("name", "default");
        defaultProperities.put("sex", "default");

        Properties mainProperities = new Properties(defaultProperities);
        mainProperities.put("name", "Lask");
        System.out.println(mainProperities.getProperty("name"));
        System.out.println(mainProperities.getProperty("sex"));

    }
}
