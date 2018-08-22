<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<script type="text/javascript">
function onReadyCus(){
	var array = [];  //定义数组   
	var areaArray=[];
    $j("#month option").each(function(){  //遍历所有option  
         var txt = $j(this).val();   //获取option值   
         if(txt!=''){  
              array.push(txt);  //添加到数组中  
         }  
    })  
   $j("#month").empty();
	for(var i=0;i<array.length-1;i++){
		if(i==0){
			$j("#month").append("<option selected='selected' value='"+array[i]+"'>"+array[i]+"</option>");
		}else{
			$j("#month").append("<option value='"+array[i]+"'>"+array[i]+"</option>");
		}
	}
	 $j("#area option").each(function(){  //遍历所有option  
         var txt = $j(this).val();   //获取option值   
         if(txt!=''){  
        	 areaArray.push(txt);  //添加到数组中  
         }  
    })  
   $j("#area").empty();
	for(var i=0;i<areaArray.length-1;i++){
		if(i==0){
			$j("#area").append("<option selected='selected' value='"+areaArray[i]+"'>"+areaArray[i]+"</option>");
		}else{
			$j("#area").append("<option value='"+areaArray[i]+"'>"+areaArray[i]+"</option>");
		}
	}
	
	var src = $j("#reportContainer").attr("src")+"&month="+array[0]+"&area="+areaArray[0];
	$j("#reportContainer").attr("src",src);
	
}
</script>
<h2 style="text-align:center">电力消耗月报表查询</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="energy_electric_month.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 	<tr>
 		<td><span class="requiredConCus">月份</span></td><td><birt:paramDef id="month" name="month" cssClass="form-control input-sm"/></td>
 		<td><span class="requiredConCus">区域</span></td><td><birt:paramDef id="area" name="area" cssClass="form-control input-sm"/></td>
 	</tr>
 </table>
</birt:parameterPage>  
<jsp:directive.include file="includes/bottom.jsp" />