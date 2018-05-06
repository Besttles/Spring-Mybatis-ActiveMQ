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
	
	/*��ѯ�û���Ϣ*/
	public List<Map> findUsers() {
		return ssmDao.findUsers();
	}

	/*��ѯ��Ʒ��Ϣ*/
	public List<Map> findOrders() {
		return ssmDao.findOrders();
	}

	/*��ѯ�ҵĶ�����Ϣ*/
	public List<Map> findMyOrders(Map param) {
		return ssmDao.findMyOrders(param);
	}

	/*������Ʒ*/
	public void saveBuyData(Map param) {
		ssmDao.saveBuyData(param);
	}

	/*ɾ������*/
	public void delOrder(Map param) {
		ssmDao.delOrder(param);
	}
	
	/*���ݵ������ڲ�ѯ�������Ӻ�*/
	public List<Map<String,Object>> getSeedByDate(Map<String,Object> param){
		return ssmDao.getSeedByDate(param);
	}
	
	/*�����һ�ʶ������Ӻ�*/
	public void saveSeed(Map<String,Object> param) {
		ssmDao.saveSeed(param);
	}
	
	/*�������Ӻ�*/
	public void updateSeedByDate(Map<String,Object> param) {
		ssmDao.updateSeedByDate(param);
	}
	
	/* �鿴�ύMQ���� */
	public List<Map<String, Object>> findSubMQData(Map param) {
		return ssmDao.findSubMQData(param);
	}
}
