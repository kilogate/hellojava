package com.kilogate.hello.java.javase.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * <p>
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 将堆的最小值 -Xms 参数与最大值 -Xmx 参数设置为一样即可避免堆自动扩展
 * 通过参数 -XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出异常时 Dump 出当前的内存转储快照以便事后进行分析
 * <p>
 * 堆溢出处理
 * 1. 使用内存映像分析工具对 Dump 出来的堆转储快照进行分析，确定是内存泄露（Memory Leak）还是内存溢出（Memory Overflow）
 * 2. 对于内存泄露查看泄露对象到 GC Roots 的引用链，定位泄露代码位置
 * 3. 对于内存溢出检查堆参数设置，并检查代码性能问题。
 *
 * @author fengquanwei
 * @create 2017/8/27 15:44
 **/
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
