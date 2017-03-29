package com.kilogate.hello.algorithm.dynamicProgramming;

/**
 * Created by fengquanwei on 2016/10/18.
 * 最长公共子序列问题（Longest Common Subsequence problem）
 */
public class LCS {
    private void LCSLength(char[] X, char[] Y, int[][] c, int[][] b) {
        // 若 x = 0 或 y = 0, 则 c[i, j] = 0
        // int 数组默认值为 0

        // 自底向上，先逐行，再逐列计算 c[i, j]
        for (int i = 1, rows = c.length; i < rows; i++) {
            for (int j = 1, columns = c[0].length; j < columns; j++) {
                if (X[i - 1] == Y[j - 1]) { // 若 i,j > 0 且 xi = yj, c[i, j] = c[i-1,j-1] + 1
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 0;
                } else if (c[i - 1][j] >= c[i][j - 1]) { // 若 i,j > 0 且 xi != yj 且 c[i, j-1] >= c[i-1, j]), c[i ,j] = c[i, j-1]
                    c[i][j] = c[i - 1][j];
                    b[i][j] = 1;
                } else { // 若 i,j > 0 且 xi != yj 且 c[i, j-1] < c[i-1, j]), c[i ,j] = c[i-1, j]
                    c[i][j] = c[i][j - 1];
                    b[i][j] = -1;
                }
            }
        }
    }

    // 打印LCS
    private void printLCS(int[][] b, char[] X, int i, int j, StringBuffer sb) {
        if (i == 0 || j == 0) {
            return;
        }

        if (b[i][j] == 0) {
            sb.append(String.valueOf(X[i - 1]));
            printLCS(b, X, i - 1, j - 1, sb);
        } else if (b[i][j] > 0) {
            printLCS(b, X, i - 1, j, sb);
        } else {
            printLCS(b, X, i, j - 1, sb);
        }
    }

    private void printArray(char[] X, char[] Y, int[][] c, boolean arrows) {
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                if (i == 0 && j > 0) {
                    System.out.print(Y[j - 1] + "\t");
                } else if (j == 0 && i > 0) {
                    System.out.print(X[i - 1] + "\t");
                } else {
                    if (arrows) {
                        int k = c[i][j];
                        char ch = ' ';
                        if (k == 0) {
                            ch = '↖';
                        } else if (k == 1) {
                            ch = '↑';
                        } else {
                            ch = '←';
                        }
                        System.out.print(ch + "\t");
                    } else {
                        System.out.print(c[i][j] + "\t");
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();

        char[] X = "ABCBDAB".toCharArray();
        char[] Y = "BDCABA".toCharArray();

        int m = X.length;
        int n = Y.length;

        int[][] c = new int[m + 1][n + 1];
        int[][] b = new int[m + 1][n + 1];

        lcs.LCSLength(X, Y, c, b);
        System.out.println("LCS length: " + c[m][n]);

        StringBuffer sb = new StringBuffer();
        lcs.printLCS(b, X, m, n, sb);
        System.out.println("LCS: " + sb.reverse()); // 倒序打印

        System.out.println("======================= c =======================");
        lcs.printArray(X, Y, c, false);
        System.out.println("======================= b =======================");
        lcs.printArray(X, Y, b, true);
    }

}
