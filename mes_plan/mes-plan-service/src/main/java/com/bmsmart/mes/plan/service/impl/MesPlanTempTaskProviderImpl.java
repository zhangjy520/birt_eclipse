package com.bmsmart.mes.plan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bmsmart.mes.plan.dao.MesPlanTempTaskDao;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.export.MesPlanTempTaskProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
public class MesPlanTempTaskProviderImpl implements MesPlanTempTaskProvider {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private  MesPlanTempTaskDao mesPlanTempTaskDao;
	
	@Override
	public List<MesPlanTempTask> getTmpTask(MesPlanTempTask mesPlanTempTask) {
		//领任务全是不关联订单的任务,设置标志
		mesPlanTempTask.setRelPlan("0");
		return mesPlanTempTaskDao.findList(mesPlanTempTask);
	}

	@Override
	public int updateUseTaskStatus(MesPlanTempTask mesPlanTempTask) {
		return mesPlanTempTaskDao.updateUseTaskStatus(mesPlanTempTask);
	}

	@Override
	public int updateFinishTaskStatus(MesPlanTempTask mesPlanTempTask) {
		return mesPlanTempTaskDao.updateFinishTaskStatus(mesPlanTempTask);
	}
	
	@Override
	public PageInfo<MesPlanTempTask> findPage(int pageNum, int pageSize, MesPlanTempTask mesPlanTempTask) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanTempTask> demoTestList = mesPlanTempTaskDao.findList(mesPlanTempTask);
		PageInfo<MesPlanTempTask> page = new PageInfo<MesPlanTempTask>(demoTestList);
		return page;
	}

	@Override
	public PlanResponse<PageInfo<MesPlanTempTask>> findPage(int pageNum, int pageSize,PlanRequest<MesPlanTempTask> planRequest) {
		try{
			PageInfo<MesPlanTempTask> responseObj = findPage(pageNum,pageSize,planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}
		catch (Exception ex){
			logger.error("调用findPage出错{},{}", ex,pageNum,pageSize);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

	@Override
	public PlanResponse<List<MesPlanTempTask>> getTmpTask(PlanRequest<MesPlanTempTask> planRequest) {
		try{
			List<MesPlanTempTask> responseObj = getTmpTask(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}
		catch (Exception ex){
			logger.error("调用getTmpTask出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

	@Override
	public PlanResponse<Integer> updateUseTaskStatus(PlanRequest<MesPlanTempTask> planRequest) {
		try{
			Integer responseObj = updateUseTaskStatus(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateUseTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), 0);
		}
	}

	@Override
	public PlanResponse<Integer> updateFinishTaskStatus(PlanRequest<MesPlanTempTask> planRequest) {
		try{
			Integer responseObj = updateFinishTaskStatus(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateFinishTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), 0);
		}
	}

}
