<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Birt测试</title>
</head>

<style type="text/css">
 input[type="radio"] {display:none} 
 input[disabled] {display:none} 
 label[title="Null Value"]{display:none} 
</style>
<body>

  <birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="new_report_1.rptdesign"
    target="_blank"
    showTitle="false"
    format="html">
 <table>
 	<tr>
 		<td>单号：</td><td><birt:paramDef id="BillNumber" name="BillNumber" value="1" style="width:160px"/></td>
 		<td>开始日期：</td><td><birt:paramDef id="BuildDate1" name="BuildDate1" value="2015-01-01" style="width:160px"/></td>
 		<td>结束日期：</td><td><birt:paramDef id="BuildDate2" name="BuildDate2" value="2017-01-01" style="width:160px"/></td>
 		<td>下拉：</td><td> <birt:paramDef id="Combox1" name="Combox1" style="width:164px;height:24px" /></td>
 	</tr>
 	<tr>
 		<td>联动参数1：</td><td> <birt:paramDef id="cat1" name="cat1" style="width:164px;height:24px" /></td>
 		<td>联动参数2：</td><td> <birt:paramDef id="cat2" name="cat2" style="width:164px;height:24px" /></td>
 		<td>联动参数3：</td><td> <birt:paramDef id="cat3" name="cat3" style="width:164px;height:24px" /></td>
 		<td>下拉：</td><td></td>
 	</tr>
 	<tr>
 		<td colSpan=6 style="text-align:center"> <input type="submit" value="Submit"></td>
 	</tr>
 </table>
</birt:parameterPage>  
<div  id="reportContainer" style="border:1px solid red;width:100%;height:100px" >
<birt:report reportContainer="reportContainer" id="birtReport" reportDesign="new_report_1.rptdesign" isHostPage="false" format="html">
	<birt:param name="BillNumber" value=""/> 
</birt:report>
</div>
</body>
</html>