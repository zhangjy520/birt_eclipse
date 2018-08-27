package com.bmsmart.mes.plan.export;

import java.util.List;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.github.pagehelper.PageInfo;

public interface PlanMonthGetProvider {
	List<MesPlanMonth>  getErpMonthPlanList(MesPlanMonth paraPlan);
	/**
	 * 根据生产任务书编号返回商品ID，生产任务书编号为erpProductTaskNo
	 * @param paraPlan
	 * @return
	 */
	MesPlanMonth  getErpMonthPlanByProductTask(MesPlanMonth paraPlan);
	List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan);
	PageInfo<MesPlanMonth> findPage(MesPlanMonth mesPlanMonth,int pageno,int  pagesize);
	
	public PlanResponse<MesPlanMonth> getErpMonthPlanByProductTask(PlanRequest<MesPlanMonth> planRequest);
	public PlanResponse<List<MesPlanMonth>> getErpProductTaskList(PlanRequest<MesPlanMonth> planRequest);
	public PlanResponse<PageInfo<MesPlanMonth>> findPage(PlanRequest<MesPlanMonth> planRequest,int pageno,int  pagesize);
	
	
}
