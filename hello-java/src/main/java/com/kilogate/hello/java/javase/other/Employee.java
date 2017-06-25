package com.kilogate.hello.java.javase.other;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 员工类
 *
 * @author fengquanwei
 * @create 2017/6/21 20:51
 **/
public class Employee implements Serializable {
    public static int NAME_SIZE = 40;
    public static int RECORD_SIZE = 100; // 每条记录包含 100 个字节

    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);
        this.hireDay = calendar.getTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    public void setHireDay(Date hireDay) {
        this.hireDay = hireDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }


}