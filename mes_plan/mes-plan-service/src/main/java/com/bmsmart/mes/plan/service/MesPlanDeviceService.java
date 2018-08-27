/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;

/**
 * 分解设备并行加工Service
 * @author zhouqz
 * @version 2017-08-16
 */

public interface MesPlanDeviceService  {
	public MesPlanDevice get(String id);
	public MesPlanDevice getByUk(String billNo);
	public List<MesPlanDevice> findList(MesPlanDevice mesPlanDevice);
	public  PageInfo<MesPlanDevice> findPage(int pageNum, int pageSize,	Map<String, String> datas);
	public  PageInfo<MesPlanDevice> findPage(int pageNum, int pageSize,	MesPlanDevice mesPlanDevice);
	public void save(MesPlanDevice mesPlanDevice);
	public void delete(MesPlanDevice mesPlanDevice);
	public int deleteById(String id);
	public int deleteRow(String id,String workcenterId,String srcBillNo) throws Exception;
 
	 
	public MesPlanDeviceSheet getSheet(String id);
	public Integer updateDispatchDetQty(String srcBillNo) throws Exception;
	public void saveDet(MesPlanDevice mesPlanDevice,MesPlanMonth mesPlanMonth,String dispatchId)  throws Exception;
	public void saveBatchDet(List<MesPlanDevice> mesPlanDeviceList,String dispatchId) throws Exception;
	
	public List<MesPlanDevice> findSchedualDeviceList(MesPlanSchedule mesPlanSchedule);
	public List<MesPlanDevice> findUnschedualDeviceList(MesPlanSchedule mesPlanSchedule);
	public Integer updateScheduleStatus(MesPlanDevice mesPlanDevice);
	public Integer updateDispatchStatus(MesPlanDevice mesPlanDevice) throws Exception;
	public int updateMaterialUseStatus(MesPlanDevice mesPlanDevice);
	public void finishBatchDet(List<MesPlanDevice> mesPlanDeviceList, String id);
	public void updateNoTechnicBatch(List<MesPlanDevice> mesPlanDeviceList);
	public void checkBatchDet(List<MesPlanDevice> mesPlanDeviceList, String workshopId, String workcenterId) throws Exception;
	public void changeErpBillStatus(MesPlanDevice mesPlanDevice);
	public Integer updateArrangeDispatchStatus(MesPlanDevice mesPlanDevice) throws Exception;
}