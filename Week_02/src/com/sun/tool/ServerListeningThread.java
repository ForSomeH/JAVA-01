package com.sun.tool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListeningThread extends Thread {

    private int bindPort;
    private ServerSocket serverSocket;

    public ServerListeningThread(int port) {
        this.bindPort = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(bindPort);
            while (true) {
                Socket rcvSocket = serverSocket.accept();

                //单独写一个类，处理接收的Socket，类的定义在下面
                HttpRequestHandler request = new HttpRequestHandler(rcvSocket);
                request.handle();

                rcvSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //最后要确保以下把ServerSocket关闭掉
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


