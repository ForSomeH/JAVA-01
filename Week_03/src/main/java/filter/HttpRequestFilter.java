package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author  hongzhengwei
 * @create  2021/1/24 下午12:15
 * @desc    输入过滤器
 **/
public interface HttpRequestFilter {

    /**
     * @author  hongzhengwei
     * @create  2021/1/24 下午12:16
     * @desc    对输入进行过滤，ctx为上下文
     **/
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
    
}
