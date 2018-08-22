<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%
String reportid = request.getParameter("__report");
if (reportid != null && !reportid.equals("") && !reportid.equals("null")){
	String reportJsp="welcome";
	int indexNo= reportid.lastIndexOf(".");
	if (indexNo >0){
		reportJsp = reportid.substring(0,indexNo);
		String filePath = application.getRealPath("/")+"/WEB-INF/reportPages/"+reportid.substring(0,indexNo)+".jsp";
		File file = new File(filePath);
		if (!file.exists()){
			reportJsp="welcome";
		}
	}
	reportJsp = reportJsp +".jsp";
	request.getRequestDispatcher("/WEB-INF/reportPages/"+reportJsp).forward(request, response);
}else{
	response.sendRedirect("index.jsp?__report=welcome.rptdesign");
}

%>
