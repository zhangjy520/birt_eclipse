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
 * 物料清单
 * @author zhouqizhi
 * @version 2017-11-22
 */
public class MesPlanBom extends MesDataEntity<MesPlanBom> {
	
	private static final long serialVersionUID = 1L;
	private String erpBillNo;		// 生产订单号
	private String rawGoodsId;		// 原材料ID
	private String rawGoodsName;		// 原材料名称
	private String suplierId;		// 厂家
	private String spec;		// 规格
	private Float unitQty;		// 单位用量
	private Float orderQty;		// 订单所需总量
	private String unit;		// 单位
	private String unitCode;		// 单位
	private String rawGoodsType;
	private String batchNo;//批号
	private String batchProp1;//管头
	private String batchProp2;//筒纱标重
	public MesPlanBom() {
		super();
	}

	public MesPlanBom(String id){
		super(id);
	}

	@Length(min=0, max=64, message="生产订单号长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@Length(min=0, max=64, message="原材料ID长度必须介于 0 和 64 之间")
	public String getRawGoodsId() {
		return rawGoodsId;
	}

	public void setRawGoodsId(String rawGoodsId) {
		this.rawGoodsId = rawGoodsId;
	}
	
	@Length(min=0, max=128, message="原材料名称长度必须介于 0 和 128 之间")
	public String getRawGoodsName() {
		return rawGoodsName;
	}

	public void setRawGoodsName(String rawGoodsName) {
		this.rawGoodsName = rawGoodsName;
	}
	
	@Length(min=0, max=64, message="厂家长度必须介于 0 和 64 之间")
	public String getSuplierId() {
		return suplierId;
	}

	public void setSuplierId(String suplierId) {
		this.suplierId = suplierId;
	}
	
	@Length(min=0, max=64, message="规格长度必须介于 0 和 64 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public Float getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Float unitQty) {
		this.unitQty = unitQty;
	}
	
	public Float getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Float orderQty) {
		this.orderQty = orderQty;
	}
	
	@Length(min=0, max=64, message="单位长度必须介于 0 和 64 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRawGoodsType() {
		return rawGoodsType;
	}

	public void setRawGoodsType(String rawGoodsType) {
		this.rawGoodsType = rawGoodsType;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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
	
}