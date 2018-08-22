<%@page pageEncoding="UTF-8"%>
</div> 		
<iframe id="reportContainer" style="width:100%;height:100%" src="<%=baseUrl%>&showData=<%=showData%>" >
</iframe>
<script src="/MesReport/webcontent/MesReport/ajax/utility/MesReportUtility.js" type="text/javascript"></script>
<script src="/MesReport/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="/MesReport/js/jquery-1.4.1.cookie.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict();  
	onQuery =  function(frameid){
	var textList = $j('#parameterPage').find('input:text:enabled');	
	var selectList = $j('#parameterPage').find('select:enabled');
	var queryText="";
	debugger
	var errorMeg = "";
	$j.each(textList,function(i,item){
		if (item && !item.id)
		{
			console.error("type=text","报且参数没有指定ID属性")
		}
		else if (item.value != "" && item.value!=("$$"+"{{((null))}}$$") ) 
		{
			queryText= queryText + "&" +item.id+"=" + item.value;
		}else if (item.hasClassName("requiredCus")){
			errorMeg = "参数【"+item.parentElement.previous().innerText+"】不能为空!";
		}
	});
	if (errorMeg) {
		alert(errorMeg);
		return false;
	}
	$j.each(selectList,function(i,item){
		if (item && !item.id)
		{
			console.error("type=text","报且参数没有指定ID属性")
		}
		else if (item.value != "" && item.value!=("$$"+"{{((null))}}$$") ) 
		{
			queryText= queryText + "&" +item.id+"=" + item.value;
		}else if (item.hasClassName("requiredCus")){
			errorMeg = "参数【"+item.parentElement.previous().innerText.replace('*','')+"】不能为空!";
		}
	});
	if (errorMeg) {
		alert(errorMeg);
		return false;
	}
	if (queryText){
		queryText= encodeURI(queryText);
	}
	if (frameid){
		$j(frameid).attr('src', '<%=baseUrl%>&showData=1'+queryText); 
	    /* $j('#reportContainer').load(function()  
	    { 
	        callback(this); 
	    });  */
	}else{
		window.open('<%=baseUrl%>'+'&showData=1&__format=xls'+queryText);   
	}
	console.info("para",queryText);
	return false;
};


onClear =  function(){
	var textList = $j('#parameterPage').find('input:text:enabled');	
	var selectList = $j('#parameterPage').find('select:enabled');
	var queryText="";
	debugger
	$j.each(textList,function(i,item){
		if (item && !item.id)
		{
			console.error("type=text","报且参数没有指定ID属性")
		}
		else if (item.value != "" ) 
		{
			item.value="";
		}
		 
	});
	
	$j.each(selectList,function(i,item){
		if (item && !item.id)
		{
			console.error("type=text","报且参数没有指定ID属性")
		}
		else if (item.value != "" ) 
		{
			item.value="";
		}
	});
	return false;
}

onExport = function(){
	
}

$j(document).ready(function (){  
	
}); 

$j(window).resize(function() {
});
	
</script>
<!-- 	//$j就相当于jQuery，名称你可以自主定义  
	// Use jQuery via $j(...)  
	//$j(document).ready(function (){  
		/* $j("div").hide();   */
	//});  
	// Use Prototype with $(...), etc.  
	//$('proto').hide();  
 --> 

 
<script src="/MesReport/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/MesReport/bootstrap_3.1.1/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>