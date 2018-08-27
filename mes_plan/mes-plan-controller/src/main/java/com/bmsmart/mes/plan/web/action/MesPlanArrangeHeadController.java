/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.material.export.InventoryProvider;
import com.bmsmart.mes.material.export.bean.ClothInventory;
import com.bmsmart.mes.material.export.request.ClothInventoryPageRequest;
import com.bmsmart.mes.material.export.request.ClothInventoryRequest;
import com.bmsmart.mes.material.export.response.InventoryResponse;
import com.bmsmart.mes.mesJob.domain.po.JobWorkShopCombineCloth;
import com.bmsmart.mes.mesJob.domain.po.JobWorkShopCombineClothItems;
import com.bmsmart.mes.mesJob.export.CombineClothProvider;
import com.bmsmart.mes.mesJob.export.entity.request.CombineClothRequest;
import com.bmsmart.mes.mesJob.export.entity.response.CombineClothResponse;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeDet;
import com.bmsmart.mes.plan.domain.po.MesPlanArrangeHead;
import com.bmsmart.mes.plan.domain.po.MesPlanDevice;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTempShiftHead;
import com.bmsmart.mes.plan.service.MesPlanArrangeDetService;
import com.bmsmart.mes.plan.service.MesPlanArrangeHeadService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;

/**
 * mes_plan_arrange_headController
 * @author zhouqz
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "/plan/mesPlanArrangeSheet")
public class MesPlanArrangeHeadController extends BaseController {

	@Autowired
	private MesPlanArrangeHeadService mesPlanArrangeHeadService;
	@Autowired
	private MesPlanArrangeDetService mesPlanArrangeDetService;
	@Autowired
	private InventoryProvider inventoryProvider;
	@Autowired
    private CombineClothProvider combineClothProvider;
	@Autowired
    private MesPlanMonthService mesPlanMonthService;

	
	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required=false) String id) {
		logger.info("get start! para={0}",id);
		AjaxResponder result =null;
		MesPlanArrangeHead entity = null;
		try{
			if (StringUtil.isNotBlank(id)){
				entity = mesPlanArrangeHeadService.get(id);
				result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  ,entity);
			}
			if (entity == null){
				entity = new MesPlanArrangeHead();
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
	
	@RequestMapping(value = {"/findList/index.do"})
	@ResponseBody
	public AjaxResponder findList(MesPlanArrangeHead mesPlanArrangeHead, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result =null;
		  try {
		   List<MesPlanArrangeHead> list = mesPlanArrangeHeadService.findList(mesPlanArrangeHead); 
	    	result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , list);
       } catch (Exception e) {
			logger.error(" findList error by MesPlanArrangeHeadController ,exception:{}", e);
			result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
	   }
		return result;
	}
	
	
	
	@RequestMapping(value = "/batchsave/det.do")
	@ResponseBody
	public AjaxResponder saveBatchDet(@RequestParam Date shiftDate,@RequestBody MesPlanArrangeHead mesPlanArrangeHead, Model model) throws Exception  {
//		if (!beanValidator(model, mesPlanDevice)){
//			//	return form(testData, model);
//		}
		if (shiftDate == null){
			return AjaxResponder.getInstance(Boolean.FALSE ,  "日期不能为空!" , null);
		}
		logger.info("saveBatchDet start!");
		AjaxResponder result = null;
		try{
			mesPlanArrangeHeadService.saveBatchDet(shiftDate,mesPlanArrangeHead);
			//MesPlanShiftSheet mesPlanShiftSheet = mesPlanTempTaskService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "批量保存成功! "  , mesPlanArrangeHead);
		}catch (Exception ex){
			logger.error("saveBatchDet mes_plan_temp_task error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , null);
		}
		logger.info("saveBatchDet end!");
		return result;
	}
	
	
	@RequestMapping(value = "/checkDet/det.do")
	@ResponseBody
	public AjaxResponder checkDet(@RequestParam String id) throws Exception  {
		logger.info("saveBatchDet start!");
		AjaxResponder result = null;
		try{
			String sheetId=mesPlanArrangeHeadService.save_chk_det(id);
			//MesPlanShiftSheet mesPlanShiftSheet = mesPlanTempTaskService.getSheetBySheetId(sheetId);
			result =  AjaxResponder.getInstance(Boolean.TRUE ,  "下发成功! "  , sheetId);
		}catch (Exception ex){
			logger.error("saveBatchDet mes_plan_temp_task error by Controller ,exception:{}", ex);
			ex.printStackTrace();
			result =  AjaxResponder.getInstance(Boolean.FALSE ,  ex.getMessage()  , "下发出错,请检查订单是否存在!");
		}
		logger.info("saveBatchDet end!");
		return result;
	}
	
	
	
	@RequestMapping(value = "/getsheet/index.do")
	@ResponseBody
	public AjaxResponder getSheet(@RequestParam(required=false)String id,@RequestParam(required=false)String sheetId) {
		AjaxResponder result = null;
		logger.info("getSheet start!");
		MesPlanArrangeHead shiftSheet = null;
		try {
			if (StringUtil.isNotBlank(id)) {
				shiftSheet = mesPlanArrangeHeadService.getSheetById(id);
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", shiftSheet);
			} else if (StringUtil.isNotBlank(sheetId)) {
				shiftSheet = mesPlanArrangeHeadService.getSheetBySheetId(sheetId);
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
		MesPlanArrangeHead shiftSheet = null;
		shiftSheet = mesPlanArrangeHeadService.getSheetByUk(mesPlanTempShiftHead);
		result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功" , shiftSheet);
		logger.info("getSheet end!");
		return result;
	}
	

	@RequestMapping(value = "/save")
	@ResponseBody
	public AjaxResponder save(MesPlanArrangeHead mesPlanArrangeHead, Model model) {
	if (!beanValidator(model, mesPlanArrangeHead)){
		//	return form(testData, model);
		}
		AjaxResponder result = null;
    	try {
		mesPlanArrangeHeadService.save(mesPlanArrangeHead);
		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "保存mes_plan_arrange_head成功! "  , null);
	} catch (Exception e) {
		logger.error("save mes_plan_arrange_head error by Controller ,exception:{}", e.getMessage());
		result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
	}
    	return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deletesheet/index.do")
	public AjaxResponder deletesheet(@RequestParam(required=true) String id,@RequestParam(required=false) String sheetId) {
		AjaxResponder result = null;
    	try {
    		MesPlanArrangeHead head = mesPlanArrangeHeadService.get(id);
    		if (head !=null ){
    			MesPlanArrangeDet detPara = new MesPlanArrangeDet();
    			detPara.setSheetId(head.getSheetId());
    			detPara.setCanDeleteStatus("1");
    			List<MesPlanArrangeDet> detList = mesPlanArrangeDetService.findList(detPara);
    			if (detList == null || detList.size() == 0)
    			{
    				mesPlanArrangeHeadService.deleteSheet(id,head.getSheetId());
            		result =  AjaxResponder.getInstance(Boolean.TRUE ,  "删除mes_plan_arrange_head成功! ",1);
    			}
    			else{
    				result =  AjaxResponder.getInstance(Boolean.FALSE ,  "明细表已下发,不能删除 ",null);
    			}
    			
    		}else{
    			result =  AjaxResponder.getInstance(Boolean.FALSE ,  "删除mes_plan_arrange_head失败! ",null);
    		}
    		
    	} catch (Exception e) {
			logger.error("delete mes_plan_arrange_head  error by Controller ,exception:{}", e.getMessage());
			result =  AjaxResponder.getInstance(Boolean.FALSE ,e.getMessage(), null);
		}
		return result;
	}
	
	
	
	@ResponseBody
    @RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = {RequestMethod.POST })	
    public AjaxResponder findPage(            
            MesPlanArrangeHead mesPlanArrangeHead,@RequestBody String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) {
		// Map<String, String> datas = WebUtil.request2Map(request);
		PageInfo<MesPlanArrangeHead> pageItems = null;
		logger.info("findPage start!");
		AjaxResponder result = null;
		try {
			pageItems = mesPlanArrangeHeadService.findPage(pageno, pagesize, mesPlanArrangeHead);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
    }
	

	@RequestMapping(value = {"/findStockTotalList/index.do"})
	@ResponseBody
	public AjaxResponder findStockTotalList(ClothInventoryRequest clothInventoryRequest, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result =null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  try {
			  InventoryResponse  inventoryResponse = inventoryProvider.findClothAmount(clothInventoryRequest);
			  List<ClothInventory> clothInventoryList = (List<ClothInventory>)inventoryResponse.getData();
			  //转换成为MesPlanArrangeDet
			  List<MesPlanArrangeDet> mesPlanArrangeDetList = new ArrayList<MesPlanArrangeDet>();
			  for(ClothInventory clothInventory:clothInventoryList){
				  MesPlanArrangeDet det = new MesPlanArrangeDet();
				  det.setErpBillNo(clothInventory.getProductOrder());
				  det.setGoodsId(clothInventory.getMaterialCode());
				  det.setGoodsName(clothInventory.getMaterialName());
				  det.setLeftQty(clothInventory.getAmount());
				  
				  if (clothInventory.getPlanObject().equals("10")){
					  det.setWorkcenterId("W14");
				  }else  if (clothInventory.getPlanObject().equals("20")){
					  det.setWorkcenterId("W15");
				  }else  if (clothInventory.getPlanObject().equals("30")){
					  det.setWorkcenterId("W16");
				  }
				  det.setQtyUnit(clothInventory.getMaterialUnitName());
				  //查找半成品订单号对应的成品订单号
				  if (StringUtil.isNotEmpty(det.getErpBillNo())){
					  MesPlanMonth monthPara2 = new MesPlanMonth();
					  monthPara2.setErpBillNo(det.getErpBillNo());
					  MesPlanMonth rtnMonth = mesPlanMonthService.getByUk(monthPara2);
					  if (rtnMonth !=null && StringUtil.isNotEmpty(rtnMonth.getGoodsErpBillNo())){
						  det.setArrangeErpBillNo(rtnMonth.getGoodsErpBillNo());
					  }
				  } 
				  if (StringUtil.isEmpty(det.getArrangeErpBillNo())){
					//查找对应的订单， 如果只有一张，直接填上arrangeErpBillNo
					  MesPlanMonth monthPara = new MesPlanMonth();
					  monthPara.setBomGoodsId(det.getGoodsId());
					  monthPara.setPlanBeginDate2(sdf.parse(clothInventoryRequest.getDate()));
					  monthPara.setPlanEndDate1(sdf.parse(clothInventoryRequest.getDate()));
					  List<String> planStatusList = new ArrayList<String>();
					  planStatusList.add("10");
					  planStatusList.add("20");
					  planStatusList.add("30");
					  monthPara.setPlanStatusList(planStatusList);
					  monthPara.setWorkshopId(MesPlanDevice.WORKSHOP_ARRANGE);
					  List<MesPlanMonth> mesPlanMonthList= mesPlanMonthService.findList(monthPara);
					  if (mesPlanMonthList != null && mesPlanMonthList.size() == 1){
						  det.setArrangeErpBillNo(mesPlanMonthList.get(0).getErpBillNo());
					  }
				  }
				  
				  
				  mesPlanArrangeDetList.add(det);
			  }
			  
			  result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , mesPlanArrangeDetList);
	       } catch (Exception e) {
				logger.error(" findList error by MesPlanArrangeHeadController ,exception:{}", e);
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
			return result;
	}
	
	@RequestMapping(value = {"/findStockList/{pageno}/{pagesize}/index.do"})
	@ResponseBody
	public AjaxResponder findStockList(@PathVariable int  pageno,@PathVariable int  pagesize,String planObject,String productOrder,String date, HttpServletRequest request, HttpServletResponse response, Model model) {
		AjaxResponder result =null;
		  try {
			  ClothInventoryPageRequest paramClothInventoryRequest = new ClothInventoryPageRequest();
			  paramClothInventoryRequest.setPageNum(pageno);
			  paramClothInventoryRequest.setPageSize(pagesize);
			  paramClothInventoryRequest.setPlanObject(planObject);
			  paramClothInventoryRequest.setProductOrder(productOrder);
			  paramClothInventoryRequest.setDate(date);
			  InventoryResponse  inventoryResponse = inventoryProvider.findClothList(paramClothInventoryRequest);
			  PageInfo<ClothInventory> clothInventoryList = (PageInfo<ClothInventory>)inventoryResponse.getData();
			  //List<ClothInventory> pageItems = JSONFormatter.newInstance().toArray(inventoryResponse.getData().toString(), ClothInventory.class);
			  result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , clothInventoryList);
	       } catch (Exception e) {
				logger.error(" findList error by MesPlanArrangeHeadController ,exception:{}", e);
				result=AjaxResponder.getInstance(Boolean.FALSE ,  "查询失败"  , null);
		   }
			return result;
	}
	
	
	@ResponseBody
    @RequestMapping(value = "/getCombineAmount/index.do")
    public AjaxResponder findCombineAmount(CombineClothRequest combineClothRequest,HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result =null;
		  try {
			  CombineClothResponse combineClothResponse =  combineClothProvider.findCombineAmount(combineClothRequest);
			  List<JobWorkShopCombineClothItems> jobWorkShopCombineClothList = combineClothResponse.getList();
			  //转换成为MesPlanArrangeDet
			  List<MesPlanArrangeDet> mesPlanArrangeDetList = new ArrayList<MesPlanArrangeDet>();
			  for(JobWorkShopCombineClothItems clothInventory:jobWorkShopCombineClothList){
				  MesPlanArrangeDet det = new MesPlanArrangeDet();
				  det.setErpBillNo(clothInventory.getErpBillNo());
				  det.setGoodsId(clothInventory.getGoodsId());
				  det.setGoodsName(clothInventory.getGoodsName());
				  det.setLeftQty(clothInventory.getAmount().doubleValue());
				  det.setWorkcenterId("W17");//打包工作中心
				  det.setQtyUnit("米");//拼件固定单位米
				  mesPlanArrangeDetList.add(det);
			  }
			  result = AjaxResponder.getInstance(Boolean.TRUE,"查询成功",mesPlanArrangeDetList);
		  }catch (Exception e) {
			  logger.error(" findCombineAmount error by MesPlanArrangeHeadController ,exception:{}", e);
			  result=AjaxResponder.getInstance(Boolean.FALSE,"查询失败",null);
		  }
        return result;
    }

	@ResponseBody
    @RequestMapping(value = "/getCombineAmountDet/{pageno}/{pagesize}/index.do")
    public AjaxResponder get(@PathVariable int  pageno,@PathVariable int  pagesize,CombineClothRequest combineClothRequest) {
		AjaxResponder result =null;
		 try {
			combineClothRequest.setPageNum(pageno);
			combineClothRequest.setPageSize(pagesize);
			CombineClothResponse combineClothResponse =  combineClothProvider.findCombineList(combineClothRequest);
			PageInfo<JobWorkShopCombineClothItems> itemsList=  combineClothResponse.getPageList();
			
			result = AjaxResponder.getInstance(Boolean.TRUE,"查询成功",itemsList);
		 }
        catch (Exception e) {
			logger.error(" findCombineAmount error by MesPlanArrangeHeadController ,exception:{}", e);
			result=AjaxResponder.getInstance(Boolean.FALSE,"查询失败",null);
		}
        return result;
	}
}