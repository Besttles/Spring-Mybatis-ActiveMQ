package com.bangi.activeMQ.producer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class ProducerService {

	@Resource(name="jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
     * 向指定队列发送消息
     */
    public void sendMessage(Destination destination, final String msg) {
    	jmsTemplate.send(destination, new MessageCreator() {
    		public Message createMessage(Session session) throws JMSException {
    			return session.createTextMessage(msg);
    		}
    	});
    }
}
