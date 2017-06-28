package com.kilogate.hello.java.javase.other;

import com.kilogate.hello.java.javase.jdkapi.io.stream.SerialCloneable;

import java.util.Date;

/**
 * 使用序列化克隆的员工类
 *
 * @author fengquanwei
 * @create 2017/6/25 20:12
 **/
public class SerialCloneEmployee extends SerialCloneable{
    private String name;
    private double salary;
    private Date hireDay;

    public SerialCloneEmployee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    @Override
    public String toString() {
        return "SerialCloneEmployee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
