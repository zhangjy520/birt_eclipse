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
 * test
 * @author zhouqizhi
 * @version 2017-10-18
 */
public class MesPlanShiftWorker extends MesDataEntity<MesPlanShiftWorker> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// user_id
	private String userName;		// user_id
	private String worktypeId;		// worktype_id
	private String workStatus;		// workstatus
	private String sheetId;		// sheet_id
	private String shiftStyle; //shift_style;
	public MesPlanShiftWorker() {
		super();
	}

	public MesPlanShiftWorker(String id){
		super(id);
	}

	@Length(min=0, max=64, message="user_id长度必须介于 0 和 64 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	 
	@Length(min=0, max=64, message="worktype_id长度必须介于 0 和 64 之间")
	public String getWorktypeId() {
		return worktypeId;
	}

	public void setWorktypeId(String worktypeId) {
		this.worktypeId = worktypeId;
	}
	
	@Length(min=0, max=10, message="workstatus长度必须介于 0 和 10 之间")
	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	
//	@Length(min=0, max=64, message="create_user长度必须介于 0 和 64 之间")
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
//	
//	@Length(min=0, max=64, message="update_user长度必须介于 0 和 64 之间")
//	public String getUpdateUser() {
//		return updateUser;
//	}
//
//	public void setUpdateUser(String updateUser) {
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
	
	@Length(min=1, max=64, message="sheet_id长度必须介于 1 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShiftStyle() {
		return shiftStyle;
	}

	public void setShiftStyle(String shiftStyle) {
		this.shiftStyle = shiftStyle;
	}
	
	
}