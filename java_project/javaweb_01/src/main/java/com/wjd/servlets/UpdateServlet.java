package com.wjd.servlets;

import com.wjd.fruit.dao.FruitDAO;
import com.wjd.fruit.dao.impl.FruitDAOImpl;
import com.wjd.fruit.pojo.Fruit;
import com.wjd.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname, price ,fcount ,remark ));

        //4.服务器内部资源跳转（下面个语句效果是一样的）
        // super.processTemplate("index",request,response);
        // request.getRequestDispatcher("index.html").forward(request,response);

        // 但是，此处不能使用服务器内部跳转，而是需要重定向，目的是重新给IndexServlet发请求，
        // 重新获取furitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        // 否则由于session没有变化，显示的数据还是旧的数据
        response.sendRedirect("index");
    }
}

// java.lang.NumberFormatException: For input string: ""