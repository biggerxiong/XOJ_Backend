package me.xiongxuan.xoj.service;

import com.google.gson.Gson;
import me.xiongxuan.xoj.entity.MqMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.transaction.Transactional;

@Service
public class ActiveMqProducerService {

    @Resource
    private JmsTemplate jmsTemplate;

    @Transactional(rollbackOn = Exception.class)
    public void sendToJudgeQueue(final MqMessage mqMessage) {
        jmsTemplate.send("JudgeQueue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Gson gson = new Gson();
                return session.createTextMessage(gson.toJson(mqMessage));
            }
        });
    }
}
