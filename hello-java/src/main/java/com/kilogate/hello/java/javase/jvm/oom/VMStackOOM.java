package com.kilogate.hello.java.javase.jvm.oom;

/**
 * 虚拟机栈栈内存溢出
 * VM Args: -Xss2M
 * <p>
 * 注意！！！此程序可能导致 Windows 操作系统假死，谨慎执行！
 * <p>
 * 创建线程导致内存溢出：只能通过减少最大堆和减少栈容量来换取更多的线程
 *
 * @author fengquanwei
 * @create 2017/8/27 22:58
 **/
public class VMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        VMStackOOM vmStackOOM = new VMStackOOM();
        vmStackOOM.stackLeakByThread();
    }
}
