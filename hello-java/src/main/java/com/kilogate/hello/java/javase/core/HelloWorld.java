package com.kilogate.hello.java.javase.core;

import java.util.Arrays;
import java.util.Objects;

/**
 * 类型与对象
 *
 * @author fengquanwei
 * @create 2017/6/13 17:19
 **/
public class HelloWorld implements Cloneable {
    private String name;
    private int age;
    private String[] hobbys;

    /**
     * 构造函数
     */
    public HelloWorld(String name, int age, String[] hobbys) {
        this.name = name;
        this.age = age;
        this.hobbys = hobbys;
    }

    /**
     * 重写 equals 方法
     */
    @Override
    public boolean equals(Object other) {
        // 一、是否引用同一个对象
        if (this == other) {
            return true;
        }

        // 二、检测 other 是否为 null
        if (other == null) {
            return false;
        }


        // 三、是否同一种类型（子类语义不一致）
        if (this.getClass() != other.getClass()) {
            return false;
        }
//        // 三、是否同一种类型（子类语义一致）
//        if (!(other instanceof HelloWorld)) {
//            return false;
//        }

        // 四、转换类型
        HelloWorld that = (HelloWorld) other;

        // 五、对比需要对比的域
        if (this.age != that.age) { // 比较基本类型域
            return false;
        }
        if (!Objects.equals(this.name, that.name)) { // 比较引用类型域
            return false;
        }
        if (!Arrays.equals(this.hobbys, that.hobbys)) { // 比较数组类型域
            return false;
        }

        // 六、如果在子类中重写还需要调用父类的 equals 方法
//        boolean equals = super.equals(other);

        return true;
    }

    /**
     * 重写 hashCode 方法
     */
    @Override
    public int hashCode() {
        int result = Objects.hashCode(this.name);
        result = 31 * result + this.age;
        result = 31 * result + Arrays.hashCode(this.hobbys);
        return result;
    }

    /**
     * 重写 toString 方法
     */
    @Override
    public String toString() {
        return "HelloWorld{" +
                "name='" + this.name + '\'' +
                ", age=" + this.age +
                ", hobbys=" + Arrays.toString(this.hobbys) +
                '}';
    }

    /**
     * 在静态方法中获取类型信息
     */
    public static String getClassName() {
        return new Object() {
        }.getClass().getEnclosingClass().getName();
    }

    /**
     * 重写克隆方法，并扩展访问权限为 public，因为 Object 的 clone 方法时 protected 的，无法让其他类访问
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        // 数组都带有 clone 方法
        HelloWorld helloWorld = new HelloWorld("Lask", 25, new String[]{"soccer", "game"});
        HelloWorld[] helloWorlds = new HelloWorld[]{helloWorld};

        HelloWorld[] helloWorldsClone = helloWorlds.clone();
        helloWorldsClone[0].setName("Lynn");

        System.out.println("helloWorld: " + helloWorld);
        System.out.println("helloWorlds: " + Arrays.toString(helloWorlds));
        System.out.println("helloWorldsClone: " + Arrays.toString(helloWorldsClone));

        // 克隆对象
        try {
            HelloWorld helloWorldClone = (HelloWorld) helloWorld.clone();
            System.out.println("helloWorldClone: " + helloWorldClone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        // 类型信息
        System.out.println(Integer.class.getName());
        System.out.println(Integer[].class.getName());
    }

    // ============================== getters and setters ==============================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHobbys() {
        return hobbys;
    }

    public void setHobbys(String[] hobbys) {
        this.hobbys = hobbys;
    }
}
