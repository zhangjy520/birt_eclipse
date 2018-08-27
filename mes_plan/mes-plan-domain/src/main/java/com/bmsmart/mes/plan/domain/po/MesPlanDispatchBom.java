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
 * 厂级下发BOM的批次
 * @author zhouqizhi
 * @version 2017-11-30
 */
public class MesPlanDispatchBom extends MesDataEntity<MesPlanDispatchBom> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private String erpBillNo;		// erp_bill_no
	private String batchNo;		// 批次
	private String batchProp1;		// 管头批次属性1
	private String batchProp2;		// 批次属性2
	private String batchProp3;		// 批次属性3
	private String rawGoodsId;		// raw_goods_id
	
	private String rawGoodsName;	//订单上面的字段
	private String suplierId;		//供应商
	private String rawGoodsType;	//原料类型
	private Float unitQty;			//单位用量
	private String unit;			//单位
	private String unitCode;			//单位
	
	private String billNo;			//厂级调度单号，查询条件用
	public MesPlanDispatchBom() {
		super();
	}

	public MesPlanDispatchBom(String id){
		super(id);
	}

	@Length(min=1, max=64, message="sheet_id长度必须介于 1 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=1, max=64, message="erp_bill_no长度必须介于 1 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@Length(min=0, max=64, message="批次长度必须介于 0 和 64 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=256, message="管头,批次属性1长度必须介于 0 和 256 之间")
	public String getBatchProp1() {
		return batchProp1;
	}

	public void setBatchProp1(String batchProp1) {
		this.batchProp1 = batchProp1;
	}
	
	@Length(min=0, max=256, message="批次属性2长度必须介于 0 和 256 之间")
	public String getBatchProp2() {
		return batchProp2;
	}

	public void setBatchProp2(String batchProp2) {
		this.batchProp2 = batchProp2;
	}
	
	@Length(min=0, max=256, message="批次属性3长度必须介于 0 和 256 之间")
	public String getBatchProp3() {
		return batchProp3;
	}

	public void setBatchProp3(String batchProp3) {
		this.batchProp3 = batchProp3;
	}
	
	@Length(min=0, max=64, message="raw_goods_id长度必须介于 0 和 64 之间")
	public String getRawGoodsId() {
		return rawGoodsId;
	}

	public void setRawGoodsId(String rawGoodsId) {
		this.rawGoodsId = rawGoodsId;
	}

	public String getRawGoodsName() {
		return rawGoodsName;
	}

	public void setRawGoodsName(String rawGoodsName) {
		this.rawGoodsName = rawGoodsName;
	}

	public String getSuplierId() {
		return suplierId;
	}

	public void setSuplierId(String suplierId) {
		this.suplierId = suplierId;
	}

	public String getRawGoodsType() {
		return rawGoodsType;
	}

	public void setRawGoodsType(String rawGoodsType) {
		this.rawGoodsType = rawGoodsType;
	}

	public Float getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Float unitQty) {
		this.unitQty = unitQty;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
}