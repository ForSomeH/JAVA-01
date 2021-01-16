package com.sun.demo;

import java.io.IOException;
import java.io.PrintWriter;
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
        ServerSocket serverSocket=new ServerSocket(8801);
        while(true){
            try {
            Socket socket=serverSocket.accept();
            server(socket);
            }catch (IOException e){
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
//            Thread.sleep(20);
            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + "连接上了");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            //模拟输出报文头和内容
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("hello,nio");

            //通讯结束关闭资源
            printWriter.close();
            socket.close();
        } catch ( IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }

    }

}
