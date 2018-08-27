/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.synth.SynthColorChooserUI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.dao.MesPlanDispatchDetDao;
import com.bmsmart.mes.plan.dao.MesPlanDispatchHeadDao;
import com.bmsmart.mes.plan.service.MesPlanBomService;
import com.bmsmart.mes.plan.service.MesPlanDispatchBomService;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanDispatchHeadService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
/**
 * 厂级调度明细Service
 * @author 周奇志
 * @version 2017-08-07
 */
 @Transactional(readOnly = true)
@Service("mesPlanDispatchDetService")
public class MesPlanDispatchDetServiceImpl extends MesCrudService<MesPlanDispatchDetDao, MesPlanDispatchDet> implements MesPlanDispatchDetService{
  @Autowired
  private MesPlanDispatchDetDao mesPlanDispatchDetDao;
  @Autowired
  private MesPlanMonthService mesPlanMonthService;
  @Autowired
  private MesPlanDispatchHeadService mesPlanDispatchHeadService;
  
  @Autowired
  private MesPlanBomService mesPlanBomService;
  
  @Autowired
  private MesPlanDispatchBomService mesPlanDispatchBomService;
  
  
	public MesPlanDispatchDet get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanDispatchDet> findList(MesPlanDispatchDet mesPlanDispatchDet) {
		return super.findList(mesPlanDispatchDet);
	}
	
	
	public  PageInfo<MesPlanDispatchDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanDispatchDet mesPlanDispatchDet) {
		super.save(mesPlanDispatchDet);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanDispatchDet mesPlanDispatchDet) {
		super.delete(mesPlanDispatchDet);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
		MesPlanDispatchDet det = get(id);
	   mesPlanDispatchDetDao.deleteById(id);
	   //重新计算数量
	   updateSheetDispatchQty(det);
	   updateErpSheetDispatchQty(null,det);
	}
	
	
	
	@Override
	public double findSheetQtyTotal(String sheetId) {
		// TODO Auto-generated method stub
		return mesPlanDispatchDetDao.findSheetQtyTotal(sheetId);
	}

	

	@Override
	public Integer updateSheetDispatchQty(MesPlanDispatchDet retDet)  {
		double dispatchQtyTotal = findSheetQtyTotal(retDet.getSheetId());
		MesPlanDispatchHead headPara =  new MesPlanDispatchHead();
		headPara.setSheetId(retDet.getSheetId());
		headPara.setDispatchQtySum(dispatchQtyTotal);
		mesPlanDispatchHeadService.updateDispatchQty(headPara);
		return 1;
	}
	
	@Override
	public Integer updateErpSheetDispatchQty(MesPlanDispatchHead mesPlanDispatchHead,MesPlanDispatchDet mesPlanDispatchDet) throws RuntimeException  {
		MesPlanDispatchHead head ;
		if (mesPlanDispatchHead == null){
			MesPlanDispatchHead planHeadPara = new MesPlanDispatchHead();
			planHeadPara.setSheetId(mesPlanDispatchDet.getSheetId());
			head = mesPlanDispatchHeadService.getByUk(planHeadPara);
		}else{
			head = mesPlanDispatchHead;
		}
		Double erpDispatchQtyTotal = mesPlanDispatchHeadService.findErpSheetQtyTotal(head.getErpBillNo());
		MesPlanMonth  monthPara = new MesPlanMonth();
		monthPara.setErpBillNo(head.getErpBillNo());
		MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(monthPara);
		//检查数量是否超过订单总数量
//		if (mesPlanMonth.getQty() < erpDispatchQtyTotal){
//			throw new RuntimeException("调度数量超过订单总数"); 
//		}
		monthPara.setDispatchQty(erpDispatchQtyTotal);
		
		mesPlanMonthService.updateDispatchQty(monthPara);
		return 1;
	}
	
	public MesPlanDispatchDet getByBillNo(String billNo){
		return mesPlanDispatchDetDao.getByBillNo(billNo);
	}
	
	
	public  PageInfo<MesPlanDispatchDet> findPage(int pageNum, int pageSize,	MesPlanDispatchDet mesPlanDispatchDet){
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanDispatchDet> demoTestList=mesPlanDispatchDetDao.findAllList(mesPlanDispatchDet);
		PageInfo<MesPlanDispatchDet> page = new PageInfo<MesPlanDispatchDet>(demoTestList);
		return page;
	}
	
	
	public Integer updateWorkCenterQty(MesPlanDispatchDet mesPlanDispatchDet){
		return mesPlanDispatchDetDao.updateWorkCenterQty(mesPlanDispatchDet);
	}
	/**
	 * 完工数量由报工人员报工上来，不根据下级计算
	 */
	public Integer updateFinishQty(MesPlanDispatchDet mesPlanDispatchDet){
		return 1;
		//return mesPlanDispatchDetDao.updateFinishQty(mesPlanDispatchDet);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public MesPlanDispatchHead saveDet(MesPlanDispatchDet mesPlanDispatchDet, String sheetId, String erpId,String remarks)
			throws Exception {
		MesPlanDispatchDet retDet;
		MesPlanDispatchHead mesPlanDispatchHead = null;
		if (StringUtil.isBlank(mesPlanDispatchDet.getId())){
			MesPlanMonth mesPlanMonth = mesPlanMonthService.get(erpId);
			if (StringUtil.isBlank(sheetId)){
				sheetId = getNewSheetId();
				mesPlanDispatchHead = new MesPlanDispatchHead();
				mesPlanDispatchHead.setSheetId(sheetId);
				mesPlanDispatchHead.setErpBillNo(mesPlanMonth.getErpBillNo());
				mesPlanDispatchHead.setDispatchQtySum(mesPlanDispatchDet.getDispatchQty());
				mesPlanDispatchHead.setSheetStatus(MesPlanDispatchHead.SHEET_STATUS_NEW);
				mesPlanDispatchHead.setRemarks(remarks);
				mesPlanDispatchHeadService.save(mesPlanDispatchHead);
				
				
//				MesPlanDispatchBom  mesPlanDispatchBom = new MesPlanDispatchBom();
//				 mesPlanDispatchBom.setSheetId(sheetId);
//				 List<MesPlanDispatchBom> mesPlanDispatchBomList = mesPlanDispatchBomService.findList(mesPlanDispatchBom);
				 
				MesPlanBom mesPlanBom = new MesPlanBom();
				 mesPlanBom.setErpBillNo(mesPlanMonth.getErpBillNo());
				 List<MesPlanBom> mesPlanBomList = mesPlanBomService.findList(mesPlanBom);
				 if (mesPlanBomList != null){
					 for (MesPlanBom bom:mesPlanBomList){
						 MesPlanDispatchBom  newBom =  new MesPlanDispatchBom();
						 newBom.setSheetId(sheetId);
						 newBom.setErpBillNo(bom.getErpBillNo());
						 newBom.setRawGoodsId(bom.getRawGoodsId());
						 newBom.setRawGoodsName(bom.getRawGoodsName());
						 newBom.setSuplierId(bom.getSuplierId());
						 newBom.setRawGoodsType(bom.getRawGoodsType());
						 newBom.setBatchNo(StringUtils.isBlank(bom.getBatchNo())?"-":bom.getBatchNo());
						 newBom.setBatchProp1(bom.getBatchProp1());
						 newBom.setBatchProp2(bom.getBatchProp2());
						 mesPlanDispatchBomService.save(newBom); 
					 }
				 }
				
			} 
			else{
				mesPlanDispatchHead = getDispatchHeadBySheetId(sheetId);
			}
			//mesPlanDispatchHead.setMesPlanMonth(mesPlanMonth);//设置订单对象，下面做校验用
//				//新增
			mesPlanDispatchDet.setDispatchStatus("0");
			mesPlanDispatchDet.setSheetId(sheetId);
			mesPlanDispatchDet.setBillNo(getNewBillNo(mesPlanMonth.getErpBillNo()));
			retDet = mesPlanDispatchDet;			
		}
		else{
			mesPlanDispatchHead = getDispatchHeadBySheetId(sheetId);
			
			//修改
			retDet = get(mesPlanDispatchDet.getId());
			retDet.setDeviceQty(mesPlanDispatchDet.getDeviceQty());// 设备数
			retDet.setDeviceChangeQty(mesPlanDispatchDet.getDeviceChangeQty());// 换产品换备数
			retDet.setDispatchQty(mesPlanDispatchDet.getDispatchQty());// 调度数量
			retDet.setWorkcenterId(mesPlanDispatchDet.getWorkcenterId());// 工作中心
			retDet.setPlanBeginDate(mesPlanDispatchDet.getPlanBeginDate());// 开始日期
			retDet.setPlanEndDate(mesPlanDispatchDet.getPlanEndDate());// 结束日期
			retDet.setDeviceChangeGoodsId(mesPlanDispatchDet.getDeviceChangeGoodsId());
			retDet.setDeviceRequire(mesPlanDispatchDet.getDeviceRequire());
			retDet.setCapacityInput(mesPlanDispatchDet.getCapacityInput());
			//整理车间属性
			retDet.setArrangeGroup(mesPlanDispatchDet.getArrangeGroup());
			retDet.setShiftId(mesPlanDispatchDet.getShiftId());
			retDet.setShiftStyle(mesPlanDispatchDet.getShiftStyle());
			retDet.setArrangeUnitRate(mesPlanDispatchDet.getArrangeUnitRate());
			retDet.setShiftBeginTime(mesPlanDispatchDet.getShiftBeginTime());
			retDet.setShiftEndTime(mesPlanDispatchDet.getShiftEndTime());
			retDet.setDispatchQtyPackage(mesPlanDispatchDet.getDispatchQtyPackage());
		}
		save(retDet);
		//修改调度单头分发的数量
		updateSheetDispatchQty(retDet);
		//修改订单已分发数量
		updateErpSheetDispatchQty(mesPlanDispatchHead,retDet);
		return mesPlanDispatchHead;
	}
	
	
	public MesPlanDispatchHead getDispatchHeadBySheetId(String sheetId) {
		MesPlanDispatchHead mesPlanDispatchHead;
		MesPlanDispatchHead paraHead = new MesPlanDispatchHead();
		paraHead.setSheetId(sheetId);
		mesPlanDispatchHead = mesPlanDispatchHeadService.getByUk(paraHead);
		return mesPlanDispatchHead;
	}

	
	public synchronized String getNewSheetId(){
		Random r = new Random();
		return "DP"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}
	
	public String getNewBillNo(String erpBillNo){
		Integer index =0 ;
		String indexStr = "";
		String billno = "";
		MesPlanDispatchDet mesPlanDispatchDet = null;
		
		String maxBillNo = mesPlanDispatchDetDao.getMaxBillNo(erpBillNo+"-");
		if (StringUtils.isNotEmpty(maxBillNo)){
			Integer index2 = maxBillNo.lastIndexOf("-");
			if (index2>0){
				index = Integer.parseInt(maxBillNo.substring(index2+1));
			}
		}
		
		do{
			index++;
			indexStr = index<=9?"0"+index.toString():index.toString();
			billno = (erpBillNo+"-")+indexStr;
			mesPlanDispatchDet= getByBillNo(billno);
		}while(mesPlanDispatchDet != null && StringUtil.isNotBlank( mesPlanDispatchDet.getBillNo()));
		
		return billno;
	}

	public MesPlanMonth getMesPlanMonthByDispatchId(String id) {
		//return mesPlanDispatchDetDao.getMesPlanMonthByDispatchId(id);
		return null;
	}

	public MesPlanMonth getMesPlanMonthByBillNo(String billNo) {
		//return mesPlanDispatchDetDao.getMesPlanMonthByDispatchId(billNo);
		return null;
	}

	@Override
	public int updateDispatchStatusById(MesPlanDispatchDet mesPlanDispatchDet) {
		int cnt =  mesPlanDispatchDetDao.updateDispatchStatusById(mesPlanDispatchDet); 
		//取消后同步报工数量数据
		if(MesPlanDispatchHead.SHEET_STATUS_CANCEL.equals(mesPlanDispatchDet.getDispatchStatus())){
			//重新计算数量
			MesPlanDispatchDet det = get(mesPlanDispatchDet.getId());
			updateSheetDispatchQty(det);
			updateErpSheetDispatchQty(null,det);
		}
		return cnt;
	}

	@Override
	public int updateDispatchStatusBySheetId(MesPlanDispatchDet mesPlanDispatchDet) {
		return mesPlanDispatchDetDao.updateDispatchStatusById(mesPlanDispatchDet);
	}

	@Override
	public int deleteBySheetId(String sheetId) {
		return mesPlanDispatchDetDao.deleteBySheetId(sheetId);
	}

	@Override
	public void checkBatchDet(List<MesPlanDispatchDet> mesPlanDispatchDetList) {
		 if (mesPlanDispatchDetList != null){
			 for (MesPlanDispatchDet det : mesPlanDispatchDetList){
				 updateDispatchStatusById(det);
			 }
			 
		 }
		
	}
 
	
}