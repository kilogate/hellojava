package com.kilogate.hello.java.javase.core.generic;

/**
 * 测试泛型方法
 *
 * @author fengquanwei
 * @create 2017/4/24 11:38
 **/
public class GenericMethodTest {
    /**
     * 泛型方法
     */
    public static <T> T getFirst(T... ts) {
        if (ts != null && ts.length > 0) {
            return ts[0];
        } else {
            return null;
        }
    }

    /**
     * 限定类型变量
     */
    public static <T extends Comparable> T getMin(T... ts) {
        if (ts != null && ts.length > 0) {
            T min = ts[0];
            for (T t : ts) {
                if (min.compareTo(t) > 0) {
                    min = t;
                }
            }
            return min;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        String first = GenericMethodTest.getFirst("A", "B", "C");
        System.out.println(first);

        Integer min = GenericMethodTest.getMin(1, 2, 3, 4);
        System.out.println(min);
    }
}
