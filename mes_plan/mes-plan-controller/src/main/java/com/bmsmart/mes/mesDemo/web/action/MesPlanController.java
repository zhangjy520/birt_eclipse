package com.bmsmart.mes.mesDemo.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.json.JSON;
import com.bmsmart.mes.base.util.UUID;
import com.bmsmart.mes.base.util.web.WebUtil;
import com.bmsmart.mes.mesDemo.domain.po.DemoTest;
import com.bmsmart.mes.mesDemo.domain.vo.response.DemoResponseVo;
import com.bmsmart.mes.mesDemo.domain.vo.response.MesPlanVo;
import com.bmsmart.mes.mesDemo.domain.vo.response.Pager;
import com.bmsmart.mes.mesDemo.domain.vo.response.ResponseData;
//import com.bmsmart.mes.mesDemo.service.MesPlanService;
//import com.bmsmart.mes.mesDemo.service.TestService;
import com.bmsmart.mes.platform.auth.base.AuthPassport;
import com.bmsmart.mes.platform.export.DictProvider;
import com.bmsmart.mes.platform.export.request.DictRequest;
import com.bmsmart.mes.platform.export.response.DictListResponse;
import com.bmsmart.mes.platform.export.vo.DictVo;
import com.github.pagehelper.PageInfo;


/**
 * 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年5月24日 下午3:09:11  
 *
 */
@Controller
public class MesPlanController {

//    @Resource(name = "mesPlanService1")
//    private MesPlanService mesPlanService;
//
//    private static final Logger logger = LoggerFactory.getLogger(MesPlanController.class);
//
    @RequestMapping(value = "/test1/{pageno}/{pagesize}/a.do", method = {RequestMethod.POST })
    @ResponseBody
    public String testRest(MesPlanVo mesPlanPara,@RequestBody String mesPlanStr,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) throws Exception{
    //public String testRest(MesPlanVo mesPlanPara,String postData,@PathVariable int  pageno,@PathVariable int  pagesize,HttpServletRequest request, HttpServletResponse response) throws Exception{
    	//MesPlanVo mesPlanPara = JSON.parse(postData, MesPlanVo.class) ;
    	
    	//System.out.println(mesPlanPara);
    	//PageInfo<MesPlanVo> pageList = mesPlanService.queryPage(mesPlanPara, pageno,pagesize);
    	//ResponseData rd = new ResponseData();
    	//rd.setData(pageList);
//    	Map<String,String[]> map =request.getParameterMap();
//    	for (Entry<String,String[]> entry : map.entrySet()) {  
//    		  
//    	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//    	  
//    	}
		//return rd;
    	return "";
    }
 
}
