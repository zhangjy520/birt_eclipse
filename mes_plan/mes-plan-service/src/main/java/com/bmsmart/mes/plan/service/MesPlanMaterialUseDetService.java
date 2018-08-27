/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseDet;

/**
 * mes_plan_material_use_detService
 * @author zhouqizhi
 * @version 2017-09-11
 */

public interface MesPlanMaterialUseDetService  {

	public MesPlanMaterialUseDet get(String id);
	
	public List<MesPlanMaterialUseDet> findList(MesPlanMaterialUseDet mesPlanMaterialUseDet);
	public List<MesPlanMaterialUseDet> findALLList(MesPlanMaterialUseDet mesPlanMaterialUseDet);
	
	public  PageInfo<MesPlanMaterialUseDet> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanMaterialUseDet mesPlanMaterialUseDet);
	public void delete(MesPlanMaterialUseDet mesPlanMaterialUseDet);
	public void deleteById(String id);
	
}