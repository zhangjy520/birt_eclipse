var dropdownDatas = {};
var erpId;
var id ;
var mesPlanMonth;
var workcenterList;
var shiftList;
var shiftStyle;
var subJobProcesssDdw;
function customReady(){
	var urlPara = "types=dispatch_order_status";
	debugger;
	$.when( Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00")
	).done(function(data1,data2,data3){
		dropdownDatas = data1;
		var workshopList  = data2;
		dropdownDatas.workshop = changeCode2Name(workshopList);
		id = Mes.getUrlParam("id");
		erpId = Mes.getUrlParam("erpId")|| '';
		var remarks = Mes.getUrlParam("remarks");
		var param
		if (!!id){
			param = "id="+id;
			Mes.getJson2("/plan/mesPlanDispatch/get/index.do",param,		
	    		function(result){
					result.data.dispatch_order_status = dropdownDatas.dispatch_order_status;
					result.data.workshop = dropdownDatas.workshop;
					result.data.erpId=erpId;
					mesPlanMonth = result.data.mesPlanMonth;
	            	getDdw(function(){
	            		var html = template('detailTab.tmp', result.data);
		            	$('#detailTab').html(html);
		            	var html3 = template('dispatchTab.tmp', result.data);
		            	$('#dispatchTab').html(html3);
		            	var html4 = template('dispatchTab2.tmp', result.data);
		            	$('#dispatchTab2').html(html4);
		            	$('#newRowA').attr('href','/mes-plan/production/mes_plan_dispatch_new.html?parId='+result.data.id+'&erpId='+erpId +"&sheetId="+result.data.sheetId);
		            	$('#stockQtyBtn').attr('href','/mes-plan/production/mes_plan_erp_material_stock.html?erpBillNo='+mesPlanMonth.erpBillNo+'&goodsId='+mesPlanMonth.goodsId);
		            	set_sheet_status(result.data.mesPlanMonth.workshopId,result.data.sheetStatus);
	            	});
	    			
		    	},
		    	function(data){
		    		//alert(JSON.stringify(data))
		    });
		}else
		{
			debugger
			if (!erpId){
				Mes.sysAlert('erpId不能为空',null);
				return;
			}
			param = "id="+erpId;
			
			Mes.getJson2("/plan/mesPlanMonth/get/index.do",param,		
		    		function(result){
						debugger
						var dateNow = new Date();
		    			var html = template('detailTab.tmp', {'dispatch_order_status':dropdownDatas.dispatch_order_status,'workshop':dropdownDatas.workshop,'mesPlanMonth':result.data,'createUserName':loginUserName,createTime:dateNow.getFullYear()+"-"+(dateNow.getMonth()+1)+"-"+dateNow.getDate(),sheetStatus:'0',dispatch_order_status:dropdownDatas.dispatch_order_status});
		            	$('#detailTab').html(html);
		            	mesPlanMonth = result.data;
		            	getDdw(function(){
		            		var tmpData={dispatchQtySum:0,mesPlanMonth:result.data}
			            	var html3 = template('dispatchTab.tmp', {'mesPlanMonth':result.data});
			            	$('#dispatchTab').html(html3);
			            	$('#newRowA').attr('href','/mes-plan/production/mes_plan_dispatch_new.html?parId='+'&erpId='+erpId +"&sheetId="+(!!result.data.sheetId?result.data.sheetId:''));
			            	$('#stockQtyBtn').attr('href','/mes-plan/production/mes_plan_erp_material_stock.html?erpBillNo='+mesPlanMonth.erpBillNo+'&goodsId='+mesPlanMonth.goodsId);
			            	set_sheet_status(result.data.workshopId,0);
		            	});
		            	
			    	},
			    	function(data){
			    		//alert(JSON.stringify(data))
			    });
		}
	});
};

function set_sheet_status(workshopId,status){
	if (!!status && parseInt(status) >= 10){
		$('#newRowA').hide();
		$('#newRowA2').hide();
		$('#dispatchBtn').hide();
	}else{
		if (workshopId == WORKSHOP_ARRANGE){
			$('#newRowA').hide();
			$('#newRowA2').show();
		}else{
			$('#newRowA').show();
			$('#newRowA2').hide();
		}
		$('#dispatchBtn').show();
	}
}
function plan_dispatch_check(){
	 var  id = $('#id').val();
     var sheetStatus =  $('#sheetStatus').val();
     if (!id){
    	 Mes.sysAlert('没有id',null);
    	 return;
     }
     if (!sheetStatus  || parseInt(sheetStatus) >= 10){
    	 Mes.sysAlert('单据状态不能为空或已下发',null);
    	 return;
     }
     var validatePass= true;
     debugger
     if ( $('#workshopId').val() != WORKSHOP_ARRANGE ){
    	 //检查是否指定批次，若没有指定，返回
    	 $('#bomTableBody').find('[name=batchNoTd]').each(function(){
    		 if (!$(this).text().trim()){
    			 if (validatePass){
    				 validatePass = false;
    			 }
    		 }
    	 });
     }
     if (!validatePass){
    	 Mes.sysAlert("BOM批次指定不完整!");
    	 return;
     }
     //sheetStatus=10为下发状态
     var urlPara = "id="+id+"&sheetStatus=10";
     Mes.getJson2("/plan/mesPlanDispatch/check/index.do",urlPara,		
 		function(result){
     		Mes.sysAlert("下发成功!",
     				function(){
     			//self.location.href = "/mes-plan/production/mes_plan_dispatch_sheet_list.html";
     			window.location.reload(true);
     		});
    	},
    	function(data){
    		
    	});
}

function plan_dispatch_close( id ){
	//self.location.href = "/mes-plan/production/mes_plan_dispatch_sheet_list.html";
	window.history.go(-1);
}

function on_del_row(rowid){
    if (!rowid){
   	 	Mes.sysAlert('没有rowid',null);
   	 	return;
    }
    layer.confirm("请确认是否删除？",
    {
        btn: ['是', '否'],
        btn1: function () {
        	var urlPara = "id="+rowid;
        	 Mes.getJson2("/plan/mesPlanDispatch/delete/det.do",urlPara,		
        			function(result){
        		 debugger
        		 			window.location.reload(true);
        		   	},
        		   	function(data){
        		   		
        		   	});
        },
        btn2: function () {
        }
    });
    
    
   
}

function on_cancel_row(rowid){
	if (!rowid){
   	 	Mes.sysAlert('没有rowid',null);
   	 	return;
    }
    layer.confirm("请确认是否取消？",
    {
        btn: ['是', '否'],
        btn1: function () {
        	 var urlPara = "id="+rowid+"&dispatchStatus=99";
         
             Mes.getJson2("/plan/mesPlanDispatch/changeStatus/det.do",urlPara,		
         		function(result){
            		 Mes.sysAlert("取消成功!",
              				function(index){
              			//self.location.href = "/mes-plan/production/mes_plan_dispatch_sheet_list.html";
            			 layer.close(index);
              			window.location.reload(true);
              		});
            	},
            	function(data){
            		
            	});
        },
        btn2: function () {
        }
    });
}

function save_head(sender){
	
	var remarks= $(sender).val();
	var rowid =  $("#id").val();
	if (!rowid) {
		$(sender).prev().text($(sender).val());
		$(sender).hide();
		$(sender).prev().show();
		
		var url = $('#newRowA').attr('href');
		var indexno = url.indexOf('remarks');
		if(indexno < 0){
			url = url +"&remarks="+encodeURIComponent(remarks);
			$('#newRowA').attr('href',url);
		}else{
			$('#newRowA').attr('href',url.substr(0,indexno)+"&remarks="+encodeURIComponent(remarks));
		}	
		return;
	}
	Mes.sysLoading('正在保存',500);
	
	
	var urlPara = "id="+rowid+"&remarks="+remarks;
    Mes.getJson2("/plan/mesPlanDispatch/saveremark/index.do",urlPara,		
		function(result){
//    		Mes.sysAlert("保存成功!",
//    				function(index){
//    			layer.close(index);
//    			$(sender).prev().text($(sender).val());
//    			$(sender).hide();$(sender).prev().show();
//    		});
		$(sender).prev().text($(sender).val());
		$(sender).hide();
		$(sender).prev().show();
   	},
   	function(data){
   		
   	});
};

function setSheetStatusRow(sheetStatus){
	debugger
	if (parseInt(sheetStatus||0) >= 10){
		$('#arrangeForm').find(":input").each(function(){
			$(this).attr('readonly','readonly');
			$(this).removeAttr("onclick");
		});
			
		$('#arrangeForm').find("select").each(function(){
			$(this).attr('disabled','disabled');
		});
		//确认按钮隐藏
		//$('#confirmBtn').css('display','none');
	}else{
		$('#planBeginDate').datepicker({
		    format: 'yyyy-mm-dd',
		    language: "zh-CN",//设置语言
		     autoclose: true//设置是否关闭
		}).on('hide',function(e) {  
            $('#arrangeForm').data('bootstrapValidator')  
            .updateStatus('planBeginDate', 'NOT_VALIDATED',null)  
            .validateField('planBeginDate');  
		});  
	}
};
function arrangeDispatch(detId){
	debugger
	var html;
	var result;
	
	if (detId){
		var urlParam = "id="+detId;
		result= Mes.getJsonSync("/plan/mesPlanDispatch/getsheet/det.do",urlParam);
		result.mesPlanMonth = mesPlanMonth;
		result.workcenterList = workcenterList;
		result.shiftList = shiftList;
		result.shiftStyleList = shiftStyle;
		//result.technicList =[{code:'W01',name:'验布'},{code:'W02',name:'分等'},{code:'W03',name:'拼件'},{code:'W04',name:'打包'}];
		result.technicList = subJobProcesssDdw ;
		
	}else{
		var dateNowStr = (new Date()).Format('yyyy-MM-dd');
		if (dateNowStr < mesPlanMonth.planBeginDate){
			dateNowStr = mesPlanMonth.planBeginDate;
		}
		if (dateNowStr > mesPlanMonth.planEndDate){
			dateNowStr = mesPlanMonth.planEndDate;
		}
		var shiftIdValue = '';
		var shiftStyleValue = '';
		var workcenterIdValue = '' ;
		if ($('#tabBody').children("tr").length && $('#tabBody').children("tr").length>0){
			shiftIdValue = $($('#tabBody').children("tr")[0]).find("[name=shiftId]").val()||'';
			shiftStyleValue = $($('#tabBody').children("tr")[0]).find("[name=shiftStyle]").val()||'';
			workcenterIdValue = $($('#tabBody').children("tr")[0]).find("[name=workcenterId]").val()||'';
		}else{
			$('#shiftStyle').attr('disabled','');
		}
		result={'shiftId':shiftIdValue,'shiftStyle':shiftStyleValue,'workcenterId':workcenterIdValue,planBeginDate:dateNowStr,planEndDate:dateNowStr,'mesPlanMonth':mesPlanMonth,shiftBeginTime:'00:00',shiftEndTime:'23:59'};
		result.workcenterList = workcenterList;
		result.shiftList = shiftList;
		result.shiftStyleList = shiftStyle;
		//result.technicList =[{code:'W01',name:'验布'},{code:'W02',name:'分等'},{code:'W03',name:'拼件'},{code:'W04',name:'打包'}];
		result.technicList = subJobProcesssDdw ;
	}
	
	html = template('test.tmp',result);
	layer.open({
		 type: 1,
		 title :'整理车间分解',
		 area: ['54em', '36em'],
		 content: html, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		 btn: ($('#sheetStatus').val()||0)>=10?[]:['确认', '取消'],
		 btn1: function(index, layero){
			 $('#arrangeForm').submit();
			   //layer.close(index);
			  }
		  ,btn2: function(index, layero){
			 // alert(2);
		  }
		 });
	setSheetStatusRow($('#sheetStatus').val());
	
	 $('#arrangeForm').bootstrapValidator({
		  message: 'This value is not valid',
		  feedbackIcons: {
			  //valid: 'glyphicon glyphicon-ok',
			 // invalid: 'glyphicon glyphicon-remove',
			  validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  workcenterId: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
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
	                         message: '请输入订单范围日期'+mesPlanMonth.planBeginDate+"至"+mesPlanMonth.planEndDate,  
	                         callback: function(value, validator) {  
	                        	 debugger
	                        	 if (mesPlanMonth && (mesPlanMonth.planBeginDate > value || mesPlanMonth.planEndDate < value)){
	                        		 return false;
	                        	 }
	                        	 return true;
	                         }
	                     }
				   }
			  },
			  arrangeGroup: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  shiftId: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  shiftStyle: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  dispatchQtyPackage: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						},stringLength: {
						    min: 1,
						    max: 6,
						    message: '长度必须在1到6位之间'
					   },
					   regexp: {
						    regexp: /^[0-9_]+$/,
						    message: '匹数只能是整理'
					   }
				   }
			  },
			  shiftBeginTime: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  shiftEndTime: {
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
	         // Prevent form submission
	         e.preventDefault();
	         debugger
	         layer.confirm("确定要添加该条新订单信息？", function (index) {
	   	    	urlPara =  $('#arrangeForm').serialize();
	   	    	//alert(urlPara);
	   	    	var remarks = $('#remarksSpan').text() || $('#remarks').val() || '';
	   	    	Mes.loadJson2("/plan/mesPlanDispatch/save/det.do",urlPara,"{erpId:'"+erpId+"',sheetId:'"+$('#sheetId').val()+"',remarks:'"+remarks+"'}",		
	   	         		function(result){
	   		        	 	//Mes.sysAlert(result.msg, null);
	   		        	 	if (!!id){
	   		    	     		Mes.sysAlert("保存成功!",
	   		    	     				function(index){
	   		    	     			layer.close(index);
	   		    	     			self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+id+"&erpId="+erpId;
	   		    	     		});
	   		    	     	}else{
	   		    	     		Mes.sysAlert("保存成功!",
	   		    	     				function(index){
	   		    	     			layer.close(index);
	   		    	     			self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+result.data+"&erpId="+erpId;
	   		    	     		});
	   		    	     	}
	   	     	    	},
	   	     	    	function(data){
	   	     	    		alert(JSON.stringify(data))
	   	     	    	});
	   	    	
	   	    	layer.close(index);
	          }) ;
		 });
};

function onFinishValidate(finishStatus){
	debugger
	var sheetStatus= $('#sheetStatus').val()||0;
	 if (parseInt(sheetStatus)==0){
		 layer.msg('单据未下发,不需要完工!',{time: 1500});
		 return false;
	 }
	//var id = tablerow.find("[name='id']").val();
	var remarks = $('#remarksTextarea').val();
	if (finishStatus != '100' && !remarks){
		layer.msg('请输入异常完工说明!',{time: 1500});
		return false;
	}
	return true;
}
function onSelectedRows(finishStatus){
	var selectedData = [];
	var validatePass = true;
	var remarks = $('#remarksTextarea').val() || '';
	$("[name=checkflag]:checkbox:checked","#tabBody").each(function(){
	  var tablerow = $(this).parent().parent()
	  var id = tablerow.find("[name='id']").val();
	  var billNo = tablerow.find("[name='billNo']").val();
	  var dispatchStatus = tablerow.find("[name='dispatchStatus']").val()||0;
	  if (parseInt(dispatchStatus)>0 && parseInt(dispatchStatus) <98){
		  //检查已下发单据是否完工
			var urlPara = "level=2&billNo="+billNo;
			if (Mes.getJsonSyncAllData("/plan/mesPlanShift/validateFinish/index.do",urlPara) ==0 ){
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
		 area: ['28em', '15em'],
		 content: html2,
		 btn: ['完工', '异常完工','取消'],
		 btn1: function(index, layero){
			 var finishStatus = '100';
			 if (!onFinishValidate(finishStatus)){
				 return false;
			 }
			 var selectedData = onSelectedRows(finishStatus);
			 if (selectedData.length > 0){
				 layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认完工吗', {title:'提示'}, function(index2){
					 on_finish_inner(selectedData,index);
					 layer.close(index2);
				 });
			 }else{
				 layer.msg('没有勾选有效数据或勾选数据无效,请检查计划(含下级计划)状态!',{time: 3000});
			 }
		 },
		 btn2: function(index, layero){
			 var finishStatus = '98';
			 if (!onFinishValidate(finishStatus)){
				 return false;
			 }
			 var selectedData = onSelectedRows(finishStatus);
			 if (selectedData.length > 0){
				 layer.confirm('您选择了['+selectedData.length+']行数有效数据,确认异常完工吗', {title:'提示'}, function(index2){
					 on_finish_inner(selectedData,index);
					 layer.close(index2);
				 });
			 }else{
				 layer.msg('没有勾选有效数据,请检查计划(含下级计划)状态!',{time: 2000});
				 return false;
			 }
			 
		 },
		 btn3: function(index, layero){
			 // alert(2);
			 layer.close(index);
		 }
	});
};

function on_finish_inner(selectedData,index){
	//var urlPara = get_unschedule_parm();
	debugger;
	if (!$('#sheetId').val()){
		Mes.sysAlert('请先保存单据');
		return;
	}
	var urlPara ="sheetId="+$('#sheetId').val();
	if (selectedData.length>0){
		Mes.loadJson2("/plan/mesPlanDispatch/batchfinish/det.do",urlPara,JSON.stringify(selectedData),		
     		function(result){
				result.data.dispatch_order_status = dropdownDatas.dispatch_order_status;
				getDdw(function(){
							result.data.mesPlanMonth =mesPlanMonth;
				        	var html3 = template('dispatchTab.tmp', result.data);
				        	$('#dispatchTab').html(html3);
				        	layer.close(index);
						}
				);
 	    	},
 	    	function(data){
 	    		Mes.sysAlert(JSON.stringify(data))
 	    });
	}else{
		Mes.sysAlert("请选择数据!")
	}
};

function orderDispatch(addr){
	layer.open({
		 type: 2,
		 title :'订单下发',
		 area: ['50em', '32em'],
		 content: (!addr)?$('#newRowA').attr('href'):addr,
		 btn: ($('#sheetStatus').val()||0)>=10?[]:['确认', '取消'],
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

 

function getDdw(callback){
	if (!mesPlanMonth) {
		alert('mesPlanMonth不能为空!');
		return;
	}
	$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+mesPlanMonth.workshopId),
			mesPlanMonth.workshopId==WORKSHOP_ARRANGE?Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT"):[],
			mesPlanMonth.workshopId==WORKSHOP_ARRANGE?Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+mesPlanMonth.workshopId):[],
			mesPlanMonth.workshopId==WORKSHOP_ARRANGE?Mes.getJsonDeferred("/plan/mesPlanMonth/getSubMesPlanTechnicList/index.do","id="+mesPlanMonth.id):[]
	).done(function(data1,data2,data3,data4){
		workcenterList = data1;
		mesPlanMonth.workcenterList = workcenterList;
		if (mesPlanMonth.workshopId==WORKSHOP_ARRANGE){
			//$('#bomDiv').css('display','none');
			$('#stockQtyBtn').css('display','none');
	    	shiftList = data2;
	    	shiftStyle = data3;
	    	mesPlanMonth.shiftList = shiftList;
	    	mesPlanMonth.shiftStyle = shiftStyle;
	    	var subJobProcesssList = data4;
	    	subJobProcesssDdw = [];
	    	if (subJobProcesssList){
		    	for (var i=0 ;i<subJobProcesssList.length; i++){
		    		subJobProcesssDdw.push({code:subJobProcesssList[i].processCode,name:subJobProcesssList[i].processName});
		    	}
	    	}
	    	mesPlanMonth.subJobProcesssDdw = subJobProcesssDdw;
		}
		callback();
	});
	
	
};

function onBom(sender){
	var detId = $(sender).parent().parent().find('[name=id]').val();
	var sheetId = $(sender).parent().parent().find('[name=sheetId]').val();
	var rawGoodsId = $(sender).parent().parent().find('[name=rawGoodsId]').val();
	var rawGoodsName = $(sender).parent().parent().find('[name=rawGoodsName]').val();
	var suplierId = $(sender).parent().parent().find('[name=suplierId]').val();
	var batchNo = $(sender).parent().parent().find('[name=batchNo]').val();
	var batchProp1 = $(sender).parent().parent().find('[name=batchProp1]').val();
	var batchProp2 = $(sender).parent().parent().find('[name=batchProp2]').val();
	var erpBillNo = $('#erpBillNo').val();
	
	var rowBom ={'id':detId,'sheetId':sheetId,'erpBillNo':erpBillNo,'rawGoodsId':rawGoodsId,'rawGoodsName':rawGoodsName,'suplierId':suplierId,'batchNo':batchNo,'batchProp1':batchProp1,'batchProp2':batchProp2};
	var html=template('bom.tmp',rowBom);
	layer.open({
		 type: 1,
		 title :'BOM指定',
		 area: ['45em', '18em'],
		 content: html, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		 btn: ($('#sheetStatus').val()||0)>=10?[]:['确认', '取消'],
		 btn1: function(index, layero){
			 $('#bomForm').submit();
			   //layer.close(index);
			  }
		  ,btn2: function(index, layero){
			 // alert(2);
		  }
		 });
	setSheetStatusRowBom($('#sheetStatus').val());
	$('#bomForm').bootstrapValidator({
		  message: 'This value is not valid',
		  feedbackIcons: {
			  validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  batchNo: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  }/*,
			  batchProp1: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  batchProp2: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  }*/
		  },
		  submitHandler: function (validator, form, submitButton) {
			  alert("submit");
		  	}
		 }).on('success.form.bv', function(e) {//点击提交之后
	         // Prevent form submission
	         e.preventDefault();
	         debugger
        	var batchNoNew = $('#bomForm').find("[name=batchNo]").val();
        	var batchProp1New = $('#bomForm').find("[name=batchProp1]").val();
        	var batchProp2New = $('#bomForm').find("[name=batchProp2]").val();
   	    	urlPara = "sheetId="+sheetId;
   	    	rowBom.batchNo = batchNoNew;
   	    	rowBom.batchProp1 = batchProp1New;
   	    	rowBom.batchProp2 = batchProp2New;
   	    	var rowsData=[];
   	    	$("#bomTableBody").children("tr").each(function(){
   	    		debugger;
				 var detIdTmp = $(this).find("[name='id']").val();
				 if (!detIdTmp)
				 {
					var sheetIdTmp = $(this).find('[name=sheetId]').val();
					var rawGoodsIdTmp = $(this).find('[name=rawGoodsId]').val();
					var rawGoodsNameTmp = $(this).find('[name=rawGoodsName]').val();
					var suplierIdTmp = $(this).find('[name=suplierId]').val();
					var batchNoTmp = $(this).find('[name=batchNo]').val();
					var batchProp1Tmp = $(this).find('[name=batchProp1]').val();
					var batchProp2Tmp = $(this).find('[name=batchProp2]').val();
					//非本行数据添加
					if (rawGoodsIdTmp != rowBom.rawGoodsId){
						rowsData.push({'sheetId':sheetIdTmp,'erpBillNo':erpBillNo,'rawGoodsId':rawGoodsIdTmp,'suplierId':suplierIdTmp,'batchNo':batchNoTmp,'batchProp1':batchProp1Tmp,'batchProp2':batchProp2Tmp});
					}
				 }
	   		});
   	    	rowsData.push(rowBom);
   	    	Mes.loadJson2("/plan/mesPlanDispatch/saveBatch/det2.do",urlPara,		JSON.stringify(rowsData),
   	         		function(result){
   		        	 	//Mes.sysAlert(result.msg, null);
   	    				if (result.succ){
   	    					Mes.sysAlert("保存成功!",
   		    	     				function(index){
   	    						layer.close(index);
   		    	     			//self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+id+"&erpId="+erpId;
   	    						param = "id="+id;
   	    						Mes.getJson2("/plan/mesPlanDispatch/get/index.do",param,		
   	    				    		function(result){
   	    		    	     			var html4 = template('dispatchTab2.tmp', result.data);
   	    		    	     			$('#dispatchTab2').html(html4);
   	    						});
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
	 
}
function setSheetStatusRowBom(sheetStatus){
	if (parseInt(sheetStatus||0) >= 10){
		$('#bomForm').find(":input").each(function(){
			$(this).attr('readonly','readonly');
			$(this).removeAttr("onclick");
		});
			
		$('#bomForm').find("select").each(function(){
			$(this).attr('disabled','disabled');
		});
		//确认按钮隐藏
		//$('#confirmBtn').css('display','none');
	}
};
function arrangeDispatchBatch(detId){
	var html;
	var result;
	
	if (detId){
		var urlParam = "id="+detId;
		result= Mes.getJsonSync("/plan/mesPlanDispatch/getsheet/det.do",urlParam);
		result.mesPlanMonth = mesPlanMonth;
		result.workcenterList = workcenterList;
		result.shiftList = shiftList;
		result.shiftStyleList = shiftStyle;
		//result.technicList =[{code:'W01',name:'验布'},{code:'W02',name:'分等'},{code:'W03',name:'拼件'},{code:'W04',name:'打包'}];
		result.technicList = subJobProcesssDdw ;
		
	}else{
		var dateNowStr = (new Date()).Format('yyyy-MM-dd');
		if (dateNowStr < mesPlanMonth.planBeginDate){
			dateNowStr = mesPlanMonth.planBeginDate;
		}
		if (dateNowStr > mesPlanMonth.planEndDate){
			dateNowStr = mesPlanMonth.planEndDate;
		}
		var shiftIdValue = '';
		var shiftStyleValue = '';
		var workcenterIdValue = '' ;
		if ($('#tabBody').children("tr").length && $('#tabBody').children("tr").length>0){
			shiftIdValue = $($('#tabBody').children("tr")[0]).find("[name=shiftId]").val()||'';
			shiftStyleValue = $($('#tabBody').children("tr")[0]).find("[name=shiftStyle]").val()||'';
			workcenterIdValue = $($('#tabBody').children("tr")[0]).find("[name=workcenterId]").val()||'';
		}else{
			$('#shiftStyle').attr('disabled','');
		}
		result={'shiftId':shiftIdValue,'shiftStyle':shiftStyleValue,'workcenterId':workcenterIdValue,planBeginDate:dateNowStr,planEndDate:dateNowStr,'mesPlanMonth':mesPlanMonth,shiftBeginTime:'00:00',shiftEndTime:'23:59'};
		result.workcenterList = workcenterList;
		result.shiftList = shiftList;
		result.shiftStyleList = shiftStyle;
		//result.technicList =[{code:'W01',name:'验布'},{code:'W02',name:'分等'},{code:'W03',name:'拼件'},{code:'W04',name:'打包'}];
		result.technicList = subJobProcesssDdw ;
	}
	
	html = template('arrangeBatch.tmp',result);
	layer.open({
		 type: 1,
		 title :'整理车间批量分解',
		 area: ['54em', '36em'],
		 content: html, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		 btn: ($('#sheetStatus').val()||0)>=10?[]:['确认', '取消'],
		 btn1: function(index, layero){
			 $('#arrangeBatchForm').submit();
			   //layer.close(index);
			  }
		  ,btn2: function(index, layero){
			 // alert(2);
		  }
		 });
	setSheetStatusRow($('#sheetStatus').val());
	
	 $('#arrangeBatchForm').bootstrapValidator({
		  message: 'This value is not valid',
		  feedbackIcons: {
			  //valid: 'glyphicon glyphicon-ok',
			 // invalid: 'glyphicon glyphicon-remove',
			  validating: 'glyphicon glyphicon-refresh'
		  },
		  fields: {
			  workcenterId: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
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
	                         message: '日期需要在订单范围内',  
	                         callback: function(value, validator) {  
	                        	 debugger
	                        	 if (mesPlanMonth && (mesPlanMonth.planBeginDate > value || mesPlanMonth.planEndDate < value)){
	                        		 return false;
	                        	 }
	                        	 return true;
	                         }
	                     }
				   }
			  },
			  shiftId: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  shiftStyle: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  shiftBeginTime: {
				   message: '不能为空',
				   validators: {
					   notEmpty: {
						   message: '不能为空'
						}
				   }
			  },
			  shiftEndTime: {
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
	         // Prevent form submission
	         e.preventDefault();
	         debugger
	         layer.confirm("确定要添加该条新订单信息？", function (index) {
	   	    	urlPara =  $('#arrangeForm').serialize();
	   	    	//alert(urlPara);
	   	    	var remarks = $('#remarksSpan').text() || $('#remarks').val() || '';
	   	    	Mes.loadJson2("/plan/mesPlanDispatch/save/det.do",urlPara,"{erpId:'"+erpId+"',sheetId:'"+$('#sheetId').val()+"',remarks:'"+remarks+"'}",		
	   	         		function(result){
	   		        	 	//Mes.sysAlert(result.msg, null);
	   		        	 	if (!!id){
	   		    	     		Mes.sysAlert("保存成功!",
	   		    	     				function(index){
	   		    	     			layer.close(index);
	   		    	     			self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+id+"&erpId="+erpId;
	   		    	     		});
	   		    	     	}else{
	   		    	     		Mes.sysAlert("保存成功!",
	   		    	     				function(index){
	   		    	     			layer.close(index);
	   		    	     			self.location.href = "/mes-plan/production/mes_plan_dispatch.html?id="+result.data+"&erpId="+erpId;
	   		    	     		});
	   		    	     	}
	   	     	    	},
	   	     	    	function(data){
	   	     	    		alert(JSON.stringify(data))
	   	     	    	});
	   	    	
	   	    	layer.close(index);
	          }) ;
		 });
};
function chgUnit(sender){
	var senderId=$(sender).attr('id');
	var newValue = $(sender).val()||0;
	var rate = $('#arrangeUnitRate').val();
	 $('#'+senderId+'span').text((newValue*rate).toFixed(3));
};
function chgShiftId(sender){
//	var newValue = $(sender).val();
//	if (newValue && shiftList){
//		var rtn = '' ;
//		$.each(shiftList,function(n,loopValue) {   
//            if (loopValue.code == newValue)   {
//            	debugger
//            	$('#shiftBeginTime').val(loopValue.startTime);
//            	$('#shiftEndTime').val(loopValue.endTime);
//            	if (loopValue.startTime>loopValue.endTime){
//            		
//            	}
//            	return false;
//            }
//           });  
//	}
}