/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.commons.service.MesCrudService;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmType;
import com.bmsmart.mes.mesExternal.export.response.MdmQueryResponse;
import com.bmsmart.mes.mesJob.domain.po.JobProcess;
import com.bmsmart.mes.mesJob.domain.po.JobTemplateManage;
import com.bmsmart.mes.mesJob.export.process.JobProcessServiceProvider;
import com.bmsmart.mes.mesJob.export.template.JobTemplateManageServiceProvider;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;
import com.bmsmart.mes.plan.dao.MesPlanMonthDao;
import com.bmsmart.mes.plan.service.MesPlanBomService;
import com.bmsmart.mes.plan.service.MesPlanCommonService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanTechnicService;

/**
 * mes月计划查询Service
 * 
 * @author 周奇志
 * @version 2017-07-31
 */
@Transactional(readOnly = true)
@Service("mesPlanMonthService")
public class MesPlanMonthServiceImpl extends MesCrudService<MesPlanMonthDao, MesPlanMonth>
		implements MesPlanMonthService {
	private final String PLAN_ORDER_TYPE = "plan_order_type";
	private final String PLAN_ORDER_STATUS = "plan_order_status";
	private final String WORK_SHOP_CODE = "C00";
	private final String PLAN_STATUS = "plan_status";
	private final String BILL_TYPE = "bill_type";
	private final String CONTRACT_ID = "contract_id";//按照合同号分组，总数就是合同数，待修改为：成品订单数量

	@Autowired
	private MesPlanMonthDao mesPlanMonthDao;
	@Autowired
	private JobTemplateManageServiceProvider jobTemplateManageServiceProvider;
	@Autowired
	private JobProcessServiceProvider jobProcessServiceProvider;

	@Autowired
	private MesPlanTechnicService mesPlanTechnicService;

	@Autowired
	private MesPlanBomService mesPlanBomService;

	@Autowired
	private MesPlanCommonService mesPlanCommonService;

	@Autowired
	private MdmQueryProvider mdmQueryProvider;

	public MesPlanMonth get(String id) {
		return super.get(id);
	}

	public List<MesPlanMonth> findList(MesPlanMonth mesPlanMonth) {
		return super.findList(mesPlanMonth);
	}

	public PageInfo<MesPlanMonth> findPage(int pageNum, int pageSize, Map<String, String> datas) {
		return super.findPage(pageNum, pageSize, datas);
	}

	@Transactional(readOnly = false)
	public void save(MesPlanMonth mesPlanMonth) {
		super.save(mesPlanMonth);
	}

	@Transactional(readOnly = false)
	public void delete(MesPlanMonth mesPlanMonth) {
		super.delete(mesPlanMonth);
	}

	@Transactional(readOnly = false)
	public void deleteById(String id) {
		mesPlanMonthDao.deleteById(id);
	}

	public MesPlanMonthDao getMesPlanMonthDao() {
		return mesPlanMonthDao;
	}

	public void setMesPlanMonthDao(MesPlanMonthDao mesPlanMonthDao) {
		this.mesPlanMonthDao = mesPlanMonthDao;
	}

	@Override
	public PageInfo<MesPlanMonth> findPage(int pageNum, int pageSize, MesPlanMonth mesPlanMonth) {
		PageHelper.startPage(pageNum, pageSize);// 使用order e.g.
		List<MesPlanMonth> demoTestList = dao.findList(mesPlanMonth);
		PageInfo<MesPlanMonth> page = new PageInfo<MesPlanMonth>(demoTestList);
		return page;
	}

	@Override
	public MesPlanMonth getByUk(MesPlanMonth planPara) {
		return dao.getByUk(planPara);
		// PageInfo<MesPlanMonth> page =findPage(1,1,planPara);
		// if (page != null && page.getList() != null &&
		// page.getList().size()>0){
		// return page.getList().get(0);
		// }else{
		// return null;
		// }
	}

	@Override
	public Integer updateDispatchQty(MesPlanMonth mesPlanMonth) {
		return mesPlanMonthDao.updateDispatchQty(mesPlanMonth);
	}

	@Override
	public Integer updateSheetStatusById(MesPlanMonth mesPlanMonth) {

		return mesPlanMonthDao.updateSheetStatusById(mesPlanMonth);
	}

	@Override
	public Integer updateSheetStatusByErpBillNo(MesPlanMonth mesPlanMonth) {

		return mesPlanMonthDao.updateSheetStatusByErpBillNo(mesPlanMonth);
	}

	@Override
	public List<MesPlanMonth> getErpProductTaskList(MesPlanMonth paraPlan) {
		// TODO Auto-generated method stub
		return mesPlanMonthDao.getErpProductTaskList(paraPlan);
	}

	@Override
	public List<Map<String, Object>> getSubProcessList(MesPlanTechnic mesPlanTechnic) {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		JobProcess jobProcess = new JobProcess();
		jobProcess.setParentProcess(mesPlanTechnic.getTechnicId());
		List<JobProcess> processList = jobProcessServiceProvider.findList(jobProcess);
		JobTemplateManage jobTemplateManage = new JobTemplateManage();
		for (JobProcess process : processList) {
			HashMap<String, Object> map = new HashMap<>();
			String processCode = process.getProcessCode();
			jobTemplateManage.setProcessCode(processCode);
			// 根据工序查工种
			List<JobTemplateManage> templateList = jobTemplateManageServiceProvider.findList(jobTemplateManage);
			map.put("templateList", templateList);
			map.put("process", process);
			resList.add(map);
		}
		return resList;
	}

	@Override
	public MesPlanMonth getWholeErpBillNo(String id) {
		MesPlanMonth mesPlanMonth = get(id);
		if (mesPlanMonth != null) {
			List<MesPlanTechnic> mesPlanTechnicList = getMesPlanTechnicList(mesPlanMonth);
			mesPlanMonth.setMesPlanTechnicList(mesPlanTechnicList);
			// BOM
			MesPlanBom mesPlanBom = new MesPlanBom();
			mesPlanBom.setErpBillNo(mesPlanMonth.getErpBillNo());
			List<MesPlanBom> mesPlanBomList = mesPlanBomService.findList(mesPlanBom);
			mesPlanMonth.setMesPlanBomList(mesPlanBomList);

		}
		return mesPlanMonth;
	}

	@Override
	public List<MesPlanTechnic> getMesPlanTechnicList(MesPlanMonth mesPlanMonth) {
		MesPlanTechnic mesPlanTechnic = new MesPlanTechnic();
		mesPlanTechnic.setErpBillNo(mesPlanMonth.getErpBillNo());
		// 工序路线及子工序路线
		List<MesPlanTechnic> mesPlanTechnicList = mesPlanTechnicService.findList(mesPlanTechnic);
		if (mesPlanTechnicList != null) {
			for (MesPlanTechnic tmpTechnic : mesPlanTechnicList) {
				List<Map<String, Object>> subTechnicList = getSubProcessList(tmpTechnic);
				tmpTechnic.setSubTechnicList(subTechnicList);
			}
		}
		return mesPlanTechnicList;
	}

	@Override
	public List<JobProcess> getMesPlanTechnicWithoutWorktypeList(MesPlanMonth mesPlanMonth) {
		List<JobProcess> rtnSubJobProcess = new ArrayList<JobProcess>();
		MesPlanTechnic mesPlanTechnic = new MesPlanTechnic();
		mesPlanTechnic.setErpBillNo(mesPlanMonth.getErpBillNo());
		// 工序路线及子工序路线
		List<MesPlanTechnic> mesPlanTechnicList = mesPlanTechnicService.findList(mesPlanTechnic);
		if (mesPlanTechnicList != null) {
			for (MesPlanTechnic tmpTechnic : mesPlanTechnicList) {
				List<JobProcess> subTechnicList = getSubProcessListWithoutWorktype(tmpTechnic);
				rtnSubJobProcess.addAll(subTechnicList);
			}
		}
		return rtnSubJobProcess;
	}

	public List<JobProcess> getSubProcessListWithoutWorktype(MesPlanTechnic mesPlanTechnic) {
		JobProcess jobProcess = new JobProcess();
		jobProcess.setParentProcess(mesPlanTechnic.getTechnicId());
		List<JobProcess> processList = jobProcessServiceProvider.findList(jobProcess);
		return processList;
	}

	@Override
	public List<JobProcess> getArrangeTechnic() {
		List<JobProcess> rtnSubJobProcess = new ArrayList<JobProcess>();
		// 工序路线及子工序路线
		List<MesPlanTechnic> mesPlanTechnicList = new ArrayList<MesPlanTechnic>();
		MesPlanTechnic technic = new MesPlanTechnic();
		technic.setTechnicId("13");// 整理
		mesPlanTechnicList.add(technic);

		MesPlanTechnic technic2 = new MesPlanTechnic();
		technic2.setTechnicId("16");// 打包
		mesPlanTechnicList.add(technic2);

		if (mesPlanTechnicList != null) {
			for (MesPlanTechnic tmpTechnic : mesPlanTechnicList) {
				List<JobProcess> subTechnicList = getSubProcessListWithoutWorktype(tmpTechnic);
				rtnSubJobProcess.addAll(subTechnicList);
			}
		}
		return rtnSubJobProcess;
	}

	@Override
	public PageInfo<MesPlanMonth> getMesPlanMonthListByCrities(MesPlanMonth param, int pageSize, int pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		List<MesPlanMonth> list = mesPlanMonthDao.getMesPlanMonthListByCrities(param);

		try {
			// 对字典字段进行翻译(订单类型,计划状态,车间名称)
			// plan_order_type,plan_order_status,workshop

			// 1 查询订单类型和计划状态的字典映射
			Map<String, List<Map<String, String>>> dicMap = mesPlanCommonService
					.getMutipleDictListByType(PLAN_ORDER_TYPE + "," + PLAN_ORDER_STATUS);

			// 2 查询车间字典映射
			MdmQueryRequest mdmRequest = new MdmQueryRequest();
			mdmRequest.setMdmType(MdmType.ORG);
			Map<String, String> qryMap = new HashMap<String, String>();
			qryMap.put("parentCode", WORK_SHOP_CODE);
			mdmRequest.setQueryMap(qryMap);
			MdmQueryResponse mdmResponse = mdmQueryProvider.queryListByParent(mdmRequest);

			List<Map> workShopList = JSONFormatter.newInstance().toArray(mdmResponse.getResultStr(), Map.class);

			// 3 分别将查询的的字典转换成 map(key,value)来取值
			Map<String, String> planOrderMap = new HashMap();
			Map<String, String> planStatusMap = new HashMap();
			Map<String, String> workShopMap = new HashMap();

			for (Map<String, String> map : dicMap.get(PLAN_ORDER_TYPE)) {
				planOrderMap.put(map.get("id"), map.get("text"));
			}
			for (Map<String, String> map : dicMap.get(PLAN_ORDER_STATUS)) {
				planStatusMap.put(map.get("id"), map.get("text"));
			}
			for (Map map : workShopList) {
				workShopMap.put(map.get("code").toString(), map.get("name").toString());
			}

			// 4 遍历list，进行翻译
			for (MesPlanMonth mesPlanMonth : list) {
				mesPlanMonth.setPlanStatus(planStatusMap.get(mesPlanMonth.getPlanStatus()));
				mesPlanMonth.setBillType(planOrderMap.get(mesPlanMonth.getBillType()));
				mesPlanMonth.setWorkshopId(workShopMap.get(mesPlanMonth.getWorkshopId()));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("getMesPlanMonthListByCrities 异常");
		}

		PageInfo<MesPlanMonth> pageInfo = new PageInfo<MesPlanMonth>(list);
		return pageInfo;
	}

	@Override
	public Map<String, List<Map>> getMesPlanMonthDetail() {

		try {
			List<Map> planStatusList = mesPlanMonthDao.getMesPlanMonthDetail(PLAN_STATUS);
			List<Map> billTypeList = mesPlanMonthDao.getMesPlanMonthDetail(BILL_TYPE);
			List<Map> contractTypeList = mesPlanMonthDao.getMesPlanMonthDetail(CONTRACT_ID);
			List<Map> planStatusViewList = new ArrayList<Map>();
			List<Map> billTypeViewList = new ArrayList<Map>();

			// 1 查询订单类型和计划状态的字典映射
			Map<String, List<Map<String, String>>> dicMap = mesPlanCommonService
					.getMutipleDictListByType(PLAN_ORDER_TYPE + "," + PLAN_ORDER_STATUS);

			// 3 分别将查询的的字典转换成 map(key,value)来取值
			Map<String, String> planOrderMap = new HashMap();
			Map<String, String> planStatusMap = new HashMap();

			for (Map<String, String> map : dicMap.get(PLAN_ORDER_TYPE)) {
				planOrderMap.put(map.get("id"), map.get("text"));
			}
			for (Map<String, String> map : dicMap.get(PLAN_ORDER_STATUS)) {
				planStatusMap.put(map.get("id"), map.get("text"));
			}

			for (Map map : planStatusList) {
				Map mapView = new HashMap();
				mapView.put(getOrigiCode(planStatusMap, map.get(PLAN_STATUS)), map.get("num"));
				planStatusViewList.add(mapView);
			}
			for (Map map : billTypeList) {
				Map mapView = new HashMap();
				mapView.put(getOrigiCode(planOrderMap, map.get(BILL_TYPE)), map.get("num"));
				billTypeViewList.add(mapView);
			}
			Map<String, List<Map>> result = new HashMap();
			result.put(PLAN_STATUS, planStatusViewList);
			result.put(BILL_TYPE, billTypeViewList);
			result.put(CONTRACT_ID,contractTypeList);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getMesPlanMonthDetail 异常");
		}
	}

	// 如果取key取不到，则不翻译了
	public String getOrigiCode(Map map, Object key) {
		Object o = map.get(key);
		if (o == null || "".equals(o)) {
			return key.toString();
		} else {
			return o.toString();
		}
	}

}