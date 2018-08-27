 var dropdownDatas = {
		"taskStatusList":[{id:'0',text:'登记'},{id:'1',text:'下发'},{id:'10',text:'接收'},{id:'98',text:'取消'},{id:'99',text:'异常'},{id:'100',text:'完成'}],
		"technicList":[{id:'50',text:'穿筘'},{id:'60',text:'结经'}] 
};

 function customReady() {
	 var sheetId = Mes.getUrlParam("sheetId");
	 var urlPara = "types=plan_order_type,plan_order_status";
	  $.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
				//Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00") ,
				 Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT")
		).done(function(data1,data3){
			dropdownDatas = data1 ;
			dropdownDatas.workshopList = [{id: "C11", text: "准备车间"},{id: "C12", text: "一织车间"},{id: "C13", text: "二织车间"}];
			dropdownDatas.shiftList = data3;
			ddwAddOption($("#workshopId"),dropdownDatas.workshopList);
			searchOrder2(null,sheetId);	
			
			$('#dispatchForm').bootstrapValidator({
				  message: 'This value is not valid',
				  feedbackIcons: {
				  //valid: 'glyphicon glyphicon-ok',
				  //invalid: 'glyphicon glyphicon-remove',
				  validating: 'glyphicon glyphicon-refresh'
				  },
				  fields: {
					  erpBillNo: {
						   message: '请输入数字',
						   validators: {
						   notEmpty: {
							   message: '不能为空'
							}
				  			,
						   remote: {
		                        message: '单号不存在',
		                        delay:100,
		                        url: '/mes-plan/plan/mesPlanMonth/validateBillNoFalse/index.do'
		                    }
						}
					  },
					  workshopId: {
							   message: '请输入数字',
							   validators: {
							   notEmpty: {
								   message: '不能为空'
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
					  ptechnicId: {
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
			         	
			        var selectedData ;
			     	var erpBillNo =  $('#erpBillNo').val();
			     	var ptechnicId =  $('#ptechnicId').val();
			     	var ptechnicName = $('#ptechnicId').find("option:selected").text();
			     	var workshopId = $('#workshopId').val();
			     	var workcenterId = $('#workcenterId').val();
			     	var planBeginDate =  $('#planBeginDate').val();
			     	//var planEndDate = $('#planEndDate').val();
			     	var id = $('#id').val();
			     	var sheetId = $('#sheetId').val();
			     	debugger
			     	var thList = $("#tabHead").find(":selected");
			     	var shiftId1 = $(thList[0]).val();
			     	var shiftStyle1 = '';//$(thList[1]).val();
			     	var shiftId2 = $(thList[2]).val();
			     	var shiftStyle2 = '';//$(thList[3]).val();
			     	var shiftId3 = $(thList[4]).val();
			     	var shiftStyle3 = ''; //$(thList[5]).val();
			     	if (shiftId1 == shiftId2 || shiftId1 == shiftId3 || shiftId2 == shiftId3){
			     		Mes.sysAlert('班次1数量,班次2数量,班次3数量的录入值不能重复! ');
			     		return;
			     	}
			     	
			     	selectedData = {"planSheetId":erpBillNo,"ptechnicId":ptechnicId,"workshopId":workshopId,"workcenterId":workcenterId,
			 	 	    		"relPlan":'1',"ptechnicName":ptechnicName,"id":id,"sheetId":sheetId};
			     	selectedData.mesPlanTempTaskDetList = [];
			  		var trList = $("#tabBody").children("tr");
			  	    for (var i=0;i<trList.length;i++) {
			  	    	var rowElement = $(trList[i]);
			  	    	var deviceId = rowElement.find("[name='deviceId']").text().trim();
			  	    	var deviceName = rowElement.find("[name='deviceName']").text().trim();
			  	    	var qty1 = rowElement.find("[name='"+deviceId+"_1']").val().trim();
			  	    	var qty2 = rowElement.find("[name='"+deviceId+"_2']").val().trim();
			  	    	var qty3 = rowElement.find("[name='"+deviceId+"_3']").val().trim();
			  	    	var detId1= $(rowElement.find("[name='"+deviceId+"_1']")).next().val();
			  	    	var detId2= $(rowElement.find("[name='"+deviceId+"_2']")).next().val();
			  	    	var detId3= $(rowElement.find("[name='"+deviceId+"_3']")).next().val();
			  	 	    if(qty1){ 		
			  	 	    	selectedData.mesPlanTempTaskDetList.push({"shiftId":shiftId1,"shiftStyle":shiftStyle1,"qty":qty1,"deviceId":deviceId,"deviceName":deviceName,orderNo:1,"id":detId1});
			  	 	    }
			  	 	    if(qty2){
			  	 	    	selectedData.mesPlanTempTaskDetList.push({"shiftId":shiftId2,"shiftStyle":shiftStyle2,"qty":qty2,"deviceId":deviceId,"deviceName":deviceName,orderNo:2,"id":detId2});
			  	 	    }
			  	 	    if(qty3){
			  	 	    	selectedData.mesPlanTempTaskDetList.push({"shiftId":shiftId3,"shiftStyle":shiftStyle3,"qty":qty3,"deviceId":deviceId,"deviceName":deviceName,orderNo:3,"id":detId3});
			  		   }
			  	    }
			  		//Mes.sysAlert(JSON.stringify(selectedData,null));
			  		//return;
			  		if (selectedData.mesPlanTempTaskDetList.length>0){
			  			var urlPara = 'planBeginDate='+planBeginDate+"&planEndDate="+planBeginDate;
			  			Mes.loadJson2("/plan/mesPlanTempTask/batchsave/det.do",urlPara,JSON.stringify(selectedData),		
			               		function(result){
			      	        	 	//Mes.sysAlert(result.msg, null);
			          	     		Mes.sysAlert("生成任务成功!",
			          	     				//function(){self.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;
			          	     				function(){
			          	     					layer.closeAll();
			          	     					//var sheetId = Mes.getUrlParam("sheetId")||result.data.sheetId;
			          	     					//searchOrder2($('#workcenterId').val(),sheetId);
			          	     					self.location.href='/mes-plan/production/mes_plan_scene_task_list.html';
			          	     				});
			           	    	},
			           	    	function(data){
			           	    		Mes.sysAlert(JSON.stringify(data))
			           	    });
			  		}else{
			  			Mes.sysAlert("没有找到有效数据!");
			  		}
			         
				 });
		}) ;
	  
	 
//	 $.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
//			
//		).done(function(data1,data2){
//			dropdownDatas.workshopList = data1;
//			dropdownDatas.shiftList = data2;
//			ddwAddOptionCodeName($("#workshopId"),dropdownDatas.workshopList);
//			searchOrder2(null,sheetId);	
//		});
    };
    function searchOrder2(workcenterId,sheetId){
    	if (sheetId){
	   		 Mes.getJson2("/plan/mesPlanTempTask/getSheet/index.do","sheetId="+sheetId,function(result){
	   			$('#id').val(result.data.id);
	   	    	$('#sheetId').val(result.data.sheetId);
	   			$('#erpBillNo').val(result.data.planSheetId);
	   			onChangeErpBill('#erpBillNo');
	   			$('#ptechnicId').val(result.data.ptechnicId);
	   			$('#workshopId').val(result.data.workshopId);

	   			ddwClearOption($('#workcenterId'),true);
		   		 
	   			$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+result.data.workshopId),
   					  Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+result.data.workshopId)
   				).done(function(data1,data2){
   					dropdownDatas.shiftStyleList = data2; 
   					ddwAddOptionCodeName($("#workcenterId"),data1);
   					$('#workcenterId').val(result.data.workcenterId);
		   			$('#planBeginDate').val(result.data.planBeginDate);
		   			//$('#planEndDate').val(result.data.planEndDate);
		   			
		   			var data = result.data.mesPlanTempTaskDetList;
		   			if (data && data.length){
			   			data.dropdownDatas = dropdownDatas;
			    		var html = template('detailTab1.tmp', data);
			        	$('#detailTab1').html(html);
			        	
			        	var thList = $("#tabHead").find(":input");
			        	var element0 = $(thList[0]);
			        	var element1 = $(thList[1]);
			        	var element2 = $(thList[2]);
			        	var element3 = $(thList[3]);
			        	var element4 = $(thList[4]);
			        	var element5 = $(thList[5]); 
			        	$.each(data,function(index,item){
			   				 	var qtyEle = $('#tabBody').find("[name='"+item.deviceId+"_"+item.orderNo+"']");
			   				 	qtyEle.val(item.qty);
			   				 	qtyEle.next().val(item.id);
				    			$('#tabBody').find("[name='id"+item.id+"']").val(item.id);
				    			if (item.orderNo == 1){
				    				if (!element0.val()){element0.val(item.shiftId);}
				    				//if (!element1.val()){element1.val(item.shiftStyle);}
				    			} 
				    			if (item.orderNo == 2){
				    				if (!element2.val()){element2.val(item.shiftId);}
				    				//if (!element3.val()){element3.val(item.shiftStyle);}
				    			} 
				    			if (item.orderNo == 3){
				    				if (!element4.val()){element4.val(item.shiftId);}
				    				//if (!element5.val()){element5.val(item.shiftStyle);}
				    			} 
				    			
			   			 });
		   			}
		   			if (parseInt(result.data.taskStatus||0)>=1){
		   				$('#confirmBtn').hide();
		   				$('body').find(':input').attr('disabled','disabled');
		   			}
		   			
   				});
	   			
	   		 });
    	}
    	else if (workcenterId ){
		    	Mes.getJson2("/plan/dubbo/getMdmList/index.do","mdmType=EQUIPMENT_INFO&workcenterId="+workcenterId,function(result){
		    		var data2 = JSON.parse(result.data);
		    		var data = [] ;
		    		$.each(data2,function(index,item){
		    			data.push({deviceId:item.code,deviceName:item.name});
		    		});
		    		
		    		data.dropdownDatas = dropdownDatas;
		    		var html = template('detailTab1.tmp', data);
		        	$('#detailTab1').html(html);
		    	});
    	}else{
    		var data = [];
    		data.dropdownDatas = dropdownDatas;
    		var html = template('detailTab1.tmp', data);
        	$('#detailTab1').html(html);
    	}
    	debugger
    	
    	 
//		$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=EQUIPMENT_INFO&workcenter='"+'W07'+"'")
//		).done(function(data1){
//			alert(data1);
//        	var html = template('detailTab1.tmp', result.data);
//        	$('#detailTab1').html(html);
//        	if (isLoadDevice){
//        		var html = template('detailTab.tmp', result.data);
//        		$('#detailTab').html(html);
//        		Mes.sysLoading("正在加载设备信息",500);
//        	}
//        	if (!head.deviceChangeQty){
//        		$('#folderA').click();
//        	}
//		});
	    	 
    }
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
     }
     var radio_click = function(sender){
//    	 alert($(sender).prop('checked')+","+$(sender).attr('checked'));
//    	 if ($(sender).prop('checked')){
//    	 $(sender).attr('checked',false);
//    	 }
     }
     var fun_chg_goods_confirm = function(){
    	 $('#dispatchForm').submit();
     };
     
     var changeFolderFun = function(){
    	 debugger
		if ($('#detailTab1').css('display')=='none')
		{	$('#folderBtn').removeClass('glyphicon-chevron-down');
			$('#folderBtn').addClass('glyphicon-chevron-up');
			$('#filterSpan1').hide();
		}
		else{
			$('#folderBtn').removeClass('glyphicon-chevron-up');
			$('#folderBtn').addClass('glyphicon-chevron-down');
			$('#filterSpan1').show();
		}
	};
 function onFilter(sender,elementId){
  	var filterValue = $(sender).val().trim();
  	if (filterValue){
  		$(elementId).children().each(function(){
  			var found = false;
  			var cols = $(this).children();
  			var colCnt = cols.length;
  			for (var i = 0 ;i < colCnt; i++)
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
  
  function onFilterTrigger(inputValue,elementId){
 	 $(elementId).val(inputValue);
 	 $(elementId).trigger('change');
  };
      
  function chgWorkshopId(sender){
	  ddwClearOption($('#workcenterId'),true);
	  //[{id: "C11", text: "准备车间"},{id: "C12", text: "一织车间"},{id: "C13", text: "二织车间"}];
	  if ($(sender).val()){
		  $.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+$(sender).val()+"&codeList='W05','W06','W07'"),
				  Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+$(sender).val())
			).done(function(data1,data2){
				dropdownDatas.shiftStyleList = data2; 
				ddwAddOptionCodeName($("#workcenterId"),data1);
			});
		  if ($(sender).val() =='C11'){
			  $('#ptechnicId').val('50');
		  }else if ($(sender).val() =='C12' || $(sender).val() =='C13'){
			  $('#ptechnicId').val('60');
		  }
		  $('#dispatchForm').data('bootstrapValidator').updateStatus('ptechnicId', 'NOT_VALIDATED',null).validateField('ptechnicId')
	  }
  };
  
  function searchOrder(isFirQry,pageNo,pageSize,lastPara,isopened){
	var param ;
	if (isFirQry){
		 param = $('#from_condition').serialize();
	}else{
		//if (!lastPara) {alert('未设置分页参数!');return;}
		param = lastPara;
	}
	param = rapRangeUrl(param);
	
	Mes.loadJson2("/plan/mesPlanMonth/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
		function(result){
    		$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=CUSTOM_INFO&codeList="+getCommaStr(result.data.list,"customerId"))
    		).done(function(data1){
    			result.data.qryParam = param;
    			result.data.dropdownDatas =  dropdownDatas;
    			//result.data.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(result.data.list,"goodsId"));
    			result.data.customerList = data1;
    			var headhtml = template('detailTab2.tmp', dropdownDatas);
    			var html = template('detailTab.tmp', result.data);
    			var render = template.compile(PAGETEMPLATE);  
    			//var subhtml = render(result.data);  
    			if (!isopened){
	    			layer.open({
         				 type: 1,
         				 title :'查询',
         				 area: ['80%', '80%'],
         				 content:(headhtml+html),
         				 btn: ['确认', '返回'],
         				 btn1: function(index, layero){
            					var rowHtml = $('#popupDiv2').find("input:checked").parent().parent();
            		      		var rowCode = $(rowHtml).find("[name=erpBillNo]").val()||'';
            		      		$('#erpBillNo').val(rowCode);
            		      		onChangeErpBill('#erpBillNo');
            		      		layer.close(index);
         				 },
         				 btn2: function(index, layero){
         					 // alert(2);
         				  }
         				 });
    			}else{
     				 
     				$('#popupDiv2').html(html);
     				 
     			}
	    		//alert(JSON.stringify(result.data))
    		});
    	},
    	function(data){
    		//alert(JSON.stringify(data))
    	});
  };
     function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
      	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
      	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickGoods','MATRIAL_INFO',function(index, layero){
      		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
      		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
      		 $('#goodsId').val(rowCode);
      		 layer.close(index);
  		  });
       };
      
       function onClickUser(isFirQry,pageNo,pageSize,lastPara,isOpened){
       	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'},{colCode:'simpleName',colName:'简称'}];
       	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','CUSTOM_INFO',function(index, layero){
       		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
       		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
       		 $('#customerId').val(rowCode);
       		 layer.close(index);
   		  });
        };
        
        function onChangeErpBill(sender){
        	var rowCode = $(sender).val();
        	 if (rowCode){
	      			Mes.getJson2("/plan/mesPlanMonth/getByBillno/index.do","erpBillNo="+rowCode,		
	      		    		function(result){
	      						debugger;
	      						$('#goodsName').text(result.data.goodsName||'');
	      						$('#qty').text(result.data.qty||'');
	      						$('#qtyUnit').text(result.data.qtyUnit||'');	
	      						$('#dispatchForm').data('bootstrapValidator').updateStatus('erpBillNo', 'NOT_VALIDATED',null).validateField('erpBillNo');
	      			    	},
	      			    	function(data){
	      			    		
	      			    	});
	      			
	      			
	      		 }
        };
        
        function changeDate (sender){
        	 
        	$('#dispatchForm').data('bootstrapValidator').updateStatus('planBeginDate', 'NOT_VALIDATED',null).validateField('planBeginDate');
        }
