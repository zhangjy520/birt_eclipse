/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;

/**
 * 厂级下发BOM的批次Service
 * @author zhouqizhi
 * @version 2017-11-30
 */

public interface MesPlanDispatchBomService  {

	public MesPlanDispatchBom get(String id);
	
	public List<MesPlanDispatchBom> findList(MesPlanDispatchBom mesPlanDispatchBom);
	
	
	public  PageInfo<MesPlanDispatchBom> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanDispatchBom mesPlanDispatchBom);
	public void delete(MesPlanDispatchBom mesPlanDispatchBom);
	public void deleteById(String id);

	public void saveBatch(List<MesPlanDispatchBom> mesPlanDispatchBomList);
	
}