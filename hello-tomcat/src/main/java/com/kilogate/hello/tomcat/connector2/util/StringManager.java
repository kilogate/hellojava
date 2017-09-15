package com.kilogate.hello.tomcat.connector2.util;

import java.util.Hashtable;
import java.util.ResourceBundle;

/**
 * 错误信息国际化管理
 *
 * @author fengquanwei
 * @create 2017/8/30 11:46
 **/
public class StringManager {
    private static Hashtable managers = new Hashtable();

    private ResourceBundle bundle;

    private StringManager(String packageName) {
        bundle = ResourceBundle.getBundle(packageName + ".LocalStrings");
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
        return bundle.getString(key);
    }
}
