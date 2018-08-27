package com.bmsmart.mes.plan.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.export.PlanMonthGetProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.github.pagehelper.PageInfo;

public class PlanMonthGetProviderImpl implements PlanMonthGetProvider {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MesPlanMonthService mesPlanMonthService;
	@Override
	public List<MesPlanMonth> getErpMonthPlanList(MesPlanMonth paraPlan) {
		return mesPlanMonthService.findList(paraPlan);
	}
	@Override
	public MesPlanMonth getErpMonthPlanByProductTask(MesPlanMonth paraPlan) {
		PageInfo<MesPlanMonth> mesPlanMonthList =  mesPlanMonthService.findPage(1, 1, paraPlan);
		if (mesPlanMonthList.getList() != null && mesPlanMonthList.getList().size()>0){
			return mesPlanMonthList.getList().get(0);
		}else{
			return null;
		}
	}
	@Override
	public List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan) {
		return mesPlanMonthService.getErpProductTaskList(paraPlan);
	}
	@Override
	public PageInfo<MesPlanMonth> findPage(MesPlanMonth mesPlanMonth, int pageno, int pagesize) {
		return mesPlanMonthService.findPage(pageno, pagesize, mesPlanMonth);
	}
	
	@Override
	public PlanResponse<MesPlanMonth> getErpMonthPlanByProductTask(PlanRequest<MesPlanMonth> planRequest) {
		try{
			MesPlanMonth responseObj = getErpMonthPlanByProductTask(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getErpMonthPlanByProductTask出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}
	
	@Override
	public PlanResponse<List<MesPlanMonth>> getErpProductTaskList(PlanRequest<MesPlanMonth> planRequest) {
		try{
			List<MesPlanMonth> responseObj = getErpProductTaskList(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getErpProductTaskList出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}
	
	@Override
	public PlanResponse<PageInfo<MesPlanMonth>> findPage(PlanRequest<MesPlanMonth> planRequest, int pageno,
			int pagesize) {
		try{
			PageInfo<MesPlanMonth> responseObj = findPage(planRequest.getRequestObj(),pageno,pagesize);
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getErpProductTaskList出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}

}
