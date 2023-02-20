package com.wjd.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"*.do","*.html"},
        initParams = {
            @WebInitParam(name = "allowed",
                    value = "/forgethson/page.do?operate=page&page=user/login,/forgethson/user.do?null")
        })
public class SessionFilter implements Filter {

    List<String> baiList = null ;

    @Override
    public void init(FilterConfig config) throws ServletException {
        // 得到输入的参数
        String bai = config.getInitParameter("allowed");
        // 将输入的参数按逗号分割开
        String[] baiArr = bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest ;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // http://localhost:8080/forgethson/page.do?operate=page&page=user/login
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());

        String uri = request.getRequestURI() ;
        String queryString = request.getQueryString() ;
        String str = uri + "?" + queryString ;

        // 如果要访问的uri在白名单里面，就放行
        if(baiList.contains(str)){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession() ;
            Object currUserObj = session.getAttribute("currUser");

            if(currUserObj==null){
                // 如果当前的用户不存在，跳转到登陆界面
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                // 如果存在则放行
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
