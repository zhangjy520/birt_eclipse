package com.bmsmart.mes.plan.domain.po;

import java.util.List;

import com.bmsmart.mes.commons.persistence.MesDataEntity;

public class MesPlanDeviceSheet extends MesDataEntity<MesPlanDeviceSheet> {
	
	private MesPlanMonth	mesPlanMonth;
	private MesPlanDispatchHead mesPlanDispatchHead;
	private MesPlanDispatchDet mesPlanDispatchDet;//厂级调度单
	private List<MesPlanDevice> mesPlanDeviceList;
	private List<MesPlanDispatchDet> mesPlanDispatchDetList;
 
	public MesPlanDispatchDet getMesPlanDispatchDet() {
		return mesPlanDispatchDet;
	}
	public void setMesPlanDispatchDet(MesPlanDispatchDet mesPlanDispatchDet) {
		this.mesPlanDispatchDet = mesPlanDispatchDet;
	}
	public List<MesPlanDevice> getMesPlanDeviceList() {
		return mesPlanDeviceList;
	}
	public void setMesPlanDeviceList(List<MesPlanDevice> mesPlanDeviceList) {
		this.mesPlanDeviceList = mesPlanDeviceList;
	}
	public MesPlanDispatchHead getMesPlanDispatchHead() {
		return mesPlanDispatchHead;
	}
	public void setMesPlanDispatchHead(MesPlanDispatchHead mesPlanDispatchHead) {
		this.mesPlanDispatchHead = mesPlanDispatchHead;
	}
	public MesPlanMonth getMesPlanMonth() {
		return mesPlanMonth;
	}
	public void setMesPlanMonth(MesPlanMonth mesPlanMonth) {
		this.mesPlanMonth = mesPlanMonth;
	}
	public List<MesPlanDispatchDet> getMesPlanDispatchDetList() {
		return mesPlanDispatchDetList;
	}
	public void setMesPlanDispatchDetList(List<MesPlanDispatchDet> mesPlanDispatchDetList) {
		this.mesPlanDispatchDetList = mesPlanDispatchDetList;
	}
 
	
}
