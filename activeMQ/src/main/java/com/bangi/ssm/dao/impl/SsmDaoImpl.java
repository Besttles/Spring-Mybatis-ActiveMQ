package com.bangi.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bangi.ssm.dao.SsmDao;
import com.bangi.ssm.mapper.SsmMapper;

@Component("ssmDao")
public class SsmDaoImpl implements SsmDao{

	@Autowired
	private SsmMapper ssmMapper;
	
	/*查询用户信息*/
	public List<Map> findUsers() {
		return ssmMapper.findUsers();
	}
	
	/*查询商品信息*/
	public List<Map> findOrders() {
		return ssmMapper.findOrders();
	}

	/*查询我的订单信息*/
	public List<Map> findMyOrders(Map param) {
		return ssmMapper.findMyOrders(param);
	}

	/*购买商品*/
	public void saveBuyData(Map param) {
		ssmMapper.saveBuyData(param);
	}

	/*删除订单*/
	public void delOrder(Map param) {
		ssmMapper.delOrder(param);
	}
	
	/*根据当天日期查询当天种子号*/
	public List<Map<String,Object>> getSeedByDate(Map<String,Object> param){
		return ssmMapper.getSeedByDate(param);
	}
	
	/*当天第一笔订单种子号*/
	public void saveSeed(Map<String,Object> param) {
		ssmMapper.saveSeed(param);
	}
	
	/*更新种子号*/
	public void updateSeedByDate(Map<String,Object> param) {
		ssmMapper.updateSeedByDate(param);
	}
	
	/* 查看提交MQ数据 */
	public List<Map<String, Object>> findSubMQData(Map param) {
		return ssmMapper.findSubMQData(param);
	}
}
