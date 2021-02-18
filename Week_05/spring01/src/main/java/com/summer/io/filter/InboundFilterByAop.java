package com.summer.io.filter;

import com.summer.io.router.HttpEndpointRouterImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.google.common.net.HttpHeaders.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @author hongzhengwei
 * @create 2021/2/5 9:21 上午
 * @desc 注解实现动态代理aop
 **/
@Aspect
@Component
public class InboundFilterByAop {

    //指定切点切面
    @Pointcut(value = "execution(* com.summer.io.inbound.HttpInboundServerHandler.*channelRead(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {
        System.out.println("========>准备拦截");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("========>彻底完成");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========>拦截前");
        //参数方法
        FullHttpRequest fullHttpRequest = (FullHttpRequest) joinPoint.getArgs()[1];
        //前拦截
        inboundFilter(fullHttpRequest);
        //切入方法执行
        joinPoint.proceed();
        //后拦截
        System.out.println("========>执行完成");

    }

    /**
     * 输入拦截器
     *
     * @param fullRequest
     */
    public void inboundFilter(FullHttpRequest fullRequest) {
        fullRequest.headers().set("AopRequestFilter", "AopRequestFilter");
        fullRequest.headers().set("requestFilter", "requestFilter");

    }


}
