<%@page import="com.bmsmart.mes.base.web.filter.AjaxHandlerFilter"%>
<%@page import="com.bmsmart.mes.base.util.web.AjaxResponder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@ page language="java" contentType="text/javascript; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%
	String datatype = (String)request.getAttribute("datatype");
	Object obj = request.getAttribute("result");
	if(!(obj instanceof AjaxResponder)){
		obj = AjaxResponder.getSuccInstance(obj);
	}
	String dataJsonStr = JSONObject.toJSONString(obj);
	if(StringUtils.equals(AjaxHandlerFilter.DataType_script, datatype)){
		response.setContentType("text/javascript; charset=UTF-8");
%>
		var data = <%=dataJsonStr %>
		${callback}(data);
<%
	}else{
		response.setContentType("text/json; charset=UTF-8");
%>
		<%=dataJsonStr%>
<%		
	}
%>