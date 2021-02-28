package com.xuezhang.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/27 22:44
 */
public class TestServlet implements Servlet {
    public TestServlet(){
        System.out.println("创建TestServlet对象");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化TestServlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("执行TestServlet 业务方法");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("释放执行TestServlet对象");
    }
}
