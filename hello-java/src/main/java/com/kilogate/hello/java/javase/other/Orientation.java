package com.kilogate.hello.java.javase.other;

import java.io.Serializable;

/**
 * 类型安全的枚举
 *
 * @author fengquanwei
 * @create 2017/6/25 19:39
 **/
public class Orientation implements Serializable {
    public static final Orientation HORIZONTAL = new Orientation(1);
    public static final Orientation VERTICAL = new Orientation(2);

    private int value;

    private Orientation(int value) {
        this.value = value;
    }

    // 解决唯一对象的序列化和反序列化问题
    protected Object readResolve() {
        if (value == 1) {
            return Orientation.HORIZONTAL;
        }
        if (value == 2) {
            return Orientation.VERTICAL;
        }
        return null;
    }
}
