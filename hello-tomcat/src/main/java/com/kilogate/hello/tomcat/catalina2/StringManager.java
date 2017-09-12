package com.kilogate.hello.tomcat.catalina2;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

/**
 * 错误信息国际化管理
 * 每个包一个配置文件（LocalStrings.properties）
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
            String filepath = "localstrings/" + packageName.replaceAll("\\.", "/") + "/LocalStrings.properties";
            properties.load(ClassLoader.getSystemResourceAsStream(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        StringManager manager = StringManager.getManager(StringManager.class.getPackage().getName());
        String string = manager.getString("httpConnector.alreadyInitialized");
        System.out.println(string);
    }
}
