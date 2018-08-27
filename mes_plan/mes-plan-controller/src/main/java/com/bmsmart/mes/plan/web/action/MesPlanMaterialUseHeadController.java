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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUse;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseDet;
import com.bmsmart.mes.plan.domain.po.MesPlanMaterialUseHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseDetService;
import com.bmsmart.mes.plan.service.MesPlanMaterialUseHeadService;

/**
 * mes_plan_material_use_headController
 * @author zhouqizhi
 * @version 2017-09-11
 */
@Controller
@RequestMapping(value = "/plan/materialUseHead")
public class MesPlanMaterialUseHeadController extends BaseController {
	@Autowired
	private MesPlanMaterialUseHeadService mesPlanMaterialUseHeadService;
	@Autowired
	private MesPlanMaterialUseDetService mesPlanMaterialUseDetService;
	
	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		AjaxResponder result =null;
		logger.info("get start!");
		MesPlanMaterialUseHead entity = null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanMaterialUseHeadService.get(id);
		}
		result=AjaxResponder.getInstance(Boolean.TRUE ,  "查询成功"  , entity);
		logger.info("get end!");
		return result;
	}
	
	
	@RequestMapping(value = "/check/index.do")
	@ResponseBody
	public AjaxResponder check(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		MesPlanMaterialUseHead entity = null;
		logger.info("check start!");
		try{
			if (StringUtil.isNotBlank(id)){
				entity = mesPlanMaterialUseHeadService.get(id);
			}
			if (StringUtil.isNotBlank(entity.getStatus()) && "10".equals(entity.getStatus())){
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "单据状态已改变,请重新打开单据查看"  , entity);
			}else{
				entity.setStatus("10");
				int rowCnt = mesPlanMaterialUseHeadService.updateSheetStatus(entity);
				if (rowCnt == 1 ){
					result=AjaxResponder.getInstance(Boolean.TRUE ,  "下发成功"  , entity);
				}else{
					result=AjaxResponder.getInstance(Boolean.FALSE ,  "修改单据失败"  , entity);
				}
			}
		}catch(Exception ex){
			logger.error(" check error by MesPlanMaterialUseController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("check end!");
		return result;
	}
	
	
	@RequestMapping(value = "/getsheet/index.do")
	@ResponseBody
	public AjaxResponder getSheet(@RequestParam(required=false) String id) {
		AjaxResponder result =null;
		logger.info("getSheet start!");
		try{
			MesPlanMaterialUseHead head = null;
			if (StringUtil.isNotBlank(id)){
				head = mesPlanMaterialUseHeadService.get(id);
			}
			MesPlanMaterialUseDet detPara = new MesPlanMaterialUseDet();
			detPara.setSheetId(head.getSheetId());
			List<MesPlanMaterialUseDet> mesPlanMaterialUseDetList = mesPlanMaterialUseDetService.findALLList(detPara);
			head.setMesPlanMaterialUseDetList(mesPlanMaterialUseDetList);
			result=AjaxResponder.getInstance(Boolean.TRUE ,  "查询成功"  , head);
		}catch(Exception ex){
			logger.error("getSheet error by MesPlanMaterialUseController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}	
		logger.info("getSheet end!");
		return result;
	}
	
	
	
	@RequestMapping(value = "/delDetRow/index.do")
	@ResponseBody
	public AjaxResponder delDetRow(@RequestParam(required=false) String id) {
		AjaxResponder result =null;
		logger.info("delDetRow start!");
		try{
			if (StringUtil.isNotBlank(id)){
				  mesPlanMaterialUseDetService.deleteById(id);
			}
			result=AjaxResponder.getInstance(Boolean.TRUE ,  "查询成功"  , null);
		}catch(Exception ex){
			logger.error("delDetRow error by MesPlanMaterialUseController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("delDetRow end!");
		return result;
	}
	
	
	@RequestMapping(value = {"/findList"})
	@ResponseBody
	public AjaxResponder findList(MesPlanMaterialUseHead mesPlanMaterialUseHead, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanMaterialUseHead> list = mesPlanMaterialUseHeadService.findList(mesPlanMaterialUseHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanMaterialUseHeadController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "/save/index.do")
	@ResponseBody
	public AjaxResponder save(MesPlanMaterialUseHead mesPlanMaterialUseHead, Model model) {
		if (!beanValidator(model, mesPlanMaterialUseHead)){
		//	return form(testData, model);
		}
		AjaxResponder result = null;
		logger.info("save start!");
    	try {
			MesPlanMaterialUseHead head  = mesPlanMaterialUseHeadService.get(mesPlanMaterialUseHead.getId());
			
			if (StringUtil.isNotBlank(head.getStatus()) && "10".equals(head.getStatus())){
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "单据状态已改变,请重新打开单据查看"  , null);
			}else{
				mesPlanMaterialUseHeadService.save(mesPlanMaterialUseHead);
        		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存成功! "  , null);
			}
		} catch (Exception e) {
			logger.error("save mes_plan_material_use_head error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("save end!");
		return result;
	
	}
	
	@RequestMapping(value = "/batchsave/det.do")
	@ResponseBody
	public AjaxResponder saveBatchDet(MesPlanMaterialUseHead mesPlanMaterialUseHead,@RequestBody List<MesPlanMaterialUseDet> mesPlanMaterialUseDetList, Model model) {
		if (!beanValidator(model, mesPlanMaterialUseHead)){
		//	return form(testData, model);
		}
		AjaxResponder result = null;
		logger.info("saveBatchDet start!");
		if (mesPlanMaterialUseHead == null 
				|| StringUtil.isBlank(mesPlanMaterialUseHead.getWorkshopId()) 
				|| mesPlanMaterialUseHead.getUseDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
    	try {
    		String id= mesPlanMaterialUseHeadService.saveSheet(mesPlanMaterialUseHead,mesPlanMaterialUseDetList);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存生产计划排程成功! "  , id);
		} catch (Exception e) {
			logger.error("save 生产计划排程 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("saveBatchDet end!");
    	return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public AjaxResponder delete(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
    	try {
    		mesPlanMaterialUseHeadService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除mes_plan_material_use_head成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete mes_plan_material_use_head  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("delete end!");
		return result;
	}
	
	@ResponseBody
    @RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(MesPlanMaterialUseHead mesPlanMaterialUseHead,@RequestBody String postData,
    		@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		PageInfo<MesPlanMaterialUseHead> pageItems = null;
		AjaxResponder result = null;
		logger.info("findPage start!");
		try {
			pageItems = mesPlanMaterialUseHeadService.findPage(pageno, pagesize, mesPlanMaterialUseHead);
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