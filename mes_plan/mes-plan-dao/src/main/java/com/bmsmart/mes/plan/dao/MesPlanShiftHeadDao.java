/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;

/**
 * 排班表头DAO接口
 * @author zhouqz
 * @version 2017-09-04
 */
@MyBatisRepository
public interface MesPlanShiftHeadDao extends MesCrudDao<MesPlanShiftHead> {
	//public void deleteById(String id);

	public MesPlanShiftHead getHeadByUk(MesPlanShiftHead mesPlanShiftHead);

	public MesPlanShiftHead getHeadBySheetId(String sheetId);
	
	public int updateSheetStatus(MesPlanShiftHead mesPlanShiftHead);

}