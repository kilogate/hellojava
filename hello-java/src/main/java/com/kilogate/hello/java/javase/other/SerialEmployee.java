package com.kilogate.hello.java.javase.other;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serializable 自定义序列化的员工
 *
 * @author fengquanwei
 * @create 2017/6/25 12:10
 **/
public class SerialEmployee implements Serializable {
    private String name;
    private double salary;
    private transient HireDay hireDay; // 声明为瞬时域，跳过默认序列化

    public SerialEmployee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = new HireDay(year, month, day);
    }

    // 自定义序列化方法
    private void writeObject(ObjectOutputStream out) throws IOException {
        // 调用默认的序列化方法
        out.defaultWriteObject();
        // 手动序列化不可序列化的类
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonth());
        out.writeInt(hireDay.getDay());
    }

    // 自定义反序列化方法
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // 调用默认的反序列化方法
        in.defaultReadObject();
        // 手动反序列化不可序列化的类
        int year = in.readInt();
        int month = in.readInt();
        int day = in.readInt();
        hireDay = new HireDay(year, month, day);
    }

    @Override
    public String toString() {
        return "SerialEmployee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}

class HireDay {
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