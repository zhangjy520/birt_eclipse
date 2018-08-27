/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceSheet;

/**
 * mes_plan_device_dayService
 * @author zhouqz
 * @version 2017-08-22
 */

public interface MesPlanDeviceDayService  {

	public MesPlanDeviceDay get(String id);
	public List<MesPlanDeviceDay> findList(MesPlanDeviceDay mesPlanDeviceDay);
	public  PageInfo<MesPlanDeviceDay> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public  PageInfo<MesPlanDeviceDay> findPage(int pageNum, int pageSize,
			MesPlanDeviceDay mesPlanDeviceDay  );
	public void save(MesPlanDeviceDay mesPlanDeviceDay);
	public void delete(MesPlanDeviceDay mesPlanDeviceDay);
	public void deleteById(String id);
	/**
	 * 调用主数据，得到工作中心所有设备信息
	 * @param workcenterId
	 * @return
	 */
	public List<MesPlanDeviceInfo> getMesPlanDeviceInfoList(String workcenterId); 
	public MesPlanDeviceDay getByUK(MesPlanDeviceDay mesPlanDeviceDay);
	public void saveByUK(MesPlanDeviceDay mesPlanDeviceDay);
	public int updateScheduleStatusByUk(MesPlanDeviceDay mesPlanDeviceDay);
	public int updateScheduleStatusById(MesPlanDeviceDay mesPlanDeviceDay);
	
	public int deleteByUk(MesPlanDeviceDay mesPlanDeviceDay);
	
	public MesPlanDeviceDay getDeviceAvailMaxDate(MesPlanDeviceDay mesPlanDeviceDay);
	public int deleteByFinish(MesPlanDeviceDay dayPara);
	
}