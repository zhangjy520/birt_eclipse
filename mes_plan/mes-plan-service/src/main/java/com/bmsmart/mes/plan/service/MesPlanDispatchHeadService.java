/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;

/**
 * 厂级调度单头Service
 * @author 周奇志
 * @version 2017-08-07
 */

public interface MesPlanDispatchHeadService  {

	public MesPlanDispatchHead get(String id);
	public MesPlanDispatchHead getSheet(MesPlanDispatchHead head);
	
	public List<MesPlanDispatchHead> findList(MesPlanDispatchHead mesPlanDispatchHead);
	
	
	public  PageInfo<MesPlanDispatchHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public  PageInfo<MesPlanDispatchHead> findPage(int pageNum, int pageSize,
			MesPlanDispatchHead mesPlanDispatchHead);
	public void save(MesPlanDispatchHead mesPlanDispatchHead);
	public void delete(MesPlanDispatchHead mesPlanDispatchHead);
	public int deleteById(String id);
	public Integer updateDispatchQty(MesPlanDispatchHead mesPlanDispatchHead);
	public MesPlanDispatchHead getByUk(MesPlanDispatchHead head) ;
	public Double findErpSheetQtyTotal(String erpBillNo);
	public int updateSheetStatusById(MesPlanDispatchHead mesPlanDispatchHead);
	public int updateSheetStatusBySheetId(MesPlanDispatchHead mesPlanDispatchHead);
	public int deleteSheet(MesPlanDispatchHead head) throws Exception;
	public MesPlanDispatchHead getSheet(String sheetId);
}