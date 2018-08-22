<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
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
 		<td style="width:10%"><span class="requiredConCus">单号</span></td><td><birt:paramDef id="BillNumber" name="BillNumber" cssClass="requiredCus form-control input-sm"/></td>
 		<%-- <td style="width:10%">开始日期</td><td style="width:15%"><birt:paramDef id="BuildDate1" name="BuildDate1" cssClass="form-control" value="2015-01-01" style="width:90%"/></td>
 		<td style="width:10%">结束日期</td><td style="width:15%"><birt:paramDef id="BuildDate2" name="BuildDate2" cssClass="form-control" value="2017-01-01" style="width:90%"/></td> --%>
 		<td style="width:10%"><span class="requiredConCus">开始日期</span></td><td style="width:15%"><input id="BuildDate1" type="text" maxlength="20" class="requiredCus form-control input-sm Wdate" pattern="yyyyMMdd"  onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
 		<td style="width:10%"><span>结束日期</span></td><td style="width:15%"><input id="BuildDate2" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyyMMdd"  onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
 		<td style="width:10%"><span>下拉</span></td><td style="width:15%"><birt:paramDef id="Combox1" name="Combox1" cssClass="form-control input-sm" value=""/></td>
 	</tr>
 	<tr>
 		<td><span class="requiredConCus">联动参数1</span></td><td> <birt:paramDef id="cat1" name="cat1" cssClass="requiredCus form-control input-sm" value=""/></td>
 		<td><span>联动参数2</span></td><td> <birt:paramDef id="cat2" name="cat2" cssClass="form-control input-sm" value=""/></td>
 		<td><span>联动参数3</span></td><td> <birt:paramDef id="cat3" name="cat3" cssClass="form-control input-sm" value=""/></td>
 		<td></td><td></td>
 	</tr>
 
 </table>
</birt:parameterPage>  
<jsp:directive.include file="includes/bottom.jsp" />