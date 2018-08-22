<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@taglib uri="/birt.tld" prefix="birt" %>
<html lang="zh" style="height:100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Birt测试</title>
<link href="/MesReport/bootstrap_3.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<link href="/MesReport/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet"/>
<link href="css/reportCus.css"  type="text/css" rel="stylesheet">
<script src="/MesReport/webcontent/birt/ajax/lib/head.js" type="text/javascript"></script>
<script src="/MesReport/webcontent/birt/ajax/lib/prototype.js" type="text/javascript"></script>
<script src="/MesReport/webcontent/birt/ajax/utility/BrowserUtility.js" type="text/javascript"></script>
</head>
<body style="height:100%;overflow:hidden">
<%final String reportid = request.getParameter("__report")== null?"welcome.rptdesign":request.getParameter("__report");%>
<%final String isDebug = request.getParameter("isDebug")== null?"0":request.getParameter("isDebug");%>
<%-- <%final String showTooBar = isDebug.equals("1")?"true":"false";%> --%>
<%final String showData = request.getParameter("showData")== null?"0":request.getParameter("showData");%>
<%final String baseUrl = String.format("/MesReport/frameset?__showtitle=false&__report=%s&isDebug=%s",reportid,isDebug);%>


<%	
	String reportPara = application.getRealPath("/")+"/WEB-INF/reportPages/blank.jsp";
	int indexNo=reportid.lastIndexOf('.');
	if  (indexNo>0){
		reportPara = application.getRealPath("/")+"/WEB-INF/reportPages/"+reportid.substring(0,indexNo)+".jsp";
	} 
	File file = new File(reportPara);
	if (!file.exists()){
		reportPara = application.getRealPath("/")+"/WEB-INF/reportPages/blank.jsp";
	}
%>
<div id ="headDiv">