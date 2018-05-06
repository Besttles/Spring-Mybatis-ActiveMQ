package com.bangi.logistics.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogisService {

	/* 查询物流订单信息表 */
	List<Map<String, Object>> findLogisOrder() throws Exception;
	
	/* 保存MQ订单信息 */
	void saveLogis(Map<String, Object> param);
	
	/*根据ID查询物流详情信息*/
	Map<String,Object> findById(Map<String, Object> param);
	
	/*修改物流详情信息*/
	void findByIdUpdateLogisDep(Map<String,Object> list);
	
	/* 保存物流变化信息 */
	void saveLogisticsDepict(Map<String,Object> param);
	
	/* 查看物流变化信息 */
	List<Map<String,Object>> findByIdLogisData(Map<String,Object> param);
}
