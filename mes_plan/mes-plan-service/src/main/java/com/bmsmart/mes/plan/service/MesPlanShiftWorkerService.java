/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanShiftWorker;

/**
 * testService
 * @author zhouqizhi
 * @version 2017-10-18
 */

public interface MesPlanShiftWorkerService  {

	public MesPlanShiftWorker get(String id);
	
	public List<MesPlanShiftWorker> findList(MesPlanShiftWorker mesPlanShiftWorker);
	
	
	public  PageInfo<MesPlanShiftWorker> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanShiftWorker mesPlanShiftWorker);
	public void delete(MesPlanShiftWorker mesPlanShiftWorker);
	public void deleteById(String id);
	
}