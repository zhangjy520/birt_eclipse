/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.CrudDao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseDet;

/**
 * mes_plan_material_use_detDAO接口
 * @author zhouqizhi
 * @version 2017-09-11
 */
@MyBatisRepository
public interface MesPlanMaterialUseDetDao extends MesCrudDao<MesPlanMaterialUseDet> {
	//public void deleteById(String id);
}