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
 * 工人设备
 * @author zhouqizhi
 * @version 2017-10-24
 */
public class MesPlanWorker2deviceInfo extends MesDataEntity<MesPlanWorker2deviceInfo> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// user_id
	private String deviceId;		// device_id
	private String deviceSubId;		// device_sub_id
	private String userName;
	private String shiftId;
	private String shiftName;
	private String worktypeId;
	private String worktypeName;
	public MesPlanWorker2deviceInfo() {
		super();
	}

	public MesPlanWorker2deviceInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="device_id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=64, message="device_sub_id长度必须介于 0 和 64 之间")
	public String getDeviceSubId() {
		return deviceSubId;
	}

	public void setDeviceSubId(String deviceSubId) {
		this.deviceSubId = deviceSubId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getWorktypeId() {
		return worktypeId;
	}

	public void setWorktypeId(String worktypeId) {
		this.worktypeId = worktypeId;
	}

	public String getWorktypeName() {
		return worktypeName;
	}

	public void setWorktypeName(String worktypeName) {
		this.worktypeName = worktypeName;
	}
	
	
	
}