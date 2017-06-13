package com.kilogate.hello.java.javase.core.generic;

/**
 * 测试通配符类型
 *
 * @author fengquanwei
 * @create 2017/4/25 15:14
 **/
public class WildcardTypeTest {
    public static void main(String[] args) {
        Son son = new Son("Son");
        Father father = new Father("Father");

        Pair<? extends Father> pair = new Pair<>(son, father);
        WildcardTypeTest.printBuddies(pair);

        Pair<Son> sonPair = new Pair<>(new Son("Son1"),new Son("Son2"));
        Pair<? extends Father> wildcardPair = sonPair;
//        wildcardPair.setFirst(new Father("father1")); // 不安全的更改器方法不允许访问
        Father first = wildcardPair.getFirst(); // 安全的访问器方法允许访问
    }

    /**
     * 子类型限定通配符测试
     */
    public static void printBuddies(Pair<? extends Father> pair) {
        Father first = pair.getFirst();
        Father second = pair.getSecond();
        System.out.println(first.getName() + " and " + second.getName());
    }

    static class Father {
        private String name;

        public Father(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Son extends Father {
        public Son(String name) {
            super(name);
        }
    }

}
