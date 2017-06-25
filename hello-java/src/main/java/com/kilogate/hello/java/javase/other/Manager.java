package com.kilogate.hello.java.javase.other;

import java.util.Date;

/**
 * 经理类
 *
 * @author fengquanwei
 * @create 2017/6/24 23:12
 **/
public class Manager extends Employee{
    private Employee secretary;

    public Manager(Employee secretary) {
        this.secretary = secretary;
    }

    public Manager(String name, double salary, Date hireDay, Employee secretary) {
        super(name, salary, hireDay);
        this.secretary = secretary;
    }

    public Manager(String name, double salary, int year, int month, int day, Employee secretary) {
        super(name, salary, year, month, day);
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "secretary=" + secretary +
                "} " + super.toString();
    }
}
