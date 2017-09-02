package com.kilogate.hello.java.javase.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池内存溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 1.8 不支持以上参数，1.7 无此异常，有机会在 1.6 的环境下测试
 *
 * @author fengquanwei
 * @create 2017/9/2 17:47
 **/
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 使用 List 保持着常量池引用，避免 Full GC 回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10M 的PermSize 在 Integer 范围内足够产生 OOM 了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
