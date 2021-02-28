package com.xuezhang.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @description:
 * @author: 学长
 * @date: 2021/2/28 12:43
 */
@WebFilter("/myservlet")
public class MyFilter implements Filter {
    public MyFilter() {
        System.out.println("MyFilter通过无参构造创建对象。。。");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter执行业务方法。。。");
        servletResponse.setContentType("text/html;charset=UTF-8");

        String str = servletRequest.getParameter("str");

        String replaceAll = str.replaceAll("垃圾", "***");

        servletRequest.setAttribute("str", replaceAll);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter释放对象资源。。。");
    }
}
