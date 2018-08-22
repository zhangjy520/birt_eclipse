<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<script type="text/javascript">
function onReadyCus3(){
	var array = [];  //定义数组   
	var areaArray=[];
	var areaTextArray=[];
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
	
	 $j("#area option").each(function(){  //遍历所有option  
         var txt = $j(this).val();   //获取option值   
         var text = $j(this).text();
         if(txt!=''){  
        	 areaArray.push(txt);  //添加到数组中  
         } 
         if(text!=''){  
        	 areaTextArray.push(text);  //添加到数组中  
         }
    })  
   $j("#area").empty();
	for(var i=0;i<areaArray.length-1;i++){
		if(i==1){
			$j("#area").append("<option selected='selected' value='"+areaArray[i]+"'>"+areaTextArray[i]+"</option>");
		}else{
			$j("#area").append("<option value='"+areaArray[i]+"'>"+areaTextArray[i]+"</option>");
		}
	}
	var src = $j("#reportContainer").attr("src")+"&year="+array[0]+"&area="+areaArray[1];
	$j("#reportContainer").attr("src",src);
	
	$j("h2").text('工业企业能源购进、消费与库存-'+ $j("#area").val())
	
}

function onQueryCus(){
	$j("h2").text('工业企业能源购进、消费与库存-'+ $j("#area").val());
}
</script>
<h2 style="text-align:center"></h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="industry_enterprise_energy_Invoicing_zone.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 	<tr>
 		<td><span class="requiredConCus">年份</span></td><td><birt:paramDef id="year" name="year" cssClass="form-control input-sm"/></td>
 		<td><span class="requiredConCus">区域</span></td><td><birt:paramDef id="area" name="area" cssClass="form-control input-sm"/></td>
 	</tr>
 </table>
</birt:parameterPage>  
<jsp:directive.include file="includes/bottom.jsp" />