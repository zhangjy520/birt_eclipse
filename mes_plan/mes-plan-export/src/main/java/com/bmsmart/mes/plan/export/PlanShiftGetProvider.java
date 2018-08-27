package com.bmsmart.mes.plan.export;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;

public interface PlanShiftGetProvider {

/**
 * 根据工作时间，日期，时间得到排班信息
 * @param workcenterId	工作中心
 * @param ShiftDate		日期	2017-01-01
 * @param worktime		时间 01:30:00 格式hh:mm:ss
 * @return
 */
	public MesPlanShiftHead getWorkcenterShiftHead(String workcenterId,Date shiftDate,String worktime);
	/**
	 * 根据设备列表，日期，时间得到加工的订单信息
	 * @param deviceList
	 * @param ShiftDate
	 * @param worktime
	 * @return
	 */
	public Map<String,MesPlanMonth> getDeviceShiftPlan(List<String> deviceList, String workcenterId, Date shiftDate,String worktime);
	
	/**
	 * 
	 * @param workcenterId 工作中心
	 * @param shiftDate 排班日期
	 * @param worktime 当前时间
	 * @param worktypeId 工种
	 * @return
	 */
	public Map<String,MesPlanShiftUser> getDeviceWroktypeUser(String workcenterId, Date shiftDate,String worktime,String worktypeId);
	/**
	 * 
	 * @param deviceList	设备列表
	 * @param workcenterId 工作中心
	 * @param shiftDate 排班日期
	 * @param worktime 当前时间
	 * @param worktypeId 工种
	 * @return
	 */
	public Map<String,MesPlanShiftUser> getDeviceWroktypeUser(List<String> deviceList, String workcenterId, Date shiftDate,String worktime,String worktypeId);
	
	/**
	 * 
	 * @param workcenterIdList 工作中心列表
	 * @param shiftDate 排班日期
	 * @param worktime 当前时间
	 * @param worktypeId 工种
	 * @return
	 */
	public Map<String,Map<String,MesPlanShiftUser>> getDeviceWroktypeUser(List<String> workcenterIdList, Date shiftDate,String worktime,String worktypeId);
	
	public PlanResponse<MesPlanShiftHead> getWorkcenterShiftHead(PlanRequest<String> workcenterRequest,PlanRequest<Date> shiftDateRequest,PlanRequest<String> worktimeRequest);
	
	public PlanResponse<Map<String,MesPlanMonth>> getDeviceShiftPlan(PlanRequest<List<String>> deviceListRequest, PlanRequest<String> workcenterRequest, PlanRequest<Date> shiftDateRequest,PlanRequest<String> worktimeRequest);
	
	public PlanResponse<Map<String,MesPlanShiftUser>> getDeviceWroktypeUser(PlanRequest<String> workcenterRequest, PlanRequest<Date> shiftDateRequest,PlanRequest<String> worktimeRequest,PlanRequest<String> worktypeRequest);
	
	public PlanResponse<Map<String,MesPlanShiftUser>> getDeviceWroktypeUser(PlanRequest<List<String>> deviceListRequest, PlanRequest<String> workcenterRequest,PlanRequest<Date> shiftDateRequest,PlanRequest<String> worktimeRequest,PlanRequest<String> worktypeRequest);
	
	public PlanResponse<Map<String,Map<String,MesPlanShiftUser>>> getDeviceWroktypeUserAll(PlanRequest<List<String>> workcenterListRequest, PlanRequest<Date> shiftDateRequest,PlanRequest<String> worktimeRequest,PlanRequest<String> worktypeRequest);
	
}
