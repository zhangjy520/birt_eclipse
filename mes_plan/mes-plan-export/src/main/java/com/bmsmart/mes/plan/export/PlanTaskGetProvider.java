package com.bmsmart.mes.plan.export;

import java.util.List;
import java.util.Map;

import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.github.pagehelper.PageInfo;

public interface PlanTaskGetProvider {
	/**
	 * 查询任务列表
	 * @param mesPlanShiftUser 按条件查询任务,不分页
	 * @return
	 */
	public List<Map<String,Object>> getUserTask(MesPlanShiftUser mesPlanShiftUser);
	/**
	 *  按条件查询任务,分页
	 * @param pageNum
	 * @param pageSize
	 * @param mesPlanTempTask
	 * @return
	 */
	public PageInfo<Map<String,Object>> getUserTask(int pageNum,int pageSize,MesPlanShiftUser mesPlanShiftUser);
	
	
	public PlanResponse<List<Map<String,Object>>> getUserTask(PlanRequest<MesPlanShiftUser> planRequest);
	public PlanResponse<PageInfo<Map<String,Object>>> getUserTask(int pageNum, int pageSize,PlanRequest<MesPlanShiftUser> planRequest);
	
	public PlanResponse<PageInfo<Map<String,Object>>> getSceneUserTask(int pageNum, int pageSize,PlanRequest<MesPlanTempShiftUser> planRequest);
	
}
