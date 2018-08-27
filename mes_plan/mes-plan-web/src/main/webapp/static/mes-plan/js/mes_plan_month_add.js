function customReady(){
	var urlPara = "types=plan_order_type,plan_order_status";
	var dropdownDatas = Mes.getJsonSync("/plan/common/getdicts/index.do",urlPara);
	var workshopList = Mes.getJsonSync2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00");
	dropdownDatas.workshop = changeCode2Name(workshopList);
	//ddwAddOption($("#planStatus"),dropdownDatas.plan_order_status);
	ddwAddOption($("#billType"),dropdownDatas.plan_order_type);
	ddwAddOption($("[name='workshopId']"),dropdownDatas.workshop);
	$("#planStatus").val('10');
//	Mes.getJson2("/plan/common/getdicts/index.do",urlPara,		
//		function(result){
//			$("#planStatus").select2({
//				data: result.data.plan_order_status
//			});
//			
//			$("#billType").select2({
//				data: result.data.plan_order_type
//			});
//		},
//    	function(data){
//    		 
//    	});
	
	$('#planBeginDate').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "zh-CN",//设置语言
	     autoclose: true//设置是否关闭
	}).on('hide',function(e) {  
        $('#addFrom').data('bootstrapValidator')  
        .updateStatus('planBeginDate', 'NOT_VALIDATED',null)  
        .validateField('planBeginDate');  
	});  
	
	$('#planEndDate').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "zh-CN",//设置语言
	     autoclose: true//设置是否关闭
	}).on('hide',function(e) {  
        $('#addFrom').data('bootstrapValidator')  
        .updateStatus('planEndDate', 'NOT_VALIDATED',null)  
        .validateField('planEndDate');  
	});  
	
	$('#addFrom').bootstrapValidator({
		  message: 'This value is not valid',
		  feedbackIcons: {
//			  valid: 'glyphicon glyphicon-ok',
//			  invalid: 'glyphicon glyphicon-remove',
			  validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  erpBillNo: {
					   message: '不能为空',
					   validators: {
						   notEmpty: {
							   message: '不能为空'
							},
						   stringLength: {
								    min: 1,
								    max: 20,
								    message: '长度必须在1到20位之间'
							   } ,
					   remote: {
	                        message: '单号重复',
	                        delay:1000,
	                        url: '/mes-plan/plan/mesPlanMonth/validateBillNo/index.do'
	                    }
					   }
			  },
			  contractId: {
				   message: '不能为空',
				   validators: {
					   stringLength: {
							    min: 0,
							    max: 20,
							    message: '长度必须在0到20位之间'
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
			  goodsId: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  qty: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						},
						stringLength: {
						    min: 1,
						    max: 10,
						    message: '长度必须在1到10位之间'
					   },integer: {
	                        message: '请输入整数'
	                    }
				   }
			  },
			  planBeginDate: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						},
						callback: {  
	                         message: '开始日期不能大于结束日期',  
	                         callback: function(value, validator) {  
	                        	 debugger
	                        	 var endDate = $('#addFrom').find("[name=planEndDate]").val();
	                        	 if (!endDate) return true;
	                        	 endDate = endDate.replace("-","/").replace("-","/");
	                        	 
	                        	 var dayBetween = (new Date(endDate) - new Date(value.replace("-","/").replace("-","/"))  )/86400000 +1;
	                        	 var result = (dayBetween>=0);
	                            return result;  
	                         }  
	                     } 
				   }
			  },
			  planEndDate: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						},
						callback: {  
	                         message: '结束日期不能小于开始日期',  
	                         callback: function(value, validator) {  
	                        	 var beginDate = $('#addFrom').find("[name=planBeginDate]").val()
	                        	 if (!beginDate) return true;
	                        	 beginDate = beginDate.replace("-","/").replace("-","/");
	                        	 var dayBetween = (new Date(value.replace("-","/").replace("-","/")) -new Date(beginDate) )/86400000 +1
	                        	 var result = (dayBetween>=0);
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
	         debugger
	         layer.confirm("确定要添加该条新订单信息？", function (index) {
	        	layer.close(index);
	   	    	urlPara =  $('#addFrom').serialize();
	   	    	 Mes.getJson2("/plan/mesPlanMonth/save/index.do",urlPara,		
	               		function(result){
	   	    		 		
	           				if (result.data){
	         			 		self.location.href = "/mes-plan/production/mes_plan_month_list.html";
	         		    	}
	           				
	           	    	},
	           	    	function(data){
	           	    		 
	           	    	});
	          }) ;
		 });
	
	};
 
function fun_submit(){
$('#addFrom').submit();
     
     //parent().test($('#createTime').val());
};
function changeBillType(sender){
	var billType=  $(sender).val();
	debugger
	if (billType == "1"){
		$('#contractId').prop('disabled','');
		$('#customerId').prop('disabled','');
	}else{
		$('#contractId').val('');
		$('#customerId').val('');
		$('#customerName').val('');
		$('#contractId').prop('disabled','disabled');
		$('#customerId').prop('disabled','disabled');
	}
	return false;
};

function chg_goods(sender){
	var goodsId = $(sender).val();
	if (goodsId){
		var goodsRow=Mes.getJsonSync2("/plan/dubbo/getMdmOne/index.do","mdmType=MATRIAL_INFO&code="+goodsId);
		if (goodsRow && goodsRow.name){
			$('#goodsName').val(goodsRow.name);
		}else{
			layer.alert('输入的物料['+goodsId+']不存在!');
			$(sender).val('');
			$('#goodsName').val('');
		}
	}else{
		$('#goodsName').val('');
	}
};
function chg_customerId(sender){
	var customerId = $(sender).val();
	if (customerId){
		var rowData=Mes.getJsonSync2("/plan/dubbo/getMdmOne/index.do","mdmType=CUSTOM_INFO&code="+customerId);
		if (rowData && rowData.name){
			$('#customerName').val(rowData.name);
		}else{
			layer.alert('输入的客户编码['+customerId+']不存在!');
			$(sender).val('');
			$('#customerName').val('');
		}
	}else{
		$('#customerName').val('');
	}
};

function onClickGoods(sender){
	 layer.open({
		 btn: ['确认', '取消'],
		 yes :function(index,dom){debugger; alert($(dom.find('iframe')[0]).contents().find('#createTime').val());layer.close(index);return false; },
		 cancel :function(index){ return true; },
		  area:['1200px', '600px'],
		  type: 2, 
		  title:'产品查询',
		  content: '/mes-plan/production/mes_cmdb_goods.html' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		}); 
};
/*function onClickUser(isFirQry,pageNo,pageSize,lastPara,isOpened){
	var param ;
 	if (isFirQry){
 		param = $('#popupForm').serialize();
 		if (param){
 			param = param+"&mdmType=CUSTOM_INFO";
 		}else{
 			param = "mdmType=CUSTOM_INFO";
 		}
 	}else{
 		//if (!lastPara) {alert('未设置分页参数!');return;}
 		param = lastPara;
 	}
 	Mes.loadJson2("/plan/dubbo/getQueryPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
 		function(result){
 			debugger
 			result.data = JSON.parse(result.data);
 			result.data.qryParam = param;
 			//result.data.dropdownDatas =  dropdownDatas;
 			//result.data.cols ="";
 			//result.data.onPage ="";
 			result.data.cols = [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'},{colCode:'simpleName',colName:'万骏化纤'}];
 			result.data.onPage = 'onClickUser';
         	//$('#detailTab').html(html+subhtml);
 			if (!isOpened){
 				var render = template.compile(PAGETEMPLATEPOPUP);  
     			var subhtml = render(result.data);  
     			
     			var render = template.compile(PAGETEMPLATEPOPUPTAB);  
     			var subhtml2 = render(result.data);  
     			layer.open({
     				 type: 1,
     				 title :'客户查询',
     				 area: ['50em', '32em'],
     				 content: subhtml+"<div id='popupDiv' style='padding:10px 5px'>"+subhtml2+'</div>', //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
     				 btn: ['确认', '返回'],
     				 btn1: function(index, layero){
     					 //$('#arrangeForm').submit();
     					 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
     					 var customCode = $(rowHtml).find("[name=code]").text()||'';
     					 var customName = $(rowHtml).find("[name=name]").text()||'';
     					 $('#customerId').val(customCode);
     					 $('#customerName').val(customName);
     					 layer.close(index);
     				 }
     				  ,btn2: function(index, layero){
     					 // alert(2);
     				  }
     				 });
 			}else{
 				var render = template.compile(PAGETEMPLATEPOPUPTAB);  
     			var subhtml2 = render(result.data);  
 				$('#popupDiv').html(subhtml2);
 			}
	    	},
	    	function(data){
	    	});
 };
 
 function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
 	var param ;
  	if (isFirQry){
  		param = $('#popupForm').serialize();
  		if (param){
  			param = param+"&mdmType=MATRIAL_INFO";
  		}else{
  			param = "mdmType=MATRIAL_INFO";
  		}
  	}else{
  		//if (!lastPara) {alert('未设置分页参数!');return;}
  		param = lastPara;
  	}
  	Mes.loadJson2("/plan/dubbo/getQueryPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
  		function(result){
  			result.data = JSON.parse(result.data);
  			result.data.qryParam = param;
  			//result.data.dropdownDatas =  dropdownDatas;
  			//result.data.cols ="";
  			//result.data.onPage ="";
  			result.data.cols = [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
  			result.data.onPage = 'onClickUser';
          	//$('#detailTab').html(html+subhtml);
  			if (!isOpened){
  				var render = template.compile(PAGETEMPLATEPOPUP);  
      			var subhtml = render(result.data);  
      			var render = template.compile(PAGETEMPLATEPOPUPTAB);  
      			var subhtml2 = render(result.data);  
	     			layer.open({
	     				 type: 1,
	     				 title :'客户查询',
	     				 area: ['50em', '32em'],
	     				 content: subhtml+"<div id='popupDiv' style='padding:10px 5px'>"+subhtml2+'</div>', //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
	     				 btn: ['确认', '返回'],
	     				 btn1: function(index, layero){
	     					 //$('#arrangeForm').submit();
	     					debugger
	     					 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
	     					 var customCode = $(rowHtml).find("[name=code]").text()||'';
	     					
	     					 $('#customerId').val(customCode);
	     					
	     					 layer.close(index);
	     					}
	     				  ,btn2: function(index, layero){
	     					 // alert(2);
	     				  }
	     				 });
  			}else{
  				var render = template.compile(PAGETEMPLATEPOPUPTAB);  
      			var subhtml2 = render(result.data);  
  				$('#popupDiv').html(subhtml2);
  			}
	    	},
	    	function(data){
	    	});
  };*/

function onClickUser(isFirQry,pageNo,pageSize,lastPara,isOpened){
	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'},{colCode:'simpleName',colName:'万骏化纤'}];
	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','CUSTOM_INFO',function(index, layero){
		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
		 var customCode = $(rowHtml).find("[name=code]").text()||'';
		 var customName = $(rowHtml).find("[name=name]").text()||'';
		 $('#customerId').val(customCode);
		 $('#customerName').val(customName);
		 layer.close(index);
	  });
 };
 
 function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
  	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
  	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickGoods','MATRIAL_INFO',function(index, layero){
  		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
  		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
  		 var rowName = $(rowHtml).find("[name=name]").text()||'';
  		 $('#goodsId').val(rowCode);
  		 $('#goodsName').val(rowName);
  		 layer.close(index);
		  });
   };