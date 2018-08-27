package com.bmsmart.mes.plan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.plan.service.MesPlanCommonService;
import com.bmsmart.mes.platform.export.DictProvider;
import com.bmsmart.mes.platform.export.request.DictRequest;
import com.bmsmart.mes.platform.export.response.DictListResponse;
import com.bmsmart.mes.platform.export.vo.DictVo;
@Transactional(readOnly = true)
@Service("mesPlanCommonService")
public class MesPlanCommonServiceImpl implements MesPlanCommonService  {
	@Autowired
	private DictProvider dictProvider;
    public List<Map<String,String>> getDictListByType(String type) throws Exception {
    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		 
		DictRequest dictRequest=new DictRequest();
		dictRequest.setType(type);
		DictListResponse response = dictProvider.getDictList(dictRequest);
		
//		 Map<String,String> emptyMap = new HashMap<String,String>();
//		 emptyMap.put("id", "");
//		 emptyMap.put("text","");
		// list.add(emptyMap);
		//list = response.getDictList();
		 for(DictVo vo:response.getDictList()){
			 Map<String,String> map = new HashMap<String,String>();
			 map.put("id", vo.getValue());
			 map.put("text", vo.getLabel());
			 list.add(map);
			 
		 }
   		return list;
    }
	
    public Map<String, List<Map<String,String>> > getMutipleDictListByType(String types) throws Exception{
    	Map<String,List<Map<String,String>>> map = new  HashMap<String,List<Map<String,String>>>();
		 
			String[]  typeArr = types.split(",");
			for (int i=0 ; i< typeArr.length; i++){
				if (StringUtil.isNotBlank(typeArr[i])){
					List<Map<String,String>> dictList =  getDictListByType(typeArr[i]);
					map.put(typeArr[i], dictList);
				}
				
			}
   		return map;
    }
}
