package com.summer.nettydemo.task;

import java.io.IOException;

/**
 * @Author hongzw
 * @Description http作业测试
 * @Date 下午10:52 2021/1/18
 **/
public class HttpTest {

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8801/test";
        testHttpClientHelper(url);
        testOkHttpUtils(url);
    }

    /**
     * @Author hongzw
     * @Description //测试okhttp
     * @Date 下午11:23 2021/1/18
     * @Param [url]
     * @return void
     **/
    public static void testOkHttpUtils(String url) throws IOException {
        String text = OkHttpUtils.getAsString(url);
        System.out.println("url: " + url + " ; response: \n" + text);
        // 清理资源
        OkHttpUtils.client = null;
    }
    /**
     * @Author hongzw
     * @Description //测试httpclient
     * @Date 下午11:23 2021/1/18
     * @Param [url]
     * @return void
     **/
    public static void testHttpClientHelper(String url) throws IOException {
        String text = HttpClientHelper.getAsString(url);
        System.out.println("url: " + url + " ; response: \n" + text);
    }
}
