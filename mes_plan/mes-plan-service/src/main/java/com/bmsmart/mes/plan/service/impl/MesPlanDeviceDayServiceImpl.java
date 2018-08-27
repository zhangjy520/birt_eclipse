/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmType;
import com.bmsmart.mes.mesExternal.export.response.MdmQueryResponse;
import com.bmsmart.mes.mesExternal.export.response.vo.MdmEquipmentInfo;
import com.bmsmart.mes.mesExternal.export.response.vo.MdmWorkCenter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceInfo;
import com.bmsmart.mes.plan.dao.MesPlanDeviceDayDao;
import com.bmsmart.mes.plan.service.MesPlanDeviceDayService;
/**
 * mes_plan_device_dayService
 * @author zhouqz
 * @version 2017-08-22
 */
 @Transactional(readOnly = true)
@Service("mesPlanDeviceDayService")
public class MesPlanDeviceDayServiceImpl extends MesCrudService<MesPlanDeviceDayDao, MesPlanDeviceDay> implements MesPlanDeviceDayService{
  @Autowired
 private MesPlanDeviceDayDao mesPlanDeviceDayDao;
  @Autowired
private MdmQueryProvider mdmQueryProvider;
	public MesPlanDeviceDay get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanDeviceDay> findList(MesPlanDeviceDay mesPlanDeviceDay) {
		return super.findList(mesPlanDeviceDay);
	}
	
	
	public  PageInfo<MesPlanDeviceDay> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanDeviceDay mesPlanDeviceDay) {
		super.save(mesPlanDeviceDay);
	}
	
	
	@Transactional(readOnly = false)
	@Override
	public void saveByUK(MesPlanDeviceDay mesPlanDeviceDay) {
		//super.save(mesPlanDeviceDay);
		//检查设备状态
		//if (mesPlanDeviceDayDao.updateGoodsByUk(mesPlanDeviceDay) == 0){
			mesPlanDeviceDay.preInsert();
			mesPlanDeviceDayDao.insert(mesPlanDeviceDay);
		//}
	}
	
	
	@Transactional(readOnly = false)
	@Override
	public MesPlanDeviceDay getByUK(MesPlanDeviceDay mesPlanDeviceDay) {
		return mesPlanDeviceDayDao.getByUk(mesPlanDeviceDay);
	}
	
	
	
	@Transactional(readOnly = false)
	public void delete(MesPlanDeviceDay mesPlanDeviceDay) {
		super.delete(mesPlanDeviceDay);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanDeviceDayDao.deleteById(id);
	}
	
	public MesPlanDeviceDayDao getMesPlanDeviceDayDao() {
	   return mesPlanDeviceDayDao;
    }
    public void setMesPlanDeviceDayDao(MesPlanDeviceDayDao mesPlanDeviceDayDao) {
	    this.mesPlanDeviceDayDao = mesPlanDeviceDayDao;
    }

	@Override
	public PageInfo<MesPlanDeviceDay> findPage(int pageNum, int pageSize, MesPlanDeviceDay mesPlanDeviceDay) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanDeviceDay> demoTestList=mesPlanDeviceDayDao.findList(mesPlanDeviceDay);
		PageInfo<MesPlanDeviceDay> page = new PageInfo<MesPlanDeviceDay>(demoTestList);
		return page;
	}

	@Override
	public List<MesPlanDeviceInfo> getMesPlanDeviceInfoList(String workcenterId) {
		List<MesPlanDeviceInfo> mesPlanDeviceInfoList = new ArrayList<MesPlanDeviceInfo>();
//		mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,"100",90));
//		mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,"101",90));
//		mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,"102",90));
//		mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,"103",90));
//		mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,"104",90));
		MdmQueryRequest request=new MdmQueryRequest();
		request.setMdmType(MdmType.EQUIPMENT_INFO);
		Map<String,String> qryMap = new HashMap<String,String>();
		qryMap.put("workcenter", workcenterId);
		request.setQueryMap(qryMap);
		MdmQueryResponse listCode	= mdmQueryProvider.queryList(request);
		List<Map<String,Object>> deviceList = JSONFormatter.newInstance().toObject(listCode.getResultStr(),List.class);
		
		MdmQueryRequest request2=new MdmQueryRequest();
		request2.setMdmType(MdmType.WORK_GROUP);
		Map<String,String> qryMap2 = new HashMap<String,String>();
		qryMap2.put("workcenter", workcenterId);
		request2.setQueryMap(qryMap2);
		MdmQueryResponse listCode2	= mdmQueryProvider.queryList(request2);
		List<Map<String,Object>> workGroupList = JSONFormatter.newInstance().toObject(listCode2.getResultStr(),List.class);
		//设备
		for (Map<String,Object> device:deviceList){
			mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,device.get("code").toString(),device.get("name").toString(),90,"0"));
		}
		//工作组
		for (Map<String,Object> device:workGroupList){
			mesPlanDeviceInfoList.add(new MesPlanDeviceInfo(workcenterId,device.get("code").toString(),device.get("name").toString(),90,"1"));
		}
		
		return mesPlanDeviceInfoList;
	}

	@Override
	public int updateScheduleStatusByUk(MesPlanDeviceDay mesPlanDeviceDay) {
		return mesPlanDeviceDayDao.updateScheduleStatusByUk(mesPlanDeviceDay);
	}

	@Override
	public int updateScheduleStatusById(MesPlanDeviceDay mesPlanDeviceDay) {
		return mesPlanDeviceDayDao.updateScheduleStatusById(mesPlanDeviceDay);
	}

	@Override
	public int deleteByUk(MesPlanDeviceDay mesPlanDeviceDay) {
		return mesPlanDeviceDayDao.deleteByUk(mesPlanDeviceDay);
	}

	@Override
	public MesPlanDeviceDay getDeviceAvailMaxDate(MesPlanDeviceDay mesPlanDeviceDay) {
		return mesPlanDeviceDayDao.getDeviceAvailMaxDate(mesPlanDeviceDay);
	}

	@Override
	public int deleteByFinish(MesPlanDeviceDay dayPara) {
		return mesPlanDeviceDayDao.deleteByFinish(dayPara);
		
	}
}