package com.kilogate.hello.java.javase.jdkapi.i18n;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

/**
 * 数字格式器用法
 *
 * @author fengquanwei
 * @create 2017/8/4 14:36
 **/
public class NumberFormatUsage {
    public static void main(String[] args) {
        // 数字格式器
        System.out.println("\n\n==================== 数字格式器 ====================");
        NumberFormat chinaNumberFormat = NumberFormat.getNumberInstance(Locale.CHINA);
        NumberFormat germanyNumberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        NumberFormat franceNumberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);
        NumberFormat koreaNumberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        NumberFormat japanNumberFormat = NumberFormat.getNumberInstance(Locale.JAPAN);
        NumberFormat usNumberFormat = NumberFormat.getNumberInstance(Locale.US);
        double number = 1234567.89;
        System.out.println(chinaNumberFormat.format(number));
        System.out.println(germanyNumberFormat.format(number));
        System.out.println(franceNumberFormat.format(number));
        System.out.println(koreaNumberFormat.format(number));
        System.out.println(japanNumberFormat.format(number));
        System.out.println(usNumberFormat.format(number));

        // 解析
        try {
            Number parseNumber = germanyNumberFormat.parse("1.234.567,89  哈哈哈"); // 任何跟在数字之后的字符都将被忽略
            System.out.println(parseNumber.doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 货币量格式器
        System.out.println("\n\n==================== 货币量格式器 ====================");
        NumberFormat chinaCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat germanyCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        NumberFormat franceCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        NumberFormat koreaCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        NumberFormat japanCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        NumberFormat usCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        double currency = 1234567.89;
        System.out.println(chinaCurrencyFormat.format(currency));
        System.out.println(germanyCurrencyFormat.format(currency));
        System.out.println(franceCurrencyFormat.format(currency));
        System.out.println(koreaCurrencyFormat.format(currency));
        System.out.println(japanCurrencyFormat.format(currency));
        System.out.println(usCurrencyFormat.format(currency));

        // 设置货币标识符
        usCurrencyFormat.setCurrency(Currency.getInstance("USD"));
        System.out.println(usCurrencyFormat.format(currency));
        usCurrencyFormat.setCurrency(Currency.getInstance("EUR"));
        System.out.println(usCurrencyFormat.format(currency));
        usCurrencyFormat.setCurrency(Currency.getInstance("GBP"));
        System.out.println(usCurrencyFormat.format(currency));
        usCurrencyFormat.setCurrency(Currency.getInstance("JPY"));
        System.out.println(usCurrencyFormat.format(currency));
        usCurrencyFormat.setCurrency(Currency.getInstance("CNY"));
        System.out.println(usCurrencyFormat.format(currency));
        usCurrencyFormat.setCurrency(Currency.getInstance("INR"));
        System.out.println(usCurrencyFormat.format(currency));
        usCurrencyFormat.setCurrency(Currency.getInstance("RUB"));
        System.out.println(usCurrencyFormat.format(currency));

        // 百分比格式器
        System.out.println("\n\n==================== 百分比格式器 ====================");
        NumberFormat chinaPercentFormat = NumberFormat.getPercentInstance(Locale.CHINA);
        NumberFormat germanyPercentFormat = NumberFormat.getPercentInstance(Locale.GERMANY);
        NumberFormat francePercentFormat = NumberFormat.getPercentInstance(Locale.FRANCE);
        NumberFormat koreaPercentFormat = NumberFormat.getPercentInstance(Locale.KOREA);
        NumberFormat japanPercentFormat = NumberFormat.getPercentInstance(Locale.JAPAN);
        NumberFormat usPercentFormat = NumberFormat.getPercentInstance(Locale.US);
        double percent = 0.99;
        System.out.println(chinaPercentFormat.format(percent));
        System.out.println(germanyPercentFormat.format(percent));
        System.out.println(francePercentFormat.format(percent));
        System.out.println(koreaPercentFormat.format(percent));
        System.out.println(japanPercentFormat.format(percent));
        System.out.println(usPercentFormat.format(percent));
    }
}
