package com.bangi.activeMQ.consumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.bangi.logistics.dao.LogisDao;

/**
 * 消费者(点对点)
 * @author 
 *
 */
@Service("consumerQueueService")
public class ConsumerQueueService {

	@Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
	
	
     
    /**
     * 接收消息
     * @throws JMSException 
     * @throws ParseException 
     */
    public TextMessage receive(Destination destination) throws JMSException, ParseException {
    	TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
    	return tm;
    	
    }
}
