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
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.dao.MesPlanTempTaskDetDao;
import com.bmsmart.mes.plan.service.MesPlanTempTaskDetService;
/**
 * zhouqzService
 * @author zhouqz
 * @version 2018-03-04
 */
 @Transactional(readOnly = true)
@Service("mesPlanTempTaskDetService")
public class MesPlanTempTaskDetServiceImpl extends MesCrudService<MesPlanTempTaskDetDao, MesPlanTempTaskDet> implements MesPlanTempTaskDetService{
  @Autowired
 private MesPlanTempTaskDetDao mesPlanTempTaskDetDao;
	public MesPlanTempTaskDet get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanTempTaskDet> findList(MesPlanTempTaskDet mesPlanTempTaskDet) {
		return super.findList(mesPlanTempTaskDet);
	}
	
	
	public List<MesPlanTempTaskDet> findAllList(MesPlanTempTaskDet mesPlanTempTaskDet) {
		return mesPlanTempTaskDetDao.findAllList(mesPlanTempTaskDet);
	}
	
	
	public  PageInfo<MesPlanTempTaskDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanTempTaskDet mesPlanTempTaskDet) {
		super.save(mesPlanTempTaskDet);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanTempTaskDet mesPlanTempTaskDet) {
		super.delete(mesPlanTempTaskDet);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanTempTaskDetDao.deleteById(id);
	}
	
	public MesPlanTempTaskDetDao getMesPlanTempTaskDetDao() {
	   return mesPlanTempTaskDetDao;
    }
    public void setMesPlanTempTaskDetDao(MesPlanTempTaskDetDao mesPlanTempTaskDetDao) {
	    this.mesPlanTempTaskDetDao = mesPlanTempTaskDetDao;
    }

	@Override
	public String getMaxBillNo(String srcBillNo) {
		return mesPlanTempTaskDetDao.getMaxBillNo(srcBillNo);
	}

	@Override
	public MesPlanTempTaskDet getByBillNo(String billno) {
		return mesPlanTempTaskDetDao.getByBillNo(billno);
	}

	@Override
	public int checkBatchDet(List<MesPlanTempTaskDet> mesPlanTempTaskDetList, String sheetId) {
		for(MesPlanTempTaskDet det:mesPlanTempTaskDetList){
			mesPlanTempTaskDetDao.updateShiftStatus(det);
		}
		return 1;
	}

	@Override
	public int updateShiftCancelStatus(List<MesPlanTempTaskDet> mesPlanTempTaskDetList, String sheetId) {
		for(MesPlanTempTaskDet det:mesPlanTempTaskDetList){
			mesPlanTempTaskDetDao.updateShiftCancelStatus(det);
		}
		return 1;
	}

	@Override
	public int updateShiftFinishStatus(List<MesPlanTempTaskDet> mesPlanTempTaskDetList, String sheetId) {
		for(MesPlanTempTaskDet det:mesPlanTempTaskDetList){
			mesPlanTempTaskDetDao.updateShiftFinishStatus(det);
		}
		return 1;
	}

	@Override
	public void deleteBySheetId(String sheetId) {
		mesPlanTempTaskDetDao.deleteBySheetId(sheetId);
		
	}
}