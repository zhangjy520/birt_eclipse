/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;
import com.bmsmart.mes.plan.export.PlanTaskGainProvider;
import com.bmsmart.mes.plan.dao.MesPlanShiftUserDao;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanShiftUserService;
import com.bmsmart.mes.plan.service.MesPlanTechnicService;
/**
 * mes_plan_shift_userService
 * @author zhouqz
 * @version 2017-09-04
 */
 @Transactional(readOnly = true)
@Service("mesPlanShiftUserService")
public class MesPlanShiftUserServiceImpl extends MesCrudService<MesPlanShiftUserDao, MesPlanShiftUser> implements MesPlanShiftUserService{
  @Autowired
 private MesPlanShiftUserDao mesPlanShiftUserDao;
  @Autowired
  private MesPlanTechnicService mesPlanTechnicService;
  @Autowired
	private JobTemplateManageServiceProvider jobTemplateManageServiceProvider;
	@Autowired
	private JobProcessServiceProvider jobProcessServiceProvider;
  @Autowired
 private MesPlanDispatchDetService mesPlanDispatchDetService;
	public MesPlanShiftUser get(String id) {
		return super.get(id);
	}
	
	public List<MesPlanShiftUser> findList(MesPlanShiftUser mesPlanShiftUser) {
		return super.findList(mesPlanShiftUser);
	}
	
	
	public  PageInfo<MesPlanShiftUser> findPage(int pageNum, int pageSize,
			Map<String, String> datas){
		return super.findPage(pageNum,pageSize, datas);
	}
	@Transactional(readOnly = false)
	public void save(MesPlanShiftUser mesPlanShiftUser) {
		super.save(mesPlanShiftUser);
	}
	@Transactional(readOnly = false)
	public void delete(MesPlanShiftUser mesPlanShiftUser) {
		super.delete(mesPlanShiftUser);
	}
	@Transactional(readOnly = false)
	public void deleteById(String id){
	   mesPlanShiftUserDao.deleteById(id);
	}
	
	public MesPlanShiftUserDao getMesPlanShiftUserDao() {
	   return mesPlanShiftUserDao;
    }
    public void setMesPlanShiftUserDao(MesPlanShiftUserDao mesPlanShiftUserDao) {
	    this.mesPlanShiftUserDao = mesPlanShiftUserDao;
    }

	@Override
	public List<MesPlanShiftUser> getEmptyShiftUserList(String workshopId,MesPlanDevice mesPlanDevice,MesPlanShiftDet mesPlanShiftDet) {
		List<MesPlanShiftUser> userList = new ArrayList<MesPlanShiftUser>();
		//if (!MesPlanDevice.WORKSHOP_ARRANGE.equals(workshopId)){
			JobProcess jobProcess = new JobProcess();
			List<JobProcess> processList = null;
			
			if (!MesPlanDevice.WORKSHOP_ARRANGE.equals(workshopId)){
				//jobProcess.setParentProcess("P01");
				processList = new ArrayList<JobProcess>();
				MesPlanTechnic mesPlanTechnic = new MesPlanTechnic();
				mesPlanTechnic.setErpBillNo(mesPlanDevice.getErpBillNo());
				//工序路线及子工序路线
				List<MesPlanTechnic> mesPlanTechnicList= mesPlanTechnicService.findList(mesPlanTechnic);
				if (mesPlanTechnicList != null){
					for(MesPlanTechnic tmpTechnic :mesPlanTechnicList){
						jobProcess.setParentProcess(tmpTechnic.getTechnicId());
						List<JobProcess> subprocessList = jobProcessServiceProvider.findList(jobProcess);
						for (JobProcess jb:subprocessList){
							 //if (!mesPlanDevice.getNoTechnicList().contains(jb.getProcessCode())){}无此功能
							//换产品的工序路线不生成上穿轴(JP19)
							if (jb.getProcessCode().equals("JP19")){
								if (mesPlanDevice.getIsChgGoods().equals("1")){
									processList.add(jb);
								}
							}else{
								processList.add(jb);
							}
							
						}
					}
				}
			}else{
				MesPlanDispatchDet dispatchDet = mesPlanDispatchDetService.getByBillNo(mesPlanDevice.getSrcBillNo());
				jobProcess.setProcessCode(dispatchDet.getArrangeGroup());
				processList = jobProcessServiceProvider.findList(jobProcess);
			}
			
	         JobTemplateManage jobTemplateManage = new JobTemplateManage();
	           for (JobProcess process : processList) {
	              String processCode = process.getProcessCode();
	              jobTemplateManage.setProcessCode(processCode);
	              //根据工序查工种
	              List<JobTemplateManage> templateList = jobTemplateManageServiceProvider.findList(jobTemplateManage);
	              if (templateList != null){
	            	  for (JobTemplateManage tempManage :templateList){
		            		MesPlanShiftUser  shiftUser = new MesPlanShiftUser();
		      	      		shiftUser.setSrcBillNo(mesPlanShiftDet.getBillNo());
		      	      		shiftUser.setTechnicUser("");
		      	      		shiftUser.setTechnicId(process.getProcessCode());
		      	      		shiftUser.setTechnicName(process.getProcessName());
		      	      		shiftUser.setWorktypeId(tempManage.getWorkTypeCode());
		      	      		
		      	      		shiftUser.setPtechnicId(process.getParentProcess());
		      	      		//shiftUser.setPTechnicName(process.);
		      	      		//shiftUser.setDeviceSubId("111子设备");
		      	      		//shiftUser.setQty(mesPlanShiftDet.getShiftQty());
		      	      		userList.add(shiftUser);
	            	  }
	            	  
	              }
	           }
//		}else{
//			MesPlanDispatchDet dispatchDet = mesPlanDispatchDetService.getByBillNo(mesPlanDevice.getSrcBillNo());
//			
//			MesPlanShiftUser  shiftUser = new MesPlanShiftUser();
//			shiftUser.setSrcBillNo(mesPlanShiftDet.getBillNo());
//			shiftUser.setTechnicUser("");
//			shiftUser.setTechnicId(dispatchDet.getArrangeGroup());
//			
//			
//			shiftUser.setWorktypeId("整理工种");
//			
//			
//			shiftUser.setTechnicBeginDate(dispatchDet.getShiftBeginTime());
//			shiftUser.setTechnicEndDate(dispatchDet.getShiftEndTime());
//			//shiftUser.setDeviceSubId("411子设备");
//			//shiftUser.setQty(mesPlanShiftDet.getShiftQty());
//			userList.add(shiftUser);
//		}
		
		return userList;
	}

	@Override
	public List<MesPlanShiftUser> getUserTaskSimpleList(MesPlanShiftUser mesPlanShiftUser) {
		return mesPlanShiftUserDao.getUserTaskList(mesPlanShiftUser);
	}

}