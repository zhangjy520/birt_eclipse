package com.bmsmart.mes.plan.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.material.export.InventoryProvider;
import com.bmsmart.mes.mesExternal.export.ErpQueryProvider;
import com.bmsmart.mes.mesExternal.export.MdmQueryProvider;
import com.bmsmart.mes.mesExternal.export.MesQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.ErpOrderMaterialQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryPageRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmQueryRequest;
import com.bmsmart.mes.mesExternal.export.request.MdmType;
import com.bmsmart.mes.mesExternal.export.request.MesWeavingInfoQueryPageRequest;
import com.bmsmart.mes.mesExternal.export.response.ErpOrderMaterialInfoResponse;
import com.bmsmart.mes.mesExternal.export.response.MdmQueryResponse;
import com.bmsmart.mes.mesExternal.export.response.MesWeavingInfoResponse;
import com.bmsmart.mes.mesExternal.export.response.vo.MesWeavingInfo;
import com.bmsmart.mes.mesJob.domain.po.JobProcess;
import com.bmsmart.mes.mesJob.domain.po.JobTemplateManage;
import com.bmsmart.mes.mesJob.export.process.JobProcessServiceProvider;
import com.bmsmart.mes.mesJob.export.template.JobTemplateManageServiceProvider;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.export.MesPlanTempTaskProvider;
import com.bmsmart.mes.plan.export.PlanMaterialUseProvider;
import com.bmsmart.mes.plan.export.PlanMonthGetProvider;
import com.bmsmart.mes.plan.export.PlanShiftGetProvider;
import com.bmsmart.mes.plan.export.PlanTaskGainProvider;
import com.bmsmart.mes.plan.export.PlanTaskGetProvider;
import com.bmsmart.mes.plan.export.PlanWorkFinishedProvider;
import com.bmsmart.mes.plan.export.request.PlanRequest;
import com.bmsmart.mes.plan.export.response.PlanResponse;
import com.bmsmart.mes.plan.service.MesPlanShiftUserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "/plan/dubbo/")
public class MesPlanDubboController extends BaseController{
	@Autowired
	private PlanTaskGetProvider planTaskGetProvider;
	@Autowired
	private PlanTaskGainProvider planTaskGainProvider;
	@Autowired
	private PlanWorkFinishedProvider planWorkFinishedProvider;
	@Autowired
	private MesPlanShiftUserService mesPlanShiftUserService;
	@Autowired
	private MdmQueryProvider mdmQueryProvider;
//	@Autowired
//	private InventoryProvider inventoryProvider;
	@Autowired
	private PlanMonthGetProvider planMonthGetProvider;
	@Autowired
	private JobTemplateManageServiceProvider jobTemplateManageServiceProvider;
	@Autowired
	private JobProcessServiceProvider jobProcessServiceProvider;
	@Autowired
	private PlanShiftGetProvider planShiftGetProvider;
	@Autowired
	private PlanMaterialUseProvider planMaterialUseProvider;
	@Autowired
	private MesQueryProvider mesQueryProvider;
	@Autowired
	private ErpQueryProvider erpQueryProvider;
	@Autowired
	private MesPlanTempTaskProvider mesPlanTempTaskProvider;
	
	

	
	
	@RequestMapping(value = "/getMdmOne/index.do")
	@ResponseBody
	public AjaxResponder getMdmOne(@RequestParam(required=true) MdmType mdmType,@RequestParam(required=true) String code){
		logger.info("getMdmOne start!");
		AjaxResponder result =null;
		try{
			MdmQueryRequest request=new MdmQueryRequest();
			request.setMdmType(mdmType);
			request.setMdmCode(code);
			MdmQueryResponse response = mdmQueryProvider.queryById(request);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , response.getResultStr());
		}catch(Exception ex){
			logger.error("getMdmOne error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getMdmOne end!");
		return result;
	}
	
	@RequestMapping(value = "/getMdmList/index.do")
	@ResponseBody
	public AjaxResponder getMdmList(@RequestParam(required=true) MdmType mdmType,
			@RequestParam(required=false) String code,@RequestParam(required=false) String name,
			@RequestParam(required=false) String codeList, 
			@RequestParam(required=false) String parentCode,
			@RequestParam(required=false) String workcenterId,
			@RequestParam(required=false) String deptCode,Model model) throws ParseException {
		AjaxResponder result =null;
		logger.info("getMdmList start!");
		try{
			MdmQueryRequest request=new MdmQueryRequest();
			request.setMdmType(mdmType);
			Map<String,String> qryMap = new HashMap<String,String>();
			if (StringUtils.isNotEmpty(code)){qryMap.put("code", code);}
			if (StringUtils.isNotEmpty(name)){qryMap.put("name", name);}
			if (StringUtils.isNotEmpty(codeList)){qryMap.put("codeList", codeList);}
			if (StringUtils.isNotEmpty(parentCode)){qryMap.put("parentCode", parentCode);}
			if (StringUtils.isNotEmpty(workcenterId)){qryMap.put("workcenter", workcenterId);}
			if (StringUtils.isNotEmpty(deptCode)){qryMap.put("deptCode", deptCode);}
			request.setQueryMap(qryMap);
			MdmQueryResponse listCode	= mdmQueryProvider.queryList(request);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , listCode.getResultStr());
		}catch(Exception ex){
			logger.error("getMdmList error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getMdmList end!");
		return result;
	}
	
	@RequestMapping(value = "/getMdmListByParent/index.do")
	@ResponseBody
	public AjaxResponder getMdmListByParent(@RequestParam(required=true) MdmType mdmType,
			@RequestParam(required=true) String parentCode, Model model) throws ParseException {
		logger.info("getMdmListByParent start!");
		AjaxResponder result =null;
		try{
			MdmQueryRequest listRequest2=new MdmQueryRequest();
			listRequest2.setMdmType(mdmType);
			Map<String,String> qryMap = new HashMap<String,String>();
			qryMap.put("parentCode", parentCode);
			listRequest2.setQueryMap(qryMap);//qMap2.put("parentCode", "C01");
			MdmQueryResponse listCode2	=mdmQueryProvider.queryListByParent(listRequest2);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , listCode2.getResultStr());
		}catch(Exception ex){
			logger.error("getMdmListByParent error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/getQueryPage/{pageno}/{pagesize}/index.do")
	@ResponseBody
	public AjaxResponder getQueryPage(@RequestParam(required=true) MdmType mdmType,
			@PathVariable int  pageno,@PathVariable int  pagesize,
			@RequestParam(required=false) String code,@RequestParam(required=false) String name,@RequestParam(required=false) String codeList) throws ParseException {
		AjaxResponder result =null;
		try{
			Map<String,String> qryMap = new HashMap<String,String>();
			if (StringUtils.isNotEmpty(code)){qryMap.put("code", code);}
			if (StringUtils.isNotEmpty(name)){qryMap.put("name", name);}
			if (StringUtils.isNotEmpty(codeList)){qryMap.put("codeList", codeList);}
			MdmQueryPageRequest pageRequest=new MdmQueryPageRequest();
			pageRequest.setMdmType(mdmType);
			pageRequest.setStartPage(pageno);
			pageRequest.setPageSize(pagesize);
			pageRequest.setQueryMap(qryMap);
			MdmQueryResponse listCode	= mdmQueryProvider.queryPage(pageRequest);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , listCode.getResultStr());
		}catch(Exception ex){
			logger.error("getQueryPage error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		return result;
	}
	
	
	
	@RequestMapping(value = "/getSimpleUserTask/index.do")
	@ResponseBody
	public AjaxResponder getSimpleUserTask(MesPlanShiftUser mesPlanShiftUser) {
		logger.info("getSimpleUserTask start!");
		AjaxResponder result =null;
		try{
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
			if (StringUtils.isBlank(mesPlanShiftUser.getTechnicUser()) && StringUtils.isBlank(mesPlanShiftUser.getId())){
				return AjaxResponder.getInstance(Boolean.FALSE ,  "员工ID或id不能为空"  , null);
			}
			
			List<MesPlanShiftUser> retList = mesPlanShiftUserService.getUserTaskSimpleList(mesPlanShiftUser);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList);
		}catch(Exception ex){
			logger.error("getQueryPage error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getSimpleUserTask end!");
		return result;
	}
	
	/**
	 * 查任务
	 * @param mesPlanShiftUser,可用参数 员工id:technicUser,任务唯一id:id,小工序technicId，工种worktypeId
	 * @return
	 */
	@RequestMapping(value = "/getUserTask/{pageno}/{pagesize}/index.do")
	@ResponseBody
	public AjaxResponder getUserTask2(MesPlanShiftUser mesPlanShiftUser,@PathVariable int pageno,@PathVariable int pagesize) {
		logger.info("getUserTask start!");
		AjaxResponder result =null;
		try{
//			if (StringUtils.isBlank(mesPlanShiftUser.getTechnicUser())&& StringUtils.isBlank(mesPlanShiftUser.getId())){
//				return AjaxResponder.getInstance(Boolean.FALSE ,  "员工ID或id不能为空"  , null);
//			}
			PageInfo<Map<String,Object>> retList = planTaskGetProvider.getUserTask(pageno, pagesize, mesPlanShiftUser);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList);
		}catch(Exception ex){
			logger.error("getUserTask error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getUserTask end!");
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/getUserTask/index.do")
	@ResponseBody
	public AjaxResponder getUserTask(MesPlanShiftUser mesPlanShiftUser) {
		logger.info("getUserTask start!");
		AjaxResponder result =null;
		try{
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
			if (StringUtils.isBlank(mesPlanShiftUser.getTechnicUser())&& StringUtils.isBlank(mesPlanShiftUser.getId())){
				return AjaxResponder.getInstance(Boolean.FALSE ,  "员工ID或id不能为空"  , null);
			}
			List<Map<String,Object>> retList = planTaskGetProvider.getUserTask(mesPlanShiftUser);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList);
		}catch(Exception ex){
			logger.error("getUserTask error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getUserTask end!");
		return result;
	}
	
	
	
	/**
	 * 查任务
	 * @param MesPlanMonth
	 * @return
	 */
	@RequestMapping(value = "/getErpMonthList/index.do")
	@ResponseBody
	public AjaxResponder getErpMonthList(MesPlanMonth mesPlanMonth) {
		logger.info("getErpMonthList start!");
		AjaxResponder result =null;
		try{
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
			if (mesPlanMonth.getPlanBeginDate1() == null || mesPlanMonth.getPlanBeginDate2() == null){
				return AjaxResponder.getInstance(Boolean.FALSE ,  "开始日期不能为空"  , null);
			}
			List<MesPlanMonth> retList = planMonthGetProvider.getErpMonthPlanList(mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList);
		}catch(Exception ex){
			logger.error("getUserTask error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getErpMonthList end!");
		return result;
	}
	
 
	
	/**
	 * 查任务
	 * @param MesPlanMonth planBeginDate1,planBeginDate2
	 * @return
	 */
	@RequestMapping(value = "/getErpProductTaskList/index.do")
	@ResponseBody
	public AjaxResponder getErpProductTaskList(MesPlanMonth mesPlanMonth) {
		logger.info("getErpProductTaskList start!");
		AjaxResponder result =null;
		try{
		//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
		if (mesPlanMonth.getPlanBeginDate1() == null || mesPlanMonth.getPlanBeginDate2() == null){
			return AjaxResponder.getInstance(Boolean.FALSE ,  "开始日期不能为空"  , null);
		}
		List<MesPlanMonth> retList = planMonthGetProvider.getErpProductTaskList(mesPlanMonth);
		result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList);
		}catch(Exception ex){
			logger.error("getUserTask error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getErpProductTaskList end!");
		return result;
	}
	
	
	
	@RequestMapping(value = "/getGoodsIdByProductTask/index.do")
	@ResponseBody
	public AjaxResponder getErpMonthPlanByProductTask(MesPlanMonth mesPlanMonth) {
		logger.info("getErpMonthPlanByProductTask start!");
		AjaxResponder result =null;
		try{
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
			if (StringUtils.isBlank(mesPlanMonth.getErpProductTaskNo())){
				return AjaxResponder.getInstance(Boolean.FALSE ,"生产任务书编号不能为空", null);
			}
			MesPlanMonth ret = planMonthGetProvider.getErpMonthPlanByProductTask(mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , ret);
		}catch(Exception ex){
			logger.error("getUserTask error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getErpMonthPlanByProductTask end!");
		return result;
	}
	
	
	/**
	 * 领任务
	 * @param mesPlanShiftUser 传入:任务唯一id:id,任务状态taskStatus 0=未领,10=已领,修改人updateUser
	 * @return
	 */
	@RequestMapping(value = "/updateUserTaskStatus/index.do")
	@ResponseBody
	public AjaxResponder updateUserTaskStatus(MesPlanShiftUser mesPlanShiftUser) {
		logger.info("updateUserTaskStatus start!");
		AjaxResponder result =null;
		//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
		if (StringUtils.isBlank(mesPlanShiftUser.getId()) || StringUtils.isBlank(mesPlanShiftUser.getTaskStatus())){
			return AjaxResponder.getInstance(Boolean.FALSE ,  "id,taskStatus参数不能为空"  , null);
		}
		int rtn =planTaskGainProvider.updateTaskStatus(mesPlanShiftUser);
		try{
			if (rtn==1){
				result = AjaxResponder.getInstance(Boolean.TRUE, "更新任务状态成功"  , "");
			}else{
				result = AjaxResponder.getInstance(Boolean.FALSE, "更新了0条记录"  , "");
			}
		}catch(Exception ex){
			logger.error("updateUserTaskStatus error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, ex.getMessage()  , "");
		}
		logger.info("updateUserTaskStatus end!");
		return result;
	}
	
	
	/**
	 * 报工
	 * @param mesPlanShiftDet传入 billNo= 领任务的scrBillNo ,finishQty,updateUser
	 * @return
	 */
	@RequestMapping(value = "/updateFinishQtyByBillNo/index.do")
	@ResponseBody
	public AjaxResponder updateFinishQtyByBillNo(MesPlanShiftDet mesPlanShiftDet) {
		logger.info("updateUserTaskStatus开始!");
		AjaxResponder result =null;
		//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
		if (StringUtils.isBlank(mesPlanShiftDet.getBillNo()) || mesPlanShiftDet.getFinishQty()==0){
			return AjaxResponder.getInstance(Boolean.FALSE ,  "billNo,finishQty参数不能为空"  , null);
		}
		try{
			int rtn = planWorkFinishedProvider.updateFinishQtyByBillNo(mesPlanShiftDet);
			if (rtn==1){
				result = AjaxResponder.getInstance(Boolean.TRUE, "报工成功"  , "");
			}else{
				result = AjaxResponder.getInstance(Boolean.FALSE, "更新了0条记录"  , "");
			}
		}catch(Exception ex){
			logger.error("updateFinishQtyByBillNo error by MesPlanDubboController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, ex.getMessage()  , "");
		}
		return result;
	}
	
//	/**
//	 * 报工
//	 * @param mesPlanShiftDet
//	 * @return
//	 */
//	@RequestMapping(value = "/updateFinishQtyById/index.do")
//	@ResponseBody
//	public AjaxResponder updateFinishQtyById(MesPlanShiftDet mesPlanShiftDet) {
//		logger.info("updateUserTaskStatus start!");
//		AjaxResponder result =null;
//		try{
//			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
//			if (StringUtils.isBlank(mesPlanShiftDet.getId()) || mesPlanShiftDet.getFinishQty()<=0){
//				return AjaxResponder.getInstance(Boolean.FALSE ,  "id,taskStatus参数不能为空"  , null);
//			}
//			int rtn = planWorkFinishedProvider.updateFinishQtyById(mesPlanShiftDet);
//			if (rtn==1){
//				result = AjaxResponder.getInstance(Boolean.TRUE, "报工成功"  , "");
//			}else{
//				result = AjaxResponder.getInstance(Boolean.FALSE, "更新了0条记录"  , "");
//			}
//		}catch(Exception ex){
//			logger.error("updateFinishQtyById error by MesPlanDubboController ,exception:{}", ex);
//			ex.printStackTrace();
//			result = AjaxResponder.getInstance(Boolean.FALSE, ex.getMessage()  , "");
//		}
//		logger.info("updateUserTaskStatus end!");
//		return result;
//	}
	
//	@RequestMapping(value = "/getMaterialBill/{pageno}/{pagesize}/index.do")
//	@ResponseBody
//	public AjaxResponder getMaterialBillProvider(@PathVariable int  pageno,@PathVariable int  pagesize,BillMaterialInfo billMaterialInfo) {
//		logger.info("getMaterialBillProvider start!");
//		AjaxResponder result =null;
//		try{
//			Map<String,String> qryMap = new HashMap<String,String>();
//			
//			SerialInventoryRecordRequest request= new SerialInventoryRecordRequest();
//			request.setPageNum(pageno);
//			request.setPageSize(pagesize);
//			
//			SerialInventoryRecordResponse ret = inventoryProvider.findSerialPage(request);
//			PageInfo<SerialInventoryRecord> recodeInfo = ret.getData();
//			result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , recodeInfo);
//		}catch(Exception ex){
//			logger.error("updateFinishQtyById error by MesPlanDubboController ,exception:{}", ex);
//			ex.printStackTrace();
//			result = AjaxResponder.getInstance(Boolean.FALSE, ex.getMessage()  , "");
//		}
//		logger.info("getMaterialBillProvider end!");
//		return result;
//	}
	
	
	/**
	    * 查工序信息，无分页
	    * @param jobProcess 传入:小工序id:id 或  工序编码processCode  或  父级工序编码parentProcess
	    * @return
	    */
	   @ResponseBody
	   @RequestMapping(value = {"/getSubProcessList/index.do"})
	   public AjaxResponder findList(JobProcess jobProcess) {
			AjaxResponder result = null;
			logger.info("findList start!");
			try {
				if (StringUtils.isBlank(jobProcess.getParentProcess())) {
					return AjaxResponder.getInstance(Boolean.FALSE, "parentProcess不能为空", null);
				}
				List<Map<String, Object>> resList = new ArrayList<>();
	
				// 根据大工序查子工序
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
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", resList);
			} catch (Exception e) {
				logger.error(" findList error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
			}
			logger.info("findList end!");
			return result;
	   }
	   
	   
	   /**
	    * 查工序信息，无分页
	    * @param jobProcess 传入:小工序id:id 或  工序编码processCode  或  父级工序编码parentProcess
	    * @return
	    */
	   @ResponseBody
	   @RequestMapping(value = {"/getSimpleSubProcessList/index.do"})
	   public AjaxResponder getSimpleSubProcessList(JobProcess jobProcess) {
			AjaxResponder result = null;
			logger.info("getSimpleSubProcessList start!");
			try {
				if (StringUtils.isBlank(jobProcess.getProcessCode())) {
					return AjaxResponder.getInstance(Boolean.FALSE, "processCode不能为空", null);
				}
				// List<Map<String,Object>> resList =new ArrayList<>();
				// 根据大工序查子工序
				List<JobProcess> processList = jobProcessServiceProvider.findList(jobProcess);
				if (processList != null && processList.size() > 0) {
					result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", processList.get(0));
				} else {
					result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", new HashMap<>());
				}
			} catch (Exception e) {
				logger.error("getSimpleSubProcessList error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
			}
			logger.info("getSimpleSubProcessList end!");
			return result;
	   }
	   
	   @RequestMapping(value = "/workcenterShiftHead/index.do")
	   @ResponseBody
		public AjaxResponder getWorkcenterShiftHead(@RequestParam(required=true) String workcenterId,@RequestParam(required=true) Date shiftDate,@RequestParam(required=true) String worktime) {
		    logger.info("getWorkcenterShiftHead start!");
			AjaxResponder result =null;
			try{
				MesPlanShiftHead head =  planShiftGetProvider.getWorkcenterShiftHead(workcenterId, shiftDate, worktime);
				result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , head);
			}catch(Exception ex){
				logger.error("getWorkcenterShiftHead error by MesPlanDubboController ,exception:{}", ex);
				ex.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE ,  "调用失败!"+ex.getMessage()  , null);
			}
			logger.info("getWorkcenterShiftHead end!");
			return result;
		}
	   
	   @RequestMapping(value = "/getDeviceShiftPlan/index.do")
	   @ResponseBody
		public AjaxResponder getDeviceShiftPlan(@RequestParam(required=true) List<String> deviceList,@RequestParam(required=true) String workcenterId,@RequestParam(required=true) Date shiftDate,@RequestParam(required=true) String worktime) {
			logger.info("getDeviceShiftPlan start");
			AjaxResponder result = null;
			try {
				Map<String, MesPlanMonth> retMap = planShiftGetProvider.getDeviceShiftPlan(deviceList, workcenterId,shiftDate, worktime);
				result = AjaxResponder.getInstance(Boolean.TRUE, "调用成功", retMap);
			} catch (Exception ex) {
				logger.error("调用getDeviceShiftPlan出错{}", ex);
				ex.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE, "调用失败!" + ex.getMessage(), null);
			}
			logger.info("getDeviceShiftPlan end!");
			return result;
		}
	   
	   
	   @ResponseBody
	   @RequestMapping(value = "/findPlanPage/{pageno}/{pagesize}/index.do")	
	    public AjaxResponder findPage(            
	            MesPlanMonth mesPlanMonth,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		   logger.info("findPage start!");
	       PageInfo<MesPlanMonth> pageItems =null;
	       AjaxResponder result =null;
	       try {
	       		pageItems=planMonthGetProvider.findPage(mesPlanMonth,pageno,pagesize);
	       		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , pageItems);
	       } catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
	       logger.info("findPage end!");
	       return result;
	    }
	   
	   @RequestMapping(value = "/getPlanMaterialUse/index.do")
		@ResponseBody
		public AjaxResponder getPlanMaterialUse(MesPlanMaterialUse mesPlanMaterialUse) {
		    logger.info("getDeviceShiftPlan start!");
		    if (mesPlanMaterialUse.getScheduleDate() == null || StringUtils.isBlank(mesPlanMaterialUse.getWorkshopId())){
				return AjaxResponder.getInstance(Boolean.FALSE ,  "车间和调度日期不能为空"  , null);
			}
			AjaxResponder result =null;
			try{
				List<MesPlanMaterialUse>  mesPlanMaterialUseList =  planMaterialUseProvider.getPlanMaterialUse(mesPlanMaterialUse);
				result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , mesPlanMaterialUseList);
			}catch(Exception ex){
				logger.error("调用getPlanMaterialUse出错,exception:{}",ex);
				result = AjaxResponder.getInstance(Boolean.FALSE ,  "调用失败!"+ex.getMessage()  , null);
				ex.printStackTrace();
			}
			logger.info("getDeviceShiftPlan end!");
			return result;
		}
	   
	   /**
	    * 查询落布信息
	    * @param weavingAssignment
	    * @param status
	    * @param startTime
	    * @param endTime
	    * @param shaftCode
	    * @param pageno
	    * @param pagesize
	    * @param request
	    * @param response
	    * @return
	    */
	   @ResponseBody
	   @RequestMapping(value = "/findSuitPage/{pageno}/{pagesize}/index.do")	
	   public AjaxResponder findSuitPage(MesWeavingInfoQueryPageRequest mesPageRequest,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
	       AjaxResponder result =null;
	       logger.info("findSuitPage start!");
	       try {
//	    	   MesWeavingInfoQueryPageRequest mesPageRequest=new MesWeavingInfoQueryPageRequest();
//	    		mesPageRequest.setStartPage(pageno);
//	    		mesPageRequest.setPageSize(pagesize);
//	    		if (StringUtils.isNotEmpty(weavingAssignment)){
//	    			mesPageRequest.setWeavingAssignment(weavingAssignment);
//	    		}
//	    		if (StringUtils.isNotEmpty(status)){
//	    			mesPageRequest.setStatus(status);
//	    		}
//	    		if (startTime != null){
//	    			mesPageRequest.setStartTime(startTime);
//	    		}
//	    		if (endTime != null){
//	    			mesPageRequest.setEndTime(endTime);
//	    		}
//	    		if (StringUtils.isNotEmpty(shaftCode)){
//	    			mesPageRequest.setShaftCode(shaftCode);
//	    		}
//	    		if (StringUtils.isNotEmpty(serialNo)){
//	    			mesPageRequest.setse
//	    		}
	    	   	mesPageRequest.setStartPage(pageno);
	    		mesPageRequest.setPageSize(pagesize);
	    		MesWeavingInfoResponse pagemcode = mesQueryProvider.queryMesWeavingInfoPage(mesPageRequest);
	    		
	    		PageInfo<MesWeavingInfo> pageItems = JSONFormatter.newInstance().toObject(pagemcode.getResultStr(), PageInfo.class);
	    		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , pageItems);
	       } catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
	       logger.info("findSuitPage end!");
	       return result;
	    }
	   
	   
	   @ResponseBody
	   @RequestMapping(value = "/findErpSuitList/index.do")	
	   public AjaxResponder findErpSuitList(String orderNo,Float planNum,Float totalNum,HttpServletRequest request, HttpServletResponse response) {
	       AjaxResponder result =null;
	       logger.info("findErpSuitList start!");
	       try {
	    	   if (StringUtils.isBlank(orderNo) || planNum == null || totalNum == null){
					return AjaxResponder.getInstance(Boolean.FALSE ,  "订单号,数量,订单总数量不能为空"  , null);
	    	   }
	    	   ErpOrderMaterialQueryRequest requestr=new ErpOrderMaterialQueryRequest();
	    	   requestr.setOrderNo(orderNo);
	    	   requestr.setTotalNum(totalNum);
	    	   requestr.setPlanNum(planNum);
	    	   ErpOrderMaterialInfoResponse list2 = erpQueryProvider.queryOrderMaterialInfo(requestr);
	    	   List<MesWeavingInfo> mesWeavingInfoList = JSONFormatter.newInstance().toObject(list2.getResultStr(), List.class);
	    	   Map<String,Object> rtnMap = new HashMap<String,Object>();
	    	   rtnMap.put("list", mesWeavingInfoList);
	    	   result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , rtnMap);
	       } catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
	       logger.info("findErpSuitList end!");
	       return result;
	    }
	   
	   
	   
	   
	   @ResponseBody
	   @RequestMapping(value = "/getDeviceWroktypeUser1/index.do")	
	   public AjaxResponder getDeviceWroktypeUser(String deviceList, String workcenterId,
				Date shiftDate, String worktime,String worktypeId) {
	       AjaxResponder result =null;
	       logger.info("getDeviceWroktypeUser start!");
	       try {
	    	   if (StringUtils.isBlank(workcenterId) || StringUtils.isBlank(deviceList) ||shiftDate == null ||StringUtils.isBlank(worktime)||StringUtils.isBlank(worktypeId) ){
					return AjaxResponder.getInstance(Boolean.FALSE ,  "参数输入不正确"  , null);
	    	   }
	    	   
	    	   String[] deviceArray  = deviceList.split(",");
	    	   
	    	   Map<String,MesPlanShiftUser> rtnMap = planShiftGetProvider.getDeviceWroktypeUser(Arrays.asList(deviceArray),workcenterId,shiftDate,worktime,worktypeId);
	    	   result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , rtnMap);
	       } catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
	       logger.info("getDeviceWroktypeUser end!");
	       return result;
	    }
	   
	   @ResponseBody
	   @RequestMapping(value = "/getDeviceWroktypeUser2/index.do")	
	   public AjaxResponder getDeviceWroktypeUser(String workcenterId, Date shiftDate,String worktime,String worktypeId,HttpServletRequest request, HttpServletResponse response) {
	       AjaxResponder result =null;
	       logger.info("getDeviceWroktypeUser start!");
	       try {
	    	   if (StringUtils.isBlank(workcenterId) || shiftDate == null ||StringUtils.isBlank(worktime)||StringUtils.isBlank(worktypeId) ){
					return AjaxResponder.getInstance(Boolean.FALSE ,  "参数输入不正确"  , null);
	    	   }
	    	   Map<String,MesPlanShiftUser> rtnMap = planShiftGetProvider.getDeviceWroktypeUser(workcenterId,shiftDate,worktime,worktypeId);
	    	   result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , rtnMap);
	       } catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
	       logger.info("getDeviceWroktypeUser end!");
	       return result;
	    }
 
	   @ResponseBody
	   @RequestMapping(value = "/getDeviceWroktypeUser3/index.do")
	   public AjaxResponder getDeviceWroktypeUser(String workcenterIdList,
				Date shiftDate, String worktime,String worktypeId){
		   AjaxResponder result =null;
		   logger.info("getDeviceWroktypeUser start!");
	       try {
	    	   if (StringUtils.isBlank(workcenterIdList) || shiftDate == null ||StringUtils.isBlank(worktime)||StringUtils.isBlank(worktypeId) ){
					return AjaxResponder.getInstance(Boolean.FALSE ,  "参数输入不正确"  , null);
	    	   }
	    	   
	    	   String[] workcenterIdArr  = workcenterIdList.split(",");
	    	   
	    	   Map<String, Map<String, MesPlanShiftUser>> rtnMap = planShiftGetProvider.getDeviceWroktypeUser(Arrays.asList(workcenterIdArr),shiftDate,worktime,worktypeId);
	    	   result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , rtnMap);
	       } catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
	       logger.info("getDeviceWroktypeUser end!");
	       return result;
	   }
	   
	   
	   
	  
		@RequestMapping(value = "/getPageTempTask/{pageno}/{pagesize}/index.do")
		@ResponseBody
		public AjaxResponder getTempTask(MesPlanTempTask mesPlanTempTask,@PathVariable int pageno,@PathVariable int pagesize) {
			logger.info("getTempTask start!");
			AjaxResponder result =null;
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
//			if (StringUtils.isBlank(mesPlanTempTask.getTaskUser()) || mesPlanTempTask.getPlanBeginDate1() == null || mesPlanTempTask.getPlanBeginDate2() == null){
//				return AjaxResponder.getInstance(Boolean.FALSE ,  "员工号,任务开始日期，结束日期不能为空"  , null);
//			}
			try {
				PageInfo<MesPlanTempTask> retList = mesPlanTempTaskProvider.findPage(pageno,pagesize,mesPlanTempTask);
				result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList);
			} catch (Exception e) {
				logger.error(" findPage error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
			}
			logger.info("getTempTask end!");
			return result;
		}
		
		
		@RequestMapping(value = "/saveUseTempTask/index.do")
		@ResponseBody
		public AjaxResponder updateTempTaskUseTaskStatus(MesPlanTempTask mesPlanTempTask) {
			logger.info("updateTempTaskUseTaskStatus start!");
			AjaxResponder result =null;
			try{
				//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
				if (StringUtils.isBlank(mesPlanTempTask.getId())){
					return AjaxResponder.getInstance(Boolean.FALSE ,  "id不能为空"  , null);
				}
				int cnt = mesPlanTempTaskProvider.updateUseTaskStatus(mesPlanTempTask);
				result = AjaxResponder.getInstance(cnt>=1 ,  "调用返回行数"  , cnt);
			} catch (Exception e) {
				logger.error(" updateTempTaskUseTaskStatus error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
			}
			logger.info("updateTempTaskUseTaskStatus end!");
			return result;
		}
		
		
		@RequestMapping(value = "/saveFinishTempTask/index.do")
		@ResponseBody
		public AjaxResponder updateTempTaskFinishTaskStatus(MesPlanTempTask mesPlanTempTask) {
			logger.info("updateTempTaskFinishTaskStatus start!");
			AjaxResponder result =null;
			try{
				//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
				if (StringUtils.isBlank(mesPlanTempTask.getId()) || StringUtils.isBlank(mesPlanTempTask.getTaskFinishDesc())){
					return AjaxResponder.getInstance(Boolean.FALSE ,  "id及报工说明不能为空"  , null);
				}
				int cnt = mesPlanTempTaskProvider.updateFinishTaskStatus(mesPlanTempTask);
				result = AjaxResponder.getInstance(cnt>=1 ,  "调用返回行数"  , cnt);
			} catch (Exception e) {
				logger.error(" updateTempTaskUseTaskStatus error by MesPlanDubboController ,exception:{}", e);
				e.printStackTrace();
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
			}
			
			logger.info("updateTempTaskFinishTaskStatus end!");
			return result;
		}
		
		
		
		
		@RequestMapping(value = "/getSceneUserTask/{pageno}/{pagesize}/index.do")
		@ResponseBody
		public AjaxResponder getSceneUserTask(MesPlanTempShiftUser mesPlanTempShiftUser,@PathVariable int pageno,@PathVariable int pagesize) {
			logger.info("getUserTask start!");
			AjaxResponder result =null;
			try{
//				if (StringUtils.isBlank(mesPlanShiftUser.getTechnicUser())&& StringUtils.isBlank(mesPlanShiftUser.getId())){
//					return AjaxResponder.getInstance(Boolean.FALSE ,  "员工ID或id不能为空"  , null);
//				}
				PlanRequest<MesPlanTempShiftUser>  request = new PlanRequest<MesPlanTempShiftUser>();
				request.setRequestObj(mesPlanTempShiftUser);
				PlanResponse<PageInfo<Map<String,Object>>> retList = planTaskGetProvider.getSceneUserTask(pageno, pagesize, request);
				result = AjaxResponder.getInstance(Boolean.TRUE ,  "调用成功"  , retList.getRetOb());
			}catch(Exception ex){
				logger.error("getUserTask error by MesPlanDubboController ,exception:{}", ex);
				ex.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
			}
			logger.info("getUserTask end!");
			return result;
		}
		
		
		
		
		/**
		 * (现场任务)领任务
		 * @param mesPlanShiftUser 传入:任务唯一id:id,任务状态taskStatus 0=未领,10=已领,修改人updateUser
		 * @return
		 */
		@RequestMapping(value = "/updateSceneUserTaskStatus/index.do")
		@ResponseBody
		public AjaxResponder updateSceneUserTaskStatus(MesPlanTempShiftUser mesPlanTempShiftUser) {
			logger.info("updateSceneUserTaskStatus start!");
			AjaxResponder result =null;
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
			if (StringUtils.isBlank(mesPlanTempShiftUser.getId()) || StringUtils.isBlank(mesPlanTempShiftUser.getTaskStatus())){
				return AjaxResponder.getInstance(Boolean.FALSE ,  "id,taskStatus参数不能为空"  , null);
			}
			PlanRequest<MesPlanTempShiftUser> requestObj =  new PlanRequest<MesPlanTempShiftUser>();
			requestObj.setRequestObj(mesPlanTempShiftUser);
			int rtn =planTaskGainProvider.updateSceneTaskStatus(requestObj).getRetOb();
			try{
				if (rtn==1){
					result = AjaxResponder.getInstance(Boolean.TRUE, "更新任务状态成功"  , "");
				}else{
					result = AjaxResponder.getInstance(Boolean.FALSE, "更新了0条记录"  , "");
				}
			}catch(Exception ex){
				logger.error("updateUserTaskStatus error by MesPlanDubboController ,exception:{}", ex);
				ex.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE, ex.getMessage()  , "");
			}
			logger.info("updateSceneUserTaskStatus end!");
			return result;
		}
		
		
		
		/**
		 * (现场任务)报工
		 * @param mesPlanShiftDet传入 billNo= 领任务的scrBillNo ,finishQty,updateUser
		 * @return
		 */
		@RequestMapping(value = "/updateSceneFinishQtyByBillNo/index.do")
		@ResponseBody
		public AjaxResponder updateSceneFinishQtyByBillNo(MesPlanTempTaskDet mesPlanTempTaskDet) {
			logger.info("updateSceneFinishQtyByBillNo开始!");
			AjaxResponder result =null;
			//mesPlanShiftHeadService.getUserTask(mesPlanShiftUser);
			if (StringUtils.isBlank(mesPlanTempTaskDet.getBillNo()) || mesPlanTempTaskDet.getFinishQty()==0){
				return AjaxResponder.getInstance(Boolean.FALSE ,  "billNo,finishQty参数不能为空"  , null);
			}
			PlanRequest<MesPlanTempTaskDet> requestObj =  new PlanRequest<MesPlanTempTaskDet>();
			requestObj.setRequestObj(mesPlanTempTaskDet);
			try{
				int rtn = planWorkFinishedProvider.updateSceneFinishQtyByBillNo(requestObj).getRetOb();
				if (rtn==1){
					result = AjaxResponder.getInstance(Boolean.TRUE, "报工成功"  , "");
				}else{
					result = AjaxResponder.getInstance(Boolean.FALSE, "更新了0条记录"  , "");
				}
			}catch(Exception ex){
				logger.error("updateSceneFinishQtyByBillNo error by MesPlanDubboController ,exception:{}", ex);
				ex.printStackTrace();
				result = AjaxResponder.getInstance(Boolean.FALSE, ex.getMessage()  , "");
			}
			return result;
		}
		
		
		
		
}
