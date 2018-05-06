package com.bangi.ssm.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bangi.ssm.service.SsmService;

import net.sf.json.JSONArray;

@Controller
public class SsmController {

	@Resource(name = "ssmService")
	private SsmService ssmService;
	
	/* ��ѯ�û�ҳ�� */
	@RequestMapping(value="/ssm/findUsers")
	public String findUsers() {
		return "ssm/findUsers";
	}
	
	/* ��ѯ��Ʒҳ�� */
	@RequestMapping(value="/ssm/toShopPage")
	public String toShopPage(Map map, HttpServletRequest req) {
		map.put("userId", req.getParameter("userId"));
		return "ssm/toShopPage";
	}
	
	/* ��ѯ�û���Ϣ����ǰ̨���� */
	@RequestMapping(value="/ssm/getUsersData")
	@ResponseBody
	public void getUsersData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Map> list = ssmService.findUsers();
		JSONArray jsonArr = JSONArray.fromObject(list);
		PrintWriter writer;
		response.setCharacterEncoding("utf-8");
		writer = response.getWriter();
		writer.write(jsonArr.toString());
	}
	
	/* ��ѯ��Ʒ��Ϣ����ǰ̨���� */
	@RequestMapping(value="/ssm/getOrdersData")
	@ResponseBody
	public void getOrdersData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Map> list = ssmService.findOrders();
		JSONArray jsonArr = JSONArray.fromObject(list);
		PrintWriter writer;
		response.setCharacterEncoding("utf-8");
		writer = response.getWriter();
		writer.write(jsonArr.toString());
	}
	
	/* �����ҵĶ�����Ϣҳ�� */
	@RequestMapping(value="/ssm/myGoodsPage")
	public String myGoodsPage(Map map, HttpServletRequest req) {
		map.put("userId", req.getParameter("userId"));
		return "ssm/myGoodsPage";
	}
	
	/* ��ѯ�ҵĶ�����Ϣ����ǰ̨���� */
	@RequestMapping(value="/ssm/getMyOrdersData")
	@ResponseBody
	public void getMyOrdersData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		
		Map map = new HashMap();
		map.put("sort", req.getParameter("sort"));
		map.put("submitUserId", req.getParameter("submitUserId"));
		//�����ύ��id�������Ϣ���ؿͻ���
		List<Map> list = ssmService.findMyOrders(map);
		JSONArray jsonArr = JSONArray.fromObject(list);
		PrintWriter writer;
		response.setCharacterEncoding("utf-8");
		writer = response.getWriter();
		writer.write(jsonArr.toString());
	}
	
	/* ������Ʒ */
	@RequestMapping(value="/ssm/getBuyData")
	@ResponseBody
	public void getBuyData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		
		//������
		//��ȡ���Ӻ�
		Map<String,Object> param = new HashMap<String,Object>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		String dateString = sdf.format(date);  
		param.put("seedDateStr", dateString);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmm");  
		String preSeed = sdf2.format(date); //�������ǰ����
		
		//�����ȡ��������Ӻ�
		List<Map<String,Object>> seeds = ssmService.getSeedByDate(param);
		if(seeds!=null&&seeds.size()>0) {
			//��ȡ�������Ӻ�
			String seedNumber = seeds.get(0).get("seed_number").toString();
			seedNumber = String.valueOf(Integer.valueOf(seedNumber)+1);
			String allseed = "0000"+seedNumber;
			//��ȡ������ź���λ
			String seedNumSuf = allseed.substring(seedNumber.length());
			//ƴ�Ӻ�Ķ������
			String orderNum = preSeed + seedNumSuf;
			
			Map map = new HashMap();
			map.put("orderId", orderNum);
			map.put("createTime", new Date());
			map.put("submitUserId", req.getParameter("userId"));
			map.put("orderAmount", req.getParameter("amount"));
			map.put("orderStatus", 1);
			ssmService.saveBuyData(map);
			/* �Ѷ�����Ϣ����ǰ̨���� */
			List<Map> list = new ArrayList<Map>();
			list.add(map);
			JSONArray jsonArr = JSONArray.fromObject(list);
			PrintWriter writer;
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(jsonArr.toString());
			//�������Ӻ�+1
			ssmService.updateSeedByDate(param);
			
		}else {
			Map map = new HashMap();
			map.put("orderId", preSeed+"0001");
			map.put("createTime", new Date());
			map.put("submitUserId", req.getParameter("userId"));
			map.put("orderAmount", req.getParameter("amount"));
			map.put("orderStatus", 1);
			ssmService.saveBuyData(map);
			//���û�о��ǵ����һ�ʡ��������ӱ�
			param.put("seedNumber", "0001");
			ssmService.saveSeed(param);
			/* �Ѷ�����Ϣ����ǰ̨���� */
			List<Map> list = new ArrayList<Map>();
			list.add(map);
			JSONArray jsonArr = JSONArray.fromObject(list);
			PrintWriter writer;
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(jsonArr.toString());
		}
	}
	
	/* ɾ��������Ϣ */
	@RequestMapping(value="/ssm/delOrdersData")
	@ResponseBody
	public void delOrdersData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", req.getParameter("orderId"));
		ssmService.delOrder(map);
	}
	
	/* ��ȡ�ύ������Ϣ */
	@RequestMapping(value="/ssm/getSubMQData")
	@ResponseBody
	public void getSubMQData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		map.put("userId", req.getParameter("userId"));
		List<Map<String, Object>> list = ssmService.findSubMQData(map);
		
		JSONArray jsonArr = JSONArray.fromObject(list);
		PrintWriter writer;
		response.setCharacterEncoding("utf-8");
		writer = response.getWriter();
		writer.write(jsonArr.toString());
	}
	
}







