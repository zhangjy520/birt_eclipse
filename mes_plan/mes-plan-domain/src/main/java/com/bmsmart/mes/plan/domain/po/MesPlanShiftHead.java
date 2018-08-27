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
 * 排班表头
 * @author zhouqz
 * @version 2017-09-04
 */
public class MesPlanShiftHead extends MesDataEntity<MesPlanShiftHead> {
	
	private static final long serialVersionUID = 1L;
	public static final String WORKSHOP_ARRANGE = "C14";//整理车间编码
	private String sheetId;		// sheet_id
	private String workshopId;		// workshop_id
	private String shiftId;		// shift_id
	private Date shiftDate;		// shift_date
	private String shiftUser;		// shift_user
	private String shiftUserName;		// shift_user_Name
	private String shiftQtySum;		// shift_qty_sum
	private String finishQtySum;		// finish_qty_sum
	private String sheetStatus;
	private String shiftStyle;
	private String workcenterId;
	private String technicUser;//任务接收人
	
	private List<String> shiftIdList;//查询条件
	public MesPlanShiftHead() {
		super();
	}

	public MesPlanShiftHead(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=64, message="workshop_id长度必须介于 0 和 64 之间")
	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}
	
	@Length(min=0, max=32, message="shift_id长度必须介于 0 和 32 之间")
	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}
	
	@Length(min=0, max=64, message="shift_user长度必须介于 0 和 64 之间")
	public String getShiftUser() {
		return shiftUser;
	}

	public void setShiftUser(String shiftUser) {
		this.shiftUser = shiftUser;
	}
	
	public String getShiftQtySum() {
		return shiftQtySum;
	}

	public void setShiftQtySum(String shiftQtySum) {
		this.shiftQtySum = shiftQtySum;
	}
	
	public String getFinishQtySum() {
		return finishQtySum;
	}

	public void setFinishQtySum(String finishQtySum) {
		this.finishQtySum = finishQtySum;
	}

	public String getSheetStatus() {
		return sheetStatus;
	}

	public void setSheetStatus(String sheetStatus) {
		this.sheetStatus = sheetStatus;
	}

	public String getShiftStyle() {
		return shiftStyle;
	}

	public void setShiftStyle(String shiftStyle) {
		this.shiftStyle = shiftStyle;
	}

	public static String getWORKSHOP_ARRANGE() {
		return WORKSHOP_ARRANGE;
	}

	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}

	public List<String> getShiftIdList() {
		return shiftIdList;
	}

	public void setShiftIdList(List<String> shiftIdList) {
		this.shiftIdList = shiftIdList;
	}

	public String getShiftUserName() {
		return shiftUserName;
	}

	public void setShiftUserName(String shiftUserName) {
		this.shiftUserName = shiftUserName;
	}

	public String getTechnicUser() {
		return technicUser;
	}

	public void setTechnicUser(String technicUser) {
		this.technicUser = technicUser;
	}	
	
	
}