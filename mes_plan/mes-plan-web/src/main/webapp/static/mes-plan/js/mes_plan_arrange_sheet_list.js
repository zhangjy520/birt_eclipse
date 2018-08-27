var dropdownDatas;
//"technicList":[{id:'JP09',text:'穿筘'},{id:'JP11',text:'结经'}]
function customReady() {
	$.when( 
			Mes.getJsonDeferred("/plan/mesPlanMonth/getArrangeTechnic/index.do",""),
			Mes.getJsonDeferred("/plan/common/getdicts/index.do","types=plan_object")
	).done(function(data1,data2){
		dropdownDatas = data2;
		dropdownDatas.shiftStatusList = [{id:'0',text:'未下发'},{id:'10',text:'已下发'}];
		//var workshopList = data1;
		var convertData = [];
		 
		for(var i=0;i<data1.length;i++)
		{
			convertData.push({'id':data1[i].processCode,'text':data1[i].processName});
		} 
		
		dropdownDatas.technicList = convertData;
		 
		ddwAddOption($("#shiftStatus"),dropdownDatas.shiftStatusList);
		ddwAddOption($("#technicId"),dropdownDatas.technicList);
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
	//(argUrl,urlData,jsonData,succCallBack,errorCallback)
	//Mes.loadJson2("/test1/"+pageNo+"/"+pageSize+"/a.do",param,"{}",
	Mes.loadJson2("/plan/mesPlanArrangeSheet/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
		function(result){
			debugger
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
 function onRelPlanChanged(sender){
//	 if ($(sender).val()==0){
//		 $('#planSheetId').css('display','none');
//	 }else{
//		 $('#planSheetId').css('display','');
//	 }
	 $('#popUpForm').data('bootstrapValidator')  
     .updateStatus('planSheetId', 'NOT_VALIDATED',null)  
     .validateField('planSheetId');  
 };
 function workshopChg(sender){
	 on_popup_workshop_change($(sender).val());
	 return false;
 };
 function on_popup_workshop_change(workshopId){
	 debugger
	 if (workshopId){
		var userList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=STAFF_INFO&parentCode="+workshopId);
		userList =  changeCode2Name(userList);
//		$('#popUpForm').find("[id=taskStarter]").select2({
//			data:userList 
//		});
//		$('#popUpForm').find("[id=taskUser]").select2({
//			data:userList  
//		});
	 }else{
//		 $('#popUpForm').find("[id=taskStarter]").select2({
//				data:[{id:'1',text:'1'},{id:'2',text:'2'}] 
//			});
//			$('#popUpForm').find("[id=taskUser]").select2({
//				data:[{id:'2',text:'2'},{id:'1',text:'1'}] 
//			});
	 }
 };
 function on_edit_sheet(sender,id,sheetId){
	 debugger
	 var param = 'id='+id;
	 Mes.getJson2("/plan/mesPlanTempTask/get/index.do",param,		
		function(result){
		 result.data.dropdownDatas = dropdownDatas;
		 var userStr = "";
		 debugger
		 if (result.data.taskStarter){
			 userStr = "'"+result.data.taskStarter+"'";
		 }
		 if (result.data.taskUser){
			 if (userStr){userStr = userStr+","}
			 userStr = userStr+"'"+result.data.taskUser+"'";
		 }
		 result.data.taskUserList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=STAFF_INFO&codeList=" + userStr);
			
		 var html=template('popup.tmp',result.data);
		 layer.open({
			 type: 1,
			 title :'临时任务',
			 area: ['45em', '34em'],
			 content: html, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			 btn: parseInt((result.data.taskStatus||0))>=1?[]:['确认', '取消'],
			 btn1: function(index, layero){
				 $('#popUpForm').submit();
				   //layer.close(index);
				  }
			  ,btn2: function(index, layero){
				 // alert(2);
			  },
			  success: function(layero){
				 // ddwAddOption($('#popUpForm').find("[id=workshopId]"),dropdownDatas.workshopList);
				//	ddwAddOption($('#popUpForm').find("[id=taskType]"),dropdownDatas.taskTypeList);
					on_popup_workshop_change(result.data.workshopId);
			  }
			 });
		 setSheetStatus(result.data.taskStatus);
		 $('#taskDesc').text(result.data.taskDesc||'');
		 $('#popUpForm').bootstrapValidator({
			  message: 'This value is not valid',
			  feedbackIcons: {
				  validating: 'glyphicon glyphicon-refresh'
			  },
			  fields: {
				  planBeginDate: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
				  },planEndDate: {
					   message: '不能为空',
					   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
					   }
				  },
				  workshopId: {
					   message: '不能为空',
					   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
					   }
				  },
				  planSheetId: {
					   message: '不能为空',
					   validators: {
						   callback: {
							   message: '不能为空或单号不存在',  
							   callback: function(value, validator) {
								 //  alert($('#popUpForm').find(":radio:checked").val());
	                        	 if ($('#popUpForm').find(":radio:checked").val()==0){
	                        		 return true;
	                        	 }else{
	                        		 if (!value) return false;
	                        		 var data = Mes.getJsonSync("/plan/mesPlanMonth/findList/index.do","erpBillNo="+value);
	                        		 if (data && data.length){
	                        			 return true;
	                        		 }else{
	                        			 return false;
	                        		 }
	                        	 }
	                        	 return true;
	                         }
						   	}
					   }
				  },
				  taskStarterName: {
					   message: '不能为空',
					   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
					   }
				  },
				  taskUserName: {
					   message: '不能为空',
					   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
					   }
				  },
				  taskDesc: {
					   message: '不能为空',
					   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
					   }
				  }
			  },
			  submitHandler: function (validator, form, submitButton) {
				  alert("submit");
			  	}
			 }).on('success.form.bv', function(e) {//点击提交之后
		         e.preventDefault();
		         var  urlPara = $('#popUpForm').serialize();
	        	//alert(JSON.stringify(param));
	   	    	Mes.loadJson("/plan/mesPlanTempTask/save/index.do",urlPara,
   	         		function(result){
   		        	 	//Mes.sysAlert(result.msg, null);
   	    				if (result.succ){
   	    					Mes.sysAlert("保存成功!",
   	    					function(index){
   	    						layer.close(index);
   		    	     			self.location.href = "/mes-plan/production/mes_plan_temp_task_list.html";
   		    	     		});
   	    				}else{
   	    					Mes.sysAlert(result.msg,function(index){
   	    						layer.close(index);
   	    					});
   	    				}
	    	     		
   	     	    	},
   	     	    	function(data){
   	     	    		alert(JSON.stringify(data))
   	     	    	});
			 });
    	},
    	function(data){
    		//alert(JSON.stringify(data))
    	});
 };
 function setSheetStatus(status){
	 if (parseInt((status||0))>=1){
		$('#popUpForm').find(":input").each(function(){
			$(this).attr('readonly','readonly');
			$(this).removeAttr("onclick");
		});
			
		$('#popUpForm').find("select").each(function(){
			$(this).attr('disabled','disabled');
		});
	 } 
 }
 function on_del_sheet(sender,id,sheetId){
	 var param = 'id='+id+"&sheetId="+sheetId;
	 Mes.sysConfirm('确认删除?删除后数据将不能恢复',function(index){
		 layer.close(index);
		 Mes.getJson2("/plan/mesPlanArrangeSheet/deletesheet/index.do",param,		
			function(result){
				//Mes.sysAlert(JSON.stringify(result.data));
			 debugger
	 			if (result.data >=1){
	 				Mes.sysAlert('删除成功!',function(index){$(sender).parent().parent().remove();layer.close(index)});
	 			}else{
	 				Mes.sysAlert('删除了0条记录,请刷新后重试!');
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

function dateChanged(sender){
	debugger
	if ($(sender).attr('name')=='planBeginDate'){
		$('#popUpForm').data('bootstrapValidator')  
	    .updateStatus('planBeginDate', 'NOT_VALIDATED',null)  
	    .validateField('planBeginDate');  
	}else{
		$('#popUpForm').data('bootstrapValidator')  
	    .updateStatus('planEndDate', 'NOT_VALIDATED',null)  
	    .validateField('planEndDate');  
	}
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
	 
	var validatePass = true;
	debugger
	$("[name=checkflag]:checkbox:checked","#tab_det_body").each(function(){
	  var tablerow = $(this).parent().parent()
	  //var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='id']").val();
	  var taskStatus = tablerow.find("[name='taskStatus']").val()||0;
	  if (parseInt(taskStatus)<1){
		  var row = {"id":id,"taskStatus":"1"};
		  selectedData.push(row);
	  }
	});
//	if (!validatePass) {
//		Mes.sysAlert('请先保存数据!');
//		return;
//	};
	
	layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认下发吗', {title:'提示'}, function(index){
		on_check_inner(selectedData);
		layer.close(index);
	});
};

function on_check_inner(selectedData){
	var urlPara = '';
	if (selectedData.length>0){
		Mes.loadJson2("/plan/mesPlanTempTask/batchcheck/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
				//result.data.dropdownDatas = dropdownDatas;
	        	//var html4= template('device_div.tmp', result.data);
	        	//$('#device_div').html(html4);
	        	//refreshStatus(sheetStatus);
				self.location.href = "/mes-plan/production/mes_plan_scene_task_list.html";
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("没有选择数据或数据已下发,请重新选择数据!")
	}
}






function onCancelBatch(){
	//检查是否有数据未保存，未保存先保存数据
	var selectedData = [];
	var validatePass = true;
	debugger
	$("[name=checkflag]:checkbox:checked","#tab_det_body").each(function(){
	  var tablerow = $(this).parent().parent()
	  //var rowIndex = tablerow.find("[name='rowIndex']").text();
	  var id = tablerow.find("[name='id']").val();
	  var taskStatus = tablerow.find("[name='taskStatus']").val()||0;
	  if (parseInt(taskStatus)==1){
		  var row = {"id":id,"taskStatus":"0"};
		  selectedData.push(row);
	  }
	});
//	if (!validatePass) {
//		Mes.sysAlert('请先保存数据!');
//		return;
//	};
	layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认取消吗', {title:'提示'}, function(index){
		on_cancel_inner(selectedData);
		layer.close(index);
	});
	
};

function on_cancel_inner(selectedData){
	var urlPara = '';
	if (selectedData.length>0){
		Mes.loadJson2("/plan/mesPlanTempTask/cancelCheck/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
				//result.data.dropdownDatas = dropdownDatas;
	        	//var html4= template('device_div.tmp', result.data);
	        	//$('#device_div').html(html4);
	        	//refreshStatus(sheetStatus);
				self.location.href = "/mes-plan/production/mes_plan_scene_task_list.html";
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("没有选择数据或数据已下发,请重新选择数据!")
	}
};


function onClickUser(isFirQry,pageNo,pageSize,lastPara,isOpened){
 	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
 	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','STAFF_INFO',function(index, layero){
 		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
 		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
 		 var rowCodeName = $(rowHtml).find("[name=name]").text()||'';
 		 $('#taskStarter').val(rowCode);
 		 $('#taskStarterName').val(rowCodeName);
 		 layer.close(index);
		  });
  };
  
  
  function onClickUser2(isFirQry,pageNo,pageSize,lastPara,isOpened){
 	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
 	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','STAFF_INFO',function(index, layero){
 		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
 		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
 		 var rowCodeName = $(rowHtml).find("[name=name]").text()||'';
 		 $('#taskUser').val(rowCode);
 		 $('#taskUserName').val(rowCodeName);
 		 layer.close(index);
		  });
  };
	  
  function onClickUser3(isFirQry,pageNo,pageSize,lastPara,isOpened){
 	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
 	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','STAFF_INFO',function(index, layero){
 		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
 		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
 		 var rowCodeName = $(rowHtml).find("[name=name]").text()||'';
 		 $('#popUpForm').find("[id=taskStarter]").val(rowCode);
 		 $('#popUpForm').find("[id=taskStarterName]").val(rowCodeName);
 		$('#popUpForm').data('bootstrapValidator')  
 	     .updateStatus('taskStarterName', 'NOT_VALIDATED',null)  
 	     .validateField('taskStarterName');  
 		 layer.close(index);
		  });
  };
  
  function onClickUser4(isFirQry,pageNo,pageSize,lastPara,isOpened){
	 	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
	 	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','STAFF_INFO',function(index, layero){
	 		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
	 		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
	 		 var rowCodeName = $(rowHtml).find("[name=name]").text()||'';
	 		 $('#popUpForm').find("[id=taskUser]").val(rowCode);
	 		 $('#popUpForm').find("[id=taskUserName]").val(rowCodeName);
	 		$('#popUpForm').data('bootstrapValidator')  
	 	     .updateStatus('taskUserName', 'NOT_VALIDATED',null)  
	 	     .validateField('taskUserName');
	 		 layer.close(index);
			  });
	  };
 