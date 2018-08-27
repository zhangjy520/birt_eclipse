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

import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;
import com.bmsmart.mes.plan.dao.MesPlanTechnicDao;
import com.bmsmart.mes.plan.service.MesPlanTechnicService;
/**
 * 订单工序Service
 * @author zhouqizhi
 * @version 2017-11-22
 */
 @Transactional(readOnly = true)
@Service("mesPlanTechnicService")
public class MesPlanTechnicServiceImpl extends MesCrudService<MesPlanTechnicDao, MesPlanTechnic> implements MesPlanTechnicService{
  @Autowired
 private MesPlanTechnicDao mesPlanTechnicDao;
	public MesPlanTechnic get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanTechnic> findList(MesPlanTechnic mesPlanTechnic) {
		return super.findList(mesPlanTechnic);
	}
	
	
	public  PageInfo<MesPlanTechnic> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanTechnic mesPlanTechnic) {
		super.save(mesPlanTechnic);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanTechnic mesPlanTechnic) {
		super.delete(mesPlanTechnic);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanTechnicDao.deleteById(id);
	}
	
	public MesPlanTechnicDao getMesPlanTechnicDao() {
	   return mesPlanTechnicDao;
    }
    public void setMesPlanTechnicDao(MesPlanTechnicDao mesPlanTechnicDao) {
	    this.mesPlanTechnicDao = mesPlanTechnicDao;
    }
}