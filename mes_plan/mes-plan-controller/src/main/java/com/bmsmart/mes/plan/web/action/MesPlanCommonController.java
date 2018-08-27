package com.bmsmart.mes.plan.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.plan.service.MesPlanCommonService;
import com.bmsmart.mes.platform.export.vo.DictVo;

@Controller
@RequestMapping(value = "/plan/common")
public class MesPlanCommonController  extends BaseController {
	//public static String  CMDBURL="http://192.168.1.178:19080/MESCMDB/asset/assetSearch/getAssetList.json?";
	@Autowired
	private MesPlanCommonService mesPlanCommonService;

	/**
	 * 得到字典
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getdict/index.do")
	@ResponseBody
	public AjaxResponder getDictListByType(@RequestParam(required=false) String type) {
		logger.info("getDictListByType start! para={0}",type);
		AjaxResponder result =null;
		try{
			List<Map<String,String>>   dictVoList = mesPlanCommonService.getDictListByType(type);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , dictVoList);
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error("getDictListByType ,exception:{}", ex);
			result = AjaxResponder.getInstance(Boolean.FALSE , ex.getMessage()  , null);
		}
		logger.info("getDictListByType end!");
		return result;
 
	}
	
	
	@RequestMapping(value = "/getdemo/index.do")
	@ResponseBody
	public Map getDemo(@RequestParam(required=false) String pageSize,@RequestParam(required=false) String pageNumber,@RequestParam(required=false) String username) {
//		 "total":200,"rows":[
//		     	            {
//		     	                "id": 0,
//		     	                "name": "Item 0",
//		     	                "price": "$0"
//		     	            }   ]
		List list = new ArrayList();   
		Map listMap = new HashMap(); 
		listMap.put("id", "0");
		listMap.put("name", "Item 0");
		listMap.put("price", 0);
		list.add(listMap);
		
		Map listMap2 = new HashMap(); 
		listMap2.put("id", "1");
		listMap2.put("name", "Item 1");
		listMap2.put("price", 1);
		list.add(listMap2);
		
		Map result =null;
		result = new HashMap(); 
		result.put("total", 200);
		result.put("rows", list);
		
		return result;
	}
	
	
	@RequestMapping(value = "/getdemo2/index.do")
	@ResponseBody
	public List getDemo2(@RequestParam(required=false) String pageSize,@RequestParam(required=false) String pageNumber,@RequestParam(required=false) String username) {
//		 "total":200,"rows":[
//		     	            {
//		     	                "id": 0,
//		     	                "name": "Item 0",
//		     	                "price": "$0"
//		     	            }   ]
		List list = new ArrayList();   
		
		for (int i=0; i<50; i++){
			Map listMap = new HashMap(); 
		listMap.put("id", i);
		listMap.put("name", "Item "+i);
		listMap.put("price", 10);
		list.add(listMap);
		}
		
		return list;
	}
	
	/**
	 * 得到多个字典
	 * @param types 多个逗号分隔
	 * @return
	 */
	@RequestMapping(value = "/getdicts/index.do")
	@ResponseBody
	public AjaxResponder getMutipleDictListByType(@RequestParam(required=false) String types) {
		logger.info("getMutipleDictListByType start! para={0}",types);
		AjaxResponder result =null;
		try{
			Map<String, List<Map<String,String>> >  map = mesPlanCommonService.getMutipleDictListByType(types);
			result = AjaxResponder.getInstance(Boolean.TRUE , "查询成功"  , map);
		}
		catch(Exception ex){
			logger.error("getMutipleDictListByType ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE , ex.getMessage()  , null);
		}
		logger.info("getMutipleDictListByType end!");
		return result;
	}
	
	
//	@RequestMapping(value = "/getcmdb/index.do")
//	@ResponseBody
//	public AjaxResponder getMutipleDictListByType(HttpServletRequest request, HttpServletResponse response, Model model) {
//		AjaxResponder result =null;
//		try{
//			String queryString =request.getQueryString(); 
//			String url =CMDBURL + queryString;
//			String rtn = HttpClientCenter.getInstance().getMethod(url);
//			result = AjaxResponder.getInstance(Boolean.TRUE , "成功" , rtn);
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//			result = AjaxResponder.getInstance(Boolean.FALSE , ex.getMessage()  , null);
//		}
//		return result;
//	}
	
}
