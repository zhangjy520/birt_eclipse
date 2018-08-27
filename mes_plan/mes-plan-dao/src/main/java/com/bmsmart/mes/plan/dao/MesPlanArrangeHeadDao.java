/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;

/**
 * mes_plan_arrange_headDAO接口
 * @author zhouqz
 * @version 2018-03-13
 */
@MyBatisRepository
public interface MesPlanArrangeHeadDao extends MesCrudDao<MesPlanArrangeHead> {
	public void deleteById(String id);
	public MesPlanArrangeHead getHeadBySheetId(String sheetId);
	public MesPlanArrangeHead getHeadByUk(MesPlanTempShiftHead mesPlanTempShiftHead);
	
	
}