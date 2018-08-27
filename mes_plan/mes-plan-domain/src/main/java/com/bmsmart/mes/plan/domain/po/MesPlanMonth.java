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
 * mes月计划查询
 * @author 周奇志
 * @version 2017-07-31
 */
public class MesPlanMonth extends MesDataEntity<MesPlanMonth> {
	
	private static final long serialVersionUID = 1L;
	private String erpBillNo;		// 单号
	private String planStatus;		// 计划状态
	private String goodsId;		// goods_id
	private String goodsName;		// goodsName
	private String goodsCat;		// goods_cat
	private String customerId;		// customer_id
	private Date planBeginDate;		// plan_begin_date
	private Date planEndDate;		// plan_end_date
	private String billType;		// bill_type
	private Double qty;				// qty
	private Double dispatchQty;		// 分派数量
	private Double finishQty;		// 完工数量
	private String workshopId;		// 生产车间
	private String contractId;		// contract_id
	private String srcErpBillNo;		// 源单号
	private String qtyUnit;				//单位名称
	private String qtyUnitCode;				//单位code
	private String goodsErpBillNo;//成品订单号
	
	private String qualityRequire;		//生产任务书,质量要求
	private String packageRequire;		//生产任务书,包装条款
	private String mark;				//生产任务书,唛头
	private String deliverTerms;		//生产任务书,交货要求
	private String erpProductTaskNo;	//生产任务书,生产任务书编号
	private String taskBillNo ;//生产任务书编号
	
	private List<MesPlanBom> mesPlanBomList;
	private List<MesPlanTechnic> mesPlanTechnicList;
	
	private Date planEndDate1;//查询条件开始
	private Date planEndDate2;//查询条件结束
	
	private Date planBeginDate1;//查询条件开始
	private Date planBeginDate2;//查询条件结束
	private List<String> planStatusList;//查询条件
	
	private String bomGoodsId;
	
	private String workcenterId;
	private List<String> billTypeList;
	public MesPlanMonth() {
		super();
	}

	public MesPlanMonth(String id){
		super(id);
	}

	@Length(min=0, max=64, message="单号长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@Length(min=0, max=10, message="计划状态长度必须介于 0 和 10 之间")
	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	
	@Length(min=0, max=32, message="goods_id长度必须介于 0 和 32 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=0, max=14, message="goods_cat长度必须介于 0 和 14 之间")
	public String getGoodsCat() {
		return goodsCat;
	}

	public void setGoodsCat(String goodsCat) {
		this.goodsCat = goodsCat;
	}
	
	@Length(min=0, max=32, message="customer_id长度必须介于 0 和 32 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	
	@Length(min=0, max=10, message="bill_type长度必须介于 0 和 10 之间")
	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	 
  
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getDispatchQty() {
		return dispatchQty;
	}

	public void setDispatchQty(Double dispatchQty) {
		this.dispatchQty = dispatchQty;
	}

	public Double getFinishQty() {
		return finishQty;
	}

	public void setFinishQty(Double finishQty) {
		this.finishQty = finishQty;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	@Length(min=0, max=4000, message="包装条款长度必须介于 0 和 4000 之间")
	public String getPackageRequire() {
		return packageRequire;
	}

	public void setPackageRequire(String packageRequire) {
		this.packageRequire = packageRequire;
	}
	
	@Length(min=0, max=64, message="contract_id长度必须介于 0 和 64 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=0, max=64, message="源单号长度必须介于 0 和 64 之间")
	public String getSrcErpBillNo() {
		return srcErpBillNo;
	}

	public void setSrcErpBillNo(String srcErpBillNo) {
		this.srcErpBillNo = srcErpBillNo;
	}
	
	@Length(min=0, max=4000, message="质量要求长度必须介于 0 和 4000 之间")
	public String getQualityRequire() {
		return qualityRequire;
	}

	public void setQualityRequire(String qualityRequire) {
		this.qualityRequire = qualityRequire;
	}

	public Date getPlanEndDate1() {
		return planEndDate1;
	}

	public void setPlanEndDate1(Date planEndDate1) {
		this.planEndDate1 = planEndDate1;
	}

	public Date getPlanEndDate2() {
		return planEndDate2;
	}

	public void setPlanEndDate2(Date planEndDate2) {
		this.planEndDate2 = planEndDate2;
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

	public String getErpProductTaskNo() {
		return erpProductTaskNo;
	}

	public void setErpProductTaskNo(String erpProductTaskNo) {
		this.erpProductTaskNo = erpProductTaskNo;
	}

	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}

	public List<String> getBillTypeList() {
		return billTypeList;
	}

	public void setBillTypeList(List<String> billTypeList) {
		this.billTypeList = billTypeList;
	}

	public String getQtyUnit() {
		return qtyUnit;
	}

	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}

	public List<MesPlanBom> getMesPlanBomList() {
		return mesPlanBomList;
	}

	public void setMesPlanBomList(List<MesPlanBom> mesPlanBomList) {
		this.mesPlanBomList = mesPlanBomList;
	}

	public List<MesPlanTechnic> getMesPlanTechnicList() {
		return mesPlanTechnicList;
	}

	public void setMesPlanTechnicList(List<MesPlanTechnic> mesPlanTechnicList) {
		this.mesPlanTechnicList = mesPlanTechnicList;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDeliverTerms() {
		return deliverTerms;
	}

	public void setDeliverTerms(String deliverTerms) {
		this.deliverTerms = deliverTerms;
	}

	public String getTaskBillNo() {
		return taskBillNo;
	}

	public void setTaskBillNo(String taskBillNo) {
		this.taskBillNo = taskBillNo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getQtyUnitCode() {
		return qtyUnitCode;
	}

	public void setQtyUnitCode(String qtyUnitCode) {
		this.qtyUnitCode = qtyUnitCode;
	}

	public String getBomGoodsId() {
		return bomGoodsId;
	}

	public void setBomGoodsId(String bomGoodsId) {
		this.bomGoodsId = bomGoodsId;
	}

	public List<String> getPlanStatusList() {
		return planStatusList;
	}

	public void setPlanStatusList(List<String> planStatusList) {
		this.planStatusList = planStatusList;
	}

	public String getGoodsErpBillNo() {
		return goodsErpBillNo;
	}

	public void setGoodsErpBillNo(String goodsErpBillNo) {
		this.goodsErpBillNo = goodsErpBillNo;
	}
	
	
}