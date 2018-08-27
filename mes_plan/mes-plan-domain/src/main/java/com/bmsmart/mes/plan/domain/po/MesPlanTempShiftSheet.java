package com.bmsmart.mes.plan.domain.po;

import java.util.List;

public class MesPlanTempShiftSheet {
	private MesPlanTempShiftHead mesPlanTempShiftHead;
	private List<Object> mdmStaffInfoList;
	
	private List<MesPlanTempTaskDet> mesPlanTempTaskDetList;
	
	private List<MesPlanTempShiftUser> mesPlanTempShiftUserList;//存储用

	public MesPlanTempShiftHead getMesPlanTempShiftHead() {
		return mesPlanTempShiftHead;
	}

	public void setMesPlanTempShiftHead(MesPlanTempShiftHead mesPlanTempShiftHead) {
		this.mesPlanTempShiftHead = mesPlanTempShiftHead;
	}

	 

	public List<MesPlanTempTaskDet> getMesPlanTempTaskDetList() {
		return mesPlanTempTaskDetList;
	}

	public void setMesPlanTempTaskDetList(List<MesPlanTempTaskDet> mesPlanTempTaskDetList) {
		this.mesPlanTempTaskDetList = mesPlanTempTaskDetList;
	}

	public List<Object> getMdmStaffInfoList() {
		return mdmStaffInfoList;
	}

	public void setMdmStaffInfoList(List<Object> mdmStaffInfoList) {
		this.mdmStaffInfoList = mdmStaffInfoList;
	}

	public List<MesPlanTempShiftUser> getMesPlanTempShiftUserList() {
		return mesPlanTempShiftUserList;
	}

	public void setMesPlanTempShiftUserList(List<MesPlanTempShiftUser> mesPlanTempShiftUserList) {
		this.mesPlanTempShiftUserList = mesPlanTempShiftUserList;
	}
	
	
	
	
}
