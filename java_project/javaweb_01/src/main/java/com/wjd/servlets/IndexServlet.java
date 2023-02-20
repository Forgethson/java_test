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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2023/1/10
 */

// Servlet从3.0版本支持注解方式的注册，不用再从xml文件里面写了
@WebServlet("/index")

public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    // 重写一下doGet（只要不是form表单的那种post形式，暂时都使用doGet的形式重写）
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 每次点击切换页的按钮或者查询按钮，都会通过下面的js函数发送请求，方法为get，然后就会进入到这个函数里面
        // window.location.href = "index?pageNo=" + pageNo;

        // 获取session
        HttpSession session = req.getSession() ;

        // 当前页默认为1
        int pageNo = 1 ;
        String oper = req.getParameter("oper");
        //如果oper!=null 说明 通过表单-查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单-查询按钮点击过来的
        String keyword = null ;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求，此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            keyword = req.getParameter("keyword");
            //如果keyword为null，需要设置为空字符串""，否则查询时会拼接成号 %null%，我们期望的是 %%
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            // 保存keyword，因为后面上一页下一页，也是基于keyword的查询结果来的
            session.setAttribute("keyword",keyword);
        }else{
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）此时keyword应该从session作用域获取
            String pageNoStr = req.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword , pageNo);

        // 更新当前页的值
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 10 - 1) / 10;
        /*
        总记录条数       总页数
        1               1
        10              1
        11              2
        fruitCount      (fruitCount+10-1)/10
         */
        session.setAttribute("pageCount", pageCount);

        super.processTemplate("index", req, resp);

    }
}
