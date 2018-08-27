/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanArrangeDet;

/**
 * mesPlanArrangeDetService
 * @author zhouqz
 * @version 2018-03-13
 */

public interface MesPlanArrangeDetService  {

	public MesPlanArrangeDet get(String id);
	
	public List<MesPlanArrangeDet> findList(MesPlanArrangeDet mesPlanArrangeDet);
	
	
	public  PageInfo<MesPlanArrangeDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanArrangeDet mesPlanArrangeDet);
	public void delete(MesPlanArrangeDet mesPlanArrangeDet);
	public void deleteById(String id);

	public void deleteBySheetId(String sheetId);
	public int updateSheetStatus2SheetId(MesPlanArrangeDet mesPlanArrangeDet);
}