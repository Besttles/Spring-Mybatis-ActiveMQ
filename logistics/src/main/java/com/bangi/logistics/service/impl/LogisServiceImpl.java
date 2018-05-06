package com.bangi.logistics.service.impl;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bangi.logistics.dao.LogisDao;
import com.bangi.logistics.service.LogisService;

import net.sf.json.JSONArray;

@Service("logisService")
public class LogisServiceImpl implements LogisService{

	@Autowired
	@Qualifier("logisDao")
	private LogisDao logisDao;

	/* 查询物流订单信息表  */
	public List<Map<String, Object>> findLogisOrder() throws Exception {
		return logisDao.findLogisOrder();
		
	}
	
	/* 保存MQ订单信息 */
	public void saveLogis(Map<String, Object> param) {
		logisDao.saveLogis(param);
	}

	/*根据ID查询物流详情信息*/
	public Map<String,Object> findById(Map<String, Object> param) {
		return logisDao.findById(param);
	}
	
	/*修改物流详情信息*/
	public void findByIdUpdateLogisDep(Map<String,Object> list) {
		logisDao.findByIdUpdateLogisDep(list);
	}
	
	/* 保存物流变化信息 */
	public void saveLogisticsDepict(Map<String, Object> param) {
		logisDao.saveLogisticsDepict(param);
	}

	/* 查看物流变化信息 */
	public List<Map<String,Object>> findByIdLogisData(Map<String, Object> param) {
		return logisDao.findByIdLogisData(param);
	}
}
