package com.kilogate.hello.algorithm.dynamicProgramming;

/**
 * Created by fengquanwei on 2016/10/17.
 * 凑硬币问题
 */
public class MinCoins {
    // 取还是不取的问题
    private int minCoins(int[] v, int n) {
        int[] d = new int[n + 1]; // d[i] 凑 i 元需要的最少硬币个数
        d[0] = 0; // 凑 0 元需要 0 个
        for (int i = 1; i <= n; i++) {
            d[i] = Integer.MAX_VALUE; // 赋初始值
        }

        int[] c = new int[n + 1]; // 最后那个硬币面值

        for (int i = 1; i <= n; i++) { // 欲求 d[i]，先求 d[1] ~ d[i-1]
            for (int j = 0, len = v.length; j < len; j++) {
                if (i >= v[j]) {
                    d[i] = min(d[i], d[i - v[j]] + 1);
                    c[i] = v[j];
                }
            }
        }

        // 打印凑硬币的方案

        StringBuffer sb = new StringBuffer();
        sb.append("Min coins plan: ");
        int rest = n;
        while (rest > 0) {
            sb.append(c[rest]).append(" + ");
            rest -= c[rest];
        }
        System.out.println(sb.toString().substring(0, sb.length() - 2));

        return d[n];
    }

    private int min(int a, int b) {
        return a > b ? b : a;
    }

    public static void main(String[] args) {
        MinCoins mc = new MinCoins();

        // 硬币种类
        int[] v = {1, 2, 3, 5};

        System.out.println("Min coins count: " + mc.minCoins(v, 11));
    }
}
