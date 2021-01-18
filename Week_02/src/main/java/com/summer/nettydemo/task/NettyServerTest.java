package com.summer.nettydemo.task;

import com.summer.nettydemo.netty.HttpServer;

public class NettyServerTest {
    /***
     * @Author hongzw
     * @Description 老师的netty demo
     * @Date 下午11:27 2021/1/18
     * @Param [args]
     * @return void
     **/
    public static void main(String[] args) throws Exception {
        //启动老师的netty demo
        new HttpServer(8801).run(); // 启动
    }
}
