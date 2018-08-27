var dropdownDatas = {};
//var subJobProcesssDdw;
function customReady(){
	var urlPara = "types=schedule_status";
	$.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara)
	).done(function(data1){
		dropdownDatas = data1;
		var id = Mes.getUrlParam("id");
		var param;
		if (!!id){
			param = "id="+id;
		}else
		{
			alert("得到id参数出错");
			return;
		}
		loadPage(param,id);
	});
};

function loadPage(urlParam,id){
	
	Mes.getJson2("/plan/device/getsheet/index.do",urlParam,		
    		function(result){
				if (result.data.mesPlanMonth.workshopId != WORKSHOP_ARRANGE){
					$('#newBtn').attr('href','/mes-plan/production/mes_plan_device_parallel_new.html?parId='+id+'&erpBillNo='+result.data.mesPlanDispatchHead.erpBillNo);
					$('#batchBtn').attr("href","/mes-plan/production/mes_plan_device_batch.html?parId="+id);
					$('#newBtn2').hide();
					$('#batchBtn2').hide();
			    	
				}else{
					$('#newBtn2').attr('href','/mes-plan/production/mes_plan_device_parallel_new2.html?parId='+id+'&erpBillNo='+result.data.mesPlanDispatchHead.erpBillNo);
					$('#batchBtn2').attr("href","/mes-plan/production/mes_plan_device_batch2.html?parId="+id);
					$('#newBtn').hide();
					$('#batchBtn').hide();
					$('#batchTechnicBtn').hide();
					result.data.shiftList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT");
					result.data.shiftStyle = Mes.getJsonSync2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+result.data.mesPlanMonth.workshopId);
					//子工序
					var subJobProcesssList = Mes.getJsonSync("/plan/mesPlanMonth/getSubMesPlanTechnicList/index.do","id="+result.data.mesPlanMonth.id);
			    	var subJobProcesssDdw = [];
			    	if (subJobProcesssList){
				    	for (var i=0 ;i<subJobProcesssList.length; i++){
				    		subJobProcesssDdw.push({code:subJobProcesssList[i].processCode,name:subJobProcesssList[i].processName});
				    	}
			    	}
			    	result.data.subJobProcesssDdw = subJobProcesssDdw;
			    	
				}
				result.data.ddw_schedule_status =  dropdownDatas.schedule_status;
				debugger
				$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&code="+result.data.mesPlanDispatchDet.workcenterId),
						Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList='"+result.data.mesPlanDispatchDet.deviceChangeGoodsId+"'")
	    		).done(function(data1,data2){
	    			result.data.workcenterList = data1; 
                	result.data.goodsList = data2;
                	
                	var html2 = template('detailTab.tmp', result.data);
                	$('#detailTab').html(html2);
                	var html3= template('qty_div.tmp', result.data);
                	$('#qty_div').html(html3);
                	var html4= template('device_div.tmp', result.data);
                	$('#device_div').html(html4);
                	$.cookie('erpId'+id,result.data.mesPlanMonth.id);
		    	});
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
};

var changeFolderFun = function(){
	if ($('#detailTab').css('display')=='none')
	{	$('#folderBtn').removeClass('glyphicon-chevron-down');
		$('#folderBtn').addClass('glyphicon-chevron-up')}
	else{
		$('#folderBtn').removeClass('glyphicon-chevron-up');
		$('#folderBtn').addClass('glyphicon-chevron-down')
	}
};

var qtyAna = function(){
	debugger
	var orderNo= $("#erpBillNoSpan").text();
	var planNum = 0;
	var totalNum = $("#dispatchQtySpan").text();
	$(":checkbox:checked","#tabBody").each(function(){
	  var tablerow = $(this).parent().parent();
	  planNum = planNum + parseFloat(tablerow.find("[name='workCenterQty']").val());
	  //var name= tablerow.find("[name='p_name']").val();
	  //var price= tablerow.find("[name='p_price']").val();
	  //selectedData.push({Code:code,Name:name,Price:price});
	});
	if (planNum == 0) {
		Mes.sysAlert('请勾选车间调度单!');
		return;
	}
	 
	location.href='/mes-plan/production/mes_plan_erp_suit_analyse.html?orderNo='+orderNo+"&planNum="+planNum+"&totalNum="+totalNum
	 return false;
};

function on_del_row(pid,rowId,workcenterId,goodsId,srcBillNo){
	if (!rowId && !workcenterId && !goodsId) return;
	var urlPara = "id=" + rowId + "&workcenterId=" + workcenterId+"&goodsId="+goodsId+"&srcBillNo="+srcBillNo+"&pid="+pid;
	Mes.getJson2("/plan/device/delete/det.do",urlPara,		
    		function(result){
				if (result.data==1){
					Mes.sysAlert('删除成功!',function(index){
						layer.close(index);
						var urlParam="id=" + pid;
						loadPage(urlParam,pid);
					});
				}else{
					Mes.sysAlert('删除了['+result.data+']条记录! ',function(index){
						layer.close(index);
						var urlParam="id=" + pid;
						loadPage(urlParam,pid);
						});
				}
				 
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
};

function on_chk_row(pid,rowId,workcenterId,workshopId){
	var urlPara = "id=" + rowId + "&workcenterId=" + workcenterId + "&workshopId=" + workshopId+"&pid="+pid;
	Mes.getJson2("/plan/device/check/det.do",urlPara,		
		function(result){
			if (result.data==1){
				Mes.sysAlert('下发成功!',function(index){
					layer.close(index);
					var urlParam="id=" + pid;
					loadPage(urlParam,pid);
				});
			}else{
				Mes.sysAlert('下发['+result.data+']条记录,可能已被其他人操作! ',function(index){
					layer.close(index);
					var urlParam="id=" + pid;
					loadPage(urlParam,pid);
				});
			}
			 
		},
		function(data){
			//alert(JSON.stringify(data))
		});
};

function on_cancel_row(pid,rowId,workcenterId,goodsId,workshopId){
	if (!rowId && !workcenterId && !goodsId) return;
	var urlPara = "id=" + rowId + "&workcenterId=" + workcenterId+"&goodsId="+goodsId+"&workshopId="+workshopId+"&pid="+pid;
	Mes.getJson2("/plan/device/cancel/det.do",urlPara,		
		function(result){
			if (result.data==1){
				Mes.sysAlert('取消成功!',function(index){
					layer.close(index);
					var urlParam="id=" + pid;
					loadPage(urlParam,pid);
				});
			}else{
				Mes.sysAlert('取消['+result.data+']条记录,可能已被其他人操作! ',function(index){
					layer.close(index);
					var urlParam="id=" + pid;
					loadPage(urlParam,pid);
				});
			}
		},
		function(data){
			//alert(JSON.stringify(data))
		});
};

function onFinishValidate(finishStatus){
	//var id = tablerow.find("[name='id']").val();
	var remarks = $('#remarksTextarea').val();
	if (finishStatus != '100' && !remarks){
		layer.msg('请输入异常完工说明!',{time: 1500});
		return false;
	}
	return true;
};

function onSelectedRows(finishStatus){
	debugger
	var selectedData = [];
	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
		var remarks = $('#remarksTextarea').val();
		var tablerow = $(this).parent().parent()
		//var rowIndex = tablerow.find("[name='rowIndex']").text();
		var id = tablerow.find("[name='id']").val();
		var billNo = tablerow.find("[name='billNo']").val();
		var dispatchStatus =  tablerow.find("[name='dispatchStatus']").val()||'0';
		if (parseInt(dispatchStatus)>=0 && parseInt(dispatchStatus)<98){
			//检查已下发单据是否完工
			var urlPara = "level=3&billNo="+billNo;
			if (Mes.getJsonSyncAllData("/plan/mesPlanShift/validateFinish/index.do",urlPara)==0 ){
				var row = {"id":id,"dispatchStatus":finishStatus,"remarks":remarks};
				selectedData.push(row);
			}
		}
	});
	return selectedData;
};

function onBatchFinish(){
	 var html2 = FINISH_TEMPLATE;
	 layer.open({
		 type: 1,
		 title :'完工说明',
		 area: ['30em', '15em'],
		 content: html2,
		 btn: ['完工', '异常完工','取消'],
		 btn1: function(index, layero){
			 var finishStatus = '100';
			 if (!onFinishValidate(finishStatus)){
				 return false;
			 }
			 var selectedData = onSelectedRows(finishStatus);
			 if (selectedData.length > 0){
				 layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认完工吗?', {title:'提示'}, function(index2){
					 on_finish_inner(selectedData,index);
					 layer.close(index2);
				 });
			 }else{
				 layer.msg('没有勾选有效数据或勾选数据无效,请检查计划(含下级计划)状态!',{time: 3000});
			 }
		 },
		 btn2: function(index, layero){
			 var remarks = $('#remarksTextarea').val();
			 var finishStatus = '98';
			 if (!onFinishValidate(finishStatus)){
				 return false;
			 }
			 var selectedData = onSelectedRows(finishStatus);
			 if (selectedData.length > 0){
				 layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认完工吗?', {title:'提示'}, function(index2){
					 on_finish_inner(selectedData,index);
					 layer.close(index2);
				 });
			 }else{
				 layer.msg('没有勾选有效数据或勾选数据无效,请检查计划(含下级计划)状态!',{time: 3000});
			 }
			 return false;
		 },
		 btn3: function(index, layero){
			 // alert(2);
			 layer.close(index);
		 }
	});
};
function on_finish_inner(selectedData,index){
	//var urlPara = get_unschedule_parm();
	if (!$('#id').val()){
		Mes.sysAlert('请先保存单据');
		return;
	}
	var urlPara ="id="+$('#id').val();
	if (selectedData.length>0){
		Mes.loadJson2("/plan/device/batchfinish/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
				if (result.succ){
					result.data.ddw_schedule_status =  dropdownDatas.schedule_status;
					result.data.workcenterList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&code="+result.data.mesPlanDispatchDet.workcenterId);
	            	result.data.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&code="+result.data.mesPlanMonth.goodsId);
	            	//result.data.deviceList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=EQUIPMENT_INFO&codeList="+getCommaStr(result.data.mesPlanDeviceList,"deviceId"));
					var html4= template('device_div.tmp', result.data);
	            	$('#device_div').html(html4);
	            	layer.close(index);
				}else{
					Mes.sysAlert(result.msg);
				}
	        	//refreshStatus(sheetStatus);
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("请选择状态为【已下发】的机台!")
	}
};
function orderParallel(sender,addr,status){
	layer.open({
		 type: 2,
		 title :'设备指定',
		 area: ['50em', '32em'],
		 content: (!addr)?$(sender).attr('href'):addr,
		 btn: (status||0)>=10?[]:['确认', '取消'],
		 btn1: function(index, layero){
			 //$('#dispatchForm').submit();
			 debugger
			 //$(layero.find('iframe')[0]).contents().find('#dispatchForm').submit();
			 layero.find('iframe')[0].contentWindow.plan_dispatch_save();
			   //layer.close(index);
			  }
		  ,btn2: function(index, layero){
			 // alert(2);
		  }
	});
};
function onBatchTechnic(){
	debugger
	 var param = 'erpBillNo='+$('#erpBillNoSpan').text();
	 Mes.getJson2("/plan/mesPlanMonth/getMesPlanTechnicList/index.do",param,		
		function(result){
		 debugger
		 var html2 = template('technic_div.tmp', result.data);
		 
		 layer.open({
			 type: 1,
			 title :'请勾选不执行的子工序',
			 area: ['25em', '12em'],
			 content: html2,
			 btn: (status||0)>=10?[]:['确认', '取消'],
			 btn1: function(index, layero){
				 //$('#dispatchForm').submit();
				 var noTechnic='';
				 layero.find('input:checkbox:checked').each(function(){
					 debugger
					 noTechnic = noTechnic + $(this).val() + ",";
				 });
				 if (noTechnic.length>0){
					 noTechnic = noTechnic.substr(0,noTechnic.length-1);
				 }
				 //alert(noTechnic);
				 var selectedData = [];
				 var validatePass = true;
				 $("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
		        	  var tablerow = $(this).parent().parent();
		        	  var id = tablerow.find("[name='id']").val();
		        	  var dispatchStatus = tablerow.find("[name='dispatchStatus']").val();
		        	  if (parseInt(dispatchStatus)>=10){
		        		  validatePass = false;
		        	  }else{
		        		  var row = {"id":id,"noTechnic":noTechnic};
		        		  selectedData.push(row);
		        	  }
		        	});
				 if (!validatePass) {
					Mes.sysAlert("您选择的机台包含已下发机台,不能修改子工序");
				 }else{
					 var urlPara ="id="+$('#id').val();
					 if (selectedData.length>=1){
						 Mes.loadJson2("/plan/device/batchtechnic/det.do",urlPara,JSON.stringify(selectedData),		
				     		function(result){
								if (result.succ){
									result.data.ddw_schedule_status =  dropdownDatas.schedule_status;
									//result.data.workcenterList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&code="+result.data.mesPlanDispatchDet.workcenterId);
					            	//result.data.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&code="+result.data.mesPlanMonth.goodsId);
					            	//result.data.deviceList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=EQUIPMENT_INFO&codeList="+getCommaStr(result.data.mesPlanDeviceList,"deviceId"));
									var html4= template('device_div.tmp', result.data);
					            	$('#device_div').html(html4);
					            	layer.close(index);
								}else{
									Mes.sysAlert(result.msg);
								}
					        	//refreshStatus(sheetStatus);
				 	    	},
				 	    	function(data){
				 	    		Mes.sysAlert(JSON.stringify(data))
				 	    });
					 }
				 }
				 //$(layero.find('iframe')[0]).contents().find('#dispatchForm').submit();
				 //layero.find('iframe')[0].contentWindow.plan_dispatch_save();
				 //layer.close(index);
			 },
			 btn2: function(index, layero){
				 // alert(2);
			 }
		});
		 
    	},
    	function(data){
    		//alert(JSON.stringify(data))
    	});
	
};
function onSelectAll(sender){
	if ($(sender).prop('checked')){
		$(sender).parent().parent().parent().next().find("input:checkbox").prop('checked','checked');
	}else{
		$(sender).parent().parent().parent().next().find("input:checkbox").prop('checked','');
	}
}; 

function onCheckBatch(){
	//检查是否有数据未保存，未保存先保存数据
	var selectedData = [];
	debugger
	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
	  var tablerow = $(this).parent().parent()
	  var id = tablerow.find("[name='id']").val();
	  var dispatchStatus = tablerow.find("[name='dispatchStatus']").val()||'0';
	  if (dispatchStatus == '0'){
		  var row = {"id":id,"dispatchStatus":"10"};
		  selectedData.push(row);
	  }
	});
	if (selectedData.length>0){
		layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认下发吗', {title:'提示'}, function(index){
			on_check_inner(selectedData);
			layer.close(index);
		});
	}else{
		layer.msg('没有勾选有效数据或勾选数据无效,请检查计划(含下级计划)状态!',{time: 3000});
	}
}

function on_check_inner(selectedData){
	//var urlPara = get_unschedule_parm();
	var pid = $('#id').val();
	var workshopId = $('#workshopId').val();
	var workcenterId = $('#workcenterId').val();
	if (!pid || !workshopId || !workcenterId) {return;}
	var urlPara ="pid=" + pid + "&workshopId=" + workshopId + "&workcenterId=" + workcenterId;
	if (selectedData.length>0){
		Mes.loadJson2("/plan/device/batchcheck/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
				Mes.sysAlert('批量下发成功!',function(index){
					layer.close(index);
					var urlParam2="id=" + pid ;
					loadPage(urlParam2,pid);
				});
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("没有选择数据或数据已下发,请重新选择数据!")
	}
}

function onFilter(sender,elementId){
	debugger
	var filterValue = $(sender).val().trim();
	if (filterValue){
		$(elementId).children().each(function(){
			var found = false;
			var cols = $(this).children();
			var colCnt = cols.length;
			for (var i = 1 ;i < colCnt; i++)
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




