var dropdownDatas={};
var parId; 
function customReady(){
	//sheetId=PA201625801&billno=2016-258-01
	var id = Mes.getUrlParam("id");
	parId = Mes.getUrlParam("parId");
	erpBillNo = Mes.getUrlParam("erpBillNo");
	sheetId = Mes.getUrlParam("sheetId");
	erpId = Mes.getUrlParam("erpId");
	
	var param
	if (parId==""){ 
		Mes.sysAlert("parId参数传入为空");
		return;
	}
	erpId = $.cookie('erpId'+parId);
	var head;
	var mesPlanMonth;
	if (parId){
		//head = JSON.parse(headStr);
		head = Mes.getJsonSync("/plan/mesPlanDispatch/getDet/index.do","id="+parId);
		mesPlanMonth = Mes.getJsonSync("/plan/mesPlanMonth/get/index.do","id="+erpId);
	}
	 
	
//	billNo:"2016-258_12"//	createTime:"2017-08-15 07:20:14"//	deviceChangeGoodsId:null//	deviceChangeQty:1//	deviceQty:1
//	deviceRequire:""//	dispatchQty:1080//	dispatchStatus:"0"//	finishQty:0
//	id:"557ac25afe444d5484c2dc9dd2cea7b7"//	isNewRecord:false
//	planBeginDate:"2017-07-14"//	planEndDate:"2017-07-19"//	planBeginDate1:null//	planBeginDate2:null
//	remarks:null//	sheetId:"DP1502781301335-1193959466"
//	srcBillNo:null//	updateTime:"2017-08-15 07:28:10"//	workCenter:"1"//	workCenterQty:4629
	//var workshopList = Mes.getJsonSync2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00");
	//dropdownDatas.workshop = changeCode2Name(workshopList);
	var deviceList = Mes.getJsonSync("/plan/mesPlanDeviceDay/findDevice2WorkGroupList/index.do","workcenterId="+head.workcenterId);//&code=+head.workcenterId
	var objSelect = $("#deviceId");
	for(var i=0;i<deviceList.length;i++)
	{
		objSelect.append("<option value='"+deviceList[i].deviceId+"'>"+"["+deviceList[i].deviceId+"]"+deviceList[i].deviceName+"</option>"); 
	}
	
	if (id){
		param = "id="+id;
		Mes.getJson2("/plan/device/get/index.do",param,		
	    		function(result){
					//Mes.sysAlert(JSON.stringify(result.data));
					debugger
					setSheetStatus(result.data.dispatchStatus);
					if (!!result.data.workcenterId && result.data.workcenterId != head.workcenterId){
						alert('workcenterId不一致，请检查');
					}else{
						result.data.workcenterId = head.workcenterId;
					}
					$('#dispatchForm').autofill( result.data );
					//设置只读
					$('#planBeginDate').attr('readonly','readonly');
					$('#planEndDate').attr('readonly','readonly');
					//$('#deviceId').attr('readonly','readonly');
					
					$('#workCenterQty').text(result.data.workCenterQty);
					$('#workCenterSpan').text(result.data.workCenterQty);
					//$('#workcenterId').val(head.workcenterId);
					var workcenterMap = Mes.getJsonSync2("/plan/dubbo/getMdmOne/index.do","mdmType=WORK_CENTER&code="+head.workcenterId)||{};
					$('#workcenterIdSpan').text(workcenterMap.name||head.workcenterId);
					$('#capacitySpan').text(head.capacityInput+mesPlanMonth.qtyUnit);
					$('#capacityInput').val(head.capacityInput);
					$('#qtyUnit').text(mesPlanMonth.qtyUnit);
		    	},
		    	function(data){
		    		//alert(JSON.stringify(data))
		    	});
	}else
	{
		$('#planBeginDate').attr('readonly','readonly');
		$('#planEndDate').attr('readonly','readonly');
		$('#erpBillNo').val(erpBillNo);
		if (head){
			$('#planBeginDate').val(head.planBeginDate);
			$('#planEndDate').val(head.planEndDate);
			$('#workcenterId').val(head.workcenterId);
			var workcenterMap = Mes.getJsonSync2("/plan/dubbo/getMdmOne/index.do","mdmType=WORK_CENTER&code="+head.workcenterId)||{};
			$('#workcenterIdSpan').text(workcenterMap.name||head.workcenterId);
			$('#capacitySpan').text(head.capacityInput+mesPlanMonth.qtyUnit);
			$('#capacityInput').val(head.capacityInput);
		}else{
			$('#planBeginDate').val('');
			$('#planEndDate').val('');
			$('#workcenterId').val('');
			alert('找不到cookie数据,请重新打开页面操作!');
			return;
		}
		$('#qtyUnit').text(mesPlanMonth.qtyUnit);
	}
	
	setDeviceTotal2('#capacityInput','#planBeginDate','#planEndDate')
	
	$('#dispatchForm').bootstrapValidator({
		  message: 'This value is not valid',
		  feedbackIcons: {
		  //valid: 'glyphicon glyphicon-ok',
		  //invalid: 'glyphicon glyphicon-remove',
		  validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  planBeginDate: {
			   message: '请选择日期',
			   validators: {
				   notEmpty: {
					   message: '不能为空'
					},
					callback: {
		                   message: '日期超过范围',
		                   callback: function(value, validator, field) {
		                       return {valid:value >= head.planBeginDate,
		                    	   message:'日期不能小于'+head.planBeginDate};
		                   }
						}
					}
					},
				planEndDate: {
				   message: '日期超过范围',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						},
						callback: {
			                   message: 'The password is not valid',
			                   callback: function(value, validator, field) {
			                       return {valid:value <= head.planEndDate,
			                    	   message:'日期不能大于'+head.planEndDate};
			                   }
							}}
						},
							
//			  deviceQty: {
//					   message: '请输入数字',
//					   validators: {
//					   notEmpty: {
//						   message: '不能为空'
//						},
//						stringLength: {
//								    min: 1,
//								    max: 4,
//								    message: '长度必须在1到4位之间'
//							   },
//						regexp: {
//								    regexp: /^[0-9_]+$/,
//								    message: '设备只能是数字'
//							   }
//					}
//			  },
//			  deviceChangeQty: {
//				   message: '请输入数字',
//				   validators: {
//				   notEmpty: {
//					   message: '不能为空'
//					},
//					stringLength: {
//							    min: 1,
//							    max: 4,
//							    message: '长度必须在1到4位之间'
//						   },
//					regexp: {
//							    regexp: /^[0-9_]+$/,
//							    message: '设备只能是数字'
//						   }
//				}
//			  },
			  capacityInput: {
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
								    message: '只能输入数字'
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
	         var  param = $('#dispatchForm').serialize();
	         if (!parId){ 
	        	 Mes.sysAlert("parId参数不能为空!",null);
	        	 return;
	         }
	         //检查数量是否超标
	       //检查数量是否超标
	         var leftQty;
	         debugger
	         if (!id){
	        	 leftQty = head.dispatchQty - head.workCenterQty ;
	        	 leftQty = leftQty.toFixed(2);
	        	 if (leftQty < parseFloat($('#workCenterQty').val())){
		        	 Mes.sysAlert("调度数量["+$('#workCenterQty').val()+"]不能大于厂级调度单剩余调度数量["+leftQty+"]!",null);
		        	 return;
		         }
	         } 
	         Mes.loadJson2("/plan/device/save/det.do",param,"{dispatchId:'"+parId+"'}",		
         		function(result){
	        	 	//Mes.sysAlert(result.msg, null);
    	     		Mes.sysAlert("保存成功!",
    	     				function(){self.parent.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;
    	     		});
     	    	},
     	    	function(data){
     	    		Mes.sysAlert(JSON.stringify(data))
     	    });
		 });
};



function plan_dispatch_save(){
	$('#dispatchForm').submit();
}

function plan_dispatch_close(){
	if (!!parId){
		Mes.sysConfirm('确认返回?', function(){self.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;
		}) ;
	}
}

function setDeviceTotal2(capacity,beginDate,endDate){
	debugger
	Mes.sysLoading('正在计算数量',500);
	
	//if ($('#optionsRadios2').prop('checked')) return;
	if ($(capacity).val() !=""  ){
		var dispatchQty = parseInt($(capacity).val());
		if (isNaN(dispatchQty)){
			dispatchQty = 0;
		}
	}
	
	var beginDate = $(beginDate).val();
	var endDate = $(endDate).val();
	if (beginDate !=""  && endDate !="" ){
		var dayNumber= (new Date(endDate) - new Date(beginDate))/24/3600000 +1 ;
		if (dayNumber<0) {
			dayNumber = 0 ;
		}
		$('#workCenterSpan').text(dispatchQty*dayNumber);
		$('#workCenterQty').val(dispatchQty*dayNumber);
		
	}else{
		$('#workCenterSpan').text('');
		$('#workCenterQty').val('');
		
	}
};

function setSheetStatus(sheetStatus){
	if (parseInt(sheetStatus) >= 10){
		debugger
		$('#dispatchForm').find(":input").each(function(){
			$(this).attr('readonly','readonly');
			$(this).attr('disabled','disabled');
		});
			
		$('#dispatchForm').find("select").each(function(){
			$(this).attr('disabled','disabled');
		});
		
		$('#dispatchForm').find(":button").each(function(){
			$(this).attr('disabled','disabled');
		});
		$('.input-group-addon').css('display','none');
//		$('#dispatchForm').find("a").each(function(){
//		});
		//确认按钮隐藏
		$('#confirmBtn').css('display','none');
	}

};
function getName(valueName){
	if (!valueName){
		return '';
	}else{
		var index = valueName.indexOf(']');
		if (valueName<0){
			return valueName;
		}else{
			return valueName.substring(index+1);
		}
	}
};