package com.wjd.nowcoder.controller.advice;

import com.wjd.nowcoder.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 只针对于@Controller注解的类
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    // 异常发生时，通过下面的方法自动捕获，Exception，表示处理所有的异常
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("服务器发生异常: " + e.getMessage());
        // 遍历异常栈的信息，element就是一条信息
        for (StackTraceElement element : e.getStackTrace()) {
            logger.error(element.toString());
        }

        // 通过下面的方式判断请求是同步请求还是异步请求，如果是异步请求需要返回json格式
        String xRequestedWith = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(xRequestedWith)) {  // 异步请求
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            // 返回json
            writer.write(CommunityUtil.getJSONString(1, "服务器异常!"));
        } else { // 同步请求
            // 重定向
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
