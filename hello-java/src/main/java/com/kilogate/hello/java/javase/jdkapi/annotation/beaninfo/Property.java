package com.kilogate.hello.java.javase.jdkapi.annotation.beaninfo;

import java.lang.annotation.*;

/**
 * 属性注解接口
 *
 * @author fengquanwei
 * @create 2017/8/8 17:02
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Property {
    String editor() default "";
}
