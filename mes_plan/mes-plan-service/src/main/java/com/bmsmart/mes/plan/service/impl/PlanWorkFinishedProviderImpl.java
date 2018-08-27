package com.bmsmart.mes.plan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.plan.dao.MesPlanDeviceDao;
import com.bmsmart.mes.plan.dao.MesPlanDispatchDetDao;
import com.bmsmart.mes.plan.dao.MesPlanMonthDao;
import com.bmsmart.mes.plan.dao.MesPlanShiftDetDao;
import com.bmsmart.mes.plan.dao.MesPlanShiftUserDao;
import com.bmsmart.mes.plan.dao.MesPlanTempTaskDetDao;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.export.PlanWorkFinishedProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;

public class PlanWorkFinishedProviderImpl implements PlanWorkFinishedProvider{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	  @Autowired
	  private MesPlanShiftDetDao mesPlanShiftDetDao;
	  @Autowired
	  private MesPlanDeviceDao mesPlanDeviceDao;
	  @Autowired
	  private MesPlanDispatchDetDao mesPlanDispatchDetDao;
	  @Autowired
	  private MesPlanMonthDao mesPlanMonthDao;
	  @Autowired
	  private MesPlanTempTaskDetDao mesPlanTempTaskDetDao;
//	@Override
//	public int updateFinishQtyById(MesPlanShiftDet mesPlanShiftDet) {
//		return mesPlanShiftDetDao.updateFinishQtyById(mesPlanShiftDet);
//	}

//		@Override
//		public PlanResponse<Integer> updateFinishQtyById(PlanRequest<MesPlanShiftDet> planRequest) {
//			try{
//				Integer responseObj = updateFinishQtyById(planRequest.getRequestObj());
//				return PlanResponse.getInstance(true, "成功", responseObj);
//			}catch (Exception ex){
//				logger.error("调用updateFinishQtyById出错", ex);
//				return PlanResponse.getInstance(false, ex.getMessage(), 0);
//			}
//		}
	@Override
	public Integer updateFinishQtyByBillNo(MesPlanShiftDet mesPlanShiftDet) {
//		if (StringUtils.isEmpty(mesPlanShiftDet.getTaskUser())){
//			throw new RuntimeException("报工人不能为空");
//		}
		
		MesPlanShiftDet shiftDetRow= mesPlanShiftDetDao.getByBillNo(mesPlanShiftDet.getBillNo());
		//厂级订单
		MesPlanMonth mesPlanMonth = new MesPlanMonth();
		mesPlanMonth.setErpBillNo(shiftDetRow.getErpBillNo());
		
		//车间调度
		//先查询车间调度得到单号
		MesPlanDevice mesPlanDevice = mesPlanDeviceDao.getByUk(shiftDetRow.getSrcBillNo());
		mesPlanDevice.setFinishQty(mesPlanShiftDet.getFinishQty());
		//厂级调度
		MesPlanDispatchDet mesPlanDispatchDet = new MesPlanDispatchDet();
		mesPlanDispatchDet.setBillNo(mesPlanDevice.getSrcBillNo());
		mesPlanDispatchDet.setFinishQty(mesPlanShiftDet.getFinishQty());
		
		MesPlanDispatchDet rtnDet= mesPlanDispatchDetDao.getByBillNo(mesPlanDevice.getSrcBillNo());
		if (StringUtils.isNotEmpty(rtnDet.getArrangeGroup()) && !MesPlanDispatchDet.LASTARRANGEGROUP.equals(rtnDet.getArrangeGroup())&& !MesPlanDispatchDet.LASTARRANGEGROUP2.equals(rtnDet.getArrangeGroup())){
			mesPlanMonth.setFinishQty(0D);
		}else{
			mesPlanMonth.setFinishQty(mesPlanShiftDet.getFinishQty());
		}
		//需要判断整理车间是否需要报工到订单
		
		Integer uptCtn = mesPlanMonthDao.updateFinishQtyByBillNo(mesPlanMonth);
		
		if (uptCtn == 0){
			throw new RuntimeException(String.format("生产订单报工出错,单号=%1$s,数量=%2$s", mesPlanMonth.getErpBillNo(),mesPlanMonth.getFinishQty()));
		}
		uptCtn = mesPlanDispatchDetDao.updateFinishQtyByBillNo(mesPlanDispatchDet);
		if (uptCtn == 0){
			throw new RuntimeException(String.format("厂级调度报工出错,单号=%1$s,数量=%2$s", mesPlanDispatchDet.getBillNo(),mesPlanDispatchDet.getFinishQty()));
		}
		uptCtn = mesPlanDeviceDao.updateFinishQtyByBillNo(mesPlanDevice);
		if (uptCtn == 0){
			throw new RuntimeException(String.format("车间调度报工出错,单号=%1$s,数量=%2$s", mesPlanDevice.getBillNo(),mesPlanDevice.getFinishQty()));
		}
		uptCtn= mesPlanShiftDetDao.updateFinishQtyByBillNo(mesPlanShiftDet);
		if (uptCtn == 0){
			throw new RuntimeException(String.format("排班报工出错,单号=%1$s,数量=%2$s", mesPlanShiftDet.getBillNo(),mesPlanShiftDet.getFinishQty()));
		}
		 return uptCtn;
	}

	@Override
	public PlanResponse<Integer> updateFinishQtyByBillNo(PlanRequest<MesPlanShiftDet> planRequest) {
		try{
			Integer responseObj = updateFinishQtyByBillNo(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用updateFinishQtyByBillNo出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), 0);
		}
	}

	@Override
	public PlanResponse<Integer> updateSceneFinishQtyByBillNo(PlanRequest<MesPlanTempTaskDet> planRequest) {
		Integer responseObj =  mesPlanTempTaskDetDao.updateFinishQtyByBillNo(planRequest.getRequestObj());
		return PlanResponse.getInstance(true, "成功", responseObj);
	}
}
