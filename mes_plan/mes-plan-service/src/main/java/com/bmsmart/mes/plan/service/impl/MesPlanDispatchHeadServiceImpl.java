/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.dao.MesPlanDispatchDetDao;
import com.bmsmart.mes.plan.dao.MesPlanDispatchHeadDao;
import com.bmsmart.mes.plan.dao.MesPlanMonthDao;
import com.bmsmart.mes.plan.service.MesPlanBomService;
import com.bmsmart.mes.plan.service.MesPlanDispatchBomService;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanDispatchHeadService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
/**
 * 厂级调度单头Service
 * @author 周奇志
 * @version 2017-08-07
 */
 @Transactional(readOnly = true)
@Service("mesPlanDispatchHeadService")
public class MesPlanDispatchHeadServiceImpl extends MesCrudService<MesPlanDispatchHeadDao, MesPlanDispatchHead> implements MesPlanDispatchHeadService{
	@Autowired
  	private MesPlanDispatchHeadDao mesPlanDispatchHeadDao;
	
	@Autowired
  	private MesPlanDispatchDetDao mesPlanDispatchDetDao;
	
	 
	@Autowired
	private MesPlanDispatchDetService mesPlanDispatchDetService;
	
	@Autowired
	private MesPlanMonthService mesPlanMonthService;
	
	@Autowired
	private MesPlanDispatchBomService mesPlanDispatchBomService;
	
	@Autowired
	private MesPlanBomService mesPlanBomService;
	
	
	public MesPlanDispatchHead get(String id) {
		return super.get(id);
	}
	public MesPlanDispatchHead getByUk(MesPlanDispatchHead head) {
		PageInfo<MesPlanDispatchHead> page =findPage(1,1,head);
		if (page != null && page.getList() != null && page.getList().size()>0){
			return page.getList().get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public MesPlanDispatchHead getSheet(MesPlanDispatchHead head) {
		MesPlanDispatchHead sheet = null;
		 if (StringUtil.isBlank(head.getId())){
			 sheet  = getByUk(head);
		 }else{
			 sheet = head;
		 }
		 if (sheet != null){
			 if (StringUtil.isNotBlank(sheet.getSheetId()))
			 {
				 MesPlanDispatchDet  dispatchDetPara =  new MesPlanDispatchDet();
				 dispatchDetPara.setSheetId(sheet.getSheetId());
				 List<MesPlanDispatchDet>  dispatchDetList = mesPlanDispatchDetService.findList(dispatchDetPara);
				 sheet.setMesPlanDispatchDetList(dispatchDetList);
				 //得到Bom
				 MesPlanDispatchBom  mesPlanDispatchBom = new MesPlanDispatchBom();
				 mesPlanDispatchBom.setSheetId(sheet.getSheetId());
				 List<MesPlanDispatchBom> mesPlanDispatchBomList = mesPlanDispatchBomService.findList(mesPlanDispatchBom);
				 //在明细表保存时已经生成bom了
//				 if (StringUtil.isNotBlank(sheet.getErpBillNo()) &&(mesPlanDispatchBomList == null || mesPlanDispatchBomList.size()==0)){
//					 mesPlanDispatchBomList = new ArrayList<MesPlanDispatchBom>();
//					 MesPlanBom mesPlanBom = new MesPlanBom();
//					 mesPlanBom.setErpBillNo(sheet.getErpBillNo());
//					 List<MesPlanBom> mesPlanBomList = mesPlanBomService.findList(mesPlanBom);
//					 if (mesPlanBomList != null){
//						 for (MesPlanBom bom:mesPlanBomList){
//							 MesPlanDispatchBom  newBom =  new MesPlanDispatchBom();
//							 newBom.setSheetId(sheet.getSheetId());
//							 newBom.setErpBillNo(bom.getErpBillNo());
//							 newBom.setRawGoodsId(bom.getRawGoodsId());
//							 newBom.setRawGoodsName(bom.getRawGoodsName());
//							 newBom.setSuplierId(bom.getSuplierId());
//							 newBom.setRawGoodsType(bom.getRawGoodsType());
//							 newBom.setBatchNo(bom.getBatchNo());
//							 newBom.setBatchProp1(bom.getBatchProp1());
//							 newBom.setBatchProp2(bom.getBatchProp2());
//							 //mesPlanDispatchBomService.save(newBom);只读事务，不能保存
//							 mesPlanDispatchBomList.add(newBom);
//						 }
//					 }
//				 }
				 sheet.setMesPlanDispatchBomList(mesPlanDispatchBomList);
			 }
			 
			 if (StringUtil.isNotBlank(sheet.getErpBillNo()))
			 {
				 MesPlanMonth mesPlanMonthPara = new MesPlanMonth();
				 mesPlanMonthPara.setErpBillNo(sheet.getErpBillNo());
				 MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(mesPlanMonthPara);
				 sheet.setMesPlanMonth(mesPlanMonth);
			 }
		 }
		 return sheet;
	}
	
	 
	public List<MesPlanDispatchHead> findList(MesPlanDispatchHead mesPlanDispatchHead) {
		return super.findList(mesPlanDispatchHead);
	}
	
	
	public  PageInfo<MesPlanDispatchHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Override
	public  PageInfo<MesPlanDispatchHead> findPage(int pageNum, int pageSize,
			MesPlanDispatchHead mesPlanDispatchHead){
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanDispatchHead> demoTestList=dao.findList(mesPlanDispatchHead);
		 PageInfo<MesPlanDispatchHead> page = new PageInfo<MesPlanDispatchHead>(demoTestList);
		return page;
	}
	
	
	@Transactional(readOnly = false)
	public void save(MesPlanDispatchHead mesPlanDispatchHead) {
		super.save(mesPlanDispatchHead);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanDispatchHead mesPlanDispatchHead) {
		super.delete(mesPlanDispatchHead);
	}
	@Transactional(readOnly = false)
	public int deleteById(String id){
		  mesPlanDispatchHeadDao.deleteById(id);
		  return 1;
	}
	
 
	
	public MesPlanDispatchHeadDao getMesPlanDispatchHeadDao() {
	   return mesPlanDispatchHeadDao;
    }
    public void setMesPlanDispatchHeadDao(MesPlanDispatchHeadDao mesPlanDispatchHeadDao) {
	    this.mesPlanDispatchHeadDao = mesPlanDispatchHeadDao;
    }
	@Override
	public Integer updateDispatchQty(MesPlanDispatchHead mesPlanDispatchHead) {
		return mesPlanDispatchHeadDao.updateDispatchQty(mesPlanDispatchHead);
		
	}

	@Override
	public Double findErpSheetQtyTotal(String erpBillNo) {
		return mesPlanDispatchHeadDao.findErpSheetQtyTotal(erpBillNo);
	}
	@Override
	public int updateSheetStatusById(MesPlanDispatchHead mesPlanDispatchHead) {
		//调度明细下发
		MesPlanDispatchHead head = get(mesPlanDispatchHead.getId());
		String erpBillNo = head.getErpBillNo();
		MesPlanDispatchDet det = new MesPlanDispatchDet();
		if(StringUtils.isBlank(mesPlanDispatchHead.getSheetId())){
			det.setSheetId(head.getSheetId());
		}else{
			det.setSheetId(mesPlanDispatchHead.getSheetId());
		}
		
		det.setDispatchStatus(mesPlanDispatchHead.getSheetStatus());		
		mesPlanDispatchDetDao.updateDispatchStatusBySheetId(det);
				
		//订单状态从计划修改成为下达
		MesPlanMonth mesPlanMonth = new MesPlanMonth();
		mesPlanMonth.setErpBillNo(erpBillNo);
		MesPlanMonth month = mesPlanMonthService.getByUk(mesPlanMonth);
		if ("10".equals(month.getPlanStatus()) || StringUtils.isBlank(month.getPlanStatus()) ){
			MesPlanMonth updatePlan  =  new MesPlanMonth();
			updatePlan.setId(month.getId());
			updatePlan.setPlanStatus("20");
			mesPlanMonthService.updateSheetStatusById(updatePlan);
		}
		
		//状态修改
		return mesPlanDispatchHeadDao.updateSheetStatusById(mesPlanDispatchHead);
	}
	@Override
	public int updateSheetStatusBySheetId(MesPlanDispatchHead mesPlanDispatchHead) {
		MesPlanDispatchDet det = new MesPlanDispatchDet();
		if(StringUtils.isBlank(mesPlanDispatchHead.getSheetId())){
			MesPlanDispatchHead head = get(mesPlanDispatchHead.getId());
			det.setSheetId(head.getSheetId());
		}else{
			det.setSheetId(mesPlanDispatchHead.getSheetId());
		}
		det.setDispatchStatus(mesPlanDispatchHead.getSheetStatus());		
		mesPlanDispatchDetDao.updateDispatchStatusBySheetId(det);

		return mesPlanDispatchHeadDao.updateSheetStatusBySheetId(mesPlanDispatchHead);
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteSheet(MesPlanDispatchHead head) throws Exception {
		//删除 单头
	   mesPlanDispatchHeadDao.deleteById(head.getId());
	   //删除明细
	   mesPlanDispatchDetService.deleteBySheetId(head.getSheetId());
	   //同步订单数量
	   mesPlanDispatchDetService.updateErpSheetDispatchQty(head,null);
	   return 1;
	}
	@Override
	public MesPlanDispatchHead getSheet(String sheetId) {
		MesPlanDispatchHead head = new MesPlanDispatchHead();
		head.setSheetId(sheetId);
		return getSheet(head);
	}
}