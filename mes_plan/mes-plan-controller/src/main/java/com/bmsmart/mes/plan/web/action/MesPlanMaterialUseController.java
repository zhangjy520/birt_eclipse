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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;


import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseService;

/**
 * mes_plan_material_useController 领用单生成
 * @author zhouqizhi
 * @version 2017-09-11
 */
@Controller
@RequestMapping(value = "/plan/use")
public class MesPlanMaterialUseController extends BaseController {
	@Autowired
	private MesPlanMaterialUseService mesPlanMaterialUseService;
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		logger.info("get start!");
		MesPlanMaterialUse entity = null;
		AjaxResponder result =null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanMaterialUseService.get(id);
		}
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		logger.info("get end!");
		return result;
	}
	
	@RequestMapping(value = {"/findList/index.do"})
	@ResponseBody
	public AjaxResponder findList(MesPlanMaterialUse mesPlanMaterialUse, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanMaterialUse> list = mesPlanMaterialUseService.findList(mesPlanMaterialUse);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanMaterialUseController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}
	
	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxResponder save(MesPlanMaterialUse mesPlanMaterialUse, Model model) {
		logger.info("save start!");
		if (!beanValidator(model, mesPlanMaterialUse)){
			//	return form(testData, model);
		}
		AjaxResponder result = null;
    	try {
    		mesPlanMaterialUseService.save(mesPlanMaterialUse);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存mes_plan_material_use成功! "  , null);
		} catch (Exception e) {
			logger.error("save mes_plan_material_use error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("save end!");
    	return result;
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public AjaxResponder delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		mesPlanMaterialUseService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除mes_plan_material_use成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete mes_plan_material_use  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("delete end!");
		return result;
	}
	
	@ResponseBody
    @RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(MesPlanMaterialUse mesPlanMaterialUse,@RequestBody String postData,
    		@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		PageInfo<MesPlanMaterialUse> pageItems = null;
		AjaxResponder result = null;
		logger.info("findPage start!");
		try {
			pageItems = mesPlanMaterialUseService.findPage(pageno, pagesize, mesPlanMaterialUse);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanMaterialUseController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
    }

}