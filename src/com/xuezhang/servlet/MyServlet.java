package com.xuezhang.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 13:05
 */
public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("服务端MyServlet接收到请求");
//        servletResponse.setContentType("text/html;charset=UTF-8");

//        String str = servletRequest.getParameter("str");

        String str = (String) servletRequest.getAttribute("str");

        servletResponse.getWriter().write(str);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
