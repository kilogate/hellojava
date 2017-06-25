package com.kilogate.hello.java.javase.jdkapi.io.stream;

import com.kilogate.hello.java.javase.other.Employee;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 随机访问文件读写对象
 *
 * @author fengquanwei
 * @create 2017/6/21 20:53
 **/
public class RandomAccessFile4Object {
    public static void main(String[] args) throws IOException {
        Employee[] employees = new Employee[3];

        employees[0] = new Employee("Tom", 7000, new Date());
        employees[1] = new Employee("Bob", 8000, new Date());
        employees[2] = new Employee("Tony", 9000, new Date());

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("/tmp/employee.dat"))) {
            for (Employee employee : employees) {
                writeData(out, employee);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("/tmp/employee.dat", "r")) {
            int n = (int) (in.length() / Employee.RECORD_SIZE);
            Employee[] staffs = new Employee[n];
            for (int i = n - 1; i >= 0; i--) {
                staffs[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                staffs[i] = readData(in);
            }

            for (Employee employee : staffs) {
                System.out.println(employee);
            }
        }

    }

    public static void writeData(DataOutput out, Employee employee) throws IOException {
        writeFixedString(employee.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(employee.getSalary());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(employee.getHireDay());
        out.writeInt(calendar.get(Calendar.YEAR));
        out.writeInt(calendar.get(Calendar.MONTH) + 1);
        out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static Employee readData(DataInput in) throws IOException {
        String name = readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int year = in.readInt();
        int month = in.readInt();
        int day = in.readInt();
        return new Employee(name, salary, year, month - 1, day);
    }

    public static void writeFixedString(String string, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char c = 0; // string 不足用 0 补
            if (i < string.length()) {
                c = string.charAt(i);
            }
            out.writeChar(c);
        }
    }

    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuffer result = new StringBuffer(size);
        int i = 0;
        boolean more = true;

        while (more && i < size) {
            char c = in.readChar();
            i++;
            if (c == 0) {
                more = false;
            } else {
                result.append(c);
            }
        }

        in.skipBytes(2 * (size - i)); // 跳过多余的 0

        return result.toString();
    }

}
