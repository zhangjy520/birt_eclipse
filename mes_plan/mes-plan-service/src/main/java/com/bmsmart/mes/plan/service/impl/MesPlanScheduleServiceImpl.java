/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.DateUtil;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanScheduleSheet;
import com.bmsmart.mes.plan.dao.MesPlanDeviceDao;
import com.bmsmart.mes.plan.dao.MesPlanScheduleDao;
import com.bmsmart.mes.plan.service.MesPlanDeviceDayService;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanDispatchBomService;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanScheduleService;
/**
 * mes_plan_scheduleService
 * @author zhouqizhi
 * @version 2017-08-29
 */
 @Transactional(readOnly = true)
@Service("mesPlanScheduleService")
public class MesPlanScheduleServiceImpl extends MesCrudService<MesPlanScheduleDao, MesPlanSchedule> implements MesPlanScheduleService{
  @Autowired
 private MesPlanScheduleDao mesPlanScheduleDao;
  @Autowired
 private MesPlanMonthService mesPlanMonthService;
  @Autowired
  private MesPlanDeviceDao mesPlanDeviceDao;
  
  @Autowired
  private MesPlanDeviceService mesPlanDeviceService;
  @Autowired
  private MesPlanDeviceDayService mesPlanDeviceDayService;
  @Autowired
  private MesPlanMaterialUseService mesPlanMaterialUseService;
  @Autowired
  private MesPlanDispatchBomService mesPlanDispatchBomService;
	public MesPlanSchedule get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanSchedule> findList(MesPlanSchedule mesPlanSchedule) {
		return super.findList(mesPlanSchedule);
	}
	
	
	public  PageInfo<MesPlanSchedule> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	
	
	public  PageInfo<MesPlanSchedule> findPage(int pageNum, int pageSize,MesPlanSchedule mesPlanSchedule){
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanSchedule> demoTestList=mesPlanScheduleDao.findList(mesPlanSchedule);
		PageInfo<MesPlanSchedule> page = new PageInfo<MesPlanSchedule>(demoTestList);
		return page;
	}
	
	
	@Transactional(readOnly = false)
	public void save(MesPlanSchedule mesPlanSchedule) {
		super.save(mesPlanSchedule);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanSchedule mesPlanSchedule) {
		super.delete(mesPlanSchedule);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanScheduleDao.deleteById(id);
	}
	
	public MesPlanScheduleDao getMesPlanScheduleDao() {
	   return mesPlanScheduleDao;
    }
    public void setMesPlanScheduleDao(MesPlanScheduleDao mesPlanScheduleDao) {
	    this.mesPlanScheduleDao = mesPlanScheduleDao;
    }

	@Override
	public MesPlanScheduleSheet getSheet(String id,String orderBy) {
		MesPlanScheduleSheet mesPlanScheduleSheet = new MesPlanScheduleSheet();
		MesPlanSchedule mesPlanSchedule = super.get(id);
		mesPlanScheduleSheet.setMesPlanSchedule(mesPlanSchedule);
		
		MesPlanSchedule mesPlanSchedulePara = new MesPlanSchedule();
		mesPlanSchedulePara.setOrderBy(orderBy==null?"plan_begin_date":orderBy);
		mesPlanSchedulePara.setWorkcenterId(mesPlanSchedule.getWorkcenterId());
		mesPlanSchedulePara.setWorkshopId(mesPlanSchedule.getWorkshopId());
		List<MesPlanDevice> unschedualDeviceList = findUnschedualDeviceList(mesPlanSchedulePara);
		
		mesPlanSchedulePara.setOrderBy("real_begin_date");
		mesPlanSchedulePara.setWorkcenterId(mesPlanSchedule.getWorkcenterId());
		mesPlanSchedulePara.setWorkshopId(mesPlanSchedule.getWorkshopId());
		mesPlanSchedulePara.setScheduleDate(mesPlanSchedule.getScheduleDate());
		mesPlanSchedulePara.setShiftId(mesPlanSchedule.getShiftId());
		mesPlanSchedulePara.setShiftStyle(mesPlanSchedule.getShiftStyle());
		List<MesPlanDevice> schedualDeviceList = findSchedualDeviceList(mesPlanSchedulePara);
		
		mesPlanScheduleSheet.setSchedualDeviceList(schedualDeviceList);
		mesPlanScheduleSheet.setUnschedualDeviceList(unschedualDeviceList);
		return mesPlanScheduleSheet;
	}
	
	@Override
	public List<MesPlanDevice> findSchedualDeviceList(MesPlanSchedule mesPlanSchedule) {
		if (StringUtil.isBlank(mesPlanSchedule.getOrderBy())){
			mesPlanSchedule.setOrderBy("real_begin_date");
		}
		return  mesPlanDeviceDao.findSchedualDeviceList(mesPlanSchedule);
	}

	@Override
	public List<MesPlanDevice> findUnschedualDeviceList(MesPlanSchedule mesPlanSchedule) {
		if (StringUtil.isBlank(mesPlanSchedule.getOrderBy())){
			mesPlanSchedule.setOrderBy("plan_begin_date");
		}
		return  mesPlanDeviceDao.findUnschedualDeviceList(mesPlanSchedule);
	}

	@Override
	public Integer saveBatch(MesPlanSchedule mesPlanSchedule,List<MesPlanDevice> mesPlanDeviceList) throws RuntimeException {
		if (mesPlanDeviceList == null) {
			return 0;
		}
		for(MesPlanDevice device : mesPlanDeviceList){
			if (device.getRealBeginDate()== null ||device.getRealEndDate()==null || device.getRealBeginDate().compareTo(device.getRealEndDate()) > 0){
				throw new RuntimeException("排程日期出错");
			}
			mesPlanDeviceService.updateScheduleStatus(device);
		}
		return 1;
	}

	
	@Override
	public Integer checkBatch(MesPlanSchedule mesPlanSchedule,List<MesPlanDevice> mesPlanDeviceList) throws RuntimeException {
		if (mesPlanDeviceList == null) {
			return 0;
		}
		
		for(MesPlanDevice device : mesPlanDeviceList){
			//查询最新状态操作，避免缓存
			MesPlanDevice newDevice  = mesPlanDeviceService.get(device.getId());
			if (newDevice.getScheduleStatus().equals("1")){
				newDevice.setScheduleStatus("10");
				mesPlanDeviceService.save(newDevice);
			}
		}
		
//		for(MesPlanDevice device : mesPlanDeviceList){
//			Date beginDate = device.getPlanBeginDate();
//			Date endDate = device.getPlanEndDate();
//			do{
//				MesPlanDeviceDay deviceDayPara = new MesPlanDeviceDay();
//				deviceDayPara.setWorkcenterId(mesPlanSchedule.getWorkcenterId());
//				deviceDayPara.setWorkDate(beginDate);
//				deviceDayPara.setDeviceId(device.getDeviceId());
//				MesPlanDeviceDay day = mesPlanDeviceDayService.getByUK(deviceDayPara);
//				if (day != null){
//					//修改
//					day.setScheduleStatus("10");
//					mesPlanDeviceDayService.updateScheduleStatusById(day);
//				}else{
//					MesPlanMonth mesPlanMonthPara = new MesPlanMonth();
//					mesPlanMonthPara.setErpBillNo(device.getErpBillNo());
//					MesPlanMonth mesPlanMonth =mesPlanMonthService.getByUk(mesPlanMonthPara);
//					deviceDayPara.setGoodsId(mesPlanMonth.getGoodsId());
//					deviceDayPara.setIsChgGoods(device.getIsChgGoods());
//					deviceDayPara.setRemarks("没有找到计划,智能排程");
//					deviceDayPara.setScheduleStatus("30");
//					mesPlanDeviceDayService.save(deviceDayPara);
//				}
//				beginDate = DateUtil.getTomorrow(beginDate);
//			}while(beginDate.compareTo(endDate) <= 0);
//			
//			
//		}
		return 1;
	}
	
	
	public synchronized String getNewSheetId(){
		Random r = new Random();
		return "PS"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}
	
	
	@Override
	public String saveSheet(MesPlanSchedule mesPlanSchedule, List<MesPlanDevice> mesPlanDeviceList) throws RuntimeException {
		MesPlanSchedule schedule = mesPlanScheduleDao.getByUk(mesPlanSchedule);
		if (StringUtil.isBlank(mesPlanSchedule.getId()) && schedule != null){
			throw new RuntimeException(String.format("日期=[%1$tY-%1$tm-%1$td]已排程，排程人[%2$s]", schedule.getScheduleDate(),schedule.getScheduleUser()));
		}
		if (schedule == null){
			mesPlanSchedule.setSheetId(getNewSheetId());
			mesPlanSchedule.preInsert();
			mesPlanScheduleDao.insert(mesPlanSchedule);
		} 
		saveBatch(mesPlanSchedule,mesPlanDeviceList);
		return schedule == null?mesPlanSchedule.getId():schedule.getId();
	}

	@Override
	public MesPlanSchedule getByUk(MesPlanSchedule mesPlanSchedule) {
		return mesPlanScheduleDao.getByUk(mesPlanSchedule);
	}

	private List<MesPlanDispatchBom> getGoodsBom(String srcBillNo){
		MesPlanDispatchBom bomPara = new MesPlanDispatchBom();
		bomPara.setBillNo(srcBillNo);
		List<MesPlanDispatchBom> mesPlanDispatchBomList= mesPlanDispatchBomService.findList(bomPara);
		return mesPlanDispatchBomList;
	}
	
	/*@Override
	public List<MesPlanMaterialUse> saveMaterialUse(String id) {
		MesPlanSchedule mesPlanSchedule = super.get(id);
		//状态检查
		if (!StringUtils.isBlank(mesPlanSchedule.getSheetStatus()) && Integer.parseInt(mesPlanSchedule.getSheetStatus()) >=10 ){
			throw new RuntimeException("排程单不能重复领料!");
		}else{
			mesPlanSchedule.setSheetStatus("10");
			super.save(mesPlanSchedule);
		}
		
		MesPlanSchedule mesPlanSchedulePara = new MesPlanSchedule();
		mesPlanSchedulePara.setOrderBy("real_begin_date");
		mesPlanSchedulePara.setWorkcenterId(mesPlanSchedule.getWorkcenterId());
		mesPlanSchedulePara.setWorkshopId(mesPlanSchedule.getWorkshopId());
		mesPlanSchedulePara.setScheduleDate(mesPlanSchedule.getScheduleDate());
		mesPlanSchedulePara.setShiftId(mesPlanSchedule.getShiftId());
		mesPlanSchedulePara.setShiftStyle(mesPlanSchedule.getShiftStyle());
		mesPlanSchedulePara.setScheduleStatus("10");
		List<MesPlanDevice> schedualDeviceList = findSchedualDeviceList(mesPlanSchedulePara);
		
		if (schedualDeviceList != null){
			for (MesPlanDevice mesPlanDevice :schedualDeviceList){
				 
				List<MesPlanDispatchBom> mesPlanDispatchBomList = getGoodsBom(mesPlanDevice.getSrcBillNo());
				for(MesPlanDispatchBom bom :mesPlanDispatchBomList ){
					MesPlanMaterialUse materialUse = new MesPlanMaterialUse();
					materialUse.setSheetId(mesPlanSchedule.getSheetId());
					materialUse.setGoodsId(bom.getRawGoodsId());
					materialUse.setQty(mesPlanDevice.getWorkCenterQty()*bom.getUnitQty());
					materialUse.setScheduleDate(mesPlanSchedule.getScheduleDate());
					materialUse.setWorkshopId(mesPlanSchedule.getWorkshopId());
					//mesPlanMaterialUseService.updateUseQtyByUk(materialUse);
				}
			}
		}
		MesPlanMaterialUse mesPlanMaterialUse = new MesPlanMaterialUse();
		mesPlanMaterialUse.setSheetId(mesPlanSchedule.getSheetId());
		mesPlanMaterialUse.setScheduleDate(mesPlanSchedule.getScheduleDate());
		//List<MesPlanMaterialUse> mesPlanMaterialUseList = mesPlanMaterialUseService.findList(mesPlanMaterialUse);
		return null;
	}*/

	@Override
	public Integer check(String id) {
		
		return null;
	}

	@Override
	public int deleteSheet(MesPlanSchedule head) {
		 
		return 1;
	}

	@Override
	public List<MesPlanMaterialUse> saveMaterialUse(MesPlanSchedule mesPlanSchedule,
			List<MesPlanDevice> mesPlanDeviceList) {
		MesPlanSchedule schedule = mesPlanScheduleDao.getByUk(mesPlanSchedule);
		if (StringUtil.isBlank(mesPlanSchedule.getId()) && schedule != null){
			throw new RuntimeException(String.format("日期=[%1$tY-%1$tm-%1$td]已排程，排程人[%2$s]", schedule.getScheduleDate(),schedule.getScheduleUser()));
		}
		if (schedule == null){
			mesPlanSchedule.setSheetId(getNewSheetId());
			mesPlanSchedule.preInsert();
			mesPlanScheduleDao.insert(mesPlanSchedule);
		} 
		
		if (mesPlanDeviceList != null){
			for (MesPlanDevice mesPlanDevice :mesPlanDeviceList){
				mesPlanDevice = mesPlanDeviceService.get(mesPlanDevice.getId());
				if (mesPlanDevice != null){
					if (Strings.isNullOrEmpty(mesPlanDevice.getMaterialUseStatus())){
						mesPlanDevice.setMaterialUseStatus("0");
					}
					if (mesPlanDevice.getMaterialUseStatus().equals("0")){
						//MesPlanMonth monthPara = new MesPlanMonth();
						//monthPara.setErpBillNo(mesPlanDevice.getErpBillNo());
						//MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(monthPara);
						List<MesPlanDispatchBom> mesGoodsDispatchBomList = getGoodsBom(mesPlanDevice.getSrcBillNo());
						Date tempDate = mesPlanDevice.getRealBeginDate();
						int dayCnt = mesPlanDevice.getRealEndDate().compareTo(mesPlanDevice.getRealBeginDate())+1;
						//日期输入错误 
						if (dayCnt<=0) {
							continue;
						}
						dayCnt = 1 ;//需求变化，不分解到天
							for(MesPlanDispatchBom bom :mesGoodsDispatchBomList ){
								MesPlanMaterialUse materialUse = new MesPlanMaterialUse();
								materialUse.setSheetId(mesPlanSchedule.getSheetId());
								materialUse.setGoodsId(bom.getRawGoodsId());
								DecimalFormat df = new DecimalFormat("#.00");
								String qty = df.format(mesPlanDevice.getWorkCenterQty()*bom.getUnitQty()/dayCnt);
								materialUse.setQty(Float.parseFloat(qty));
								materialUse.setScheduleDate(tempDate);
								materialUse.setWorkshopId(mesPlanSchedule.getWorkshopId());
								materialUse.setErpBillNo(mesPlanDevice.getErpBillNo());
								//materialUse.setBillNo(mesPlanDevice.getBillNo());
								materialUse.setBillNo("");
								materialUse.setBatchNo(bom.getBatchNo());
								materialUse.setBatchProp1(bom.getBatchProp1());
								materialUse.setBatchProp2(bom.getBatchProp2());
								materialUse.setSupplier(bom.getSuplierId());
								materialUse.setUnit(bom.getUnit());
								materialUse.setUnitCode(bom.getUnitCode());
								mesPlanMaterialUseService.updateUseQtyByUk(materialUse);
							}
							//tempDate = DateUtil.add(tempDate, Calendar.DAY_OF_MONTH, 1);
							//do{}while(tempDate.compareTo(mesPlanDevice.getRealEndDate())<=0);
					
						
						mesPlanDevice.setMaterialUseStatus("10");
						mesPlanDeviceService.updateMaterialUseStatus(mesPlanDevice);
					}
				}
				
			}
		}
		return null;
	}
}