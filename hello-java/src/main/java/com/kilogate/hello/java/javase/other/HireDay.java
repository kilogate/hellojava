package com.kilogate.hello.java.javase.other;

/**
 * 招聘日期
 *
 * @author fengquanwei
 * @create 2017/8/4 20:07
 **/
public class HireDay {
    private int year;
    private int month;
    private int day;

    public HireDay(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "HireDay{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
