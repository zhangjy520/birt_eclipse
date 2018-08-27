/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;

/**
 * mes_plan_shift_userService
 * @author zhouqz
 * @version 2017-09-04
 */

public interface MesPlanShiftUserService  {

	public MesPlanShiftUser get(String id);
	
	public List<MesPlanShiftUser> findList(MesPlanShiftUser mesPlanShiftUser);
	
	public List<MesPlanShiftUser> getEmptyShiftUserList(String workshopId,MesPlanDevice mesPlanDevice,MesPlanShiftDet mesPlanShiftDet);
	
	public  PageInfo<MesPlanShiftUser> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanShiftUser mesPlanShiftUser);
	public void delete(MesPlanShiftUser mesPlanShiftUser);
	public void deleteById(String id);
	public List<MesPlanShiftUser> getUserTaskSimpleList(MesPlanShiftUser mesPlanShiftUser);
	
	
}