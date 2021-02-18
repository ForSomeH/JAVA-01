package com.summer.spring02;

import com.summer.School;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

import javax.annotation.Resource;

@SpringBootApplication
@EnableJms    //启动消息队列
@EnableMongoRepositories
public class Springboot02Application  implements CommandLineRunner {

	@Resource
	private School school;

	public static void main(String[] args) {
		SpringApplication.run(Springboot02Application.class, args);
	}
	public void run(String... args) throws Exception {
		school.print();
	}

}
