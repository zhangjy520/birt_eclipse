/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;
import java.util.List;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;

/**
 * mes月计划查询DAO接口
 * @author 周奇志
 * @version 2017-07-31
 */
@MyBatisRepository
public interface MesPlanMonthDao extends MesCrudDao<MesPlanMonth> {
	//public void deleteById(String id);
	
	public Integer  updateDispatchQty(MesPlanMonth mesPlanMonth);
	
	public Integer updateSheetStatusById(MesPlanMonth mesPlanMonth);
	public Integer updateSheetStatusByErpBillNo(MesPlanMonth mesPlanMonth);

	public List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan);
	
	public int updateFinishQtyByBillNo(MesPlanMonth mesPlanMonth);
	
	
	public MesPlanMonth getByUk(MesPlanMonth paraPlan);
	
	
}