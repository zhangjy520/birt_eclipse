/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;

/**
 * zhouqzService
 * @author zhouqz
 * @version 2018-03-04
 */

public interface MesPlanTempTaskDetService  {

	public MesPlanTempTaskDet get(String id);
	
	
	
	public List<MesPlanTempTaskDet> findList(MesPlanTempTaskDet mesPlanTempTaskDet);
	public List<MesPlanTempTaskDet> findAllList(MesPlanTempTaskDet mesPlanTempTaskDet);
	
	public  PageInfo<MesPlanTempTaskDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanTempTaskDet mesPlanTempTaskDet);
	public void delete(MesPlanTempTaskDet mesPlanTempTaskDet);
	public void deleteById(String id);

	public String getMaxBillNo(String srcBillNo);

	public MesPlanTempTaskDet getByBillNo(String billno);

	public int checkBatchDet(List<MesPlanTempTaskDet> mesPlanTempTaskDetList, String sheetId);

	public int updateShiftCancelStatus(List<MesPlanTempTaskDet> mesPlanTempTaskDetList, String sheetId);

	public int updateShiftFinishStatus(List<MesPlanTempTaskDet> mesPlanShiftDetList, String sheetId);



	public void deleteBySheetId(String sheetId);
}