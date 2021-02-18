package com.summer.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author hongzhengwei
 * @create 2021/2/7 2:26 下午
 * @desc 消费者
 **/
public class JmsReceiver {

    public static void main1(String[] args) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springjms-receiver.xml");

        System.in.read();

        System.out.println("send successfully, please visit http://localhost:8161/admin to see it");
    }

}
