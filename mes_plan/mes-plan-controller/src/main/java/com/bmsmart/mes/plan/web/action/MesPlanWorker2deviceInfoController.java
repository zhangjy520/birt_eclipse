/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;


import com.bmsmart.mes.plan.domain.po.MesPlanWorker2deviceInfo;
import com.bmsmart.mes.plan.service.MesPlanWorker2deviceInfoService;

/**
 * 工人设备Controller
 * @author zhouqizhi
 * @version 2017-10-24
 */
@Controller
@RequestMapping(value = "/plan/mesPlanWorker2deviceInfo")
public class MesPlanWorker2deviceInfoController extends BaseController {

	@Autowired
	private MesPlanWorker2deviceInfoService mesPlanWorker2deviceInfoService;
	
	@RequestMapping(value = "/get")
	public MesPlanWorker2deviceInfo get(@RequestParam(required=false) String id) {
		MesPlanWorker2deviceInfo entity = null;
		logger.info("get start!");
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanWorker2deviceInfoService.get(id);
		}
		if (entity == null){
			entity = new MesPlanWorker2deviceInfo();
		}
		logger.info("get end!");
		return entity;
	}
	
	@RequestMapping(value = {"/findList/index.do"})
	@ResponseBody
	public AjaxResponder findList(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanWorker2deviceInfo> list = mesPlanWorker2deviceInfoService.findList(mesPlanWorker2deviceInfo);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanWorker2deviceInfoController ,exception:{}", e);
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		 
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "form")
	public String form(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo, Model model) {
		model.addAttribute("mesPlanWorker2deviceInfo", mesPlanWorker2deviceInfo);
		return "mes/plan/mesPlanWorker2deviceInfoForm";
	}

	@RequestMapping(value = "/save/index.do")
	@ResponseBody
	public AjaxResponder save(MesPlanWorker2deviceInfo mesPlanWorker2deviceInfo, Model model) {
		logger.info("save start!");
		AjaxResponder result = null;
		if (StringUtils.isBlank(mesPlanWorker2deviceInfo.getWorktypeId()) 	||
			StringUtils.isBlank(mesPlanWorker2deviceInfo.getShiftId())	||
			StringUtils.isBlank(mesPlanWorker2deviceInfo.getDeviceId())	||
			StringUtils.isBlank(mesPlanWorker2deviceInfo.getUserId())
			) {
			 return AjaxResponder.getInstance(Boolean.FALSE, "设备,人员,工种,班次参数不能为空! ", null);
		}
		try {
			mesPlanWorker2deviceInfoService.save(mesPlanWorker2deviceInfo);
			result = AjaxResponder.getInstance(Boolean.TRUE, "保存工人设备成功! ", null);
		} catch (Exception e) {
			logger.error("save 工人设备 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		 
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public ModelAndView delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		mesPlanWorker2deviceInfoService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除工人设备成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete 工人设备  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
		ModelAndView mv = new ModelAndView("response");
		mv.addObject("result", result);
		logger.info("delete end!");
		return mv;
	}
	
	@ResponseBody
    @RequestMapping("/findPage")
    public ModelAndView findPage(@RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request, HttpServletResponse response) {
		logger.info("findPage start!");
		Map<String, String> datas = WebUtil.request2Map(request);
		ModelAndView mv = new ModelAndView("response");
		PageInfo<MesPlanWorker2deviceInfo> pageItems = null;
		AjaxResponder result = null;
		try {
			pageItems = mesPlanWorker2deviceInfoService.findPage(pageNum, pageSize, datas);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanWorker2deviceInfoController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		mv.addObject("result", result);
		logger.info("findPage end!");
		return mv;
    }

}