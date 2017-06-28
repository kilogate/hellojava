package com.kilogate.hello.java.javase.jdkapi.io.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

/**
 * Files and File
 *
 * @author fengquanwei
 * @create 2017/6/25 22:25
 **/
public class FileUsage {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("/tmp/filePath.txt");

        List<String> lines = new ArrayList<>();
        lines.add("床前明月光，");
        lines.add("疑是地上霜。");
        lines.add("举头望明月，");
        lines.add("低头思故乡。");

        // 写字符串列表到文件
        Files.write(filePath, lines, Charset.forName("UTF-8"));

        // 从文件读字符串列表
        List<String> strings = Files.readAllLines(filePath, Charset.forName("UTF-8"));
        // 从文件读字节数组
        byte[] bytes = Files.readAllBytes(filePath);

        // 复制文件
        Path copyPath = Paths.get("/tmp/copyPath.txt");
//        Files.copy(filePath, copyPath);
        Files.copy(filePath, copyPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

        // 移动文件
        Path movePath = Paths.get("/tmp/movePath.txt");
//        Files.move(copyPath, movePath);
//        Files.move(copyPath, movePath, StandardCopyOption.ATOMIC_MOVE);

        // 删除文件
//        Files.delete(movePath);
        boolean isDelete = Files.deleteIfExists(copyPath);

        // 创建文件和目录
//        Files.createDirectory(Paths.get("/tmp/newDir"));
        Files.createDirectories(Paths.get("/tmp/a/b/c/d/e/f"));
//        Files.createFile(Paths.get("/tmp/tmp.tmp"));
        Path tempFilePath = Files.createTempFile(null, ".txt");
        Path tempDirectoryPath = Files.createTempDirectory("pre");

        // 获取文件信息
        Path path = Paths.get("/tmp/filePath.txt");
        boolean exists = Files.exists(path);
        boolean hidden = Files.isHidden(path);
        boolean readable = Files.isReadable(path);
        boolean writable = Files.isWritable(path);
        boolean executable = Files.isExecutable(path);
        boolean regularFile = Files.isRegularFile(path);
        boolean directory = Files.isDirectory(path);
        boolean symbolicLink = Files.isSymbolicLink(path);
        long size = Files.size(path);
        UserPrincipal owner = Files.getOwner(path);
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
        PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);

        // 迭代目录中的文件（比 File 性能高，可以使用 glob 模式过滤文件）
        Path tmpDir = Paths.get("/Tmp");
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(tmpDir, "*.{log,txt}")) {
            for (Path p : entries) {
                System.out.println(p.getFileName());
            }
        }

        System.out.println("=============================================================================");

        // ZIP 文件系统
        Path zipPath = Paths.get("/Users/kilogate/Downloads/out.zip");
        FileSystem fileSystem = FileSystems.newFileSystem(zipPath, null);
        // 访问某个目录的所有子孙成员
        Files.walkFileTree(fileSystem.getPath("/"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("访问 " + dir + " 之前");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("访问 " + file + " 之中");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("访问 " + file + " 失败");
                return FileVisitResult.CONTINUE; // 必须覆盖，否则遇到无权限的文件立即失败
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("访问 " + dir + " 之后");
                return FileVisitResult.CONTINUE;
            }
        });

    }
}
