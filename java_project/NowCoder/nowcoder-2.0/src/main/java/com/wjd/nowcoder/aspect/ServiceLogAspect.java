package com.wjd.nowcoder.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class ServiceLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Pointcut("execution(* com.wjd.nowcoder.service.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 日志格式：用户[1.2.3.4],在[xxx时间],访问了[com.nowcoder.community.service.xxx()].
        // 利用RequestContextHolder工具类获取当前的ServletRequestAttributes，进而获得request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 这里，由于kafka的消费者中，Eventconsumer调用了messageService，因此切面类拦截到此，由于不是通过Controller调用，因此attributes为null（bug）
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        // 得到当前用户的ip地址
        String ip = request.getRemoteHost();
        // 指定日期格式
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 得到方法签名中的：类型和方法名
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 记录日志
        logger.info(String.format("用户[%s],在[%s],访问了[%s].", ip, now, target));
    }
}
