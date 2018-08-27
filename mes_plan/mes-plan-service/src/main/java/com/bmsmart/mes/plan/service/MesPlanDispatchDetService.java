/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;

/**
 * 厂级调度明细Service
 * @author 周奇志
 * @version 2017-08-07
 */

public interface MesPlanDispatchDetService  {

	public MesPlanDispatchDet get(String id);
	
	public MesPlanDispatchDet getByBillNo(String billNo);
	//public MesPlanMonth getMesPlanMonthByDispatchId(String id);
	//public MesPlanMonth getMesPlanMonthByBillNo(String billNo);
	public List<MesPlanDispatchDet> findList(MesPlanDispatchDet mesPlanDispatchDet);
	
	
	public  PageInfo<MesPlanDispatchDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public  PageInfo<MesPlanDispatchDet> findPage(int pageNum, int pageSize,
			MesPlanDispatchDet mesPlanDispatchDet);
	
 
	public void save(MesPlanDispatchDet mesPlanDispatchDet);
	public void delete(MesPlanDispatchDet mesPlanDispatchDet);
	public void deleteById(String id);
	
	public double findSheetQtyTotal(String sheetId);
 
	/**
	 * 修改单据头分发的数量
	 * @param retDet
	 */
	public Integer updateSheetDispatchQty(MesPlanDispatchDet retDet);
	public Integer updateErpSheetDispatchQty(MesPlanDispatchHead mesPlanDispatchHead,MesPlanDispatchDet mesPlanDispatchDet)  throws Exception ;
	
	
	public Integer updateWorkCenterQty(MesPlanDispatchDet mesPlanDispatchDet);
	public Integer updateFinishQty(MesPlanDispatchDet mesPlanDispatchDet);
	public MesPlanDispatchHead saveDet(MesPlanDispatchDet mesPlanDispatchDet, String sheetId, String erpId,String remarks)	throws Exception;
	
	public int updateDispatchStatusById(MesPlanDispatchDet mesPlanDispatchDet);
	public int updateDispatchStatusBySheetId(MesPlanDispatchDet mesPlanDispatchDet);

	public int deleteBySheetId(String sheetId);

	public void checkBatchDet(List<MesPlanDispatchDet> mesPlanDispatchDetList);
			
	
}