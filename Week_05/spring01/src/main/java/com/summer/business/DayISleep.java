package com.summer.business;

public class DayISleep implements ISleep {

    @Override
    public void sleeping() {
        System.out.println("午睡中。。。zZZZZZZZZ");
    }
}
