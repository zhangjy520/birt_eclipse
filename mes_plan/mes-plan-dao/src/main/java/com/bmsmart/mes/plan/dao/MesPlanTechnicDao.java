/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;

/**
 * 订单工序DAO接口
 * @author zhouqizhi
 * @version 2017-11-22
 */
@MyBatisRepository
public interface MesPlanTechnicDao extends MesCrudDao<MesPlanTechnic> {
	//public void deleteById(String id);
}