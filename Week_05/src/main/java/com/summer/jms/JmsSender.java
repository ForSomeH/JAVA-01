package com.summer.jms;

import com.summer.beans.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmsSender {
    
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springjms-sender.xml");
        SendService sendService = (SendService)context.getBean("sendService");
        for (int i = 101; i < 110; i++) {
            User student2 = new User("hongzw","nan",i);
            sendService.send(student2);
        }
        System.out.println("send successfully, please visit http://localhost:8161/admin to see it");
    }
    
}
