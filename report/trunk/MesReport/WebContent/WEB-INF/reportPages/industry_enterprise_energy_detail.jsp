<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<script type="text/javascript">
function onReadyCus1(){
	var array = [];  //定义数组   
    $j("#year option").each(function(){  //遍历所有option  
         var txt = $j(this).val();   //获取option值   
         if(txt!=''){  
              array.push(txt);  //添加到数组中  
         }  
    })  
   $j("#year").empty();
	for(var i=0;i<array.length-1;i++){
		if(i==0){
			$j("#year").append("<option selected='selected' value='"+array[i]+"'>"+array[i]+"</option>");
		}else{
			$j("#year").append("<option value='"+array[i]+"'>"+array[i]+"</option>");
		}
	}
	var src = $j("#reportContainer").attr("src")+"&year="+array[0];
	$j("#reportContainer").attr("src",src);
	
}
</script>
<h2 style="text-align:center">工业企业能源明细表查询</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="industry_enterprise_energy_detail.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 	<tr>
 		<td><span class="requiredConCus">年份</span></td><td><birt:paramDef id="year" name="year" cssClass="form-control input-sm"/></td>
 	</tr>
 </table>
</birt:parameterPage>  
<jsp:directive.include file="includes/bottom.jsp" />