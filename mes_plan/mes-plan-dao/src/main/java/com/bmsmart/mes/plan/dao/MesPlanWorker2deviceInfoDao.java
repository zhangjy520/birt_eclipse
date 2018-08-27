/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.CrudDao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanWorker2deviceInfo;

/**
 * 工人设备DAO接口
 * @author zhouqizhi
 * @version 2017-10-24
 */
@MyBatisRepository
public interface MesPlanWorker2deviceInfoDao extends MesCrudDao<MesPlanWorker2deviceInfo> {
	//public void deleteById(String id);
	public int updateByUk(MesPlanWorker2deviceInfo info);
}