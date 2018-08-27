dropdownDatas = {};
function customReady () {
	var urlPara = "types=dispatch_order_status";
	$('#planBeginDate1').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "zh-CN",//设置语言
	     autoclose: true//设置是否关闭
	});
	$('#planBeginDate2').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "zh-CN",//设置语言
	     autoclose: true//设置是否关闭
	});
	
	$.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER")
	).done(function(data1,data2,data3){
		dropdownDatas = data1;
		//去掉登记状态
		dropdownDatas.dispatch_order_status.splice(0,1);  
		var workshopList = data2;
		dropdownDatas.workshop = changeCode2Name(workshopList);
		var workcenterList = data3;
		dropdownDatas.workcenter = changeCode2Name(workcenterList);
		
		ddwAddOption($("[name='dispatchStatus']"),dropdownDatas.dispatch_order_status);
		ddwAddOption($("[name='workshopId']"),dropdownDatas.workshop);
		ddwAddOption($("[name='workcenterId']"),dropdownDatas.workcenter);
		$("[name='dispatchStatus']").val('10').trigger('change');
		searchOrder(true,1,PAGESIZE);
	});
};
 
    function searchOrder(isFirQry,pageNo,pageSize,lastPara){
    	var param ;
    	debugger
    	if (isFirQry){
    		 param = $('#from_condition').serialize();
    		 if (!$("[name='dispatchStatus']").val()){
    			 //过滤登记状态
    			 param = param.replace('dispatchStatus=','dispatchStatus2=0')
    		 }
    	}else{
    		//if (!lastPara) {alert('未设置分页参数!');return;}
    		param = lastPara;
    	}
    	param = rapRangeUrl(param);
    	//(argUrl,urlData,jsonData,succCallBack,errorCallback)
    	//Mes.loadJson2("/test1/"+pageNo+"/"+pageSize+"/a.do",param,"{}",
    	Mes.loadJson2("/plan/mesPlanDispatch/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
    		function(result){
    			debugger
    			result.data.qryParam = param;
    			result.data.dispatch_order_status = dropdownDatas.dispatch_order_status;
    			result.data.workshopList = dropdownDatas.workshop;
    			result.data.workcenterList = dropdownDatas.workcenter;
    			
    			//var userStr = getCommaStr(result.data.list,"createUser");
    			//Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=STAFF_INFO&codeList=" + userStr),
    			$.when(
    					Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT"),
    					Mes.getJsonSync2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+WORKSHOP_ARRANGE),
    					Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(result.data.list,"goodsId"))
    			).done(function(data1,data2,data3){
        			result.data.shiftIdList = data1;
        			result.data.shiftStyleList = data2;
        			result.data.arrangeGroupList =  ARRANGEGROUPLIST;
        			result.data.goodsList = data3;
    				
    				var html = template('detailTab.tmp', result.data);
       			 
        			var render = template.compile(PAGETEMPLATE);  
        			var subhtml = render(result.data);  
        			
                	$('#detailTab').html(html+subhtml);
                	
    			});
    			
	    		//alert(JSON.stringify(result.data))
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
    };
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
     };
     function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
      	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
      	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickGoods','MATRIAL_INFO',function(index, layero){
      		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
      		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
      		 $('#goodsId').val(rowCode);
      		 layer.close(index);
  		  });
       };