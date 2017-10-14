package com.kilogate.hello.algorithm.core;

/**
 * 常用数学算法
 *
 * @author fengquanwei
 * @create 2017/10/1 21:49
 **/
public class Math {
    public static void main(String[] args) {
        System.out.println(gcd(30, 90));
    }

    /**
     * 最大公约数（欧几里得算法）GreatestCommonDivisor
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
