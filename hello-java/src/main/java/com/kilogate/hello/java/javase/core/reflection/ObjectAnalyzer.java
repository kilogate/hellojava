package com.kilogate.hello.java.javase.core.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 对象分析器
 */
public class ObjectAnalyzer {
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
