package com.summer.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author hongzhengwei
 * @create 2021/2/5 9:21 上午
 * @desc 注解实现动态代理aop
 **/
@Aspect
@Component
public class AopByAnnotate {

    //指定切点切面
    @Pointcut(value = "execution(* com.summer.*.Dinner.*eating(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {
        System.out.println("========>前菜");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("========>饭后要漱口");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========>饭前要洗手");
        joinPoint.proceed();
        System.out.println("========>饭后甜点");

    }
}
