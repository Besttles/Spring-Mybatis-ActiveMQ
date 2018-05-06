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
	
	/*��ѯ�û���Ϣ*/
	public List<Map> findUsers() {
		return ssmMapper.findUsers();
	}
	
	/*��ѯ��Ʒ��Ϣ*/
	public List<Map> findOrders() {
		return ssmMapper.findOrders();
	}

	/*��ѯ�ҵĶ�����Ϣ*/
	public List<Map> findMyOrders(Map param) {
		return ssmMapper.findMyOrders(param);
	}

	/*������Ʒ*/
	public void saveBuyData(Map param) {
		ssmMapper.saveBuyData(param);
	}

	/*ɾ������*/
	public void delOrder(Map param) {
		ssmMapper.delOrder(param);
	}
	
	/*���ݵ������ڲ�ѯ�������Ӻ�*/
	public List<Map<String,Object>> getSeedByDate(Map<String,Object> param){
		return ssmMapper.getSeedByDate(param);
	}
	
	/*�����һ�ʶ������Ӻ�*/
	public void saveSeed(Map<String,Object> param) {
		ssmMapper.saveSeed(param);
	}
	
	/*�������Ӻ�*/
	public void updateSeedByDate(Map<String,Object> param) {
		ssmMapper.updateSeedByDate(param);
	}
	
	/* �鿴�ύMQ���� */
	public List<Map<String, Object>> findSubMQData(Map param) {
		return ssmMapper.findSubMQData(param);
	}
}
