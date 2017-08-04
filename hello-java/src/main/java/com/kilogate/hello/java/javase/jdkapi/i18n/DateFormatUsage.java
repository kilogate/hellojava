package com.kilogate.hello.java.javase.jdkapi.i18n;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * 日期和时间格式器用法
 *
 * @author fengquanwei
 * @create 2017/8/4 15:25
 **/
public class DateFormatUsage {
    public static void main(String[] args) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.CHINA);
        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.CHINA);
        DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);

        System.out.println(dateTimeFormat.format(new Date()));
        try {
            Date parseDate = dateTimeFormat.parse("2017年8月4日 星期五 下午03时32分16秒 CST");
            System.out.println(dateFormat.format(parseDate));
            System.out.println(timeFormat.format(parseDate));
            System.out.println(DateFormat.getDateTimeInstance().format(parseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
