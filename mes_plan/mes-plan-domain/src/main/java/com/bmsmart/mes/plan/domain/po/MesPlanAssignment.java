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
 * 任务书
 * @author zhouqizhi
 * @version 2017-11-22
 */
public class MesPlanAssignment extends MesDataEntity<MesPlanAssignment> {
	
	private static final long serialVersionUID = 1L;
	private String erpBillNo;		// 生产订单号
	private String assignmentBillNo;		// 下达号
	private String orderIndex;		// 顺序号
	private String taskBillNo;		// 任务书编号
	private String deliverTerms;		// 交货条款
	private String packageTerms;		// 包装条款
	private String mark;		// 唛头
	private String qualityRequire;		// 质量要求
	
	
	public MesPlanAssignment() {
		super();
	}

	public MesPlanAssignment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="生产订单号长度必须介于 0 和 64 之间")
	public String getErpBillNo() {
		return erpBillNo;
	}

	public void setErpBillNo(String erpBillNo) {
		this.erpBillNo = erpBillNo;
	}
	
	@Length(min=0, max=64, message="下达号长度必须介于 0 和 64 之间")
	public String getAssignmentBillNo() {
		return assignmentBillNo;
	}

	public void setAssignmentBillNo(String assignmentBillNo) {
		this.assignmentBillNo = assignmentBillNo;
	}
	
	@Length(min=0, max=11, message="顺序号长度必须介于 0 和 11 之间")
	public String getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	@Length(min=0, max=64, message="任务书编号长度必须介于 0 和 64 之间")
	public String getTaskBillNo() {
		return taskBillNo;
	}

	public void setTaskBillNo(String taskBillNo) {
		this.taskBillNo = taskBillNo;
	}
	
	@Length(min=0, max=3000, message="交货条款长度必须介于 0 和 3000 之间")
	public String getDeliverTerms() {
		return deliverTerms;
	}

	public void setDeliverTerms(String deliverTerms) {
		this.deliverTerms = deliverTerms;
	}
	
	@Length(min=0, max=3000, message="包装条款长度必须介于 0 和 3000 之间")
	public String getPackageTerms() {
		return packageTerms;
	}

	public void setPackageTerms(String packageTerms) {
		this.packageTerms = packageTerms;
	}
	
	@Length(min=0, max=1024, message="唛头长度必须介于 0 和 1024 之间")
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	@Length(min=0, max=3000, message="质量要求长度必须介于 0 和 3000 之间")
	public String getQualityRequire() {
		return qualityRequire;
	}

	public void setQualityRequire(String qualityRequire) {
		this.qualityRequire = qualityRequire;
	}
}