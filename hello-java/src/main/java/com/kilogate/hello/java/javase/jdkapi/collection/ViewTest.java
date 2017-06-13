package com.kilogate.hello.java.javase.jdkapi.collection;

import java.util.*;

/**
 * 视图测试
 *
 * @author fengquanwei
 * @create 2017/4/30 18:17
 **/
public class ViewTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>() {{ add("A"); add("A"); add("A"); }};

        // 1. 包装器视图
        List<String> stringList = Arrays.asList("A", "B", "C");
        Set<String> stringSet = Collections.singleton("A");
        List<String> singletonList = Collections.singletonList("A");
        Map<String, String> singletonMap = Collections.singletonMap("A", "A");

        // 2. 子范围视图
        List<String> subList = list.subList(0, 2);
        subList.clear();

        // 3. 不可修改视图
        List<String> unmodifiableList = Collections.unmodifiableList(list);

        // 4. 同步视图
        List<String> synchronizedList = Collections.synchronizedList(list);

        // 5. 检查视图
        List rawList = list;
        rawList.add(new Date()); // 不报错，get 的时候才转型异常
        List checkedList = Collections.checkedList(list, String.class);
        checkedList.add(new Date());  // 报错，转型异常

        System.out.println(list);

    }
}
