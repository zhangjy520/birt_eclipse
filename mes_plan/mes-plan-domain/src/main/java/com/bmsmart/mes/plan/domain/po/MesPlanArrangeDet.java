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
 * mesPlanArrangeDet
 * @author zhouqz
 * @version 2018-03-13
 */
public class MesPlanArrangeDet extends MesDataEntity<MesPlanArrangeDet> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private String erpBillNo;		// erp_bill_no
	private String goodsId;		// goods_id
	private String goodsName;		// goods_id
	private Double leftQty;		// left_qty
	private String qtyUnit;		// qty_unit
	private String workcenterId;		// workcenter_id
	private String shiftId1;		// shift_id1
	private String shiftStyle1;		// shift_style1
	private String shiftId2;		// shift_id2
	private String shiftStyle2;		// shift_style2
	private Double qty1;		// qty1
	private Double qty2;		// qty2
	private String shiftStatus;		// shift_status
	private String planSheetId1;
	private String planSheetId2;
	private String canDeleteStatus ;		// 0=能删除1=不能删除
	private String arrangeErpBillNo;
	public MesPlanArrangeDet() {
		super();
	}

	public MesPlanArrangeDet(String id){
		super(id);
	}

	@Length(min=1, max=64, message="sheet_id长度必须介于 1 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=64, message="erp_bill_no长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@Length(min=0, max=64, message="goods_id长度必须介于 0 和 64 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	

	public Double getLeftQty() {
		return leftQty;
	}

	public void setLeftQty(Double leftQty) {
		this.leftQty = leftQty;
	}

	@Length(min=0, max=64, message="qty_unit长度必须介于 0 和 64 之间")
	public String getQtyUnit() {
		return qtyUnit;
	}

	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}
	
	@Length(min=0, max=64, message="workcenter_id长度必须介于 0 和 64 之间")
	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}
	
	@Length(min=0, max=64, message="shift_id1长度必须介于 0 和 64 之间")
	public String getShiftId1() {
		return shiftId1;
	}

	public void setShiftId1(String shiftId1) {
		this.shiftId1 = shiftId1;
	}
	
	@Length(min=0, max=64, message="shift_style1长度必须介于 0 和 64 之间")
	public String getShiftStyle1() {
		return shiftStyle1;
	}

	public void setShiftStyle1(String shiftStyle1) {
		this.shiftStyle1 = shiftStyle1;
	}
	
	@Length(min=0, max=64, message="shift_id2长度必须介于 0 和 64 之间")
	public String getShiftId2() {
		return shiftId2;
	}

	public void setShiftId2(String shiftId2) {
		this.shiftId2 = shiftId2;
	}
	
	@Length(min=0, max=64, message="shift_style2长度必须介于 0 和 64 之间")
	public String getShiftStyle2() {
		return shiftStyle2;
	}

	public void setShiftStyle2(String shiftStyle2) {
		this.shiftStyle2 = shiftStyle2;
	}

	

	public Double getQty1() {
		return qty1;
	}

	public void setQty1(Double qty1) {
		this.qty1 = qty1;
	}

	public Double getQty2() {
		return qty2;
	}

	public void setQty2(Double qty2) {
		this.qty2 = qty2;
	}

	public String getShiftStatus() {
		return shiftStatus;
	}

	public void setShiftStatus(String shiftStatus) {
		this.shiftStatus = shiftStatus;
	}

	public String getCanDeleteStatus() {
		return canDeleteStatus;
	}

	public void setCanDeleteStatus(String canDeleteStatus) {
		this.canDeleteStatus = canDeleteStatus;
	}

	public String getPlanSheetId1() {
		return planSheetId1;
	}

	public void setPlanSheetId1(String planSheetId1) {
		this.planSheetId1 = planSheetId1;
	}

	public String getPlanSheetId2() {
		return planSheetId2;
	}

	public void setPlanSheetId2(String planSheetId2) {
		this.planSheetId2 = planSheetId2;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getArrangeErpBillNo() {
		return arrangeErpBillNo;
	}

	public void setArrangeErpBillNo(String arrangeErpBillNo) {
		this.arrangeErpBillNo = arrangeErpBillNo;
	}
	
	
	
}