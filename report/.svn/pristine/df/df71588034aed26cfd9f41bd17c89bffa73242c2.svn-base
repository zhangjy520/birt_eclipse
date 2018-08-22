<jsp:directive.include file="includes/top.jsp" />
<%@page pageEncoding="UTF-8"%>
<h2 style="text-align:center">钻取表</h2>
<birt:parameterPage
    id="parameterPage"
    name="form1"
    isCustom="true"
    pattern="frameset"
    reportDesign="demo_drilldown3.rptdesign"
    target="_blank"
    showTitle="false"
    scrolling="no"
    format="html">
 <table>
 	<tr>
 		<td style="width:10%"><span>开始日期</span></td><td style="width:15%"><input id="BuildDate1" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyyMMdd"  onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
 		<td style="width:10%"><span>结束日期</span></td><td style="width:15%"><input id="BuildDate2" type="text" maxlength="20" class="form-control input-sm Wdate" pattern="yyyyMMdd"  onclick="WdatePicker({dateFmt:'yyyyMMdd'});"/></td>
 		<td><span>联动参数1</span></td><td> <birt:paramDef id="cat2" name="cat2" cssClass="form-control input-sm" value=""/></td>
 		<td colspan=2 style="width:25%"></td>
 	</tr>
 </table>
</birt:parameterPage>  
<script type="text/javascript">
onReadyCus = function(){
	var selectList = $j('#parameterPage').find('input:text:enabled');
	$j.each(selectList,function(i,item){
		if (item && item.id && item.id=="cat2")
		{
			item.value="1001";
		}
	});
}
</script>
<jsp:directive.include file="includes/bottom.jsp" />
<%-- 

<div style="text-align:center;margin:10px 0px">
 			<input type="submit" style="width:80px" class="btn btn-primary" value="查    询" onclick="return onQuery('#reportContainer');"/>
			<button  type="button" style="width:80px;text-align:left" class="btn"  onclick="return onQuery();"><img src="/MesReport/webcontent/birt/images/ExportReport.gif"  class="localeImg"/>导    出</button>
			<!-- <div class="btn-group"  role="group" >
				<button  type="button"  class="btn dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="caret"></span> <span class="sr-only">Toggle Dropdown</span> style="margin:8px 0px"
				</button>
				<ul class="dropdown-menu">
					<li><a href="#"><img src="/MesReport/webcontent/MesReport/images/Export.gif"  alt="" />Excel2003</a></li>
					<li><a href="#"><img src="/MesReport/webcontent/MesReport/images/Export.gif"  alt="" />Excel2007</a></li>
					<li><a href="#"><img src="/MesReport/webcontent/MesReport/images/Export.gif"  alt="" />Word</a></li>
				</ul>
			</div> -->
 			<input type="reset" style="width:80px" type="button" class="btn" value="清    空" onclick="return onClear();"/>
</div>

<iframe id="reportContainer" style="width:100%;height:400px" src="/MesReport/frameset?__showtitle=false&__toolbar=false&<%=request.getQueryString()%>" >
</iframe>

<script src="/MesReport/webcontent/MesReport/ajax/utility/MesReportUtility.js" type="text/javascript"></script>
<script src="/MesReport/js/jquery-1.8.3.js" type="text/javascript"></script>
<script src="/MesReport/js/jquery-1.4.1.cookie.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var $j = jQuery.noConflict();  
	$j(document).ready(function (){  
		var selectList = $j('#parameterPage').find('input:text:enabled');
		$j.each(selectList,function(i,item){
			if (item && item.id && item.id=="cat2")
			{
				item.value="1001";
			}
		});
	});  

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
		window.open('<%=baseUrl%>'+'&__format=xls&showData=1'+queryText);   
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
</script>
<script src="/MesReport/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/MesReport/bootstrap_3.1.1/js/bootstrap.js" type="text/javascript"></script>
</body>
</html> --%>

