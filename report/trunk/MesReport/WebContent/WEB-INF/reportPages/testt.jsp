<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<h2 id="titleH2" style="text-align:center">图形</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="testt.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table style="width:90%">
 	<tr>
 		<td style="width:10%"><span>名称</span></td><td style="width:15%"> <birt:paramDef id="catName" name="catName" cssClass="form-control input-sm" value=""/></td>
 		<td style="width:10%"><span>catCode</span></td><td style="width:15%"> <birt:paramDef id="catCode" name="catCode" cssClass="form-control input-sm" value=""/></td>
 		<td style="width:10%"><span>level</span></td><td style="width:15%"> <birt:paramDef id="level" name="level" cssClass="form-control input-sm" value=""/></td>
 		<td style="width:10%"></td><td style="width:15%"></td>
 	</tr>
 
 </table>
</birt:parameterPage> 
<script type="text/javascript"> 

</script> 
<jsp:directive.include file="includes/bottom.jsp" />