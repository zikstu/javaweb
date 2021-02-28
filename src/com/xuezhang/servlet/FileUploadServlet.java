package com.xuezhang.servlet;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 15:26
 */
@WebServlet("/fileUpload")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);

            for (FileItem fileItem: fileItemList){
                if (fileItem.isFormField()){
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name + ":" + value);
                }else {
                    String fileName = fileItem.getName();

                    long size = fileItem.getSize();

                    System.out.println(fileName + ":" + size + "Byte");

                    InputStream inputStream = fileItem.getInputStream();

                    String path = req.getServletContext().getRealPath("file/" + fileName);

                    OutputStream outputStream = new FileOutputStream(path);

                    int temp = 0;

                    while ((temp = inputStream.read()) != -1){
                        outputStream.write(temp);
                    }

                    outputStream.close();
                    inputStream.close();
                    System.out.println("上传成功！");
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
