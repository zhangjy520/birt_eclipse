/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.validator.BeanValidators;
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
import com.bmsmart.mes.plan.domain.po.MesPlanDeviceSheet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchBom;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchDet;
import com.bmsmart.mes.plan.domain.po.MesPlanDispatchHead;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.service.MesPlanDeviceService;
import com.bmsmart.mes.plan.service.MesPlanDispatchBomService;
import com.bmsmart.mes.plan.service.MesPlanDispatchDetService;
import com.bmsmart.mes.plan.service.MesPlanDispatchHeadService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.impl.MesPlanDispatchHeadServiceImpl;
/**
 * 厂级调度单头Controller
 * @author 周奇志
 * @version 2017-08-07
 */
@Controller
@RequestMapping(value = "/plan/mesPlanDispatch")
public class MesPlanDispatchController extends BaseController {
	
	@Autowired
	private MesPlanDispatchHeadService mesPlanDispatchHeadService;
	
	@Autowired
	private MesPlanDispatchDetService mesPlanDispatchDetService;
	
	@Autowired
	private MesPlanDispatchBomService mesPlanDispatchBomService;
	
	@Autowired
	private MesPlanDeviceService mesPlanDeviceService;
	
	@RequestMapping(value = "/getsheet/index.do")
	@ResponseBody
	public AjaxResponder getSheet(MesPlanDispatchHead headSheetPara) {
		logger.info("getSheet start!");
		AjaxResponder result =null;
		MesPlanDispatchHead entity = null;
		if (headSheetPara != null && StringUtil.isNotBlank(headSheetPara.getSheetId())){
			entity = mesPlanDispatchHeadService.getSheet(headSheetPara);
		}
		if (entity == null){
			entity = new MesPlanDispatchHead();
		}
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		logger.info("getSheet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/getsheet/det.do")
	@ResponseBody
	public AjaxResponder getSheetDet(String id) {
		logger.info("getSheetDet start! para={}!",id);
		AjaxResponder result =null;
		MesPlanDispatchDet entity = null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanDispatchDetService.get(id);
		}
		if (entity == null){
			entity = new MesPlanDispatchDet();
		}
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		logger.info("getSheetDet end!");
		return result;
	}
	
	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(String id) {
		logger.info("get start! para={}!",id);
		AjaxResponder result = null;
		MesPlanDispatchHead entity = null;
		if (StringUtil.isNotBlank(id)) {
			entity = mesPlanDispatchHeadService.get(id);
			if (entity != null) {
				entity = mesPlanDispatchHeadService.getSheet(entity);
			}
		}
		if (entity == null) {
			entity = new MesPlanDispatchHead();
		}
		result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", entity);
		logger.info("get end!");
		return result;
	}
	
	@RequestMapping(value = "/getDet/index.do")
	@ResponseBody
	public AjaxResponder getDet(String id) {
		logger.info("getDet start! para={}!",id);
		AjaxResponder result =null;
		MesPlanDispatchDet entity = null;
		if (StringUtil.isNotBlank(id)){
			entity = mesPlanDispatchDetService.get(id);
		}
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , entity);
		logger.info("getDet end!");
		return result;
	}
	
	@ResponseBody
    @RequestMapping(value = "/findSheetPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findSheetPage(            
    		MesPlanDispatchHead headPara,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		// Map<String, String> datas = WebUtil.request2Map(request);
		logger.info("findSheetPage start!");
		PageInfo<MesPlanDispatchHead> pageItems = null;
		AjaxResponder result = null;
		try {
			pageItems = mesPlanDispatchHeadService.findPage(pageno, pagesize, headPara);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findSheetPage end!");
		return result;
    }
	
	@ResponseBody
    @RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(            
    		MesPlanDispatchDet detPara,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
    	//Map<String, String> datas = WebUtil.request2Map(request);
		logger.info("findPage start!");
		PageInfo<MesPlanDispatchDet> pageItems = null;
		AjaxResponder result = null;
		try {
			pageItems = mesPlanDispatchDetService.findPage(pageno, pagesize, detPara);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
    }
	
		
	@RequestMapping(value = {"/findList"})
	@ResponseBody
	public AjaxResponder findList(MesPlanDispatchHead mesPlanDispatchHead, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanDispatchHead> list = mesPlanDispatchHeadService.findList(mesPlanDispatchHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanDispatchHeadController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "form")
	public String form(MesPlanDispatchHead mesPlanDispatchHead, Model model) {
		model.addAttribute("mesPlanDispatchHead", mesPlanDispatchHead);
		return "mes/plan/mesPlanDispatchHeadForm";
	}

	@RequestMapping(value = "/save/index.do")
	@ResponseBody
	public AjaxResponder save(MesPlanDispatchHead mesPlanDispatchHead, Model model) {
		logger.info("save start!");
		AjaxResponder result = null;
		if (!beanValidator(model, mesPlanDispatchHead)) {
			result = AjaxResponder.getInstance(Boolean.TRUE, model.asMap().get("message").toString(), null);
			return result;
		}

		try {
			if (StringUtils.isBlank(mesPlanDispatchHead.getSheetStatus())) {
				mesPlanDispatchHead.setSheetStatus(MesPlanDispatchHead.SHEET_STATUS_NEW);
			}

			mesPlanDispatchHeadService.save(mesPlanDispatchHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "保存厂级调度单头成功! ", null);
		} catch (Exception e) {
			logger.error("save 厂级调度单头 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("save end!");
		return result;
	}
	
	
	@RequestMapping(value = "/check/index.do")
	@ResponseBody
	public AjaxResponder check(MesPlanDispatchHead mesPlanDispatchHead, Model model) {
		AjaxResponder result = null;
		logger.info("check start!");
		try {
			int rowCnt = mesPlanDispatchHeadService.updateSheetStatusById(mesPlanDispatchHead);
			if (rowCnt == 1) {
				result = AjaxResponder.getInstance(Boolean.TRUE, "下发厂级调度单头成功! ", null);
			} else {
				result = AjaxResponder.getInstance(Boolean.FALSE, "调度单据已被其他人操作，请重新打开查看! ", null);
			}

		} catch (Exception e) {
			logger.error("check 厂级调度单头 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("check end!");
		return result;
	}
	
	@Override
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		if (super.beanValidator(model, object)){
			try{
				if (object == null){
					addMessage(model, "传入对象为空!");
					return false;
				}
				MesPlanDispatchDet det = (MesPlanDispatchDet)object;
				if (det.getDispatchStatus() != "0"){
					addMessage(model, "调度单已经开始车间生产,不能修改!");
				}
				
			}catch(ConstraintViolationException ex){
				List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
				list.add(0, "数据验证失败：");
				addMessage(model, list.toArray(new String[]{}));
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	
	
	@RequestMapping(value = "/save/det.do")
	@ResponseBody
	public AjaxResponder saveDet(MesPlanDispatchDet mesPlanDispatchDet,@RequestBody String postMapStr, Model model) {
		AjaxResponder result = null;
		logger.info("saveDet start!");
		try {
			if (!beanValidator(model, mesPlanDispatchDet)){
			}
			MesPlanDispatchDet retDet = null;
			Map<String,String> postMap = JSON.parse(postMapStr, Map.class) ;
			//String parId= postMap.get("parId");//订单ID
			String sheetId= postMap.get("sheetId");//调度单头
			String erpId= postMap.get("erpId");//订单ID
			String remarks= postMap.get("remarks");
			MesPlanDispatchHead mesPlanDispatchHead = mesPlanDispatchDetService.saveDet(mesPlanDispatchDet, sheetId, erpId,remarks);		
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "调度成功! "  , mesPlanDispatchHead.getId());
		} catch (Exception e) {
			 logger.error("save 厂级调度单头 error by Controller ,exception:{}", e);
			 e.printStackTrace();
			 result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		} 
		logger.info("saveDet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/saveBatch/det2.do")
	@ResponseBody
	public AjaxResponder saveDet2(@RequestParam(required=true)String sheetId ,@RequestBody List<MesPlanDispatchBom> mesPlanDispatchBomList,Model model) {
		AjaxResponder result = null;
		logger.info("saveDet2 start!");
		try {
			if (mesPlanDispatchBomList != null){
				for(MesPlanDispatchBom mesPlanDispatchBom:mesPlanDispatchBomList){
					if (StringUtils.isEmpty(mesPlanDispatchBom.getErpBillNo())||
							StringUtils.isEmpty(mesPlanDispatchBom.getRawGoodsId()) ){
						return  AjaxResponder.getInstance(Boolean.FALSE ,"订单号,商品不能为空", null);
					}
				}
				mesPlanDispatchBomService.saveBatch(mesPlanDispatchBomList);
			}
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "调度成功! ","");
		} catch (Exception e) {
			 logger.error("save 厂级调度单头 error by Controller ,exception:{}", e);
			 e.printStackTrace();
			 result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		} 
		logger.info("saveDet2 end!");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete/index.do")
	public AjaxResponder delete(@RequestParam(required=true) String id,@RequestParam(required=true) String sheetId) {
		AjaxResponder result = null;
		logger.info("delete start!");
		MesPlanDispatchHead head =mesPlanDispatchHeadService.get(id);
		if (StringUtils.isNotEmpty(head.getSheetStatus()) && Integer.parseInt(head.getSheetStatus())>0){
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "单据状态不正确,不能删除! "  , 0);
			return result;
		}
		
    	try {
    		mesPlanDispatchHeadService.deleteSheet(head);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除厂级调度单头成功! "  , 1);
    	} catch (Exception e) {
			logger.error("delete 厂级调度单头  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("delete end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete/det.do")
	public AjaxResponder deleteDet(@RequestParam(required=false) String id) {
		AjaxResponder result = null;
		logger.info("deleteDet start!");
    	try {
    		mesPlanDispatchDetService.deleteById(id);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除厂级调度明细成功! "  , null);
    	} catch (Exception e) {
			logger.error("delete 厂级调度明细  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("deleteDet end!");
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/changeStatus/det.do")
	public AjaxResponder changeStatus(MesPlanDispatchDet det) {
		AjaxResponder result = null;
		logger.info("changeStatus start!");
    	try {
    		if (MesPlanDispatchHead.SHEET_STATUS_CANCEL.equals(det.getDispatchStatus())){
    			//得到最新状态，=10的才能取消
    			MesPlanDispatchDet currentDet =  mesPlanDispatchDetService.get(det.getId());
    			if (Integer.parseInt(currentDet.getDispatchStatus()) > 10){
    				result =  AjaxResponder.getInstance(Boolean.FALSE ,  "订单状态为已开始,不能取消! "  , null);
    				return result;
    			}
    			MesPlanDevice devicePara = new MesPlanDevice();
    			devicePara.setSrcBillNo(currentDet.getBillNo());
    			List<MesPlanDevice> deviceList = mesPlanDeviceService.findList(devicePara);
    			if (deviceList != null && deviceList.size()>0){
    				result =  AjaxResponder.getInstance(Boolean.FALSE ,  "车间已开始调度,不能取消! "  , null);
    				return result;
    			}
    		} 
    		mesPlanDispatchDetService.updateDispatchStatusById(det);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "修改厂级调度明细状态成功! "  , null);
    	} catch (Exception e) {
    		e.printStackTrace();
			logger.error("delete 厂级调度明细  error by Controller ,exception:{}", e);
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("changeStatus end!");
		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/saveremark/index.do")
	public AjaxResponder saveremark(MesPlanDispatchHead head) {
		AjaxResponder result = null;
		logger.info("saveremark start!");
    	try {
			//得到最新状态，小于10才能取消,10为下达状态
			MesPlanDispatchHead currentHead =  mesPlanDispatchHeadService.get(head.getId());
			if (StringUtil.isNotBlank(currentHead.getSheetStatus())
						&& Integer.parseInt(currentHead.getSheetStatus()) >= 10){
				result =  AjaxResponder.getInstance(Boolean.FALSE ,  "订单状态为已开始,不能修改! "  , null);
				return result;
			}
			currentHead.setRemarks(head.getRemarks());
    		mesPlanDispatchHeadService.save(currentHead);
    		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存成功! "  , null);
    	} catch (Exception e) {
			logger.error("保存说明失败  error by Controller ,exception:{}", e);
			e.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
    	logger.info("saveremark end!");
		return result;
	}
	
	
	@ResponseBody
    @RequestMapping("/findPage")
    public ModelAndView findPage(@RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> datas = WebUtil.request2Map(request);
		ModelAndView mv = new ModelAndView("response");
		PageInfo<MesPlanDispatchHead> pageItems = null;
		AjaxResponder result = null;
		logger.info("findPage start!");
		try {
			pageItems = mesPlanDispatchHeadService.findPage(pageNum, pageSize, datas);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanDispatchHeadController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		mv.addObject("result", result);
		logger.info("findPage end!");
		return mv;
    }

	@RequestMapping(value = "/batchfinish/det.do")
	@ResponseBody
	public AjaxResponder checkBatchDet(String sheetId,@RequestBody List<MesPlanDispatchDet> mesPlanDispatchDetList, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		AjaxResponder result = null;
		logger.info("checkBatchDet start!");
		try{
			mesPlanDispatchDetService.checkBatchDet(mesPlanDispatchDetList);
			 
			MesPlanDispatchHead mesPlanDisPatchHead = mesPlanDispatchHeadService.getSheet(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量下发成功! ", mesPlanDisPatchHead);
		}catch (Exception ex){
			logger.error(" checkBatchDet error by MesPlanDispatchHeadController ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("checkBatchDet end!");
		return result;
	}
	
}