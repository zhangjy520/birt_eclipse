/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.commons.persistence.DataEntity;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * 分解设备并行加工
 * @author zhouqz
 * @version 2017-08-16
 */
public class MesPlanDevice extends MesDataEntity<MesPlanDevice> {
	
	private static final long serialVersionUID = 1L;
	public static final String WORKSHOP_ARRANGE = "C14";//整理车间编码
	private String dispatchStatus;		// dispatch_status
	private Date planBeginDate;		// plan_begin_date
	private Date planEndDate;		// plan_end_date
	private String srcBillNo;		// src_bill_no
//	private String createUser;		// create_user 基类有
//	private Date createTime;		// create_time
//	private String updateUser;		// update_user
//	private Date updateTime;		// update_time
	private String deviceRequire;		// device_require
	private String billNo;		// bill_no
	private Float workCenterQty;		// work_center_qty
	private Double finishQty;		// finish_qty
	private String deviceId;		// device_id
	private String scheduleStatus;		// schedule_status
	private String erpBillNo;		// erp_bill_no
	private Date realBeginDate;		// real_begin_date
	private Date realEndDate;		// real_end_date
	private String isChgGoods;		// real_end_date
	private String materialUseStatus;
	//private String workcenterId;	//统一整理车间和其它二个车间时增加
	private List<MesPlanShiftDet> mesPlanShiftDetList;//值班长排班班次明细
	private List<MesPlanShiftUser> mesPlanShiftUserList;//值班长排班人员明细
	private MesPlanMonth mesPlanMonth;
	private String noTechnic;
	private String deviceName;
	
	private String unfinished;//未完工订单
	public MesPlanDevice() {
		super();
	}

	public MesPlanDevice(String id){
		super(id);
	}

 
	
	@Length(min=0, max=10, message="dispatch_status长度必须介于 0 和 10 之间")
	public String getDispatchStatus() {
		return dispatchStatus;
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
	
	@Length(min=0, max=64, message="src_bill_no长度必须介于 0 和 64 之间")
	public String getSrcBillNo() {
		return srcBillNo;
	}

	public void setSrcBillNo(String srcBillNo) {
		this.srcBillNo = srcBillNo;
	}
	
	@Length(min=0, max=4000, message="device_require长度必须介于 0 和 4000 之间")
	public String getDeviceRequire() {
		return deviceRequire;
	}

	public void setDeviceRequire(String deviceRequire) {
		this.deviceRequire = deviceRequire;
	}
	
	@Length(min=0, max=64, message="bill_no长度必须介于 0 和 64 之间")
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public Float getWorkCenterQty() {
		return workCenterQty;
	}

	public void setWorkCenterQty(Float workCenterQty) {
		this.workCenterQty = workCenterQty;
	}
	
	 
	
	public Double getFinishQty() {
		return finishQty;
	}

	public void setFinishQty(Double finishQty) {
		this.finishQty = finishQty;
	}

	@Length(min=0, max=64, message="device_id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=10, message="schedule_status长度必须介于 0 和 10 之间")
	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	
	@Length(min=0, max=64, message="erp_bill_no长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getRealBeginDate() {
		return realBeginDate;
	}

	public void setRealBeginDate(Date realBeginDate) {
		this.realBeginDate = realBeginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}

	public String getIsChgGoods() {
		return isChgGoods;
	}

	public void setIsChgGoods(String isChgGoods) {
		this.isChgGoods = isChgGoods;
	}

	public List<MesPlanShiftDet> getMesPlanShiftDetList() {
		return mesPlanShiftDetList;
	}

	public void setMesPlanShiftDetList(List<MesPlanShiftDet> mesPlanShiftDetList) {
		this.mesPlanShiftDetList = mesPlanShiftDetList;
	}

	public List<MesPlanShiftUser> getMesPlanShiftUserList() {
		return mesPlanShiftUserList;
	}

	public void setMesPlanShiftUserList(List<MesPlanShiftUser> mesPlanShiftUserList) {
		this.mesPlanShiftUserList = mesPlanShiftUserList;
	}

	public String getMaterialUseStatus() {
		return materialUseStatus;
	}

	public void setMaterialUseStatus(String materialUseStatus) {
		this.materialUseStatus = materialUseStatus;
	}

	public MesPlanMonth getMesPlanMonth() {
		return mesPlanMonth;
	}

	public void setMesPlanMonth(MesPlanMonth mesPlanMonth) {
		this.mesPlanMonth = mesPlanMonth;
	}

	public String getNoTechnic() {
		return noTechnic;
	}

	public void setNoTechnic(String noTechnic) {
		this.noTechnic = noTechnic;
	}

	private List<String> tmpNoTechnicList;
	public List<String> getNoTechnicList() {
		//无此功能，提高效率
		return new ArrayList<String>();
//		if (StringUtil.isBlank(noTechnic)){
//			return new ArrayList<String>();
//		}else
//		{
//			if (tmpNoTechnicList != null){
//				return tmpNoTechnicList;
//			}
//			String[] strArr = noTechnic.split(",");
//			for(int i=0 ;i<strArr.length ;i++){
//				strArr[i] = strArr[i].split(" ")[0];
//			}
//			tmpNoTechnicList = java.util.Arrays.asList(strArr);
//			return tmpNoTechnicList;
//		}
		
	}

	public String getUnfinished() {
		return unfinished;
	}

	public void setUnfinished(String unfinished) {
		this.unfinished = unfinished;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}   
	
//	public String getWorkcenterId() {
//		return workcenterId;
//	}
//
//	public void setWorkcenterId(String workcenterId) {
//		this.workcenterId = workcenterId;
//	}
	
}