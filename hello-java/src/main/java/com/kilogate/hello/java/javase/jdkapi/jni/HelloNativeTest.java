package com.kilogate.hello.java.javase.jdkapi.jni;

/**
 * 测试本地方法
 *
 * @author fengquanwei
 * @create 2017/8/11 10:50
 **/
public class HelloNativeTest {
    static {
        System.loadLibrary("hellonative");
    }

    /**
     * vim HelloNative.java
     * cd /Users/kilogate/IdeaProjects/hello/hello-java/src/main/java
     * javac com/kilogate/hello/java/javase/jdkapi/jni/HelloNative.java
     * javah com.kilogate.hello.java.javase.jdkapi.jni.HelloNative
     * mv com_kilogate_hello_java_javase_jdkapi_jni_HelloNative.h com/kilogate/hello/java/javase/jdkapi/jni/
     * vim HelloNative.c
     * gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/include/ com/kilogate/hello/java/javase/jdkapi/jni/HelloNative.c -o libhellonative.jnilib
     * mv libhellonative.jnilib com/kilogate/hello/java/javase/jdkapi/jni/
     * vim HelloNativeTest.java
     * javac com/kilogate/hello/java/javase/jdkapi/jni/HelloNativeTest.java
     * java -Djava.library.path=com/kilogate/hello/java/javase/jdkapi/jni/ com.kilogate.hello.java.javase.jdkapi.jni.HelloNativeTest
     */
    public static void main(String[] args) {
        HelloNative.greeting();
    }
}
