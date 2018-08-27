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
 * mes_plan_material_use_det
 * @author zhouqizhi
 * @version 2017-09-11
 */
public class MesPlanMaterialUseDet extends MesDataEntity<MesPlanMaterialUseDet> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private String goodsId;		// goodsId
	private Float useQty;		// use_qty
	private Float leftQty;
	 
	private Date scheduleDate;		// schedule_date
	
	public MesPlanMaterialUseDet() {
		super();
	}

	public MesPlanMaterialUseDet(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Float getUseQty() {
		return useQty;
	}

	public void setUseQty(Float useQty) {
		this.useQty = useQty;
	}

	public Float getLeftQty() {
		return leftQty;
	}

	public void setLeftQty(Float leftQty) {
		this.leftQty = leftQty;
	}
	
}