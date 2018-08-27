/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;

/**
 * mes_plan_shift_userService
 * @author zhouqz
 * @version 2017-09-04
 */

public interface MesPlanTempShiftUserService  {

	public MesPlanTempShiftUser get(String id);
	
	public List<MesPlanTempShiftUser> findList(MesPlanTempShiftUser mesPlanTempShiftUser);
	
	public  PageInfo<MesPlanTempShiftUser> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanTempShiftUser mesPlanTempShiftUser);
	public void delete(MesPlanTempShiftUser mesPlanTempShiftUser);
	public void deleteById(String id);
	public List<MesPlanTempShiftUser> getUserTaskSimpleList(MesPlanTempShiftUser mesPlanShiftUser);

	public List<MesPlanTempShiftUser> getEmptyShiftUserList(String workshopId, MesPlanTempTaskDet mesPlanDevice);
	
	
}