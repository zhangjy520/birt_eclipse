/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;

/**
 * mes_plan_shift_detDAO接口
 * @author zhouqz
 * @version 2017-09-04
 */
@MyBatisRepository
public interface MesPlanShiftDetDao extends MesCrudDao<MesPlanShiftDet> {
	//public void deleteById(String id);
	public MesPlanShiftDet getByBillNo(String billNo);
	public int updateShiftQtyById(MesPlanShiftDet mesPlanShiftDet);
	public int updateFinishQtyById(MesPlanShiftDet mesPlanShiftDet);
	public int updateFinishQtyByBillNo(MesPlanShiftDet mesPlanShiftDet);
	public int updateShiftStatus(MesPlanShiftDet mesPlanShiftDet);
	public int updateShiftFinishStatus(MesPlanShiftDet mesPlanShiftDet);
	public int updateShiftCancelStatus(MesPlanShiftDet mesPlanShiftDet);
	public String getMaxBillNo(String srcBillNo);
	
}