package com.kilogate.hello.tomcat.connector2.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数 Map
 *
 * @author fengquanwei
 * @create 2017/8/31 17:48
 **/
public class ParameterMap extends HashMap {
    // ------------------------------ 变量 ------------------------------
    private static final StringManager stringManager = StringManager.getManager("com.kilogate.hello.tomcat.connector2.util");

    // ------------------------------ 属性 ------------------------------
    private boolean locked = false;

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    // ------------------------------ 构造函数 ------------------------------

    public ParameterMap() {
        super();
    }

    public ParameterMap(Map map) {
        super(map);
    }

    public ParameterMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ParameterMap(int initialCapacity) {
        super(initialCapacity);
    }

    // ------------------------------ 公共方法 ------------------------------

    public void clear() {
        if (locked) {
            throw new IllegalStateException(stringManager.getString("parameterMap.locked"));
        }

        super.clear();
    }

    public Object put(Object key, Object value) {
        if (locked) {
            throw new IllegalStateException(stringManager.getString("parameterMap.locked"));
        }

        return super.put(key, value);
    }

    public void putAll(Map map) {
        if (locked) {
            throw new IllegalStateException(stringManager.getString("parameterMap.locked"));
        }

        super.putAll(map);
    }

    public Object remove(Object key) {
        if (locked) {
            throw new IllegalStateException(stringManager.getString("parameterMap.locked"));
        }

        return super.remove(key);
    }
}
