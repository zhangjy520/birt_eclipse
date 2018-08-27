package com.bmsmart.mes.plan.domain.po;

import java.util.List;

import com.bmsmart.mes.commons.persistence.MesDataEntity;

public class MesPlanDeviceDaySheet extends MesDataEntity<MesPlanDeviceDaySheet> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MesPlanDeviceInfo> mesPlanDeviceInfoList;
	private List<MesPlanDeviceDay> mesPlanDeviceDayList;//非整理车间设备占用情况
	private List<MesPlanDevice> mesPlanDeviceList;//整理车间设备占用情况
	public List<MesPlanDeviceInfo> getMesPlanDeviceInfoList() {
		return mesPlanDeviceInfoList;
	}
	public void setMesPlanDeviceInfoList(List<MesPlanDeviceInfo> mesPlanDeviceInfoList) {
		this.mesPlanDeviceInfoList = mesPlanDeviceInfoList;
	}
	public List<MesPlanDeviceDay> getMesPlanDeviceDayList() {
		return mesPlanDeviceDayList;
	}
	public void setMesPlanDeviceDayList(List<MesPlanDeviceDay> mesPlanDeviceDayList) {
		this.mesPlanDeviceDayList = mesPlanDeviceDayList;
	}
	public List<MesPlanDevice> getMesPlanDeviceList() {
		return mesPlanDeviceList;
	}
	public void setMesPlanDeviceList(List<MesPlanDevice> mesPlanDeviceList) {
		this.mesPlanDeviceList = mesPlanDeviceList;
	}
 
	
}
