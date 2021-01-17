package com.summer.nettydemo.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.summer.nettydemo.demo.SocketServer.server;

/**
 * @Author hongzw
 * @Description 线程池处理
 * @Date 下午3:11 2021/1/16
 **/
public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        //创建一个固定的线程池来处理
        ExecutorService executorService = Executors.newFixedThreadPool(80);
        //绑定端口
        final ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> {
                    server(socket);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
