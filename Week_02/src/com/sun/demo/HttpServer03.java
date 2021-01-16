package com.sun.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author hongzw
 * @Description 线程池处理
 * @Date 下午3:11 2021/1/16
 **/
public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        //创建一个固定的线程池来处理
        ExecutorService executorService = Executors.newFixedThreadPool(40);
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

    /*
     * @Author hongzw
     * @Description 客户端
     * @Date 下午3:14 2021/1/16
     * @Param [socket]
     * @return void
     **/
    private static void server(Socket socket) {
        try {
            //模拟业务操作
            Thread.sleep(20);
            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + "连接上了");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            //模拟输出报文头和内容
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,线程池nio");
            printWriter.flush();
            //通讯结束关闭资源
            printWriter.close();
            socket.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

}
