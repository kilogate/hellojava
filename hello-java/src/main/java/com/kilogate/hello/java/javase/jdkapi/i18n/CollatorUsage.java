package com.kilogate.hello.java.javase.jdkapi.i18n;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * 排序器用法
 *
 * @author fengquanwei
 * @create 2017/8/4 15:50
 **/
public class CollatorUsage {
    public static void main(String[] args) {
        List<String> strings = new ArrayList() {{
            add("aaa");
            add("BBB");
            add("ccc");
            add("DDD");
        }};

        // 默认按照 Unicode 码元大小排序
        Collections.sort(strings);
        System.out.println(strings);

        // 使用排序器排序
        Collator usCollator = Collator.getInstance(Locale.US);
        Collections.sort(strings, usCollator);
        System.out.println(strings);
    }
}
