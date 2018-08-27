/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.DateUtil;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.dao.MesPlanDeviceDao;
import com.bmsmart.mes.plan.service.MesPlanDeviceDayService;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanDispatchHeadService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
/**
 * 分解设备并行加工Service
 * @author zhouqz
 * @version 2017-08-16
 */
@Transactional(readOnly = true)
@Service("mesPlanDeviceService")
public class MesPlanDeviceServiceImpl extends MesCrudService<MesPlanDeviceDao, MesPlanDevice> implements MesPlanDeviceService{
 @Autowired
 private MesPlanDeviceDao mesPlanDeviceDao;
 @Autowired
 private MesPlanDispatchHeadService mesPlanDispatchHeadService;
 @Autowired
 private MesPlanDispatchDetService mesPlanDispatchDetService;
 @Autowired
 private MesPlanMonthService mesPlanMonthService;
 @Autowired
 private MesPlanDeviceDayService mesPlanDeviceDayService;
	public MesPlanDevice get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanDevice> findList(MesPlanDevice mesPlanDevice) {
		return super.findList(mesPlanDevice);
	}
	
	
	public  PageInfo<MesPlanDevice> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void saveDeviceAndDeviceDay(MesPlanDevice mesPlanDevice,MesPlanMonth mesPlanMonth,String workcenterId) {
		//整理车间不插入MesPlanDeviceDay，因为计划本身就是到天的
		if (!MesPlanDevice.WORKSHOP_ARRANGE.equals(mesPlanMonth.getWorkshopId())){
			Date beginDate  = mesPlanDevice.getPlanBeginDate();
			Date endDate = mesPlanDevice.getPlanEndDate();
			do {
				MesPlanDeviceDay mesPlanDeviceDay = new MesPlanDeviceDay(); 
				mesPlanDeviceDay.setWorkDate(beginDate);
				mesPlanDeviceDay.setWorkcenterId(workcenterId);
				mesPlanDeviceDay.setDeviceId(mesPlanDevice.getDeviceId());
				mesPlanDeviceDay.setGoodsId(mesPlanMonth.getGoodsId());
				mesPlanDeviceDay.setIsChgGoods(mesPlanDevice.getIsChgGoods());
				mesPlanDeviceDay.setScheduleStatus("0");
				try{
					mesPlanDeviceDayService.saveByUK(mesPlanDeviceDay);
				}catch(Exception ex){
					throw new RuntimeException("设备计划重复制订!");
				}
				
				beginDate = DateUtil.getTomorrow(beginDate);
			}while(beginDate.compareTo(endDate)<=0);
		}
		super.save(mesPlanDevice);
	}
	
	
	
	@Transactional(readOnly = false)
	public void delDeviceDay(MesPlanDevice mesPlanDevice,MesPlanMonth mesPlanMonth,String workcenterId) {
		
		Date beginDate  = mesPlanDevice.getPlanBeginDate();
		Date endDate = mesPlanDevice.getPlanEndDate();
		do {
			MesPlanDeviceDay mesPlanDeviceDay = new MesPlanDeviceDay(); 
			mesPlanDeviceDay.setWorkDate(beginDate);
			mesPlanDeviceDay.setWorkcenterId(workcenterId);
			mesPlanDeviceDay.setDeviceId(mesPlanDevice.getDeviceId());
			mesPlanDeviceDay.setGoodsId(mesPlanMonth.getGoodsId());
			mesPlanDeviceDay.setIsChgGoods(mesPlanDevice.getIsChgGoods());
			mesPlanDeviceDay.setScheduleStatus("0");
			try{
				mesPlanDeviceDayService.saveByUK(mesPlanDeviceDay);
			}catch(Exception ex){
				throw new RuntimeException("设备计划重复制订!");
			}
			
			beginDate = DateUtil.getTomorrow(beginDate);
		}while(beginDate.compareTo(endDate)<=0);
		
		super.save(mesPlanDevice);
	}
	
	
	@Transactional(readOnly = false)
	public void save(MesPlanDevice mesPlanDevice) {
		super.save(mesPlanDevice);
	}
	
	@Transactional(readOnly = false)
	public void delete(MesPlanDevice mesPlanDevice) {
		super.delete(mesPlanDevice);
	}
	@Transactional(readOnly = false)
	public int deleteById(String id){
	    mesPlanDeviceDao.deleteById(id);
	    return 1;
	}
	
	public MesPlanDeviceDao getMesPlanDeviceDao() {
	   return mesPlanDeviceDao;
    }
    public void setMesPlanDeviceDao(MesPlanDeviceDao mesPlanDeviceDao) {
	    this.mesPlanDeviceDao = mesPlanDeviceDao;
    }

	public MesPlanDispatchHeadService getMesPlanDispatchHeadService() {
		return mesPlanDispatchHeadService;
	}

	public void setMesPlanDispatchHeadService(MesPlanDispatchHeadService mesPlanDispatchHeadService) {
		this.mesPlanDispatchHeadService = mesPlanDispatchHeadService;
	}

	public MesPlanDispatchDetService getMesPlanDispatchDetService() {
		return mesPlanDispatchDetService;
	}

	public void setMesPlanDispatchDetService(MesPlanDispatchDetService mesPlanDispatchDetService) {
		this.mesPlanDispatchDetService = mesPlanDispatchDetService;
	}

	public MesPlanMonthService getMesPlanMonthService() {
		return mesPlanMonthService;
	}

	public void setMesPlanMonthService(MesPlanMonthService mesPlanMonthService) {
		this.mesPlanMonthService = mesPlanMonthService;
	}

	public MesPlanDeviceDayService getMesPlanDeviceDayService() {
		return mesPlanDeviceDayService;
	}

	public void setMesPlanDeviceDayService(MesPlanDeviceDayService mesPlanDeviceDayService) {
		this.mesPlanDeviceDayService = mesPlanDeviceDayService;
	}

	@Override
	public MesPlanDeviceSheet getSheet(String id) {
		MesPlanDispatchDet mesPlanDispatchDet = mesPlanDispatchDetService.get(id);
		
		MesPlanDispatchHead headPara = new MesPlanDispatchHead();
		headPara.setSheetId(mesPlanDispatchDet.getSheetId());
		MesPlanDispatchHead mesPlanDispatchHead = mesPlanDispatchHeadService.getByUk(headPara);
		
		MesPlanMonth paraMonth = new MesPlanMonth();
		paraMonth.setErpBillNo(mesPlanDispatchHead.getErpBillNo());
		MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(paraMonth);
		
		MesPlanDevice para = new MesPlanDevice();
		para.setSrcBillNo(mesPlanDispatchDet.getBillNo());
		List<MesPlanDevice> mesPlanDeviceList = mesPlanDeviceDao.findList(para);
		
		List<MesPlanDispatchDet> mesPlanDispatchDetList = new ArrayList<MesPlanDispatchDet>();
		//整理车间取出关联单据
		if (MesPlanDevice.WORKSHOP_ARRANGE.equals(mesPlanMonth.getWorkshopId())){
			MesPlanDispatchDet detPara = new MesPlanDispatchDet();
			detPara.setSheetId(mesPlanDispatchDet.getSheetId());
			mesPlanDispatchDetList = mesPlanDispatchDetService.findList(detPara);
		}
		
		MesPlanDeviceSheet sheet = new MesPlanDeviceSheet();
		 
		sheet.setMesPlanMonth(mesPlanMonth);
		sheet.setMesPlanDispatchHead(mesPlanDispatchHead);
		sheet.setMesPlanDispatchDet(mesPlanDispatchDet);
		sheet.setMesPlanDeviceList(mesPlanDeviceList);
		sheet.setMesPlanDispatchDetList(mesPlanDispatchDetList);//整理车间用
		return sheet;
	}

 
	public Integer updateDispatchDetQty(String srcBillNo,Boolean isDel) throws Exception {
		MesPlanDevice para = new MesPlanDevice();
		para.setSrcBillNo(srcBillNo);
		List<MesPlanDevice> mesPlanDeviceList = mesPlanDeviceDao.findList(para);
		
		double workCenterQtySum = 0,workCenterFinishQtySum = 0 ;
		if (mesPlanDeviceList != null){
			for (MesPlanDevice tmpDevice:mesPlanDeviceList){
				if (tmpDevice.getWorkCenterQty() != null){
					workCenterQtySum = workCenterQtySum + tmpDevice.getWorkCenterQty();
				}
				if (tmpDevice.getFinishQty() != null){
					workCenterFinishQtySum = workCenterFinishQtySum +tmpDevice.getFinishQty();
				}
			}	
		} 
		//检查数量是否超出厂调度单数量
		if (!isDel){
			MesPlanDispatchDet mesPlanDispatchDetDb = mesPlanDispatchDetService.getByBillNo(srcBillNo);
			if (mesPlanDispatchDetDb.getDispatchQty() < workCenterQtySum){
				throw new Exception("数量不能超过厂级调度单数量");
			}
		}
		MesPlanDispatchDet mesPlanDispatchDet = new MesPlanDispatchDet();
		mesPlanDispatchDet.setBillNo(srcBillNo);
		mesPlanDispatchDet.setWorkCenterQty(workCenterQtySum);
		mesPlanDispatchDet.setFinishQty(workCenterFinishQtySum);
		mesPlanDispatchDetService.updateWorkCenterQty(mesPlanDispatchDet);
		//mesPlanDispatchDetService.updateFinishQty(mesPlanDispatchDet);
		return 1;
	} 
	
	@Override
	public Integer updateDispatchDetQty(String srcBillNo) throws Exception  {
		return updateDispatchDetQty(srcBillNo,false);
	}
	
	
	@Override
	public MesPlanDevice getByUk(String billNo) {
		return mesPlanDeviceDao.getByUk(billNo);
	}

	@Override
	public PageInfo<MesPlanDevice> findPage(int pageNum, int pageSize, MesPlanDevice mesPlanDevice) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanDevice> demoTestList=dao.findList(mesPlanDevice);
		//List<MesPlanDevice> demoTestList=dao.findAllList(mesPlanDevice)
		PageInfo<MesPlanDevice> page = new PageInfo<MesPlanDevice>(demoTestList);
		return page;
	}
	
	@Override
	public void saveDet(MesPlanDevice mesPlanDevice,MesPlanMonth mesPlanMonth,String dispatchId) throws Exception{
		MesPlanDispatchDet mesPlanDispatchDet = mesPlanDispatchDetService.get(dispatchId);
		
		saveInner(mesPlanDevice, mesPlanDispatchDet,mesPlanMonth);
		
	}
	
	private void syncDelDeviceDay(String id ,String workcenterId) throws Exception{
		MesPlanDevice oldDevice =  get(id);
		syncDelDeviceDay(oldDevice,workcenterId);
	}
	
	private void syncDelDeviceDay(MesPlanDevice device,String workcenterId){
		Date beginDateTmp = device.getPlanBeginDate();
		do {
			MesPlanDeviceDay mesPlanDeviceDay = new MesPlanDeviceDay(); 
			mesPlanDeviceDay.setWorkDate(beginDateTmp);
			mesPlanDeviceDay.setWorkcenterId(workcenterId);
			mesPlanDeviceDay.setDeviceId(device.getDeviceId());
			 
			mesPlanDeviceDayService.deleteByUk(mesPlanDeviceDay);
			
			beginDateTmp = DateUtil.getTomorrow(beginDateTmp);
		}while(beginDateTmp.compareTo(device.getPlanEndDate())<=0);
	}
	
	
	private void saveInner(MesPlanDevice mesPlanDevice, MesPlanDispatchDet mesPlanDispatchDet,MesPlanMonth mesPlanMonth) throws Exception {
		if (mesPlanDispatchDet == null){
			throw new RuntimeException("dispatchId数据已删除! ");
		}
		
		if (mesPlanMonth == null){
			throw new RuntimeException("订单对象为空");
		}
		//非整理车间检查设备是否冲突
		if (!MesPlanDevice.WORKSHOP_ARRANGE.equals(mesPlanMonth.getWorkshopId())){
			//刷新设备状态 
			if (StringUtils.isBlank(mesPlanDevice.getId())){
				//新增检查是否计划是否冲突
				MesPlanDeviceDay para = new MesPlanDeviceDay();
				para.setWorkDate1(mesPlanDevice.getPlanBeginDate());
				para.setWorkDate2(mesPlanDevice.getPlanEndDate());
				para.setDeviceId(mesPlanDevice.getDeviceId());
				para.setWorkcenterId(mesPlanDispatchDet.getWorkcenterId());
				List<MesPlanDeviceDay> deviceDayList =  mesPlanDeviceDayService.findList(para);
				if (deviceDayList != null && deviceDayList.size() > 0){
					throw new RuntimeException("设备计划重复!");
				}
			}else{
				//删除原始时间段设备状态 
				//MesPlanDevice oldDevice =  get(mesPlanDevice.getId());
				syncDelDeviceDay(mesPlanDevice.getId(),mesPlanDispatchDet.getWorkcenterId());
			}
		}else{
			if (StringUtils.isBlank(mesPlanDevice.getId()) 
					&& StringUtils.isNotEmpty(mesPlanDispatchDet.getBillNo()) 
					&& StringUtils.isNotEmpty(mesPlanDevice.getDeviceId()) ){
				//整理车间检查,新增设备检查设备是否已添加
				MesPlanDevice para = new MesPlanDevice();
				
				para.setSrcBillNo(mesPlanDispatchDet.getBillNo());
				para.setDeviceId(mesPlanDevice.getDeviceId());
				
				List<MesPlanDevice> deviceList2 = mesPlanDeviceDao.findList(para);
				if (deviceList2 != null && deviceList2.size() >0){
					throw new RuntimeException("设备已排计划,请重新选择!");
				}
			}
			
		}
				
		if (mesPlanDevice.getIsNewRecord()){
			mesPlanDevice.setDispatchStatus("0");
			mesPlanDevice.setSrcBillNo(mesPlanDispatchDet.getBillNo());
			mesPlanDevice.setBillNo(getNewBillNo(mesPlanDispatchDet.getBillNo()));
			mesPlanDevice.setFinishQty(0D);
		}else{
			MesPlanDevice dbDevice= get(mesPlanDevice.getId());
			dbDevice.setIsChgGoods(mesPlanDevice.getIsChgGoods());
			dbDevice.setWorkCenterQty(mesPlanDevice.getWorkCenterQty());
			dbDevice.setPlanBeginDate(mesPlanDevice.getPlanBeginDate());
			dbDevice.setPlanEndDate(mesPlanDevice.getPlanEndDate());
			dbDevice.setDeviceId(mesPlanDevice.getDeviceId());
			
			//dbDevice.setWorkcenterId(mesPlanDevice.getWorkcenterId());//整理车间添加
			
			if (StringUtil.isNotBlank(mesPlanDevice.getDeviceRequire())){
				dbDevice.setDeviceRequire(mesPlanDevice.getDeviceRequire());
			}
			mesPlanDevice = dbDevice;
		}
		
		saveDeviceAndDeviceDay(mesPlanDevice,mesPlanMonth,mesPlanDispatchDet.getWorkcenterId());
		updateDispatchDetQty(mesPlanDispatchDet.getBillNo());
	}
	
	@Override
	public void saveBatchDet(List<MesPlanDevice> mesPlanDeviceList,String dispatchId) throws Exception{
		MesPlanDispatchDet mesPlanDispatchDet = mesPlanDispatchDetService.get(dispatchId);
		MesPlanMonth order = null;
		for (MesPlanDevice mesPlanDevice:mesPlanDeviceList){
			if (StringUtil.isBlank(mesPlanDevice.getErpBillNo())){
				throw new RuntimeException("订单号不能为空!");
			}
			if (order == null){
				MesPlanMonth paraMonth = new MesPlanMonth();
				paraMonth.setErpBillNo(mesPlanDevice.getErpBillNo());
				order = mesPlanMonthService.getByUk(paraMonth);
			}
			saveInner(mesPlanDevice,mesPlanDispatchDet,order);
		}
	}
	
	private String getNewBillNo(String dispatchBillNo){
		Integer index =0 ;
		String indexStr = "";
		String billNo = "";
		MesPlanDevice mesPlanDevice = null;
		
		String maxBillNo = mesPlanDeviceDao.getMaxBillNo(dispatchBillNo+"-");
		if (StringUtils.isNotEmpty(maxBillNo)){
			Integer index2 = maxBillNo.lastIndexOf("-");
			if (index2>0){
				index = Integer.parseInt(maxBillNo.substring(index2+1));
			}
		}
		
		do{
			index++;
			indexStr = index<=9?"0"+index.toString():index.toString();
			billNo = (dispatchBillNo+"-")+indexStr;
			mesPlanDevice= getByUk(billNo);
		}while(mesPlanDevice != null && StringUtil.isNotBlank( mesPlanDevice.getBillNo()));
		
		return billNo;
	}

	@Override
	public List<MesPlanDevice> findSchedualDeviceList(MesPlanSchedule mesPlanSchedule) {
		 return mesPlanDeviceDao.findSchedualDeviceList(mesPlanSchedule);
	}
	@Override
	public List<MesPlanDevice> findUnschedualDeviceList(MesPlanSchedule mesPlanSchedule) {
		return mesPlanDeviceDao.findUnschedualDeviceList(mesPlanSchedule);
	}

	@Override
	public Integer updateScheduleStatus(MesPlanDevice mesPlanDevice) {
		return mesPlanDeviceDao.updateScheduleStatus(mesPlanDevice);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteRow(String id,String workcenterId,String srcBillNo) throws Exception {
		syncDelDeviceDay(id,workcenterId);
		mesPlanDeviceDao.deleteById(id);
		
		//同步刷新数量
		updateDispatchDetQty(srcBillNo,true) ;
				
		return 1;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateDispatchStatus(MesPlanDevice mesPlanDevice) throws Exception {
		//同步刷新数量
//		if (StringUtils.isBlank(mesPlanDevice.getSrcBillNo())){
//			MesPlanDevice device = get(mesPlanDevice.getId());
//			updateDispatchDetQty(device.getSrcBillNo(),true) ;
//		}else{
//			updateDispatchDetQty(mesPlanDevice.getSrcBillNo(),true) ;
//		}
		//检查订单状态 并修改,如果为下达，修改成为开始
		changeErpBillStatus(mesPlanDevice);
		return mesPlanDeviceDao.updateDispatchStatus(mesPlanDevice);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeErpBillStatus(MesPlanDevice mesPlanDevice) {
		if (!StringUtils.isBlank(mesPlanDevice.getErpBillNo())){
			MesPlanMonth mesPlanMonth = new MesPlanMonth();
			mesPlanMonth.setErpBillNo(mesPlanDevice.getErpBillNo());
			MesPlanMonth month = mesPlanMonthService.getByUk(mesPlanMonth);
			if ("20".equals(month.getPlanStatus()) || StringUtils.isBlank(month.getPlanStatus()) ){
				MesPlanMonth updatePlan  =  new MesPlanMonth();
				updatePlan.setId(month.getId());
				updatePlan.setPlanStatus("30");
				mesPlanMonthService.updateSheetStatusById(updatePlan);
			}
		}
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer updateArrangeDispatchStatus(MesPlanDevice mesPlanDevice) throws Exception {
		changeErpBillStatus(mesPlanDevice);
		save(mesPlanDevice);
		return 1;
	}
	@Override
	public int updateMaterialUseStatus(MesPlanDevice mesPlanDevice) {
		return  mesPlanDeviceDao.updateMaterialUseStatus(mesPlanDevice);
	}

	@Override
	public void finishBatchDet(List<MesPlanDevice> mesPlanDeviceList, String id) {
			for (MesPlanDevice mesPlanDevice:mesPlanDeviceList){
				//订单完工解除设备锁定状态
				MesPlanDevice rtnDevice = mesPlanDeviceDao.get(mesPlanDevice.getId());
				Date beginDate = rtnDevice.getPlanBeginDate();
				Date endDate = rtnDevice.getPlanEndDate();
				MesPlanDeviceDay dayPara = new MesPlanDeviceDay();
				dayPara.setDeviceId(rtnDevice.getDeviceId());
				//dayPara.setGoodsId(mesPlanDevice.get);
				//dayPara.setWorkcenterId(rtnDet.getWorkcenterId());
				dayPara.setWorkDate1(beginDate);
				dayPara.setWorkDate2(endDate);
				mesPlanDeviceDayService.deleteByFinish(dayPara);
				 
				
				mesPlanDeviceDao.updateDispatchStatus(mesPlanDevice);
			}
	}

	@Override
	public void updateNoTechnicBatch(List<MesPlanDevice> mesPlanDeviceList) {
		if (mesPlanDeviceList != null){
			for(MesPlanDevice mesPlanDevice: mesPlanDeviceList){
				//检查状态是否小于10，大于10的跳过
				MesPlanDevice rtnDevice = this.get(mesPlanDevice.getId());
				if (StringUtils.isBlank(rtnDevice.getDispatchStatus())){
					rtnDevice.setDispatchStatus("0");
				}
				if (Integer.parseInt(rtnDevice.getDispatchStatus()) < 10){
					mesPlanDeviceDao.updateNoTechnic(mesPlanDevice);
				}
			}
			
		}
	}

	@Override
	public void checkBatchDet(List<MesPlanDevice> mesPlanDeviceList, String workshopId, String workcenterId) throws Exception {
		if (mesPlanDeviceList != null){
			for(MesPlanDevice tmpDevice:mesPlanDeviceList){
				//查找设备最新状态
				MesPlanDevice device = get(tmpDevice.getId());
				if (StringUtils.isEmpty(device.getDispatchStatus()) || "0".equals(device.getDispatchStatus())){
					device.setDispatchStatus("10");
					int rowCnt = 0;
					//整理车间不排程,直接修改状态
					if (MesPlanDevice.WORKSHOP_ARRANGE.equals(workshopId)){
						device.setRealBeginDate(device.getPlanBeginDate());
						device.setRealEndDate(device.getPlanEndDate());
						device.setScheduleStatus("10");
						save(device);
						rowCnt = 1;
					}else{
						rowCnt = updateDispatchStatus(device);
					}
					//dsfds
				}	
			}
			
		}
			
	}

	 
}