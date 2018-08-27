/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;

/**
 * mes_plan_temp_taskService
 * @author zhouqz
 * @version 2017-12-22
 */

public interface MesPlanTempTaskService  {

	public MesPlanTempTask get(String id);
	
	public MesPlanTempTask getSheet(String sheetId);
	
	public List<MesPlanTempTask> findList(MesPlanTempTask mesPlanTempTask);
	
	
	public  PageInfo<MesPlanTempTask> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public  PageInfo<MesPlanTempTask> findPage(int pageNum, int pageSize,	MesPlanTempTask mesPlanTempTask);
	public void save(MesPlanTempTask mesPlanTempTask);
	public void delete(MesPlanTempTask mesPlanTempTask);
	public void deleteById(String id);

	public void checkBatchDet(List<MesPlanTempTask> mesPlanTempTaskList);

	public void cancelBatchDet(List<MesPlanTempTask> mesPlanTempTaskList);

	public void saveBatchDet(Date planBeginDate,Date planEndDate,MesPlanTempTask mesPlanTempTask);

	public void  deleteSheet(String id,String sheetId);
	
}