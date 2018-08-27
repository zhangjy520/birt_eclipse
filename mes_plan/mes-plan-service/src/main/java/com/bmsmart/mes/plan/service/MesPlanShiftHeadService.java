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
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftWorker;

/**
 * 排班表头Service
 * @author zhouqz
 * @version 2017-09-04
 */

public interface MesPlanShiftHeadService  {

	public MesPlanShiftHead getHead(String id);
	public MesPlanShiftHead getHeadBySheetId(String sheetId);
	public MesPlanShiftHead getHeadByUk(MesPlanShiftHead mesPlanShiftHead);
	public MesPlanShiftSheet getSheetById(String id);
	public MesPlanShiftSheet getSheetBySheetId(String sheetId);
	
	public List<MesPlanShiftHead> findList(MesPlanShiftHead mesPlanShiftHead);
	public  PageInfo<MesPlanShiftHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	
	public  PageInfo<MesPlanShiftHead> findPage(int pageNum, int pageSize,
			MesPlanShiftHead mesPlanShiftHead);
	
	public void save(MesPlanShiftHead mesPlanShiftHead);
	public void delete(MesPlanShiftHead mesPlanShiftHead);
	public void deleteById(String id);
	public MesPlanShiftSheet getSheetByUk(MesPlanShiftHead headPara);
	public String saveSheet(MesPlanShiftHead mesPlanShiftHead, List<MesPlanShiftDet> mesPlanShiftDetList,List<MesPlanShiftWorker> mesPlanShiftWorkerList);
	public int updateSheetStatus(MesPlanShiftHead mesPlanShiftHead);
}