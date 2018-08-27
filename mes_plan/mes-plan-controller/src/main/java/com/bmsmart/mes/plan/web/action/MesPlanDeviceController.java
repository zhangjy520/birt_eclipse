/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.base.util.web.WebUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;


import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftSheet;
import com.bmsmart.mes.plan.service.MesPlanDeviceDayService;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;

/**
 * 分解设备并行加工Controller
 * @author zhouqz
 * @version 2017-08-16
 */
@Controller
@RequestMapping(value = "/plan/device")
public class MesPlanDeviceController extends BaseController {

	@Autowired
	private MesPlanMonthService mesPlanMonthService;
	
	@Autowired
	private MesPlanDeviceService mesPlanDeviceService;
	
	@Autowired
	private MesPlanDeviceDayService mesPlanDeviceDayService;
	
	@Autowired
	private MesPlanDispatchDetService mesPlanDispatchDetService;
	
	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		logger.info("get start! para={0}",id);
		AjaxResponder result =null;
		MesPlanDevice entity = null;
		try{
			if (StringUtil.isNotBlank(id)){
				entity = mesPlanDeviceService.get(id);
				result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  ,entity);
			}
			if (entity == null){
				entity = new MesPlanDevice();
				result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败"  ,entity);
			}
		}catch(Exception ex){
			logger.error("get ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE ,ex.getMessage() ,null);
		}
		logger.info("get end!");
		return result;
	}
	
	@RequestMapping(value = "/getsheet/index.do")
	@ResponseBody
	public AjaxResponder getSheet(@RequestParam(required=false) String id) {
		logger.info("getSheet start! para={0}",id);
		AjaxResponder result =null;
		try{
			MesPlanDeviceSheet entity = null;
			if (StringUtil.isNotBlank(id)){
				entity = mesPlanDeviceService.getSheet(id);
				result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
			} 
			if (entity == null){
				result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败"  , null);
			}
		}catch(Exception ex){
			logger.error("getSheet ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE ,ex.getMessage() ,null);
		}
		logger.info("getSheet end! para={0}",id);
		return result;
	}
	
	@RequestMapping(value = "/getAvail/index.do")
	@ResponseBody
	public AjaxResponder getDeviceAvailMaxDate(MesPlanDeviceDay mesPlanDeviceDay) {
		logger.info("getDeviceAvailMaxDate start!");
		
		AjaxResponder result =null;
		MesPlanDeviceDay entity = null;
		entity = mesPlanDeviceDayService.getDeviceAvailMaxDate(mesPlanDeviceDay);
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		
		logger.info("getDeviceAvailMaxDate end!");
		return result;
	}
	
	
	
	@RequestMapping(value = {"/findList/index.do"})
	@ResponseBody
	public AjaxResponder findList(MesPlanDevice mesPlanDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("findList start!");
		AjaxResponder result =null;
		  try {
		   List<MesPlanDevice> list = mesPlanDeviceService.findList(mesPlanDevice); 
	    	result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , list);
       } catch (Exception e) {
			logger.error(" findList error by MesPlanDeviceController ,exception:{}", e);
			result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
	   }
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "form")
	public String form(MesPlanDevice mesPlanDevice, Model model) {
		model.addAttribute("mesPlanDevice", mesPlanDevice);
		return "mes/plan/mesPlanDeviceForm";
	}

	@RequestMapping(value = "/save/index.do")
	@ResponseBody
	public AjaxResponder save(MesPlanDevice mesPlanDevice, Model model) {
		logger.info("save start!");
		if (!beanValidator(model, mesPlanDevice)){
			//	return form(testData, model);
			}
			AjaxResponder result = null;
	    	try {
				mesPlanDeviceService.save(mesPlanDevice);
				result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存分解设备并行加工成功! "  , null);
		} catch (Exception e) {
				logger.error("save 分解设备并行加工 error by Controller ,exception:{}", e.getMessage());
				e.printStackTrace();
				result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
	    logger.info("save end!");
		return result;
	}
		
	@RequestMapping(value = "/save/det.do")
	@ResponseBody
	public AjaxResponder saveDet(MesPlanDevice mesPlanDevice,@RequestBody String postMapStr, Model model) throws Exception  {
		logger.info("saveDet start!");
		if (!beanValidator(model, mesPlanDevice)){
			//	return form(testData, model);
		}
		AjaxResponder result = null;
		//dispatchId
		Map<String,String> postMap = JSON.parse(postMapStr, Map.class) ;
		if (!postMap.containsKey("dispatchId")){
			return AjaxResponder.getInstance(Boolean.FALSE ,  "没有dispatchId参数! "  , null);
		}
		String dispatchId = postMap.get("dispatchId");//厂级调度单号
    	try {
    		
    		MesPlanMonth paraMonth = new MesPlanMonth();
    		paraMonth.setErpBillNo(mesPlanDevice.getErpBillNo());
    		MesPlanMonth order = mesPlanMonthService.getByUk(paraMonth);
    		
    		mesPlanDeviceService.saveDet(mesPlanDevice, order,dispatchId);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "指定设备成功! "  , null);
    	} catch (Exception e) {
			logger.error("save 分解设备并行加工 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
    	}
    	logger.info("saveDet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/batchsave/det.do")
	@ResponseBody
	//public AjaxResponder saveBatchDet(String parId,@RequestBody String postMapStr, Model model) throws Exception  {
	public AjaxResponder saveBatchDet(String dispatchId,@RequestBody List<MesPlanDevice> mesPlanDeviceList, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		logger.info("saveBatchDet start!");
		AjaxResponder result = null;
		try{
			mesPlanDeviceService.saveBatchDet(mesPlanDeviceList, dispatchId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "指定设备成功! "  , null);
		}catch (Exception ex){
			logger.error("saveBatchDet 分解设备并行加工  error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("saveBatchDet end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete/det.do")
	public AjaxResponder delete(@RequestParam(required=true) String id,@RequestParam(required=true)String workcenterId,@RequestParam(required=true)String goodsId,@RequestParam(required=true)String srcBillNo) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		MesPlanDevice device = mesPlanDeviceService.get(id);
    		if ("0".equals(device.getDispatchStatus())){
    			int rowcnt = mesPlanDeviceService.deleteRow(id, workcenterId,srcBillNo);
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除分解设备并行加工成功! "  , rowcnt);
    		}
    		else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "计划状态不正确,不能删除! "  , null);
    		}
    	} catch (Exception e) {
    		
			logger.error("delete 分解设备并行加工  error by Controller ,exception:{}", e.getMessage());
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("delete end!");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/check/det.do")
	public AjaxResponder check(@RequestParam(required=true) String id,@RequestParam(required=true)String workcenterId,@RequestParam(required=true)String workshopId) {
		AjaxResponder result = null;
		logger.info("check start!");
    	try {
    		MesPlanDevice device = mesPlanDeviceService.get(id);
    		if (StringUtils.isEmpty(device.getDispatchStatus()) || "0".equals(device.getDispatchStatus())){
    			device.setDispatchStatus("10");
    			int rowCnt = 0;
    			//整理车间不排程,直接修改状态
    			if (MesPlanDevice.WORKSHOP_ARRANGE.equals(workshopId)){
    				device.setRealBeginDate(device.getPlanBeginDate());
    				device.setRealEndDate(device.getPlanEndDate());
    				device.setScheduleStatus("10");
    				mesPlanDeviceService.updateArrangeDispatchStatus(device);
    				rowCnt = 1;
    			}else{
    				rowCnt = mesPlanDeviceService.updateDispatchStatus(device);
    			}
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "计划状态下发成功! "  , rowCnt);
    		}
    		else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "计划状态不正确,不能下发! "  , null);
    		}
    	} catch (Exception e) {
			logger.error("check 计划状态下发 error by Controller ,exception:{}", e.getMessage());
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("check end!");
		return result;
	}
	
	@RequestMapping(value = "/batchfinish/det.do")
	@ResponseBody
	public AjaxResponder batchFinishDet(String id,@RequestBody List<MesPlanDevice> mesPlanDeviceList, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		AjaxResponder result = null;
		logger.info("batchFinishDet start!");
		try{
			mesPlanDeviceService.finishBatchDet(mesPlanDeviceList,id);
			MesPlanDeviceSheet mesPlanDeviceSheet = mesPlanDeviceService.getSheet(id);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! ", mesPlanDeviceSheet);
		}catch (Exception ex){
			logger.error("checkBatchDet error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("batchFinishDet end!");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/cancel/det.do")
	public AjaxResponder cancel(@RequestParam(required=true) String id,@RequestParam(required=true)String workcenterId,@RequestParam(required=true)String goodsId,@RequestParam(required=true)String workshopId) {
		AjaxResponder result = null;
		logger.info("cancel start!");
    	try {
    		MesPlanDevice device = mesPlanDeviceService.get(id);
    		if ("10".equals(device.getDispatchStatus())){
    			//整理车间校验 
    			if (MesPlanDevice.WORKSHOP_ARRANGE.equals(workshopId) 
    					&& StringUtils.isNotEmpty(device.getScheduleStatus()) 
    					&& Integer.parseInt(device.getScheduleStatus())>10
    					){
    				result =  AjaxResponder.getInstance(Boolean.FALSE ,  "计划已排程,不能取消! "  , null);
    				return result;
    			}
    			device.setDispatchStatus("0");
    			int rowCnt = mesPlanDeviceService.updateDispatchStatus(device);
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "取消计划成功! "  , rowCnt);
    		}
    		else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "计划状态不正确,不能下发! "  , null);
    		}
    	} catch (Exception e) {
			logger.error("cancel  error by Controller ,exception:{}", e.getMessage());
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("cancel end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(MesPlanDevice mesPlanDevice,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("findPage start!");
		PageInfo<MesPlanDevice> pageItems =null;
		AjaxResponder result =null;
	    try {
	   		pageItems=mesPlanDeviceService.findPage(pageno,pagesize,mesPlanDevice);
	   		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , pageItems);
	    } catch (Exception e) {
			logger.error(" findPage error by MesPlanDeviceController ,exception:{}", e);
			e.printStackTrace();
			result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
	    }
	    logger.info("findPage end!");
	    return result;
    }
	
	@RequestMapping(value = "/batchtechnic/det.do")
	@ResponseBody
	public AjaxResponder checkBatchTechnic(String id,@RequestBody List<MesPlanDevice> mesPlanDeviceList, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		AjaxResponder result = null;
		logger.info("checkBatchTechnic start!");
		try{
			mesPlanDeviceService.updateNoTechnicBatch(mesPlanDeviceList);
			MesPlanDeviceSheet mesPlanDeviceSheet = mesPlanDeviceService.getSheet(id);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! ", mesPlanDeviceSheet);
		}catch (Exception ex){
			logger.error("checkBatchTechnic error by MesPlanDeviceController ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("checkBatchTechnic end!");
		return result;
	}
	
	
	@RequestMapping(value = "/batchcheck/det.do")
	@ResponseBody
	public AjaxResponder checkBatchDet(@RequestParam(required=true)String pid,@RequestBody List<MesPlanDevice> mesPlanDeviceList,@RequestParam(required=true)String workcenterId,@RequestParam(required=true)String workshopId, Model model) throws Exception  {
		logger.info("checkBatchDet start!");
		AjaxResponder result = null;
		try{
			
			mesPlanDeviceService.checkBatchDet(mesPlanDeviceList, workshopId,workcenterId);
	 
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! "  , null);
		}catch (Exception ex){
			logger.error("checkBatchDet error by MesPlanShiftSheetController ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("checkBatchDet end!");
		return result;
	}
	
	
}