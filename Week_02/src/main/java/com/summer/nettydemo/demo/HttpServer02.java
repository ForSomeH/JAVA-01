package com.summer.nettydemo.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.summer.nettydemo.demo.SocketServer.server;

/**
 * @Author hongzw
 * @Description 每个请求单独处理
 * @Date 下午3:11 2021/1/16
 **/
public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        //创建一个socket绑定8081
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    server(socket);
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
