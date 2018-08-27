/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.bmsmart.mes.commons.persistence.DataEntity;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * zhouqz
 * @author zhouqz
 * @version 2018-03-04
 */
public class MesPlanTempTaskDet extends MesDataEntity<MesPlanTempTaskDet> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private Integer orderNo;		// order_no
	private String deviceId;		// device_id
	private String deviceName;		// device_name
	private String shiftId;		// shift_id
	private String shiftStyle;		// shift_style
	private Float qty;		// qty
	private Float finishQty;
	private Float dispatchQty;
	private String billNo;
	private String shiftStatus;
	private String taskUser;
	private String taskTime;
	
	private String planSheetId;//关联主表字段
	private Date shiftDate;//关联主表字段
	private String workshopId;//关联主表字段
	private String workcenterId;//关联主表字段
	private String ptechnicId;//关联主表字段
	private String ptechnicName;//关联主表字段
	private String taskStatus;
	private String validStatus;//任务是否下发，=1表示是已下发的任务
	
	private MesPlanMonth mesPlanMonth;
	private List<MesPlanTempShiftUser> mesPlanTempShiftUserList;
	
	public MesPlanTempTaskDet() {
		super();
	}

	public MesPlanTempTaskDet(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="device_id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=128, message="device_name长度必须介于 0 和 128 之间")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=64, message="shift_id长度必须介于 0 和 64 之间")
	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	
	@Length(min=0, max=64, message="shift_style长度必须介于 0 和 64 之间")
	public String getShiftStyle() {
		return shiftStyle;
	}

	public void setShiftStyle(String shiftStyle) {
		this.shiftStyle = shiftStyle;
	}

	public Float getQty() {
		return qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}

	public Float getFinishQty() {
		return finishQty;
	}

	public void setFinishQty(Float finishQty) {
		this.finishQty = finishQty;
	}

	public Float getDispatchQty() {
		return dispatchQty;
	}

	public void setDispatchQty(Float dispatchQty) {
		this.dispatchQty = dispatchQty;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getPlanSheetId() {
		return planSheetId;
	}

	public void setPlanSheetId(String planSheetId) {
		this.planSheetId = planSheetId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
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

	public String getPtechnicId() {
		return ptechnicId;
	}

	public void setPtechnicId(String ptechnicId) {
		this.ptechnicId = ptechnicId;
	}

	public String getPtechnicName() {
		return ptechnicName;
	}

	public void setPtechnicName(String ptechnicName) {
		this.ptechnicName = ptechnicName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getShiftStatus() {
		return shiftStatus;
	}

	public void setShiftStatus(String shiftStatus) {
		this.shiftStatus = shiftStatus;
	}

	public MesPlanMonth getMesPlanMonth() {
		return mesPlanMonth;
	}

	public void setMesPlanMonth(MesPlanMonth mesPlanMonth) {
		this.mesPlanMonth = mesPlanMonth;
	}

	public List<MesPlanTempShiftUser> getMesPlanTempShiftUserList() {
		return mesPlanTempShiftUserList;
	}

	public void setMesPlanTempShiftUserList(List<MesPlanTempShiftUser> mesPlanTempShiftUserList) {
		this.mesPlanTempShiftUserList = mesPlanTempShiftUserList;
	}

	public String getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}
	
	
}