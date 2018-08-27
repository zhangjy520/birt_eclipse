package com.bmsmart.mes.plan.domain.po;

import java.util.List;

public class MesPlanShiftSheet {
	private MesPlanShiftHead mesPlanShiftHead;
	private List<MesPlanDevice> schedualDeviceList;//排程清单
	private List<MesPlanShiftWorker> mesPlanShiftWorkerList;
	
	private List<MesPlanShiftDet> mesPlanShiftDetList;//保存时参数使用
	
	public MesPlanShiftHead getMesPlanShiftHead() {
		return mesPlanShiftHead;
	}
	public void setMesPlanShiftHead(MesPlanShiftHead mesPlanShiftHead) {
		this.mesPlanShiftHead = mesPlanShiftHead;
	}
	public List<MesPlanDevice> getSchedualDeviceList() {
		return schedualDeviceList;
	}
	public void setSchedualDeviceList(List<MesPlanDevice> schedualDeviceList) {
		this.schedualDeviceList = schedualDeviceList;
	}
	public List<MesPlanShiftWorker> getMesPlanShiftWorkerList() {
		return mesPlanShiftWorkerList;
	}
	public void setMesPlanShiftWorkerList(List<MesPlanShiftWorker> mesPlanShiftWorkerList) {
		this.mesPlanShiftWorkerList = mesPlanShiftWorkerList;
	}
	public List<MesPlanShiftDet> getMesPlanShiftDetList() {
		return mesPlanShiftDetList;
	}
	public void setMesPlanShiftDetList(List<MesPlanShiftDet> mesPlanShiftDetList) {
		this.mesPlanShiftDetList = mesPlanShiftDetList;
	}
	
	
}
