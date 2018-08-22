<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Birt测试</title>
</head>
<link href="/Birt/bootstrap_3.1.1/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="/Birt/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet">
<script src="/Birt/webcontent/birt/ajax/lib/head.js" type="text/javascript"></script>
<script src="/Birt/webcontent/birt/ajax/lib/prototype.js" type="text/javascript"></script>
<script src="/Birt/webcontent/birt/ajax/utility/BrowserUtility.js" type="text/javascript"></script>
<style type="text/css">
 input{font-size 13px !important}
 input[type="radio"] {display:none} 
 select {height:30px !important} 
 input[disabled] {display:none} 
 label[title="Null Value"]{display:none} 
 iframe {border:0px}
 td {padding:4px;font-size:13px;text-align:right}
 th {padding:2px}
</style>
<body>
<h2 style="text-align:center">订单查询</h2>
  <birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="new_report_1.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 <tr>
 	</tr>
 	<tr>
 		<td style="width:10%">单号：</td><td style="width:15%"><birt:paramDef id="BillNumber" name="BillNumber" cssClass="form-control input-sm" value="1" /></td>
 		<%-- <td style="width:10%">开始日期：</td><td style="width:15%"><birt:paramDef id="BuildDate1" name="BuildDate1" cssClass="form-control" value="2015-01-01" style="width:90%"/></td>
 		<td style="width:10%">结束日期：</td><td style="width:15%"><birt:paramDef id="BuildDate2" name="BuildDate2" cssClass="form-control" value="2017-01-01" style="width:90%"/></td> --%>
 		<td style="width:10%">开始日期：</td><td style="width:15%"><input name="BuildDate1" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyy-MM-dd"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
 		<td style="width:10%">结束日期：</td><td style="width:15%"><input name="BuildDate2" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyy-MM-dd"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
 		<td style="width:10%">下拉：</td><td style="width:15%"><birt:paramDef id="Combox1" name="Combox1" cssClass="form-control input-sm"/></td>
 	</tr>
 	<tr>
 		<td>联动参数1：</td><td> <birt:paramDef id="cat1" name="cat1" cssClass="form-control input-sm"/></td>
 		<td>联动参数2：</td><td> <birt:paramDef id="cat2" name="cat2" cssClass="form-control input-sm"/></td>
 		<td>联动参数3：</td><td> <birt:paramDef id="cat3" name="cat3" cssClass="form-control input-sm"/></td>
 		<td>下拉：</td><td></td>
 	</tr>
 	<tr>
 		<td colSpan=8 style="text-align:center"> 
 			<input type="submit" class="btn btn-primary" value="查询" onclick="return false;"/> 
 			<div class="btn-group"  role="group" >
				<button  type="button" style="text-align:left" class="btn"><img src="/Birt/webcontent/birt/images/ExportReport.gif"  class="localeImg" alt="" />导出</button>
				<button  type="button"  class="btn dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="caret" style="height:20px"></span> <span class="sr-only">Toggle Dropdown</span>
				</button>
				<ul class="dropdown-menu">
					<li><a href="#"><img src="/Birt/webcontent/birt/images/Export.gif"  alt="" />Excel2003</a></li>
					<li><a href="#"><img src="/Birt/webcontent/birt/images/Export.gif"  alt="" />Excel2007</a></li>
					<li><a href="#"><img src="/Birt/webcontent/birt/images/Export.gif"  alt="" />Word</a></li>
				</ul>
			</div>
			
 			<input type="reset" class="btn" value="清空" onclick="return false;"/>
 		</td>
 	</tr>
 </table>
</birt:parameterPage>  

<iframe id="reportContainer" style="width:100%;height:400px" src="/Birt/frameset?__showtitle=false&__id=parameterPage&__report=new_report_1.rptdesign&__format=html&&__toolbar=false" >
</iframe>

<script src="/Birt/webcontent/birt/ajax/utility/BirtUtility.js" type="text/javascript"></script>
<script src="/Birt/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="/Birt/js/jquery-1.4.1.cookie.min.js" type="text/javascript"></script>
<script src="/Birt/bootstrap_3.1.1/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/Birt/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript">  
	//$j就相当于jQuery，名称你可以自主定义  
	var  $j = jQuery.noConflict();  
	// Use jQuery via $j(...)  
	//$j(document).ready(function (){  
		//$j("div").hide();  
	//});  
	// Use Prototype with $(...), etc.  
	//$('proto').hide();  
</script>

</body>
</html>