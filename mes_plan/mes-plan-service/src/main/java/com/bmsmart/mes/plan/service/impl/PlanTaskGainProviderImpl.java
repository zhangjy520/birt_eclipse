package com.bmsmart.mes.plan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.plan.dao.MesPlanShiftUserDao;
import com.bmsmart.mes.plan.dao.MesPlanTempShiftUserDao;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.export.PlanTaskGainProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;

public class PlanTaskGainProviderImpl implements PlanTaskGainProvider{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MesPlanShiftUserDao mesPlanShiftUserDao;
	@Autowired
	MesPlanTempShiftUserDao mesPlanTempShiftUserDao;
	@Override
	public int updateTaskStatus(MesPlanShiftUser mesPlanShiftUser) {
//		if (StringUtils.isEmpty(mesPlanShiftUser.getTaskUser())){
//			throw new RuntimeException("报工人不能为空");
//		}
		 return mesPlanShiftUserDao.updateTaskStatus(mesPlanShiftUser);
	}
	@Override
	public PlanResponse<Integer> updateTaskStatus(PlanRequest<MesPlanShiftUser> planRequest) {
		try{
			Integer responseObj = updateTaskStatus(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), 0);
		}
	}
	@Override
	public PlanResponse<Integer> updateSceneTaskStatus(PlanRequest<MesPlanTempShiftUser> planRequest) {
		try{
			Integer responseObj = mesPlanTempShiftUserDao.updateTaskStatus(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), 0);
		}
	}
}
