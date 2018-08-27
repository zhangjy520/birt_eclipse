var dropdownDatas = {};
function customReady() {
	$.when(Mes.getJsonSync2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
			Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT"),
			Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER")
	).done(function(data1,data2,data3){
		var workshopList = data1;
		dropdownDatas.workshop = changeCode2Name(workshopList);
		
		var shiftList = data2;
		dropdownDatas.shiftList = changeCode2Name(shiftList);
		
		var workcenterList = data3;
		dropdownDatas.workcenterList = changeCode2Name(workcenterList);
		
		ddwAddOption($("[name='workshopId']"),dropdownDatas.workshop);
		ddwAddOption($("[name='workcenterId']"),dropdownDatas.workcenterList);
		ddwAddOption($("[name='shiftId']"),dropdownDatas.shiftList);
		
		$('#shiftDate').datepicker({
		    format: 'yyyy-mm-dd',
		    language: "zh-CN",//设置语言
		     autoclose: true//设置是否关闭
		});
		
	    searchOrder(true,1,PAGESIZE);
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
	Mes.loadJson2("/plan/mesPlanTempShift/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
		function(result){
			result.data.qryParam = param;
			result.data.dropdownDatas = dropdownDatas;
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
function onClickUser(isFirQry,pageNo,pageSize,lastPara,isOpened){
 	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
 	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','STAFF_INFO',function(index, layero){
 		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
 		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
 		 var rowCodeName = $(rowHtml).find("[name=name]").text()||'';
 		 $('#technicUser').val(rowCode);
 		 layer.close(index);
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