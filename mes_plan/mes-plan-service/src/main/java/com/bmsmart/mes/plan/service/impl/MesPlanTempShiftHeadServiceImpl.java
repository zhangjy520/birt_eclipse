package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmType;
import com.bmsmart.mes.mesExternal.export.response.MdmQueryResponse;
import com.bmsmart.mes.mesExternal.export.response.vo.MdmStaffInfo;
import com.bmsmart.mes.plan.dao.MesPlanShiftHeadDao;
import com.bmsmart.mes.plan.dao.MesPlanTempShiftHeadDao;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftWorker;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanTempShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanTempShiftUserService;
import com.bmsmart.mes.plan.service.MesPlanTempTaskDetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("mesPlanTempShiftHeadService")
public class MesPlanTempShiftHeadServiceImpl extends MesCrudService<MesPlanTempShiftHeadDao, MesPlanTempShiftHead>  implements MesPlanTempShiftHeadService {
	@Autowired
	private MesPlanTempShiftHeadDao mesPlanTempShiftHeadDao;
	@Autowired
	private MesPlanTempTaskDetService mesPlanTempTaskDetService;
	@Autowired
	private MdmQueryProvider mdmQueryProvider;
	@Autowired
	private MesPlanTempShiftUserService mesPlanTempShiftUserService;
	
	@Autowired
	private MesPlanMonthService mesPlanMonthService;
	
	@Override
	public MesPlanTempShiftHead getHead(String id) {
		return super.get(id);
	}

	@Override
	public MesPlanTempShiftHead getHeadBySheetId(String sheetId) {
		return mesPlanTempShiftHeadDao.getHeadBySheetId(sheetId);
	}

	@Override
	public MesPlanTempShiftHead getHeadByUk(MesPlanTempShiftHead mesPlanTempShiftHead) {
		return mesPlanTempShiftHeadDao.getHeadByUk(mesPlanTempShiftHead);
	}

	@Override
	public MesPlanTempShiftSheet getSheetById(String id) {
		MesPlanTempShiftHead mesPlanTempShiftHead = getHead(id);
		MesPlanTempShiftSheet sheet = getSheet(mesPlanTempShiftHead);
		return sheet;
	}
	@Override
	public MesPlanTempShiftSheet getSheetBySheetId(String sheetId) {
		MesPlanTempShiftHead head  = getHeadBySheetId(sheetId);
		return getSheet(head);
	}

	@Override
	public List<MesPlanTempShiftHead> findList(MesPlanTempShiftHead mesPlanTempShiftHead) {
		return super.findList(mesPlanTempShiftHead);
	}

	@Override
	public PageInfo<MesPlanTempShiftHead> findPage(int pageNum, int pageSize, Map<String, String> datas) {
		return super.findPage(pageNum,pageSize, datas);
	}

	@Override
	public PageInfo<MesPlanTempShiftHead> findPage(int pageNum, int pageSize, MesPlanTempShiftHead mesPlanTempShiftHead) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanTempShiftHead> pageList =mesPlanTempShiftHeadDao.findList(mesPlanTempShiftHead);
		PageInfo<MesPlanTempShiftHead> page = new PageInfo<MesPlanTempShiftHead>(pageList);
		return page;
	}

	@Override
	public void save(MesPlanTempShiftHead mesPlanTempShiftHead) {
		super.save(mesPlanTempShiftHead);

	}

	@Override
	public void delete(MesPlanTempShiftHead mesPlanTempShiftHead) {
		super.delete(mesPlanTempShiftHead);

	}

	@Override
	public void deleteById(String id) {
		mesPlanTempShiftHeadDao.deleteById(id);

	}

	@Override
	public MesPlanTempShiftSheet getSheetByUk(MesPlanTempShiftHead headPara) {
		MesPlanTempShiftHead mesPlanTempShiftHead = getHeadByUk(headPara);
		if (mesPlanTempShiftHead == null){
			mesPlanTempShiftHead = headPara;
		}
		MesPlanTempShiftSheet sheet = getSheet(mesPlanTempShiftHead);
		return sheet;
	}
	
//	@Override
//	public MesPlanShiftSheet getSheetById(String id) {
//		MesPlanShiftHead mesPlanShiftHead = getHead(id);
//		MesPlanShiftSheet sheet = getSheet(mesPlanShiftHead);
//		return sheet;
//	}
	

	public MesPlanTempShiftSheet getSheet(MesPlanTempShiftHead mesPlanTempShiftHead) {
		MesPlanTempShiftSheet sheet = new MesPlanTempShiftSheet();
		MesPlanTempTaskDet devicePara= new MesPlanTempTaskDet();
		devicePara.setShiftDate(mesPlanTempShiftHead.getShiftDate());
		devicePara.setWorkshopId(mesPlanTempShiftHead.getWorkshopId());
		devicePara.setWorkcenterId(mesPlanTempShiftHead.getWorkcenterId());
		devicePara.setShiftId(mesPlanTempShiftHead.getShiftId());
		//devicePara.setTaskStatus("1");
		devicePara.setValidStatus("1");
		
		List<MesPlanTempTaskDet> mesPlanTempTaskDetList = null;
		if (mesPlanTempShiftHead.getShiftDate() != null &&
				StringUtil.isNotBlank(mesPlanTempShiftHead.getWorkshopId()) &&
				StringUtil.isNotBlank(mesPlanTempShiftHead.getWorkcenterId()) &&
				StringUtil.isNotBlank(mesPlanTempShiftHead.getShiftId())
				){
			mesPlanTempTaskDetList =  mesPlanTempTaskDetService.findAllList(devicePara);
		}	
		if (mesPlanTempTaskDetList != null){
			getSheetDet(mesPlanTempShiftHead, mesPlanTempTaskDetList);
		}
		
		List<Object> mdmStaffInfoList ;//班组人员信息
		if (StringUtils.isBlank(mesPlanTempShiftHead.getShiftStyle())){
			mdmStaffInfoList = new ArrayList<Object>();
		}else{
			mdmStaffInfoList= (List)getWorkerListNew(mesPlanTempShiftHead.getShiftStyle());
		}
		
		sheet.setMesPlanTempShiftHead(mesPlanTempShiftHead);
		sheet.setMdmStaffInfoList(mdmStaffInfoList);
		sheet.setMesPlanTempTaskDetList(mesPlanTempTaskDetList);
		return sheet;
	}
	
	private void getSheetDet(MesPlanTempShiftHead mesPlanTempShiftHead, List<MesPlanTempTaskDet> mesPlanTempTaskDetList) {
		for (MesPlanTempTaskDet mesPlanDevice:mesPlanTempTaskDetList){
		 
			addShiftUser(mesPlanTempShiftHead.getWorkshopId(),mesPlanDevice);
			
			MesPlanMonth mesPlanMonthPara = new MesPlanMonth();
			mesPlanMonthPara.setErpBillNo(mesPlanDevice.getPlanSheetId());
			MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(mesPlanMonthPara);
			mesPlanDevice.setMesPlanMonth(mesPlanMonth);
		}
	}	
	
	
	private void addShiftUser(String workshopId,MesPlanTempTaskDet mesPlanDevice) {
		List<MesPlanTempShiftUser> userlist = null;
		if (StringUtil.isNotBlank(mesPlanDevice.getBillNo()))
		{
			MesPlanTempShiftUser userPara = new MesPlanTempShiftUser();
			userPara.setSrcBillNo(mesPlanDevice.getBillNo());
			//
			userlist = mesPlanTempShiftUserService.findList(userPara);//本班次
		} 
		
		if (userlist == null ||userlist.size() == 0 ){
			userlist = mesPlanTempShiftUserService.getEmptyShiftUserList(workshopId,mesPlanDevice);
		}
		mesPlanDevice.setMesPlanTempShiftUserList(userlist);
	}

	private List<MdmStaffInfo> getWorkerListNew(String shiftStyleId){
		MdmQueryRequest request=new MdmQueryRequest();
		request.setMdmType(MdmType.STAFF_INFO);
		Map<String,String> qryMap = new HashMap<String,String>();
		qryMap.put("parentCode", shiftStyleId);
		request.setQueryMap(qryMap);
		MdmQueryResponse listCode	= mdmQueryProvider.queryList(request);
		
		List<MdmStaffInfo> useList= JSONFormatter.newInstance().toArray(listCode.getResultStr(), MdmStaffInfo.class);
		 
		return useList;
	}

	public synchronized String getNewSheetId(){
		Random r = new Random();
		return "TT"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}
	
	@Override
	public String saveSheet(MesPlanTempShiftHead mesPlanTempShiftHead,List<MesPlanTempShiftUser> mesPlanTempShiftUserList) {
		String id ="";
		String sheetId;
		if (mesPlanTempShiftHead.getIsNewRecord()){
			if (StringUtil.isBlank(mesPlanTempShiftHead.getSheetId())){
				mesPlanTempShiftHead.setSheetId(getNewSheetId());
			}
			mesPlanTempShiftHead.preInsert();
			mesPlanTempShiftHeadDao.insert(mesPlanTempShiftHead);
		}
		id = mesPlanTempShiftHead.getId();
		sheetId = mesPlanTempShiftHead.getSheetId();
		if (StringUtil.isBlank(sheetId)){
			throw new RuntimeException("sheetId不能为空!");
		}
		if (mesPlanTempShiftUserList != null){
			for (MesPlanTempShiftUser mesPlanTempShiftUser: mesPlanTempShiftUserList){
				if (StringUtil.isBlank(mesPlanTempShiftUser.getId())){
					mesPlanTempShiftUser.setSheetId(sheetId);
				} 
				mesPlanTempShiftUserService.save(mesPlanTempShiftUser);
			}
		}
		return id;
	}
	
}
