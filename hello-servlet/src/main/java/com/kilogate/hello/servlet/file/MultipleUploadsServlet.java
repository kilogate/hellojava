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
import java.util.Collection;

/**
 * 多文件上传
 *
 * @author fengquanwei
 * @create 2017/11/13 23:33
 **/
@WebServlet("/multipleUploads")
@MultipartConfig
public class MultipleUploadsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();

        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getContentType() != null) {
                String fileName = UploadUtil.getFilename(part);
                if (fileName != null) {
                    String path = getServletContext().getRealPath("/WEB-INF") + "/" + fileName;
                    part.write(path);
                    writer.print("<br/>Uploaded file name: " + fileName);
                    writer.print("<br/>Size: " + part.getSize());
                }
            } else {
                String partName = part.getName();
                writer.print("<br/>" + partName + ": " + request.getParameter(partName));
            }
        }
    }
}
