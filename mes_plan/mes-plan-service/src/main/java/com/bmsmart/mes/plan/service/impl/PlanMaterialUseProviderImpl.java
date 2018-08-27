package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.export.PlanMaterialUseProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseService;

public class PlanMaterialUseProviderImpl implements PlanMaterialUseProvider {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	MesPlanMaterialUseService mesPlanMaterialUseService;
	@Autowired
	MdmQueryProvider mdmQueryProvider;
	@Override
	public List<MesPlanMaterialUse> getPlanMaterialUse(MesPlanMaterialUse mesPlanMaterialUse) {
		List<MesPlanMaterialUse> mesPlanMaterialUseList = mesPlanMaterialUseService.findList(mesPlanMaterialUse);
//		List<MesPlanMaterialUse> rtnList = new ArrayList<MesPlanMaterialUse>();
//		
//		for (MesPlanMaterialUse use :mesPlanMaterialUseList){
//			MdmQueryRequest request=new MdmQueryRequest();
//			request.setMdmType(MdmType.MATRIAL_INFO);
//			HashMap queryMap = new HashMap();
//			queryMap.put("code", use.getGoodsId());
//			queryMap.put("parentCode", "G00");
//			request.setQueryMap(queryMap);
//			MdmQueryResponse listCode	= mdmQueryProvider.queryList(request);
//			List<Object> o = JSONFormatter.newInstance().toObject(listCode.getResultStr(), List.class);
//			if (o != null && o.size()>0){
//				rtnList.add(use);
//			}
//		}
		return mesPlanMaterialUseList;
	}
	@Override
	public PlanResponse<List<MesPlanMaterialUse>> getPlanMaterialUse(PlanRequest<MesPlanMaterialUse> planRequest) {
		try{
			List<MesPlanMaterialUse> responseObj = getPlanMaterialUse(planRequest.getRequestObj());
			return PlanResponse.getInstance(true, "成功", responseObj);
		}catch (Exception ex){
			logger.error("调用getPlanMaterialUse出错", ex);
			return PlanResponse.getInstance(false, ex.getMessage(), null);
		}
	}
}
