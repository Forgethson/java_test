package com.wjd.nowcoder.controller.interceptor;


import com.wjd.nowcoder.annotation.LoginRequired;
import com.wjd.nowcoder.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果拦截到的是个方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 得到该方法
            Method method = handlerMethod.getMethod();
            // 获取自定义注解LoginRequired对象
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            // 如果方法标记了LoginRequired注解，且本地线程中User对象为null
            if (loginRequired != null && hostHolder.getUser() == null) {
                // 重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}
