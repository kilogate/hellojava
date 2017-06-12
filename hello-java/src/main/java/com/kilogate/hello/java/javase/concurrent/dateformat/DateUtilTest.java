package com.kilogate.hello.java.javase.concurrent.dateformat;

import java.text.ParseException;
import java.util.Date;

/**
 * 日期工具类测试类
 *
 * @author fengquanwei
 * @create 2017/6/1 16:08
 **/
public class DateUtilTest {
    public static class DateFormatTestThread extends Thread {
        @Override
        public void run() {
            System.out.println(new Date() + " : " + this.getName() + " : start.");
            while (true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(new Date() + " : " + this.getName() + " : " + DateUtil.parseDate("2017-06-01 16:00:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new DateFormatTestThread().start();
        }
    }
}
