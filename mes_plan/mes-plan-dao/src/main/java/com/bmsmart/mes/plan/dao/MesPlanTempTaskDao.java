/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;

/**
 * mes_plan_temp_taskDAO接口
 * @author zhouqz
 * @version 2017-12-22
 */
@MyBatisRepository
public interface MesPlanTempTaskDao extends MesCrudDao<MesPlanTempTask> {
	public MesPlanTempTask getBySheetId(String sheetId);
	public void deleteById(String id);
	/**
	 * 下发
	 * @param det
	 */
	public int updateCheckTaskStatus(MesPlanTempTask det);
	/**
	 * 取消下发
	 * @param det
	 */
	public int updateCancelTaskStatus(MesPlanTempTask det);
	/**
	 * 领任务
	 * @param det
	 */
	public int updateUseTaskStatus(MesPlanTempTask det);
	/**
	 * 任务完工
	 * @param det
	 */
	public int updateFinishTaskStatus(MesPlanTempTask det);
}