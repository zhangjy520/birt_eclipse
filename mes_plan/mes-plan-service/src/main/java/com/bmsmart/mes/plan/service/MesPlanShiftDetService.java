/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;

/**
 * mes_plan_shift_detService
 * @author zhouqz
 * @version 2017-09-04
 */

public interface MesPlanShiftDetService  {

	public MesPlanShiftDet get(String id);
	
	public MesPlanShiftDet getbyBillNo(String billNo);
	
	public List<MesPlanShiftDet> findList(MesPlanShiftDet mesPlanShiftDet);
	public List<MesPlanShiftDet> findALLList(MesPlanShiftDet mesPlanShiftDet);
	public  PageInfo<MesPlanShiftDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanShiftDet mesPlanShiftDet);
	public void delete(MesPlanShiftDet mesPlanShiftDet);
	public void deleteById(String id);
	
	public int updateShiftQtyById(MesPlanShiftDet mesPlanShiftDet);

	public List<MesPlanShiftDet> getEmptyShift(MesPlanDevice mesPlanDevice);

	public MesPlanShiftDet getEmptyShiftOne(MesPlanShiftHead mesPlanShiftHead, MesPlanDevice mesPlanDevice);

	public int checkBatchDet(List<MesPlanShiftDet> mesPlanShiftDetList, String sheetId);
	public int updateShiftFinishStatus(List<MesPlanShiftDet> mesPlanShiftDetList, String sheetId);

	int updateShiftCancelStatus(List<MesPlanShiftDet> mesPlanShiftDetList, String sheetId);
	
	/**
	 * 计划单是否能够完工
	 * @param level  1=订单是否能完工,2=调度通知单是否能完工,3=车间调度是否能完工，4=班次调度单是否能完工
	 * @param srcBillNo
	 * @return
	 */
	int validateCanFinish(int level,String billNo);
	
	Map<String,Object> getErpBillDetail(String erpBillNo);
}