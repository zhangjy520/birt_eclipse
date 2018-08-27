/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanWorker2deviceInfo;
import com.bmsmart.mes.plan.dao.MesPlanWorker2deviceInfoDao;
import com.bmsmart.mes.plan.service.MesPlanWorker2deviceInfoService;
/**
 * 工人设备Service
 * @author zhouqizhi
 * @version 2017-10-24
 */
 @Transactional(readOnly = true)
@Service("mesPlanWorker2deviceInfoService")
public class MesPlanWorker2deviceInfoServiceImpl extends MesCrudService<MesPlanWorker2deviceInfoDao, MesPlanWorker2deviceInfo> implements MesPlanWorker2deviceInfoService{
  @Autowired
 private MesPlanWorker2deviceInfoDao mesPlanWorker2deviceInfoDao;
	public MesPlanWorker2deviceInfo get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanWorker2deviceInfo> findList(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo) {
		return super.findList(mesPlanWorker2deviceInfo);
	}
	
	
	public  PageInfo<MesPlanWorker2deviceInfo> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo) {
		int cnt = mesPlanWorker2deviceInfoDao.updateByUk(mesPlanWorker2deviceInfo);
		if (cnt == 0){
			super.save(mesPlanWorker2deviceInfo);
		}
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo) {
		super.delete(mesPlanWorker2deviceInfo);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanWorker2deviceInfoDao.deleteById(id);
	}
	
	public MesPlanWorker2deviceInfoDao getMesPlanWorker2deviceInfoDao() {
	   return mesPlanWorker2deviceInfoDao;
    }
    public void setMesPlanWorker2deviceInfoDao(MesPlanWorker2deviceInfoDao mesPlanWorker2deviceInfoDao) {
	    this.mesPlanWorker2deviceInfoDao = mesPlanWorker2deviceInfoDao;
    }
}