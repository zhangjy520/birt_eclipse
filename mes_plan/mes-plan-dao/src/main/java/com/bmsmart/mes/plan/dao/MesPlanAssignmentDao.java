/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanAssignment;

/**
 * 任务书DAO接口
 * @author zhouqizhi
 * @version 2017-11-22
 */
@MyBatisRepository
public interface MesPlanAssignmentDao extends MesCrudDao<MesPlanAssignment> {
	//public void deleteById(String id);
	public MesPlanAssignment getByErpBillNo(String erpBillNo);
}