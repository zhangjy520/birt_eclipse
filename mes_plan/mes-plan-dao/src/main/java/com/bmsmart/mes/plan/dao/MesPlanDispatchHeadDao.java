/**
 * Copyright &copy; 2017-2018 <a href="">Bmsmart</a> All rights reserved.
 */
package com.bmsmart.mes.plan.dao;


import com.bmsmart.mes.commons.persistence.CrudDao;
import com.bmsmart.mes.commons.persistence.MesCrudDao;
import com.bmsmart.mes.plan.dao.mybatis.MyBatisRepository;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;

/**
 * 厂级调度单头DAO接口
 * @author 周奇志
 * @version 2017-08-07
 */
@MyBatisRepository
public interface MesPlanDispatchHeadDao extends MesCrudDao<MesPlanDispatchHead> {
	//public void deleteById(String id);
	public Integer updateDispatchQty(MesPlanDispatchHead mesPlanDispatchHead);
	public Double findErpSheetQtyTotal(String erpBillNo);
	
	public int updateSheetStatusById(MesPlanDispatchHead mesPlanDispatchHead);
	public int updateSheetStatusBySheetId(MesPlanDispatchHead mesPlanDispatchHead);
}