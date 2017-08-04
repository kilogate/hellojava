package com.kilogate.hello.java.javase.jdkapi.i18n;

import java.text.DateFormat;
import java.util.Arrays;
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
        System.out.println(locale.getCountry());
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getLanguage());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayName());

        System.out.println(locale.getDisplayName(Locale.GERMANY));
        System.out.println(locale.getDisplayCountry(Locale.KOREA));

        Locale[] availableLocales = DateFormat.getAvailableLocales();
        System.out.println(Arrays.toString(availableLocales));
    }
}
