package com.wjd.servlets;

import com.wjd.fruit.dao.impl.FruitDAOImpl;
import com.wjd.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/7
 */
public class OriAddServlet extends HttpServlet {

    // 响应 post 请求
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        //get方式目前不需要设置编码（基于tomcat8）
        //如果是get请求发送的中文数据，转码稍微有点麻烦（tomcat8之前的版本才需要这样）
        String fname = request.getParameter("fname");
        //1.将字符串打散成字节数组
        byte[] bytes = fname.getBytes("ISO-8859-1");
        //2.将字节数组按照设定的编码重新组装成字符串
        fname = new String(bytes,"UTF-8");
        */

        //post方式下，设置编码，防止中文乱码
        //需要注意的是，设置编码这一句代码必须在所有的获取参数动作之前
        request.setCharacterEncoding("utf-8");
        String fname = request.getParameter("fname");

        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);

        String focuntStr = request.getParameter("fcount");
        Integer focunt = Integer.parseInt(focuntStr);

        String remark = request.getParameter("remark");

        System.out.println("===========开始测试===========");
        FruitDAOImpl fruitDAO = new FruitDAOImpl();
        boolean flag = fruitDAO.addFruit(new Fruit(0, fname, price, focunt, remark));
        System.out.println(flag ? "添加成功" : "添加失败");

        // 重定向到index
        response.sendRedirect("index");
    }
}
