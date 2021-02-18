package com.summer.io.filter;

import com.summer.io.outbound.HttpOutboundServerHandlerOne;
import com.summer.io.router.HttpEndpointRouterImpl;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author hongzhengwei
 * @create 2021/2/5 9:21 上午
 * @desc 注解实现动态代理aop
 **/
@Aspect
@Component
public class OutboundFilterByAop {

    //指定切点切面
    @Pointcut(value = "execution(* com.summer.io.outbound.OutHandleImpl.*handle(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {
        System.out.println("========>准备拦截:");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("========>彻底完成");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========>拦截前");
        //传入参数
        Object[] obj = joinPoint.getArgs();
        String inputMsg = null;

        for (Object argItem : obj) {
            System.out.println("---->now-->argItem:" + argItem);
            if (argItem instanceof FullHttpRequest) {
                FullHttpRequest request = (FullHttpRequest) argItem;
                inputMsg = request.headers().get("AopRequestFilter");
            }
            if (argItem instanceof FullHttpResponse) {
                FullHttpResponse response = (FullHttpResponse) argItem;
                response.headers().set("responseFilterAop", "responseFilterAop");
                if (!StringUtils.isEmpty(inputMsg)) {
                    response.headers().set("AopRequestFilter", inputMsg);
                }
            }
            System.out.println("---->after-->argItem:" + argItem);
        }
        //切入方法执行
        joinPoint.proceed(obj);
    }


}
