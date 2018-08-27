/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanScheduleSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanScheduleService;
import com.bmsmart.mes.plan.service.MesPlanShiftDetService;
import com.github.pagehelper.PageInfo;

/**
 * mes_plan_scheduleController
 * @author zhouqizhi
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "/plan/schedule")
public class MesPlanScheduleController extends BaseController {

	@Autowired
	private MesPlanScheduleService mesPlanScheduleService;
	@Autowired
	private MesPlanDeviceService mesPlanDeviceService;
	
	@Autowired
	private MesPlanShiftDetService mesPlanShiftDetService;
	
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		logger.info("get start!");
		AjaxResponder result = null;
		MesPlanSchedule entity = null;
		if (StringUtil.isNotBlank(id)) {
			entity = mesPlanScheduleService.get(id);
		}
		if (entity == null) {
			result = AjaxResponder.getInstance(Boolean.FALSE, "查找失败,单号不存在或没有权限查看", null);
		} else {
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", entity);
		}
		logger.info("get end!");
		return result;
	}
	
	
	@RequestMapping(value = "/getByUk/index.do")
	@ResponseBody
	public AjaxResponder getByUk(MesPlanSchedule mesPlanSchedule) {
		logger.info("getByUk start!");
		AjaxResponder result = null;
		MesPlanSchedule entity = null;
		entity = mesPlanScheduleService.getByUk(mesPlanSchedule);
		if (entity == null) {
			result = AjaxResponder.getInstance(Boolean.TRUE, "查找失败,单号不存在或没有权限查看", null);
		} else {
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", entity);
		}
		logger.info("getByUk end!");
		return result;
	}
	
	
	@RequestMapping(value = "/saveMaterialUse/det.do")
	@ResponseBody
	public AjaxResponder saveMaterialUse(MesPlanSchedule mesPlanSchedule,@RequestBody List<MesPlanDevice> mesPlanDeviceList, Model model) {
		if (!beanValidator(model, mesPlanSchedule)){
		//	return form(testData, model);
		}
		logger.info("saveMaterialUse start!");
		AjaxResponder result = null;
		if (mesPlanSchedule == null 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkshopId()) 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkcenterId())
				|| mesPlanSchedule.getScheduleDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
    	try {
    		mesPlanScheduleService.saveMaterialUse(mesPlanSchedule,mesPlanDeviceList);
			result = AjaxResponder.getInstance(Boolean.TRUE , "生成领料成功"  , mesPlanSchedule);
    		//String id= mesPlanScheduleService.saveSheet(mesPlanSchedule,mesPlanDeviceList);
    		//result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存生产计划排程成功! "  , id);
		} catch (Exception e) {
			logger.error("save 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("saveMaterialUse end!");
    	return result;
	}
	
	
	
	@RequestMapping(value = "/getsheet/{orderBy}/index.do")
	@ResponseBody
	public AjaxResponder getSheet(@RequestParam(required=false) String id,@PathVariable String orderBy) {
		logger.info("getSheet start!");
		AjaxResponder result =null;
		MesPlanScheduleSheet entity = null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanScheduleService.getSheet(id, orderBy);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		} 
		if (entity == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败"  , null);
		}
		logger.info("getSheet end!");
		return result;
	}
	
	/**
	 * 	至少传入3个参数workcenterId,workshopId,scheduleDate 
	 * unFinished表示是否是延迟单据
	 * @param mesPlanSchedule
	 * @return
	 */
	@RequestMapping(value = "/get/unschedule/index.do")
	@ResponseBody
	public AjaxResponder getUnschedualDeviceList(MesPlanSchedule mesPlanSchedule, HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result =null;
		logger.info("getUnschedualDeviceList start!");
		if (mesPlanSchedule == null 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkshopId()) 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkcenterId())
				|| mesPlanSchedule.getScheduleDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
		try{
			List<MesPlanDevice> deviceList = null;
			deviceList=  mesPlanScheduleService.findUnschedualDeviceList(mesPlanSchedule);
			MesPlanScheduleSheet sheet = new MesPlanScheduleSheet();
			sheet.setUnschedualDeviceList(deviceList);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , sheet);
		}catch (Exception e) {
			logger.error("getUnschedualDeviceList 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
		logger.info("getUnschedualDeviceList end!");
		return result;
	}
	
	
	
	/**
	 * 	至少传入3个参数workcenterId,workshopId,scheduleDate 
	 * unFinished表示是否是延迟单据
	 * @param mesPlanSchedule
	 * @return
	 */
	@RequestMapping(value = "/get/schedule/index.do")
	@ResponseBody
	public AjaxResponder getSchedualDeviceList(MesPlanSchedule mesPlanSchedule, HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result =null;
		logger.info("getSchedualDeviceList start!");
		if (mesPlanSchedule == null 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkshopId()) 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkcenterId())
				|| mesPlanSchedule.getScheduleDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
		try{
			List<MesPlanDevice> deviceList = null;
			deviceList=  mesPlanScheduleService.findSchedualDeviceList(mesPlanSchedule);
			MesPlanScheduleSheet sheet = new MesPlanScheduleSheet();
			sheet.setSchedualDeviceList(deviceList);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , sheet);
		}catch (Exception e) {
			logger.error("getUnschedualDeviceList 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
		logger.info("getSchedualDeviceList end!");
		return result;
	}
	
	
	@RequestMapping(value = {"/findList"})
	@ResponseBody
	public AjaxResponder findList(MesPlanSchedule mesPlanSchedule, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanSchedule> list = mesPlanScheduleService.findList(mesPlanSchedule);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanScheduleController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "form")
	public String form(MesPlanSchedule mesPlanSchedule, Model model) {
		model.addAttribute("mesPlanSchedule", mesPlanSchedule);
		return "mes/plan/mesPlanScheduleForm";
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxResponder save(MesPlanSchedule mesPlanSchedule, Model model) {
		logger.info("save start!");
		if (!beanValidator(model, mesPlanSchedule)){
		//	return form(testData, model);
		}
		AjaxResponder result = null;
    	try {
    		mesPlanScheduleService.save(mesPlanSchedule);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存生产计划排程成功! "  , null);
		} catch (Exception e) {
			logger.error("save 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("save end!");
    	return result;
	}
	
	@RequestMapping(value = "/batchsave/det.do")
	@ResponseBody
	public AjaxResponder saveBatchDet(MesPlanSchedule mesPlanSchedule,@RequestBody List<MesPlanDevice> mesPlanDeviceList, Model model) {
		if (!beanValidator(model, mesPlanSchedule)){
		//	return form(testData, model);
		}
		AjaxResponder result = null;
		logger.info("saveBatchDet start!");
		if (mesPlanSchedule == null 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkshopId()) 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkcenterId())
				|| mesPlanSchedule.getScheduleDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
    	try {
    		String id= mesPlanScheduleService.saveSheet(mesPlanSchedule,mesPlanDeviceList);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存生产计划排程成功! "  , id);
		} catch (Exception e) {
			logger.error("save 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("saveBatchDet end!");
    	return result;
	}
	
	@RequestMapping(value = "/batchcheck/det.do")
	@ResponseBody
	public AjaxResponder checkBatchDet(MesPlanSchedule mesPlanSchedule,@RequestBody List<MesPlanDevice> mesPlanDeviceList, Model model) {
		if (!beanValidator(model, mesPlanSchedule)){
		//	return form(testData, model);
		}
		logger.info("checkBatchDet start!");
		AjaxResponder result = null;
		if (mesPlanSchedule == null 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkshopId()) 
				|| StringUtil.isBlank(mesPlanSchedule.getWorkcenterId())
				|| mesPlanSchedule.getScheduleDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
    	try { 
    		mesPlanScheduleService.checkBatch(mesPlanSchedule,mesPlanDeviceList);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "下发车间排程成功! ",mesPlanSchedule);
		} catch (Exception e) {
			logger.error("save 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("checkBatchDet end!");
    	return result;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/delete/index.do")
	public AjaxResponder delete(@RequestParam(required=true) String id,@RequestParam(required=true) String sheetId) {
		logger.info("delete start!");
		AjaxResponder result = null;
		// 检查是否有单据已排程，已排程就不能删除
		MesPlanScheduleSheet sheet = mesPlanScheduleService.getSheet(id, null);
		if (sheet == null || sheet.getSchedualDeviceList() == null) {
			result = AjaxResponder.getInstance(Boolean.FALSE, "单据可能已被其他人删除,请刷新重试! ", 0);
			return result;
		}
		if (sheet.getSchedualDeviceList() != null && sheet.getSchedualDeviceList().size() > 0) {
			result = AjaxResponder.getInstance(Boolean.FALSE, "单据已有明细排程记录,请先清理后再删除! ", 0);
			return result;
		}
		if (StringUtils.isNotEmpty(sheet.getMesPlanSchedule().getSheetStatus())
				&& Integer.parseInt(sheet.getMesPlanSchedule().getSheetStatus()) > 0) {
			result = AjaxResponder.getInstance(Boolean.FALSE, "单据状态不正确,不能删除! ", 0);
			return result;
		}
		try {
			mesPlanScheduleService.deleteById(sheet.getMesPlanSchedule().getId());
			result = AjaxResponder.getInstance(Boolean.TRUE, "删除厂级调度单头成功! ", 1);
		} catch (Exception e) {
			logger.error("delete 厂级调度单头  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("delete end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete/det.do")
	public ModelAndView delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		mesPlanScheduleService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除生产计划排程成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete 生产计划排程  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
		ModelAndView mv = new ModelAndView("response");
		mv.addObject("result", result);
		logger.info("delete end!");
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/cancel/det.do")
	public AjaxResponder cancel(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("cancel start!");
    	try {
    		MesPlanDevice device = mesPlanDeviceService.get(id);
    		if ("1".equals(device.getScheduleStatus())){
    			device.setScheduleStatus("0");
    			int rowCnt = mesPlanDeviceService.updateScheduleStatus(device);
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "取消排程成功! "  , rowCnt);
    		}
    		else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "排程状态不正确,不能取消! "  , 0);
    		}
    	} catch (Exception e) {
			logger.error("delete 排程状态取消  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), 0);
		}
    	logger.info("cancel end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/cancelcheck/det.do")
	public AjaxResponder cancel2(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("cancel2 start!");
    	try {
    		MesPlanDevice device = mesPlanDeviceService.get(id);
    		if ("10".equals(device.getScheduleStatus())){
    			//检查排班长是否排班，排班后不能清除
    			MesPlanShiftDet detPara = new MesPlanShiftDet();
    			detPara.setSrcBillNo(device.getBillNo());
    			List<MesPlanShiftDet> detList = mesPlanShiftDetService.findList(detPara);
    			if (detList != null && detList.size()>0){
    				result =  AjaxResponder.getInstance(Boolean.FALSE ,  "值班长已排班,不能取消! "  , 0);
    				return result;
    			}
    			device.setScheduleStatus("1");
    			int rowCnt = mesPlanDeviceService.updateScheduleStatus(device);
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "取消排程成功! "  , rowCnt);
    		}
    		else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "排程状态不正确,不能取消! "  , 0);
    		}
    	} catch (Exception e) {
			logger.error("delete 排程状态取消  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("cancel2 end!");
		return result;
	}
	
		
	@ResponseBody
    @RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(MesPlanSchedule mesPlanSchedule,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
       PageInfo<MesPlanSchedule> pageItems =null;
     	AjaxResponder result =null;
     	logger.info("findPage start!");
		try {
			pageItems = mesPlanScheduleService.findPage(pageno, pagesize, mesPlanSchedule);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanScheduleController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
	}
	
}