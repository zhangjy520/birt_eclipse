/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.bmsmart.mes.mesJob.domain.po.JobProcess;
import com.bmsmart.mes.mesJob.domain.po.JobTemplateManage;
import com.bmsmart.mes.mesJob.export.process.JobProcessServiceProvider;
import com.bmsmart.mes.mesJob.export.template.JobTemplateManageServiceProvider;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;
import com.bmsmart.mes.plan.dao.MesPlanMonthDao;
import com.bmsmart.mes.plan.service.MesPlanBomService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanTechnicService;
/**
 * mes月计划查询Service
 * @author 周奇志
 * @version 2017-07-31
 */
 @Transactional(readOnly = true)
@Service("mesPlanMonthService")
public class MesPlanMonthServiceImpl extends MesCrudService<MesPlanMonthDao, MesPlanMonth> implements MesPlanMonthService{
  @Autowired
  private MesPlanMonthDao mesPlanMonthDao;
  @Autowired
  private JobTemplateManageServiceProvider jobTemplateManageServiceProvider;
  @Autowired
  private JobProcessServiceProvider jobProcessServiceProvider;
  
  @Autowired
	private MesPlanTechnicService mesPlanTechnicService ;
  
  @Autowired
	private MesPlanBomService mesPlanBomService ;
  
  
	public MesPlanMonth get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanMonth> findList(MesPlanMonth mesPlanMonth) {
		return super.findList(mesPlanMonth);
	}
	
	
	public  PageInfo<MesPlanMonth> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanMonth mesPlanMonth) {
		super.save(mesPlanMonth);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanMonth mesPlanMonth) {
		super.delete(mesPlanMonth);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanMonthDao.deleteById(id);
	}
	
	public MesPlanMonthDao getMesPlanMonthDao() {
	   return mesPlanMonthDao;
    }
    public void setMesPlanMonthDao(MesPlanMonthDao mesPlanMonthDao) {
	    this.mesPlanMonthDao = mesPlanMonthDao;
    }

	@Override
	public PageInfo<MesPlanMonth> findPage(int pageNum, int pageSize, MesPlanMonth mesPlanMonth) {
		 PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
			List<MesPlanMonth> demoTestList=dao.findList(mesPlanMonth);
			 PageInfo<MesPlanMonth> page = new PageInfo<MesPlanMonth>(demoTestList);
			return page;
	}

	@Override
	public MesPlanMonth getByUk(MesPlanMonth planPara) {
		return dao.getByUk(planPara);
//		PageInfo<MesPlanMonth> page =findPage(1,1,planPara);
//		if (page != null && page.getList() != null && page.getList().size()>0){
//			return page.getList().get(0);
//		}else{
//			return null;
//		}
	}

	@Override
	public Integer updateDispatchQty(MesPlanMonth mesPlanMonth) {
		return mesPlanMonthDao.updateDispatchQty(mesPlanMonth);
	}

	@Override
	public Integer updateSheetStatusById(MesPlanMonth mesPlanMonth) {
		 
		return mesPlanMonthDao.updateSheetStatusById(mesPlanMonth);
	}

	@Override
	public Integer updateSheetStatusByErpBillNo(MesPlanMonth mesPlanMonth) {
	 
		return mesPlanMonthDao.updateSheetStatusByErpBillNo(mesPlanMonth);
	}

	@Override
	public List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan) {
		// TODO Auto-generated method stub
		return mesPlanMonthDao.getErpProductTaskList(paraPlan);
	}
	
	@Override
	public List<Map<String, Object>> getSubProcessList(MesPlanTechnic mesPlanTechnic) {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		JobProcess jobProcess = new JobProcess();
		 jobProcess.setParentProcess(mesPlanTechnic.getTechnicId());
		 List<JobProcess> processList = jobProcessServiceProvider.findList(jobProcess);
		 JobTemplateManage jobTemplateManage = new JobTemplateManage();
		   for (JobProcess process : processList) {
			   HashMap<String,Object> map = new HashMap<>();
		      String processCode = process.getProcessCode();
		      jobTemplateManage.setProcessCode(processCode);
		      //根据工序查工种
		      List<JobTemplateManage> templateList = jobTemplateManageServiceProvider.findList(jobTemplateManage);
		      map.put("templateList",templateList);
		      map.put("process",process);
		      resList.add(map);
		   }
		   return resList;
	}

	@Override
	public MesPlanMonth getWholeErpBillNo(String id) {
		MesPlanMonth mesPlanMonth = get(id);
		if (mesPlanMonth != null ){
			List<MesPlanTechnic> mesPlanTechnicList = getMesPlanTechnicList(mesPlanMonth);
			mesPlanMonth.setMesPlanTechnicList(mesPlanTechnicList);
			//BOM
			MesPlanBom mesPlanBom = new MesPlanBom();
			mesPlanBom.setErpBillNo(mesPlanMonth.getErpBillNo());
			List<MesPlanBom> mesPlanBomList = mesPlanBomService.findList(mesPlanBom);
			mesPlanMonth.setMesPlanBomList(mesPlanBomList);
			
		}
		return mesPlanMonth;
	}
	@Override
	public List<MesPlanTechnic> getMesPlanTechnicList(MesPlanMonth mesPlanMonth) {
		MesPlanTechnic mesPlanTechnic = new MesPlanTechnic();
		mesPlanTechnic.setErpBillNo(mesPlanMonth.getErpBillNo());
		//工序路线及子工序路线
		List<MesPlanTechnic> mesPlanTechnicList= mesPlanTechnicService.findList(mesPlanTechnic);
		if (mesPlanTechnicList != null){
			for(MesPlanTechnic tmpTechnic :mesPlanTechnicList){
				List<Map<String, Object>> subTechnicList = getSubProcessList(tmpTechnic);
				tmpTechnic.setSubTechnicList(subTechnicList);
			}
		}
		return mesPlanTechnicList;
	}

	@Override
	public List<JobProcess> getMesPlanTechnicWithoutWorktypeList(MesPlanMonth mesPlanMonth) {
		List<JobProcess> rtnSubJobProcess = new ArrayList<JobProcess>();
		MesPlanTechnic mesPlanTechnic = new MesPlanTechnic();
		mesPlanTechnic.setErpBillNo(mesPlanMonth.getErpBillNo());
		//工序路线及子工序路线
		List<MesPlanTechnic> mesPlanTechnicList= mesPlanTechnicService.findList(mesPlanTechnic);
		if (mesPlanTechnicList != null){
			for(MesPlanTechnic tmpTechnic :mesPlanTechnicList){
				List<JobProcess> subTechnicList = getSubProcessListWithoutWorktype(tmpTechnic);
				rtnSubJobProcess.addAll(subTechnicList);
			}
		}
		return rtnSubJobProcess;
	}
	
	
	public List<JobProcess> getSubProcessListWithoutWorktype(MesPlanTechnic mesPlanTechnic) {
		JobProcess jobProcess = new JobProcess();
		 jobProcess.setParentProcess(mesPlanTechnic.getTechnicId());
		 List<JobProcess> processList = jobProcessServiceProvider.findList(jobProcess);
		 return processList;
	}

	@Override
	public List<JobProcess> getArrangeTechnic() {
		List<JobProcess> rtnSubJobProcess = new ArrayList<JobProcess>();
		//工序路线及子工序路线
		List<MesPlanTechnic> mesPlanTechnicList= new ArrayList<MesPlanTechnic>();
		MesPlanTechnic technic = new MesPlanTechnic();
		technic.setTechnicId("13");//整理
		mesPlanTechnicList.add(technic);
		
		MesPlanTechnic technic2 = new MesPlanTechnic();
		technic2.setTechnicId("16");//打包
		mesPlanTechnicList.add(technic2);
		
		
		if (mesPlanTechnicList != null){
			for(MesPlanTechnic tmpTechnic :mesPlanTechnicList){
				List<JobProcess> subTechnicList = getSubProcessListWithoutWorktype(tmpTechnic);
				rtnSubJobProcess.addAll(subTechnicList);
			}
		}
		return rtnSubJobProcess;
	}
	
}