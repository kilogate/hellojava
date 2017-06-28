package com.kilogate.hello.java.javase.jdkapi.io.stream;

import java.io.*;

/**
 * 使用序列化克隆对象
 * 深克隆，但是没有新建对象复制域值快
 *
 * @author fengquanwei
 * @create 2017/6/25 20:14
 **/
public class SerialCloneable implements Cloneable, Serializable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteArrayOut);
            out.writeObject(this);
            out.close();

            ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteArrayIn);
            Object ret = in.readObject();
            in.close();

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
