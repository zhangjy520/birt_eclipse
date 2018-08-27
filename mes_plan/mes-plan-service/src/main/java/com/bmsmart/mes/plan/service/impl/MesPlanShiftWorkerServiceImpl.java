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

import com.bmsmart.mes.plan.domain.po.MesPlanShiftWorker;
import com.bmsmart.mes.plan.dao.MesPlanShiftWorkerDao;
import com.bmsmart.mes.plan.service.MesPlanShiftWorkerService;
/**
 * testService
 * @author zhouqizhi
 * @version 2017-10-18
 */
 @Transactional(readOnly = true)
@Service("mesPlanShiftWorkerService")
public class MesPlanShiftWorkerServiceImpl extends MesCrudService<MesPlanShiftWorkerDao, MesPlanShiftWorker> implements MesPlanShiftWorkerService{
  @Autowired
 private MesPlanShiftWorkerDao mesPlanShiftWorkerDao;
	public MesPlanShiftWorker get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanShiftWorker> findList(MesPlanShiftWorker mesPlanShiftWorker) {
		return super.findList(mesPlanShiftWorker);
	}
	
	
	public  PageInfo<MesPlanShiftWorker> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanShiftWorker mesPlanShiftWorker) {
		super.save(mesPlanShiftWorker);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanShiftWorker mesPlanShiftWorker) {
		super.delete(mesPlanShiftWorker);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanShiftWorkerDao.deleteById(id);
	}
	
	public MesPlanShiftWorkerDao getMesPlanShiftWorkerDao() {
	   return mesPlanShiftWorkerDao;
    }
    public void setMesPlanShiftWorkerDao(MesPlanShiftWorkerDao mesPlanShiftWorkerDao) {
	    this.mesPlanShiftWorkerDao = mesPlanShiftWorkerDao;
    }
}