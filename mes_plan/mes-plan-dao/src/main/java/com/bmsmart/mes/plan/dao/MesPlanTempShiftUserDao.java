/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import java.util.List;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;

/**
 * mes_plan_shift_userDAO接口
 * @author zhouqz
 * @version 2017-09-04
 */
@MyBatisRepository
public interface MesPlanTempShiftUserDao extends MesCrudDao<MesPlanTempShiftUser> {
	//public void deleteById(String id);
	public List<MesPlanTempShiftUser> getUserTaskList(MesPlanTempShiftUser mesPlanTempShiftUser);
	public int updateTaskStatus(MesPlanTempShiftUser mesPlanTempShiftUser);
}