package com.sun.tool;

public class test {
    public static void main(String[] args) {
        new ServerListeningThread(8888).start();
    }
}
