package com.bmsmart.mes.plan.export;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;

public interface PlanWorkFinishedProvider {
	/**
	 * 根据id报工
	 * @param mesPlanShiftDet
	 * @return
	 */
	//public int updateFinishQtyById(MesPlanShiftDet mesPlanShiftDet);
	/**
	 * 报工billlno报工
	 * @param mesPlanShiftDet
	 * @return
	 */
	public Integer updateFinishQtyByBillNo(MesPlanShiftDet mesPlanShiftDet);
	
	
	//public PlanResponse<Integer> updateFinishQtyById(PlanRequest<MesPlanShiftDet> planRequest);
	
	public PlanResponse<Integer> updateFinishQtyByBillNo(PlanRequest<MesPlanShiftDet> planRequest);
	
	public PlanResponse<Integer> updateSceneFinishQtyByBillNo(PlanRequest<MesPlanTempTaskDet> planRequest);
}
