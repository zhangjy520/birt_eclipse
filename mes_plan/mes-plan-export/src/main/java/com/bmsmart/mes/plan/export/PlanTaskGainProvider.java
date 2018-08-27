package com.bmsmart.mes.plan.export;

import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;

public interface PlanTaskGainProvider {
	/**
	 * 领任务
	 * @param mesPlanShiftUser
	 * @return
	 */
	public int updateTaskStatus(MesPlanShiftUser mesPlanShiftUser);
	
	public PlanResponse<Integer> updateTaskStatus(PlanRequest<MesPlanShiftUser> planRequest);
	
	
	public PlanResponse<Integer> updateSceneTaskStatus(PlanRequest<MesPlanTempShiftUser> planRequest);
	
}
