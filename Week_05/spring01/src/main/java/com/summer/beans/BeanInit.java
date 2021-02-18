package com.summer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInit {

    @Bean(name = "beanUser")
    public User getUser() {
        return new User("hongzw", "man", 24);
    }
}
