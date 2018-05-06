package com.bangi.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bangi.ssm.dao.SsmDao;
import com.bangi.ssm.service.SsmService;

@Service("ssmService")
public class SsmServiceImpl implements SsmService{

	@Autowired
	@Qualifier("ssmDao")
	private SsmDao ssmDao;
	
	/*查询用户信息*/
	public List<Map> findUsers() {
		return ssmDao.findUsers();
	}

	/*查询商品信息*/
	public List<Map> findOrders() {
		return ssmDao.findOrders();
	}

	/*查询我的订单信息*/
	public List<Map> findMyOrders(Map param) {
		return ssmDao.findMyOrders(param);
	}

	/*购买商品*/
	public void saveBuyData(Map param) {
		ssmDao.saveBuyData(param);
	}

	/*删除订单*/
	public void delOrder(Map param) {
		ssmDao.delOrder(param);
	}
	
	/*根据当天日期查询当天种子号*/
	public List<Map<String,Object>> getSeedByDate(Map<String,Object> param){
		return ssmDao.getSeedByDate(param);
	}
	
	/*当天第一笔订单种子号*/
	public void saveSeed(Map<String,Object> param) {
		ssmDao.saveSeed(param);
	}
	
	/*更新种子号*/
	public void updateSeedByDate(Map<String,Object> param) {
		ssmDao.updateSeedByDate(param);
	}
	
	/* 查看提交MQ数据 */
	public List<Map<String, Object>> findSubMQData(Map param) {
		return ssmDao.findSubMQData(param);
	}
}
