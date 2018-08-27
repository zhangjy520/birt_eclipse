package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmsmart.mes.commons.service.MesCrudService;
import com.bmsmart.mes.mesJob.domain.po.JobProcess;
import com.bmsmart.mes.mesJob.domain.po.JobTemplateManage;
import com.bmsmart.mes.mesJob.export.process.JobProcessServiceProvider;
import com.bmsmart.mes.mesJob.export.template.JobTemplateManageServiceProvider;
import com.bmsmart.mes.plan.dao.MesPlanTempShiftHeadDao;
import com.bmsmart.mes.plan.dao.MesPlanTempShiftUserDao;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.service.MesPlanTempShiftUserService;
import com.github.pagehelper.PageInfo;
@Service("mesPlanTempShiftUserService")
public class MesPlanTempShiftUserServiceImpl extends MesCrudService<MesPlanTempShiftUserDao, MesPlanTempShiftUser> implements MesPlanTempShiftUserService {
	@Autowired
	MesPlanTempShiftUserDao mesPlanTempShiftUserDao;
	@Autowired
	private JobProcessServiceProvider jobProcessServiceProvider;
	@Autowired
	private JobTemplateManageServiceProvider jobTemplateManageServiceProvider;
	 
	@Override
	public MesPlanTempShiftUser get(String id) {
		return super.get(id);
	}

	@Override
	public List<MesPlanTempShiftUser> findList(MesPlanTempShiftUser mesPlanTempShiftUser) {
		return super.findList(mesPlanTempShiftUser);
	}

	@Override
	public PageInfo<MesPlanTempShiftUser> findPage(int pageNum, int pageSize, Map<String, String> datas) {
		return super.findPage(pageNum,pageSize, datas);
	}

	@Override
	public void save(MesPlanTempShiftUser mesPlanTempShiftUser) {
		super.save(mesPlanTempShiftUser);
	}

	@Override
	public void delete(MesPlanTempShiftUser mesPlanTempShiftUser) {
		super.delete(mesPlanTempShiftUser);
	}

	@Override
	public void deleteById(String id) {
		mesPlanTempShiftUserDao.deleteById(id);
	}

	@Override
	public List<MesPlanTempShiftUser> getUserTaskSimpleList(MesPlanTempShiftUser mesPlanShiftUser) {
		return mesPlanTempShiftUserDao.getUserTaskList(mesPlanShiftUser);
	}

	@Override
	public List<MesPlanTempShiftUser> getEmptyShiftUserList(String workshopId, MesPlanTempTaskDet mesPlanDevice) {
		
		List<MesPlanTempShiftUser> userList = new ArrayList<MesPlanTempShiftUser>();
		
		List<JobProcess> processList = new ArrayList<JobProcess>();
		
		JobProcess jobProcess = new JobProcess();
		jobProcess.setParentProcess(mesPlanDevice.getPtechnicId());
		List<JobProcess> subprocessList = jobProcessServiceProvider.findList(jobProcess);
		for (JobProcess jb:subprocessList){
			processList.add(jb);
		}
			
		JobTemplateManage jobTemplateManage = new JobTemplateManage();
		for (JobProcess process : processList) {
          String processCode = process.getProcessCode();
          jobTemplateManage.setProcessCode(processCode);
          //根据工序查工种
          List<JobTemplateManage> templateList = jobTemplateManageServiceProvider.findList(jobTemplateManage);
          if (templateList != null){
        	  for (JobTemplateManage tempManage :templateList){
            		MesPlanTempShiftUser  shiftUser = new MesPlanTempShiftUser();
      	      		shiftUser.setSrcBillNo(mesPlanDevice.getBillNo());
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
		return userList;
	}
}
