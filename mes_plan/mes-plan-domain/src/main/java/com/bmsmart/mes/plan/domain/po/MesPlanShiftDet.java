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
 * mes_plan_shift_det
 * @author zhouqz
 * @version 2017-09-04
 */
public class MesPlanShiftDet extends MesDataEntity<MesPlanShiftDet> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private String billNo;		// bill_no
	private String srcBillNo;		// src_bill_no
	private String erpBillNo;		// erp_bill_no
	private float shiftQty;		// shift_qty
	private double finishQty;		// finish_qty
	private String shiftStatus;//下发标志
	private String shiftId;			//班次，关联表头数据
	private Date shiftDate;//关联主表查询条件
	private String workshopId;//关联主表查询条件
	private String unfinished;
	private String taskUser;
	protected Date taskTime;
	
	private List<MesPlanShiftUser> mesPlanShiftUserList;
	public MesPlanShiftDet() {
		super();
	}

	public MesPlanShiftDet(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=64, message="bill_no长度必须介于 0 和 64 之间")
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	@Length(min=0, max=64, message="src_bill_no长度必须介于 0 和 64 之间")
	public String getSrcBillNo() {
		return srcBillNo;
	}

	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	
	@Length(min=0, max=64, message="erp_bill_no长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}

	public float getShiftQty() {
		return shiftQty;
	}

	public void setShiftQty(float shiftQty) {
		this.shiftQty = shiftQty;
	}

 
	public double getFinishQty() {
		return finishQty;
	}

	public void setFinishQty(double finishQty) {
		this.finishQty = finishQty;
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

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public List<MesPlanShiftUser> getMesPlanShiftUserList() {
		return mesPlanShiftUserList;
	}

	public void setMesPlanShiftUserList(List<MesPlanShiftUser> mesPlanShiftUserList) {
		this.mesPlanShiftUserList = mesPlanShiftUserList;
	}

	public String getShiftStatus() {
		return shiftStatus;
	}

	public void setShiftStatus(String shiftStatus) {
		this.shiftStatus = shiftStatus;
	}

	public String getUnfinished() {
		return unfinished;
	}

	public void setUnfinished(String unfinished) {
		this.unfinished = unfinished;
	}

	public String getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(String taskUser) {
		this.taskUser = taskUser;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}
	
	
}