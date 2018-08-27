/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.bmsmart.mes.commons.persistence.DataEntity;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * mes_plan_arrange_head
 * @author zhouqz
 * @version 2018-03-13
 */
public class MesPlanArrangeHead extends MesDataEntity<MesPlanArrangeHead>  {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private Date shiftDate;		// shift_date
	private String technicId;		// technic_id
	private String planObject;		// plan_object
	private String totalQty;		// total_qty
	private String qtyUnit;
	
	private List<MesPlanArrangeDet> mesPlanArrangeDetList;
	
	
	public MesPlanArrangeHead() {
		super();
	}

	public MesPlanArrangeHead(String id){
		super(id);
	}

	@Length(min=1, max=64, message="sheet_id长度必须介于 1 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}
	
	@Length(min=1, max=256, message="technic_id长度必须介于 1 和 256 之间")
	public String getTechnicId() {
		return technicId;
	}

	public void setTechnicId(String technicId) {
		this.technicId = technicId;
	}
	
	@Length(min=0, max=64, message="plan_object长度必须介于 0 和 64 之间")
	public String getPlanObject() {
		return planObject;
	}

	public void setPlanObject(String planObject) {
		this.planObject = planObject;
	}
	
	public String getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}
	

	public List<MesPlanArrangeDet> getMesPlanArrangeDetList() {
		return mesPlanArrangeDetList;
	}

	public void setMesPlanArrangeDetList(List<MesPlanArrangeDet> mesPlanArrangeDetList) {
		this.mesPlanArrangeDetList = mesPlanArrangeDetList;
	}

	public String getQtyUnit() {
		return qtyUnit;
	}

	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}
	
	
	
}