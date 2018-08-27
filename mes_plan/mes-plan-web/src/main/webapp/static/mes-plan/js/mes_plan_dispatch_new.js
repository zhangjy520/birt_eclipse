var dropdownDatas = {};
var mesPlanData;
var parId; 
function customReady(){
	//sheetId=PA201625801&billno=2016-258-01
	var id = Mes.getUrlParam("id");
	parId = Mes.getUrlParam("parId");
	sheetId = Mes.getUrlParam("sheetId");
	erpId = Mes.getUrlParam("erpId");
	sheetStatus = Mes.getUrlParam("sheetStatus");
	remarks = Mes.getUrlParam("remarks");
	debugger
	if (erpId==""){ 
		Mes.sysAlert("erpId参数传入为空");
		return;
	}
	
	$.when(Mes.getJsonDeferred("/plan/mesPlanMonth/get/index.do","id="+erpId)
	).done(function(data1,data2){
		mesPlanData = data1;
		var workcenterList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+mesPlanData.workshopId);
		dropdownDatas.workcenter = changeCode2Name(workcenterList);
		ddwAddOption($("[name='workcenterId']"),dropdownDatas.workcenter);
		var param;
		if (id){
			param = "id="+id;
			Mes.getJson2("/plan/mesPlanDispatch/getsheet/det.do",param,		
		    		function(result){
						//Mes.sysAlert(JSON.stringify(result.data));
						var chgGoodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList='"+result.data.deviceChangeGoodsId+"'");
						if (chgGoodsList && chgGoodsList.length>0){
							result.data.deviceChangeGoodsName = chgGoodsList[0].name;
						}
						$('#dispatchForm').autofill( result.data );
						$('#capacitySpan').text(result.data.capacityInput);
						$('#dispatchQtySpan').text(result.data.dispatchQty);
						$('#diviceTotalSpan').text(result.data.deviceQty+result.data.deviceChangeQty);
						$('#unitSpan').text(mesPlanData.qtyUnit);
						$('#unitSpan2').text(mesPlanData.qtyUnit);
						
						setSheetStatus(sheetStatus);
			    	},
			    	function(data){
			    		//alert(JSON.stringify(data))
			    	});
		}else
		{
			var todayStr =  (new Date()).Format('yyyy-MM-dd');
			setSheetStatus(0); 
			$('#planBeginDate').datepicker("setDate", mesPlanData.planBeginDate<todayStr?mesPlanData.planBeginDate:todayStr);
			//$('#planEndDate').datepicker("setDate", mesPlanData.planEndDate);
			$('#dispatchQty').val('0');
			$('#diviceTotal').val('0');
			$('#dispatchQtySpan').text('0');
			$('#diviceTotalSpan').text('0');
			$('#unitSpan').text(mesPlanData.qtyUnit);
			$('#unitSpan2').text(mesPlanData.qtyUnit);
		}
		setTimeout(function(){
			$('#planBeginDate').on('changeDate', function(ev){
				setDeviceTotal2('#deviceQty','#deviceChangeQty','#capacityInput','#planBeginDate','#planEndDate');
			});
			
			$('#planEndDate').on('changeDate', function(ev){
				setDeviceTotal2('#deviceQty','#deviceChangeQty','#capacityInput','#planBeginDate','#planEndDate');
			}); 
		},1000);
		
		$('#dispatchForm').bootstrapValidator({
			  message: 'This value is not valid',
			  feedbackIcons: {
			  //valid: 'glyphicon glyphicon-ok',
			  //invalid: 'glyphicon glyphicon-remove',
			  validating: 'glyphicon glyphicon-refresh'
			  },
			  fields: {
				  deviceQty: {
						   message: '请输入数字',
						   validators: {
						   notEmpty: {
							   message: '不能为空'
							},
							stringLength: {
									    min: 1,
									    max: 4,
									    message: '长度必须在1到4位之间'
								   },
							regexp: {
									    regexp: /^[0-9_]+$/,
									    message: '设备只能是数字'
								   }
						}
				  },
				  deviceChangeQty: {
					   message: '请输入数字',
					   validators: {
					   notEmpty: {
						   message: '不能为空'
						},
						stringLength: {
								    min: 1,
								    max: 4,
								    message: '长度必须在1到4位之间'
							   },
						regexp: {
								    regexp: /^[0-9_]+$/,
								    message: '设备只能是数字'
							   }
					}
				  },
				  capacityInput: {
						   message: '请输入数字',
						   validators: {
						   notEmpty: {
							   message: '不能为空'
							},
							stringLength: {
									    min: 1,
									    max: 8,
									    message: '长度必须在1到8位之间'
								   },
							regexp: {
									    regexp: /^[0-9_]+$/,
									    message: '只能输入数字'
								   }
						}
				  },
				  workcenterId: {
						   message: '请输入数字',
						   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
						}
				  },
				  planBeginDate: {
						   message: '请输入数字',
						   validators: {
						   notEmpty: {
							   message: '不能为空'
							},
							callback: {  
		                         message: '日期需要大于等于'+mesPlanData.planBeginDate+",跨度60天",  
		                         callback: function(value, validator) {  
		                        	 debugger
		                        	 if (mesPlanData && mesPlanData.planBeginDate > value){
		                        		 return false;
		                        	 }
		                        	 var endDate = $('#dispatchForm').find("[name=planEndDate]").val();
		                        	 if (!endDate) return true;
		                        	 endDate = endDate.replace("-","/").replace("-","/");
		                        	 
		                        	 var dayBetween = (new Date(endDate) - new Date(value.replace("-","/").replace("-","/"))  )/86400000 +1;
		                        	 var result = dayBetween>=1 && dayBetween<=60;
//		                        	 if (result){
//		                        	 setDeviceTotal2('#deviceQty','#deviceChangeQty','#capacityInput','#planBeginDate','#planEndDate');
//		                        	 }
		                            return result;  
		                         }  
		                     } 
						}
				  },
				  planEndDate: {
						   message: '请输入数字',
						   validators: {
						   notEmpty: {
							   message: '不能为空'
							},
							callback: {  
		                         message: '日期需要小于等于'+mesPlanData.planEndDate+",跨度60天",  
		                         callback: function(value, validator) {  
		                        	 if (mesPlanData && mesPlanData.planEndDate < value){
		                        		 return false;
		                        	 }
		                        	 var beginDate = $('#dispatchForm').find("[name=planBeginDate]").val();
		                        	 if (!beginDate) return true;
		                        	 beginDate = beginDate.replace("-","/").replace("-","/");
		                        	 var dayBetween = (new Date(value.replace("-","/").replace("-","/")) -new Date(beginDate) )/86400000 +1
		                        	 var result = dayBetween>=1 && dayBetween<=60;
//		                        	 if (result){
//			                        	 setDeviceTotal2('#deviceQty','#deviceChangeQty','#capacityInput','#planBeginDate','#planEndDate');
//			                        	 }
		                            return result ;  
		                         }  
		                     } 
						}
				  }
			  },
			  submitHandler: function (validator, form, submitButton) {
				  alert("submit");
			  	}
			 }).on('success.form.bv', function(e) {//点击提交之后
		         // Prevent form submission
		         e.preventDefault();
		         var dispatchQty = $('#dispatchQty').val();
		         if (parseFloat(dispatchQty)>999999999){
		        	 layer.alert("数量不能大于999999999");
		        	 return;
		         }
		         var  param = $('#dispatchForm').serialize();
		         Mes.loadJson2("/plan/mesPlanDispatch/save/det.do",param,"{erpId:'"+erpId+"',sheetId:'"+sheetId+"',remarks:'"+decodeURIComponent(remarks)+"'}",		
	         		function(result){
		        	 	//Mes.sysAlert(result.msg, null);
		        	 debugger
		        	 	if (!!parId){
//		    	     		Mes.sysAlert("保存成功!",
//		    	     				function(index){
//		    	     			layer.close(index);
//		    	     			
//		    	     		});
		        	 		self.parent.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+parId+"&erpId="+erpId;
		    	     	}else{
		    	     		debugger
//		    	     		Mes.sysAlert("保存成功!",
//		    	     				function(index){
//		    	     			layer.close(index);
//		    	     			
//		    	     		});
		    	     		self.parent.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+result.data+"&erpId="+erpId;
		    	     	}
	     	    	},
	     	    	function(data){
	     	    		alert(JSON.stringify(data))
	     	    	});
			 });
	});
};

function setSheetStatus(sheetStatus){
	debugger
	if (parseInt(sheetStatus) >= 10){
		debugger
		$('#dispatchForm').find(":input").each(function(){
			$(this).attr('readonly','readonly');
		});
			
		$('#dispatchForm').find("select").each(function(){
			$(this).attr('disabled','disabled');
		});
		
		$('#dispatchForm').find(":button").each(function(){
			$(this).attr('disabled','disabled');
		});
		
		//确认按钮隐藏
		$('#confirmBtn').css('display','none');
		
//		$('#planBeginDate').datepicker().on('click',function(e) {  
//			event.preventDefault();
//		    event.stopPropagation();
//		});  
//		$('#planEndDate').datepicker().on('click',function(e) {  
//			event.preventDefault();
//		    event.stopPropagation();
//		});  
	}else{
		$('#planBeginDate').datepicker({
		    format: 'yyyy-mm-dd',
		    language: "zh-CN",//设置语言
		     autoclose: true//设置是否关闭
		}).on('hide',function(e) {  
	        $('#dispatchForm').data('bootstrapValidator')  
	        .updateStatus('planBeginDate', 'NOT_VALIDATED',null)  
	        .validateField('planBeginDate');  
	        $('#dispatchForm').data('bootstrapValidator')  
	        .updateStatus('planEndDate', 'NOT_VALIDATED',null)  
	        .validateField('planEndDate');  
		});  
		
		
		$('#planEndDate').datepicker({
		    format: 'yyyy-mm-dd',
		    language: "zh-CN",//设置语言
		     autoclose: true//设置是否关闭
		}).on('hide',function(e) {  
	        $('#dispatchForm').data('bootstrapValidator')  
	        .updateStatus('planEndDate', 'NOT_VALIDATED',null)  
	        .validateField('planEndDate');  
	        $('#dispatchForm').data('bootstrapValidator')  
	        .updateStatus('planBeginDate', 'NOT_VALIDATED',null)  
	        .validateField('planBeginDate'); 
		}); 
	}
}

function plan_dispatch_save(){
	$('#dispatchForm').submit();
}

function plan_dispatch_close(){
	Mes.sysConfirm('确认返回?', function(index){
		layer.close(index);
		//self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+parId+"&erpId="+erpId+"&remarks="+remarks;
	}) ;
//	if (!!parId){
//		
//	}else{
//		history.go(-1);
//	}
}
/*
function setDeviceTotal(sender,inputId){
	Mes.sysLoading('正在计算数量',500);
	 var totalDevice = 0 ;
	if ($(sender).val() !=""){
		totalDevice = totalDevice +parseInt($(sender).val())
	}
	if ($(inputId).val() !=""  ){
		totalDevice = totalDevice +parseInt($(inputId).val())
	}
	if (isNaN(totalDevice)) {
		totalDevice="";
	} 
	$('#diviceTotalSpan').text(totalDevice);
	
	if ($('#capacityInput').val() !=""  ){
		if (!totalDevice) {totalDevice =0;}
		var dispatchQty = totalDevice*parseInt($('#capacityInput').val());
		if (isNaN(totalDevice)){
			dispatchQty = 0;
		}
	}
	  
	var beginDate = $('#planBeginDate').val();
	var endDate = $('#planEndDate').val();
	if (beginDate !=""  && endDate !="" ){
		var dayNumber= (new Date(endDate) - new Date(beginDate))/24/3600000 +1 ;
		if (dayNumber<0) {
			dayNumber = 0 ;
		}
		$('#dispatchQtySpan').text(dispatchQty*dayNumber);
		$('#dispatchQty').val(dispatchQty*dayNumber);
		
	}else{
		$('#dispatchQtySpan').text('');
		$('#dispatchQty').val('');
		
	}
};
*/

function setDeviceTotal2(deviceQty,changeQty,capacity,beginDate,endDate){
	Mes.sysLoading('正在计算数量',1000);
	 var totalDevice = 0 ;
	if ($(deviceQty).val() !=""){
		totalDevice = totalDevice +parseInt($(deviceQty).val())
	}
	if ($(changeQty).val() !=""  ){
		totalDevice = totalDevice +parseInt($(changeQty).val())
	}
	if (isNaN(totalDevice)) {
		totalDevice="";
	} 
	$('#diviceTotalSpan').text(totalDevice);
	//if ($('#optionsRadios2').prop('checked')) return;
	if ($(capacity).val() !=""  ){
		if (!totalDevice) {totalDevice = 0;}
		var dispatchQty = totalDevice*parseInt($(capacity).val());
		if (isNaN(dispatchQty)){
			dispatchQty = 0;
		}
	}
	debugger
	//var beginDate1 = $(beginDate).val();
	//var endDate1 = $(endDate).val();
	var beginDate1 = $(beginDate).datepicker("getDate");
	var endDate1 = $(endDate).datepicker("getDate");
	
	if (beginDate1 !=""  && endDate1 !="" ){
		var dayNumber= (endDate1 - beginDate1)/24/3600000 +1 ;
		if (dayNumber<0) {
			dayNumber = 0 ;
		}
		$('#dispatchQtySpan').text(dispatchQty*dayNumber);
		$('#dispatchQty').val(dispatchQty*dayNumber);
	}else{
		$('#dispatchQtySpan').text('');
		$('#dispatchQty').val('');
	}
};

function addWorkCenterDdw(workshopId){
	
};
function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
 	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
 	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickGoods','MATRIAL_INFO',function(index, layero){
 		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
 		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
 		 var rowName = $(rowHtml).find("[name=name]").text()||'';
 		 $('#deviceChangeGoodsId').val(rowCode);
 		 $('#deviceChangeGoodsName').val(rowName);
 		 layer.close(index);
		});
  };
  
  
  function chg_goods(sender){
		var goodsId = $(sender).val();
		if (goodsId){
			var goodsRow=Mes.getJsonSync2("/plan/dubbo/getMdmOne/index.do","mdmType=MATRIAL_INFO&code="+goodsId);
			if (goodsRow && goodsRow.name){
				$('#deviceChangeGoodsName').val(goodsRow.name);
			}else{
				layer.alert('输入的物料['+goodsId+']不存在!');
				$(sender).val('');
				$('#deviceChangeGoodsName').val('');
			}
		}else{
			$('#deviceChangeGoodsName').val('');
		}
	};
	
