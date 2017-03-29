package com.kilogate.hello.algorithm.dynamicProgramming;

/**
 * Created by fengquanwei on 2016/9/18.
 * 矩阵链乘法问题
 */
public class MatrixChainMultiply {
    private static int count = 0;

    // 自底向上表格法：m[i,j] = m[i,k] + m[k+1,j] + P(i-1)PkPj
    private static String name = "ABCDEF"; // 矩阵名称
    private static int[] P = {1, 2, 3, 4, 5, 6, 7}; // 矩阵链规模
    private static int length = P.length - 1; // 矩阵数量
    private static int[][] m = new int[length][length]; // 最优值
    private static int[][] s = new int[length][length]; // 最优解
    private int matrixChainOrder(int[] P, int[][] m, int[][] s) {
        int j = 0;
        int min = 0; // 最优值
        int temp = 0;

        for (int len = 2; len < P.length; len++) { // 矩阵链长度
            for (int i = 0; i < P.length - len; i++) {
                j = i + len - 1;

                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) { // 括号位置
                    temp = m[i][k] + m[k + 1][j] + P[i] * P[k + 1] * P[j + 1];

                    if (temp < m[i][j]) {
                        min = temp;
                        m[i][j] = temp;
                        s[i][j] = k;
                    }
                }
            }
        }

        // 打印最优值列表
        System.out.println();
        System.out.println("Optimum value list");
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < m.length; i++) {
            for (int jj = i; jj < m[i].length; jj++) {
                System.out.print("m[" + i + "][" + jj + "]: " + m[i][jj] + "\t");
            }
            System.out.println();
        }

        // 打印最优解列表
        System.out.println();
        System.out.println("Optimum plan list");
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < s.length; i++) {
            for (int jj = i; jj < s[i].length; jj++) {
                System.out.print("s[" + i + "][" + jj + "]: " + s[i][jj] + "\t");
            }
            System.out.println();
        }

        System.out.println();
        return min;
    }

    // 打印完全括号化方案
    public static void Display(int[][] s, String name, int i, int j) {
        if (i == j) {
            System.out.print(name.charAt(i));
        } else {
            System.out.print("(");
            Display(s, name, i, s[i][j]);
            Display(s, name, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    // 矩阵乘法：A[m][p] * B[p][n] = C[m][n]
    // 标量乘法的次数 count = m * p * n
    private int[][] matrixMultiply(int[][] A, int[][] B) {
        if (A == null || B == null) {
            System.out.println("Errors: Matrix can't be null");
            return null;
        }

        if (A[0].length != B.length) {
            System.out.println("Errors: A.columns != B.rows");
            return null;
        }

        int m = A.length;
        int p = B.length;
        int n = B[0].length;

        int[][] C = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = 0;
                for (int k = 0; k < p; k++) {
                    count++;
                    temp += A[i][k] * B[k][j];
                }
                C[i][j] = temp;
            }
        }

        return C;
    }

    // 格式化打印矩阵
    private void printMatrix(int[][] A) {
        if (A != null) {
            for (int i = 0, length = A.length; i < length; i++) {
                for (int j = 0, len = A[i].length; j < len; j++) {
                    System.out.print(A[i][j] + "\t");
                }
                System.out.println();
            }
        } else {
            System.out.println("NULL");
        }
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{5, 2, 4}, {3, 8, 2}, {6, 0, 4}, {0, 1, 6}};
        int[][] B = new int[][]{{2, 4}, {1, 3}, {3, 2}};
        MatrixChainMultiply mcm = new MatrixChainMultiply();

        // 矩阵乘法
        System.out.println("=====================A=====================");
        mcm.printMatrix(A);
        System.out.println("=====================B=====================");
        mcm.printMatrix(B);
        System.out.println("===================A * B===================");
        mcm.printMatrix(mcm.matrixMultiply(A, B));
        System.out.println();
        System.out.println("Execute count: " + mcm.count);

        // 自底向上表格法
//        System.out.println("The least computation times: "+ mcm.matrixChainOrder(P, m, s));
//        System.out.print("Fully parenthesized plan: ");
//        Display(s, name, 0, length - 1);
//        System.out.println();
    }
}
