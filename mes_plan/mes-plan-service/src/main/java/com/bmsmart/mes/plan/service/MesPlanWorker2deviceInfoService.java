/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanWorker2deviceInfo;

/**
 * 工人设备Service
 * @author zhouqizhi
 * @version 2017-10-24
 */

public interface MesPlanWorker2deviceInfoService  {

	public MesPlanWorker2deviceInfo get(String id);
	
	public List<MesPlanWorker2deviceInfo> findList(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo);
	
	
	public  PageInfo<MesPlanWorker2deviceInfo> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo);
	public void delete(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo);
	public void deleteById(String id);
	
}