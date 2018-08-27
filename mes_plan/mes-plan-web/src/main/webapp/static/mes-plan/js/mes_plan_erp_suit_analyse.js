var dropdownDatas={};
function customReady() {
		debugger
		var orderNo = Mes.getUrlParam("orderNo");
		var planNum = Mes.getUrlParam("planNum");
		var totalNum = Mes.getUrlParam("totalNum");
		$('#orderNo').val(orderNo);
		$('#planNum').val(planNum);
		var param = 'orderNo='+orderNo+"&planNum="+planNum+"&totalNum="+totalNum;
		Mes.getJson2("/plan/dubbo/findErpSuitList/index.do",param,	
    		function(result){
    			//Mes.sysAlert(JSON.stringify(result.data));
    			//result.data.dropdownDatas=dropdownDatas;
    			var html = template('detailTab.tmp', result.data);
    		 
            	$('#detailTab').html(html);
            	//$('#detailTab').html(html);
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
    };
