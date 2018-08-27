/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanTechnicWorktype;

/**
 * 工种Service
 * @author zhouqizhi
 * @version 2017-10-18
 */

public interface MesPlanTechnicWorktypeService  {

	public MesPlanTechnicWorktype get(String id);
	
	public List<MesPlanTechnicWorktype> findList(MesPlanTechnicWorktype mesPlanTechnicWorktype);
	
	
	public  PageInfo<MesPlanTechnicWorktype> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanTechnicWorktype mesPlanTechnicWorktype);
	public void delete(MesPlanTechnicWorktype mesPlanTechnicWorktype);
	public void deleteById(String id);
	
}