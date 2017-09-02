package com.kilogate.hello.tomcat.catalina3;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * 错误信息国际化管理
 *
 * @author fengquanwei
 * @create 2017/8/30 11:46
 **/
public class StringManager {
    private static Hashtable managers = new Hashtable();

    private String packageName;

    private StringManager(String packageName) {
        this.packageName = packageName;
    }

    public synchronized static StringManager getManager(String packageName) {
        StringManager manager = (StringManager) managers.get(packageName);
        if (manager == null) {
            manager = new StringManager(packageName);
            managers.put(packageName, manager);
        }
        return manager;
    }

    public String getString(String key) {
        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(packageName.replaceAll("\\.", "/") + "/LocalStrings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }
}
