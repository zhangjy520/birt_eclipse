/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.base.util.web.WebUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTask;
import com.bmsmart.mes.plan.service.MesPlanTempTaskService;

/**
 * mes_plan_temp_taskController
 * @author zhouqz
 * @version 2017-12-22
 */
@Controller
@RequestMapping(value = "/plan/mesPlanTempTask")
public class MesPlanTempTaskController extends BaseController {
	@Autowired
	private MesPlanTempTaskService mesPlanTempTaskService;
	
	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		AjaxResponder result =null;
		logger.info("get start!");
		MesPlanTempTask entity = null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanTempTaskService.get(id);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		}
		if (entity == null){
			entity = new MesPlanTempTask();
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询失败"  ,entity);
		}
		logger.info("get end!");
		return result;
	}
	
	@RequestMapping(value = "/getSheet/index.do")
	@ResponseBody
	public AjaxResponder getSheet(@RequestParam(required=false) String sheetId) {
		AjaxResponder result =null;
		logger.info("getSheet start!");
		MesPlanTempTask entity = null;
		if (StringUtil.isNotBlank(sheetId)){
			entity = mesPlanTempTaskService.getSheet(sheetId);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		}
		if (entity == null){
			entity = new MesPlanTempTask();
			result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败"  ,entity);
		}
		logger.info("getSheet end!");
		return result;
	}
	
	
	@RequestMapping(value = {"/findList/index.do"})
	@ResponseBody
	public AjaxResponder findList(MesPlanTempTask mesPlanTempTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanTempTask> list = mesPlanTempTaskService.findList(mesPlanTempTask);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanTempTaskController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "save/index.do")
	@ResponseBody
	public AjaxResponder save(MesPlanTempTask mesPlanTempTask, Model model) {
		logger.info("save start!");
		if (!beanValidator(model, mesPlanTempTask)) {
			// return form(testData, model);
		}
		AjaxResponder result = null;
		try {
			mesPlanTempTaskService.save(mesPlanTempTask);
			result = AjaxResponder.getInstance(Boolean.TRUE, "保存mes_plan_temp_task成功! ", null);
		} catch (Exception e) {
			logger.error("save mes_plan_temp_task error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("save end!");
		return result;
	}
	
	@RequestMapping(value = "/batchsave/det.do")
	@ResponseBody
	public AjaxResponder saveBatchDet(@RequestParam Date planBeginDate,@RequestParam Date planEndDate,@RequestBody MesPlanTempTask mesPlanTempTask, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		logger.info("saveBatchDet start!");
		AjaxResponder result = null;
		try{
			mesPlanTempTaskService.saveBatchDet(planBeginDate,planEndDate,mesPlanTempTask);
			//MesPlanShiftSheet mesPlanShiftSheet = mesPlanTempTaskService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! "  , mesPlanTempTask);
		}catch (Exception ex){
			logger.error("saveBatchDet mes_plan_temp_task error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("saveBatchDet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/batchcheck/det.do")
	@ResponseBody
	public AjaxResponder checkBatchDet(@RequestBody List<MesPlanTempTask> mesPlanTempTaskList, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		logger.info("checkBatchDet start!");
		AjaxResponder result = null;
		try{
			mesPlanTempTaskService.checkBatchDet(mesPlanTempTaskList);
			//MesPlanShiftSheet mesPlanShiftSheet = mesPlanTempTaskService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! "  , null);
		}catch (Exception ex){
			logger.error("checkBatchDet mes_plan_temp_task error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("checkBatchDet end!");
		return result;
	}
	
	@RequestMapping(value = "/cancelCheck/det.do")
	@ResponseBody
	public AjaxResponder cancelBatchDet(@RequestBody List<MesPlanTempTask> mesPlanTempTaskList, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		logger.info("cancelBatchDet start!");
		AjaxResponder result = null;
		try{
			mesPlanTempTaskService.cancelBatchDet(mesPlanTempTaskList);
			//MesPlanShiftSheet mesPlanShiftSheet = mesPlanTempTaskService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! "  , null);
		}catch (Exception ex){
			logger.error("cancelBatchDet mes_plan_temp_task error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("cancelBatchDet end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "delete/index.do")
	public AjaxResponder delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		MesPlanTempTask taskHead= mesPlanTempTaskService.get(id);
    		if (taskHead != null&&(StringUtils.isBlank(taskHead.getTaskStatus()) || taskHead.getTaskStatus().equals("0"))){
    			mesPlanTempTaskService.deleteSheet(id,taskHead.getSheetId());
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,"删除mes_plan_temp_task成功! ",1);
    		}
    		else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,"单据状态不对,不能删除! ",null);
    		}
    		
    	} catch (Exception e) {
			logger.error("delete mes_plan_temp_task  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("delete end!");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(MesPlanTempTask mesPlanTempTask ,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		PageInfo<MesPlanTempTask> pageItems = null;
		AjaxResponder result = null;
		logger.info("findPage start!");
		try {
			pageItems = mesPlanTempTaskService.findPage(pageno, pagesize, mesPlanTempTask);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanTempTaskController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
    }

}