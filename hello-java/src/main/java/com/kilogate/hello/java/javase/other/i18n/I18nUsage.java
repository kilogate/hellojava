package com.kilogate.hello.java.javase.other.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化用法
 * 资源文件编码工具：native2ascii
 *
 * @author fengquanwei
 * @create 2017/4/19 17:45
 **/
public class I18nUsage {
    public static void main(String[] args) {
        Locale locale1 = new Locale("zh", "CN");
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("i18n_resource", locale1);
        System.out.println(resourceBundle1.getString("hi"));

        Locale locale2 = Locale.getDefault();
        ResourceBundle resourceBundle2 = ResourceBundle.getBundle("i18n_resource", locale2);
        System.out.println(resourceBundle2.getString("hi"));

        Locale locale3 = new Locale("en", "US");
        ResourceBundle resourceBundl3 = ResourceBundle.getBundle("i18n_resource", locale3);
        System.out.println(resourceBundl3.getString("hi"));

    }
}