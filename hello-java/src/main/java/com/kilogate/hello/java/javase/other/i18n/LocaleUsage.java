package com.kilogate.hello.java.javase.other.i18n;

import java.util.Locale;

/**
 * 语言和区域
 *
 * @author fengquanwei
 * @create 2017/4/19 18:05
 **/
public class LocaleUsage {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();

        String country = locale.getCountry();
        String displayCountry = locale.getDisplayCountry();
        String displayCountryEnUS = locale.getDisplayCountry(new Locale("en", "US"));
        String language = locale.getLanguage();
        String displayLanguage = locale.getDisplayLanguage();
    }
}
