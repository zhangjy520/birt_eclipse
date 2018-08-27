var dropdownDatas={};
function customReady() {
	$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00")
	).done(function(data1){
		var workshopList = data1;
		dropdownDatas.workshop = changeCode2Name(workshopList);
		ddwAddOption($("[name='workshopId']"),dropdownDatas.workshop);
		$('#scheduleDate').datepicker({
		    format: 'yyyy-mm-dd',
		    language: "zh-CN",//设置语言
		     autoclose: true//设置是否关闭
		}); 
		var todayStr = (new Date());
		var scheduleDate = Mes.getUrlParam("scheduleDate")||todayStr.Format('yyyy-MM-dd');
		$("#scheduleDate").datepicker("setDate",scheduleDate);
	    searchOrder(true,1,10);
	});
	};
 
    function searchOrder(isFirQry,pageNo,pageSize,lastPara){
    	var param ;
    	if (isFirQry){
    		param = $('#from_condition').serialize();
    	}else{
    		//if (!lastPara) {alert('未设置分页参数!');return;}
    		param = lastPara;
    	}
    	param = rapRangeUrl(param);
    	//(argUrl,urlData,jsonData,succCallBack,errorCallback)
    	//Mes.loadJson2("/test1/"+pageNo+"/"+pageSize+"/a.do",param,"{}",
    	Mes.loadJson2("/plan/use/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
    		function(result){
    			debugger
    			result.data.qryParam = param;
    			//Mes.sysAlert(JSON.stringify(result.data));
    			result.data.dropdownDatas=dropdownDatas;
    			var html = template('detailTab.tmp', result.data);
    			var render = template.compile(PAGETEMPLATE);  
    			var subhtml = render(result.data);  
            	$('#detailTab').html(html+subhtml);
            	//$('#detailTab').html(html);
	    		//alert(JSON.stringify(result.data))
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
    }
     function fun_cancel(){
    	 layer.confirm("请确认是否取消释放？",
                 {
                     btn: ['是', '否'],
                     btn1: function () {
                         layer.alert("取消释放成功！",
                             function () {
                                 layer.closeAll();
                             });
                     },
                     btn2: function () {
                     }
                 });
     }