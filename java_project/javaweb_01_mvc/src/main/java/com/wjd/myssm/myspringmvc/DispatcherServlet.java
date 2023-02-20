package com.wjd.myssm.myspringmvc;

import com.wjd.myssm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// 表示对应于各种以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {
    }

    public void init() {
        try {
            // 注意路径："applicationContext.xml" 指的是以项目结构中，以源代码文件夹为准的相对路径！
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建 DocumentBuilderFactory 对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建 DocumentBuilder 对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建 Document 对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取（applicationContext.xml里面）所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);

                // 如果是元素类型的节点
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    // 强转为 Element 类型
                    Element beanElement = (Element) beanNode;
                    // 通过 Element 类里面的方法，得到bean节点的id和class属性
                    String beanId = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");

                    // 得到某一个bean节点class反射而来的Class类对象，如 FruitController 类
                    Class controllerBeanClass = Class.forName(className);
                    // 生成一个如 FruitController 类的实例
                    Object beanObj = controllerBeanClass.newInstance();

                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
                    setServletContextMethod.invoke(beanObj, this.getServletContext());

                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | IllegalAccessException |
                 InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        // 当浏览器访问 http://localhost:8080/forgethson/hello.do 时
        //当前获取到的路径为servletPath：    /hello.do
        // 我们想要从中得到 hello
        String servletPath = request.getServletPath();
        // 得到点.的索引
        int lastDotIndex = servletPath.lastIndexOf(".do");
        // 去掉开头的斜杠以及.后面的部分，得到hello
        servletPath = servletPath.substring(1, lastDotIndex);

        // 通过beanMap以及该请求的标签名hello，得到与之对应的一个HttpServlet子类的对象（如：HelloServlet）
        Object controllerBeanObj = beanMap.get(servletPath);


        // 下面的代码就是之前FruitControllers中service方法的一部分；operate就是FruitControllers中其他的方法名
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        try {
            // 前面FruitControllers里面用的是getDeclaredMethods()，得到的是列表
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            // 由于FruitControllers里面的方法是private修饰的因此用爆破
            method.setAccessible(true);
            method.invoke(controllerBeanObj, request, response);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e){
            throw new RuntimeException("operate值非法!");
        }
    }
}
