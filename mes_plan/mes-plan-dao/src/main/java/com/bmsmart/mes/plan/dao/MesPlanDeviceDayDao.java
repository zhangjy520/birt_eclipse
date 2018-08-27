/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;

/**
 * mes_plan_device_dayDAO接口
 * @author zhouqz
 * @version 2017-08-22
 */
@MyBatisRepository
public interface MesPlanDeviceDayDao extends MesCrudDao<MesPlanDeviceDay> {
	//public void deleteById(String id);
	public int deleteByFinish(MesPlanDeviceDay mesPlanDeviceDay);
	public int deleteByUk(MesPlanDeviceDay mesPlanDeviceDay);
	public int updateGoodsByUk(MesPlanDeviceDay mesPlanDeviceDay);
	public MesPlanDeviceDay getByUk(MesPlanDeviceDay mesPlanDeviceDay);
	public int updateScheduleStatusByUk(MesPlanDeviceDay mesPlanDeviceDay);
	public int updateScheduleStatusById(MesPlanDeviceDay mesPlanDeviceDay);
	public MesPlanDeviceDay getDeviceAvailMaxDate(MesPlanDeviceDay mesPlanDeviceDay);
	
	
	
	
}