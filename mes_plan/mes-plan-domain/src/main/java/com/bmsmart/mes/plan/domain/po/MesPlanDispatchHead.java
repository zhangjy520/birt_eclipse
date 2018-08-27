/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * 厂级调度单头
 * @author 周奇志
 * @version 2017-08-07
 */
public class MesPlanDispatchHead extends MesDataEntity<MesPlanDispatchHead> {
	
	private static final long serialVersionUID = 1L;
	public static final String SHEET_STATUS_NEW = "0";
	public static final String SHEET_STATUS_CANCEL = "99";
	public static final String SHEET_STATUS_FINISH = "100";
	private String sheetId;		// 厂级调度单号
	private String erpBillNo;		// 订单编号
	private Double dispatchQtySum;		//本次调度数量
	private String erpId;		// 订单id
	private String sheetStatus;
	private List<MesPlanDispatchDet> mesPlanDispatchDetList; //厂级调度明细
	private List<MesPlanDispatchBom> mesPlanDispatchBomList;
	private MesPlanMonth mesPlanMonth; //生产订单
	
	private String workshopId;//查询条件
	private String goodsId;//查询条件
	private String goodsName;//查询条件
	private String billType;//查询条件
	private String workcenterId;//查询条件
	private String billNo;//查询条件
	private String dispatchStatus;//查询条件
 
	public MesPlanDispatchHead() {
		super();
	}

	public MesPlanDispatchHead(String id){
		super(id);
	}

	@Length(min=0, max=64, message="厂级调度单号长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=64, message="订单编号长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}

	public List<MesPlanDispatchDet> getMesPlanDispatchDetList() {
		return mesPlanDispatchDetList;
	}

	public void setMesPlanDispatchDetList(List<MesPlanDispatchDet> mesPlanDispatchDetList) {
		this.mesPlanDispatchDetList = mesPlanDispatchDetList;
	}

	public MesPlanMonth getMesPlanMonth() {
		return mesPlanMonth;
	}

	public void setMesPlanMonth(MesPlanMonth mesPlanMonth) {
		this.mesPlanMonth = mesPlanMonth;
	}

	 

	public Double getDispatchQtySum() {
		return dispatchQtySum;
	}

	public void setDispatchQtySum(Double dispatchQtySum) {
		this.dispatchQtySum = dispatchQtySum;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getErpId() {
		return erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getSheetStatus() {
		return sheetStatus;
	}

	public void setSheetStatus(String sheetStatus) {
		this.sheetStatus = sheetStatus;
	}

	public String getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}

	public List<MesPlanDispatchBom> getMesPlanDispatchBomList() {
		return mesPlanDispatchBomList;
	}

	public void setMesPlanDispatchBomList(List<MesPlanDispatchBom> mesPlanDispatchBomList) {
		this.mesPlanDispatchBomList = mesPlanDispatchBomList;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
}