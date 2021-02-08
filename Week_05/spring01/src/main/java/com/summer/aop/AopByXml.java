package com.summer.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author hongzhengwei
 * @create 2021/2/5 9:21 上午
 * @desc xml配置实现动态代理aop，xml指定启动加载类
 **/
public class AopByXml {
    //前置通知
    public void startTransaction() {
        System.out.println("饭前要洗手");
    }

    //后置通知
    public void commitTransaction() {
        System.out.println("饭后要漱口");
    }

    //环绕通知
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前菜");
        //调用process()方法才会真正的执行实际被代理的方法
        joinPoint.proceed();
        System.out.println("饭后甜点");
    }
}
