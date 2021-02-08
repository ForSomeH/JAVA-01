package com.summer.bytebuddy;

public class WorkService {
    @Log
    public String  working(String value) {
        System.out.println("working: " + value);
        return value;
    }
}
