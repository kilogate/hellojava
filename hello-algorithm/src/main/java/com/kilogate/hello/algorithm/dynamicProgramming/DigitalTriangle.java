package com.kilogate.hello.algorithm.dynamicProgramming;

/**
 * Created by fengquanwei on 2016/10/20.
 * 数字三角形问题
 */
public class DigitalTriangle {
    // 打印数字三角形
    public static void printTriangle(int[][] a) {
        for (int i = 1, length = a.length; i < length; i++) {
            for (int k = 1; k < length - i; k++) {
                System.out.print("  ");
            }
            for (int j = 1, len = a[i].length; j < len; j++) {
                if (a[i][j] < 10) {
                    System.out.print("0");
                }
                System.out.print(a[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[][] a = new int[n + 1][];
//
//        for (int i = 1; i <= n; i++) {
//            a[i] = new int[i + 1];
//            for (int j = 1; j <= i; j++) {
//                a[i][j] = sc.nextInt();
//            }
//        }

        int n = 5; // 行数
        int[][] a = {null, {0, 7}, {0, 3, 8}, {0, 8, 1, 0}, {0, 2, 7, 4, 4}, {0, 4, 5, 2, 6, 5}}; // 原数字三角形

        System.out.println("Before================================================");
        printTriangle(a);

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i - 1; j++) {
                a[i - 1][j] += Math.max(a[i][j], a[i][j + 1]);
            }
        }

        System.out.println("After=================================================");
        printTriangle(a);

        System.out.println("Result================================================");
        System.out.println("Optimum value: " + a[1][1]);

        // 打印最优解
        StringBuffer sb = new StringBuffer("Optimum plan: ");
        int j = 1;
        for (int i = 1; i <= n; i++) {
            if (i == n) {
                if (a[i][j] > a[i][j + 1]) {
                    sb.append(a[i][j] + " + ");
                } else {
                    sb.append(a[i][j + 1] + " + ");
                }
                break;
            }

            int left = a[i + 1][j];
            int right = a[i + 1][j + 1];

            if (left > right) {
                sb.append(a[i][j] - a[i + 1][j] + " + ");
            } else {
                sb.append(a[i][j] - a[i + 1][j + 1] + " + ");
                j = j + 1;
            }
        }
        System.out.println(sb.toString().substring(0, sb.length() - 2));
    }
}
