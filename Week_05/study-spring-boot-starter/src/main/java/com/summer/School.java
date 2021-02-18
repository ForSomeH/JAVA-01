package com.summer;


import com.summer.Klass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hongzw
 * @date 2021-02-08
 * */
@Data
@NoArgsConstructor
public class School {
    private String name;
    List<Klass> klasses;

    public School(String name,List<Klass> klasses){
        this.klasses=klasses;
        this.name=name;
    }
    public void print(){
        System.out.println("klasses:"+this.klasses.size());
        klasses.forEach(Klass::print);
    }
}
