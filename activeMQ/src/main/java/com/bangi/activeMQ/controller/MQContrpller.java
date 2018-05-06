package com.bangi.activeMQ.controller;


import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bangi.activeMQ.producer.ProducerService;

@Controller
public class MQContrpller {

	 //������ ������������
    @Resource(name="queueDestination")
    private Destination queueDestination;
    
    

    //������Ϣ������
    @Resource(name="producerService")
    private ProducerService producerService;
    
    
    
    /**
     * ������Ϣ����
     * @param message
     * @return
     */
    @RequestMapping(value="/activeMQ/toSendMessage")
    @ResponseBody
    public void producer(HttpServletRequest req) {
    	String message = req.getParameter("message");
    	producerService.sendMessage(queueDestination, message);
    }
}
