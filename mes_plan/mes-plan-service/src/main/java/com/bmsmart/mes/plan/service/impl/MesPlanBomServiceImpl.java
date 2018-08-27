/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageInfo;

import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.dao.MesPlanBomDao;
import com.bmsmart.mes.plan.service.MesPlanBomService;
/**
 * 物料清单Service
 * @author zhouqizhi
 * @version 2017-11-22
 */
 @Transactional(readOnly = true)
@Service("mesPlanBomService")
public class MesPlanBomServiceImpl extends MesCrudService<MesPlanBomDao, MesPlanBom> implements MesPlanBomService{
  @Autowired
 private MesPlanBomDao mesPlanBomDao;
	public MesPlanBom get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanBom> findList(MesPlanBom mesPlanBom) {
		return super.findList(mesPlanBom);
	}
	
	
	public  PageInfo<MesPlanBom> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanBom mesPlanBom) {
		super.save(mesPlanBom);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanBom mesPlanBom) {
		super.delete(mesPlanBom);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanBomDao.deleteById(id);
	}
	
	public MesPlanBomDao getMesPlanBomDao() {
	   return mesPlanBomDao;
    }
    public void setMesPlanBomDao(MesPlanBomDao mesPlanBomDao) {
	    this.mesPlanBomDao = mesPlanBomDao;
    }
}