package com.wjd.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/9
 * 将session里面的属性 uname设置为lina
 */

public class test02Servlet2 extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("HelloServlet2 正在服务...");
        // 设置session的属性
        request.getSession().setAttribute("uname", "lina");
    }
}
