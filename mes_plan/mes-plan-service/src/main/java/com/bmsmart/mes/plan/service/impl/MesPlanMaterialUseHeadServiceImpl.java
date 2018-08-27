/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseHead;
import com.bmsmart.mes.plan.dao.MesPlanMaterialUseHeadDao;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseDetService;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseHeadService;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseService;
/**
 * mes_plan_material_use_headService
 * @author zhouqizhi
 * @version 2017-09-11
 */
 @Transactional(readOnly = true)
@Service("mesPlanMaterialUseHeadService")
public class MesPlanMaterialUseHeadServiceImpl extends MesCrudService<MesPlanMaterialUseHeadDao, MesPlanMaterialUseHead> implements MesPlanMaterialUseHeadService{
  @Autowired
 private MesPlanMaterialUseHeadDao mesPlanMaterialUseHeadDao;
  @Autowired
  private MesPlanMaterialUseService mesPlanMaterialUseService;
  
  @Autowired
  private MesPlanMaterialUseDetService mesPlanMaterialUseDetService;
  
  
	public MesPlanMaterialUseHead get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanMaterialUseHead> findList(MesPlanMaterialUseHead mesPlanMaterialUseHead) {
		return super.findList(mesPlanMaterialUseHead);
	}
	
	
	public  PageInfo<MesPlanMaterialUseHead> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanMaterialUseHead mesPlanMaterialUseHead) {
		super.save(mesPlanMaterialUseHead);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanMaterialUseHead mesPlanMaterialUseHead) {
		super.delete(mesPlanMaterialUseHead);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanMaterialUseHeadDao.deleteById(id);
	}
	
	public MesPlanMaterialUseHeadDao getMesPlanMaterialUseHeadDao() {
	   return mesPlanMaterialUseHeadDao;
    }
    public void setMesPlanMaterialUseHeadDao(MesPlanMaterialUseHeadDao mesPlanMaterialUseHeadDao) {
	    this.mesPlanMaterialUseHeadDao = mesPlanMaterialUseHeadDao;
    }
    public synchronized String getNewSheetId(){
		Random r = new Random();
		return "US"+r.nextInt(1000)+"-"+System.currentTimeMillis();
	}

	@Override
	public String saveSheet(MesPlanMaterialUseHead mesPlanMaterialUseHead, List<MesPlanMaterialUseDet> mesPlanMaterialUseDetList) {
		if (StringUtils.isBlank(mesPlanMaterialUseHead.getSheetId())){
			mesPlanMaterialUseHead.setSheetId(getNewSheetId());
			super.save(mesPlanMaterialUseHead);
		}
		String sheetid = mesPlanMaterialUseHead.getSheetId();
		for (MesPlanMaterialUseDet det:mesPlanMaterialUseDetList){
			if (StringUtils.isBlank(det.getSheetId())){
				det.setSheetId(sheetid);
			}else if (!sheetid.equals(det.getSheetId())){
				throw new RuntimeException("单号sheetid不一致");
			}
			//更改已领用数量同量，检查 数量是否超标
			MesPlanMaterialUse use = new MesPlanMaterialUse();
			use.setUseQty(det.getUseQty()==null?0:det.getUseQty());
			use.setScheduleDate(det.getScheduleDate());
			use.setWorkshopId(mesPlanMaterialUseHead.getWorkshopId());
			use.setGoodsId(det.getGoodsId());
//			if (mesPlanMaterialUseService.updateUseQty(use)==0){
//				//throw new RuntimeException("领用数量超过排程可领用数量");
//				use.setQty(0F);
//				use.setRemarks("根据出货生成");
//				mesPlanMaterialUseService.save(use);
//			}
			mesPlanMaterialUseDetService.save(det);
		}
		
		return mesPlanMaterialUseHead.getId();
	}

	@Override
	public int updateSheetStatus(MesPlanMaterialUseHead mesPlanMaterialUseHead) {
		// TODO Auto-generated method stub
		return  mesPlanMaterialUseHeadDao.updateSheetStatus(mesPlanMaterialUseHead);
	}

	@Override
	public PageInfo<MesPlanMaterialUseHead> findPage(int pageno, int pagesize, MesPlanMaterialUseHead head) {
		PageHelper.startPage(pageno,pagesize);// 使用order e.g.
		List<MesPlanMaterialUseHead> useList=mesPlanMaterialUseHeadDao.findList(head);
		PageInfo<MesPlanMaterialUseHead> page = new PageInfo<MesPlanMaterialUseHead>(useList);
		return page;
	}
}