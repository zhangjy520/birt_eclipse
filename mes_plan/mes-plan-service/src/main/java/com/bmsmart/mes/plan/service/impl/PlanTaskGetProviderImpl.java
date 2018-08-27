package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bmsmart.mes.plan.dao.MesPlanTempTaskDao;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.export.PlanTaskGetProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanShiftDetService;
import com.bmsmart.mes.plan.service.MesPlanShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanShiftUserService;
import com.bmsmart.mes.plan.service.MesPlanTempShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanTempShiftUserService;
import com.bmsmart.mes.plan.service.MesPlanTempTaskDetService;
import com.bmsmart.mes.plan.service.MesPlanTempTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PlanTaskGetProviderImpl implements PlanTaskGetProvider{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MesPlanShiftDetService mesPlanShiftDetService;
	@Autowired
	MesPlanDeviceService mesPlanDeviceService;
	@Autowired
	MesPlanMonthService mesPlanMonthService;
	@Autowired
	MesPlanShiftUserService mesPlanShiftUserService;
	@Autowired
	MesPlanShiftHeadService mesPlanShiftHeadService;
	@Autowired
	MesPlanTempShiftUserService mesPlanTempShiftUserService;
	@Autowired
	MesPlanTempShiftHeadService mesPlanTempShiftHeadService;
	@Autowired
	MesPlanTempTaskDetService mesPlanTempTaskDetService;
	
	@Autowired
	MesPlanTempTaskDao mesPlanTempTaskDao;
	
	public Map<String,Object> getUserTaskDetOne(MesPlanShiftUser mesPlanShiftUser){
		
		Map<String,Object> map = new HashMap<String,Object>();
		//MesPlanShiftHead headPara = new MesPlanShiftHead();
		//headPara.setSheetId(mesPlanShiftUser.getSheetId());
		//单头
		MesPlanShiftHead retHead =  mesPlanShiftHeadService.getHeadBySheetId(mesPlanShiftUser.getSheetId());
		//任务
		MesPlanShiftDet mesPlanShiftDet = mesPlanShiftDetService.getbyBillNo(mesPlanShiftUser.getSrcBillNo());
		//根据任务得到机台
		MesPlanDevice mesPlanDevice  = mesPlanDeviceService.getByUk(mesPlanShiftDet.getSrcBillNo());
		//得到月计划
		MesPlanMonth monthPara = new MesPlanMonth();
		monthPara.setErpBillNo(mesPlanDevice.getErpBillNo());
		MesPlanMonth mesPlanMonth =  mesPlanMonthService.getByUk(monthPara);
		
		map.put("task", mesPlanShiftUser);
		map.put("taskDate", retHead);
		map.put("taskQty", mesPlanShiftDet);
		map.put("taskDevice", mesPlanDevice);
		map.put("taskErpGoods", mesPlanMonth);
		return map;
		
	 
	}

 
	public List<Map<String,Object>> getUserTaskDetList(List<MesPlanShiftUser> mesPlanShiftUserList) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		for (MesPlanShiftUser user :mesPlanShiftUserList){
			try{
				Map<String,Object> userTask= getUserTaskDetOne(user);
				rtnList.add(userTask);
			}catch(Exception ex){
				logger.error("批量取任务出错", ex);
			}
		}
		
		return rtnList;
	}

	@Override
	public List<Map<String, Object>> getUserTask(MesPlanShiftUser mesPlanShiftUser) {
		List<MesPlanShiftUser> mesPlanShiftUserList  = mesPlanShiftUserService.getUserTaskSimpleList(mesPlanShiftUser);
		if (mesPlanShiftUserList != null){
			return getUserTaskDetList(mesPlanShiftUserList);
		}else{
			return null;
		}
		
	}


	@Override
	public PageInfo<Map<String, Object>> getUserTask(int pageNum, int pageSize, MesPlanShiftUser mesPlanShiftUser) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanShiftUser> mesPlanShiftUserList  = mesPlanShiftUserService.getUserTaskSimpleList(mesPlanShiftUser);
		
		PageInfo<MesPlanShiftUser> page = new PageInfo<MesPlanShiftUser>(mesPlanShiftUserList);
		
		List<Map<String, Object>>  rtn = null;
		if (mesPlanShiftUserList != null){
			rtn = getUserTaskDetList(mesPlanShiftUserList);
		}else{
			rtn =  new ArrayList<Map<String,Object>>();
		}
		
		PageInfo<Map<String,Object>> page2 = new PageInfo<Map<String,Object>>(rtn);
		page2.setPageNum(page.getPageNum());
		page2.setPageSize(page.getPageSize());
		page2.setSize(rtn.size());
		page2.setStartRow(page.getStartRow());
		page2.setEndRow(page.getEndRow());
		page2.setTotal(page.getTotal());
		page2.setPages(page.getPages());
		page2.setPrePage(page.getPrePage());
		page2.setNextPage(page.getNextPage());
		page2.setIsFirstPage(page.isIsFirstPage());
		page2.setIsLastPage(page.isIsLastPage());
		page2.setHasPreviousPage(page.isHasPreviousPage());
		page2.setHasNextPage(page.isHasNextPage());
		page2.setNavigatePages(page.getNavigatePages());
		page2.setNavigatepageNums(page.getNavigatepageNums());
		page2.setNavigateFirstPage(page.getNavigateFirstPage());
		page2.setNavigateLastPage(page.getNavigateLastPage());
		page2.setLastPage(page.getLastPage());
		page2.setFirstPage(page.getFirstPage());
		 
		return page2;
	}


	@Override
	public PlanResponse<List<Map<String, Object>>> getUserTask(PlanRequest<MesPlanShiftUser> planRequest) {
		try{
			List<Map<String,Object>> responseObj = getUserTask(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateFinishTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(),null);
		}
	}


	@Override
	public PlanResponse<PageInfo<Map<String, Object>>> getUserTask(int pageNum, int pageSize,
			PlanRequest<MesPlanShiftUser> planRequest) {
		try{
			PageInfo<Map<String,Object>> responseObj = getUserTask(pageNum,pageSize,planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateFinishTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(),null);
		}
	}


	@Override
	public PlanResponse<PageInfo<Map<String, Object>>> getSceneUserTask(int pageNum, int pageSize,
			PlanRequest<MesPlanTempShiftUser> planRequest) {
		try{
			PageInfo<Map<String,Object>> responseObj = getSceneUserTask(pageNum,pageSize,planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateFinishTaskStatus出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(),null);
		}
	}


	private PageInfo<Map<String, Object>> getSceneUserTask(int pageNum, int pageSize, MesPlanTempShiftUser mesPlanTempShiftUser) {
		PageHelper.startPage(pageNum,pageSize);// 使用order e.g.
		List<MesPlanTempShiftUser> mesPlanTempShiftUserList  = mesPlanTempShiftUserService.getUserTaskSimpleList(mesPlanTempShiftUser);
		
		PageInfo<MesPlanTempShiftUser> page = new PageInfo<MesPlanTempShiftUser>(mesPlanTempShiftUserList);
		
		List<Map<String, Object>>  rtn = null;
		if (mesPlanTempShiftUserList != null){
			rtn = getSceneUserTaskDetList(mesPlanTempShiftUserList);
		}else{
			rtn =  new ArrayList<Map<String,Object>>();
		}
		
		PageInfo<Map<String,Object>> page2 = new PageInfo<Map<String,Object>>(rtn);
		page2.setPageNum(page.getPageNum());
		page2.setPageSize(page.getPageSize());
		page2.setSize(page.getSize());
		page2.setStartRow(page.getStartRow());
		page2.setEndRow(page.getEndRow());
		page2.setTotal(page.getTotal());
		page2.setPages(page.getPages());
		page2.setPrePage(page.getPrePage());
		page2.setNextPage(page.getNextPage());
		page2.setIsFirstPage(page.isIsFirstPage());
		page2.setIsLastPage(page.isIsLastPage());
		page2.setHasPreviousPage(page.isHasPreviousPage());
		page2.setHasNextPage(page.isHasNextPage());
		page2.setNavigatePages(page.getNavigatePages());
		page2.setNavigatepageNums(page.getNavigatepageNums());
		page2.setNavigateFirstPage(page.getNavigateFirstPage());
		page2.setNavigateLastPage(page.getNavigateLastPage());
		page2.setLastPage(page.getLastPage());
		page2.setFirstPage(page.getFirstPage());
		 
		return page2;
	}


	private List<Map<String, Object>> getSceneUserTaskDetList(List<MesPlanTempShiftUser> mesPlanTempShiftUserList) {
		List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
		for (MesPlanTempShiftUser user :mesPlanTempShiftUserList){
			 Map<String,Object> userTask= getSceneUserTaskDetOne(user);
			 rtnList.add(userTask);
		}
		
		return rtnList;
	}


	private Map<String, Object> getSceneUserTaskDetOne(MesPlanTempShiftUser mesPlanTempShiftUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		//MesPlanShiftHead headPara = new MesPlanShiftHead();
		//headPara.setSheetId(mesPlanShiftUser.getSheetId());
		//单头
		MesPlanTempShiftHead retHead =  mesPlanTempShiftHeadService.getHeadBySheetId(mesPlanTempShiftUser.getSheetId());

		//根据任务得到机台
		MesPlanTempTaskDet mesPlanTempTaskDet  = mesPlanTempTaskDetService.getByBillNo(mesPlanTempShiftUser.getSrcBillNo());
		
		MesPlanTempTask mesPlanTempTask = mesPlanTempTaskDao.getBySheetId(mesPlanTempTaskDet.getSheetId());
		
		//得到月计划
		MesPlanMonth monthPara = new MesPlanMonth();
		monthPara.setErpBillNo(mesPlanTempTask.getPlanSheetId());
		MesPlanMonth mesPlanMonth =  mesPlanMonthService.getByUk(monthPara);
		
		map.put("sceneTask", mesPlanTempShiftUser);
		map.put("sceneTaskHead", retHead);
		map.put("sceneTaskDevice2Qty", mesPlanTempTaskDet);
		map.put("taskErpPlan", mesPlanMonth);
		return map;
	}
}
