/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;

/**
 * 排班表头DAO接口
 * @author zhouqz
 * @version 2017-09-04
 */
@MyBatisRepository
public interface MesPlanTempShiftHeadDao extends MesCrudDao<MesPlanTempShiftHead> {
	//public void deleteById(String id);

	public MesPlanTempShiftHead getHeadByUk(MesPlanTempShiftHead mesPlanTempShiftHead);

	public MesPlanTempShiftHead getHeadBySheetId(String sheetId);
	
	public int updateSheetStatus(MesPlanTempShiftHead mesPlanShiftHead);

}