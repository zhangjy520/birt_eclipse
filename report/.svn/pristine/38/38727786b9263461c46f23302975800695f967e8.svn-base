<%@page pageEncoding="UTF-8"%>
 
<!-- <div id="buttonDiv" style="text-align:center;height:45px;margin-top:-10px">
 			<input type="button" style="width:80px" class="btn btn-primary" value="查    询" onclick="return onQuery('#reportContainer');"/>
			<button  type="button" style="width:80px;text-align:left" class="btn"  onclick="return onQuery();"><img src="/MesReport/webcontent/birt/images/ExportReport.gif"  class="localeImg"/>导    出</button>
			<div class="btn-group"  role="group" >
				<button  type="button"  class="btn dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span> style="margin:8px 0px"
				</button>
				<ul class="dropdown-menu">
					<li><a href="#"><img src="/MesReport/webcontent/MesReport/images/Export.gif"  alt="" />Excel2003</a></li>
					<li><a href="#"><img src="/MesReport/webcontent/MesReport/images/Export.gif"  alt="" />Excel2007</a></li>
					<li><a href="#"><img src="/MesReport/webcontent/MesReport/images/Export.gif"  alt="" />Word</a></li>
				</ul>
			</div>
 			<input type="button" style="width:80px" type="button" class="btn" value="清    空" onclick="return onClear();"/>
 </div>	   -->
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
		var errorMeg = "";
		$j.each(textList,function(i,item){
			if (item && !item.id)
			{
				console.error("type=text","报表参数没有指定ID属性")
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
		if (window.onQueryCus)
		{
			onQueryCus();
		}
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
	//alert($j(window).height());
	//alert($j('#headDiv').height());
	$j('#reportContainer').height($j(window).height()-$j('#headDiv').height());
	//是否有自定义ready函数，有就执行
	if (window.onReadyCus)
	{
		onReadyCus();
	}
	if (window.onReadyCus1)
	{
		onReadyCus1();
	}
	if (window.onReadyCus2)
	{
		onReadyCus2();
	}
	if (window.onReadyCus3)
	{
		onReadyCus3();
	}
	//alert($j(document).height()-$j('#headDiv').height());
	//alert($j('#reportContainer').height());
}); 


var iframe = document.getElementById("reportContainer");

//$j('#reportContainer').load = function(){
//	alert($j(window.frames["reportContainer"].contentDocument.getElementById('dftQryBtn')).css('display'));
//	$j(window.frames["reportContainer"].contentDocument.getElementById('dftQryBtn')).css('display','inline');
//	alert($j(window.frames["reportContainer"].contentDocument.getElementById('dftQryBtn')).css('display'));
//};

iframe.onload = function(){
	if (<%=isDebug%>)
	{
		$j(window.frames["reportContainer"].contentDocument.getElementById('dftQryBtn')).css('display','inline');
	}
};
    
$j(window).resize(function() {
	//alert($j(window).height());
	//alert($j('#headDiv').height());
	$j('#reportContainer').height($j(window).height()-$j('#headDiv').height());
});
/* 
$j('#headDiv').on('shown.bs.collapse', function () {
	$j(window).trigger('resize');
})
$j('#headDiv').on('hidden.bs.collapse', function () {
	$j(window).trigger('resize');
})
$j('#headDiv').on('show.bs.collapse', function () {
	$j(window.frames["reportContainer"].contentDocument.getElementById('toolbar')).css('display','block');
})
$j('#headDiv').on('hide.bs.collapse', function () {
	
})
 */

onFolder = function(){
	//$j(window.frames["reportContainer"].contentDocument.getElementById('toolbar')).css('display');
	var folderDiv = $j(window.frames["reportContainer"].contentDocument.getElementById('folderDiv'));
	if ($j('#headDiv').css('display')=="block"){
		$j('#headDiv').css('display','none');
		$j(window.frames["reportContainer"].contentDocument.getElementById('toolbar')).hide() ;
		$j('#reportContainer').height('100%');
		folderDiv.removeClass('triangle-down');
		folderDiv.addClass('triangle-up');
	}else{
		$j('#headDiv').css('display','block');
		$j(window.frames["reportContainer"].contentDocument.getElementById('toolbar')).show();
		$j(window).trigger('resize');
		folderDiv.removeClass('triangle-up');
		folderDiv.addClass('triangle-down');
	}
	//window.setInterval(function(){$j(window).trigger('resize');window.clearInterval();},500);
	
	
} 	
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