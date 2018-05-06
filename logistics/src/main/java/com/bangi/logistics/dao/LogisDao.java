package com.bangi.logistics.dao;

import java.util.List;
import java.util.Map;

public interface LogisDao {

	/* 查询物流订单信息表 */
	List<Map<String,Object>> findLogisOrder();
	
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
