package com.wjd.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/9
 * 将创建session，并访问其uname属性
 */
public class test01Servlet extends HttpServlet {

    public test01Servlet() {
        System.out.println("HelloServlet 正在实例化...");
    }

    public void init() {
        System.out.println("HelloServlet 正在初始化...");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("HelloServlet 正在服务...");
        //获取session,如果获取不到，则创建一个新的
        HttpSession session = request.getSession();
        System.out.println("session ID : " + session.getId());
        System.out.println("创建时间：" + session.getCreationTime());
        System.out.println(request.getSession().getAttribute("uname"));

        // 服务器端内部转发
//        request.getRequestDispatcher("test02").forward(request,response);
        // 客户端重定向
        response.sendRedirect("test02");
        System.out.println("HelloServlet 服务结束");
    }

    public void destroy() {
        System.out.println("HelloServlet 正在销毁...");
    }


}