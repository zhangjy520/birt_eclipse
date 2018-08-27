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

import com.bmsmart.mes.plan.domain.po.MesPlanAssignment;
import com.bmsmart.mes.plan.dao.MesPlanAssignmentDao;
import com.bmsmart.mes.plan.service.MesPlanAssignmentService;
/**
 * 任务书Service
 * @author zhouqizhi
 * @version 2017-11-22
 */
 @Transactional(readOnly = true)
@Service("mesPlanAssignmentService")
public class MesPlanAssignmentServiceImpl extends MesCrudService<MesPlanAssignmentDao, MesPlanAssignment> implements MesPlanAssignmentService{
  @Autowired
 private MesPlanAssignmentDao mesPlanAssignmentDao;
	public MesPlanAssignment get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanAssignment> findList(MesPlanAssignment mesPlanAssignment) {
		return super.findList(mesPlanAssignment);
	}
	
	
	public  PageInfo<MesPlanAssignment> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanAssignment mesPlanAssignment) {
		super.save(mesPlanAssignment);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanAssignment mesPlanAssignment) {
		super.delete(mesPlanAssignment);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanAssignmentDao.deleteById(id);
	}
	
	public MesPlanAssignmentDao getMesPlanAssignmentDao() {
	   return mesPlanAssignmentDao;
    }
    public void setMesPlanAssignmentDao(MesPlanAssignmentDao mesPlanAssignmentDao) {
	    this.mesPlanAssignmentDao = mesPlanAssignmentDao;
    }
}