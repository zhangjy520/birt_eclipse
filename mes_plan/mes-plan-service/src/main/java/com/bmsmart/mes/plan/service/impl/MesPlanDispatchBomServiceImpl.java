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

import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;
import com.bmsmart.mes.plan.dao.MesPlanDispatchBomDao;
import com.bmsmart.mes.plan.service.MesPlanDispatchBomService;
/**
 * 厂级下发BOM的批次Service
 * @author zhouqizhi
 * @version 2017-11-30
 */
 @Transactional(readOnly = true)
@Service("mesPlanDispatchBomService")
public class MesPlanDispatchBomServiceImpl extends MesCrudService<MesPlanDispatchBomDao, MesPlanDispatchBom> implements MesPlanDispatchBomService{
  @Autowired
 private MesPlanDispatchBomDao mesPlanDispatchBomDao;
	public MesPlanDispatchBom get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanDispatchBom> findList(MesPlanDispatchBom mesPlanDispatchBom) {
		return super.findList(mesPlanDispatchBom);
	}
	
	
	public  PageInfo<MesPlanDispatchBom> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanDispatchBom mesPlanDispatchBom) {
		super.save(mesPlanDispatchBom);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanDispatchBom mesPlanDispatchBom) {
		super.delete(mesPlanDispatchBom);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanDispatchBomDao.deleteById(id);
	}
	
	public MesPlanDispatchBomDao getMesPlanDispatchBomDao() {
	   return mesPlanDispatchBomDao;
    }
    public void setMesPlanDispatchBomDao(MesPlanDispatchBomDao mesPlanDispatchBomDao) {
	    this.mesPlanDispatchBomDao = mesPlanDispatchBomDao;
    }

	@Override
	public void saveBatch(List<MesPlanDispatchBom> mesPlanDispatchBomList) {
		for (MesPlanDispatchBom bom:mesPlanDispatchBomList){
			super.save(bom);
		}
		
	}
}