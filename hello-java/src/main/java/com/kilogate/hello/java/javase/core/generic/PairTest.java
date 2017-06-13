package com.kilogate.hello.java.javase.core.generic;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型类
 *
 * @author fengquanwei
 * @create 2017/4/21 20:51
 **/
public class PairTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // 测试 Pair 使用
        String[] strings = {"Mary", "Lily", "Lucy", "Bob", "Tom"};
        Pair<String> minmax = PairTest.minmax(strings);
        if (minmax != null) {
            System.out.println(minmax.getFirst());
            System.out.println(minmax.getSecond());
        }

        // 实例化泛型对象
        Pair<String> stringPair = PairTest.makePair(String.class);

        // 实例化泛型数组
        Pair<? extends Serializable>[] pairArray = PairTest.makePairArray("", "", "", 4);

        // 最好不要使用泛型数组，而是使用集合持有泛型类型
        List<Pair<String>> pairList = new ArrayList<>();
    }

    /**
     * 测试 Pair 使用
     */
    public static Pair<String> minmax(String[] strings) {
        if (strings == null || strings.length == 0) {
            return null;
        }

        String min = strings[0];
        String max = strings[0];

        for (String string : strings) {
            if (min.compareTo(string) > 0) {
                min = string;
            }
            if (max.compareTo(string) < 0) {
                max = string;
            }
        }

        return new Pair<>(min, max);
    }

    /**
     * 实例化泛型对象（不能 new T()，因为 T 会被擦除为 Object）
     */
    public static <T> Pair<T> makePair(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return new Pair<T>(clazz.newInstance(), clazz.newInstance());
    }

    /**
     * 实例化泛型数组（不能直接实例化泛型数组，编译器不允许【数组存储只会检查擦除的类型，有转型异常的可能】）
     */
    public static <T> Pair<T>[] makePairArray(T... ts) {
        return (Pair<T>[]) Array.newInstance(ts.getClass().getComponentType(), ts.length);
    }
}