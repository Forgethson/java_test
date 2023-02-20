package com.wjd.servlets;

import com.wjd.fruit.dao.FruitDAO;
import com.wjd.fruit.dao.impl.FruitDAOImpl;
import com.wjd.fruit.pojo.Fruit;
import com.wjd.myssm.myspringmvc.ViewBaseServlet;
import com.wjd.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 渲染并跳转到add.html页面
        super.processTemplate("add", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price"));
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");

        fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));

        response.sendRedirect("index");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
