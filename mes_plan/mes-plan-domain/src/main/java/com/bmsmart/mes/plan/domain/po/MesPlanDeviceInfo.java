package com.bmsmart.mes.plan.domain.po;

import com.bmsmart.mes.commons.persistence.MesDataEntity;

public class MesPlanDeviceInfo extends MesDataEntity<MesPlanDeviceInfo>{
	private String deviceId;
	private String deviceName;
	private String workcenterId;
	private float capacityInput;
	private String deviceType; //1=设备,2=工作组,3=
	public MesPlanDeviceInfo(){
		
	}
	
	public MesPlanDeviceInfo(String workcenterId,String deviceId){
		this.workcenterId = workcenterId;
		this.deviceId = deviceId;
	}
	
	public MesPlanDeviceInfo(String workcenterId,String deviceId,float capacityInput){
		this.workcenterId = workcenterId;
		this.deviceId = deviceId;
		this.capacityInput = capacityInput;
	}
	
	public MesPlanDeviceInfo(String workcenterId,String deviceId,String deviceName){
		this.workcenterId = workcenterId;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		//this.capacityInput = capacityInput;
	}
	
	public MesPlanDeviceInfo(String workcenterId,String deviceId,String deviceName,float capacityInput,String deviceType){
		this.workcenterId = workcenterId;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.capacityInput = capacityInput;
		this.deviceType = deviceType;
	}
	

	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getWorkcenterId() {
		return workcenterId;
	}
	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}

	public float getCapacityInput() {
		return capacityInput;
	}

	public void setCapacityInput(float capacityInput) {
		this.capacityInput = capacityInput;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
	
}
