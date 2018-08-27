/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.base.util.web.WebUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDay;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceDaySheet;
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceInfo;
import com.bmsmart.mes.plan.service.MesPlanDeviceDayService;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;

/**
 * mes_plan_device_dayController
 * @author zhouqz
 * @version 2017-08-22
 */
@Controller
@RequestMapping(value = "/plan/mesPlanDeviceDay")
public class MesPlanDeviceDayController extends BaseController {

	@Autowired
	private MesPlanDeviceDayService mesPlanDeviceDayService;
	
	@Autowired
	private MesPlanDeviceService mesPlanDeviceService;
	
	
	
	@RequestMapping(value = "/get")
	public AjaxResponder get(@RequestParam(required=false) String id) {
		logger.info("get start! para={0}",id);
		MesPlanDeviceDay entity = null;
		AjaxResponder result =null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanDeviceDayService.get(id);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		}
		if (entity == null){
			entity = new MesPlanDeviceDay();
			result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败"  ,entity);
		}
		logger.info("get end!");
		return result;
	}
	
	@RequestMapping(value = {"/findList/index.do"})
	@ResponseBody
	public AjaxResponder findList(MesPlanDeviceDay mesPlanDeviceDay, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result =null;
		logger.info("findList start!");
		try {
			MesPlanDeviceDaySheet sheet = new MesPlanDeviceDaySheet();
		   List<MesPlanDeviceDay> list = mesPlanDeviceDayService.findList(mesPlanDeviceDay); 
		   sheet.setMesPlanDeviceInfoList(mesPlanDeviceDayService.getMesPlanDeviceInfoList(mesPlanDeviceDay.getWorkcenterId())); 
		   sheet.setMesPlanDeviceDayList(list);
		   result = AjaxResponder.getInstance(Boolean.TRUE ,"查询成功", sheet);
       } catch (Exception e) {
			logger.error(" findList error by MesPlanDeviceDayController ,exception:{}", e);
			e.printStackTrace();
			result=AjaxResponder.getInstance(Boolean.FALSE ,"查询失败", null);
	   }
		logger.info("findList end!");
		return result;
	}
	
	@RequestMapping(value = {"/findArrangeList/index.do"})
	@ResponseBody
	public AjaxResponder findArrangeList(MesPlanDevice mesPlanDevice,String workcenterId, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result =null;
		logger.info("findList start!");
		if (StringUtils.isEmpty(mesPlanDevice.getSrcBillNo()) || StringUtils.isEmpty(workcenterId)) {
			logger.error("厂级调度单号不能为空!");
			return AjaxResponder.getInstance(Boolean.FALSE, "厂级调度单号不能为空!", null);
		}
		
		try {
			MesPlanDeviceDaySheet sheet = new MesPlanDeviceDaySheet();
		   List<MesPlanDevice> list = mesPlanDeviceService.findList(mesPlanDevice); 
		   
		   sheet.setMesPlanDeviceInfoList(mesPlanDeviceDayService.getMesPlanDeviceInfoList(workcenterId)); //所有设备
		   sheet.setMesPlanDeviceList(list);//班次已占用设备
		   result = AjaxResponder.getInstance(Boolean.TRUE ,"查询成功", sheet);
       } catch (Exception e) {
			logger.error(" findList error by MesPlanDeviceDayController ,exception:{}", e);
			e.printStackTrace();
			result=AjaxResponder.getInstance(Boolean.FALSE ,"查询失败", null);
	   }
		logger.info("findList end!");
		return result;
	}
	
	
	@RequestMapping(value = {"/findDevice2WorkGroupList/index.do"})
	@ResponseBody
	public AjaxResponder findDevice2WorkGroupList(String workcenterId, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result =null;
		logger.info("findDevice2WorkGroupList start!");
		try {
			List<MesPlanDeviceInfo> mesPlanDeviceInfoList=  mesPlanDeviceDayService.getMesPlanDeviceInfoList(workcenterId);
		   result = AjaxResponder.getInstance(Boolean.TRUE ,"查询成功", mesPlanDeviceInfoList);
		} catch (Exception e) {
			logger.error(" findDevice2WorkGroupList error by MesPlanDeviceDayController ,exception:{}", e);
			e.printStackTrace();
			result=AjaxResponder.getInstance(Boolean.FALSE ,"查询失败", null);
		}
		logger.info("findDevice2WorkGroupList end!");
		return result;
	}

//	@RequestMapping(value = "form")
//	public String form(MesPlanDeviceDay mesPlanDeviceDay, Model model) {
//		model.addAttribute("mesPlanDeviceDay", mesPlanDeviceDay);
//		return "mes/plan/mesPlanDeviceDayForm";
//	}
//
//	@RequestMapping(value = "save")
//	public AjaxResponder save(MesPlanDeviceDay mesPlanDeviceDay, Model model) {
//		if (!beanValidator(model, mesPlanDeviceDay)){
//			//	return form(testData, model);
//			}
//			AjaxResponder result = null;
//	    	try {
//			mesPlanDeviceDayService.save(mesPlanDeviceDay);
//			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存mes_plan_device_day成功! "  , null);
//		} catch (Exception e) {
//			logger.error("save mes_plan_device_day error by Controller ,exception:{}", e.getMessage());
//			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
//		}
//		return result;
//	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public AjaxResponder delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
		try {
			mesPlanDeviceDayService.deleteById(id);
			result = AjaxResponder.getInstance(Boolean.TRUE, "删除mes_plan_device_day成功! ", null);
		} catch (Exception e) {
			logger.error("delete mes_plan_device_day  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("delete end!");
		return result;
	}
	
	@ResponseBody
    @RequestMapping("/findPage")
    public AjaxResponder findPage(MesPlanDeviceDay mesPlanDeviceDay,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response){ 
		logger.info("findPage start!");
		PageInfo<MesPlanDeviceDay> pageItems = null;
		AjaxResponder result = null;
		try {
			pageItems = mesPlanDeviceDayService.findPage(pageno, pagesize, mesPlanDeviceDay);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanDeviceDayController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
    }

}