package com.bmsmart.mes.plan.service;

import java.util.List;
import java.util.Map;

import com.bmsmart.mes.platform.export.vo.DictVo;

public interface MesPlanCommonService {
	public  List<Map<String,String>>  getDictListByType(String type) throws Exception;
    public Map<String, List<Map<String,String>> > getMutipleDictListByType(String types) throws Exception;
}
