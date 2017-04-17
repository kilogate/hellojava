package com.kilogate.hello.java.javase.other.configuration.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 * 首选项（存储在本地）
 *
 * @author kilogate
 * @create 2017/3/29 19:25
 **/
public class PreferencesUsage {
    public static void main(String[] args) throws BackingStoreException, IOException, InvalidPreferencesFormatException, ClassNotFoundException {
        // 系统首选项根节点
        Preferences systemRoot = Preferences.systemRoot();
        // 用户首选项根节点
        Preferences userRoot = Preferences.userRoot();

        Preferences systemNode = systemRoot.node("com.kilogate.hello.java.javase.other.configuration.preferences.PreferencesUsage");
        Preferences userNode = userRoot.node("com.kilogate.hello.java.javase.other.configuration.preferences.PreferencesUsage");

        Class<?> clazz = Class.forName("com.kilogate.hello.java.javase.other.configuration.preferences.PreferencesUsage");
        Preferences userNodeForPackage = Preferences.userNodeForPackage(clazz);
        Preferences systemNodeForPackage = Preferences.systemNodeForPackage(clazz);

        // 列出节点中全部的键
        String[] systemRootKeys = systemRoot.keys();
        String[] userRootKeys = userRoot.keys();

        // 根据键取值
        String userId = userRoot.get("JetBrains.UserIdOnMachine", "default");
//        userRoot.put("test", "TEST");
        String test = userRoot.get("test", "default");
        String tttt = userRoot.get("tttt", "default");

        // 将子树的全部值输出
        systemRoot.exportSubtree(new FileOutputStream(new File("SubTrees.preferences")));
        // 将节点的全部值输出
        systemRoot.exportNode(new FileOutputStream(new File("Nodes.preferences")));

        // 读取中心知识库数据
        systemNode.importPreferences(new FileInputStream(new File("SubTrees.preferences")));
        userNode.importPreferences(new FileInputStream(new File("Nodes.preferences")));
    }
}
