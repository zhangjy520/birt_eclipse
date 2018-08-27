/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;

import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;

/**
 * 物料清单DAO接口
 * @author zhouqizhi
 * @version 2017-11-22
 */
@MyBatisRepository
public interface MesPlanBomDao extends MesCrudDao<MesPlanBom> {
	//public void deleteById(String id);
}