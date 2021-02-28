package com.xuezhang.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 13:23
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        String password = req.getParameter("password");

        if (username.equals("admin") && password.equals("123456")){
            HttpSession httpSession = req.getSession();

            httpSession.setAttribute("username", username);

            resp.sendRedirect("download.jsp");
        }
    }
}
