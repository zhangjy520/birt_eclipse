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
 * mes_plan_temp_task
 * @author zhouqz
 * @version 2017-12-22
 */
public class MesPlanTempTask extends MesDataEntity<MesPlanTempTask> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private Date planBeginDate;		// plan_begin_date
	private Date planEndDate;		// plan_end_date
	private String workshopId;		// workshop_id
	private String taskUser;		// task_user
	private String relPlan;		// 0=否1=是
	private String planSheetId;		// plan_sheet_id
	private String taskDesc;		// task_desc
	private String taskType;		// task_type
//	private String createUser;		// create_user
//	private Date createTime;		// create_time
//	private String updateUser;		// udpate_user
//	private Date updateTime;		// update_time
	private String taskStatus;		// task_status
	private String attachId;		// attach_id
	private String taskStarter;
	private String taskFinishDesc;//完工内容
	private Date planBeginDate1;		// plan_begin_date
	private Date planEndDate1;		// plan_end_date
	
	private Date planBeginDate2;		// plan_begin_date
	private Date planEndDate2;		// plan_end_date
	private String workcenterId;//工作中心
	private String deviceId;//设备
	private String ptechnicId;//工序
	private String shiftId;//班次
	private String shiftStyle;//班别(组)
	private String deviceName;//设备名称
	private String ptechnicName;
	private List<MesPlanTempTaskDet> mesPlanTempTaskDetList;
	
	public MesPlanTempTask() {
		super();
	}

	public MesPlanTempTask(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getPlanBeginDate() {
		return planBeginDate;
	}

	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	@Length(min=0, max=64, message="workshop_id长度必须介于 0 和 64 之间")
	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}
	
	@Length(min=0, max=64, message="task_user长度必须介于 0 和 64 之间")
	public String getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}
	
	@Length(min=0, max=10, message="0=否1=是长度必须介于 0 和 10 之间")
	public String getRelPlan() {
		return relPlan;
	}

	public void setRelPlan(String relPlan) {
		this.relPlan = relPlan;
	}
	
	@Length(min=0, max=64, message="plan_sheet_id长度必须介于 0 和 64 之间")
	public String getPlanSheetId() {
		return planSheetId;
	}

	public void setPlanSheetId(String planSheetId) {
		this.planSheetId = planSheetId;
	}
	
	@Length(min=0, max=4000, message="task_desc长度必须介于 0 和 4000 之间")
	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	@Length(min=0, max=10, message="task_type长度必须介于 0 和 10 之间")
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
//	@Length(min=0, max=60, message="create_user长度必须介于 0 和 60 之间")
//	public String getCreateUser() {
//		return createUser;
//	}
//
//	public void setCreateUser(String createUser) {
//		this.createUser = createUser;
//	}
//	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Date createTime) {
//		this.createTime = createTime;
//	}
	
//	@Length(min=0, max=60, message="update_user长度必须介于 0 和 60 之间")
//	public String getUpdateUser() {
//		return updateUser;
//	}
//
//	public void setUdpateUser(String updateUser) {
//		this.updateUser = updateUser;
//	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	public Date getUpdateTime() {
//		return updateTime;
//	}
//
//	public void setUpdateTime(Date updateTime) {
//		this.updateTime = updateTime;
//	}
	
	@Length(min=0, max=10, message="task_status长度必须介于 0 和 10 之间")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	@Length(min=0, max=2000, message="attach_id长度必须介于 0 和 2000 之间")
	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getTaskStarter() {
		return taskStarter;
	}

	public void setTaskStarter(String taskStarter) {
		this.taskStarter = taskStarter;
	}

	public Date getPlanBeginDate1() {
		return planBeginDate1;
	}

	public void setPlanBeginDate1(Date planBeginDate1) {
		this.planBeginDate1 = planBeginDate1;
	}

	public Date getPlanEndDate1() {
		return planEndDate1;
	}

	public void setPlanEndDate1(Date planEndDate1) {
		this.planEndDate1 = planEndDate1;
	}

	public Date getPlanBeginDate2() {
		return planBeginDate2;
	}

	public void setPlanBeginDate2(Date planBeginDate2) {
		this.planBeginDate2 = planBeginDate2;
	}

	public Date getPlanEndDate2() {
		return planEndDate2;
	}

	public void setPlanEndDate2(Date planEndDate2) {
		this.planEndDate2 = planEndDate2;
	}

	public String getTaskFinishDesc() {
		return taskFinishDesc;
	}

	public void setTaskFinishDesc(String taskFinishDesc) {
		this.taskFinishDesc = taskFinishDesc;
	}

	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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

	public List<MesPlanTempTaskDet> getMesPlanTempTaskDetList() {
		return mesPlanTempTaskDetList;
	}

	public void setMesPlanTempTaskDetList(List<MesPlanTempTaskDet> mesPlanTempTaskDetList) {
		this.mesPlanTempTaskDetList = mesPlanTempTaskDetList;
	}
	
}