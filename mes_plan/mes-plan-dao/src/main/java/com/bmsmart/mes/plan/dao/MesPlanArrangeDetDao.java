/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeDet;

/**
 * mesPlanArrangeDetDAO接口
 * @author zhouqz
 * @version 2018-03-13
 */
@MyBatisRepository
public interface MesPlanArrangeDetDao extends MesCrudDao<MesPlanArrangeDet> {
	public void deleteById(String id);

	public void deleteBySheetId(String sheetId);
	
	public int updateSheetStatus2SheetId(MesPlanArrangeDet mesPlanArrangeDet);
}