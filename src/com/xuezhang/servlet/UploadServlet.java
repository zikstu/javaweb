package com.xuezhang.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 14:01
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 字节流
        ServletInputStream stream = req.getInputStream();

        int temp = 0;

//        while ((temp = stream.read()) != -1){
//            System.out.println(temp);
//        }

        //字符流
        Reader reader = new InputStreamReader(stream);

        //缓冲字符流
        BufferedReader bufferedReader = new BufferedReader(reader);

        //获取保存文件的path
        String realPath = req.getServletContext().getRealPath("file/copy.txt");

        // 输出到本地 获取输出流
        OutputStream outputStream = new FileOutputStream(realPath);

        //输入字节流
        Writer writer = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        String str = "";
        while ((str = bufferedReader.readLine()) != null){
            bufferedWriter.write(str);
        }

        // 不要忘记关闭流
        bufferedWriter.close();
        writer.close();
        outputStream.close();
        bufferedReader.close();
        reader.close();
        stream.close();
    }
}
