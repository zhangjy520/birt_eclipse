/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanBom;

/**
 * 物料清单Service
 * @author zhouqizhi
 * @version 2017-11-22
 */

public interface MesPlanBomService  {

	public MesPlanBom get(String id);
	
	public List<MesPlanBom> findList(MesPlanBom mesPlanBom);
	
	
	public  PageInfo<MesPlanBom> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanBom mesPlanBom);
	public void delete(MesPlanBom mesPlanBom);
	public void deleteById(String id);
	
}