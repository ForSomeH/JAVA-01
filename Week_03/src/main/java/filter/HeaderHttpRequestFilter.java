package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author hongzhengwei
 * @create 2021/1/24 下午12:15
 * @desc 输入过滤器
 **/
public class HeaderHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
            fullRequest.headers().set("requestFilter", "requestFilter");

    }
}
