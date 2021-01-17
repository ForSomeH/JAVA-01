package com.summer.nettydemo.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author hongzw
 * @Description 单线程处理
 * @Date 下午3:11 2021/1/16
 **/
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        //创建一个socket绑定8081
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                SocketServer.server(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
