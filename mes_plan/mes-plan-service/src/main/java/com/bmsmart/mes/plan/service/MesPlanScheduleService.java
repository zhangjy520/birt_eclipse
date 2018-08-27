/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanScheduleSheet;

/**
 * mes_plan_scheduleService
 * @author zhouqizhi
 * @version 2017-08-29
 */

public interface MesPlanScheduleService  {

	public MesPlanSchedule get(String id);
	public MesPlanSchedule getByUk(MesPlanSchedule mesPlanSchedule);
	public MesPlanScheduleSheet getSheet(String id,String orderBy);
	public List<MesPlanSchedule> findList(MesPlanSchedule mesPlanSchedule);
	public PageInfo<MesPlanSchedule> findPage(int pageNum, int pageSize,Map<String, String> datas);
	public PageInfo<MesPlanSchedule> findPage(int pageNum, int pageSize,MesPlanSchedule mesPlanSchedule);
	public void save(MesPlanSchedule mesPlanSchedule);
	public void delete(MesPlanSchedule mesPlanSchedule);
	public void deleteById(String id);
	public List<MesPlanDevice> findSchedualDeviceList(MesPlanSchedule mesPlanSchedule);
	public List<MesPlanDevice> findUnschedualDeviceList(MesPlanSchedule mesPlanSchedule);
	public Integer saveBatch(MesPlanSchedule mesPlanSchedule,List<MesPlanDevice> mesPlanDeviceList); 
	public Integer checkBatch(MesPlanSchedule mesPlanSchedule,List<MesPlanDevice> mesPlanDeviceList) ;
	public String saveSheet(MesPlanSchedule mesPlanSchedule,List<MesPlanDevice> mesPlanDeviceList); 
	
/*	public List<MesPlanMaterialUse> saveMaterialUse(String id);*/
	public Integer check(String id);
	public int deleteSheet(MesPlanSchedule head);
	public List<MesPlanMaterialUse> saveMaterialUse(MesPlanSchedule mesPlanSchedule,	List<MesPlanDevice> mesPlanDeviceList);
}