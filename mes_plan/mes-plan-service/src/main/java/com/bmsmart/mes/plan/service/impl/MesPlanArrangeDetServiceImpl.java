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

import com.bmsmart.mes.plan.domain.po.MesPlanArrangeDet;
import com.bmsmart.mes.plan.dao.MesPlanArrangeDetDao;
import com.bmsmart.mes.plan.service.MesPlanArrangeDetService;
/**
 * mesPlanArrangeDetService
 * @author zhouqz
 * @version 2018-03-13
 */
 @Transactional(readOnly = true)
@Service("mesPlanArrangeDetService")
public class MesPlanArrangeDetServiceImpl extends MesCrudService<MesPlanArrangeDetDao, MesPlanArrangeDet> implements MesPlanArrangeDetService{
  @Autowired
 private MesPlanArrangeDetDao mesPlanArrangeDetDao;
	public MesPlanArrangeDet get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanArrangeDet> findList(MesPlanArrangeDet mesPlanArrangeDet) {
		return super.findList(mesPlanArrangeDet);
	}
	
	
	public  PageInfo<MesPlanArrangeDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanArrangeDet mesPlanArrangeDet) {
		super.save(mesPlanArrangeDet);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanArrangeDet mesPlanArrangeDet) {
		super.delete(mesPlanArrangeDet);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanArrangeDetDao.deleteById(id);
	}
	
	public MesPlanArrangeDetDao getMesPlanArrangeDetDao() {
	   return mesPlanArrangeDetDao;
    }
    public void setMesPlanArrangeDetDao(MesPlanArrangeDetDao mesPlanArrangeDetDao) {
	    this.mesPlanArrangeDetDao = mesPlanArrangeDetDao;
    }

	@Override
	public void deleteBySheetId(String sheetId) {
		mesPlanArrangeDetDao.deleteBySheetId(sheetId);
	}

	@Override
	public int updateSheetStatus2SheetId(MesPlanArrangeDet mesPlanArrangeDet) {
		// TODO Auto-generated method stub
		return mesPlanArrangeDetDao.updateSheetStatus2SheetId(mesPlanArrangeDet);
	}
}