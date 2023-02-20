package com.wjd.servlets;

import com.wjd.fruit.dao.FruitDAO;
import com.wjd.fruit.dao.impl.FruitDAOImpl;
import com.wjd.myssm.myspringmvc.ViewBaseServlet;
import com.wjd.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            boolean flag = fruitDAO.delFruit(fid);
            System.out.println(flag ? "删除成功" : "删除失败");

            //super.processTemplate("index",request,response);
            response.sendRedirect("index");
        }
    }
}
