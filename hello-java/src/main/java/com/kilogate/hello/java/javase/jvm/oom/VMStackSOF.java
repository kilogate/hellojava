package com.kilogate.hello.java.javase.jvm.oom;

/**
 * 虚拟机栈栈溢出
 * VM Args: -Xss128k
 *
 * @author fengquanwei
 * @create 2017/8/27 22:49
 **/
public class VMStackSOF {
    private int stackLength = -1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        VMStackSOF vmStackSOF = new VMStackSOF();

        try {
            vmStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + vmStackSOF.stackLength);
            throw e;
        }
    }
}
