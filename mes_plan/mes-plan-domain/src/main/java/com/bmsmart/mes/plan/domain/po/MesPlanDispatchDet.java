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
 * 厂级调度明细
 * @author 周奇志
 * @version 2017-08-07
 */
public class MesPlanDispatchDet extends MesDataEntity<MesPlanDispatchDet> {
	
	private static final long serialVersionUID = 1L;
	public  static String LASTARRANGEGROUP = "JP18";//打包
	public  static String LASTARRANGEGROUP2 = "JP17";//卷布成包
	private Integer deviceQty;		// 设备数
	private Integer deviceChangeQty;		// 换产品换备数
	private Double dispatchQty;		// 调度数量
	private String workcenterId;		// 工作中心
	private String dispatchStatus;		// 状态
	private Date planBeginDate;		// 开始日期
	private Date planEndDate;		// 结束日期
	private String srcBillNo;		// 源单号
	private String deviceChangeGoodsId;		// 换产品
	private String deviceRequire;		// 说明
	private String sheetId;		// 单据编号
	private String billNo;		//调度单号
	private Date planBeginDate1;		// 开始日期 查询条件
	private Date planBeginDate2;		// 开始日期 查询条件
	private Double workCenterQty;		// 车间调度数量
	private Double finishQty;		// 完工数量
	private String technicLineId;//技术路线
	
	private Float capacityInput;
	
	private String arrangeGroup;
	private String shiftId;
	private String shiftStyle;
	private Float arrangeUnitRate;
	private String shiftBeginTime;
	private String shiftEndTime;
	private Double  dispatchQtyPackage;
	
	private String goodsName;//关联对象
	private String qtyUnit;//关联对象
	
	
	private String goodsId;//查询条件
	private String erpBillNo;//查询条件
	private String workshopId;//查询条件
	private String dispatchStatus2;		//查询条件 过滤状态
	private String unfinished;	//未完工单据
	
	public MesPlanDispatchDet() {
		super();
	}

	public MesPlanDispatchDet(String id){
		super(id);
	}

	public Integer getDeviceQty() {
		return deviceQty;
	}

	public void setDeviceQty(Integer deviceQty) {
		this.deviceQty = deviceQty;
	}
	
	public Integer getDeviceChangeQty() {
		return deviceChangeQty;
	}

	public void setDeviceChangeQty(Integer deviceChangeQty) {
		this.deviceChangeQty = deviceChangeQty;
	}
	
 
	public Double getDispatchQty() {
		return dispatchQty;
	}

	public void setDispatchQty(Double dispatchQty) {
		this.dispatchQty = dispatchQty;
	}

	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getDispatchStatus() {
		return dispatchStatus;
	}
	@Length(min=0, max=10, message="工作中心长度必须介于 0 和 10 之间")
	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}

	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getPlanBeginDate() {
		return planBeginDate;
	}
	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	@Length(min=0, max=64, message="源单号长度必须介于 0 和 64 之间")
	public String getSrcBillNo() {
		return srcBillNo;
	}

	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	
	@Length(min=0, max=64, message="换产品长度必须介于 0 和 64 之间")
	public String getDeviceChangeGoodsId() {
		return deviceChangeGoodsId;
	}

	public void setDeviceChangeGoodsId(String deviceChangeGoodsId) {
		this.deviceChangeGoodsId = deviceChangeGoodsId;
	}
	
	@Length(min=0, max=4000, message="说明长度必须介于 0 和 4000 之间")
	public String getDeviceRequire() {
		return deviceRequire;
	}

	public void setDeviceRequire(String deviceRequire) {
		this.deviceRequire = deviceRequire;
	}

	@Length(min=0, max=64, message="调度单号长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Date getPlanBeginDate1() {
		return planBeginDate1;
	}

	public void setPlanBeginDate1(Date planBeginDate1) {
		this.planBeginDate1 = planBeginDate1;
	}

	public Date getPlanBeginDate2() {
		return planBeginDate2;
	}

	public void setPlanBeginDate2(Date planBeginDate2) {
		this.planBeginDate2 = planBeginDate2;
	}

	 

	 

	public Double getWorkCenterQty() {
		return workCenterQty;
	}

	public void setWorkCenterQty(Double workCenterQty) {
		this.workCenterQty = workCenterQty;
	}

	public Double getFinishQty() {
		return finishQty;
	}

	public void setFinishQty(Double finishQty) {
		this.finishQty = finishQty;
	}

	public String getTechnicLineId() {
		return technicLineId;
	}

	public void setTechnicLineId(String technicLineId) {
		this.technicLineId = technicLineId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}

	public Float getCapacityInput() {
		return capacityInput;
	}

	public void setCapacityInput(Float capacityInput) {
		this.capacityInput = capacityInput;
	}

	public String getArrangeGroup() {
		return arrangeGroup;
	}

	public void setArrangeGroup(String arrangeGroup) {
		this.arrangeGroup = arrangeGroup;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftStyle() {
		return shiftStyle;
	}

	public void setShiftStyle(String shiftStyle) {
		this.shiftStyle = shiftStyle;
	}

	public Float getArrangeUnitRate() {
		return arrangeUnitRate;
	}

	public void setArrangeUnitRate(Float arrangeUnitRate) {
		this.arrangeUnitRate = arrangeUnitRate;
	}

	public String getShiftBeginTime() {
		return shiftBeginTime;
	}

	public void setShiftBeginTime(String shiftBeginTime) {
		this.shiftBeginTime = shiftBeginTime;
	}

	public String getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	

	public Double getDispatchQtyPackage() {
		return dispatchQtyPackage;
	}

	public void setDispatchQtyPackage(Double dispatchQtyPackage) {
		this.dispatchQtyPackage = dispatchQtyPackage;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public String getDispatchStatus2() {
		return dispatchStatus2;
	}

	public void setDispatchStatus2(String dispatchStatus2) {
		this.dispatchStatus2 = dispatchStatus2;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getQtyUnit() {
		return qtyUnit;
	}

	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}

	public String getUnfinished() {
		return unfinished;
	}

	public void setUnfinished(String unfinished) {
		this.unfinished = unfinished;
	}
	
	
}