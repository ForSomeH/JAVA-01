package com.summer.nettydemo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
    private  static Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    boolean ssl;

    public void run() throws Exception {
        final SslContext sslCtx;
        //判断是不是https请求
        if (ssl) {
            //获取安全证书
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
        } else {
            sslCtx = null;
        }
        EventLoopGroup bossGroup = new NioEventLoopGroup(3);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1000);
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .option(EpollChannelOption.SO_KEEPALIVE, true);

            sb.group(bossGroup, workerGroup) // 绑定线程池
                    .channel(NioServerSocketChannel.class) // 指定使用的channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //日志记录
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 绑定客户端连接时候触发操作
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("connected...; Client:" + ch.remoteAddress());
                            ch.pipeline().addLast(new HttpServerHandler()); // 客户端触发操作
                        }
                    });

            Channel cf = sb.bind(port).sync().channel(); // 服务器异步创建绑定
            System.out.println(HttpServer.class + " started and listen on " + cf.localAddress());
            cf.closeFuture().sync(); // 关闭服务器通道
        } finally {
            // 释放线程池资源
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new HttpServer(8801).run(); // 启动
    }
}
