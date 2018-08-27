/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.dao.MesPlanTempTaskDao;
import com.bmsmart.mes.plan.dao.MesPlanTempTaskDetDao;
import com.bmsmart.mes.plan.service.MesPlanTempTaskDetService;
import com.bmsmart.mes.plan.service.MesPlanTempTaskService;
/**
 * mes_plan_temp_taskService
 * @author zhouqz
 * @version 2017-12-22
 */
 @Transactional(readOnly = true)
@Service("mesPlanTempTaskService")
public class MesPlanTempTaskServiceImpl extends MesCrudService<MesPlanTempTaskDao, MesPlanTempTask> implements MesPlanTempTaskService{
  @Autowired
 private MesPlanTempTaskDao mesPlanTempTaskDao;
  
  @Autowired
 private MesPlanTempTaskDetService mesPlanTempTaskDetService;
  
  
	public MesPlanTempTask get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanTempTask> findList(MesPlanTempTask mesPlanTempTask) {
		return super.findList(mesPlanTempTask);
	}
	
	
	public  PageInfo<MesPlanTempTask> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanTempTask mesPlanTempTask) {
		if (StringUtils.isBlank(mesPlanTempTask.getId()) && StringUtils.isBlank(mesPlanTempTask.getSheetId())){
			mesPlanTempTask.setSheetId( getNewSheetId());
		}
		super.save(mesPlanTempTask);
	}
	
	
	public synchronized String getNewSheetId(){
		Random r = new Random();
		return "TP"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}
	
	@Transactional(readOnly = false)
	public void delete(MesPlanTempTask mesPlanTempTask) {
		super.delete(mesPlanTempTask);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanTempTaskDao.deleteById(id);
	}
	
	public MesPlanTempTaskDao getMesPlanTempTaskDao() {
	   return mesPlanTempTaskDao;
    }
    public void setMesPlanTempTaskDao(MesPlanTempTaskDao mesPlanTempTaskDao) {
	    this.mesPlanTempTaskDao = mesPlanTempTaskDao;
    }

	@Override
	public PageInfo<MesPlanTempTask> findPage(int pageNum, int pageSize, MesPlanTempTask mesPlanTempTask) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanTempTask> demoTestList = mesPlanTempTaskDao.findList(mesPlanTempTask);
		PageInfo<MesPlanTempTask> page = new PageInfo<MesPlanTempTask>(demoTestList);
		return page;
	}

	@Override
	public void checkBatchDet(List<MesPlanTempTask> mesPlanTempTaskList) {
		for(MesPlanTempTask det:mesPlanTempTaskList){
			mesPlanTempTaskDao.updateCheckTaskStatus(det);
		}		
	}

	@Override
	public void cancelBatchDet(List<MesPlanTempTask> mesPlanTempTaskList) {
		for(MesPlanTempTask det:mesPlanTempTaskList){
			mesPlanTempTaskDao.updateCancelTaskStatus(det);
		}
		
	}

	@Override
	public void saveBatchDet(Date planBeginDate,Date planEndDate,MesPlanTempTask mesPlanTempTask) {
		if (mesPlanTempTask != null){
			mesPlanTempTask.setPlanBeginDate(planBeginDate);
			mesPlanTempTask.setPlanEndDate(planEndDate);
			save(mesPlanTempTask);
			if (mesPlanTempTask.getMesPlanTempTaskDetList() != null)
			for (MesPlanTempTaskDet task:mesPlanTempTask.getMesPlanTempTaskDetList()){
				if (StringUtils.isBlank(task.getSheetId())){
					task.setSheetId(mesPlanTempTask.getSheetId());
				}
				//检查是插入还是新增，新增生成billNo
				if (StringUtils.isBlank(task.getId())){
					task.setBillNo(getNewBillNo(mesPlanTempTask.getPlanSheetId()));
				}
				mesPlanTempTaskDetService.save(task);
				
			}
		}
	}
	
	public String getNewBillNo(String erpBillNo){
		Integer index =0 ;
		String indexStr = "";
		String billno = "";
		MesPlanTempTaskDet det = null;
		
		String maxBillNo = mesPlanTempTaskDetService.getMaxBillNo(erpBillNo+"-T-");
		if (StringUtils.isNotEmpty(maxBillNo)){
			Integer index2 = maxBillNo.lastIndexOf("-");
			if (index2>0){
				index = Integer.parseInt(maxBillNo.substring(index2+1));
			}
		}
		
		do{
			index++;
			indexStr = index<=9?"0"+index.toString():index.toString();
			billno = (erpBillNo+"-T-")+indexStr;
			det= mesPlanTempTaskDetService.getByBillNo(billno);
		}while(det != null && StringUtil.isNotBlank( det.getBillNo()));
		
		return billno;
	}

	@Override
	public MesPlanTempTask getSheet(String sheetId) {
		MesPlanTempTask rtnTask = null;
		if (StringUtils.isBlank(sheetId) ){
			return rtnTask;
		}
		MesPlanTempTask taskpara = new MesPlanTempTask();
		taskpara.setSheetId(sheetId);
		List<MesPlanTempTask> taskList =  findList(taskpara);
		if (taskList != null && taskList.size()>0){
			rtnTask = taskList.get(0);
			MesPlanTempTaskDet detpara = new MesPlanTempTaskDet();
			detpara.setSheetId(sheetId);
			List<MesPlanTempTaskDet>  detList =  mesPlanTempTaskDetService.findList(detpara);
			rtnTask.setMesPlanTempTaskDetList(detList);
		}
		return rtnTask;
	}

	@Override
	public void deleteSheet(String id,String sheetId) {
		mesPlanTempTaskDao.deleteById(id);
		mesPlanTempTaskDetService.deleteBySheetId(sheetId);
	}
}