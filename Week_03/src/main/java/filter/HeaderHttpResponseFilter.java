package filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author hongzhengwei
 * @create 2021/1/24 下午12:16
 * @desc 输出过滤器
 **/
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("responseFilter", "responseFilter");
    }
}
