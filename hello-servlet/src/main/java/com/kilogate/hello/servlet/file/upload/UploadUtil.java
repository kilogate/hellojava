package com.kilogate.hello.servlet.file.upload;

import javax.servlet.http.Part;

/**
 * 文件上传工具类
 *
 * @author fengquanwei
 * @create 2017/11/16 00:05
 **/
public class UploadUtil {
    public static String getFilename(Part part) {
        if (part != null) {
            String header = part.getHeader("content-disposition");
            if (header != null) {
                String[] elements = header.split(";");
                for (String element : elements) {
                    if (element.trim().startsWith("filename")) {
                        return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
                    }
                }
            }
        }

        return null;
    }
}
