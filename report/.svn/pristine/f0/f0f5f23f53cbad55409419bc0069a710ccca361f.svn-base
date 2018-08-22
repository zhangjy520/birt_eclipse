<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<script type="text/javascript">
function onReadyCus(){
	var array = [];  //定义数组   
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
	var src = $j("#reportContainer").attr("src")+"&month="+array[0];
	$j("#reportContainer").attr("src",src);
	
}
</script>
<h2 style="text-align:center">电费单价查询</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="unit_price_of_electricity.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 	<tr>
 		<td><span class="requiredConCus">月份</span></td><td><birt:paramDef id="month" name="month" cssClass="form-control input-sm"/></td>
 	</tr>
 </table>
</birt:parameterPage>  
<jsp:directive.include file="includes/bottom.jsp" />