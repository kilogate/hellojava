package com.kilogate.hello.java.javase.core.generic;

import com.kilogate.hello.java.javase.other.Father;
import com.kilogate.hello.java.javase.other.Son;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * 泛型用法
 *
 * @author fengquanwei
 * @create 2017/6/16 17:07
 **/
public class GenericUsage {
    public static void main(String[] args) {
        // 测试桥方法
//        DatePair datePair = new DatePair(new Date(), new Date());
//        Pair<Date> pair = datePair;
//        pair.setSecond(new Date());
        // 上述 setSecond 方法执行顺序
        // 1. DatePair.setSecond(Object second)
        // 2. DatePair.setSecond(Date second)
        // 3. Pair.setSecond(Object second)

        // 实例化类型参数
//        Pair<String> stringPair = makePair(String.class);
//        // 构造泛型数组（最好不要使用泛型数组，而是使用集合持有泛型类型）
//        String[] pairs = makePairArray("A", "B", "C", "D", "E");

        // 消除对已检查异常的检查
        new Block() {
            @Override
            public void body() throws Exception {
                Scanner in = new Scanner(new File("not exist"));
                while (in.hasNext()) {
                    System.out.println(in.next());
                }
            }
        }.toThread().start();

        // 通配符的子类型限定
        Pair<Son> sonPair = new Pair<>(new Son(), new Son());
        Pair<? extends Father> subPair = sonPair;
        Father first = subPair.getFirst(); // 安全的访问器方法
//        subPair.setFirst(new Father()); // 不安全的更改器方法

        // 无限定通配符
        Pair<?> wildcardPair = new Pair<>(null, null);
        wildcardPair.setFirst(null); // 无法设置 Object，只能设置 null
        Object wildcardPairFirst = wildcardPair.getFirst(); // 只能返回 Object
        // 原始类型
        Pair rawPari = new Pair(null, null);
        rawPari.setFirst(new Object());
        Object rawPariFirst = rawPari.getFirst();
    }

    // 使用泛型类 Pair<T>
    public static Pair<String> minmax(String... strings) {
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

    // 泛型方法
    public static <T> T getMiddle(T... ts) {
        if (ts == null || ts.length == 0) {
            return null;
        }
        return ts[ts.length / 2];
    }

    // 类型参数限定
    public static <T extends Comparable> Pair<T> minmax(T[] ts) {
        if (ts == null || ts.length == 0) {
            return null;
        }
        T min = ts[0];
        T max = ts[0];
        for (T t : ts) {
            if (min.compareTo(t) > 0) {
                min = t;
            }

            if (max.compareTo(t) < 0) {
                max = t;
            }
        }
        return new Pair<T>(min, max);
    }

    // 实例化类型参数
    public static <T> Pair<T> makePair(Class<T> clazz) {
        try {
            // 不能直接实例化参数类型，new T() 是错误的，T.class 也是错误的，要如下使用泛型类 Class
            return new Pair<T>(clazz.newInstance(), clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 构造泛型数组
    public static <T> T[] makePairArray(T... ts) {
        Object o = Array.newInstance(String.class, 3);
        T[] result = (T[]) Array.newInstance(ts.getClass().getComponentType(), ts.length);
        for (int i = 0; i < ts.length; i++) {
            result[i] = ts[i];
        }
        return result;
    }

    // 通配符的超类型限定
    public static <T extends Comparable<? super T>> T min(T[] ts) {
        // int compareTo(? super T)
        if (ts == null || ts.length == 0) {
            return null;
        }
        T result = ts[0];
        for (T t : ts) {
            if (result.compareTo(t) < 0) {
                result = t;
            }
        }
        return result;
    }
}

