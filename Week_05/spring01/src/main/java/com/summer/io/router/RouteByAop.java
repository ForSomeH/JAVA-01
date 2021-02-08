package com.summer.io.router;

import io.netty.handler.codec.http.FullHttpRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hongzhengwei
 * @create 2021/2/5 9:21 上午
 * @desc 注解实现动态代理aop
 **/
@Aspect
@Component
public class RouteByAop {

    //指定切点切面
    @Pointcut(value = "execution(* com.summer.io.outbound.HttpOutboundServerHandlerOne.*handleTaskOne(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {

    }

    @AfterReturning(value = "point()")
    public void after() {

    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        //传入参数
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            System.out.println("---->now-->argItem:" + argItem);
            if (argItem instanceof String) {
                HttpEndpointRouterImpl router = HttpEndpointRouterImpl.getRoute();
                obj[2] = router.roundRobin((List<String>) obj[3]);
            }
            System.out.println("---->after-->argItem:" + argItem);
        }
        //切入方法执行
        joinPoint.proceed(obj);
        //后拦截
        System.out.println("========>执行完成");

    }


}
