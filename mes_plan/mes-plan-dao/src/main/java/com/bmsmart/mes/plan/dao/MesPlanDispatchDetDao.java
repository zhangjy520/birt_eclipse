/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.CrudDao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;

/**
 * 厂级调度明细DAO接口
 * @author 周奇志
 * @version 2017-08-07
 */
@MyBatisRepository
public interface MesPlanDispatchDetDao extends MesCrudDao<MesPlanDispatchDet> {
	//public void deleteById(String id);
	
	public String getMaxBillNo(String srcBillNo);
	
	public Double findSheetQtyTotal(String sheetId);
	public MesPlanDispatchDet getByBillNo(String billNo);
	public int updateWorkCenterQty(MesPlanDispatchDet mesPlanDispatchDet);
	//public int updateFinishQty(MesPlanDispatchDet mesPlanDispatchDet);
	
	public int updateDispatchStatusById(MesPlanDispatchDet mesPlanDispatchDet);
	public int updateDispatchStatusBySheetId(MesPlanDispatchDet mesPlanDispatchDet);
	public int deleteBySheetId(String sheetId);
	
	public int updateFinishQtyByBillNo(MesPlanDispatchDet mesPlanDispatchDet);
	
//	public MesPlanMonth getMesPlanMonthByDispatchId(String id);
//	public MesPlanMonth getMesPlanMonthByBillNo(String billNo);
	
}