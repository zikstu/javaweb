package com.xuezhang.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 22:04
 */
@WebServlet("/hello")
public class HelloServlet implements Servlet {
    public HelloServlet(){
        System.out.println("创建了Servlet 对象");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet 初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("执行了Servlet 业务方法");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("你好, Servlet!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("释放了Servlet 对象");
    }
}
