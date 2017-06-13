package com.kilogate.hello.java.javase.jdkapi.tool;

import java.util.Arrays;

/**
 * Arrays 工具类
 *
 * @author fengquanwei
 * @create 2017/6/12 20:09
 **/
public class ArraysTool {
    public static void main(String[] args) {
        int[][] twoDimensionalArray = new int[][]{{1, 3, 2}, {6, 5, 4}, {7, 8}};
        int[] a = new int[]{3, 2, 1};
        int[] b = new int[]{1, 2, 3};

        System.out.println(Arrays.hashCode(twoDimensionalArray));
        System.out.println(Arrays.toString(twoDimensionalArray));
        System.out.println(Arrays.deepToString(twoDimensionalArray));

        System.out.println(Arrays.equals(a, b));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.equals(a, b));
    }
}
