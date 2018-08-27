var dropdownDatas = {dispatch_order_status:[{id: "0", text: "未调度"},{id: "10", text: "已调度"}],
schedule_status:[{id: "0", text: "未排程"},{id: "1", text: "已排程"},{id: "10", text: "已下发"}],
material_use_status:[{id: "0", text: "未生成"},{id: "10", text: "已生成"}],
dispatch_order_status:[]
};

function customReady (){
	//var urlPara = "types=dispatch_order_status,schedule_status";
	//dropdownDatas = Mes.getJsonSync("/plan/common/getdicts/index.do",urlPara);
	//下拉框处理
	var id = Mes.getUrlParam("id");
	if(!id){
		var workshopId = userDeptCode;
		if (!workshopId) {
			ddwClearOption($("#workcenterId"),true);
			$('#workcenterSpan').text('');
			return;
		}
		$.when( Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+workshopId)
		).done(function(data1){
			var workcenterList = data1;
			var workcenterId =workcenterList[0].code;
			if (!workcenterId){
				alert("取登录员工所在部门的工作中心出错!");
				return;
			}
			var scheduleDate = new Date();
			scheduleDate = scheduleDate.Format("yyyy-MM-dd");
			
			loadDataNew(true,workshopId,workcenterId,scheduleDate);
		});
	}else{
		loadData(id);
	}
};


function chgWorkshopId(workshopId,workcenterId,scheduleDate){
	debugger;
	$('#workcenterSpan').text('');
	if (!workshopId) {
		ddwClearOption($("#workcenterId"),true);
		return;
	}
	
	$.when( Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+workshopId)
	).done(function(data1){
		if (!workcenterId){
			ddwClearOption($("#workcenterId"),true);
			ddwAddOption($("#workcenterId"),changeCode2Name(data1));
		}
		loadDataNew(false,workshopId,workcenterId,scheduleDate);
	});
};

var setScheduleDateAHide = function(){
	//$('#scheduleDateA').css('display','none');
}

var loadDataNew  = function(isopen,workshopIdIn,workcenterIdIn,scheduleDateIn){
	debugger
	if (!workshopIdIn || !workcenterIdIn || !scheduleDateIn){
		if (!workcenterIdIn){
			$('#workcenterSpan').text('');
		}
		return;
	}
	 
	var urlPara = "workshopId="+workshopIdIn+"&workcenterId="+workcenterIdIn+"&scheduleDate="+scheduleDateIn
	Mes.getJson2("/plan/schedule/getByUk/index.do",urlPara,		
		function(result){
			var data2={};
			
			$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
					Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+workshopIdIn)
			).done(function(data1,data2){
				var workshopList = data1;
				dropdownDatas.workshopListNew = changeCode2Name(workshopList);
				var workcenterList = data2;
				dropdownDatas.workcenterListNew = changeCode2Name(workcenterList);
				
				if (result.data && !result.data.id){
					
					data2.mesPlanSchedule = {"workshopId":workshopIdIn,"workcenterId":workcenterIdIn,"scheduleDate":scheduleDateIn,"scheduleUser":loginUser,"scheduleUserName":loginUserName};
					data2.mesPlanSchedule.workshopList = workshopList;
					data2.mesPlanSchedule.workcenterList = workcenterList;
					data2.mesPlanSchedule.schedule_status = dropdownDatas.schedule_status;
					data2.mesPlanSchedule.material_use_status = dropdownDatas.material_use_status;
	            	
					var html2 = template('detailTab.tmp', data2.mesPlanSchedule);
			    	$('#detailTab').html(html2);
			    	
			    	var html3= template('qty_div.tmp', data2.mesPlanSchedule);
			    	$('#qty_div').html(html3);
			    	clearDetData();
	            	
				}else{
					if (isopen){
						data2.mesPlanSchedule = {"workshopId":workshopIdIn,"workcenterId":workcenterIdIn,"scheduleDate":scheduleDateIn,"scheduleUser":loginUser,"scheduleUserName":loginUserName};
						data2.mesPlanSchedule.workshopList = workshopList;
						data2.mesPlanSchedule.workcenterList = workcenterList;
						data2.mesPlanSchedule.schedule_status = dropdownDatas.schedule_status;
						data2.mesPlanSchedule.material_use_status = dropdownDatas.material_use_status;
						var html2 = template('detailTab.tmp', data2.mesPlanSchedule);
				    	$('#detailTab').html(html2)
					}else{
						
						if (result.data && result.data.id){
							loadData(result.data.id);
						}
						else{
							hideScheduleDateInput();
							clearDetData();
			            	var mesPlanSchedule =  {"scheduleDate":$('#scheduleDate').val()}
			            	mesPlanSchedule.workshopList = workshopList;
							mesPlanSchedule.workcenterList = workcenterList;
					    	var html3= template('qty_div.tmp',mesPlanSchedule);
					    	$('#qty_div').html(html3);
					    	
					    	chg_unschedule("plan_begin_date");
					    	load_schedule("real_begin_date");
						}
					}
				}
				ddwClearOption($("#workshopId"),true);
				ddwClearOption($("#workcenterId"),true);
				ddwAddOption($("#workshopId"),dropdownDatas.workshopListNew);
				ddwAddOption($("#workcenterId"),dropdownDatas.workcenterListNew);
				$("#workshopId").val(workshopIdIn);
				$("#workcenterId").val(workcenterIdIn);
				if (!workcenterIdIn){
					$('#workcenterSpan').text('');
				} 
			});
			
    	},
    	function(data){
    		alert(JSON.stringify(data))
    	});
};

var  hideScheduleDateInput = function(){
	$('#scheduleDateSpan').css("display","");
	$('#scheduleDateSpan').text($('#scheduleDate').val());
	$('#scheduleDate').css("display","none");
};

var  clearDetData = function(){
	var html4= template('device_div.tmp', {dropdownDatas:dropdownDatas});
	$('#device_div').html(html4);
	//$.cookie('head'+id,JSON.stringify(result.data.mesPlanDispatchDet));
	//$.cookie('erphead'+id,JSON.stringify(result.data.mesPlanMonth));
	
	var html5= template('device_div2.tmp',{dropdownDatas:dropdownDatas});
	$('#device_div2').html(html5);
};

var loadData = function(id){
	var param
	if (!!id){
		//param = "id="+id;
		param = "id="+id;
	}else
	{
		alert("id参数为空!");
		return;
	}
	var orderBy="plan_begin_date"
	Mes.getJson2("/plan/schedule/getsheet/"+orderBy+"/index.do",param,		
    		function(result){
				//$('#newBtn').attr('href','/mes-plan/production/mes_plan_device_parallel_new.html?parId='+id+'&erpBillNo='+result.data.mesPlanDispatchHead.erpBillNo);
				//$('#batchBtn').attr("href","/mes-plan/production/mes_plan_device_batch.html?parId="+id);
				
				$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
						Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER")
				).done(function(data1,data2){
					var workshopList = data1;
					dropdownDatas.workshopListNew = changeCode2Name(workshopList);
					var workcenterList = data2;
					dropdownDatas.workcenterListNew = changeCode2Name(workcenterList);
			
					result.data.mesPlanSchedule.workshopList = workshopList;
					result.data.mesPlanSchedule.workcenterList = workcenterList;
					result.data.dropdownDatas = dropdownDatas;
					var rowCnt = result.data.schedualDeviceList.length;
					result.data.mesPlanSchedule.rowCnt = rowCnt || 0;
	            	var html2 = template('detailTab.tmp', result.data.mesPlanSchedule);
	            	$('#detailTab').html(html2);
	            	
	            	var html3= template('qty_div.tmp', result.data.mesPlanSchedule);
	            	$('#qty_div').html(html3);
	            	//dropdownDatas.workgroupList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.schedualDeviceList,"deviceId"));
	            	//result.data.deviceList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.unschedualDeviceList,"deviceId"))||[];
	            	//result.data.deviceList2 = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.schedualDeviceList,"deviceId"))||[];
	            	
	            	var html4= template('device_div.tmp', result.data);
	            	$('#device_div').html(html4);
	            	
	            	var html5= template('device_div2.tmp', result.data);
	            	$('#device_div2').html(html5);
	            	
	            	setScheduleDateAHide();
	            	refreshSheetStatus(result.data.mesPlanSchedule.sheetStatus);
	            	
	            	
	            	ddwAddOption($("#workshopId"),dropdownDatas.workshopListNew);
	    			ddwAddOption($("#workcenterId"),dropdownDatas.workcenterListNew);
	    			$("#workshopId").val(result.data.mesPlanSchedule.workshopId);
	    			$("#workcenterId").val(result.data.mesPlanSchedule.workcenterId);
				});
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
	
};
function refreshSheetStatus(status){
	if (status == "10"){
		$('#genMatBtn').hide();
	}
}

var changeFolderFun = function(){
	if ($('#detailTab').css('display')=='none')
	{	$('#folderBtn').removeClass('glyphicon-chevron-down');
		$('#folderBtn').addClass('glyphicon-chevron-up')}
	else{
		$('#folderBtn').removeClass('glyphicon-chevron-up');
		$('#folderBtn').addClass('glyphicon-chevron-down')
	}
}


var changeFolderFun2 = function(){
	if ($('#device_div').css('display')=='none')
	{	$('#folderBtn2').removeClass('glyphicon-chevron-down');
		$('#folderBtn2').addClass('glyphicon-chevron-up')}
	else{
		$('#folderBtn2').removeClass('glyphicon-chevron-up');
		$('#folderBtn2').addClass('glyphicon-chevron-down')
	}
}

var changeFolderFun3 = function(){
	if ($('#device_div2').css('display')=='none')
	{	$('#folderBtn3').removeClass('glyphicon-chevron-down');
		$('#folderBtn3').addClass('glyphicon-chevron-up')}
	else{
		$('#folderBtn3').removeClass('glyphicon-chevron-up');
		$('#folderBtn3').addClass('glyphicon-chevron-down')
	}
}

function get_unschedule_parm(orderBy){
	
	var id = $("#id").val();
	var workshopId = $("#workshopId").val();
	var workcenterId = $("#workcenterId").val();
	var scheduleDate = $("#scheduleDate").val();
	var scheduleUser = $("#scheduleUser").val();
	var scheduleUserName = $("#scheduleUserName").val();
	
	
	var schedulefilterValue = $("#schedulefilterValue").val();
	if (!workshopId || !workcenterId || !scheduleDate){
		return "";
	}
	var urlparam ='workshopId='+workshopId+'&workcenterId='+workcenterId+"&scheduleDate="+scheduleDate+"&scheduleUser="+scheduleUser+"&scheduleUserName="+scheduleUserName+"&schedulefilterValue="+schedulefilterValue;
	if (id){
		urlparam = urlparam + "&id="+id;
	}
	if ($("#unscheduleRadio1").prop('checked')){//unscheduleRadio1 只显示延迟单据
		urlparam = urlparam+"&unFinished=1";
	} 
	if (orderBy){
		urlparam = urlparam+"&orderBy="+orderBy;
	}
	return urlparam;
}
var chg_unschedule= function(orderBy){
		var urlparam = get_unschedule_parm(orderBy);
		if (urlparam ===""){
			return;
		}
		Mes.getJson2("/plan/schedule/get/unschedule/index.do",urlparam,		
		function(result){
			result.data.dropdownDatas = dropdownDatas;
			//result.data.deviceList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.unschedualDeviceList,"deviceId"))||[];
        	var html5= template('device_div2.tmp', result.data);
        	
        	$('#device_div2').html(html5);
    	},
    	function(data){
    		//alert(JSON.stringify(data))
    	});
	 
}



var load_schedule= function(orderBy){
	var urlparam = get_unschedule_parm(orderBy);
	if (urlparam ===""){
		return;
	}
	Mes.getJson2("/plan/schedule/get/schedule/index.do",urlparam,		
	function(result){
		debugger
		result.data.dropdownDatas = dropdownDatas;
		//result.data.deviceList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.unschedualDeviceList,"deviceId"))||[];
    	var html4= template('device_div.tmp', result.data);
    	$('#device_div').html(html4);
	},
	function(data){
		//alert(JSON.stringify(data))
	});
 
}

var chg_order = function(orderBy){
	chg_unschedule(orderBy);
}
function getOneRow(tablerow){
	
}
var on_save=function(){
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var validatePass = true;
	
	$("[name=checkflag]:checkbox:checked","#tab2Body").each(function(){
	  var tablerow = $(this).parent().parent()
	  var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='id']").val()
	  var realBeginDate = tablerow.find("[name='realBeginDate']").val();
	  var realEndDate = tablerow.find("[name='realEndDate']").val();
	  var deviceId = tablerow.find("[name='deviceId']").val();
	  var isChgGoods = tablerow.find("[name='isChgGoods']").val();
	  
	  //var price= tablerow.find("[name='p_price']").val();
	  //selectedData.push({Code:code,Name:name,Price:price});
	  //验证日期是否符合条件
	  if (!realBeginDate || !realEndDate || realBeginDate > realEndDate || realBeginDate > scheduleDateStr  || realEndDate < scheduleDateStr){
		  validatePass = false;
		  Mes.sysAlert("行号:"+rowIndex+",排程日期为空或录入错误!",null);
		  return false;
	  }
	  var row = {"id":id,"scheduleStatus":"1","realBeginDate":realBeginDate,"realEndDate":realEndDate,"deviceId":deviceId,"isChgGoods":isChgGoods};
	  selectedData.push(row);
	});
	if (!validatePass) return;
	on_save_inner(selectedData);
}

function on_save_inner(selectedData){
	var urlPara = get_unschedule_parm();
	if (selectedData.length>0){
		Mes.loadJson2("/plan/schedule/batchsave/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
			 	var id = result.data;
			 	
			 	if (id){
			 		loadData(id);
			 	}
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("请选择数据!")
	}
}

function on_save_one(sender){
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	
	  var tablerow = $(sender).parent().parent().parent().parent();
	  var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='id']").val()
	  var realBeginDate = tablerow.find("[name='realBeginDate']").val();
	  var realEndDate = tablerow.find("[name='realEndDate']").val();
	  var deviceId = tablerow.find("[name='deviceId']").val();
	  var isChgGoods = tablerow.find("[name='isChgGoods']").val();
	  
	  //var price= tablerow.find("[name='p_price']").val();
	  //selectedData.push({Code:code,Name:name,Price:price});
	  //验证日期是否符合条件
	  if (!realBeginDate || !realEndDate || realBeginDate > realEndDate || realBeginDate > scheduleDateStr  || realEndDate < scheduleDateStr){
		  Mes.sysAlert("行号:"+rowIndex+",排程日期为空或录入错误!",null);
		  return false;
	  }
	  var row = {"id":id,"scheduleStatus":"1","realBeginDate":realBeginDate,"realEndDate":realEndDate,"deviceId":deviceId,"isChgGoods":isChgGoods};
	  selectedData.push(row);
	  on_save_inner(selectedData);
}

function on_check_one_row(scheduleDateStr,tablerow)
{
	var rowIndex = tablerow.find("[name='rowIndex']").text();
	var id = tablerow.find("[name='id']").val()
	var realBeginDate = tablerow.find("[name='realBeginDate']").val();
	var realEndDate = tablerow.find("[name='realEndDate']").val();
	var deviceId = tablerow.find("[name='deviceId']").val();
	var isChgGoods = tablerow.find("[name='isChgGoods']").val();
	  
	//var price= tablerow.find("[name='p_price']").val();
	//selectedData.push({Code:code,Name:name,Price:price});
	//验证日期是否符合条件
	if (!realBeginDate || !realEndDate || realBeginDate > realEndDate || realBeginDate > scheduleDateStr  || realEndDate < scheduleDateStr){
		Mes.sysAlert("行号:"+rowIndex+",排程日期为空或录入错误!",null);
		return null;
	}
	var row = {"id":id,"scheduleStatus":"10","realBeginDate":realBeginDate,"realEndDate":realEndDate,"deviceId":deviceId,"isChgGoods":isChgGoods};
	return row;
}
function on_check_one(sender){
	
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var tablerow = $(sender).parent().parent().parent().parent();
	var selectRow =  on_check_one_row(scheduleDateStr,tablerow);
	if (selectRow){
	  selectedData.push(selectRow);
	  on_check_one_inner(selectedData);
	}
}

function on_check_one_inner(selectedData){
	var urlPara = get_unschedule_parm();
	if (selectedData.length>0){
		Mes.loadJson2("/plan/schedule/batchcheck/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
			 	var id = result.data.id;
			 	
			 	if (id){
			 		Mes.sysAlert("下发成功",function(index){
			 			layer.close(index);
			 			loadData(id);
			 		});
			 	}else{
			 		Mes.sysAlert("更新失败,请刷新后重试!");
			 	}
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("请选择数据!")
	}
}

var validateBeginDate = function(sender){
	var scheduleDateStr = $('#scheduleDate').val();
	var beginDateStr = $(sender).val();
	var endDateStr = $(sender).parent().next().find("[name='realEndDate']").val();
	if (!beginDateStr){
		return;
	}
	if (beginDateStr > scheduleDateStr){
		Mes.sysAlert("开始日始不能大于排程日期",null);
		$(sender).val(''); 
		return;
	}
	
	if (!!endDateStr && beginDateStr > endDateStr){
		Mes.sysAlert("开始日始不能大于结束日期",null);
		$(sender).val(''); 
		return;
	}
	/*if (beginDateStr && endDateStr){
		var datearr = beginDateStr.split('-');
		var beginDate = new Date(datearr[0],datearr[1],datearr[2]);
		datearr = endDateStr.split('-');
		var endDate = new Date(datearr[0],datearr[1],datearr[2]);
		if ((endDate - beginDate)/24/3600/1000 > 9){
			Mes.sysAlert("日期跨度大于10天",null);
			$(sender).val(''); 
		}
	}*/
};


var validateEndDate = function(sender){
	debugger;
	var scheduleDateStr = $('#scheduleDate').val();
	var beginDateStr = $(sender).parent().prev().find("[name='realBeginDate']").val();
	var endDateStr = $(sender).val();
	if (!beginDateStr){
		return;
	}
	if (endDateStr < scheduleDateStr){
		Mes.sysAlert("结束日期不能小于排程日期",null);
		$(sender).val(''); 
		return;
	}
	
	if (!!endDateStr && beginDateStr > endDateStr ){
		Mes.sysAlert("结束日始不能小于开始日期",null);
		$(sender).val(''); 
		return;
	}
//	if (beginDateStr && endDateStr){
//		var datearr = beginDateStr.split('-');
//		var beginDate = new Date(datearr[0],datearr[1],datearr[2]);
//		datearr = endDateStr.split('-');
//		var endDate = new Date(datearr[0],datearr[1],datearr[2]);
//		if ((endDate - beginDate)/24/3600/1000 > 9){
//			Mes.sysAlert("日期跨度大于10天",null);
//			$(sender).val(''); 
//		}
//	}
};


var qtyAna = function(){
	var selectedData = [];
	$(":checkbox:checked","#tab2Body").each(function(){
	  var tablerow = $(this).parent().parent()
	  var id = tablerow.find("[name='id']").val()
	  //var name= tablerow.find("[name='p_name']").val();
	  //var price= tablerow.find("[name='p_price']").val();
	  //selectedData.push({Code:code,Name:name,Price:price});
	  selectedData.push({"id":id});
	});
	// alert(text1);
	
};

function on_saveMaterialUseOne(sender){
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var tablerow = $(sender).parent().parent().parent().parent();
	var rowIndex = tablerow.find("[name='rowIndex']").text();
	var id = tablerow.find("[name='id']").val()
	var realBeginDate = tablerow.find("[name='realBeginDate']").val();
	var realEndDate = tablerow.find("[name='realEndDate']").val();
	var deviceId = tablerow.find("[name='deviceId']").val();
	var isChgGoods = tablerow.find("[name='isChgGoods']").val();
	  
	//验证日期是否符合条件
	if (!realBeginDate || !realEndDate || realBeginDate > realEndDate || realBeginDate > scheduleDateStr  || realEndDate < scheduleDateStr){
		Mes.sysAlert("行号:"+rowIndex+",排程日期为空或录入错误!",null);
		return false;
	}
	var row = {"id":id,"materialUseStatus":"10"};
	  selectedData.push(row);
	  var urlPara = get_unschedule_parm();
		if (selectedData.length>0){
			Mes.loadJson2("/plan/schedule/saveMaterialUse/det.do",urlPara,JSON.stringify(selectedData),		
	     		function(result){
				 	var id = result.data.id;
				 	
				 	if (id){
				 		Mes.sysAlert("生成领料成功",function(index){
				 			layer.close(index);
				 			loadData(id);
				 		});
				 	}else{
				 		Mes.sysAlert("更新失败,请刷新后重试!");
				 	}
	 	    	},
	 	    	function(data){
	 	    		Mes.sysAlert(JSON.stringify(data))
	 	    });
		}else{
			Mes.sysAlert("请选择数据!")
		}
};

function on_cancel(headid,rowId){
	var urlPara = "id="+rowId;
	
	Mes.getJson2("/plan/schedule/cancel/det.do",urlPara, 	
 		function(result){
			 if (result.data == 1){
				 	Mes.sysAlert("取消成功",function(index){
					layer.close(index);	 
					loadData(headid);
				 });
			 }else{
				 Mes.sysAlert("取消了["+result.data+"]条记录",function(index){
						layer.close(index);	 
				 });
			}
    	},
    	function(data){
    		Mes.sysAlert(JSON.stringify(data))
    });
};

function on_cancelcheck_one(headid,rowId){
	var urlPara = "id="+rowId;
	
	Mes.getJson2("/plan/schedule/cancelcheck/det.do",urlPara, 	
 		function(result){
			 if (result.data == 1){
				 	Mes.sysAlert("取消下发成功",function(index){
					layer.close(index);	 
					loadData(headid);
				 });
			 }else{
				 Mes.sysAlert("取消了["+result.data+"]条记录",function(index){
						layer.close(index);	 
				 });
			}
    	},
    	function(data){
    		Mes.sysAlert(JSON.stringify(data))
    });
};

function on_batchDown(){
	
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var validatePass = true;
	
	$("[name=checkflag]:checkbox:checked","#tab1Body").each(function(){
		var tablerow = $(this).parent().parent();
		var selectRow =  on_check_one_row(scheduleDateStr,tablerow);
		validatePass = !!selectRow;
		selectedData.push(selectRow);
	});
	if (!validatePass) {
		Mes.sysAlert('数据验证失败');
		return;
	}
	
	if (selectedData.length>=1){
	 
	  on_check_one_inner(selectedData);
	}
};

function on_batchMaterial(){
	
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	
	
	var validatePass = true;
	
	$("[name=checkflag]:checkbox:checked","#tab1Body").each(function(){
		var tablerow = $(this).parent().parent();
		var rowIndex = tablerow.find("[name='rowIndex']").text();
		var id = tablerow.find("[name='id']").val()
		var realBeginDate = tablerow.find("[name='realBeginDate']").val();
		var realEndDate = tablerow.find("[name='realEndDate']").val();
		var deviceId = tablerow.find("[name='deviceId']").val();
		var isChgGoods = tablerow.find("[name='isChgGoods']").val();
		  
		//验证日期是否符合条件
		if (!realBeginDate || !realEndDate || realBeginDate > realEndDate || realBeginDate > scheduleDateStr  || realEndDate < scheduleDateStr){
			Mes.sysAlert("行号:"+rowIndex+",排程日期为空或录入错误!",null);
			validatePass = false;
		}
		var row = {"id":id,"materialUseStatus":"10"};
		selectedData.push(row);
	});
	if (!validatePass) {
		Mes.sysAlert('数据验证失败');
		return;
	}
	if (selectedData.length >=1 ){
	  var urlPara = get_unschedule_parm();
		if (selectedData.length>0){
			Mes.loadJson2("/plan/schedule/saveMaterialUse/det.do",urlPara,JSON.stringify(selectedData),		
	     		function(result){
				 	var id = result.data.id;
				 	
				 	if (id){
				 		Mes.sysAlert("生成领料成功",function(index){
				 			layer.close(index);
				 			loadData(id);
				 		});
				 	}else{
				 		Mes.sysAlert("更新失败,请刷新后重试!");
				 	}
	 	    	},
	 	    	function(data){
	 	    		Mes.sysAlert(JSON.stringify(data))
	 	    });
		}else{
			Mes.sysAlert("请选择数据!")
		}
	}
};
function onSelectAll(sender){
	if ($(sender).prop('checked')){
		$(sender).parent().parent().parent().next().find("input:checkbox").prop('checked','checked');
	}else{
		$(sender).parent().parent().parent().next().find("input:checkbox").prop('checked','');
	}
};

function onFilter(sender,elementId){
	var filterValue = $(sender).val().trim();
	if (filterValue){
		$(elementId).children().each(function(){
			var found = false;
			var cols = $(this).children();
			var colCnt = cols.length;
			for (var i = 2 ;i < colCnt; i++)
			{
				if ($(cols[i]).text().indexOf(filterValue) >=0){
					found = true;
					break;
				}
			}
			if (found){
				$(this).css('display','');
			} else{
				$(this).css('display','none');
			}
		});
	}else{
		$(elementId).children().each(function(){
			$(this).css('display','');
		});
	}
};
