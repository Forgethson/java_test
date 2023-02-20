package com.wjd.myssm.myspringmvc;

import com.wjd.myssm.ioc.BeanFactory;
import com.wjd.myssm.ioc.ClassPathXmlApplicationContext;
import com.wjd.myssm.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

// 表示对应于各种以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory ;

    public DispatcherServlet() {
    }

    public void init() throws ServletException {
        super.init();
        //之前是在此处主动创建IOC容器的
        //现在优化为从application作用域去获取
        //beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if(beanFactoryObj!=null){
            beanFactory = (BeanFactory)beanFactoryObj ;
        }else{
            throw new RuntimeException("IOC容器获取失败！");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 当浏览器访问 http://localhost:8080/forgethson/hello.do 时
        //当前获取到的路径为servletPath：    /hello.do
        // 我们想要从中得到 hello
        String servletPath = request.getServletPath();
        // 得到点.的索引
        int lastDotIndex = servletPath.lastIndexOf(".do");
        // 去掉开头的斜杠以及.后面的部分，得到hello
        servletPath = servletPath.substring(1, lastDotIndex);

        // 通过beanMap以及该请求的标签名hello，得到与之对应的一个HttpServlet子类的对象（如：HelloServlet）
        Object controllerBeanObj = beanFactory.getBean(servletPath);


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
            throw new DispatcherServletException("DispatcherServlet 出错了");
        }
    }
}
