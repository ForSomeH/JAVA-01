package router;

import java.util.List;

/**
 * @author hongzhengwei
 * @create 2021/1/24 上午11:19
 * @desc 不同机制的路由器
 **/
public interface HttpEndpointRouter {

    /**
     * 随机路由
     *
     * @param endpoints
     * @return
     */
    String randomRoute(List<String> endpoints);

    // LoadBalance

    /**
     * @author hongzhengwei
     * @create 2021/1/24 下午5:16
     * @desc    轮训算法
     **/
    String RoundRobin(List<String> endpoints);
    // - server01,20
    // - server02,30
    // - server03,50

}
