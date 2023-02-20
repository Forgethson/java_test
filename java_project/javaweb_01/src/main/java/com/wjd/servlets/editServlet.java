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

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/12
 */
@WebServlet("/edit.do")
public class editServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);

            // 根据fid从数据库获得相应的fruit对象
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            // 将fruit对象设置为request属性
            req.setAttribute("fruit", fruit);

            // 然后将fruit传入到edit里面
            // 自己写的函数，就是相当于服务器，渲染并内部跳转到/edit.html
            super.processTemplate("edit", req, resp);
        }
    }
}
