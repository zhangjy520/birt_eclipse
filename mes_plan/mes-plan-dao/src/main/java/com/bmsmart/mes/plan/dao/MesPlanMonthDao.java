/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;

/**
 * mes月计划查询DAO接口
 * 
 * @author 周奇志
 * @version 2017-07-31
 */
@MyBatisRepository
public interface MesPlanMonthDao extends MesCrudDao<MesPlanMonth> {
	// public void deleteById(String id);

	public Integer updateDispatchQty(MesPlanMonth mesPlanMonth);

	public Integer updateSheetStatusById(MesPlanMonth mesPlanMonth);

	public Integer updateSheetStatusByErpBillNo(MesPlanMonth mesPlanMonth);

	public List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan);

	public int updateFinishQtyByBillNo(MesPlanMonth mesPlanMonth);

	public MesPlanMonth getByUk(MesPlanMonth paraPlan);

	
	/*
	 * 根据动态查询条件查询列表
	 * 
	 * @author 张建雨
	 * @date 2018-08-28
	 */
	public List<MesPlanMonth> getMesPlanMonthListByCrities(MesPlanMonth param);
	

	/*
	 * 统计订单详情
	 * @param type //plan_status,bill_type分别v带不爱订单状态,订单类型
	 * @author 张建雨
	 * @date 2018-08-28
	 */
	public List<Map> getMesPlanMonthDetail(@Param("type") String type);
}