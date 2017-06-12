package com.kilogate.hello.algorithm.dynamicProgramming;

/**
 * 字符串匹配算法
 *
 * @author fengquanwei
 * @create 2017/5/25 11:27
 **/
public class StringMatching {
    /**
     * 计算字符数组 a 与 b 的边界距离 f
     * 递推公式：
     * f(i,j) = f(i-1,j-1) 当 a[i] = b[j]
     * f(i,j) = 1 + min(f(i,j-1), f(i-1,j), f(i-1,j-1)) 当 a[i] != b[j]
     * 相似度等于边界距离 +1 的导数
     * http://www.cnblogs.com/xudong-bupt/p/3182051.html
     */
    public static void main(String[] args) {

    }
}
