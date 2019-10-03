package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 不能知道那个Controller 里的那个方法
 */
//@Component
public class TimeFilter implements Filter {
    /**
     * 初始化
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    /**
     * 过滤
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("time filter start!");
        long start = new Date().getTime();
        chain.doFilter(request, response);
        System.out.println("time filter:" + (new Date().getTime() - start));
        System.out.println("time filter end!");
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        System.out.println("time filter destory!");
    }
}
