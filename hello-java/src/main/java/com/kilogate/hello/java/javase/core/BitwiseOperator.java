package com.kilogate.hello.java.javase.core;

/**
 * 位运算符
 *
 * @author fengquanwei
 * @create 2017/6/12 20:35
 **/
public class BitwiseOperator {
    public static void main(String[] args) {
        // 左移，低位补零
        System.out.println("左移: ");
        System.out.println(5);
        System.out.println(toBinaryString(5));
        System.out.println("5 << 2 = " + (5 << 2));
        System.out.println(toBinaryString(5 << 2));
        System.out.println();

        // 右移，高位补符号位
        System.out.println("右移: ");
        System.out.println(-5);
        System.out.println(toBinaryString(-5));
        System.out.println("-5 >> 3 = " + (-5 >> 3));
        System.out.println(toBinaryString(-5 >> 3));
        System.out.println();

        // 无符号右移，高位补零
        System.out.println("无符号右移: ");
        System.out.println(-5);
        System.out.println(toBinaryString(-5));
        System.out.println("-5 >>> 3 = " + (-5 >>> 3)); // 0001 1111 1111 1111 1111 1111 1111 1111
        System.out.println(toBinaryString(-5 >>> 3));
        System.out.println();

        // 按位与
        System.out.println("按位与: ");
        System.out.println(5);
        System.out.println(toBinaryString(5));
        System.out.println(toBinaryString(1));
        System.out.println("5 & 1 = " + (5 & 1));
        System.out.println(toBinaryString(5 & 1));
        System.out.println();

        // 按位或
        System.out.println("按位或: " + 5);
        System.out.println(5);
        System.out.println(toBinaryString(5));
        System.out.println(toBinaryString(1));
        System.out.println("5 | 1 = " + (5 | 1));
        System.out.println(toBinaryString(5 | 1));
        System.out.println();

        // 按位非
        System.out.println("按位非: ");
        System.out.println(5);
        System.out.println(toBinaryString(5));
        System.out.println("~5 = " + (~5));
        System.out.println(toBinaryString(~5));
        System.out.println();

        // 按位亦或
        System.out.println("按位亦或: ");
        System.out.println(5);
        System.out.println(toBinaryString(5));
        System.out.println(toBinaryString(1));
        System.out.println("5 ^ 1 = " + (5 ^ 1));
        System.out.println(toBinaryString(5 ^ 1));
        System.out.println();
    }

    private static String toBinaryString(Integer integer) {
        char[] result = new char[40];
        int index = 0;
        for (int i = 0; i < 32; i++) {
            result[index++] = (integer >> (31 - i) & 1) == 0 ? '0' : '1';
            if ((i + 1) % 4 == 0) {
                result[index++] = ' ';
            }
        }
        return String.valueOf(result);
    }
}
