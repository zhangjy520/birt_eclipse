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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;


import com.bmsmart.mes.plan.domain.po.MesPlanTechnicWorktype;
import com.bmsmart.mes.plan.service.MesPlanTechnicWorktypeService;

/**
 * 工种Controller
 * @author zhouqizhi
 * @version 2017-10-18
 */
@Controller
@RequestMapping(value = "/plan/mesPlanTechnicWorktype")
public class MesPlanTechnicWorktypeController extends BaseController {

	@Autowired
	private MesPlanTechnicWorktypeService mesPlanTechnicWorktypeService;
	
	@RequestMapping(value = "/get")
	public MesPlanTechnicWorktype get(@RequestParam(required=false) String id) {
		MesPlanTechnicWorktype entity = null;
		logger.info("get start!");
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanTechnicWorktypeService.get(id);
		}
		if (entity == null){
			entity = new MesPlanTechnicWorktype();
		}
		logger.info("get end!");
		return entity;
	}
	
	@RequestMapping(value = {"/findList"})
	public AjaxResponder findList(MesPlanTechnicWorktype mesPlanTechnicWorktype, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanTechnicWorktype> list = mesPlanTechnicWorktypeService.findList(mesPlanTechnicWorktype);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error("findList error by MesPlanTechnicWorktypeController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "form")
	public String form(MesPlanTechnicWorktype mesPlanTechnicWorktype, Model model) {
		model.addAttribute("mesPlanTechnicWorktype", mesPlanTechnicWorktype);
		return "mes/plan/mesPlanTechnicWorktypeForm";
	}

	@RequestMapping(value = "save")
	public ModelAndView save(MesPlanTechnicWorktype mesPlanTechnicWorktype, Model model) {
		logger.info("save start!");
		if (!beanValidator(model, mesPlanTechnicWorktype)) {
			// return form(testData, model);
		}
		AjaxResponder result = null;
		try {
			mesPlanTechnicWorktypeService.save(mesPlanTechnicWorktype);
			result = AjaxResponder.getInstance(Boolean.TRUE, "保存测试成功! ", null);
		} catch (Exception e) {
			logger.error("save 测试 error by Controller ,exception:{}", e);
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		ModelAndView mv = new ModelAndView("response");
		mv.addObject("result", result);
		logger.info("save end!");
		return mv;
	}
	@ResponseBody
	@RequestMapping(value = "delete")
	public ModelAndView delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		mesPlanTechnicWorktypeService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除测试成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete 测试  error by Controller ,exception:{}", e);
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
		PageInfo<MesPlanTechnicWorktype> pageItems = null;
		AjaxResponder result = null;
		try {
			pageItems = mesPlanTechnicWorktypeService.findPage(pageNum, pageSize, datas);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanTechnicWorktypeController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		mv.addObject("result", result);
		logger.info("findPage end!");
		return mv;
	}
}