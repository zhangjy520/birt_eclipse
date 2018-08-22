<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<h2 style="text-align:center">钻取表</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="demo_drilldown.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 	<tr>
 		<td style="width:10%"><span>开始日期</span></td><td style="width:15%"><input id="BuildDate1" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyyMMdd"  onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
 		<td style="width:10%"><span>结束日期</span></td><td style="width:15%"><input id="BuildDate2" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyyMMdd"  onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
 		<td colspan=2 style="width:25%"></td>
 		<td colspan=2 style="width:25%"></td>
 	</tr>
 </table>
</birt:parameterPage>  
<jsp:directive.include file="includes/bottom.jsp" />