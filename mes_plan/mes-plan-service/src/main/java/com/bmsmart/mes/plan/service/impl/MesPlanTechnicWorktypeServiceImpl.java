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

import com.bmsmart.mes.plan.domain.po.MesPlanTechnicWorktype;
import com.bmsmart.mes.plan.dao.MesPlanTechnicWorktypeDao;
import com.bmsmart.mes.plan.service.MesPlanTechnicWorktypeService;
/**
 * 工种Service
 * @author zhouqizhi
 * @version 2017-10-18
 */
 @Transactional(readOnly = true)
@Service("mesPlanTechnicWorktypeService")
public class MesPlanTechnicWorktypeServiceImpl extends MesCrudService<MesPlanTechnicWorktypeDao, MesPlanTechnicWorktype> implements MesPlanTechnicWorktypeService{
  @Autowired
 private MesPlanTechnicWorktypeDao mesPlanTechnicWorktypeDao;
	public MesPlanTechnicWorktype get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanTechnicWorktype> findList(MesPlanTechnicWorktype mesPlanTechnicWorktype) {
		return super.findList(mesPlanTechnicWorktype);
	}
	
	
	public  PageInfo<MesPlanTechnicWorktype> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanTechnicWorktype mesPlanTechnicWorktype) {
		super.save(mesPlanTechnicWorktype);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanTechnicWorktype mesPlanTechnicWorktype) {
		super.delete(mesPlanTechnicWorktype);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanTechnicWorktypeDao.deleteById(id);
	}
	
	public MesPlanTechnicWorktypeDao getMesPlanTechnicWorktypeDao() {
	   return mesPlanTechnicWorktypeDao;
    }
    public void setMesPlanTechnicWorktypeDao(MesPlanTechnicWorktypeDao mesPlanTechnicWorktypeDao) {
	    this.mesPlanTechnicWorktypeDao = mesPlanTechnicWorktypeDao;
    }
}