package com.bangi.ssm.service;

import java.util.List;
import java.util.Map;

public interface SsmService {

	/*��ѯ�û���Ϣ*/
	List<Map> findUsers();
	
	/*��ѯ��Ʒ��Ϣ*/
	List<Map> findOrders();
	
	/*��ѯ�ҵĶ�����Ϣ*/
	List<Map> findMyOrders(Map param);
	
	/*������Ʒ*/
	void saveBuyData(Map param);
	
	/*ɾ������*/
	void delOrder(Map param);
	
	/*���ݵ������ڲ�ѯ�������Ӻ�*/
	public List<Map<String,Object>> getSeedByDate(Map<String,Object> param);
	
	/*�����һ�ʶ������Ӻ�*/
	public void saveSeed(Map<String,Object> param);
	
	/*�������Ӻ�*/
	public void updateSeedByDate(Map<String,Object> param);
	
	/* �鿴�ύMQ���� */
	public List<Map<String,Object>> findSubMQData(Map param);
}
