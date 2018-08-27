var dropdownDatas = {'shiftStatus':[{id: "0", text: "未下发"},{id: "10", text: "已下发"},{id: "98", text: "异常完工"},{id: "99", text: "终止"},{id: "100", text: "完工"}]
,'workgroupList':[]};
var batchOper = false;
function customReady(){
	var id = Mes.getUrlParam("id");
	var workshopId = userDeptCode;
	if (!workshopId) {return;}
	$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT"),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_TYPE")
	).done(function(data11,data22,data33){
		var workshopList = data11;
		dropdownDatas.workshop = changeCode2Name(workshopList);
		var shiftList = data22;
		dropdownDatas.shiftList = changeCode2Name(shiftList);
		var worktypeList = data33;
		dropdownDatas.worktypeList = changeCode2Name(worktypeList);
		
		if(!id){
			$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+workshopId),
					Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+workshopId)
			).done(function(data44,data55){
				var workcenterList = data44;
				dropdownDatas.workcenterList = changeCode2Name(workcenterList);
				if (dropdownDatas.workcenterList.length==0){
					dropdownDatas.workcenterList = [{id:0,text:'请选择'}];
				}
				var shiftStyle = data55;
				dropdownDatas.shiftStyle = changeCode2Name(shiftStyle);
				if (dropdownDatas.shiftStyle.length==0){
					dropdownDatas.shiftStyle = [{id:'',text:'请选择'}];
				}
				
				var data2={}
				data2.mesPlanShift = {"workshopId":workshopId,"shiftId":"W01","shiftStyle":dropdownDatas.shiftStyle[0].id,"workcenterId":dropdownDatas.workcenterList[0].id,"shiftUser":loginUser,"shiftUserName":loginUserName};
				
				data2.mesPlanShift.dropdownDatas = dropdownDatas;
				var html2 = template('detailTab.tmp', data2.mesPlanShift);
		    	$('#detailTab').html(html2)
		    	
		    	ddwAddOption($("#workshopId"),dropdownDatas.workshop);
		    	$("#workshopId").val(data2.mesPlanShift.workshopId);
		    	
		    	ddwAddOption($("#shiftId"),dropdownDatas.shiftList);
		    	$("#shiftId").val(data2.mesPlanShift.shiftId);
		    	
		    	ddwAddOption($("#shiftStyle"),dropdownDatas.shiftStyle);
		    	$("#shiftStyle").val(data2.mesPlanShift.shiftStyle);
		    	
		    	ddwAddOption($("#workcenterId"),dropdownDatas.workcenterList);
		    	$("#workcenterId").val(data2.mesPlanShift.workcenterId);
		    	
		    	var shiftDate = new Date();
		    	shiftDate = shiftDate.Format("yyyy-MM-dd");
		    	$("#shiftDate").val(shiftDate);
		    	$("#shiftDateSpan").html(shiftDate);
		    	loadDataNew(false,$('#workshopId').val(),$('#shiftId').val(),shiftDate,$('#shiftStyle').val(),$('#workcenterId').val());
				
			});
		}else{
			loadData(id);
		}
	});
	
};

function changeWorkshop(sender){
	$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+$(sender).val()),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+$(sender).val())
	).done(function(data1,data2){
		var workcenterList = data1;
		dropdownDatas.workcenterList = changeCode2Name(workcenterList);
		if (dropdownDatas.workcenterList.length==0){
			dropdownDatas.workcenterList = [{id:0,text:'请选择'}];
		}
		ddwClearOption($("#workcenterId"),false);
		ddwAddOption($("#workcenterId"),dropdownDatas.workcenterList);
		$('#workcenterIdA').text(dropdownDatas.workcenterList[0].text);
		
		var shiftStyle = data2;
		dropdownDatas.shiftStyle = changeCode2Name(shiftStyle);
		if (dropdownDatas.shiftStyle.length==0){
			dropdownDatas.shiftStyle = [{id:'',text:'请选择'}];
		}
		ddwClearOption($("#shiftStyle"),false);
		ddwAddOption($("#shiftStyle"),dropdownDatas.shiftStyle);
		$('#shiftStyleA').text(dropdownDatas.shiftStyle[0].text);
	});
	//$("#workcenterId").val();
}
var setScheduleDateAHide = function(){
	//$('#scheduleDateA').css('display','none');
}

var loadDataNew  = function(isopen,workshopIdIn,shiftIdIn,shiftDateIn,shiftStyle,workcenterId){
	debugger
	var urlPara = "workshopId="+workshopIdIn+"&shiftId="+shiftIdIn+"&shiftDate="+shiftDateIn+"&shiftStyle="+shiftStyle+"&workcenterId="+workcenterId;
	Mes.getJson2("/plan/mesPlanShift/getsheetbyuk/index.do",urlPara,		
		function(result){
			var data2={};
			if (result.data && result.data.mesPlanShiftHead && result.data.mesPlanShiftHead.id){
				//dropdownDatas.workgroupList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.schedualDeviceList,"deviceId"));
				result.data.dropdownDatas = dropdownDatas;
				
				result.data.mesPlanShiftHead.dropdownDatas = dropdownDatas;
				var html2 = template('detailTab.tmp', result.data.mesPlanShiftHead);
            	$('#detailTab').html(html2);
            	
//            	var html3= template('qty_div.tmp', result.data.mesPlanShiftHead);
//            	$('#qty_div').html(html3);
            	
            	var html4= template('device_div.tmp', result.data);
            	$('#device_div').html(html4);
            	
            	var html5= template('worker_div.tmp', result.data);
            	$('#worker_div').html(html5);
            	refreshStatus(result.data.mesPlanShiftHead.sheetStatus);
			 
			}else{
			    if (isopen){
			    	data2.mesPlanShift = {"workshopId":workshopIdIn,"shiftId":shiftIdIn,"shiftUser":loginUser,"shiftUserName":loginUserName};
					var html2 = template('detailTab.tmp', data2.mesPlanShift);
			    	$('#detailTab').html(html2);
			    	
			    	ddwAddOption($("#shiftStyle"),dropdownDatas.shiftStyle);
			    	$("#shiftStyle").val(data2.mesPlanShift.shiftStyle);
				}else{
	            	var shiftDate = $('#shiftDate').val();
	            	debugger
	            	//dropdownDatas.workgroupList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_GROUP&codeList="+getCommaStr(result.data.schedualDeviceList,"deviceId"));
	            	result.data.dropdownDatas = dropdownDatas;
	            	
	            	//var html3= template('qty_div.tmp', {'shiftDate':shiftDate});
	            	//$('#qty_div').html(html3);

	            	var html4= template('device_div.tmp', result.data);
	            	$('#device_div').html(html4);
	            	
	            	var html5= template('worker_div.tmp', result.data);
	            	$('#worker_div').html(html5);
	            	
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
		
			$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+result.data.mesPlanShiftHead.workshopId),
					Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+result.data.mesPlanShiftHead.workshopId)
			).done(function(data1,data2){
				var shiftStyle = data1;
				dropdownDatas.shiftStyle = changeCode2Name(shiftStyle);
				
				var workcenterList = data2;
				dropdownDatas.workcenterList = changeCode2Name(workcenterList);
				
				result.data.dropdownDatas = dropdownDatas;
				result.data.mesPlanShiftHead.dropdownDatas = dropdownDatas;
				
            	var html2 = template('detailTab.tmp', result.data.mesPlanShiftHead);
            	$('#detailTab').html(html2);
            	
            	//var html3= template('qty_div.tmp', result.data.mesPlanShiftHead);
            	//$('#qty_div').html(html3);
            	
            	var html4= template('device_div.tmp', result.data);
            	$('#device_div').html(html4);
            	
            	var html5= template('worker_div.tmp', result.data);
            	$('#worker_div').html(html5);
            	
            	
            	refreshStatus(result.data.mesPlanShiftHead.sheetStatus);
			});
				
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
	
}

function refreshStatus(sheetStatus){
	debugger
	$("#tabBody").children().each(function(){
		var shiftStatus = $(this).find("[id=shiftStatus]").val()||'0';
		if (shiftStatus == "0"){
			//$(this).find('#checkBtn').show();
			$(this).find('.select2').select2();
		}else{
			//$(this).find('#saveBtn').hide();
			var element = $(this).find('.select2');
			element.select2({disabled:true});
			
			$($(this)).find('[name=technicUser]').next().children().each(function(){
				var select2Element = $(this).children()[0];
				$(select2Element).css('margin-left','-5px');
				$(select2Element).css('background-color','transparent');
				$(select2Element).css('border','0px');
			});
			
			//$(this).find('[name=checkflag]').attr('disabled','disabled');
			$(this).find('[name=shiftQty]').attr('readonly','readonly');
		}
	});
	 //$('#checkBtn').hide();
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
	debugger
	if ($('#worker_div').css('display')=='none')
	{	$('#folderBtn2').removeClass('glyphicon-chevron-down');
		$('#folderBtn2').addClass('glyphicon-chevron-up')}
	else{
		$('#folderBtn2').removeClass('glyphicon-chevron-up');
		$('#folderBtn2').addClass('glyphicon-chevron-down')
	}
}





function get_unschedule_obj(){
	var id = $("#id").val();
	var sheetId = $("#sheetId").val();
	var workshopId = $("#workshopId").val();
	var shiftDate = $("#shiftDate").val();
	var shiftId = $("#shiftId").val();
	var shiftStyle = $("#shiftStyle").val();
	var shiftUser = $("#shiftUser").val();
	var workcenterId =  $("#workcenterId").val();
	if (!workshopId || !shiftId || !shiftDate || !shiftUser || !workcenterId){
		return null;
	}
	else{
		//整理车间需要输入班别
		if (workshopId==WORKSHOP_ARRANGE && !shiftStyle){
			return null;
		}
		
		return {'id':id,'sheetId':sheetId,'workshopId':workshopId,'shiftDate':shiftDate,'shiftId':shiftId,'shiftUser':shiftUser,'shiftStyle':shiftStyle,'workcenterId':workcenterId};
	}
	
}

function get_unschedule_parm(orderBy){
	var id = $("#id").val();
	var sheetId = $("#sheetId").val();
	var workshopId = $("#workshopId").val();
	var shiftDate = $("#shiftDate").val();
	var shiftId = $("#shiftId").val();
	var shiftStyle = $("#shiftStyle").val();
	var shiftUser = $("#shiftUser").val();
	var shiftUserName = $("#shiftUserName").val();
	var workcenterId =  $("#workcenterId").val();
	if (!workshopId || !shiftId || !shiftDate || !shiftUser || !workcenterId){
		return "";
	}
	//整理车间需要输入班别
	if (workshopId == WORKSHOP_ARRANGE && !shiftStyle){
		return "";
	}
	var urlparam ='workshopId='+workshopId+'&shiftId='+shiftId+"&shiftDate="+shiftDate+"&shiftUser="+shiftUser+"&shiftStyle="+shiftStyle+"&workcenterId="+workcenterId+"&shiftUserName="+shiftUserName;
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
	
	if (!$('#shiftStyle').val()){
		alert('请先选择班别!');
		return;
	}
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
	if ($(sender).next().next().attr('name')=="tempBtn" && !batchOper){
		$(sender).next().next().show();
	}
}
 
 
 function setchanged2 (sender){
	 debugger
		$(sender).parent().parent().parent().parent().parent().find("[name='saveBtn']").prop('disabled','');
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
	 if (!$('#shiftStyle').val()){
		alert('请先选择班别!');
		return;
	}
	 var detDatas = [];
	 var workerDatas = [];
	 var data={'mesPlanShiftDetList':detDatas,'mesPlanShiftWorkerList':workerDatas};
	  
	 debugger
	 var shiftStyle = $('#shiftStyle').val();
	 setWorker('#worker_body',shiftStyle,workerDatas);
	 //常日班shift_style
	 var shiftStyle_cr = shiftStyle.substr(0,3)+"04";
	 
	 setWorker('#worker_body2',shiftStyle_cr,workerDatas);
	 
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
			  var subRows  = $(this).find("tbody").find("tr") ;
			  
			  subRows.each(function(){
				 var technicId= $(this).find("[name='technicId']").val();
				 var ptechnicId= $(this).find("[name='ptechnicId']").val();
				 var id= $(this).find("[name='id']").val();
				 var sheetId= $(this).find("[name='sheetId']").val();
				 var technicUser = $(this).find("[name='technicUser']").val()|| '';
				 technicUser = technicUser.toString();
				 var srcBillNo  = $(this).find("[name='srcBillNo']").val();
				 var technicBeginDate = $(this).find("[name='technicBeginDate']").val();
				 var technicEndDate = $(this).find("[name='technicEndDate']").val();
				 var worktypeId = $(this).find("[name='worktypeId']").val();
				 var deviceSubId = $(this).find("[name='deviceSubId']").val(); 
				 var qty = $(this).find("[name='qty']").val() || 0; 
				 var technicName = $(this).find("[name='technicName']").val()
				 subData ={'id':id,'sheetId':sheetId,'technicId':technicId,'ptechnicId':ptechnicId,'technicName':technicName,'technicUser':technicUser,'srcBillNo':srcBillNo,'worktypeId':worktypeId,'deviceSubId':deviceSubId,'qty':qty};
				 subDatas.push(subData);
			  });
		  }
	});
	 if (data.mesPlanShiftDetList.length>0 || data.mesPlanShiftWorkerList.length>0){
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

function setWorker(element,shiftStyleIn,workerDatas){
	$(element).find("[name='workerInfo']").each(function(index){
	var id = $(this).find("[name='id']").val();
	var sheetId = $(this).find("[name='sheetId']").val();
	var worktypeId =$(this).find("[name='worktypeId']").val();
	var workStatus = $(this).find("[name='workStatus']").prop('checked')?'1':'0';
	var userId = $(this).find("[name='userId']").val();
	var userName = $(this).find("[name='userName']").val();
	//var deviceSubId = $(this).find("[name='deviceSubId']").val();
	workerDatas.push({'id':id,'sheetId':sheetId,'worktypeId':worktypeId,'workStatus':workStatus,'userId':userId,'userName':userName,'shiftStyle':shiftStyleIn});
});}


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
};
 
 function setTechnicStatus(sender){
	 var tabBody=$(sender).parent().parent().parent().next(); 
	 var tabFolderBtn = $(sender).find('[name=tabFolderBtn]');
	 if (tabBody.css('display')=="none") 
	 {
		 tabBody.show();//tabFolderBtn
		 tabFolderBtn.removeClass('glyphicon-chevron-up');
		 tabFolderBtn.addClass('glyphicon-chevron-down');
	 }
	 else
	 {
		 tabBody.hide();
		 tabFolderBtn.removeClass('glyphicon-chevron-down');
		 tabFolderBtn.addClass('glyphicon-chevron-up');
	 }
	 return false;
};

function onShiftStyleInfo(sheetId,workshopId,shiftDate,shiftId,shiftStyle){
	if (!$('#shiftStyle').val()){
		alert('请先选择班别!');
		return;
	}
}


function getWoker(workerList,worktypeId,deviceId){
	var rtuUser={technicUser:'',deviceSubId:''};
	for (i=0;i<workerList.length;i++){
		if (workerList[i].worktypeId == worktypeId && workerList[i].deviceId == deviceId){
			if(! workerList[i].used){
				workerList[i].used = true;
			}
			rtuUser.technicUser = workerList[i].userId;
			//rtuUser.deviceSubId = workerList[i].deviceSubId;
			return   rtuUser;
		}
	}
	return rtuUser;
};
function on_default_shift(){
	layer.confirm('确认根据历史排程数据给机台分配人员吗?', {title:'提示'}, function(index){
		var shiftId = $('#shiftStyle').val();
		if (!shiftId) return;
		var param = "shiftId="+shiftId;
		Mes.getJson2("/plan/mesPlanWorker2deviceInfo/findList/index.do",param,		
	    		function(result){
					var worker2DeiceInfo = result.data;
					if (!worker2DeiceInfo || worker2DeiceInfo.length < 0){
						return;
					}
					debugger
					var workerList = [];
					var keys={};
					var data={'mesPlanShiftWorkerList':workerList};
					$('#worker_body').find("[name='workerInfo']").each(function(index){
						//var id = $(this).find("[name='id']").val();
						//var sheetId = $(this).find("[name='sheetId']").val();
						var worktypeId =$(this).find("[name='worktypeId']").val();
						var workStatus = $(this).find("[name='workStatus']").prop('checked')?'1':'0';
						var userId = $(this).find("[name='userId']").val();
						var userName = $(this).find("[name='userName']").val();
						//var deviceSubId = $(this).find("[name='deviceSubId']").val();
						if (workStatus == "1"){
							var deviceMap = {};
							for (var i=0; i< worker2DeiceInfo.length;i++){
								if (worker2DeiceInfo[i].userId == userId 
										|| worker2DeiceInfo[i].userId.startsWith(userId+",")
										|| worker2DeiceInfo[i].userId.endsWith(","+userId)
										|| worker2DeiceInfo[i].userId.indexOf(","+userId+",")>0 
								){
									//deviceMap[worker2DeiceInfo[i].deviceId]=worker2DeiceInfo[i].deviceId;
									//deviceMap[userId]=worker2DeiceInfo[i].userId;
									worktypeId = worker2DeiceInfo[i].worktypeId||worktypeId;
									if (!keys[worktypeId+worker2DeiceInfo[i].deviceId]){
										keys[worktypeId+worktypeId] = worktypeId+worktypeId;
										workerList.push({'worktypeId':worktypeId,'userId':worker2DeiceInfo[i].userId,'userName':worker2DeiceInfo[i].userName,'deviceId':worker2DeiceInfo[i].deviceId});
									}
								} 
							}
							 
							//workerList.push({'id':id,'sheetId':sheetId,'worktypeId':worktypeId,'workStatus':workStatus,'userId':userId,'userName':userName});
						}
					});
					
					
					//alert(getWoker(workerList,'11'));
					var sucLogs=[];
					var errLogs=[];
					//启用批量
					batchOper = true;
					$("#tabBody").children("tr").each(function(){
						  var tablerow = $(this);
						  var deviceId = tablerow.find("[name='deviceId']").val();
						  var subRows  = tablerow.find("tbody").find("tr") ;
						  
						  debugger
						  //状态大于0的状态不参与排程
						  var shiftStatus = parseInt($(this).find("[name='shiftStatus']").val()||0);
						  if (shiftStatus > 0){
							  return true;
						  }
						  subRows.each(function(){
							 //var technicId= $(this).find("[name='technicId']").val();
							 //var id= $(this).find("[name='id']").val();
								 //var technicUser = $(this).find("[name='technicUser']").val()|| '';
								// technicUser = technicUser.toString();
								 var worktypeId = $(this).find("[name='worktypeId']").val();
								 //var deviceSubId = $(this).find("[name='deviceSubId']").val(); 
								 var workerMap = getWoker(workerList,worktypeId,deviceId) ;
								 var userElement = $(this).find("[name='technicUser']");
								 var userValue = ($(this).find("[name='technicUser']").val()||'').toString();
								 if (workerMap.technicUser && !userValue){
									//如果已有人员则跳过默认对应关系
										userElement.val(workerMap.technicUser.split(',')).trigger("change");
									 	//userElement.val(workerMap.technicUser).trigger("change");
										 //workerMap.deviceSubId;
										 //errLogs.push({"deviceId":deviceId,"worktypeId":worktypeId,"technicUser":workerMap.technicUser});
								 }else if (!userValue){
									 errLogs.push({"deviceId":deviceId,"worktypeId":worktypeId});
								 }
//								 var sheetId= $(this).find("[name='sheetId']").val();
//								 var srcBillNo  = $(this).find("[name='srcBillNo']").val();
//								 var technicBeginDate = $(this).find("[name='technicBeginDate']").val();
//								 var technicEndDate = $(this).find("[name='technicEndDate']").val();
//								 subData ={'id':id,'sheetId':sheetId,'technicId':technicId,'technicUser':technicUser,'srcBillNo':srcBillNo,'worktypeId':worktypeId,'deviceSubId':deviceSubId};
//								 subDatas.push(subData);
						  });
						 
					});
					//恢复标志
					batchOper = false;
					 var hasError = false;
					  var logs = "";
					  var j=0;
//					  for (i=0 ;i<workerList.length;i++){
//						  debugger
//						  if (!workerList[i].used){
//							  logs = logs+workerList[i].userId+" "+workerList[i].userName+",";
//							  j++;
//							  if (j != 0 && j%6 == 0){
//								  logs = logs +"<br>"
//							  }
//							  hasError = true;
//						  }
//						  if (i == (workerList.length-1) ){
//							  logs = "<strong>以下("+j+")位人员未指定设备</strong><br>" + logs + "<br>"
//						  }
//					  }
					  
					  logs =logs + "<strong>以下设备工种("+errLogs.length+")个未分配到人员</strong><br>";
					  for (i=0;i<errLogs.length;i++){
						  logs = logs + "设备[" + errLogs[i].deviceId + "]工种[" + getName(errLogs[i].worktypeId)+"];  ";
						  if (i != 0 && (i+1)%3 == 0){
								  logs = logs +"<br>"
						  }
						  hasError = true;
					  }
					  if (hasError){
						  layer.open({
							  title:"排班信息",
							  area: ['600px', '380px'],
							  btn: ['保存信息', '关闭'],
							  btn1: function(index, layero){
								  debugger;
								  download("排班信息.txt",layero.text()); 
								  },
								  
							  content: logs
						  }); 
					  }else{
						  layer.alert("一键排程成功!");
					  }
		    	});
				layer.close(index);
	});
};

function on_add_shift_worker(sender){
	layer.confirm('确认添加将设备,工种,人员设为默认对应关系?', {title:'提示'}, function(index2){
		var shiftId=$('#shiftStyle').val();
		 
		var deviceId=$(sender).parent().parent().parent().parent().parent().parent().find("[name=deviceId]").val();
		var worktypeId=$(sender).parent().parent().find("[name=worktypeId]").val();
		var userId=($(sender).parent().find("[name=technicUser]").val()||'').toString();
		var param = 'shiftId='+shiftId+'&deviceId='+deviceId+"&worktypeId="+worktypeId+"&userId="+userId;
		Mes.getJson2("/plan/mesPlanWorker2deviceInfo/save/index.do",param,		
	    		function(result){
					$(sender).hide();
					layer.alert("设置为默认排程成功!");
		    	},
		    	function(data){
		    		//alert(JSON.stringify(data))
		    });
	});
	//alert(shiftId+','+deviceId+','+worktypeId+','+userId);
};
function fake_click(obj) {  
    var ev = document.createEvent("MouseEvents");  
    ev.initMouseEvent(  
        "click", true, false, window, 0, 0, 0, 0, 0  
        , false, false, false, false, 0, null  
        );  
    obj.dispatchEvent(ev);  
};
function download(name, data) {  
    var urlObject = window.URL || window.webkitURL || window;  
   
    var downloadData = new Blob([data]);  
   
    var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a") ;
    save_link.href = urlObject.createObjectURL(downloadData);  
    save_link.download = name;  
    fake_click(save_link);  
};

function validateQty(sender){
	debugger;
	var value = $(sender).val();
	var re = /^\d{0,5}\.?\d{0,3}$/;
	
	if (!re.test(value)){
		$(sender).val('');
		layer.alert("请输入5位以内的数字!",function(index){
			layer.close(index);
			$(sender).focus();
		});
		
	}
};

function onSelectAll(sender){
	if ($(sender).prop('checked')){
		$(sender).parent().parent().parent().next().find("input:checkbox").prop('checked','checked');
	}else{
		$(sender).parent().parent().parent().next().find("input:checkbox").prop('checked','');
	}
};

function onShow(showStatus){
	//showStatus 1=未下发 2=已下发 3=全部
	$('#tabBody').children().each(function(){
		debugger
		if (showStatus==101){
			$(this).css('display','');
			$('#filterBtn').text('请选择过滤');
		}else if (showStatus ==0){
			var shiftStatus = $(this).find("[name='shiftStatus']").val()||0;
			if (shiftStatus == 0){
				$(this).css('display','');
			}else{
				$(this).css('display','none');
			}
			$('#filterBtn').text('过滤(未下发)');
		}else if (showStatus ==10){
			var shiftStatus = $(this).find("[name='shiftStatus']").val()||0;
			if (shiftStatus == 10){
				$(this).css('display','');
			}else{
				$(this).css('display','none');
			}
			$('#filterBtn').text('过滤(已下发)');
		}else if (showStatus == 99){
			var shiftStatus = $(this).find("[name='shiftStatus']").val()||0;
			if (shiftStatus == 99){
				$(this).css('display','');
			}else{
				$(this).css('display','none');
			}
			$('#filterBtn').text('过滤(终止)');
		}else if (showStatus == 100){
			var shiftStatus = $(this).find("[name='shiftStatus']").val()||0;
			if (shiftStatus == 100){
				$(this).css('display','');
			}else{
				$(this).css('display','none');
			}
			$('#filterBtn').text('过滤(完工)');
		}
	}); 
}

function onShow2(isMatched){
	debugger;
	if (isMatched){
		$("#tabBody").children("tr").each(function(){
			 $(this).css('display','');
			  var tablerow = $(this);
			  var subRows  = $(this).find("tbody").find("tr") ;
			  
			  subRows.each(function(){
				 var technicUser = $(this).find("[name='technicUser']").val()|| '';
				 technicUser = technicUser.toString();
				 if (!technicUser){
					 tablerow.css('display','none');
					 return false;
				 }
			  });
		});
		$('#filterBtn').text('过滤(人员完整)');
	}else{
		$("#tabBody").children("tr").each(function(){
			 $(this).css('display','none');
			  var tablerow = $(this);
			  var subRows  = $(this).find("tbody").find("tr") ;
			  
			  subRows.each(function(){
				 var technicUser = $(this).find("[name='technicUser']").val()|| '';
				 technicUser = technicUser.toString();
				 if (!technicUser){
					 tablerow.css('display','');
					 return false;
				 }
			  });
		});
		$('#filterBtn').text('过滤(人员不完整)');
		
	}
}


function onCheckBatch(){
	//检查是否有数据未保存，未保存先保存数据
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var validatePass = true;
	debugger
	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
	  var tablerow = $(this).parent().parent()
	  
	  var saveBtnStatus = tablerow.find("[name='saveBtn']").attr("disabled") ;
	  if (!saveBtnStatus || saveBtnStatus != "disabled"){
		  validatePass = false;
	  }
	  var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='detId']").val();
	  var shiftStatus = tablerow.find("[name='shiftStatus']").val()||0;
	  
	  var yesUser = true;
	  tablerow.find("[name='technicUser']").each(function(){
		  if (!(($(this).val()||'').toString())){
			  yesUser = false;
			  return false;
		  }
	  });  
	  if (parseInt(shiftStatus)<10 && yesUser){
		  var row = {"id":id,"shiftStatus":"10"};
		  selectedData.push(row);
	  }
	});
	if (!validatePass) {
		Mes.sysAlert('请先保存数据!');
		return;
	};
	on_check_inner(selectedData);
}

function on_check_inner(selectedData){
	//var urlPara = get_unschedule_parm();
	var urlPara ="sheetId="+$('#sheetId').val();
	if (selectedData.length>0){
		layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认下发到车间吗?', {title:'提示'}, function(index2){
			Mes.loadJson2("/plan/mesPlanShift/batchcheck/det.do",urlPara,JSON.stringify(selectedData),		
	     		function(result){
					result.data.dropdownDatas = dropdownDatas;
		        	var html4= template('device_div.tmp', result.data);
		        	$('#device_div').html(html4);
		        	refreshStatus(sheetStatus);
	 	    	},
	 	    	function(data){
	 	    		Mes.sysAlert(JSON.stringify(data))
	 	    });
			 layer.close(index2);
		 });
	}else{
		Mes.sysAlert("没有选择数据或数据已下发,请重新选择数据!")
	}
};

function onFinishBatch(){
	 var html2 = FINISH_TEMPLATE;
	 layer.open({
		 type: 1,
		 title :'完工说明',
		 area: ['30em', '15em'],
		 content: html2,
		 btn: ['完工', '异常完工','取消'],
		 btn1: function(index, layero){
			 var remarks = $('#remarksTextarea').val();
			 	var selectedData = [];
	        	var scheduleDateStr = $('#scheduleDate').val();
	        	var validatePass = true;
	        	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
	        	  var tablerow = $(this).parent().parent();
	        	  
	        	  var saveBtnStatus = tablerow.find("[name='saveBtn']").attr("disabled") ;
	        	  if (!saveBtnStatus || saveBtnStatus != "disabled"){
	        		  validatePass = false;
	        	  }
	        	  var rowIndex = tablerow.find("[name='rowIndex']").text();
	        	  var id = tablerow.find("[name='detId']").val();
	        	  var shiftStatus = tablerow.find("[name='shiftStatus']").val()||0;
	        	  if (parseInt(shiftStatus)==10){
	        		  var row = {"id":id,"shiftStatus":"100","remarks":remarks};
	        		  selectedData.push(row);
	        	  }
	        	  
	        	});
	        	if (!validatePass) {
	        		Mes.sysAlert('请先保存数据!');
	        		return;
	        	};
	        	on_finish_inner(selectedData,index);
	        	
	        	 
		 },
		 btn2: function(index, layero){
			 var remarks = $('#remarksTextarea').val();
			 if (!remarks){
				 layer.msg('请输入异常完工说明!',{time: 1500});
				 return false;
			 }else{
				 var selectedData = [];
		        	var scheduleDateStr = $('#scheduleDate').val();
		        	var validatePass = true;
		        	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
		        	  var tablerow = $(this).parent().parent()
		        	  
		        	  var saveBtnStatus = tablerow.find("[name='saveBtn']").attr("disabled") ;
		        	  if (!saveBtnStatus || saveBtnStatus != "disabled"){
		        		  validatePass = false;
		        	  }
		        	  var rowIndex = tablerow.find("[name='rowIndex']").text();
		        	  var id = tablerow.find("[name='detId']").val();
		        	  var shiftStatus = tablerow.find("[name='shiftStatus']").val()||0;
		        	  if (parseInt(shiftStatus)==10){
		        		  var row = {"id":id,"shiftStatus":"98","remarks":remarks};
		        		  selectedData.push(row);
		        	  }
		        	  
		        	});
		        	if (!validatePass) {
		        		Mes.sysAlert('请先保存数据!');
		        		return;
		        	};
		        	on_finish_inner(selectedData,index);
		        	 
			 }
		 },
		 btn3: function(index, layero){
			 // alert(2);
			 layer.close(index);
		 }
	});
};

function onFinishBatch11(){
	layer.confirm("确认此调度单已完成生产吗?",
    {
        btn: ['是', '否'],
        btn1: function (index) {
        	//检查是否有数据未保存，未保存先保存数据
        	
        },
        btn2: function () {
        }
    });
};
function on_finish_inner(selectedData,index){
	//var urlPara = get_unschedule_parm();
	var urlPara ="sheetId="+$('#sheetId').val();
	if (selectedData.length>0){
		Mes.loadJson2("/plan/mesPlanShift/batchfinish/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
				layer.close(index);
				result.data.dropdownDatas = dropdownDatas;
	        	var html4= template('device_div.tmp', result.data);
	        	$('#device_div').html(html4);
	        	refreshStatus(sheetStatus);
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("计划状态不正确或没有选择数据,请重新选择!")
	}
};
function onCancelBatch(){
	var selectedData = [];
	var scheduleDateStr = $('#scheduleDate').val();
	var validatePass = true;
	debugger
	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
	  var tablerow = $(this).parent().parent()
	  
	  var saveBtnStatus = tablerow.find("[name='saveBtn']").attr("disabled") ;
	  if (!saveBtnStatus || saveBtnStatus != "disabled"){
		  validatePass = false;
	  }
	  var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='detId']").val();
	  var shiftStatus = tablerow.find("[name='shiftStatus']").val()||0;
	  if (parseInt(shiftStatus)==10){
		  var row = {"id":id,"shiftStatus":"0"};
		  selectedData.push(row);
	  }
	});
	if (!validatePass) {
		Mes.sysAlert('计划状态不正确或没有选择数据,请重新选择!');
		return;
	};
	
	on_cancel_inner(selectedData);
};
function on_cancel_inner(selectedData){
	//var urlPara = get_unschedule_parm();
	var urlPara ="sheetId="+$('#sheetId').val();
	if (selectedData.length>0){
		layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认取消下发吗?', {title:'提示'}, function(index2){
			Mes.loadJson2("/plan/mesPlanShift/batchcancel/det.do",urlPara,JSON.stringify(selectedData),		
	     		function(result){
					result.data.dropdownDatas = dropdownDatas;
		        	var html4= template('device_div.tmp', result.data);
		        	$('#device_div').html(html4);
		        	refreshStatus(sheetStatus);
	 	    	},
	 	    	function(data){
	 	    		Mes.sysAlert(JSON.stringify(data))
	 	    });
			 layer.close(index2);
		 });
		
	}else{
		Mes.sysAlert("生产任务状态不正确或没有选择数据!")
	}
};
function onFilterUser(sender){
	debugger
	var userName = $(sender).val().trim();
	if (userName){
		$('#tabBody').children().each(function(){
			var found = false;
			
			var cols = $(this).children();
			var colCnt = cols.length;
			for (var i = 1 ;i< colCnt-2; i++)
			{
				if ($(cols[i]).text().indexOf(userName) >0){
					found = true;
					break;
				}
				
			}
			if (!found){
				$(this).find("[name=technicUser]").each(function(){
					if ($(this).children(":selected").text().indexOf(userName) >=0){
						found = true;
						return false;
					}
				});
			}
			if (found){
				$(this).css('display','');
			} else{
				$(this).css('display','none');
			}
		});
	}else{
		$('#tabBody').children().each(function(){
			$(this).css('display','');
		});
	}
	
};

function getName(valueIn) {
	var rtn;
	$.each(dropdownDatas.worktypeList,function(n,loopValue) {   
            if (loopValue.id == valueIn)   {
            	rtn = loopValue.text;
            	return false;
            }  
           });  
	return rtn || valueIn;  
};

