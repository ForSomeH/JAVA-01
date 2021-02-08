package com.summer.jms;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.IOException;

/**
 * @author hongzhengwei
 * @create 2021/2/7 2:27 下午
 * @desc 对jms发来的消息进行监听，并转发给相应的后端服务
 **/
@Component(value = "jmsListener")
public class JmsListener implements MessageListener {

    //收到信息时的动作
    @Override
    public void onMessage(Message m) {
        ObjectMessage message = (ObjectMessage) m;
        try {
            System.out.println("收到的信息：" + message.getObject());
            String asString = getAsString((String) message.getObject());
            System.out.println("输出的结果"+asString);
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    // GET 调用
    public String getAsString(String url) throws IOException {
        //MQ进行解耦，将请求路径发消息出去
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");
        } finally {
            if (null != response1) {
                response1.close();
            }
        }
    }
}