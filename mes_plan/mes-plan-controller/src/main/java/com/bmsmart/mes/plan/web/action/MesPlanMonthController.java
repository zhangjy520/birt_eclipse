/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.bmsmart.mes.plan.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.exception.MesBaseException;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.base.util.prop.PropertyReader;
import com.bmsmart.mes.base.util.web.AjaxResponder;
import com.bmsmart.mes.base.util.web.WebUtil;
import com.bmsmart.mes.common.SessionUserInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bmsmart.mes.commons.web.BaseController;
import com.bmsmart.mes.mesDemo.domain.vo.response.MesPlanVo;
import com.bmsmart.mes.mesExternal.export.ErpQueryProvider;
import com.bmsmart.mes.mesExternal.export.request.ErpInventoryQueryPageRequest;
import com.bmsmart.mes.mesExternal.export.response.ErpInventoryInfoResponse;
import com.bmsmart.mes.mesExternal.export.response.vo.ErpInventoryInfo;
import com.bmsmart.mes.mesExternal.export.response.vo.MesWeavingInfo;
import com.bmsmart.mes.mesJob.domain.po.JobProcess;
import com.bmsmart.mes.plan.domain.po.BootTablePagEntity;
import com.bmsmart.mes.plan.domain.po.MesPlanBom;
import com.bmsmart.mes.plan.domain.po.MesPlanMonth;
import com.bmsmart.mes.plan.domain.po.MesPlanTechnic;
import com.bmsmart.mes.plan.service.MesPlanBomService;
import com.bmsmart.mes.plan.service.MesPlanMonthService;
import com.bmsmart.mes.plan.service.MesPlanTechnicService;
import com.bmsmart.mes.platform.auth.base.AuthHelper;
import com.bmsmart.mes.platform.auth.base.AuthPassport;
import com.bmsmart.mes.platform.export.UserProvider;
import com.bmsmart.mes.platform.export.vo.PermissionInfo;
import com.bmsmart.mes.platform.export.vo.UserVo;

/**
 * mes月计划查询Controller
 * 
 * @author 周奇志
 * @version 2017-07-31
 */
@Controller
@RequestMapping(value = "/plan/mesPlanMonth")
public class MesPlanMonthController extends BaseController {
	@Autowired
	private MesPlanMonthService mesPlanMonthService;
	@Autowired
	private ErpQueryProvider erpQueryProvider;
	@Autowired
	private MesPlanBomService mesPlanBomService;

	@RequestMapping(value = "/getPermissionInfo/index.do")
	@ResponseBody
	public AjaxResponder getPermissionInfo(HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result = null;
		logger.info("getPermissionInfo start!");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// String permissionSessionName =
			// PropertyReader.getContextProperty("mes.app.auth.permissionsession");
			String permissionSessionName = PropertyReader.getContextProperty(AuthHelper.SESSION_PERMISSION);
			// 用户权限
			PermissionInfo permissionInfo = (PermissionInfo) request.getSession().getAttribute(permissionSessionName);
			// String userSessionName =
			// PropertyReader.getContextProperty("mes.app.auth.usersession");
			// 用户
			String userSessionName = PropertyReader.getContextProperty(AuthHelper.SESSION_USER);

			HttpSession session = request.getSession();
			UserVo userVo = (UserVo) session.getAttribute(userSessionName);

			map.put("user", userVo);
			map.put("permission", permissionInfo);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查找成功", map);
		} catch (Exception ex) {
			logger.error("getPermissionInfo error by MesPlanMonthController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "取用户信息出错!" + ex.getMessage(), null);
		}
		logger.info("getPermissionInfo end!");
		return result;
	}

	@RequestMapping(value = "/get/index.do")
	@ResponseBody
	public AjaxResponder get(@RequestParam(required = false) String id) {
		MesPlanMonth entity = null;
		AjaxResponder result = null;
		logger.info("get start!");
		try {
			if (StringUtil.isNotBlank(id)) {
				entity = mesPlanMonthService.get(id);
			}
			if (entity == null) {
				result = AjaxResponder.getInstance(Boolean.FALSE, "查找失败,单号不存在或没有权限查看", null);
			} else {
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", entity);
			}

		} catch (Exception ex) {
			logger.error("get error by MesPlanMonthController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "取用户信息出错!" + ex.getMessage(), null);
		}
		logger.info("get end!");
		return result;
	}

	@RequestMapping(value = "/getByBillno/index.do")
	@ResponseBody
	public AjaxResponder getByBillNo(@RequestParam(required = false) String erpBillNo) {
		MesPlanMonth entity = null;
		AjaxResponder result = null;
		logger.info("getByBillNo start!");
		try {
			if (StringUtil.isNotBlank(erpBillNo)) {
				MesPlanMonth planPara = new MesPlanMonth();
				planPara.setErpBillNo(erpBillNo);
				entity = mesPlanMonthService.getByUk(planPara);
			}
			if (entity == null) {
				result = AjaxResponder.getInstance(Boolean.FALSE, "查找失败,单号不存在或没有权限查看", null);
			} else {
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", entity);
			}
		} catch (Exception ex) {
			logger.error("getByBillNo error by MesPlanMonthController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查找失败" + ex.getMessage(), null);
		}
		logger.info("getByBillNo end!");
		return result;
	}

	@RequestMapping(value = "/getWholeErpBillNo/index.do")
	@ResponseBody
	public AjaxResponder getWholeErpBillNo(@RequestParam(required = false) String id) {
		MesPlanMonth entity = null;
		AjaxResponder result = null;
		logger.info("getWholeErpBillNo start!");
		try {
			if (StringUtil.isNotBlank(id)) {
				entity = mesPlanMonthService.getWholeErpBillNo(id);
			}
			if (entity == null) {
				result = AjaxResponder.getInstance(Boolean.FALSE, "查找失败,单号不存在或没有权限查看", null);
			} else {
				result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", entity);
			}
		} catch (Exception ex) {
			logger.error("getWholeErpBillNo error by MesPlanMonthController ,exception:{}", ex);
			ex.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查找失败" + ex.getMessage(), null);
		}
		logger.info("getWholeErpBillNo end!");
		return result;
	}

	@RequestMapping(value = "/getMesPlanTechnicList/index.do")
	@ResponseBody
	public AjaxResponder getMesPlanTechnicList(MesPlanMonth mesPlanMonth) {
		AjaxResponder result = null;
		logger.info("getMesPlanTechnicList start!");
		if (StringUtils.isBlank(mesPlanMonth.getErpBillNo())) {
			if (StringUtils.isNotEmpty(mesPlanMonth.getId())) {
				mesPlanMonth = mesPlanMonthService.get(mesPlanMonth.getId());
			} else {
				return AjaxResponder.getInstance(Boolean.FALSE, "id或billno不能为空", null);
			}
		}
		try {
			List<MesPlanTechnic> list = mesPlanMonthService.getMesPlanTechnicList(mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getMesPlanTechnicList end!");
		return result;
	}

	@RequestMapping(value = "/getSubMesPlanTechnicList/index.do")
	@ResponseBody
	public AjaxResponder getSubMesPlanTechnicList(MesPlanMonth mesPlanMonth) {
		AjaxResponder result = null;
		logger.info("getSubMesPlanTechnicList start!");
		if (StringUtils.isBlank(mesPlanMonth.getErpBillNo())) {
			if (StringUtils.isNotEmpty(mesPlanMonth.getId())) {
				mesPlanMonth = mesPlanMonthService.get(mesPlanMonth.getId());
			} else {
				return AjaxResponder.getInstance(Boolean.FALSE, "id或billno不能为空", null);
			}
		}
		try {
			List<JobProcess> list = mesPlanMonthService.getMesPlanTechnicWithoutWorktypeList(mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getSubMesPlanTechnicList end!");
		return result;
	}

	@RequestMapping(value = "/getArrangeTechnic/index.do")
	@ResponseBody
	public AjaxResponder getArrangeTechnic() {
		AjaxResponder result = null;
		logger.info("getSubMesPlanTechnicList start!");

		try {
			List<JobProcess> list = mesPlanMonthService.getArrangeTechnic();
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("getSubMesPlanTechnicList end!");
		return result;
	}

	@RequestMapping(value = "/getDetail/index.do")
	@ResponseBody
	public AjaxResponder getDetail(@RequestParam(required = false) String id) {
		logger.info("getDetail start!");
		return get(id);
	}

	@RequestMapping(value = { "/findList/index.do" })
	@ResponseBody
	public AjaxResponder findList(MesPlanMonth mesPlanMonth, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		AjaxResponder result = null;
		logger.info("findList start!");
		try {
			List<MesPlanMonth> list = mesPlanMonthService.findList(mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", list);
		} catch (Exception e) {
			logger.error(" findList error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findList end!");
		return result;
	}

	@RequestMapping(value = "save/index.do")
	@ResponseBody
	public AjaxResponder save(MesPlanMonth mesPlanMonth, Model model) {
		logger.info("save start!");
		if (!beanValidator(model, mesPlanMonth)) {
			return AjaxResponder.getInstance(Boolean.FALSE, model.asMap().get("message").toString(), null);
		}
		AjaxResponder result = null;
		try {
			mesPlanMonthService.save(mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE, "保存mes月计划查询成功! ", mesPlanMonth.getId());
		} catch (Exception e) {
			logger.error("save mes月计划查询 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("save end!");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public ModelAndView delete(@RequestParam(required = false) String id) {
		AjaxResponder result = null;
		logger.info("delete start!");
		try {
			mesPlanMonthService.deleteById(id);
			result = AjaxResponder.getInstance(Boolean.TRUE, "删除mes月计划查询成功! ", null);
		} catch (Exception e) {
			logger.error("delete mes月计划查询  error by Controller ,exception:{}", e.getMessage());
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		ModelAndView mv = new ModelAndView("response");
		mv.addObject("result", result);
		logger.info("delete end!");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/findPage/{pageno}/{pagesize}/index.do", method = { RequestMethod.POST })
	public AjaxResponder findPage(MesPlanMonth mesPlanMonth, @RequestBody String postData, @PathVariable int pageno,
			@PathVariable int pagesize, HttpServletRequest request, HttpServletResponse response) {
		// Map<String, String> datas = WebUtil.request2Map(request);
		PageInfo<MesPlanMonth> pageItems = null;
		logger.info("findPage start!");
		AjaxResponder result = null;
		try {
			pageItems = mesPlanMonthService.findPage(pageno, pagesize, mesPlanMonth);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("findPage end!");
		return result;
	}

	@RequestMapping(value = "/finish/index.do")
	@ResponseBody
	public AjaxResponder finish(MesPlanMonth mesPlanMonth, Model model) {
		logger.info("finish start!");
		if (!beanValidator(model, mesPlanMonth)) {
			return AjaxResponder.getInstance(Boolean.FALSE, model.asMap().get("message").toString(), null);
		}
		if (StringUtils.isEmpty(mesPlanMonth.getId()) || StringUtils.isEmpty(mesPlanMonth.getPlanStatus())) {
			return AjaxResponder.getInstance(Boolean.FALSE, "id,planStatus不能为空", null);
		}
		AjaxResponder result = null;
		try {
			mesPlanMonthService.updateSheetStatusById(mesPlanMonth);
			MesPlanMonth retMesPlan = mesPlanMonthService.get(mesPlanMonth.getId());
			result = AjaxResponder.getInstance(Boolean.TRUE, "完工月计划成功! ", retMesPlan);
		} catch (Exception e) {
			logger.error("save mes月计划查询 error by Controller ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, e.getMessage(), null);
		}
		logger.info("finish end!");
		return result;
	}

	@RequestMapping(value = "/validateBillNo/index.do")
	@ResponseBody
	public Map<String, Boolean> getByErpBillNo(@RequestParam(required = false) String erpBillNo) {
		logger.info("getByErpBillNo start!");
		MesPlanMonth para = null;
		MesPlanMonth entity = null;
		if (StringUtil.isNotBlank(erpBillNo)) {
			para = new MesPlanMonth();
			para.setErpBillNo(erpBillNo);
			entity = mesPlanMonthService.getByUk(para);
		}
		Map<String, Boolean> rtnMap = new HashMap<String, Boolean>();
		if (entity == null) {
			// 没有重复数据
			rtnMap.put("valid", true);
		} else {
			rtnMap.put("valid", false);
		}
		logger.info("getByErpBillNo end!");
		return rtnMap;
	}

	@RequestMapping(value = "/validateBillNoFalse/index.do")
	@ResponseBody
	public Map<String, Boolean> getByErpBillNoFalse(@RequestParam(required = false) String erpBillNo) {
		Map<String, Boolean> rtnMap = getByErpBillNo(erpBillNo);
		Boolean rtnValue = (Boolean) rtnMap.get("valid");
		rtnMap.put("valid", !rtnValue);
		return rtnMap;
	}

	// @RequestMapping(value = "/getErpBillSubProcessList/index.do")
	// @ResponseBody
	// public AjaxResponder
	// getErpBillSubProcessList(@RequestParam(required=false) String erpBillNo){
	// AjaxResponder result = null;
	// MesPlanTechnic para = new MesPlanTechnic();
	// para.setErpBillNo(erpBillNo);
	// List<MesPlanTechnic> mesPlanTechnicList =
	// mesPlanTechnicService.findList(para);
	// List<Map<String,Object>> retList =
	// mesPlanMonthService.getSubProcessList(mesPlanTechnicList);
	// result = AjaxResponder.getInstance(Boolean.TRUE , "取数据成功! " , retList);
	// return result;
	// }

	@ResponseBody
	@RequestMapping(value = "/queryInventoryInfoPage/{pageno}/{pagesize}/index.do", method = { RequestMethod.POST })
	public AjaxResponder queryInventoryInfoPage(MesPlanMonth mesPlanMonth, @PathVariable int pageno,
			@PathVariable int pagesize, HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result = null;
		logger.info("queryInventoryInfoPage start!");
		try {
			if (StringUtils.isBlank(mesPlanMonth.getGoodsId()) || StringUtils.isBlank(mesPlanMonth.getErpBillNo())) {
				return AjaxResponder.getInstance(Boolean.FALSE, "订单号不能为空", null);
			}
			MesPlanBom bomPara = new MesPlanBom();
			bomPara.setErpBillNo(mesPlanMonth.getErpBillNo());
			List<MesPlanBom> bomList = mesPlanBomService.findList(bomPara);
			String codeList = "";
			if (bomList != null) {
				for (MesPlanBom bom : bomList) {
					codeList = codeList + "'" + bom.getRawGoodsId() + "',";
				}
			}
			if (codeList.length() > 0) {
				codeList = codeList.substring(0, codeList.length() - 1);
			} else {
				return AjaxResponder.getInstance(Boolean.FALSE, "订单没有对应的bom信息", null);
			}
			ErpInventoryQueryPageRequest request2 = new ErpInventoryQueryPageRequest();
			request2.setCodeList(codeList);
			request2.setPageSize(pagesize);
			request2.setStartPage(pageno);
			ErpInventoryInfoResponse erpResponse = erpQueryProvider.queryInventoryInfoPage(request2);
			PageInfo<ErpInventoryInfo> pageItems = JSONFormatter.newInstance().toObject(erpResponse.getResultStr(),
					PageInfo.class);
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", pageItems);
		} catch (Exception e) {
			logger.error(" findPage error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		logger.info("queryInventoryInfoPage end!");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/queryMesPlanMonthListByCrities/index.do", method = { RequestMethod.POST })
	public BootTablePagEntity<MesPlanMonth> queryMesPlanMonthListByCrities(MesPlanMonth mesPlanMonth,
			@RequestParam("limit") int limit, @RequestParam("offset") int offset,
			HttpServletRequest request, HttpServletResponse response) {
		try { 
			PageInfo<MesPlanMonth> mesMonthPageInfo = mesPlanMonthService.getMesPlanMonthListByCrities(mesPlanMonth,
					limit, offset / limit + 1);

			BootTablePagEntity<MesPlanMonth> table = new BootTablePagEntity<MesPlanMonth>();
			table.setTotal(mesMonthPageInfo.getTotal());
			table.setRows(mesMonthPageInfo.getList());			
			return table;
		} catch (Exception e) {
			logger.error(" queryMesPlanMonthListByCrities error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getMesPlanMonthDetail/index.do", method = { RequestMethod.GET })
	public AjaxResponder getMesPlanMonthDetail(HttpServletRequest request, HttpServletResponse response) {
		AjaxResponder result = null;

		try {
			Map<String, List<Map>> detail = mesPlanMonthService.getMesPlanMonthDetail();
			result = AjaxResponder.getInstance(Boolean.TRUE, "查询成功", detail);
		} catch (Exception e) {
			logger.error(" getMesPlanMonthDetail error by MesPlanMonthController ,exception:{}", e);
			e.printStackTrace();
			result = AjaxResponder.getInstance(Boolean.FALSE, "查询失败", null);
		}
		return result;
	}

}