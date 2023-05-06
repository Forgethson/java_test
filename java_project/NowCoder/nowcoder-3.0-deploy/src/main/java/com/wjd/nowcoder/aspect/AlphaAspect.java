//package com.wjd.nowcoder.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class AlphaAspect {
//
//    // 匹配了业务层中所有的类、方法、任意的参数
//    @Pointcut("execution(* com.wjd.nowcoder.service.*.*(..))")
//    public void pointcut() {
//
//    }
//
//    @Before("pointcut()")
//    public void before() {
//        System.out.println("before");
//    }
//
//    @After("pointcut()")
//    public void after() {
//        System.out.println("after");
//    }
//
//    @AfterReturning("pointcut()")
//    public void afterRetuning() {
//        System.out.println("afterRetuning");
//    }
//
//    @AfterThrowing("pointcut()")
//    public void afterThrowing() {
//        System.out.println("afterThrowing");
//    }
//
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("around before");
//        Object obj = joinPoint.proceed();
//        System.out.println("around after");
//        return obj;
//    }
//
//}
