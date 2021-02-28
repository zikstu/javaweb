package com.xuezhang.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 23:33
 */
@WebServlet("/testHttpServlet")
public class TestHttpServlet extends MyHttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("TestHttpServlet-> GET");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.getWriter().write("TestHttpServlet-> POST");
    }
}
