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

import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseDet;
import com.bmsmart.mes.plan.dao.MesPlanMaterialUseDetDao;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseDetService;
/**
 * mes_plan_material_use_detService
 * @author zhouqizhi
 * @version 2017-09-11
 */
 @Transactional(readOnly = true)
@Service("mesPlanMaterialUseDetService")
public class MesPlanMaterialUseDetServiceImpl extends MesCrudService<MesPlanMaterialUseDetDao, MesPlanMaterialUseDet> implements MesPlanMaterialUseDetService{
  @Autowired
 private MesPlanMaterialUseDetDao mesPlanMaterialUseDetDao;
	public MesPlanMaterialUseDet get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanMaterialUseDet> findList(MesPlanMaterialUseDet mesPlanMaterialUseDet) {
		return super.findList(mesPlanMaterialUseDet);
	}
	
	
	public List<MesPlanMaterialUseDet> findAllList(MesPlanMaterialUseDet mesPlanMaterialUseDet) {
		return mesPlanMaterialUseDetDao.findAllList(mesPlanMaterialUseDet);
	}
	
	
	
	public  PageInfo<MesPlanMaterialUseDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanMaterialUseDet mesPlanMaterialUseDet) {
		super.save(mesPlanMaterialUseDet);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanMaterialUseDet mesPlanMaterialUseDet) {
		super.delete(mesPlanMaterialUseDet);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanMaterialUseDetDao.deleteById(id);
	}
	
	public MesPlanMaterialUseDetDao getMesPlanMaterialUseDetDao() {
	   return mesPlanMaterialUseDetDao;
    }
    public void setMesPlanMaterialUseDetDao(MesPlanMaterialUseDetDao mesPlanMaterialUseDetDao) {
	    this.mesPlanMaterialUseDetDao = mesPlanMaterialUseDetDao;
    }

	@Override
	public List<MesPlanMaterialUseDet> findALLList(MesPlanMaterialUseDet mesPlanMaterialUseDet) {
	 
		return mesPlanMaterialUseDetDao.findAllList(mesPlanMaterialUseDet);
	}
}