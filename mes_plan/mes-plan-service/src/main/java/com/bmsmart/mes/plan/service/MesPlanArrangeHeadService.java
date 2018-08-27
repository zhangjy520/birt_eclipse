/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanArrangeHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftSheet;

/**
 * mes_plan_arrange_headService
 * @author zhouqz
 * @version 2018-03-13
 */

public interface MesPlanArrangeHeadService  {

	public MesPlanArrangeHead get(String id);
	
	public List<MesPlanArrangeHead> findList(MesPlanArrangeHead mesPlanArrangeHead);
	
	
	public  PageInfo<MesPlanArrangeHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanArrangeHead mesPlanArrangeHead);
	public void delete(MesPlanArrangeHead mesPlanArrangeHead);
	public void deleteById(String id);

	public PageInfo<MesPlanArrangeHead> findPage(int pageno, int pagesize, MesPlanArrangeHead mesPlanArrangeHead);

	public MesPlanArrangeHead getSheetById(String id);

	public MesPlanArrangeHead getSheetBySheetId(String sheetId);

	public MesPlanArrangeHead getSheetByUk(MesPlanTempShiftHead mesPlanTempShiftHead);

	public void saveBatchDet(Date shiftDate, MesPlanArrangeHead mesPlanArrangeHead);

	public void deleteSheet(String id, String sheetId);
	
	public String save_chk_det(String detId) throws Exception;
	
}