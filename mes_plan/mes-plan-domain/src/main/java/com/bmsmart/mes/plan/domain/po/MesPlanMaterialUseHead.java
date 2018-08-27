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
 * mes_plan_material_use_head
 * @author zhouqizhi
 * @version 2017-09-11
 */
public class MesPlanMaterialUseHead extends MesDataEntity<MesPlanMaterialUseHead> {
	
	private static final long serialVersionUID = 1L;
	private String sheetId;		// sheet_id
	private String status;		// status
	private String title;		// title
	private String useDesc;		// use_desc
	private String useType;		// use_type
	private String usePrior;		// use_prior
	private Date useDate;		// use_date
	private String useUser;		// use_user
	private String workshopId;	//workshopId
	
	private Date useDate1;		// 查询条件
	private Date useDate2;		// 查询条件
	List<MesPlanMaterialUseDet> mesPlanMaterialUseDetList;
	
	public MesPlanMaterialUseHead() {
		super();
	}

	public MesPlanMaterialUseHead(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sheet_id长度必须介于 0 和 64 之间")
	public String getSheetId() {
		return sheetId;
	}

	public void setSheetId(String sheetId) {
		this.sheetId = sheetId;
	}
	
	@Length(min=0, max=10, message="status长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=256, message="title长度必须介于 0 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=10, message="use_desc长度必须介于 0 和 10 之间")
	public String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}
	
	@Length(min=0, max=10, message="use_type长度必须介于 0 和 10 之间")
	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}
	
	@Length(min=0, max=10, message="use_prior长度必须介于 0 和 10 之间")
	public String getUsePrior() {
		return usePrior;
	}

	public void setUsePrior(String usePrior) {
		this.usePrior = usePrior;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Length(min=0, max=64, message="use_user长度必须介于 0 和 64 之间")
	public String getUseUser() {
		return useUser;
	}

	public void setUseUser(String useUser) {
		this.useUser = useUser;
	}

	public String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}

	public List<MesPlanMaterialUseDet> getMesPlanMaterialUseDetList() {
		return mesPlanMaterialUseDetList;
	}

	public void setMesPlanMaterialUseDetList(List<MesPlanMaterialUseDet> mesPlanMaterialUseDetList) {
		this.mesPlanMaterialUseDetList = mesPlanMaterialUseDetList;
	}

	public Date getUseDate1() {
		return useDate1;
	}

	public void setUseDate1(Date useDate1) {
		this.useDate1 = useDate1;
	}

	public Date getUseDate2() {
		return useDate2;
	}

	public void setUseDate2(Date useDate2) {
		this.useDate2 = useDate2;
	}
	
	
}