/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;

/**
 * zhouqzDAO接口
 * @author zhouqz
 * @version 2018-03-04
 */
@MyBatisRepository
public interface MesPlanTempTaskDetDao extends MesCrudDao<MesPlanTempTaskDet> {
	public void deleteById(String id);

	public String getMaxBillNo(String srcBillNo);

	public MesPlanTempTaskDet getByBillNo(String billno);

	public int updateShiftStatus(MesPlanTempTaskDet det);

	public void updateShiftFinishStatus(MesPlanTempTaskDet det);

	public void updateShiftCancelStatus(MesPlanTempTaskDet det);
	
	public int updateFinishQtyById(MesPlanTempTaskDet mesPlanTempTaskDet);
	
	public int updateFinishQtyByBillNo(MesPlanTempTaskDet mesPlanTempTaskDet);

	public void deleteBySheetId(String sheetId);
	
}