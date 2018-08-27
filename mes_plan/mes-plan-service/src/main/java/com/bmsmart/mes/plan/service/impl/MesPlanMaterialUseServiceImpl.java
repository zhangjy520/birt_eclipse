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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.dao.MesPlanMaterialUseDao;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseService;
/**
 * mes_plan_material_useService
 * @author zhouqizhi
 * @version 2017-09-11
 */
 @Transactional(readOnly = true)
@Service("mesPlanMaterialUseService")
public class MesPlanMaterialUseServiceImpl extends MesCrudService<MesPlanMaterialUseDao, MesPlanMaterialUse> implements MesPlanMaterialUseService{
  @Autowired
 private MesPlanMaterialUseDao mesPlanMaterialUseDao;
	public MesPlanMaterialUse get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanMaterialUse> findList(MesPlanMaterialUse mesPlanMaterialUse) {
		return super.findList(mesPlanMaterialUse);
	}
	
	
	public  PageInfo<MesPlanMaterialUse> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanMaterialUse mesPlanMaterialUse) {
		super.save(mesPlanMaterialUse);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanMaterialUse mesPlanMaterialUse) {
		super.delete(mesPlanMaterialUse);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanMaterialUseDao.deleteById(id);
	}
	
	public MesPlanMaterialUseDao getMesPlanMaterialUseDao() {
	   return mesPlanMaterialUseDao;
    }
    public void setMesPlanMaterialUseDao(MesPlanMaterialUseDao mesPlanMaterialUseDao) {
	    this.mesPlanMaterialUseDao = mesPlanMaterialUseDao;
    }

	@Override
	public PageInfo<MesPlanMaterialUse> findPage(int pageNum, int pageSize, MesPlanMaterialUse msesPlanMaterialUse) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanMaterialUse> useList=dao.findList(msesPlanMaterialUse);
		PageInfo<MesPlanMaterialUse> page = new PageInfo<MesPlanMaterialUse>(useList);
		return page;
	}

	@Override
	public int updateUseQtyByUk(MesPlanMaterialUse mesPlanMaterialUse) {
		Integer rtn = mesPlanMaterialUseDao.updateUseQtyByUk(mesPlanMaterialUse);
		if (rtn == 0){
			mesPlanMaterialUse.preInsert();
			rtn = mesPlanMaterialUseDao.insert(mesPlanMaterialUse);
		}
		return rtn;
	}

	 
}