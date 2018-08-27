package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmType;
import com.bmsmart.mes.mesExternal.export.response.MdmQueryResponse;
import com.bmsmart.mes.mesExternal.export.response.vo.MdmEquipmentInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.export.PlanShiftGetProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanScheduleService;
import com.bmsmart.mes.plan.service.MesPlanShiftDetService;
import com.bmsmart.mes.plan.service.MesPlanShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanShiftUserService;

public class PlanShiftGetProviderImpl implements PlanShiftGetProvider {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MdmQueryProvider mdmQueryProvider;
	@Autowired
	private MesPlanShiftHeadService mesPlanShiftHeadService;
	@Autowired
	private MesPlanScheduleService mesPlanScheduleService;
	@Autowired
	private MesPlanMonthService mesPlanMonthService;
	@Autowired
	private MesPlanShiftUserService mesPlanShiftUserService;
	
	@Autowired
	private MesPlanShiftDetService mesPlanShiftDetService;
	
	@Autowired
	private MesPlanDeviceService mesPlanDeviceService;
	
	
	@Override
	public MesPlanShiftHead getWorkcenterShiftHead(String workcenterId, Date shiftDate, String worktime) {
		List<MesPlanShiftHead> mesPlanShiftHeadList = getWorkcenterShiftHeadInner(workcenterId, shiftDate, worktime);
		if (mesPlanShiftHeadList != null && mesPlanShiftHeadList.size() >0){
			return mesPlanShiftHeadList.get(0);
		}else{
			return null;
		}
	}



	private List<MesPlanShiftHead> getWorkcenterShiftHeadInner(String workcenterId, Date shiftDate, String worktime) {
		List<String> shiftList = getShiftId(worktime);
		MesPlanShiftHead headPara = new MesPlanShiftHead();
		headPara.setShiftIdList(shiftList);
		headPara.setWorkcenterId(workcenterId);
		headPara.setShiftDate(shiftDate);
		List<MesPlanShiftHead> mesPlanShiftHeadList =  mesPlanShiftHeadService.findList(headPara);
		return mesPlanShiftHeadList;
	}

 
	
	private List<String> getShiftId(String worktime){
		List<String> rtnList = new ArrayList<String>();
		MdmQueryRequest request=new MdmQueryRequest();
		request.setMdmType(MdmType.SHIFT);
		Map<String,String> qryMap = new HashMap<String,String>();
		 
		request.setQueryMap(qryMap);
		MdmQueryResponse listCode	= mdmQueryProvider.queryList(request);
		List<Map<String,Object>> shiftList = JSONFormatter.newInstance().toObject(listCode.getResultStr(),List.class);
		
		for (Map<String,Object> shiftRow:shiftList){
			if (shiftRow.get("startTime") == null || shiftRow.get("endTime") == null ){
				continue;
			}
			String startTime = shiftRow.get("startTime").toString();
			String endTime = shiftRow.get("endTime").toString();
			//当前时间是否属于本班次，考虑跨天的情况
			if ((startTime.compareTo(endTime)<=0 && startTime.compareTo(worktime) <=0 && endTime.compareTo(worktime)>=0)||
					(
						startTime.compareTo(endTime)>0 && 
						((startTime.compareTo(worktime) <=0  && "23:59:59".compareTo(worktime)>=0)
						||("00:00:00".compareTo(worktime) <=0 &&  endTime.compareTo(worktime)>=0)))
					){
				rtnList.add(shiftRow.get("code").toString());
			}
		}
		if (rtnList.size()==0){
			throw new RuntimeException(worktime+"的班次为空!从主数据返回了"+(shiftList==null?0:shiftList.size())+"个班次");
		}
		return rtnList;
	}



	@Override
	public Map<String,MesPlanMonth> getDeviceShiftPlan(List<String> deviceList, String workcenterId, Date shiftDate,String worktime) {
		//任务
		MesPlanShiftHead head = getWorkcenterShiftHead(workcenterId,shiftDate,worktime);
		if (head == null) return null;
		MesPlanSchedule schedulePara = new MesPlanSchedule();
		schedulePara.setScheduleDate(head.getShiftDate());
		schedulePara.setWorkshopId(head.getWorkshopId());
		schedulePara.setShiftId(head.getShiftId());
		schedulePara.setShiftStyle(head.getShiftStyle());
		schedulePara.setWorkcenterId(workcenterId);
		List<MesPlanDevice> mesPlanDeviceList = mesPlanScheduleService.findSchedualDeviceList(schedulePara);
		
		Map<String,MesPlanMonth> retMap = new HashMap<String,MesPlanMonth>();
		for (MesPlanDevice mesPlanDevice:mesPlanDeviceList){
			if (deviceList.contains(mesPlanDevice.getDeviceId())){
				MesPlanMonth planPara = new MesPlanMonth();
				planPara.setErpBillNo(mesPlanDevice.getErpBillNo());
				MesPlanMonth mesPlanMonth = mesPlanMonthService.getByUk(planPara);
				retMap.put(mesPlanDevice.getDeviceId(),mesPlanMonth);
			}
		}
		return retMap;
	}



	@Override
	public Map<String, MesPlanShiftUser> getDeviceWroktypeUser(String workcenterId, Date shiftDate, String worktime,String worktypeId) {
		//工作中心设备列表
		MdmQueryRequest request=new MdmQueryRequest();
		request.setMdmType(MdmType.EQUIPMENT_INFO);
		Map<String,String> qryMap = new HashMap<String,String>();
		qryMap.put("workcenter", workcenterId);
		request.setQueryMap(qryMap);
		MdmQueryResponse response	= mdmQueryProvider.queryList(request);
		List<MdmEquipmentInfo> deviceList = JSONFormatter.newInstance().toArray(response.getResultStr(),MdmEquipmentInfo.class);
		if (deviceList == null)
		{
			return new HashMap<String, MesPlanShiftUser>();
		}
		List<String> deviceList2 = new ArrayList<String>();
		for (MdmEquipmentInfo device:deviceList){
			deviceList2.add(device.getCode());
		}
		return getDeviceWroktypeUser(deviceList2,workcenterId,shiftDate,worktime,worktypeId);
		 
	}



	@Override
	public Map<String, MesPlanShiftUser> getDeviceWroktypeUser(List<String> deviceList, String workcenterId,
			Date shiftDate, String worktime,String worktypeId) {
		Map<String, MesPlanShiftUser> rtnMap = new HashMap<String, MesPlanShiftUser>();
		if (deviceList == null) {
			return rtnMap; 
		}
		for(String deviceId:deviceList){
			rtnMap.put(deviceId,null);
		}
		MesPlanShiftHead head = getWorkcenterShiftHead(workcenterId,shiftDate,worktime);
		if (head == null){
			return rtnMap;
		}
 
		MesPlanShiftUser userPara = new MesPlanShiftUser();
		userPara.setSheetId(head.getSheetId());
		userPara.setWorktypeId(worktypeId);
		List<MesPlanShiftUser> userList = mesPlanShiftUserService.findList(userPara);
		if (userList != null ){
			for(MesPlanShiftUser user: userList){
				MesPlanShiftDet detPara = new MesPlanShiftDet();
				detPara.setSheetId(head.getSheetId());
				detPara.setBillNo(user.getSrcBillNo());
				List<MesPlanShiftDet> detList = mesPlanShiftDetService.findList(detPara);
				if (detList != null && detList.size() >0){
					MesPlanDevice mesPlanDevice = mesPlanDeviceService.getByUk(detList.get(0).getSrcBillNo());
					if (mesPlanDevice != null){
						rtnMap.containsKey(mesPlanDevice.getDeviceId());
						rtnMap.put(mesPlanDevice.getDeviceId(), user);
					}
				}
			}
			
		}
		
		return rtnMap;
	}


	@Override
	public Map<String, Map<String, MesPlanShiftUser>> getDeviceWroktypeUser(List<String> workcenterIdList,
			Date shiftDate, String worktime,String worktypeId) {
		 Map<String, Map<String, MesPlanShiftUser>> rtnMap = new HashMap<String, Map<String, MesPlanShiftUser>>();
		 if (workcenterIdList == null) return rtnMap;
		 for(String workcenterId :workcenterIdList){
			 Map<String, MesPlanShiftUser> map= getDeviceWroktypeUser(workcenterId, shiftDate, worktime,worktypeId);
			 rtnMap.put(workcenterId, map);
		 }
		return rtnMap;
	}

	@Override
	public PlanResponse<MesPlanShiftHead> getWorkcenterShiftHead(PlanRequest<String> workcenterRequest,
			PlanRequest<Date> shiftDateRequest, PlanRequest<String> worktimeRequest) {
		try{
			MesPlanShiftHead responseObj = getWorkcenterShiftHead(workcenterRequest.getRequestObj(),
					shiftDateRequest.getRequestObj(),worktimeRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getWorkcenterShiftHead出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

	@Override
	public PlanResponse<Map<String, MesPlanMonth>> getDeviceShiftPlan(PlanRequest<List<String>> deviceListRequest,
			PlanRequest<String> workcenterRequest, PlanRequest<Date> shiftDateRequest,
			PlanRequest<String> worktimeRequest) {
		try{
			Map<String, MesPlanMonth> responseObj = getDeviceShiftPlan(deviceListRequest.getRequestObj(),workcenterRequest.getRequestObj(),
					shiftDateRequest.getRequestObj(),worktimeRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getDeviceShiftPlan出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

	@Override
	public PlanResponse<Map<String, MesPlanShiftUser>> getDeviceWroktypeUser(PlanRequest<String> workcenterRequest,
			PlanRequest<Date> shiftDateRequest, PlanRequest<String> worktimeRequest,
			PlanRequest<String> worktypeRequest) {
		try{
			Map<String, MesPlanShiftUser> responseObj = getDeviceWroktypeUser(workcenterRequest.getRequestObj(),shiftDateRequest.getRequestObj(),
					worktimeRequest.getRequestObj(),worktypeRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getDeviceWroktypeUser出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

	@Override
	public PlanResponse<Map<String, MesPlanShiftUser>> getDeviceWroktypeUser(
			PlanRequest<List<String>> deviceListRequest, PlanRequest<String> workcenterRequest,
			PlanRequest<Date> shiftDateRequest, PlanRequest<String> worktimeRequest,
			PlanRequest<String> worktypeRequest) {
		try{
			Map<String, MesPlanShiftUser> responseObj = getDeviceWroktypeUser(deviceListRequest.getRequestObj(),workcenterRequest.getRequestObj(),shiftDateRequest.getRequestObj(),
					worktimeRequest.getRequestObj(),worktypeRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getDeviceWroktypeUser出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

	@Override
	public PlanResponse<Map<String, Map<String, MesPlanShiftUser>>> getDeviceWroktypeUserAll(
			PlanRequest<List<String>> workcenterIdListRequest, PlanRequest<Date> shiftDateRequest,
			PlanRequest<String> worktimeRequest, PlanRequest<String> worktypeRequest) {
		try{
			Map<String, Map<String, MesPlanShiftUser>> responseObj = getDeviceWroktypeUser(workcenterIdListRequest.getRequestObj(),shiftDateRequest.getRequestObj(),worktimeRequest.getRequestObj(),
					worktypeRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getDeviceWroktypeUserAll出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}
}
