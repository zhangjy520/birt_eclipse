var dropdownDatas={};
function customReady() {
	$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER")
	).done(function(data1,data2){
		var workshopList = data1;
		dropdownDatas.workshopList = workshopList;
		dropdownDatas.workshopListNew = changeCode2Name(workshopList);
		var workcenterList = data2;
		dropdownDatas.workcenterList = workcenterList;
		dropdownDatas.workcenterListNew = changeCode2Name(workcenterList);
		
		ddwAddOption($("[name='workshopId']"),dropdownDatas.workshopListNew);
		ddwAddOption($("[name='workcenterId']"),dropdownDatas.workcenterListNew);
		
		$('#scheduleDate').datepicker({
		    format: 'yyyy-mm-dd',
		    language: "zh-CN",//设置语言
		     autoclose: true//设置是否关闭
		});
		
	    searchOrder(true,1,PAGESIZE);
	})
	
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
	Mes.loadJson2("/plan/schedule/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
		function(result){
			debugger
			result.data.qryParam = param;
			
			result.data.workshopList = dropdownDatas.workshopList;
			result.data.workcenterList = dropdownDatas.workcenterList;
			
			var html = template('detailTab.tmp', result.data);
			var render = template.compile(PAGETEMPLATE);  
			var subhtml = render(result.data);  
			
        	$('#detailTab').html(html+subhtml);
    		//alert(JSON.stringify(result.data))
    	},
    	function(data){
    		//alert(JSON.stringify(data))
    	});
};
//     function fun_cancel(){
//    	 layer.confirm("请确认是否取消释放？",
//                 {
//                     btn: ['是', '否'],
//                     btn1: function () {
//                         layer.alert("取消释放成功！",
//                             function () {
//                                 layer.closeAll();
//                             });
//                     },
//                     btn2: function () {
//                     }
//                 });
//     }


function on_del_sheet(sender,id,sheetId){
	 var param = 'id='+id+"&sheetId="+sheetId;
	 Mes.sysConfirm('确认删除?删除后数据将不能恢复',function(index){
		 layer.close(index);
		 Mes.getJson2("/plan/schedule/delete/index.do",param,		
			function(result){
				//Mes.sysAlert(JSON.stringify(result.data));
	 			if (result.data >=1){
	 				debugger
	 				Mes.sysAlert('删除成功!',function(index){$(sender).parent().parent().parent().parent().remove();layer.close(index)});
	 			}else{
	 				Mes.SysAlert('删除了0条记录,请刷新后重试!');
	 			}
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
	 });
};
