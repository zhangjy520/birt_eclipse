var dropdownDatas = {};
function customReady () {
	var urlPara = "types=dispatch_order_status";
	$.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER")
	).done(function(data1,data2,data3){
		dropdownDatas = data1;
		dropdownDatas.workshop = data2;
		dropdownDatas.workcenter = data3;
		ddwAddOption($("[name='dispatchStatus']"),dropdownDatas.dispatch_order_status);
		ddwAddOptionCodeName($("[name='workshopId']"),dropdownDatas.workshop);
		ddwAddOptionCodeName($("[name='workcenterId']"),dropdownDatas.workcenter);
	    searchOrder(true,1,PAGESIZE);
	}, function(){} );
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
	Mes.loadJson2("/plan/mesPlanDispatch/findSheetPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
		function(result){
			debugger
			result.data.qryParam = param;
			result.data.workshopList = dropdownDatas.workshop;
			result.data.dispatch_order_status = dropdownDatas.dispatch_order_status;
			var userStr = getCommaStr(result.data.list,"updateUser");
			 
			var html = template('detailTab.tmp', result.data);
			var render = template.compile(PAGETEMPLATE);  
			var subhtml = render(result.data);  
        	$('#detailTab').html(html+subhtml);
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
 function on_del_sheet(sender,id,sheetId){
	 var param = 'id='+id+"&sheetId="+sheetId;
	 Mes.sysConfirm('确认删除?删除后数据将不能恢复',function(index){
		 layer.close(index);
		 Mes.getJson2("/plan/mesPlanDispatch/delete/index.do",param,		
			function(result){
				//Mes.sysAlert(JSON.stringify(result.data));
	 			if (result.data >=1){
	 				Mes.sysAlert('删除成功!',function(index){$(sender).parent().parent().remove();layer.close(index)});
	 			}else{
	 				Mes.SysAlert('删除了0条记录,请刷新后重试!');
	 			}
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
	 });
 };
 function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
	  	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
	  	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickGoods','MATRIAL_INFO',function(index, layero){
	  		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
	  		 var customCode = $(rowHtml).find("[name=code]").text()||'';
	  		 $('#goodsId').val(customCode);
	  		 layer.close(index);
			  });
	   };