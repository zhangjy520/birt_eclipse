/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseHead;

/**
 * mes_plan_material_use_headService
 * @author zhouqizhi
 * @version 2017-09-11
 */

public interface MesPlanMaterialUseHeadService  {

	public MesPlanMaterialUseHead get(String id);
	
	public List<MesPlanMaterialUseHead> findList(MesPlanMaterialUseHead mesPlanMaterialUseHead);
	
	
	public  PageInfo<MesPlanMaterialUseHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanMaterialUseHead mesPlanMaterialUseHead);
	public void delete(MesPlanMaterialUseHead mesPlanMaterialUseHead);
	public void deleteById(String id);

	public String saveSheet(MesPlanMaterialUseHead mesPlanMaterialUseHead,
			List<MesPlanMaterialUseDet> mesPlanMaterialUseDetList);
	public int updateSheetStatus(MesPlanMaterialUseHead mesPlanMaterialUseHead);

	public PageInfo<MesPlanMaterialUseHead> findPage(int pageno, int pagesize, MesPlanMaterialUseHead head);
	
}