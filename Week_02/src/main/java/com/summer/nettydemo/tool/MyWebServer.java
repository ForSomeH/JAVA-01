package com.summer.nettydemo.tool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyWebServer {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // 创建一个监听8000端口的服务器Socket
            ServerSocket s = new ServerSocket(8000, 3);
            System.out.println("MyWebServer等待来自浏览器的连接\n");
            while (true) {
                socket = s.accept();
                System.out.println("连接已建立。端口号：" + socket.getPort());
                new MyWebServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

