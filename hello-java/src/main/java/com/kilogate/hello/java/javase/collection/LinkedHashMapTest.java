package com.kilogate.hello.java.javase.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 测试链接映射表
 *
 * @author fengquanwei
 * @create 2017/4/30 15:34
 **/
public class LinkedHashMapTest {
    public static void main(String[] args) {
        // 在 size 大于 100 时删除最近不用的元素
        Map<Integer, String> cache = new LinkedHashMap<Integer, String>(128, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 100;
            }
        };

        for (int i = 1; i <= 150; i++) {
            cache.put(i, "S" + i);
        }

        System.out.println(cache);

    }
}
