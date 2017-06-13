package com.kilogate.hello.java.javase.core;

/**
 * Java 基本类型
 *
 * @author fengquanwei
 * @create 2017/6/12 19:45
 **/
public class PrimitiveType {
    public static void main(String[] args) {
        // ============================== 基本类型 ==============================
        byte b = 1;
        short s = 1;
        int i = 1;
        long l = 1;
        float f = 1.0F;
        double d = 1.0; // 1.0D（D 可选）
        char c = '1';
        boolean bl = true;

        // ============================== 数字字面量 ==============================

        // JAVA7 开始，支持数字字面量加下划线，增加可读性，编译器会去除下划线
        int a = 123_456_789;
        System.out.println(a);

        // ============================== 进制 ==============================

        // Java 没有二进制直接表示法
        // 八进制 1 x 8^0 + 1 x 8^1 = 9
        int octal = 011;
        System.out.println(octal);
        // 十六进制 1 x 16^0 + 1 x 16^1 = 17
        int hex = 0x11;
        System.out.println(hex);

        // 转换为二进制
        String binaryString = Integer.toBinaryString(a);
        System.out.println(binaryString);
        // 转换为八进制
        String octalString = Integer.toOctalString(a);
        System.out.println(octalString);
        // 转换为十六进制
        String hexString = Integer.toHexString(a);
        System.out.println(hexString);

        // ============================== 转义字符 ==============================
        System.out.println("退格\b退格");
        System.out.println("制表\t制表");
        System.out.println("换行\n换行");
        System.out.println("回车前\r回车后");
        System.out.println("走纸\f走纸");
        System.out.println("\"");
        System.out.println("\'");
        System.out.println("\\");

        // ============================== 包装器与自动装箱拆箱 ==============================
        // 四进制：3 x 4^0 + 2 x 4^1 + 1 x 4^2 = 27
        Integer integer = Integer.valueOf("123", 4);
        System.out.println(integer);

        // 变量名可用字符
        System.out.println(Character.isJavaIdentifierStart('1'));
        System.out.println(Character.isJavaIdentifierPart('$'));

    }
}
