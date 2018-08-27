/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.bmsmart.mes.commons.persistence.DataEntity;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * 订单工序
 * @author zhouqizhi
 * @version 2017-11-22
 */
public class MesPlanTechnic extends MesDataEntity<MesPlanTechnic> {
	
	private static final long serialVersionUID = 1L;
	private String erpBillNo;		// erp_bill_no
	private String technicId;		// 工序编码
	private String technicName;		// 工序名称
	private String technicIndex;		// 工序序号
	private String technicDesc;		// 工序描述
	private String workcenterId;		// 工作中心
	private String worktypeId;		// 工种，多个时默认一个。
	private String worktypeStdTime;		// 标准工时
	private String worktypeUserTime;		// 人力工时
	
	List<Map<String, Object>> subTechnicList; 
	
	public MesPlanTechnic() {
		super();
	}

	public MesPlanTechnic(String id){
		super(id);
	}

	@Length(min=0, max=64, message="erp_bill_no长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@Length(min=0, max=64, message="工序编码长度必须介于 0 和 64 之间")
	public String getTechnicId() {
		return technicId;
	}

	public void setTechnicId(String technicId) {
		this.technicId = technicId;
	}
	
	@Length(min=0, max=128, message="工序名称长度必须介于 0 和 128 之间")
	public String getTechnicName() {
		return technicName;
	}

	public void setTechnicName(String technicName) {
		this.technicName = technicName;
	}
	
	@Length(min=0, max=11, message="工序序号长度必须介于 0 和 11 之间")
	public String getTechnicIndex() {
		return technicIndex;
	}

	public void setTechnicIndex(String technicIndex) {
		this.technicIndex = technicIndex;
	}
	
	@Length(min=0, max=2000, message="工序描述长度必须介于 0 和 2000 之间")
	public String getTechnicDesc() {
		return technicDesc;
	}

	public void setTechnicDesc(String technicDesc) {
		this.technicDesc = technicDesc;
	}
	
	@Length(min=0, max=64, message="工作中心长度必须介于 0 和 64 之间")
	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}
	
	@Length(min=0, max=64, message="工种，多个时默认一个。长度必须介于 0 和 64 之间")
	public String getWorktypeId() {
		return worktypeId;
	}

	public void setWorktypeId(String worktypeId) {
		this.worktypeId = worktypeId;
	}
	
	public String getWorktypeStdTime() {
		return worktypeStdTime;
	}

	public void setWorktypeStdTime(String worktypeStdTime) {
		this.worktypeStdTime = worktypeStdTime;
	}
	
	public String getWorktypeUserTime() {
		return worktypeUserTime;
	}

	public void setWorktypeUserTime(String worktypeUserTime) {
		this.worktypeUserTime = worktypeUserTime;
	}

	public List<Map<String, Object>> getSubTechnicList() {
		return subTechnicList;
	}

	public void setSubTechnicList(List<Map<String, Object>> subTechnicList) {
		this.subTechnicList = subTechnicList;
	}
	
	
}