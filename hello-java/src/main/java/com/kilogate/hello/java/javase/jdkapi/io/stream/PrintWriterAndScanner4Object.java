package com.kilogate.hello.java.javase.jdkapi.io.stream;

import com.kilogate.hello.java.javase.other.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Scanner and PrintWriter 读写对象
 *
 * @author fengquanwei
 * @create 2017/6/20 17:44
 **/
public class PrintWriterAndScanner4Object {
    public static void main(String[] args) throws IOException {
        Employee[] employees = new Employee[3];

        employees[0] = new Employee("Tom", 7000, new Date());
        employees[1] = new Employee("Bob", 8000, new Date());
        employees[2] = new Employee("Tony", 9000, new Date());

        try (PrintWriter writer = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(employees, writer);
        }

        try (Scanner scanner = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] employeesData = readData(scanner);
            System.out.println(Arrays.toString(employeesData));
        }

    }

    /**
     * 写数据
     */
    public static void writeData(Employee[] employees, PrintWriter printWriter) {
        printWriter.println(employees.length);
        for (Employee employee : employees) {
            writeEmployee(printWriter, employee);
        }
    }

    private static void writeEmployee(PrintWriter printWriter, Employee employee) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(employee.getHireDay());

        printWriter.println(employee.getName() + "|" + employee.getSalary() + "|" + calendar.get(Calendar.YEAR) + "|" + calendar.get(Calendar.MONTH) + "|" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 读数据
     */
    public static Employee[] readData(Scanner scanner) {
        int n = scanner.nextInt();
        scanner.nextLine();

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(scanner);
        }
        return employees;
    }

    private static Employee readEmployee(Scanner scanner) {
        String line = scanner.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        int month = Integer.parseInt(tokens[3]);
        int day = Integer.parseInt(tokens[4]);

        return new Employee(name, salary, year, month, day);
    }

}