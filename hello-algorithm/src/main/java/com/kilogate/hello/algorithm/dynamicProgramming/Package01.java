package com.kilogate.hello.algorithm.dynamicProgramming;

/**
 * Created by fengquanwei on 2016/10/19.
 * 01背包问题
 */
public class Package01 {
    // c[i][j]=max{c[i-1][j], c[i-1][j-w[i]]+p[i]}
    public int[][] pack(int m, int n, int w[], int p[]) {
        //c[i][j] 表示前 i 件物品恰放入一个重量为 j 的背包可以获得的最大价值
        int c[][] = new int[m + 1][n + 1];

        for (int i = 1; i < m + 1; i++) { // 物品
            for (int j = 1; j < n + 1; j++) { // 重量
                if (j >= w[i - 1]) {
                    c[i][j] = max(c[i - 1][j], c[i - 1][j - w[i - 1]] + p[i - 1]);
                } else {
                    c[i][j] = c[i - 1][j];
                }
            }
        }

        return c;
    }

    private int max(int x, int y) {
        return x > y ? x : y;
    }

    // 逆推法求出最优解
    public void printPack(int c[][], int w[], int p[], int m, int n) {
        int x[] = new int[m]; // 物品选择
        StringBuffer weight = new StringBuffer("Weight: " + n + " >= ");
        StringBuffer value = new StringBuffer("Value: " + c[m][n] + " = ");

        // 从最后一个状态记录 c[m][n] 开始逆推
        for (int i = m; i > 0; i--) {
            // 如果c[i][n] 大于 c[i-1][n]，说明 c[i][n] 这个最优值中包含了 w[i-1] (注意这里是i-1，因为c数组长度是m+1)
            if (c[i][n] > c[i - 1][n]) {
                x[i - 1] = 1;
                n -= w[i - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            if (x[i] == 1) {
                weight.append(w[i]).append(" + ");
                value.append(p[i]).append(" + ");
            }
        }

        System.out.println(weight.toString().substring(0, weight.length() - 2));
        System.out.println(value.toString().substring(0, value.length() - 2));
    }

    public static void main(String args[]) {
        int m = 3; // 物品数量
        int n = 10; // 背包重量
        int w[] = {3, 4, 5}; // 物品重量
        int p[] = {4, 5, 6}; // 物品价值

        Package01 pack = new Package01();
        int c[][] = pack.pack(m, n, w, p);
        pack.printPack(c, w, p, m, n);
    }
}
