package com.kilogate.hello.tomcat.catalina3.util;

import com.kilogate.hello.tomcat.catalina3.StringManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数 Map
 *
 * @author fengquanwei
 * @create 2017/8/31 17:48
 **/
public class ParameterMap extends HashMap {
    private static final StringManager sm = StringManager.getManager("com.kilogate.hello.tomcat.catalina3.util");
    private boolean locked = false;

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

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void clear() {
        if (locked) {
//            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }

        super.clear();
    }

    public Object put(Object key, Object value) {
        if (locked) {
//            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }

        return super.put(key, value);
    }

    public void putAll(Map map) {
        if (locked) {
//            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }

        super.putAll(map);
    }

    public Object remove(Object key) {
        if (locked) {
//            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }

        return super.remove(key);
    }
}
