package com.bmsmart.mes.plan.export;

import java.util.List;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.github.pagehelper.PageInfo;
/**
 * 临时任务接口
 * @author zhouqz
 *
 */
public interface MesPlanTempTaskProvider {
	/**
	 * 分页查找临时任务
	 * @param pageNum 页号
	 * @param pageSize 每页大小
	 * @param mesPlanTempTask 
	 * @return
	 */
	public PageInfo<MesPlanTempTask> findPage(int pageNum, int pageSize, MesPlanTempTask mesPlanTempTask);
	/**
	 * 查找临时任务不分页
	 * @param mesPlanTempTask
	 * @return
	 */
	public List<MesPlanTempTask> getTmpTask(MesPlanTempTask mesPlanTempTask);
	/**
	 * 修改任务状态 
	 * @param mesPlanTempTask
	 * @return
	 */
	public int updateUseTaskStatus(MesPlanTempTask mesPlanTempTask);
	/**
	 * 任务接收人完成任务
	 * @param mesPlanTempTask
	 * @return
	 */
	public int updateFinishTaskStatus(MesPlanTempTask mesPlanTempTask);
	
	public PlanResponse<PageInfo<MesPlanTempTask>> findPage(int pageNum, int pageSize, PlanRequest<MesPlanTempTask> planRequest);
	public PlanResponse<List<MesPlanTempTask>> getTmpTask(PlanRequest<MesPlanTempTask> planRequest);
	public PlanResponse<Integer> updateUseTaskStatus(PlanRequest<MesPlanTempTask> planRequest);
	public PlanResponse<Integer> updateFinishTaskStatus(PlanRequest<MesPlanTempTask> planRequest);//  MesPlanTempTask mesPlanTempTask
}
