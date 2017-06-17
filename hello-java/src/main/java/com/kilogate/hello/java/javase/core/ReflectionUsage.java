package com.kilogate.hello.java.javase.core;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 反射用法
 *
 * @author fengquanwei
 * @create 2017/4/25 17:02
 **/
public class ReflectionUsage {
    public static void main(String[] args) {
        // 一、通用 toString 测试
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(new com.kilogate.hello.java.javase.core.ObjectAnalyzer().toString(list));

        // 二、通用数组拷贝测试
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        int[] newIntArr = (int[]) new com.kilogate.hello.java.javase.core.ObjectAnalyzer().copyOf(intArr, 10);
        System.out.println(Arrays.toString(newIntArr));

        // 三、打印类信息
        System.out.println("classpath: " + ReflectionUsage.class.getResource("/"));

        while (true) {
            System.out.println();
            System.out.println("Enter class name (e.g. java.util.Collections): ");
            Scanner in = new Scanner(System.in);
            String name = in.next();

            if (name.equals("exit")) {
                System.exit(0);
            }

            try {
                Class<?> clazz = Class.forName(name);
                printClass(clazz);
                for (Method method : clazz.getDeclaredMethods()) {
                    printMethod(method);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printClass(Class<?> clazz) {
        System.out.print(clazz);
        printTypes(clazz.getTypeParameters(), "<", ", ", ">", true);
        Type sc = clazz.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }
        printTypes(clazz.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    public static void printMethod(Method method) {
        String name = method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        printTypes(method.getTypeParameters(), "<", ", ", ">", false);

        printType(method.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(method.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})) {
            return;
        }

        if (types.length > 0) {
            System.out.print(pre);
        }

        for (int i = 0; i < types.length; i++) {
            if (i > 0) {
                System.out.print(sep);
            }
            printType(types[i], isDefinition);
        }

        if (types.length > 0) {
            System.out.print(suf);
        }
    }

    public static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        } else if (type instanceof TypeVariable) {
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if (isDefinition) {
                printTypes(t.getBounds(), " extends ", "&", "", false);
            }
        } else if (type instanceof WildcardType) {
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", "&", "", false);
            printTypes(t.getLowerBounds(), " super ", "&", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }
}

/**
 * 对象分析器
 */
class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    /**
     * 通用 toString 方法
     */
    public String toString(Object object) {
        if (object == null) {
            return "null";
        }

        if (visited.contains(object)) {
            return "...";
        }

        visited.add(object);

        Class<?> clazz = object.getClass();
        if (clazz == String.class) {
            return (String) object;
        }

        if (clazz.isArray()) {
            String result = clazz.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(object); i++) {
                if (i > 0) {
                    result += ",";
                }
                Object value = Array.get(object, i);
                if (clazz.getComponentType().isPrimitive()) {
                    result += value;
                } else {
                    result += toString(value);
                }
            }
            return result + "}";
        }

        String result = clazz.getName();
        do {
            result += "[";
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!result.endsWith("[")) {
                        result += ",";
                    }
                    result += field.getName() + "=";
                    try {
                        Class<?> type = field.getType();
                        Object value = field.get(object);
                        if (type.isPrimitive()) {
                            result += value;
                        } else {
                            result += toString(value);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            result += "]";
            clazz = clazz.getSuperclass();
        } while (clazz != null);
        return result;
    }

    /**
     * 通用数组拷贝方法
     * 之所以数组形参和返回值类型设置为 Object 而不是 Object[] 是因为基本类型数组可以转换为对象而不能转换为对象数组
     */
    public Object copyOf(Object object, int newLength) {
        Class<?> clazz = object.getClass();
        if (!clazz.isArray()) {
            return null;
        }
        Class<?> componentType = clazz.getComponentType();
        int length = Array.getLength(object);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(object, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
