/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeDet;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeHead;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftSheet;
import com.bmsmart.mes.plan.dao.MesPlanArrangeDetDao;
import com.bmsmart.mes.plan.dao.MesPlanArrangeHeadDao;
import com.bmsmart.mes.plan.service.MesPlanArrangeDetService;
import com.bmsmart.mes.plan.service.MesPlanArrangeHeadService;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanDispatchHeadService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
/**
 * mes_plan_arrange_headService
 * @author zhouqz
 * @version 2018-03-13
 */
 @Transactional(readOnly = true)
@Service("mesPlanArrangeHeadService")
public class MesPlanArrangeHeadServiceImpl extends MesCrudService<MesPlanArrangeHeadDao, MesPlanArrangeHead> implements MesPlanArrangeHeadService{
  @Autowired
 private MesPlanArrangeHeadDao mesPlanArrangeHeadDao;
  @Autowired
  private MesPlanArrangeDetService mesPlanArrangeDetService;
  @Autowired
  private MesPlanDispatchDetService mesPlanDispatchDetService;
  @Autowired
  private MesPlanMonthService mesPlanMonthService;
  
	public MesPlanArrangeHead get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanArrangeHead> findList(MesPlanArrangeHead mesPlanArrangeHead) {
		return super.findList(mesPlanArrangeHead);
	}
	
	
	public  PageInfo<MesPlanArrangeHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanArrangeHead mesPlanArrangeHead) {
		super.save(mesPlanArrangeHead);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanArrangeHead mesPlanArrangeHead) {
		super.delete(mesPlanArrangeHead);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanArrangeHeadDao.deleteById(id);
	}
	
	public MesPlanArrangeHeadDao getMesPlanArrangeHeadDao() {
	   return mesPlanArrangeHeadDao;
    }
    public void setMesPlanArrangeHeadDao(MesPlanArrangeHeadDao mesPlanArrangeHeadDao) {
	    this.mesPlanArrangeHeadDao = mesPlanArrangeHeadDao;
    }

	@Override
	public PageInfo<MesPlanArrangeHead> findPage(int pageno, int pagesize, MesPlanArrangeHead mesPlanArrangeHead) {
		PageHelper.startPage(pageno,pagesize);// 使用order e.g.
		List<MesPlanArrangeHead> demoTestList=dao.findList(mesPlanArrangeHead);
		 PageInfo<MesPlanArrangeHead> page = new PageInfo<MesPlanArrangeHead>(demoTestList);
		return page;
	}

	@Override
	public MesPlanArrangeHead getSheetById(String id) {
		MesPlanArrangeHead rtnHead = mesPlanArrangeHeadDao.get(id);
		if (rtnHead != null && StringUtils.isNotEmpty(rtnHead.getSheetId())){
			MesPlanArrangeDet detPara  =  new MesPlanArrangeDet();
			detPara.setSheetId(detPara.getSheetId());
			List<MesPlanArrangeDet> detList = mesPlanArrangeDetService.findList(detPara);
			rtnHead.setMesPlanArrangeDetList(detList);
		}
		return rtnHead;
	}

	@Override
	public MesPlanArrangeHead getSheetBySheetId(String sheetId) {
		MesPlanArrangeHead rtnHead = mesPlanArrangeHeadDao.getHeadBySheetId(sheetId);
		if (rtnHead != null && StringUtils.isNotEmpty(rtnHead.getSheetId())){
			MesPlanArrangeDet detPara  =  new MesPlanArrangeDet();
			detPara.setSheetId(rtnHead.getSheetId());
			List<MesPlanArrangeDet> detList = mesPlanArrangeDetService.findList(detPara);
			rtnHead.setMesPlanArrangeDetList(detList);
		}
		return rtnHead;
	}

	@Override
	public MesPlanArrangeHead getSheetByUk(MesPlanTempShiftHead mesPlanTempShiftHead) {
		MesPlanArrangeHead rtnHead = mesPlanArrangeHeadDao.getHeadByUk(mesPlanTempShiftHead);
		if (rtnHead != null && StringUtils.isNotEmpty(rtnHead.getSheetId())){
			MesPlanArrangeDet detPara  =  new MesPlanArrangeDet();
			detPara.setSheetId(detPara.getSheetId());
			List<MesPlanArrangeDet> detList = mesPlanArrangeDetService.findList(detPara);
			rtnHead.setMesPlanArrangeDetList(detList);
		}
		return rtnHead;
	}

	@Override
	public void saveBatchDet(Date shiftDate, MesPlanArrangeHead mesPlanArrangeHead) {
		if (shiftDate != null && mesPlanArrangeHead.getShiftDate() == null){
			mesPlanArrangeHead.setShiftDate(shiftDate);
		}
		
		if (mesPlanArrangeHead.getIsNewRecord()){
			String sheetId = getNewSheetId();
			mesPlanArrangeHead.setSheetId(sheetId);
		}
		save(mesPlanArrangeHead);
		if (mesPlanArrangeHead.getMesPlanArrangeDetList() != null){
			for(MesPlanArrangeDet det:mesPlanArrangeHead.getMesPlanArrangeDetList()){
				if (StringUtils.isEmpty(det.getSheetId())){
					det.setSheetId(mesPlanArrangeHead.getSheetId());
				}
				mesPlanArrangeDetService.save(det);
			}
		}
		
	}
	
	
	public synchronized String getNewSheetId(){
		Random r = new Random();
		return "AR"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}

	@Override
	public void deleteSheet(String id, String sheetId) {
		if (StringUtils.isBlank(sheetId)){
			throw new RuntimeException("sheetId不能为空");
		}
		mesPlanArrangeDetService.deleteBySheetId(sheetId);
		mesPlanArrangeHeadDao.deleteById(id);
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public String save_chk_det(String detId) throws Exception  {
		MesPlanArrangeDet arrangeDet = mesPlanArrangeDetService.get(detId);
		if (arrangeDet.getQty1() == null || arrangeDet.getQty2() == null){
			throw new Exception("班次数量不能为空");
		}
		if (StringUtil.isBlank(arrangeDet.getArrangeErpBillNo())){
			throw new Exception("整理生产订单号不能为空");
		}
		//检查状态标志
		if (StringUtils.isNotEmpty(arrangeDet.getShiftStatus()) && Integer.parseInt(arrangeDet.getShiftStatus())>0){
			throw new RuntimeException("数据状态不正确,请刷新后重试!");
		}
		if (StringUtils.isEmpty(arrangeDet.getArrangeErpBillNo())){
			throw new RuntimeException("整理车间订单号不能为空!");
		}
		
		MesPlanArrangeHead arrangeHead  = getSheetBySheetId(arrangeDet.getSheetId());
		//表体
		List<MesPlanDispatchDet> mesPlanDispatchDetList = new ArrayList<MesPlanDispatchDet>();
		List<MesPlanDispatchDet> mesPlanDispatchDetList2 = new ArrayList<MesPlanDispatchDet>();
		String[] technicArr = arrangeHead.getTechnicId().split(",");
		for (int i=0;i<technicArr.length;i++){
			MesPlanDispatchDet  mesPlanDispatchDet = new MesPlanDispatchDet();
			mesPlanDispatchDet.setWorkcenterId(arrangeDet.getWorkcenterId());
			mesPlanDispatchDet.setDispatchStatus("0");
			mesPlanDispatchDet.setPlanBeginDate(arrangeHead.getShiftDate());
			mesPlanDispatchDet.setPlanEndDate(arrangeHead.getShiftDate());
			mesPlanDispatchDet.setCapacityInput(80F);
			mesPlanDispatchDet.setArrangeGroup(technicArr[i]);
			mesPlanDispatchDet.setDispatchQty(arrangeDet.getQty1());
			mesPlanDispatchDet.setShiftId(arrangeDet.getShiftId1());
			mesPlanDispatchDet.setShiftStyle(arrangeDet.getShiftStyle1());
			mesPlanDispatchDet.setArrangeUnitRate(36.5760F);
			mesPlanDispatchDet.setShiftBeginTime("00:00");
			mesPlanDispatchDet.setShiftEndTime("23:59");
			mesPlanDispatchDet.setDispatchQtyPackage(arrangeDet.getQty1()/36.5760F);
			
			
			MesPlanDispatchDet  mesPlanDispatchDet2 = new MesPlanDispatchDet();
			mesPlanDispatchDet2.setWorkcenterId(arrangeDet.getWorkcenterId());
			mesPlanDispatchDet2.setDispatchStatus("0");
			mesPlanDispatchDet2.setPlanBeginDate(arrangeHead.getShiftDate());
			mesPlanDispatchDet2.setPlanEndDate(arrangeHead.getShiftDate());
			mesPlanDispatchDet2.setCapacityInput(80F);
			mesPlanDispatchDet2.setArrangeGroup(technicArr[i]);
			mesPlanDispatchDet2.setDispatchQty(arrangeDet.getQty2());
			mesPlanDispatchDet2.setShiftId(arrangeDet.getShiftId2());
			mesPlanDispatchDet2.setShiftStyle(arrangeDet.getShiftStyle2());
			mesPlanDispatchDet2.setArrangeUnitRate(36.5760F);
			mesPlanDispatchDet2.setShiftBeginTime("00:00");
			mesPlanDispatchDet2.setShiftEndTime("23:59");
			mesPlanDispatchDet2.setDispatchQtyPackage(arrangeDet.getQty2()/36.5760F);
			if (arrangeDet.getQty1() !=0){
				mesPlanDispatchDetList.add(mesPlanDispatchDet);
			}
			if (arrangeDet.getQty2() !=0){
				mesPlanDispatchDetList2.add(mesPlanDispatchDet2);
			}
		}
		MesPlanMonth monthPara = new MesPlanMonth();
		monthPara.setErpBillNo(arrangeDet.getArrangeErpBillNo());
		String erpId  = mesPlanMonthService.getByUk(monthPara).getId();
		//生成班次1单据
		String sheet1="",sheet2="";
		if (mesPlanDispatchDetList.size()>0){
			MesPlanDispatchHead mesPlanDispatchHead1 = mesPlanDispatchDetService.saveDet(mesPlanDispatchDetList.get(0), "", erpId,getRemarks(arrangeHead.getShiftDate(),"班次1"));
			String sheetId = mesPlanDispatchDetList.get(0).getSheetId();
			for (int i=1 ;i<mesPlanDispatchDetList.size();i++){
				mesPlanDispatchDetList.get(i).setSheetId(sheetId);
				mesPlanDispatchDetService.saveDet(mesPlanDispatchDetList.get(i),sheetId, erpId,getRemarks(arrangeHead.getShiftDate(),"班次1"));
			}
			sheet1 = mesPlanDispatchHead1.getSheetId();
		}
		//生成班次2单据
		if (mesPlanDispatchDetList2.size()>0){
			MesPlanDispatchHead mesPlanDispatchHead2 = mesPlanDispatchDetService.saveDet(mesPlanDispatchDetList2.get(0), "", erpId,getRemarks(arrangeHead.getShiftDate(),"班次2"));
			String sheetId = mesPlanDispatchDetList2.get(0).getSheetId();
			for (int i=1 ;i<mesPlanDispatchDetList2.size();i++){
				mesPlanDispatchDetList2.get(i).setSheetId(sheetId);
				mesPlanDispatchDetService.saveDet(mesPlanDispatchDetList2.get(i),sheetId, erpId,getRemarks(arrangeHead.getShiftDate(),"班次2"));
			}
			sheet2 = mesPlanDispatchHead2.getSheetId();
		}
		
		arrangeDet.setShiftStatus("1");
		arrangeDet.setPlanSheetId1(sheet1);
		arrangeDet.setPlanSheetId2(sheet2);
		mesPlanArrangeDetService.updateSheetStatus2SheetId(arrangeDet);
		return arrangeDet.getSheetId();
	}
	
	private String getRemarks(Date date,String shiftName){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String DateStr = sdf.format(date);
		return DateStr+"自动生成["+shiftName+"]计划";
	}
}