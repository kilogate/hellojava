package com.kilogate.hello.java.javase.core.innerclass;

import java.util.ArrayList;

/**
 * 外部类
 *
 * @author fengquanwei
 * @create 2017/8/4 20:13
 **/
public class Outer {
    private String outerName;

    public Outer(String outerName) {
        this.outerName = outerName;
    }

    // ============================== 内部类 ==============================
    // 获取内部类的名字
    public String getInnerName() {
        // 创建内部类
//        Inner inner = new Inner("inner");
        Inner inner = this.new Inner("inner"); // 正规语法
        return inner.getName();
    }

    // 内部类：定义在外部类之中
    class Inner {
        private String innerName;

        public Inner(String innerName) {
            this.innerName = innerName;
        }

        public String getName() {
            // 内部类可以访问外部类的私有域
//            return outerName + "." + innerName; // 直接访问
            return Outer.this.outerName + "." + this.innerName; // 正规语法
        }
    }

    // ============================== 局部内部类 ==============================
    // 如果方法退出，参数局部变量将消失，局部内部类中的方法将无法访问到参数局部变量，所以必须在局部内部类中保存访问到的参数局部变量且不能修改，故为 final，本方法暂时不能说明此问题
    public String getLocalName(final String name) { // jdk1.8 可以不用加 final 了，像这样：String name
        // 局部内部类
        class Local {
            private String localName;

            public Local(String localName) {
                this.localName = localName;
            }

            public String getName() {
                return name + "." + localName;
            }
        }

        Local local = new Local("local");
        return local.getName();
    }

    // ============================== 匿名内部类 ==============================
    public void testAnonymousClass() {
        // 一、匿名内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };

        // 二、双括号初始化
        System.out.println(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});
    }

    // ============================== 静态内部类 ==============================
    public static class Pair {
        private int min;
        private int max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

    public static Pair minmax(int[] ints) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i : ints) {
            if (min > i) min = i;
            if (max < i) max = i;
        }

        return new Pair(min, max);
    }
}
