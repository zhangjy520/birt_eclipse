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
 * mes_plan_material_use
 * @author zhouqizhi
 * @version 2017-09-11
 */
public class MesPlanMaterialUse extends MesDataEntity<MesPlanMaterialUse> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private Date scheduleDate;		// schedule_date
	private String goodsId;		// goods_id
	private Float qty;		// qty
	private Float useQty;		// use_qty
	private String workshopId;
	private String erpBillNo;
	private String billNo;
	private String batchNo;
	private String batchProp1;
	private String batchProp2;
	private String supplier;
	private String unit;
	private String unitCode;
	
	private String goodsName;		// goods_id
	public MesPlanMaterialUse() {
		super();
	}

	public MesPlanMaterialUse(String id){
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
	
	@Length(min=0, max=64, message="goods_id长度必须介于 0 和 64 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Float getQty() {
		return qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}

	public Float getUseQty() {
		return useQty;
	}

	public void setUseQty(Float useQty) {
		this.useQty = useQty;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchProp1() {
		return batchProp1;
	}

	public void setBatchProp1(String batchProp1) {
		this.batchProp1 = batchProp1;
	}

	public String getBatchProp2() {
		return batchProp2;
	}

	public void setBatchProp2(String batchProp2) {
		this.batchProp2 = batchProp2;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	
}