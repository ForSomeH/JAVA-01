package com.summer.jms;

import com.summer.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class SendService {
    @Autowired
    JmsTemplate jmsTemplate;

    public void send(final User user) {
        jmsTemplate.send("test.queue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(user);
            }
        });
    }

    public void sendUrl(final String url) {
        jmsTemplate.send("test.queue", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(url);
            }
        });
    }
}