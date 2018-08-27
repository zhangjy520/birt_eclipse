/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryPageRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmType;
import com.bmsmart.mes.mesExternal.export.response.MdmQueryResponse;
import com.bmsmart.mes.mesExternal.export.response.vo.MdmStaffInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftWorker;
import com.bmsmart.mes.plan.export.PlanTaskGetProvider;
import com.bmsmart.mes.plan.dao.MesPlanShiftDetDao;
import com.bmsmart.mes.plan.dao.MesPlanShiftHeadDao;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanScheduleService;
import com.bmsmart.mes.plan.service.MesPlanShiftDetService;
import com.bmsmart.mes.plan.service.MesPlanShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanShiftUserService;
import com.bmsmart.mes.plan.service.MesPlanShiftWorkerService;
/**
 * 排班表头Service
 * @author zhouqz
 * @version 2017-09-04
 */
 @Transactional(readOnly = true)
@Service("mesPlanShiftHeadService")
public class MesPlanShiftHeadServiceImpl extends MesCrudService<MesPlanShiftHeadDao, MesPlanShiftHead> implements MesPlanShiftHeadService{
  @Autowired
 private MesPlanShiftHeadDao mesPlanShiftHeadDao;
  @Autowired
 private MesPlanScheduleService mesPlanScheduleService;
  @Autowired
 private MesPlanMonthService mesPlanMonthService;
  @Autowired
 private MesPlanShiftWorkerService mesPlanShiftWorkerService;
  @Autowired
 private MesPlanShiftDetService mesPlanShiftDetService;
  @Autowired
 private MesPlanShiftUserService mesPlanShiftUserService;
  @Autowired
  private MesPlanShiftDetDao mesPlanShiftDetDao;
  @Autowired
private MdmQueryProvider mdmQueryProvider;
	public MesPlanShiftHead getHead(String id) {
		return super.get(id);
	}
	
	public List<MesPlanShiftHead> findList(MesPlanShiftHead mesPlanShiftHead) {
		return super.findList(mesPlanShiftHead);
	}
	
	
	public  PageInfo<MesPlanShiftHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanShiftHead mesPlanShiftHead) {
		super.save(mesPlanShiftHead);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanShiftHead mesPlanShiftHead) {
		super.delete(mesPlanShiftHead);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanShiftHeadDao.deleteById(id);
	}
	
	public MesPlanShiftHeadDao getMesPlanShiftHeadDao() {
	   return mesPlanShiftHeadDao;
    }
    public void setMesPlanShiftHeadDao(MesPlanShiftHeadDao mesPlanShiftHeadDao) {
	    this.mesPlanShiftHeadDao = mesPlanShiftHeadDao;
    }

	@Override
	public MesPlanShiftSheet getSheetById(String id) {
		MesPlanShiftHead mesPlanShiftHead = getHead(id);
		MesPlanShiftSheet sheet = getSheet(mesPlanShiftHead);
		return sheet;
	}
	
	@Override
	public MesPlanShiftSheet getSheetByUk(MesPlanShiftHead headPara) {
		MesPlanShiftHead mesPlanShiftHead = getHeadByUk(headPara);
		if (mesPlanShiftHead == null){
			mesPlanShiftHead = headPara;
		}
		MesPlanShiftSheet sheet = getSheet(mesPlanShiftHead);
		return sheet;
	}
	private List<MesPlanShiftWorker> getWorkerListNew(String shiftStyleId){
		List<MesPlanShiftWorker> list= new ArrayList<MesPlanShiftWorker>();
		MdmQueryRequest request=new MdmQueryRequest();
		request.setMdmType(MdmType.STAFF_INFO);
		Map<String,String> qryMap = new HashMap<String,String>();
		qryMap.put("parentCode", shiftStyleId);
		request.setQueryMap(qryMap);
		MdmQueryResponse listCode	= mdmQueryProvider.queryList(request);
		
		
		List<MdmStaffInfo> useList= JSONFormatter.newInstance().toArray(listCode.getResultStr(), MdmStaffInfo.class);
		for(MdmStaffInfo use :useList){
			MesPlanShiftWorker worker = new MesPlanShiftWorker();
			worker.setUserId(use.getCode());
			worker.setWorktypeId(use.getWorkTypeCode());
			worker.setWorkStatus("1"); 
			worker.setUserName(use.getName());
			worker.setShiftStyle(shiftStyleId);
			list.add(worker);
		}
		//增加常日班数据
		MdmQueryResponse listCode2  = null;
		String shiftStyleIdNew = null;
		if (shiftStyleId.startsWith("C11") && !shiftStyleId.equals("C1104")){
			shiftStyleIdNew = "C1104";
			qryMap.put("parentCode", shiftStyleIdNew);
			listCode2	= mdmQueryProvider.queryList(request);
		}else if  (shiftStyleId.startsWith("C12") && !shiftStyleId.equals("C1204")){
			shiftStyleIdNew = "C1204";
			qryMap.put("parentCode", shiftStyleIdNew);
			listCode2	= mdmQueryProvider.queryList(request);
		}else if  (shiftStyleId.startsWith("C13") && !shiftStyleId.equals("C1304")){
			shiftStyleIdNew = "C1304";
			qryMap.put("parentCode", shiftStyleIdNew);
			listCode2	= mdmQueryProvider.queryList(request);
		}else if  (shiftStyleId.startsWith("C14") && !shiftStyleId.equals("C1404")){
			shiftStyleIdNew = "C1404";
			qryMap.put("parentCode", shiftStyleIdNew);
			listCode2	= mdmQueryProvider.queryList(request);
		}
		if (listCode2 != null){
			useList= JSONFormatter.newInstance().toArray(listCode2.getResultStr(), MdmStaffInfo.class);
			for(MdmStaffInfo use :useList){
				MesPlanShiftWorker worker = new MesPlanShiftWorker();
				worker.setUserId(use.getCode());
				worker.setWorktypeId(use.getWorkTypeCode());
				worker.setWorkStatus("1"); 
				worker.setUserName(use.getName());
				worker.setShiftStyle(shiftStyleIdNew);
				list.add(worker);
			}
		}
		
		
//		List<Map<String,Object>> useList= JSONFormatter.newInstance().toObject(listCode.getResultStr(), List.class);
//		for(Map<String,Object> use :useList){
//			MesPlanShiftWorker worker = new MesPlanShiftWorker();
//			worker.setUserId(use.get("code").toString());
//			worker.setWorktypeId(use.get("workTypeCode").toString());
//			worker.setWorkStatus("1"); 
//			worker.setUserName(use.get("name").toString());
//			list.add(worker);
//		}
		return list;
	}
	
	private MesPlanShiftSheet getSheet(MesPlanShiftHead mesPlanShiftHead) {
		MesPlanShiftSheet sheet = new MesPlanShiftSheet();
		MesPlanSchedule schedulePara = new MesPlanSchedule();
		schedulePara.setScheduleDate(mesPlanShiftHead.getShiftDate());
		schedulePara.setWorkshopId(mesPlanShiftHead.getWorkshopId());
		schedulePara.setShiftId(mesPlanShiftHead.getShiftId());
		schedulePara.setShiftStyle(mesPlanShiftHead.getShiftStyle());
		schedulePara.setWorkcenterId(mesPlanShiftHead.getWorkcenterId());
		//已下发的才显示
		schedulePara.setScheduleStatus("10");
		
		MesPlanShiftWorker workerPara = new MesPlanShiftWorker();
		List<MesPlanShiftWorker> workerList = null;
		if (StringUtil.isNotBlank(mesPlanShiftHead.getSheetId())){
			workerPara.setSheetId(mesPlanShiftHead.getSheetId());
			workerList= mesPlanShiftWorkerService.findList(workerPara);
		}else{
			workerList = getWorkerListNew(mesPlanShiftHead.getShiftStyle());
		}
		
		List<MesPlanDevice> mesPlanDeviceList = mesPlanScheduleService.findSchedualDeviceList(schedulePara);
		if (mesPlanDeviceList != null){
			getSheetDet(mesPlanShiftHead, mesPlanDeviceList);
		}
		
		sheet.setMesPlanShiftHead(mesPlanShiftHead);
		sheet.setMesPlanShiftWorkerList(workerList);
		sheet.setSchedualDeviceList(mesPlanDeviceList);
		return sheet;
	}

	private void getSheetDet(MesPlanShiftHead mesPlanShiftHead, List<MesPlanDevice> mesPlanDeviceList) {
		for (MesPlanDevice mesPlanDevice:mesPlanDeviceList){
			MesPlanShiftDet detPara = new MesPlanShiftDet();
			//
			detPara.setSrcBillNo(mesPlanDevice.getBillNo());
			detPara.setShiftDate(mesPlanShiftHead.getShiftDate());
			detPara.setWorkshopId(mesPlanShiftHead.getWorkshopId());
			List<MesPlanShiftDet> shiftDetList = mesPlanShiftDetService.findALLList(detPara);
			if (shiftDetList == null || shiftDetList.size() == 0){
				//shiftDetList = mesPlanShiftDetService.getEmptyShift(mesPlanDevice);
				shiftDetList = new ArrayList<MesPlanShiftDet>();
			} 
			boolean found = false;
			for(MesPlanShiftDet det:shiftDetList){
				if (det.getShiftId().equals(mesPlanShiftHead.getShiftId())){
					addShiftUser(mesPlanShiftHead.getWorkshopId(),mesPlanDevice, det);
					found = true;
					break;
				}
			}
			//没有本班次，首次排程，add班次
			if (!found){
				
				MesPlanShiftDet det = mesPlanShiftDetService.getEmptyShiftOne(mesPlanShiftHead,mesPlanDevice);
				shiftDetList.add(det);
				addShiftUser(mesPlanShiftHead.getWorkshopId(),mesPlanDevice, det);
			}
			 
			mesPlanDevice.setMesPlanShiftDetList(shiftDetList);
			
			MesPlanMonth mesPlanMonthPara = new MesPlanMonth();
			mesPlanMonthPara.setErpBillNo(mesPlanDevice.getErpBillNo());
			MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(mesPlanMonthPara);
			mesPlanDevice.setMesPlanMonth(mesPlanMonth);
		}
	}

	private void addShiftUser(String workshopId,MesPlanDevice mesPlanDevice, MesPlanShiftDet det) {
		List<MesPlanShiftUser> userlist = null;
		if (StringUtil.isNotBlank(det.getBillNo()))
		{
			MesPlanShiftUser userPara = new MesPlanShiftUser();
			userPara.setSrcBillNo(det.getBillNo());
			//
			userlist = mesPlanShiftUserService.findList(userPara);//本班次
		} 
		
		if (userlist == null ||userlist.size() == 0 ){
			userlist = mesPlanShiftUserService.getEmptyShiftUserList(workshopId,mesPlanDevice,det);
		}
		mesPlanDevice.setMesPlanShiftUserList(userlist);
	}

	@Override
	public MesPlanShiftHead getHeadByUk(MesPlanShiftHead mesPlanShiftHead) {
		return mesPlanShiftHeadDao.getHeadByUk(mesPlanShiftHead);
	}

	@Override
	public MesPlanShiftHead getHeadBySheetId(String sheetId) {
		// TODO Auto-generated method stub
		return mesPlanShiftHeadDao.getHeadBySheetId(sheetId);
	}

	public synchronized String getNewSheetId(){
		Random r = new Random();
		return "ZB"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}
	
	public String getNewBillNo(String erpBillNo){
		Integer index =0 ;
		String indexStr = "";
		String billno = "";
		MesPlanShiftDet mesPlanShiftDet = null;
		
		String maxBillNo = mesPlanShiftDetDao.getMaxBillNo(erpBillNo+"-");
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
			mesPlanShiftDet= mesPlanShiftDetService.getbyBillNo(billno);
		}while(mesPlanShiftDet != null && StringUtil.isNotBlank( mesPlanShiftDet.getBillNo()));
		
		return billno;
	}
	
	@Override
	public String saveSheet(MesPlanShiftHead mesPlanShiftHead, List<MesPlanShiftDet> mesPlanShiftDetList,List<MesPlanShiftWorker> mesPlanShiftWorkerList) {
		String id ="";
		String sheetId;
		if (mesPlanShiftHead.getIsNewRecord()){
			if (StringUtil.isBlank(mesPlanShiftHead.getSheetId())){
				mesPlanShiftHead.setSheetId(getNewSheetId());
			}
			mesPlanShiftHead.preInsert();
			mesPlanShiftHeadDao.insert(mesPlanShiftHead);
		}
		id = mesPlanShiftHead.getId();
		sheetId = mesPlanShiftHead.getSheetId();
		if (StringUtil.isBlank(sheetId)){
			throw new RuntimeException("sheetId不能为空!");
		}
		if (mesPlanShiftDetList != null){
			for (MesPlanShiftDet mesPlanShiftDet:mesPlanShiftDetList){
				if (StringUtil.isBlank(mesPlanShiftDet.getId())){
					mesPlanShiftDet.setSheetId(sheetId);
					
					if (StringUtil.isBlank(mesPlanShiftDet.getBillNo())){
						if (StringUtil.isBlank(mesPlanShiftDet.getSrcBillNo())){
							throw new RuntimeException("srcBillno不能为空,请检查传入参数!");
						}
						mesPlanShiftDet.setBillNo(getNewBillNo(mesPlanShiftDet.getSrcBillNo()));
					}
					mesPlanShiftDetService.save(mesPlanShiftDet);
				}else{
					mesPlanShiftDetService.updateShiftQtyById(mesPlanShiftDet);
				}
				
				if (mesPlanShiftDet.getMesPlanShiftUserList() != null){
					for (MesPlanShiftUser mesPlanShiftUser: mesPlanShiftDet.getMesPlanShiftUserList()){
					 
						if (StringUtil.isBlank(mesPlanShiftUser.getId())){
							//新增
							if (StringUtil.isNotBlank(mesPlanShiftDet.getBillNo())){
								mesPlanShiftUser.setSrcBillNo(mesPlanShiftDet.getBillNo());
							}
							mesPlanShiftUser.setSheetId(sheetId);
						} 
						mesPlanShiftUserService.save(mesPlanShiftUser);
					}
				}
				
			}
		}
		
		if (mesPlanShiftWorkerList != null){
			for(MesPlanShiftWorker mesPlanShiftWorker:mesPlanShiftWorkerList){
				if (StringUtils.isBlank(mesPlanShiftWorker.getSheetId())){
					mesPlanShiftWorker.setSheetId(sheetId);
				}
				mesPlanShiftWorkerService.save(mesPlanShiftWorker);
			}
		}
		return id;
	}

	@Override
	public MesPlanShiftSheet getSheetBySheetId(String sheetId) {
		MesPlanShiftHead head  = getHeadBySheetId(sheetId);
		return getSheet(head);
	}

	@Override
	public PageInfo<MesPlanShiftHead> findPage(int pageNum, int pageSize, MesPlanShiftHead mesPlanShiftHead) {
		 PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
			List<MesPlanShiftHead> pageList =mesPlanShiftHeadDao.findList(mesPlanShiftHead);
			PageInfo<MesPlanShiftHead> page = new PageInfo<MesPlanShiftHead>(pageList);
			return page;
	}

	@Override
	public int updateSheetStatus(MesPlanShiftHead mesPlanShiftHead) {
		return mesPlanShiftHeadDao.updateSheetStatus(mesPlanShiftHead);
	}
}