function customReady(){
	var id = Mes.getUrlParam("id");
	if(!id){
		var data2={}
		data2.mesPlanShift = {"workshopId":"3","shiftId":"1"};
		var html2 = template('detailTab.tmp', data2.mesPlanShift);
    	$('#detailTab').html(html2)
    	
	}else{
		loadData(id);
	}
};

var setScheduleDateAHide = function(){
	//$('#scheduleDateA').css('display','none');
}

var loadDataNew  = function(isopen,workshopIdIn,shiftIdIn,shiftDateIn){
	var urlPara = "workshopId="+workshopIdIn+"&shiftId="+shiftIdIn+"&shiftDate="+shiftDateIn
	Mes.getJson2("/plan/mesPlanShift/getsheetbyuk/index.do",urlPara,		
		function(result){
			var data2={};
			if (result.data && result.data.mesPlanShiftHead && result.data.mesPlanShiftHead.id){
				var html2 = template('detailTab.tmp', result.data.mesPlanShiftHead);
            	$('#detailTab').html(html2);
            	
            	var html3= template('qty_div.tmp', result.data.mesPlanShiftHead);
            	$('#qty_div').html(html3);
            	
            	var html4= template('device_div.tmp', result.data);
            	$('#device_div').html(html4);
            	refreshStatus(result.data.mesPlanShiftHead.sheetStatus);
			 
			}else{
			    if (isopen){
			    	data2.mesPlanShift = {"workshopId":workshopIdIn,"shiftId":shiftIdIn};
					var html2 = template('detailTab.tmp', data2.mesPlanShift);
			    	$('#detailTab').html(html2)
				}else{
	            	var shiftDate = $('#shiftDate').val();
	            	var html3= template('qty_div.tmp', {'shiftDate':shiftDate});
	            	$('#qty_div').html(html3);

	            	var html4= template('device_div.tmp', result.data);
	            	$('#device_div').html(html4);
					//clearDetData();
				}
			    refreshStatus('0');
			}
			
    	},
    	function(data){
    		alert(JSON.stringify(data))
    	});
}

var  hideScheduleDateInput = function(){
	$('#scheduleDateSpan').css("display","");
	$('#scheduleDateSpan').text($('#scheduleDate').val());
	$('#scheduleDate').css("display","none");
}

var  clearDetData = function(){
	var html4= template('device_div.tmp', {});
	$('#device_div').html(html4);
	//$.cookie('head'+id,JSON.stringify(result.data.mesPlanDispatchDet));
	//$.cookie('erphead'+id,JSON.stringify(result.data.mesPlanMonth));
	
	//var html5= template('device_div2.tmp',{});
	//$('#device_div2').html(html5);
}



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
	Mes.getJson2("/plan/mesPlanShift/getsheet/index.do",param,		
    		function(result){
            	var html2 = template('detailTab.tmp', result.data.mesPlanShiftHead);
            	$('#detailTab').html(html2);
            	
            	var html3= template('qty_div.tmp', result.data.mesPlanShiftHead);
            	$('#qty_div').html(html3);
            	
            	var html4= template('device_div.tmp', result.data);
            	$('#device_div').html(html4);
//            	setScheduleDateAHide();
            	refreshStatus(result.data.mesPlanShiftHead.sheetStatus);
//            	if (result.data.mesPlanShiftHead.sheetStatus && result.data.mesPlanShiftHead.sheetStatus == "10"){
//            		 $('#saveBtn').hide();
//            		 $('#checkBtn').hide();
//            		 $('.select2').select2({disabled: true});
//            	}else{
//            		$('#checkBtn').show();
//            		$('.select2').select2();
//            	}
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
	
}

function refreshStatus(sheetStatus){
	if (sheetStatus && sheetStatus == "10"){
		 $('#saveBtn').hide();
		 $('#checkBtn').hide();
		 $('.select2').select2({disabled: true});
	}else{
		$('#checkBtn').show();
		$('.select2').select2();
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

function get_unschedule_obj(){
	var id = $("#id").val();
	var sheetId = $("#sheetId").val();
	var workshopId = $("#workshopId").val();
	var shiftDate = $("#shiftDate").val();
	var shiftId = $("#shiftId").val();
	var shiftUser = $("#shiftUser").val();
	if (!workshopId || !shiftId || !shiftDate || !shiftUser){
		return null;
	}
	else{
		return {'id':id,'sheetId':sheetId,'workshopId':workshopId,'shiftDate':shiftDate,'shiftId':shiftId,'shiftUser':shiftUser};
	}
	
}

function get_unschedule_parm(orderBy){
	var id = $("#id").val();
	var sheetId = $("#sheetId").val();
	var workshopId = $("#workshopId").val();
	var shiftDate = $("#shiftDate").val();
	var shiftId = $("#shiftId").val();
	var shiftUser = $("#shiftUser").val();
	if (!workshopId || !shiftId || !shiftDate || !shiftUser){
		return "";
	}
	var urlparam ='workshopId='+workshopId+'&shiftId='+shiftId+"&shiftDate="+shiftDate+"&shiftUser="+shiftUser;
	if (id){
		urlparam = urlparam + "&id="+id;
	}
	if (sheetId){
		urlparam = urlparam + "&sheetId="+sheetId;
	}
	if ($("#unscheduleRadio1").prop('checked')){//unscheduleRadio1 只显示延迟单据
		urlparam = urlparam+"&unFinished=1";
	} 
	if (orderBy){
		urlparam = urlparam+"&orderBy="+orderBy;
	}
	return urlparam;
}
 



var load_schedule= function(orderBy){
	var urlparam = get_unschedule_parm(orderBy);
	if (urlparam ===""){
		return;
	}
	Mes.getJson2("/plan/schedule/get/schedule/index.do",urlparam,		
	function(result){
    	var html4= template('device_div.tmp', result.data);
    	$('#device_div').html(html4);
	},
	function(data){
		//alert(JSON.stringify(data))
	});
 
}

var chg_order = function(orderBy){
	//chg_unschedule(orderBy);
}

var on_save1=function(){
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var validatePass = true;
	debugger
	$("[name=checkflag]:checkbox:checked","#tab2Body").each(function(){
	  var tablerow = $(this).parent().parent()
	  var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='id']").val()
	  var realBeginDate = tablerow.find("[name='realBeginDate']").val();
	  var realEndDate = tablerow.find("[name='realEndDate']").val();
	  //var price= tablerow.find("[name='p_price']").val();
	  //selectedData.push({Code:code,Name:name,Price:price});
	  //验证日期是否符合条件
	  if (!realBeginDate || !realEndDate || realBeginDate > realEndDate || realBeginDate > scheduleDateStr  || realEndDate < scheduleDateStr){
		  validatePass = false;
		  Mes.sysAlert("行号:"+rowIndex+",排程日期为空或录入错误!",null);
		  return;
	  }
	  
	  selectedData.push({"id":id,"scheduleStatus":"1","realBeginDate":realBeginDate,"realEndDate":realEndDate});
	});
	if (!validatePass) return;
	var urlPara = get_unschedule_parm();
	if (selectedData.length>0){
		Mes.loadJson2("/plan/schedule/batchsave/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
			 	var id = result.data;
			 	debugger
			 	if (id){
			 		loadData(id);
			 	}
	     		//Mes.sysAlert(result.msg,null);
	     				//function(){self.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;}
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}
}
var validateBeginDate = function(sender){
	debugger;
	var scheduleDateStr = $('#scheduleDate').val();
	var beginDateStr = $(sender).val();
	var endDateStr = $(sender).parent().next().find("[name='realEndDate']").val();
	if (!beginDateStr){
		return;
	}
	if (beginDateStr > scheduleDateStr){
		Mes.sysAlert("开始日始不能大于排程日期",null);
		$(sender).val(''); 
	}
	
	if (!!endDateStr && beginDateStr > endDateStr){
		Mes.sysAlert("开始日始不能大于结束日期",null);
		$(sender).val(''); 
	}
}


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
	}
	
	if (!!endDateStr && beginDateStr > endDateStr ){
		Mes.sysAlert("结束日始不能小于开始日期",null);
		$(sender).val(''); 
	}
}


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
	
}

 function setchanged (sender){
	$(sender).parent().parent().parent().parent().parent().parent().find("[name='saveBtn']").prop('disabled','');
	$('#checkBtn').hide();
}
 
 
 function setchanged2 (sender){
	 debugger
		$(sender).parent().parent().parent().parent().find("[name='saveBtn']").prop('disabled','');
		$('#checkBtn').hide();
	}
 
 
 
 
 function on_check(){
	var id = $('#id').val();
	if (!id) {
		Mes.sysAlert('请保存后重新操作!');
		 return;
	}
	var urlPara = "id=" + id;
	Mes.getJson2("/plan/mesPlanShift/check/index.do",urlPara,	
 		function(result){
			Mes.sysAlert('下发成功!',function(index){
				layer.close(index);
				loadData(id);
			});
    	},
    	function(data){
    		Mes.sysAlert(JSON.stringify(data))
    });
 }
 
 function on_save(){
	 var urlPara = get_unschedule_parm();
	 if (!urlPara){
		 //Mes.sysAlert('',null);
		 return;
	 }
	 var detDatas = [];
	 var data=detDatas;
	  
	 $("#tabBody").children("tr").each(function(){
		  var tablerow = $(this);
		  var saveBtnStatus = tablerow.find("[name='saveBtn']").attr("disabled") ;
		  if (!saveBtnStatus || saveBtnStatus != "disabled"){
			  var shiftQty = tablerow.find("[name='shiftQty']").val();
			  var detid =  tablerow.find("[name='detId']").val();
			  var srcBillNo =  tablerow.find("[name='billNo']").val();//排程单号为排次源单号
			  var erpBillNo =  tablerow.find("[name='erpBillNo']").val();
			  var detData={'id':detid,'shiftQty':shiftQty,'srcBillNo':srcBillNo,'erpBillNo':erpBillNo};
			  var subDatas= [];
			  detData.mesPlanShiftUserList = subDatas;
			  detDatas.push(detData);
			  var subRows  = $(this).find("tr") ;
			  if (subRows && subRows.length && subRows.length>=3){
				  var cols1 = $(subRows[1]).children("td");
				  var cols2 = $(subRows[1]).children("td");
				  $(subRows[0]).children("th").each(function(index){
					 var technicId= $(this).find("[name='technicId']").val();
					 var id= $(this).find("[name='id']").val();
					 var sheetId= $(this).find("[name='sheetId']").val();
					 var technicUser = $(cols1[index]).find("[name='technicUser']").val()|| '';
					 technicUser = technicUser.toString();
					 var srcBillNo  = $(this).find("[name='srcBillNo']").val();
					 var technicBeginDate = $(cols2[index]).find("[name='technicBeginDate']").val();
					 var technicEndDate = $(cols2[index]).find("[name='technicEndDate']").val();
					  subData ={'id':id,'sheetId':sheetId,'technicId':technicId,'technicUser':technicUser,'srcBillNo':srcBillNo};
					  subDatas.push(subData);
				  });
				 
			  }	  
		  }
	});
	 if (data.length>0){
			Mes.loadJson2("/plan/mesPlanShift/batchsave/det.do",urlPara,JSON.stringify(data),		
	     		function(result){
				 	var id = result.data;
				 	if (id){
				 		Mes.sysAlert(result.msg,function(index){
				 			layer.close(index);
				 			loadData(id);
				 		});
				 	}else{
				 		Mes.sysAlert(result.msg,null);
				 	}
	 	    	},
	 	    	function(data){
	 	    		Mes.sysAlert(JSON.stringify(data))
	 	    });
		}else{
			Mes.sysAlert('没有待保存数据');
		}
 }


 function chg_shiftDet (orderBy){
		var urlparam = get_unschedule_parm(orderBy);
		if (urlparam ===""){
			return;
		}
		Mes.getJson2("/plan/mesPlanShift/getsheetbyuk/index.do",urlparam,		
		function(result){
			debugger
        	var html4= template('device_div.tmp', result.data);
        	$('#device_div').html(html4);
    	},
    	function(data){
    		//alert(JSON.stringify(data))
    	});
}