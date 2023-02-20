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
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

// 表示对应于各种以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {
    }

    public void init() throws ServletException {
        // 注意，这里重写了父类ViewBaseServlet的初始化方法，需要补充上，这里不能简单的直接重写覆盖！
        super.init();
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
                    beanMap.put(beanId, beanObj);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | IllegalAccessException |
                 InstantiationException | ClassNotFoundException e) {
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
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //1.统一获取请求参数

                    //1-1.获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //1-2.parameterValues 用来存放参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //如果参数名是request,response,session 那么就不是通过请求中获取参数的方式了（就是Servlet相关的参数值了，因此手动赋值）
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;

                            // 如果该参数的类型是Integer，需要将String其转换为Integer
                            if (parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj;
                        }
                    }
                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    //3.视图处理
                    String methodReturnStr = (String) returnObj;

                    // 如果以 redirect 开头，说明需要执行重定向语句
                    if (methodReturnStr.startsWith("redirect:")) {        //比如：  redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        // redirectStr = fruit.do，重定向
                        response.sendRedirect(redirectStr);
                    } else { // 否则，执行服务器内部转发（通过Thymeleaf）
                        super.processTemplate(methodReturnStr, request, response);    // 比如：  "edit"
                    }
                }
            }

            /*
            }else{
                throw new RuntimeException("operate值非法!");
            }
            */
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
