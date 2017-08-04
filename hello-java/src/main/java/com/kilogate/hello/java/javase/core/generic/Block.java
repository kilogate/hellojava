package com.kilogate.hello.java.javase.core.generic;

/**
 * 消除对已检查异常的检查
 */
public abstract class Block {
    public abstract void body() throws Exception;

    public Thread toThread() {
        return new Thread() {
            @Override
            public void run() {
                try {
                    body();
                } catch (Throwable e) {
                    Block.<RuntimeException>throwAs(e);
                }
            }
        };
    }

    public static <T extends Throwable> void throwAs(Throwable e) throws T {
        throw (T) e;
    }
}
