/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.mesJob.domain.po.JobProcess;
import com.bmsmart.mes.mesJob.domain.po.JobTemplateManage;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;

/**
 * mes月计划查询Service
 * @author 周奇志
 * @version 2017-07-31
 */

public interface MesPlanMonthService  {
	public MesPlanMonth get(String id);
	
	public MesPlanMonth getByUk(MesPlanMonth planPara);
	
	public MesPlanMonth getWholeErpBillNo(String id);
	
	public List<MesPlanMonth> findList(MesPlanMonth mesPlanMonth);
	
	public  PageInfo<MesPlanMonth> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public  PageInfo<MesPlanMonth> findPage(int pageNum, int pageSize,
			MesPlanMonth mesPlanMonth);
	public void save(MesPlanMonth mesPlanMonth);
	public void delete(MesPlanMonth mesPlanMonth);
	public void deleteById(String id);
	public Integer updateDispatchQty(MesPlanMonth mesPlanMonth);
	
	public Integer updateSheetStatusById(MesPlanMonth mesPlanMonth);
	public Integer updateSheetStatusByErpBillNo(MesPlanMonth mesPlanMonth);

	public List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan);
	
	public List<Map<String,Object>> getSubProcessList(MesPlanTechnic mesPlanTechnic);
	public List<MesPlanTechnic> getMesPlanTechnicList(MesPlanMonth mesPlanMonth);
	public List<JobProcess> getMesPlanTechnicWithoutWorktypeList(MesPlanMonth mesPlanMonth);
	
	public List<JobProcess> getArrangeTechnic();
}