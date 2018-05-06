package com.bangi.logistics.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.bangi.logistics.service.LogisService;

import net.sf.json.JSONArray;

@Controller
public class LogisController {

	@Resource(name="logisService")
	private LogisService logisService;
	
	/* 返回修改页面 */
	@RequestMapping(value="/logistics/updateLogis")
	public String updateLogis(Map map, HttpServletRequest req) {
		map.put("orderId", req.getParameter("orderId"));
		return "updateLogisPage";
	}
	
	/* 返回物流详情页面 */
	@RequestMapping(value="/logistics/logisticsDepictPage")
	public String logisticsDepictPage(Map<String, Object> map, HttpServletRequest req) {
		map.put("orderId", req.getParameter("orderId"));
		return "logisDepict";
	}
	
	/* 查询物流订单信息 */
	@RequestMapping(value="/logistics/findLogisOrder")
	@ResponseBody
	public void findLogisOrder(HttpServletResponse res) throws Exception {
		List<Map<String, Object>> list = logisService.findLogisOrder();
		JSONArray jsonArr = JSONArray.fromObject(list);
		PrintWriter writer;
		res.setCharacterEncoding("utf-8");
		writer = res.getWriter();
		writer.write(jsonArr.toString());
	}
	
	/* 给修改页面默认信息 */
	@RequestMapping(value="/logistics/findById")
	@ResponseBody
	public void findById(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", req.getParameter("orderId"));
		Map<String,Object> orderDepict = logisService.findById(map);
		JSONArray jsonArr = JSONArray.fromObject(orderDepict);
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(jsonArr.toString());
	}
	
	/* 修改物流详情信息 */
	@RequestMapping(value="/logistics/findByIdUpdateLogisDep")
	public void findByIdUpdateLogisDep(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", req.getParameter("orderId"));
		map.put("orderDepict", req.getParameter("orderDepict"));
		logisService.findByIdUpdateLogisDep(map);
	}
	
	/* 保存物流变化信息 */
	@RequestMapping(value="/logistics/saveLogisticsDepict")
	public void saveLogisticsDepict(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", req.getParameter("logisticsId"));
		map.put("orderDepict", req.getParameter("logisticsDepict"));
		map.put("createTime", new Date());
		logisService.saveLogisticsDepict(map);
	}
	
	/* 查看物流信息 */
	@RequestMapping(value="/logistics/findByIdLogisData")
	public void findByIdLogisData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logisticsId", req.getParameter("logisticsId"));
		List<Map<String, Object>> list = logisService.findByIdLogisData(map);
		JSONArray jsonarr = JSONArray.fromObject(list);
		res.setCharacterEncoding("utf-8");
		PrintWriter writer = res.getWriter();
		writer.write(jsonarr.toString());
	}
}
