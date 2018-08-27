	var dropdownDatas={};
	function customReady() {
		debugger
		var goodsId = Mes.getUrlParam("goodsId");
		$('#goodsId').val(goodsId);
		var erpBillNo = Mes.getUrlParam("erpBillNo");
		$('#erpBillNo').val(erpBillNo);
		
		
	    searchOrder(true,1,10);
	};
 
    function searchOrder(isFirQry,pageNo,pageSize,lastPara){
    	var param ;
    	if (isFirQry){
    		param = $('#from_condition').serialize();
    	}else{
    		//if (!lastPara) {alert('未设置分页参数!');return;}
    		param = lastPara;
    	}
    	//param = rapRangeUrl(param); 无权限限制
    	Mes.loadJson2("/plan/mesPlanMonth/queryInventoryInfoPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
    		function(result){
    			debugger
    			result.data.qryParam = param;
    			//Mes.sysAlert(JSON.stringify(result.data));
    			var codeList = getCommaStr(result.data.list,'locationCode');
    			$.when(
    				Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=STOCK_LOCATION&codeList="+codeList)
    				).done(function(data1){
    				debugger
    				dropdownDatas.stockLocationList=  data1;
    				result.data.dropdownDatas=dropdownDatas;
        			var html = template('detailTab.tmp', result.data);
        			var render = template.compile(PAGETEMPLATE);  
        			var subhtml = render(result.data);  
                	$('#detailTab').html(html+subhtml);
    			});
    			
    			
    			
            	//$('#detailTab').html(html);
	    		//alert(JSON.stringify(result.data))
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
    }