/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.CrudDao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;

/**
 * mes_plan_material_useDAO接口
 * @author zhouqizhi
 * @version 2017-09-11
 */
@MyBatisRepository
public interface MesPlanMaterialUseDao extends MesCrudDao<MesPlanMaterialUse> {
	//public void deleteById(String id);
	public int updateUseQtyByUk(MesPlanMaterialUse mesPlanMaterialUse);
	//public int updateUseQty(MesPlanMaterialUse mesPlanMaterialUse);
	
}