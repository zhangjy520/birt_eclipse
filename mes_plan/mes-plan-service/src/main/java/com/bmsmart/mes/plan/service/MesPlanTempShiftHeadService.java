/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftWorker;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;

/**
 * 排班表头Service
 * @author zhouqz
 * @version 2017-09-04
 */

public interface MesPlanTempShiftHeadService  {

	public MesPlanTempShiftHead getHead(String id);
	public MesPlanTempShiftHead getHeadBySheetId(String sheetId);
	public MesPlanTempShiftHead getHeadByUk(MesPlanTempShiftHead mesPlanTempShiftHead);
	public MesPlanTempShiftSheet getSheetById(String id);
	public MesPlanTempShiftSheet getSheetBySheetId(String sheetId);
	
	public List<MesPlanTempShiftHead> findList(MesPlanTempShiftHead mesPlanTempShiftHead);
	public  PageInfo<MesPlanTempShiftHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	
	public  PageInfo<MesPlanTempShiftHead> findPage(int pageNum, int pageSize,
			MesPlanTempShiftHead mesPlanTempShiftHead);
	
	public void save(MesPlanTempShiftHead mesPlanTempShiftHead);
	public void delete(MesPlanTempShiftHead mesPlanTempShiftHead);
	public void deleteById(String id);

//	public String saveSheet(MesPlanShiftHead mesPlanShiftHead, List<MesPlanShiftDet> mesPlanShiftDetList,List<MesPlanShiftWorker> mesPlanShiftWorkerList);
//	public int updateSheetStatus(MesPlanShiftHead mesPlanShiftHead);
	public MesPlanTempShiftSheet getSheetByUk(MesPlanTempShiftHead mesPlanTempShiftHead);
	public String saveSheet(MesPlanTempShiftHead mesPlanTempShiftHead,List<MesPlanTempShiftUser> mesPlanTempShiftUserList);
}