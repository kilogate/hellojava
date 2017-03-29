package com.kilogate.hello.algorithm.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengquanwei on 2016/9/18.
 * 钢条切割问题
 * r(n) = max(p(i) + r(n-i))
 */
public class RodCut {
    private int count = 0; // 执行次数
    private int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30}; // 价格表

    // 自顶向下的递归实现
    // Rn = max(Pn, R1 + Rn-1, R2 + Rn-2,…,Rn-1 + R1)
    // 时间复杂度：O(2^N)，考察了2^(N-1)种可能（为什么？切还是不切）
    public int cutRod(int n) {
        System.out.println("Subproblem Scale: " + n);
        count++;
        int q = 0; // Optimum value
        if (n > 0) {
            int length = n < p.length ? n : p.length;
            for (int i = 1; i <= length; i++) {
                q = max(q, p[i - 1] + cutRod(n - i));
            }
        }
        System.out.println("Subproblem Scale: " + n + ", Optimum value: " + q);
        System.out.println();
        return q;
    }

    // 动态规划：带备忘的自顶向下
    private Map<Integer, Integer> r = new HashMap<Integer, Integer>(); // 备忘录
    public int memoizedCutRod(int n) {
        if (r.get(n) != null) {
            return r.get(n);
        }
        System.out.println("Subproblem Scale: " + n);
        count++;
        int q = 0;
        if (n > 0) {
            int length = n < p.length ? n : p.length;
            for (int i = 1; i <= length; i++) {
                q = max(q, p[i - 1] + memoizedCutRod(n - i));
            }
        }
        r.put(n, q);
        System.out.println("Subproblem Scale: " + n + ", Optimum value: " + q);
        return q;
    }

    // 动态规划：自底向上
    public int bottomUpCutRod(int n) {
        r.put(0, 0);
        for (int j = 1; j <= n; j++) {
            System.out.println("Subproblem Scale: " + j);
            count++;
            int q = 0;
            int length = j < p.length ? j : p.length;
            for (int i = 1; i <= length; i++) {
                q = max(q, p[i - 1] + r.get(j - i));
            }
            System.out.println("Subproblem Scale: " + j + ", Optimum value: " + q);
            r.put(j, q);
        }
        return r.get(n);
    }

    // 动态规划：自底向上（记录最优切割方案）
    private Map<Integer, Integer> s = new HashMap<Integer, Integer>(); // 切割方案
    public int extendBottomUpCutRod(int n) {
        r.put(0, 0);
        for (int j = 1; j <= n; j++) {
            System.out.println("Subproblem Scale: " + j);
            count++;
            int q = 0;
            int length = j < p.length ? j : p.length;
            for (int i = 1; i <= length; i++) {
                if (q < p[i - 1] + r.get(j - i)) {
                    q = p[i - 1] + r.get(j - i);
                    s.put(j, i); // 记录长度为 j 的钢条的最优切割方案的第一段长度
                }
            }
            System.out.println("Subproblem Scale: " + n + ", Optimum value: " + q);
            r.put(j, q);
        }
        return r.get(n);
    }

    // 打印切割方案
    public void printCutRodSolution(int n, Map<Integer, Integer> s) {
        if (s != null && s.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("Cut plan: ").append(n).append(" = ");
            while (n > 0) { // 递归打印
                sb.append(s.get(n)).append(" + ");
                n = n - s.get(n);
            }
            System.out.println(sb.toString().substring(0, sb.length() - 2));
        }
    }

    private int max(int x, int y) {
        return x > y ? x : y;
    }

    public static void main(String[] args) {
        RodCut cr = new RodCut();

        // 自顶向下的递归实现
//        System.out.println("Optimum value: " + cr.cutRod(100));
//        System.out.println("Execute count: " + cr.count);

        // 动态规划：带备忘的自顶向下
//        System.out.println("Optimum value: " + cr.memoizedCutRod(100));
//        System.out.println("Execute count: " + cr.count);

        // 动态规划：自底向上
//        System.out.println("Optimumimum value: " + cr.bottomUpCutRod(10));
//        System.out.println("Execute count: " + cr.count);

        // 动态规划：自底向上（记录最优切割方案）
        System.out.println("Optimum value: " + cr.extendBottomUpCutRod(9));
        System.out.println("Execute count: " + cr.count);
        cr.printCutRodSolution(9, cr.s);
    }
}
