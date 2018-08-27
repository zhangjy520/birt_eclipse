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
 * mes_plan_shift_user
 * @author zhouqz
 * @version 2017-09-04
 */
public class MesPlanShiftUser extends MesDataEntity<MesPlanShiftUser> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private String technicId;		// technic_id
	private String technicName;		// technic_id
	private Float qty;		// qty
	private String technicBeginDate;		// technic_begin_date
	private String technicEndDate;		// technic_end_date
	private String technicUser;		// technic_user
	private String technicUnit;		// technic_unit
	private String srcBillNo;
	private String worktypeId;		// technic_unit
	private String deviceSubId;
	private String taskStatus;
	
	private Date shiftDate;//查询条件
	private Date shiftDate1;//查询条件
	private Date shiftDate2;//查询条件
	private String workshopId;//车间
	private String workcenterId;//工作中心
	private String shiftId;//班次
	private String shiftStyle;//班别
	
	private String goodsId;//产品
	
	private String deviceId;//机台
	
	private String shiftStatus;//任务状态
	private String ptechnicId;
	//private String ptechnicName;
	private String taskUser;
	private Date taskTime;
	public MesPlanShiftUser() {
		super();
	}

	public MesPlanShiftUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=64, message="technic_id长度必须介于 0 和 64 之间")
	public String getTechnicId() {
		return technicId;
	}

	public void setTechnicId(String technicId) {
		this.technicId = technicId;
	}
	
	public Float getQty() {
		return qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}
	
	public String getTechnicBeginDate() {
		return technicBeginDate;
	}

	public void setTechnicBeginDate(String technicBeginDate) {
		this.technicBeginDate = technicBeginDate;
	}

	public String getTechnicEndDate() {
		return technicEndDate;
	}

	public void setTechnicEndDate(String technicEndDate) {
		this.technicEndDate = technicEndDate;
	}

	@Length(min=0, max=64, message="technic_user长度必须介于 0 和 64 之间")
	public String getTechnicUser() {
		return technicUser;
	}

	public void setTechnicUser(String technicUser) {
		this.technicUser = technicUser;
	}
	@Length(min=0, max=10, message="technic_unit长度必须介于 0 和 10 之间")
	public String getTechnicUnit() {
		return technicUnit;
	}

	public void setTechnicUnit(String technicUnit) {
		this.technicUnit = technicUnit;
	}

	public String getSrcBillNo() {
		return srcBillNo;
	}

	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}

	public String getWorktypeId() {
		return worktypeId;
	}

	public void setWorktypeId(String worktypeId) {
		this.worktypeId = worktypeId;
	}

	public String getDeviceSubId() {
		return deviceSubId;
	}

	public void setDeviceSubId(String deviceSubId) {
		this.deviceSubId = deviceSubId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTechnicName() {
		return technicName;
	}

	public void setTechnicName(String technicName) {
		this.technicName = technicName;
	}

	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}

	public Date getShiftDate1() {
		return shiftDate1;
	}

	public void setShiftDate1(Date shiftDate1) {
		this.shiftDate1 = shiftDate1;
	}

	public Date getShiftDate2() {
		return shiftDate2;
	}

	public void setShiftDate2(Date shiftDate2) {
		this.shiftDate2 = shiftDate2;
	}

	public String getShiftStatus() {
		return shiftStatus;
	}

	public void setShiftStatus(String shiftStatus) {
		this.shiftStatus = shiftStatus;
	}

	public String getPtechnicId() {
		return ptechnicId;
	}

	public void setPtechnicId(String ptechnicId) {
		this.ptechnicId = ptechnicId;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
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

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}
	
}