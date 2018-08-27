/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.DateUtil;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.export.PlanWorkFinishedProvider;
import com.bmsmart.mes.plan.dao.MesPlanDeviceDao;
import com.bmsmart.mes.plan.dao.MesPlanDispatchDetDao;
import com.bmsmart.mes.plan.dao.MesPlanMonthDao;
import com.bmsmart.mes.plan.dao.MesPlanShiftDetDao;
import com.bmsmart.mes.plan.service.MesPlanShiftDetService;
/**
 * mes_plan_shift_detService
 * @author zhouqz
 * @version 2017-09-04
 */
 @Transactional(readOnly = true)
@Service("mesPlanShiftDetService")
public class MesPlanShiftDetServiceImpl extends MesCrudService<MesPlanShiftDetDao, MesPlanShiftDet> implements MesPlanShiftDetService{
  @Autowired
  private MesPlanShiftDetDao mesPlanShiftDetDao;
  @Autowired
  private MesPlanDeviceDao mesPlanDeviceDao;
  @Autowired
  private MesPlanDispatchDetDao mesPlanDispatchDetDao;
  @Autowired
  private MesPlanMonthDao mesPlanMonthDao;
	public MesPlanShiftDet get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanShiftDet> findList(MesPlanShiftDet mesPlanShiftDet) {
		return super.findList(mesPlanShiftDet);
	}
	
	
	public  PageInfo<MesPlanShiftDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanShiftDet mesPlanShiftDet) {
		super.save(mesPlanShiftDet);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanShiftDet mesPlanShiftDet) {
		super.delete(mesPlanShiftDet);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanShiftDetDao.deleteById(id);
	}
	
	public MesPlanShiftDetDao getMesPlanShiftDetDao() {
	   return mesPlanShiftDetDao;
    }
    public void setMesPlanShiftDetDao(MesPlanShiftDetDao mesPlanShiftDetDao) {
	    this.mesPlanShiftDetDao = mesPlanShiftDetDao;
    }

	@Override
	public List<MesPlanShiftDet> findALLList(MesPlanShiftDet mesPlanShiftDet) {
		return mesPlanShiftDetDao.findAllList(mesPlanShiftDet);
	}

	@Override
	public MesPlanShiftDet getbyBillNo(String billNo) {
		// TODO Auto-generated method stub
		return mesPlanShiftDetDao.getByBillNo(billNo);
	}

	@Override
	public int updateShiftQtyById(MesPlanShiftDet mesPlanShiftDet) {
		 
		return mesPlanShiftDetDao.updateShiftQtyById(mesPlanShiftDet);
	}

	@Override
	public List<MesPlanShiftDet> getEmptyShift(MesPlanDevice mesPlanDevice) {
		List<MesPlanShiftDet>   detList = new ArrayList<MesPlanShiftDet>();
		for (Integer i= 1 ; i <= 3 ; i ++){
			MesPlanShiftDet det = new MesPlanShiftDet();
			det.setSrcBillNo(mesPlanDevice.getBillNo());
			det.setErpBillNo(mesPlanDevice.getErpBillNo());
			det.setShiftId(i.toString());
			det.setShiftQty(0);
			det.setFinishQty(0);
			detList.add(det);
		}
		return detList;
	}

	public Float calcQty(Float qty ,Date beginDate,Date endDate,Integer shiftQty){
		long day = DateUtil.compare(beginDate, endDate, Calendar.DATE)+1;
		if (day > 0.0 && shiftQty > 0.0){
			DecimalFormat    df   = new DecimalFormat("######0.00");   
			return Float.parseFloat(df.format( qty/day/shiftQty));
		}else{
			return 0F;
		}
	} 
	@Override
	public MesPlanShiftDet getEmptyShiftOne(MesPlanShiftHead mesPlanShiftHead, MesPlanDevice mesPlanDevice) {
		MesPlanShiftDet det = new MesPlanShiftDet();
		det.setSrcBillNo(mesPlanDevice.getBillNo());
		det.setErpBillNo(mesPlanDevice.getErpBillNo());
		det.setShiftId(mesPlanShiftHead.getShiftId());
		//智能计算数量
		if (mesPlanShiftHead.getWorkshopId() != MesPlanShiftHead.WORKSHOP_ARRANGE){
			det.setShiftQty(calcQty(mesPlanDevice.getWorkCenterQty() ,mesPlanDevice.getRealBeginDate(),mesPlanDevice.getRealEndDate(),3));
		}else{
			det.setShiftQty(mesPlanDevice.getWorkCenterQty());
		}
		det.setFinishQty(0);
		return det;
	}

	@Override
	public int checkBatchDet(List<MesPlanShiftDet> mesPlanShiftDetList, String sheetId) {
		
		for(MesPlanShiftDet det:mesPlanShiftDetList){
			mesPlanShiftDetDao.updateShiftStatus(det);
		}
		return 1;
	}

	@Override
	public int updateShiftFinishStatus(List<MesPlanShiftDet> mesPlanShiftDetList, String sheetId) {
		for(MesPlanShiftDet det:mesPlanShiftDetList){
			mesPlanShiftDetDao.updateShiftFinishStatus(det);
		}
		return 1;
	}
	
	@Override
	public int updateShiftCancelStatus(List<MesPlanShiftDet> mesPlanShiftDetList, String sheetId) {
		for(MesPlanShiftDet det:mesPlanShiftDetList){
			mesPlanShiftDetDao.updateShiftCancelStatus(det);
		}
		return 1;
	}

	@Override
	public int validateCanFinish(int level, String billNo) {
		if (StringUtils.isBlank(billNo)){
			throw new RuntimeException("单号不能为空!");
		}
		if (level == 1){
			MesPlanDispatchDet detPara = new MesPlanDispatchDet();
			detPara.setErpBillNo(billNo);
			detPara.setUnfinished("1");
			List<MesPlanDispatchDet> detList = mesPlanDispatchDetDao.findList(detPara);
			return detList.size();
		}else if (level == 2){
			MesPlanDevice devicePara = new MesPlanDevice();
			devicePara.setSrcBillNo(billNo);
			devicePara.setUnfinished("1");
			List<MesPlanDevice> detList = mesPlanDeviceDao.findList(devicePara);
			return detList.size();
		}else if(level == 3){
			MesPlanShiftDet mesPlanShiftDet = new MesPlanShiftDet();
			mesPlanShiftDet.setSrcBillNo(billNo);
			mesPlanShiftDet.setUnfinished("1");
			List<MesPlanShiftDet> mesPlanShiftDetList = mesPlanShiftDetDao.findList(mesPlanShiftDet);
			return mesPlanShiftDetList.size();
		}
		else if(level == 4){
			//暂无限制条件
		}
		return 0;
	}

	@Override
	public Map<String, Object> getErpBillDetail(String erpBillNo) {
		if (StringUtils.isBlank(erpBillNo)){
			return null; 
		}
		HashMap<String, Object> rtnMap = new HashMap<String, Object>();
		MesPlanMonth paraMonth = new MesPlanMonth();
		paraMonth.setErpBillNo(erpBillNo);
		MesPlanMonth mesPlanMonth = mesPlanMonthDao.getByUk(paraMonth);
		rtnMap.put("mesPlanMonth", mesPlanMonth);
			
		MesPlanDispatchDet detPara = new MesPlanDispatchDet();
		detPara.setErpBillNo(erpBillNo);
		List<MesPlanDispatchDet> dispatchList = mesPlanDispatchDetDao.findList(detPara);
		rtnMap.put("dispatchList", dispatchList);
		
		MesPlanDevice devicePara = new MesPlanDevice();
		devicePara.setErpBillNo(erpBillNo);
		List<MesPlanDevice> detList = mesPlanDeviceDao.findList(devicePara);
		rtnMap.put("detList", detList);
		
		MesPlanShiftDet mesPlanShiftDet = new MesPlanShiftDet();
		mesPlanShiftDet.setErpBillNo(erpBillNo);
		List<MesPlanShiftDet> shiftDetList = mesPlanShiftDetDao.findAllList(mesPlanShiftDet);
		rtnMap.put("shiftDetList", shiftDetList);
		
		return rtnMap;
	}
	
}