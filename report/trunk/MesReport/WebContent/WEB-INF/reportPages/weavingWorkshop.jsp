<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<h2 id="titleH2" style="text-align:center">纺织车间报表</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="weavingWorkshop.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table style="width:90%">
 	<tr>
 	 <td><span>班组</span></td><td> <birt:paramDef id="classGroup" name="classGroup" cssClass="form-control input-sm" value=""/></td>
 	<td style="width:10%"><span>开始日期</span></td><td style="width:15%"><input id="beginDate" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyy-MM-dd"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
 	<td style="width:10%"><span>结束日期</span></td><td style="width:15%"><input id="endDate" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyy-MM-dd"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>

 		<td style="width:10%"></td><td style="width:15%"></td>
 	</tr>
 
 </table>
</birt:parameterPage> 
<script type="text/javascript"> 

</script> 
<jsp:directive.include file="includes/bottom.jsp" />