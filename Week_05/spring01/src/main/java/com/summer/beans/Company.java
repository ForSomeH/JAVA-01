package com.summer.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    String name;
    @Autowired
    List<Department> departments;

    @Resource(name = "boss")
    User boss;
}
