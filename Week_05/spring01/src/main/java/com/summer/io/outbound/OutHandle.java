package com.summer.io.outbound;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import static com.google.common.net.HttpHeaders.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

public interface OutHandle {

    /**
     * aop过滤
     */
    void handle(FullHttpRequest fullHttpRequest, FullHttpResponse response, ChannelHandlerContext ctx);
}
