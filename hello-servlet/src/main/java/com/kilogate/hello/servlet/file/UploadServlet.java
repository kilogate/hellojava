package com.kilogate.hello.servlet.file;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 文件上传
 *
 * @author fengquanwei
 * @create 2017/11/13 23:33
 **/
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private String getFilename(Part part) {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("uploadFile");
        String fileName = getFilename(part);
        if (fileName != null) {
            String path = getServletContext().getRealPath("/WEB-INF") + "/" + fileName;
            part.write(path);
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print("<br/>Uploaded file name: " + fileName);
        writer.print("<br/>Size: " + part.getSize());
        writer.print("<br/>Author: " + request.getParameter("author"));
    }
}
