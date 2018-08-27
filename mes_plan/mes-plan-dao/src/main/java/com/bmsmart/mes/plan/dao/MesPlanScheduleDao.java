/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.CrudDao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;

/**
 * mes_plan_scheduleDAO接口
 * @author zhouqizhi
 * @version 2017-08-29
 */
@MyBatisRepository
public interface MesPlanScheduleDao extends MesCrudDao<MesPlanSchedule> {
	//public void deleteById(String id);
	public MesPlanSchedule getByUk(MesPlanSchedule mesPlanSchedule);
}