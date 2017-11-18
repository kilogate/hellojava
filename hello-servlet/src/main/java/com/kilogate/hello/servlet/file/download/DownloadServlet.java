package com.kilogate.hello.servlet.file.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *  通过编程发送一个 pdf 文件资源到浏览器
 *
 * @author fengquanwei
 * @create 2017/11/18 16:05
 **/
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/data");
        File file = new File(dataDirectory, "demo.pdf");
        if (file.exists()) {
//            response.setContentType("application/pdf"); // 下载 PDF 文件
            response.setContentType("application/octet-stream"); // 另存为
//            response.addHeader("Content-Disposition", "attachment; filename=demo.pdf");
            response.addHeader("Content-Disposition", "attachment; filename=abc.txt");

            byte[] buffer = new byte[1024];
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                ServletOutputStream outputStream = response.getOutputStream();
                int read = inputStream.read(buffer);
                while (read != -1) {
                    outputStream.write(buffer, 0, read);
                    read = inputStream.read(buffer);
                }
            }
        }


    }
}
