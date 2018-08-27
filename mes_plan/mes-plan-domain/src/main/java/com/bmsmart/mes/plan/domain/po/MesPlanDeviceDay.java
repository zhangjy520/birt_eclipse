/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.domain.po;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.bmsmart.mes.commons.persistence.MesDataEntity;

/**
 * mes_plan_device_day
 * @author zhouqz
 * @version 2017-08-22
 */
public class MesPlanDeviceDay extends MesDataEntity<MesPlanDeviceDay> {
	
	private static final long serialVersionUID = 1L;
	private String workcenterId;		// workcenter_id
	private String deviceId;		// device_id
	private String goodsId;		// goods_id
	private Date workDate;		// work_date
	private Date workDate1;	//开始时间
	private Date workDate2;	//结束时间
	private String scheduleStatus;
	private String isChgGoods;
	
	public MesPlanDeviceDay() {
		super();
	}

	public MesPlanDeviceDay(String id){
		super(id);
	}

	@Length(min=0, max=64, message="workcenter_id长度必须介于 0 和 64 之间")
	public String getWorkcenterId() {
		return workcenterId;
	}

	public void setWorkcenterId(String workcenterId) {
		this.workcenterId = workcenterId;
	}
	
	@Length(min=0, max=64, message="device_id长度必须介于 0 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=64, message="goods_id长度必须介于 0 和 64 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Date getWorkDate1() {
		return workDate1;
	}

	public void setWorkDate1(Date workDate1) {
		this.workDate1 = workDate1;
	}

	public Date getWorkDate2() {
		return workDate2;
	}

	public void setWorkDate2(Date workDate2) {
		this.workDate2 = workDate2;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getIsChgGoods() {
		return isChgGoods;
	}

	public void setIsChgGoods(String isChgGoods) {
		this.isChgGoods = isChgGoods;
	}
	
}