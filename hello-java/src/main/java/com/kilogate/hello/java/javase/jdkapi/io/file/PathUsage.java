package com.kilogate.hello.java.javase.jdkapi.io.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Paths and Path
 *
 * @author fengquanwei
 * @create 2017/6/25 20:46
 **/
public class PathUsage {
    public static void main(String[] args) {
        // Paths and Path
        Path absolutePath = Paths.get("/Users", "kilogate");
        File file = absolutePath.toFile();
        Path relativePath = Paths.get("hello-java");
        Path toAbsolutePath = relativePath.toAbsolutePath();

        // 解析路径
        Path path = Paths.get("/Users/kilogate");
        Path downloads = path.resolve("Downloads"); // 相对路径：跟在当前路径后面
        Path documents = path.resolve("/Users/kilogate/Documents"); // 绝对路径：结果就是此绝对路径
        Path documents1 = downloads.resolveSibling("Documents");// 解析兄弟路径

        // 相对路径
        Path a = Paths.get("/home/cay");
        Path b = Paths.get("/home/fred/myprog");
        Path c = a.relativize(b);// 给定路径 b 相对于当前路径 a 的相对路径

        // 移除所有冗余的 .. 和 .
        path = Paths.get("/Users/./kilogate/../../Users/.");
        Path normalize = path.normalize();

    }
}
