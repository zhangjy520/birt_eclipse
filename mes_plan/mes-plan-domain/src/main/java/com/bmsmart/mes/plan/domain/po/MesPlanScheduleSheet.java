package com.bmsmart.mes.plan.domain.po;

import java.util.List;

public class MesPlanScheduleSheet {
	MesPlanSchedule mesPlanSchedule;
	List<MesPlanDevice> schedualDeviceList;
	List<MesPlanDevice> unschedualDeviceList;
	public MesPlanSchedule getMesPlanSchedule() {
		return mesPlanSchedule;
	}
	public void setMesPlanSchedule(MesPlanSchedule mesPlanSchedule) {
		this.mesPlanSchedule = mesPlanSchedule;
	}
	public List<MesPlanDevice> getSchedualDeviceList() {
		return schedualDeviceList;
	}
	public void setSchedualDeviceList(List<MesPlanDevice> schedualDeviceList) {
		this.schedualDeviceList = schedualDeviceList;
	}
	public List<MesPlanDevice> getUnschedualDeviceList() {
		return unschedualDeviceList;
	}
	public void setUnschedualDeviceList(List<MesPlanDevice> unschedualDeviceList) {
		this.unschedualDeviceList = unschedualDeviceList;
	}
}
