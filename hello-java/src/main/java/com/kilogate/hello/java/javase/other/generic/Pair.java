package com.kilogate.hello.java.javase.other.generic;

/**
 * 泛型类就是具有一个或多个类型变量的类
 *
 * @author fengquanwei
 * @create 2017/4/21 20:35
 **/
public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    // 编译器疯了
//    @Override
//    public boolean equals(T value){
//        return Objects.equals(this.first, value);
//    }
}
