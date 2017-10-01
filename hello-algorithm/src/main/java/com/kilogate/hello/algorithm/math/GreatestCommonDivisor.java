package com.kilogate.hello.algorithm.math;

/**
 * 用欧几里得算法求最大公约数
 *
 * @author fengquanwei
 * @create 2017/10/1 21:49
 **/
public class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.println(gcd(2,3));
        System.out.println(gcd(10,8));
        System.out.println(gcd(5,8));
        System.out.println(gcd(6,10));
        System.out.println(gcd(30,90));
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
