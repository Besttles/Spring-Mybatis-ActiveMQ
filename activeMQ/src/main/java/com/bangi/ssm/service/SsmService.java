package com.bangi.ssm.service;

import java.util.List;
import java.util.Map;

public interface SsmService {

	/*查询用户信息*/
	List<Map> findUsers();
	
	/*查询商品信息*/
	List<Map> findOrders();
	
	/*查询我的订单信息*/
	List<Map> findMyOrders(Map param);
	
	/*购买商品*/
	void saveBuyData(Map param);
	
	/*删除订单*/
	void delOrder(Map param);
	
	/*根据当天日期查询当天种子号*/
	public List<Map<String,Object>> getSeedByDate(Map<String,Object> param);
	
	/*当天第一笔订单种子号*/
	public void saveSeed(Map<String,Object> param);
	
	/*更新种子号*/
	public void updateSeedByDate(Map<String,Object> param);
	
	/* 查看提交MQ数据 */
	public List<Map<String,Object>> findSubMQData(Map param);
}
