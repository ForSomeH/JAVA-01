package filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author  hongzhengwei
 * @create  2021/1/24 下午12:16
 * @desc    输出过滤器
 **/
public interface HttpResponseFilter {

    /**
     * 返回增强
     * @param response
     */
    void filter(FullHttpResponse response);

}
