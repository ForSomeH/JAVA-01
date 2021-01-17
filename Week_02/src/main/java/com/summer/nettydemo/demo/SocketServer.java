package com.summer.nettydemo.demo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * @Author hongzw
 * @Description 套接字的服务端代码
 * @Date 下午11:51 2021/1/16
 **/
public class SocketServer {
    /**
     * @return void
     * @Author hongzw
     * @Description 实现套接字不报错的方法
     * @Date 下午11:52 2021/1/16
     * @Param [socket]
     **/
    public static void server(Socket socket) {
        try {
            //设置超时， 如果10毫秒还没有数据，则视同没有新的数据了。因为有Keep-Alive的缘故，浏览器可能不主动断开连接的。 实际应用，会根据协议第一行是GET还是 POST确定。
            //socket.setSoTimeout(10);
            Thread.sleep(20);
//            System.out.println(socket.getInetAddress() + ":" + socket.getPort() + "连接上了");
            //readMsg(socket);
            //服务器响应输出
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,线程池nio";
            //不知道报文长度，会异常结束请求
            out.println("Content-length:" + body.getBytes().length);
            out.println();
            out.write(body);
            out.close();
            //is.close();
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    void readMsg(Socket socket) {
        //读取链接的请求
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] bs = new char[2048];
        StringBuilder msg = new StringBuilder();
        int len = -1;
        try {
            while ((len = is.read(bs)) != -1) {
                msg.append(bs, 0, len);
                msg.append("\n");
            }
        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }
}
