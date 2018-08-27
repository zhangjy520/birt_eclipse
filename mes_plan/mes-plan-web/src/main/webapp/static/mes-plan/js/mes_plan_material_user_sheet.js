var parId; 
function customReady(){
	//sheetId=PA201625801&billno=2016-258-01
	var id = Mes.getUrlParam("id");
	parId = Mes.getUrlParam("parId");
	sheetId = Mes.getUrlParam("sheetId");
	erpId = Mes.getUrlParam("erpId");
	var param;
	if (id){
		loadData(id);
		
	}else
	{
		var html = template('detailTab.tmp', {status:0,mesPlanMaterialUseDetList:[]});
    	$('#detailTab').html(html);
		var html2 = template('detailTab2.tmp');
    	$('#detailTab2').html(html2);
    	$('#useDate').datepicker({
    	    format: 'yyyy-mm-dd',
    	    language: "zh-CN",//设置语言
    	     autoclose: true//设置是否关闭
    	}).on('hide',function(e) {  
            $('#useForm').data('bootstrapValidator')  
            .updateStatus('useDate', 'NOT_VALIDATED',null)  
            .validateField('useDate');  
    	});  
    	validateSheet();
	}
	
};

function validateSheet(){
	$('#useForm').bootstrapValidator({
		  message: 'This value is not valid',
		  feedbackIcons: {
		  //valid: 'glyphicon glyphicon-ok',
		  //invalid: 'glyphicon glyphicon-remove',
		  validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
//			  workshopId: {
//					   message: '不能为空',
//					   validators: {
//					   notEmpty: {
//						   message: '不能为空'
//						},
//						callback: {  
//	                         message: '明细表已有数据,不能修改',  
//	                         callback: function(value, validator) {  
////	                        	  if ($("#detailTableBody").children("tr").length && $("#detailTableBody").children("tr").length >0 ){
////	                        		  return false;
////	                        	  }else{
////	                        		  return true;
////	                        	  }
//	                        	 return true;
//	                         }  
//	                     } 
//					}
//			  },
			  useType: {
				   message: '不能为空',
				   validators: {
				   notEmpty: {
					   message: '不能为空'
					}
				}
			  },
			  title: {
				   message: '不能为空',
				   validators: {
				   notEmpty: {
					   message: '不能为空'
					}
				}
			  },
			  useDate: {
				   message: '不能为空',
				   validators: {
				   notEmpty: {
					   message: '不能为空'
					}
				}
			  },
			  useDesc: {
					   message: '长度必须在1到1000位之间',
					   validators: {
						stringLength: {
								    min: 1,
								    max: 1000,
								    message: '长度必须在1到1000位之间'
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
	         var  urlParam = $('#useForm').serialize();
	         var data = []  ;
	         var validateFlag = true;
	         $("#detailTableBody").children("tr").each(function(){
	   		  var tablerow = $(this);
	   		  var saveBtnStatus = tablerow.find("[name='saveBtn']").attr("disabled") ;
	   		  if (!saveBtnStatus || saveBtnStatus != "disabled"){
	   			  var id = tablerow.find("[name='id']").val();
	   			  var sheetId = tablerow.find("[name='sheetId']").val();
	   			  var scheduleDate = tablerow.find("[name='scheduleDate']").val();//排程单号为排次源单号
	   			  var goodsId = tablerow.find("[name='goodsId']").val();
	   			  var useQty = tablerow.find("[name='useQty']").val();
	   			  
	   			  var detData={'id':id,'sheetId':sheetId,'scheduleDate':scheduleDate,'goodsId':goodsId,'useQty':useQty};
	   			  //验证不通过
	   			  if (!validateRow(detData)){
	   				validateFlag = false;
	   			  }
	   			  data.push(detData);
	   		  }
	   	});
	     if (!validateFlag) return false;
         if (data.length>0 || !$('#id').val() ){
				Mes.loadJson2("/plan/materialUseHead/batchsave/det.do",urlParam,JSON.stringify(data),		
		     		function(result){
					 	var id = result.data;
					 	if (id){
					 		Mes.sysAlert(result.msg);
					 		loadData(id);
					 	}else{
					 		Mes.sysAlert(result.msg,null);
					 	}
		 	    	},
		 	    	function(data){
		 	    		Mes.sysAlert(JSON.stringify(data))
		 	    });
			}else{
				Mes.getJson2("/plan/materialUseHead/save/index.do",urlParam,		
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
			}
	});
}

function loadData(id){
	param = "id="+id;
	debugger
	Mes.getJson2("/plan/materialUseHead/getsheet/index.do",param,		
    		function(result){
				//Mes.sysAlert(JSON.stringify(result.data));
				//dsfsdf,ewqeqwewqlkjkldkladk1=kdskfdsk
				//$('#useForm').autofill( result.data );
				var html = template('detailTab.tmp', result.data);
            	$('#detailTab').html(html);
				//$("#sheetIdSpan").text(result.data.sheetId);
				var html2 = template('detailTab2.tmp', result.data);
            	$('#detailTab2').html(html2);
            	//Mes.sysAlert(JSON.stringify(result.data))
            	
            	$('#useDate').datepicker({
            	    format: 'yyyy-mm-dd',
            	    language: "zh-CN",//设置语言
            	     autoclose: true//设置是否关闭
            	}).on('hide',function(e) {  
                    $('#useForm').data('bootstrapValidator')  
                    .updateStatus('useDate', 'NOT_VALIDATED',null)  
                    .validateField('useDate');  
            	});  
            	
            	if (result.data.status == "10"){
            		$('#saveBtn').hide();
            		$('#checkBtn').hide();
            		$('#newBtn').css('visibility','hidden');
            	}else{
            		validateSheet();
            	}
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
}

function validateRow(tabRow){
	//'scheduleDate':scheduleDate,'goodsId':goodsId,'useQty':useQty
	if (!tabRow.scheduleDate || !tabRow.goodsId || !tabRow.useQty){
		Mes.sysAlert('领用日期，原料，领用数量不能为空或是0!');
		return false;
	}
	return true;
}

function on_save(){
	$('#useForm').submit();
}


function on_check(){
	Mes.getJson2("/plan/materialUseHead/check/index.do",param,		
    		function(result){
				//$('#useForm').autofill( result.data );
				//var html = template('detailTab2.tmp', result.data);
            	//$('#detailTab2').html(html);
				if (result.data.id){
					Mes.sysAlert('下发成功',function(index){
						layer.close(index);
						loadData(result.data.id);
					});
				}
				
	    	},
	    	function(data){
	    	});
}

function plan_dispatch_close(){
	if (!!parId){
		Mes.sysConfirm('确认返回?', function(){self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+parId;
		}) ;
	}
}

function delRow(sender){
	var rowJquery = $(sender).parent().parent();
	var id = rowJquery.find("[name=id]").val();
	if (!id){
		rowJquery.remove();
	}else{
		Mes.sysConfirm("确认删除,删除后无法恢复",function(){
			var urlParam = "id="+id;
			Mes.getJson2("/plan/materialUseHead/delDetRow/index.do",urlParam,		
		    		function(result){
						rowJquery.remove();
						layer.closeAll();
			    	},
			    	function(data){
			    		//alert(JSON.stringify(data))
			    	});
		});
	}
};

function addRow(){
	var rowHtml = template('srciptRow.tmp', {scheduleDate:$("#useDate").datepicker("getDate").Format('yyyy-MM-dd'),goodsId:'',leftQty:'',useQty:''});
	var row = $(rowHtml).appendTo($("#detailTableBody"));
	debugger;
	row.find("[name=saveBtn]").prop('disabled','');
	row.find("[name=goodsId]").next().click();
};

function setChangedCommon(sender){
	debugger
	$(sender).next().text($(sender).val());
	$(sender).parent().parent().find("[name=saveBtn]").prop('disabled','');
	$(sender).hide();
	$(sender).next().show();
}
function setChangedUse(sender,scheduleDate,goodsId,workshopId){
	debugger;
	if (!scheduleDate || !goodsId || goodsId=='请选择' || !workshopId) {
		setChangedCommon(sender);
		return;
	}
	var urlPara = "scheduleDate="+scheduleDate+"&goodsId="+goodsId+"&workshopId="+workshopId ;
	Mes.loadJson2("/plan/use/findList/index.do",urlPara,"{}",		
    		function(result){
				if (result.data.length && result.data.length >= 1){
					var leftQty =  result.data[0].qty - result.data[0].useQty || 0;
					//var leftQty =  result.data[0].qty;
					var useQtyObj = $(sender).parent().parent().find("[name=leftQty]");
					useQtyObj.text(leftQty);
					setChangedCommon(sender);
				}
				else{//没有可领用的原材料
					//$(sender).focus();
					//$(sender).val($(sender).next().text());
					var useQtyObj = $(sender).parent().parent().find("[name=leftQty]");
					useQtyObj.text('0');
					setChangedCommon(sender);
					//Mes.sysAlert("日期"+scheduleDate+"原材料"+goodsId+"不能领用",null);
				}
				return true;
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
	//setChangedCommon(sender);
}

function setChangeQty(sender){
	if (!$(sender).val()) return;
	var leftQty = $(sender).parent().parent().find("[name=leftQty]").text();
	if (!leftQty){
		Mes.sysAlert("请选输入排程日期和原材料",null);
		return;
	}
	var qty = parseFloat($(sender).val()) ;
//	if (leftQty && parseFloat(leftQty) < qty){
//		$(sender).focus();
//		$(sender).val($(sender).next().text());
//		
//		Mes.sysAlert("领用数量"+qty+"不能大于可领数量"+leftQty,null);
//	}else{
//		setChangedCommon(sender);
//	}
	setChangedCommon(sender);
};
function on_close(){
	Mes.sysConfirm('确认返回?',
			function(){
		self.location.href = "/mes-plan/production/mes_plan_material_use_list.html";
	}) ;
}
