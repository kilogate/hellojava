package com.kilogate.hello.java.javase.jdkapi.annotation.beaninfo;

/**
 * 使用 Property 注解的类
 *
 * @author fengquanwei
 * @create 2017/8/8 17:42
 **/
public class ChartBean {
    private int titlePosition;

    @Property(editor = "String")
    public void setTitlePosition(int p) {
        this.titlePosition = p;
    }
}
