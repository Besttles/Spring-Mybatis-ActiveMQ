package com.bangi.logistics.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bangi.logistics.dao.LogisDao;
import com.bangi.logistics.mapper.LogisMapper;

@Component("logisDao")
public class LogisDaoImpl implements LogisDao {

	@Autowired
	private LogisMapper logisMapper;

	/* 查询物流订单信息表 */
	public List<Map<String, Object>> findLogisOrder() {
		return logisMapper.findLogisOrder();
	}

	/* 保存MQ订单信息 */
	public void saveLogis(Map<String, Object> param) {
		logisMapper.saveLogis(param);
	}

	/*根据ID查询物流详情信息*/
	public Map<String,Object> findById(Map<String, Object> param) {
		return logisMapper.findById(param);
	}

	/*修改物流详情信息*/
	public void findByIdUpdateLogisDep(Map<String,Object> list) {
		logisMapper.findByIdUpdateLogisDep(list);
	}

	/* 保存物流变化信息 */
	public void saveLogisticsDepict(Map<String, Object> param) {
		logisMapper.saveLogisticsDepict(param);
	}
	
	/* 查看物流变化信息 */
	public List<Map<String,Object>> findByIdLogisData(Map<String, Object> param) {
		return logisMapper.findByIdLogisData(param);
	}
}
