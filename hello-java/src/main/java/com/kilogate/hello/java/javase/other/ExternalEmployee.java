package com.kilogate.hello.java.javase.other;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * Externalnalizable 自定义序列化的员工
 *
 * @author fengquanwei
 * @create 2017/6/25 12:52
 **/
public class ExternalEmployee implements Externalizable {
    private String name;
    private double salary;
    private Date hireDay;

    public ExternalEmployee() {
    }

    public ExternalEmployee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    // 自定义序列化方法
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeDouble(salary);
        out.writeLong(hireDay.getTime());

    }

    // 自定义反序列化方法
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        salary = in.readDouble();
        hireDay = new Date(in.readLong());
    }

    @Override
    public String toString() {
        return "ExternalEmployee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
