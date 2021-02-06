package com.summer;


import com.summer.beans.Company;
import com.summer.beans.User;
import com.summer.beans.UserC;
import com.summer.business.Dinner;
import com.summer.business.ISleep;
import com.summer.io.inbound.HttpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTestApplication {

    public static void main(String[] args) {
        //注册上下文
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //todo 作业1：aop代理测试,xml+注解
        homeWork1(context);
        //todo 作业2不同方式装配bean
        homeWork2(context);
        //todo 作业3xml装配一组bean
        homeWork3(context);
        //todo 作业4将netty的inbound，outbound，filter，router交给spring管理
        //todo   4.2 (挑战)基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现;
        //todo   4.3 (中级挑战)基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息;
        //todo   4.4 (中级挑战)尝试使用 ByteBuddy 实现一个简单的基于类的 AOP;
        //todo   4.5 (超级挑战)尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入 下的 AOP。
        //netty改造
        //初始化后端服务请求的数据
        String proxyServers = System.getProperty("proxyServers", "http://localhost:8088/api/hello");
        HttpServer server = (HttpServer) context.getBean("httpServer");
        //HttpServer server = new HttpServer(2100, Arrays.asList(proxyServers.split(",")));
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @author hongzhengwei
     * @create 2021/2/5 9:07 下午
     * @desc 作业1，aop动态代理
     **/
    static void homeWork1(ApplicationContext context) {
        Dinner dinner = context.getBean(Dinner.class);
        System.out.println("Dinner对象AOP代理后的实际类型是否是Dinner子类：" + (dinner instanceof Dinner));
        dinner.eating();
    }

    /**
     * @author hongzhengwei
     * @create 2021/2/5 9:07 下午
     * @desc 作业2不同方式装配bean
     **/
    static void homeWork2(ApplicationContext context) {
        //方式1：xml配置接口
        ISleep ISleep = context.getBean(ISleep.class);
        System.out.println(ISleep);
        System.out.println("第一种加载方式，xml配置，接口的对象AOP代理后的实际类型：" + ISleep.getClass());
        //方式2：@Bean注解bean
        User user = (User) context.getBean("beanUser");
        System.out.println("第二种加载方式，@Bean获取：" + user);
        //方式3：@Component
        UserC userC = context.getBean(UserC.class);
        System.out.println("第三种加载方式，@Bean获取：" + userC);
    }

    /**
     * @author hongzhengwei
     * @create 2021/2/5 9:07 下午
     * @desc 作业3，xml方式装配一组bean
     * xml装配bean Company->Department->User,其中company是注解，其余是xml指定
     **/
    static void homeWork3(ApplicationContext context) {
        Company gillion = context.getBean(Company.class);
        System.out.println(gillion);
    }

}
