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
 * 工种
 * @author zhouqizhi
 * @version 2017-10-18
 */
public class MesPlanTechnicWorktype extends MesDataEntity<MesPlanTechnicWorktype> {
	
	private static final long serialVersionUID = 1L;
	private String technicId;		// technic_id
	private String workTypeId;		// work_type_id
	
	public MesPlanTechnicWorktype() {
		super();
	}

	public MesPlanTechnicWorktype(String id){
		super(id);
	}

	@Length(min=0, max=64, message="technic_id长度必须介于 0 和 64 之间")
	public String getTechnicId() {
		return technicId;
	}

	public void setTechnicId(String technicId) {
		this.technicId = technicId;
	}
	
	@Length(min=0, max=64, message="work_type_id长度必须介于 0 和 64 之间")
	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}
}