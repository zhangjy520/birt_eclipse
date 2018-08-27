/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.bmsmart.mes.commons.persistence.DataEntity;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * mes_plan_schedule
 * @author zhouqizhi
 * @version 2017-08-29
 */
public class MesPlanSchedule extends MesDataEntity<MesPlanSchedule> {
	
	private static final long serialVersionUID = 1L;
	public static final String WORKSHOP_ARRANGE = "C14";//整理车间编码
	private String sheetId;		// sheet_id
	private String workshopId;		// workshop_id
	private String workcenterId;		// workcenter_id
	private Date scheduleDate;	// schedule_date
	private String scheduleUser;		// schedule_user
	private String scheduleUserName;		// schedule_user
	private String scheduleStatus;//查询条件用
	private String orderBy;
	private String unFinished;
	private String sheetStatus;
	
	private String shiftId;//查询条件用
	private String shiftStyle;//查询条件用
	
	public MesPlanSchedule() {
		super();
	}

	public MesPlanSchedule(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=64, message="workshop_id长度必须介于 0 和 64 之间")
	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}
	
	@Length(min=0, max=64, message="workcenter_id长度必须介于 0 和 64 之间")
	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}
		
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	@Length(min=0, max=64, message="schedule_user长度必须介于 0 和 64 之间")
	public String getScheduleUser() {
		return scheduleUser;
	}

	public void setScheduleUser(String scheduleUser) {
		this.scheduleUser = scheduleUser;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getUnFinished() {
		return unFinished;
	}

	public void setUnFinished(String unFinished) {
		this.unFinished = unFinished;
	}

	public String getSheetStatus() {
		return sheetStatus;
	}

	public void setSheetStatus(String sheetStatus) {
		this.sheetStatus = sheetStatus;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftStyle() {
		return shiftStyle;
	}

	public void setShiftStyle(String shiftStyle) {
		this.shiftStyle = shiftStyle;
	}

	public static String getWORKSHOP_ARRANGE() {
		return WORKSHOP_ARRANGE;
	}

	public String getScheduleUserName() {
		return scheduleUserName;
	}

	public void setScheduleUserName(String scheduleUserName) {
		this.scheduleUserName = scheduleUserName;
	}
	
}