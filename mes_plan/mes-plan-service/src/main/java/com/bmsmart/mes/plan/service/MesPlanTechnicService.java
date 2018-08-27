/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;

/**
 * 订单工序Service
 * @author zhouqizhi
 * @version 2017-11-22
 */

public interface MesPlanTechnicService  {

	public MesPlanTechnic get(String id);
	
	public List<MesPlanTechnic> findList(MesPlanTechnic mesPlanTechnic);
	
	
	public  PageInfo<MesPlanTechnic> findPage(int pageNum, int pageSize,
			Map<String, String> datas);
	public void save(MesPlanTechnic mesPlanTechnic);
	public void delete(MesPlanTechnic mesPlanTechnic);
	public void deleteById(String id);
	
}