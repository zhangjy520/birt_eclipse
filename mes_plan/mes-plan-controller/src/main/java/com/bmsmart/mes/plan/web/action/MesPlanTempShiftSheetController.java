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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanSchedule;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftDet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftUser;
import com.bmsmart.mes.plan.domain.po.MesPlanTempTaskDet;
import com.bmsmart.mes.plan.export.PlanTaskGainProvider;
import com.bmsmart.mes.plan.export.PlanTaskGetProvider;
import com.bmsmart.mes.plan.export.PlanWorkFinishedProvider;
import com.bmsmart.mes.plan.service.MesPlanShiftDetService;
import com.bmsmart.mes.plan.service.MesPlanShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanShiftUserService;
import com.bmsmart.mes.plan.service.MesPlanTempShiftHeadService;
import com.bmsmart.mes.plan.service.MesPlanTempTaskDetService;

/**
 * 排班表头Controller
 * @author zhouqz
 * @version 2017-09-04
 */
@Controller
@RequestMapping(value = "/plan/mesPlanTempShift")
public class MesPlanTempShiftSheetController extends BaseController {
	@Autowired
	private MesPlanTempShiftHeadService mesPlanTempShiftHeadService;
	
	@Autowired
	private MesPlanTempTaskDetService mesPlanTempTaskDetService;
 
	//@Autowired
	//private MesPlanShiftUserService mesPlanShiftUserService;
	//@Autowired
	//private MesPlanShiftDetService mesPlanShiftDetService;
//	private PlanTaskGetProvider planTaskGetProvider;
//	private PlanTaskGainProvider planTaskGainProvider;
//	private PlanWorkFinishedProvider planWorkFinishedProvider;
	
	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		AjaxResponder result =null;
		MesPlanTempShiftHead entity = null;
		logger.info("get start!");
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanTempShiftHeadService.getHead(id);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
			
		}else{
			result = AjaxResponder.getInstance(Boolean.FALSE , "查询失败,id可能不存在" , null);
		}
		logger.info("get end!");
		return result;
	}
	
	
//	@RequestMapping(value = "/check/index.do")
//	@ResponseBody
//	public AjaxResponder check(@RequestParam(required=false) String id) {
//		AjaxResponder result = null;
//		MesPlanShiftHead head = null;
//		logger.info("check start!");
//		try{
//			if (StringUtil.isNotBlank(id)) {
//				head = mesPlanTempShiftHeadService.getHead(id);
//				if (StringUtils.isBlank(head.getSheetStatus()) || "0".equals(head.getSheetStatus())) {
//					head.setSheetStatus("10");
//					int rowCnt = mesPlanShiftHeadService.updateSheetStatus(head);
//					if (rowCnt == 1) {
//						result = AjaxResponder.getInstance(Boolean.TRUE, "确认成功", head);
//					} else {
//						result = AjaxResponder.getInstance(Boolean.FALSE, "更新了0条记录", head);
//					}
//				} else {
//					result = AjaxResponder.getInstance(Boolean.FALSE, "单据状态不正确,请刷新后重新操作!", head);
//				}
//	
//			} else {
//				result = AjaxResponder.getInstance(Boolean.FALSE, "确认失败,id可能不存在!", null);
//			}
//		} catch (Exception e) {
//			logger.error("check error by Controller ,exception:{}", e);
//			e.printStackTrace();
//			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
//		}
//		logger.info("check end!");
//		return result;
//	}
	
	
	@RequestMapping(value = "/getsheet/index.do")
	@ResponseBody
	public AjaxResponder getSheet(@RequestParam(required=false)String id,@RequestParam(required=false)String sheetId) {
		AjaxResponder result = null;
		logger.info("getSheet start!");
		MesPlanTempShiftSheet shiftSheet = null;
		try {
			if (StringUtil.isNotBlank(id)) {
				shiftSheet = mesPlanTempShiftHeadService.getSheetById(id);
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", shiftSheet);
			} else if (StringUtil.isNotBlank(sheetId)) {
				shiftSheet = mesPlanTempShiftHeadService.getSheetBySheetId(sheetId);
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", shiftSheet);
			} else {
				result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败,id可能不存在", null);
			}
		} catch (Exception e) {
			logger.error("getSheet error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("getSheet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/getsheetbyuk/index.do")
	@ResponseBody
	public AjaxResponder getSheet(MesPlanTempShiftHead mesPlanTempShiftHead) {
		logger.info("getSheet start para=object!");
		AjaxResponder result =null;
		MesPlanTempShiftSheet shiftSheet = null;
		shiftSheet = mesPlanTempShiftHeadService.getSheetByUk(mesPlanTempShiftHead);
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功" , shiftSheet);
		logger.info("getSheet end!");
		return result;
	}
	
	
	@RequestMapping(value = {"/findList"})
	@ResponseBody
	public AjaxResponder findList(MesPlanTempShiftHead mesPlanTempShiftHead, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start para=object!");
		try {
			List<MesPlanTempShiftHead> list = mesPlanTempShiftHeadService.findList(mesPlanTempShiftHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanShiftSheetController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList start para=object!");
		return result;
	}


	@RequestMapping(value = "save")
	@ResponseBody
	public AjaxResponder save(MesPlanTempShiftHead mesPlanTempShiftHead, Model model) {
		if (!beanValidator(model, mesPlanTempShiftHead)) {
			// return form(testData, model);
		}
		AjaxResponder result = null;
		logger.info("save start!");
		try {
			mesPlanTempShiftHeadService.save(mesPlanTempShiftHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "保存排班表头成功! ", null);
		} catch (Exception e) {
			logger.error("save 排班表头 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("save end!");
		return result;
	}
	
	
	@RequestMapping(value = "/batchsave/det.do")
	@ResponseBody
	public AjaxResponder saveBatchDet(MesPlanTempShiftHead mesPlanTempShiftHead,@RequestBody List<MesPlanTempShiftUser> mesPlanTempShiftUserList, Model model) {
		//List<MesPlanShiftDet> mesPlanShiftDetList
		logger.info("saveBatchDet start!");
		if (!beanValidator(model, mesPlanTempShiftHead)){
		//	return form(testData, model);
		}
		AjaxResponder result = null;
		if (mesPlanTempShiftHead == null 
				|| StringUtil.isBlank(mesPlanTempShiftHead.getWorkshopId()) 
				|| StringUtil.isBlank(mesPlanTempShiftHead.getShiftId())
				|| StringUtil.isBlank(mesPlanTempShiftHead.getShiftUser())
				|| StringUtil.isBlank(mesPlanTempShiftHead.getWorkcenterId())
				|| mesPlanTempShiftHead.getShiftDate() == null){
			result = AjaxResponder.getInstance(Boolean.FALSE , "参数传入为空"  , null);
			return result;
		}
    	try {
    		String id= mesPlanTempShiftHeadService.saveSheet(mesPlanTempShiftHead,mesPlanTempShiftUserList);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存生产计划排程成功! "  , id);
		} catch (Exception e) {
			logger.error("save 生产计划排程 error by Controller ,exception:{}", e.getMessage());
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
    		mesPlanTempShiftHeadService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除排班表头成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete 排班表头  error by Controller ,exception:{}", e.getMessage());
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("delete end!");
		return result;
	}
	
	@ResponseBody
    @RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
	 public AjaxResponder findPage(            
				 MesPlanTempShiftHead mesPlanTempShiftHead,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result = null;
		logger.info("findPage start!");
		try {
			PageInfo<MesPlanTempShiftHead> pageItems = mesPlanTempShiftHeadService.findPage(pageno, pagesize, mesPlanTempShiftHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanShiftSheetController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
	}
	
	@RequestMapping(value = "/batchcheck/det.do")
	@ResponseBody
	public AjaxResponder checkBatchDet(String sheetId,@RequestBody List<MesPlanTempTaskDet> mesPlanTempTaskDetList, Model model) throws Exception  {
		logger.info("checkBatchDet start!");
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		AjaxResponder result = null;
		try{
			mesPlanTempTaskDetService.checkBatchDet(mesPlanTempTaskDetList, sheetId);
			MesPlanTempShiftSheet mesPlanTempShiftSheet = mesPlanTempShiftHeadService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! "  , mesPlanTempShiftSheet);
		}catch (Exception ex){
			logger.error("checkBatchDet error by MesPlanShiftSheetController ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("checkBatchDet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/batchcancel/det.do")
	@ResponseBody
	public AjaxResponder cancelBatchDet(String sheetId,@RequestBody List<MesPlanTempTaskDet> mesPlanTempTaskDetList, Model model) throws Exception  {
		logger.info("cancelBatchDet start!");
//		if (!beanValidator(model, mesPlanDevice)){
//			return form(testData, model);
//		}
		AjaxResponder result = null;
		try{
			mesPlanTempTaskDetService.updateShiftCancelStatus(mesPlanTempTaskDetList, sheetId);
			MesPlanTempShiftSheet mesPlanTempShiftSheet = mesPlanTempShiftHeadService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量取消成功! "  , mesPlanTempShiftSheet);
		}catch (Exception ex){
			logger.error("cancelBatchDet error by MesPlanShiftSheetController ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("cancelBatchDet end!");
		return result;
	}
	
	@RequestMapping(value = "/batchfinish/det.do")
	@ResponseBody
	public AjaxResponder finishBatchDet(String sheetId,@RequestBody List<MesPlanTempTaskDet> mesPlanShiftDetList, Model model) throws Exception  {
		logger.info("finishBatchDet start!");
//		if (!beanValidator(model, mesPlanDevice)){
//			return form(testData, model);
//		}
		AjaxResponder result = null;
		try{
			mesPlanTempTaskDetService.updateShiftFinishStatus(mesPlanShiftDetList, sheetId);
			MesPlanTempShiftSheet mesPlanTempShiftSheet = mesPlanTempShiftHeadService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! "  , mesPlanTempShiftSheet);
		}catch (Exception ex){
			logger.error("finishBatchDet error by MesPlanShiftSheetController ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("finishBatchDet end!");
		return result;
	}
	
//	@RequestMapping(value = "/validateFinish/index.do")
//	@ResponseBody
//	public AjaxResponder validateFinish(int level,String billNo, Model model) throws Exception  {
//		logger.info("validateFinish start!");
//		if (level <= 0 || StringUtils.isBlank(billNo)){
//			return AjaxResponder.getInstance(Boolean.FALSE ,  "billNo参数不能为空! "  , null);
//		}
//		AjaxResponder result = null;
//		try{
//			int cnt = mesPlanShiftDetService.validateCanFinish(level, billNo);
//			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "取调用条数成功! "  , cnt);
//		}catch (Exception ex){
//			logger.error("finishBatchDet error by MesPlanShiftSheetController ,exception:{}", ex);
//			ex.printStackTrace();
//			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
//		}
//		logger.info("validateFinish end!");
//		return result;
//	}
	
//	@RequestMapping(value = "/getErpBillDetail/index.do")
//	@ResponseBody
//	public AjaxResponder getErpBillDetail(String erpBillNo, Model model) throws Exception  {
//		logger.info("getErpBillDetail start!");
//		AjaxResponder result = null;
//		try{
//			Map<String,Object> rtn = mesPlanShiftDetService.getErpBillDetail(erpBillNo);
//			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "返回调用条数成功! "  , rtn);
//		}catch (Exception ex){
//			logger.error("getErpBillDetail error by MesPlanShiftSheetController ,exception:{}", ex);
//			ex.printStackTrace();
//			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
//		}
//		logger.info("getErpBillDetail end!");
//		return result;
//	}
	
}