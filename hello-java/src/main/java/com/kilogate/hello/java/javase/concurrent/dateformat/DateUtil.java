package com.kilogate.hello.java.javase.concurrent.dateformat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author fengquanwei
 * @create 2017/6/1 16:07
 **/
public class DateUtil {
    //    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<DateFormat> sdf = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static String formatDate(Date date) throws ParseException {
        return sdf.get().format(date);
    }

    public static Date parseDate(String strDate) throws ParseException {
        return sdf.get().parse(strDate);
    }
}
