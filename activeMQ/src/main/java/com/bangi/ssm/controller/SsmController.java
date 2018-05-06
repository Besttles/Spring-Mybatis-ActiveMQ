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
	
	/* 查询用户页面 */
	@RequestMapping(value="/ssm/findUsers")
	public String findUsers() {
		return "ssm/findUsers";
	}
	
	/* 查询商品页面 */
	@RequestMapping(value="/ssm/toShopPage")
	public String toShopPage(Map map, HttpServletRequest req) {
		map.put("userId", req.getParameter("userId"));
		return "ssm/toShopPage";
	}
	
	/* 查询用户信息并向前台传入 */
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
	
	/* 查询商品信息并向前台传入 */
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
	
	/* 返回我的订单信息页面 */
	@RequestMapping(value="/ssm/myGoodsPage")
	public String myGoodsPage(Map map, HttpServletRequest req) {
		map.put("userId", req.getParameter("userId"));
		return "ssm/myGoodsPage";
	}
	
	/* 查询我的订单信息并向前台传入 */
	@RequestMapping(value="/ssm/getMyOrdersData")
	@ResponseBody
	public void getMyOrdersData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		
		Map map = new HashMap();
		map.put("sort", req.getParameter("sort"));
		map.put("submitUserId", req.getParameter("submitUserId"));
		//根据提交人id查出的信息返回客户端
		List<Map> list = ssmService.findMyOrders(map);
		JSONArray jsonArr = JSONArray.fromObject(list);
		PrintWriter writer;
		response.setCharacterEncoding("utf-8");
		writer = response.getWriter();
		writer.write(jsonArr.toString());
	}
	
	/* 购买商品 */
	@RequestMapping(value="/ssm/getBuyData")
	@ResponseBody
	public void getBuyData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		
		//订单号
		//获取种子号
		Map<String,Object> param = new HashMap<String,Object>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		String dateString = sdf.format(date);  
		param.put("seedDateStr", dateString);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmm");  
		String preSeed = sdf2.format(date); //订单编号前日期
		
		//按天获取当天的种子号
		List<Map<String,Object>> seeds = ssmService.getSeedByDate(param);
		if(seeds!=null&&seeds.size()>0) {
			//获取当天种子号
			String seedNumber = seeds.get(0).get("seed_number").toString();
			seedNumber = String.valueOf(Integer.valueOf(seedNumber)+1);
			String allseed = "0000"+seedNumber;
			//获取订单编号后四位
			String seedNumSuf = allseed.substring(seedNumber.length());
			//拼接后的订单编号
			String orderNum = preSeed + seedNumSuf;
			
			Map map = new HashMap();
			map.put("orderId", orderNum);
			map.put("createTime", new Date());
			map.put("submitUserId", req.getParameter("userId"));
			map.put("orderAmount", req.getParameter("amount"));
			map.put("orderStatus", 1);
			ssmService.saveBuyData(map);
			/* 把订单信息发送前台处理 */
			List<Map> list = new ArrayList<Map>();
			list.add(map);
			JSONArray jsonArr = JSONArray.fromObject(list);
			PrintWriter writer;
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(jsonArr.toString());
			//更新种子号+1
			ssmService.updateSeedByDate(param);
			
		}else {
			Map map = new HashMap();
			map.put("orderId", preSeed+"0001");
			map.put("createTime", new Date());
			map.put("submitUserId", req.getParameter("userId"));
			map.put("orderAmount", req.getParameter("amount"));
			map.put("orderStatus", 1);
			ssmService.saveBuyData(map);
			//如果没有就是当天第一笔。插入种子表
			param.put("seedNumber", "0001");
			ssmService.saveSeed(param);
			/* 把订单信息发送前台处理 */
			List<Map> list = new ArrayList<Map>();
			list.add(map);
			JSONArray jsonArr = JSONArray.fromObject(list);
			PrintWriter writer;
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(jsonArr.toString());
		}
	}
	
	/* 删除订单信息 */
	@RequestMapping(value="/ssm/delOrdersData")
	@ResponseBody
	public void delOrdersData(HttpServletRequest req,HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", req.getParameter("orderId"));
		ssmService.delOrder(map);
	}
	
	/* 获取提交数据信息 */
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







