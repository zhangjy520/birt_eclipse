/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;

/**
 * mes_plan_material_useService
 * @author zhouqizhi
 * @version 2017-09-11
 */

public interface MesPlanMaterialUseService  {

	public MesPlanMaterialUse get(String id);
	public List<MesPlanMaterialUse> findList(MesPlanMaterialUse mesPlanMaterialUse);
	public  PageInfo<MesPlanMaterialUse> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public  PageInfo<MesPlanMaterialUse> findPage(int pageNum, int pageSize,
			MesPlanMaterialUse msesPlanMaterialUse);
	
	public void save(MesPlanMaterialUse mesPlanMaterialUse);
	
	public int updateUseQtyByUk(MesPlanMaterialUse mesPlanMaterialUse);
	 
	
	public void delete(MesPlanMaterialUse mesPlanMaterialUse);
	public void deleteById(String id);
	
}