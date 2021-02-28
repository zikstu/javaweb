package com.xuezhang.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 18:06
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应方式
        resp.setContentType("application/x-msdownload");

        String fileName = "1.png";

        //设置下载后的文件名
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        //获取输出流
        OutputStream outputStream = resp.getOutputStream();

        //获取要下载的文件路径
        String path = req.getServletContext().getRealPath("file/qr.png");

        InputStream inputStream = new FileInputStream(path);

        int temp = 0;

        while ((temp = inputStream.read()) != -1){
            outputStream.write(temp);
        }

        outputStream.close();
        inputStream.close();
    }
}
