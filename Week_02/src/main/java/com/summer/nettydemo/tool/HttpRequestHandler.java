package com.summer.nettydemo.tool;

import java.io.IOException;
import java.net.Socket;

public class HttpRequestHandler {
    private Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() throws IOException {
        //TODO 这里写处理接收到的socket的逻辑
    }
}
