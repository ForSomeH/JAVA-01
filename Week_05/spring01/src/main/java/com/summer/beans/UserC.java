package com.summer.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserC {
    String name;
    String sex;
    int age;
}
