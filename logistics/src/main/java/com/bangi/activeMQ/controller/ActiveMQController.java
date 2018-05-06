package com.bangi.activeMQ.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bangi.activeMQ.consumer.ConsumerQueueService;
import com.bangi.logistics.dao.LogisDao;

@Controller
public class ActiveMQController {

	@Autowired
	@Qualifier("logisDao")
	private LogisDao logisDao;
	
	//队列名为物流信息详情
    @Resource(name="queueDestination")
    private Destination queueDestination;
    
    //接收消息
    @Resource(name="consumerQueueService")
    private ConsumerQueueService consumerQueueService;
    
    /* 返回物流主页面 */
	@RequestMapping(value="/logistics/logis")
	public String toLogisPage(){
		return "logis";
	}
    
    /**
     * 接收消息
     * @return
     * @throws JMSException
     * @throws ParseException 
     */
    @RequestMapping(value="/activeMQ/receiveQueue")
    @ResponseBody
    public void queue_receive() throws JMSException, ParseException  {
    	TextMessage tm = consumerQueueService.receive(queueDestination);
    	if(tm != null) {
    		String message = tm.getText();
            String[] msg = message.split(";");
            //获取订单信息
            String orderId = msg[0];
            Date createTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").parse(msg[1]);
            int submitUserId = Integer.valueOf(msg[2]);
            int orderNum = Integer.valueOf(msg[3]);
            float orderAmount = Float.valueOf(msg[4]);
            String orderStatus = msg[5];
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", orderId);
            map.put("createTime", createTime);
            map.put("submitUserId", submitUserId);
            map.put("orderStatus", orderStatus);
            map.put("orderNum", orderNum);
            map.put("orderAmount", orderAmount);
            map.put("orderDepict", "订单已提交");
            logisDao.saveLogis(map);
            logisDao.saveLogisticsDepict(map);
    	}
    }
}
