package com.bmsmart.mes.plan.export;

import java.util.List;

import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;

public interface PlanMaterialUseProvider {
	List<MesPlanMaterialUse> getPlanMaterialUse(MesPlanMaterialUse mesPlanMaterialUse);
	
	
	PlanResponse<List<MesPlanMaterialUse>> getPlanMaterialUse(PlanRequest<MesPlanMaterialUse> planRequest);
}
