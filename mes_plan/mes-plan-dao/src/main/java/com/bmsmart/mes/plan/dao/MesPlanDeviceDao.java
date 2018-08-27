/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import java.util.List;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;

/**
 * 分解设备并行加工DAO接口
 * @author zhouqz
 * @version 2017-08-16
 */
@MyBatisRepository
public interface MesPlanDeviceDao extends MesCrudDao<MesPlanDevice> {
	//public int deleteById(String id);//基类有
	public MesPlanDevice getByUk(String billNo);
	public String getMaxBillNo(String srcBillNo);
	public List<MesPlanDevice> findSchedualDeviceList(MesPlanSchedule mesPlanSchedule);
	public List<MesPlanDevice> findUnschedualDeviceList(MesPlanSchedule mesPlanSchedule);
	public Integer updateScheduleStatus(MesPlanDevice mesPlanDevice);
	public Integer updateDispatchStatus(MesPlanDevice mesPlanDevice);
	public int updateMaterialUseStatus(MesPlanDevice mesPlanDevice);
	
	public int updateFinishQtyByBillNo(MesPlanDevice mesPlanDevice);
	public int updateNoTechnic(MesPlanDevice mesPlanDevice);
}