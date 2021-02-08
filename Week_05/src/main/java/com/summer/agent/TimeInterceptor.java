package com.summer.agent;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimeInterceptor  {
    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable)
            throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            System.out.println("测试aop agent");
            return callable.call();
        } finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
