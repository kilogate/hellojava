package com.kilogate.hello.java.javase.jdkapi.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 资源包用法
 * 资源文件编码工具：native2ascii
 *
 * @author fengquanwei
 * @create 2017/4/19 17:45
 **/
public class ResourceBundleUsage {
    public static void main(String[] args) {
        // 测试资源包
        Locale zhCNLocale = new Locale("zh", "CN");
        ResourceBundle zhCNResourceBundle = ResourceBundle.getBundle("i18n", zhCNLocale);
        System.out.println(zhCNResourceBundle.getString("hi"));

        Locale defaultLocale = Locale.getDefault();
        ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("i18n", defaultLocale);
        System.out.println(defaultResourceBundle.getString("hi"));

        Locale enUSLocale = new Locale("en", "US");
        ResourceBundle enUSResourceBundle = ResourceBundle.getBundle("i18n", enUSLocale);
        System.out.println(enUSResourceBundle.getString("hi"));
    }
}

