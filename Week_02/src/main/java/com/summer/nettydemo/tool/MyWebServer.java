package com.summer.nettydemo.tool;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

class MyWebServerThread extends Thread {
    private Socket socket;

    MyWebServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader is = new InputStreamReader(socket.getInputStream());
            char[] bs = new char[2048];
            PrintStream out;
            out = new PrintStream(socket.getOutputStream());
            StringBuilder msg = new StringBuilder();
//            // 如果10毫秒还没有数据，则视同没有新的数据了。
//            // 因为有Keep-Alive的缘故，浏览器可能不主动断开连接的。
//            // 实际应用，会根据协议第一行是GET还是 POST确定。
            socket.setSoTimeout(10);
            //
            // 此处读入请求数据并做相应的处理
            //
            int len = -1;
            try {
                while ((len = is.read(bs)) != -1) {
                    msg.append(bs, 0, len);
                    msg.append("\n");
                }
            } catch (Exception ex) {
                // ex.printStackTrace();
            }
            // 下面是由服务器直接生成的主页内容
            // 1、首先向浏览器输出响应头信息
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type:text/html;charset:GBK");
            out.println();
            // 2、输出主页信息
            out.println(" success");
            out.flush();
            out.close();
            is.close();
            System.out.println("close");
            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
