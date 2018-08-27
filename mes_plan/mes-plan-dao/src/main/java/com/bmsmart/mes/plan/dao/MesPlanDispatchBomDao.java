/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;

/**
 * 厂级下发BOM的批次DAO接口
 * @author zhouqizhi
 * @version 2017-11-30
 */
@MyBatisRepository
public interface MesPlanDispatchBomDao extends MesCrudDao<MesPlanDispatchBom> {
	//public void deleteById(String id);
}