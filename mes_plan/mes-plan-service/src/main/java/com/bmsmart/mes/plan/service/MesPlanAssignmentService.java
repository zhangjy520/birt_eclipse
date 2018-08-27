/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanAssignment;

/**
 * 任务书Service
 * @author zhouqizhi
 * @version 2017-11-22
 */

public interface MesPlanAssignmentService  {

	public MesPlanAssignment get(String id);
	
	public List<MesPlanAssignment> findList(MesPlanAssignment mesPlanAssignment);
	
	
	public  PageInfo<MesPlanAssignment> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanAssignment mesPlanAssignment);
	public void delete(MesPlanAssignment mesPlanAssignment);
	public void deleteById(String id);
	
}