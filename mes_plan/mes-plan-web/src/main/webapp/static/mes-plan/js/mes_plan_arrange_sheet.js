 var dropdownDatas = {};
 var clickA;
 function customReady() {
	 var sheetId = Mes.getUrlParam("sheetId");
	 var urlPara = "types=plan_object,plan_order_type,plan_order_status";
	  $.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
			 Mes.getJsonDeferred("/plan/mesPlanMonth/getArrangeTechnic/index.do",""),
			 Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT&codeList='W04','W05'"),
			 Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode="+WORKSHOP_ARRANGE),
			 Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT")
		).done(function(data1,data2,data3,data4,data5){
			debugger
			dropdownDatas = data1 ;
			var convertData = [];
			for(var i=0;i<data2.length;i++)
			{
				convertData.push({'id':data2[i].processCode,'text':data2[i].processName});
			} 
			dropdownDatas.technicList= convertData;
			
			dropdownDatas.shiftStatusList = [{id:'0',text:'未下发'},{id:'10',text:'已下发'}];
			dropdownDatas.workshopList = [{id: "C11", text: "准备车间"},{id: "C12", text: "一织车间"},{id: "C13", text: "二织车间"}];
			dropdownDatas.shiftList = data3;
			dropdownDatas.shiftStyleList = data4;
			dropdownDatas.shiftListAll  =data5;
			ddwAddOption($("#technicId"),dropdownDatas.technicList);
			ddwAddOption($("#planObject"),dropdownDatas.plan_object);
			$("#technicId").select2({multiple: true});
			$("#technicId").val('').trigger("change");
			searchOrder2(null,sheetId);	
			setFormValidate();
		}) ;
    };
    function setFormValidate(){
    	$('#dispatchForm').bootstrapValidator({
			  message: 'This value is not valid',
			  feedbackIcons: {
			  //valid: 'glyphicon glyphicon-ok',
			  //invalid: 'glyphicon glyphicon-remove',
			  validating: 'glyphicon glyphicon-refresh'
			  },
			  fields: {
				  shiftDate: {
						   message: '不能为空',
						   validators: {
						   notEmpty: {message: '不能为空'}
						}
				  },
				  technicId: {
					   message: '不能为空',
					   validators: {
					   notEmpty: {message: '不能为空'}
					}
				  },
				  planObject: {
					   message: '不能为空',
					   validators: {
					   notEmpty: {message: '不能为空'}
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
		     	var technicId =  $('#technicId').val().toString();
		     	var planObject = $('#planObject').val();
		     	var totalQty = $('#totalQty').text().trim();
		     	var qtyUnit = $('#qtyUnit').text().trim();
		     	var shiftDate =  $('#shiftDate').val();
		     	var id = $('#id').val();
		     	var sheetId = $('#sheetId').val();
		     	var thList = $("#tabHead").find(":selected");
		     	var shiftId1 = $(thList[0]).val();
		     	var shiftStyle1 = $(thList[1]).val();
		     	var shiftId2 = $(thList[2]).val();
		     	var shiftStyle2 = $(thList[3]).val();
		     	if (shiftId1 == shiftId2 || shiftStyle1 ==shiftStyle2){
		     		Mes.sysAlert('班次1,班次2的班组值不能重复!');
		     		return;
		     	}
		     	
		     	selectedData = {"technicId":technicId,"planObject":planObject,"totalQty":totalQty,"qtyUnit":qtyUnit,"id":id,"sheetId":sheetId};
		     	
		     	selectedData.mesPlanArrangeDetList = [];
		  		var trList = $("#tabBody").children("tr");
		  	    for (var i=0;i<trList.length;i++) {
		  	    	var rowElement = $(trList[i]);
		  	    	var erpBillNo = rowElement.find("[name='erpBillNo']").text().trim();
		  	    	var arrangeErpBillNo = rowElement.find("[name='arrangeErpBillNo']").val().trim();
		  	    	var goodsId = rowElement.find("[name='goodsId']").val();
		  	    	var detId = rowElement.find("[name='detId']").val();
		  	    	var leftQty = rowElement.find("[name='leftQty']").text().trim();
		  	    	var qtyUnit = rowElement.find("[name='qtyUnit']").text().trim();
		  	    	var workcenterId = rowElement.find("[name='workcenterId']").val();
		  	    	var qty1 = rowElement.find("[name='qty1']").val().trim();
		  	    	var qty2 = rowElement.find("[name='qty2']").val().trim();
		  	 	    		
		  	 	    	selectedData.mesPlanArrangeDetList.push({"id":detId,"erpBillNo":erpBillNo,"arrangeErpBillNo":arrangeErpBillNo,"goodsId":goodsId,"leftQty":leftQty,
		  	 	    		"qtyUnit":qtyUnit,"workcenterId":workcenterId,"qty1":qty1,"qty2":qty2,"shiftId1":shiftId1,"shiftStyle1":shiftStyle1,
		  	 	    		"shiftId2":shiftId2,"shiftStyle2":shiftStyle2});
		  	 	    
		  	    }
		  		//Mes.sysAlert(JSON.stringify(selectedData,null));
		  		//return;
		  		if (selectedData.mesPlanArrangeDetList.length>0){
		  			var urlPara = 'shiftDate='+shiftDate;
		  			Mes.loadJson2("/plan/mesPlanArrangeSheet/batchsave/det.do",urlPara,JSON.stringify(selectedData),		
		               		function(result){
		      	        	 	//Mes.sysAlert(result.msg, null);
		          	     		Mes.sysAlert("生成任务成功!",
		          	     				//function(){self.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;
		          	     				function(){
		          	     					layer.closeAll();
		          	     					var sheetId = Mes.getUrlParam("sheetId")||result.data.sheetId;
		          	     					//searchOrder2($('#workcenterId').val(),sheetId);
		          	     					self.location.href='/mes-plan/production/mes_plan_arrange_sheet.html?sheetId='+sheetId;
		          	     				});
		           	    	},
		           	    	function(data){
		           	    		Mes.sysAlert(JSON.stringify(data))
		           	    });
		  		}else{
		  			Mes.sysAlert("没有找到有效数据!");
		  		}
		         
			 });
    }
    
    function searchOrder2(workcenterId,sheetId){
    	if (sheetId){
    		$('#loadBtn').hide();
	   		 Mes.getJson2("/plan/mesPlanArrangeSheet/getsheet/index.do","sheetId="+sheetId,function(result){
	   			$('#id').val(result.data.id);
	   	    	$('#sheetId').val(result.data.sheetId);
	   			$('#shiftDate').val(result.data.shiftDate);
	   			if (result.data.technicId){
	   				$('#technicId').val(result.data.technicId.split(',')).trigger("change");
	   			}
	   			$('#planObject').val(result.data.planObject);
	   			$('#totalQty').text(result.data.totalQty);
	   			$('#qtyUnit').text(result.data.qtyUnit);
	   			
	   			result.data.dropdownDatas = dropdownDatas;
	   			result.data.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(result.data.mesPlanArrangeDetList,"goodsId"));
	   			debugger
	   			var html = template('detailTab1.tmp', result.data);
	        	$('#detailTab1').html(html);
	        	
	        	var thList = $("#tabHead").find(":input");
	        	var element0 = $(thList[0]);
	        	var element1 = $(thList[1]);
	        	var element2 = $(thList[2]);
	        	var element3 = $(thList[3]);
	        	if (result.data.mesPlanArrangeDetList && result.data.mesPlanArrangeDetList.length>0){
	        		element0.val(result.data.mesPlanArrangeDetList[0].shiftId1);
	        		element1.val(result.data.mesPlanArrangeDetList[0].shiftStyle1);
	        		element2.val(result.data.mesPlanArrangeDetList[0].shiftId2);
	        		element3.val(result.data.mesPlanArrangeDetList[0].shiftStyle2);
	        		
	        		$.each(result.data.mesPlanArrangeDetList,function(index,item){
		        		if (parseInt(item.shiftStatus||0)>0){
		        			element0.attr('disabled','disabled');
			   				element1.attr('disabled','disabled');
			   				element2.attr('disabled','disabled');
			   				element3.attr('disabled','disabled');
			   				$('#dispatchForm').find(':input').attr('disabled','disabled');
		        		}
		        	});
	        		
	        	}
	   		 });
    	}
    	else{
    		var data = [];
    		data.dropdownDatas = dropdownDatas;
    		var html = template('detailTab1.tmp', data);
        	$('#detailTab1').html(html);
        	
        	setShiftDefault();
    	}
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
  
  
  function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
  	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
  	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickGoods','MATRIAL_INFO',function(index, layero){
  		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
  		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
  		 $('#goodsId').val(rowCode);
  		 layer.close(index);
	  });
   };
      
   function changeDate (sender){
    	$('#dispatchForm').data('bootstrapValidator').updateStatus('shiftDate', 'NOT_VALIDATED',null).validateField('shiftDate');
    	$('#tabBody').html('');
   };
   function on_chk_det(id){
	   Mes.getJson2("/plan/mesPlanArrangeSheet/checkDet/det.do","id="+id,function(result){
	   		//alert(result.data);
		   self.location.href='/mes-plan/production/mes_plan_arrange_sheet.html?sheetId='+result.data;
	   	},function(){
	   		Mes.sysAlert("下发出错,请检查数据");
	   	});
   };
   function onOpenPlan(planSheetId){
	   Mes.loadJson2("/plan/mesPlanDispatch/findSheetPage/1/5/index.do","sheetId="+planSheetId,"{}",		
				function(result){
		   //result.data.list[0].id
		   //result.data.list[0].erpId
		   if (result.data &&result.data.list.length>0)
			{
			   self.location.href = "/mes-plan/production/mes_plan_dispatch.html?erpId="+result.data.list[0].erpId+"&id="+result.data.list[0].id;
			}else{
				Mes.syaAlert('查询数据出错!');
			}
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
   };
   function loadData(){
	   var date = $('#shiftDate').val();
	   var planObject = $('#planObject').val();
	   if (!date || !planObject){return;}
	   var param= "planObject="+planObject+"&date="+date;
	   if (planObject == "10" || planObject == "20" || planObject == "30" ){
		   Mes.getJson2("/plan/mesPlanArrangeSheet/findStockTotalList/index.do",param,function(result){
			   //重新构造数据
			   debugger
			    var newData = {};
			    newData.dropdownDatas = dropdownDatas;
			    newData.mesPlanArrangeDetList = result.data;
			    var totalQty=0;
			    $.each(result.data,function(index,item){
			    	totalQty = totalQty + item.leftQty;
			    	if (index==0){
			    		 $('#qtyUnit').text(item.qtyUnit);
			    	}
			    });
			    $('#totalQty').text(totalQty);
			    newData.planObject = planObject;
			    newData.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(newData.mesPlanArrangeDetList,"goodsId"));
	  			var html = template('detailTab1.tmp', newData);
		       	$('#detailTab1').html(html);
		       	setShiftDefault();
		   },function(){});
	   }else{
		   //打包接口
		   Mes.getJson2("/plan/mesPlanArrangeSheet/getCombineAmount/index.do",param,function(result){
			   //重新构造数据
			    var newData = {};
			    newData.dropdownDatas = dropdownDatas;
			    newData.mesPlanArrangeDetList = result.data;
			    var totalQty=0;
			    $.each(result.data,function(index,item){
			    	totalQty = totalQty + item.leftQty;
			    	if (index==0){
			    		 $('#qtyUnit').text(item.qtyUnit);
			    	}
			    });
			    $('#totalQty').text(totalQty);
			    newData.planObject = planObject;
			    newData.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(newData.mesPlanArrangeDetList,"goodsId"));
	  			var html = template('detailTab1.tmp', newData);
		       	$('#detailTab1').html(html);
		       	setShiftDefault();
		   },function(){});
	   }
   };
   function on_chg_planObject(sender){
	   debugger
	   var itemValue = $(sender).val();
	   var technicIdValue =  $('#technicId').val();
	  
	   if (itemValue =="10" || itemValue =="20" || itemValue =="30"){
		   $('#technicId').val(['JP14','JP15','JP16']).trigger("change");
	   }else if (itemValue =="40"){
		   $('#technicId').val(['JP17','JP18']).trigger("change");
	   }
	   $('#tabBody').html('');
   };
   function on_show_det(sender){
	   debugger
	   var date = $('#shiftDate').val();
	   var planObject = $('#planObject').val();
	   var productOrder = $(sender).parent().parent().find("[name='erpBillNo']").text().trim();
	   var goodsId =   $(sender).parent().parent().find("[name='goodsId']").val();
	   //alert(productOrder);
	   
	   if (planObject !="40"){
		   var param1="date="+date+"&planObject="+planObject+"&productOrder="+productOrder;
		   searchOrder(false,1,5,param1,false);
	   }else{
		   //endTime=2018-03-16&erpBillNo=204_*_*&goodsId=C070320320045
		   var param3="endTime="+date+"&goodsId="+goodsId+"&erpBillNo="+productOrder;
		   searchOrder3(false,1,5,param3,false);
	   }
	   
   }
   
   function searchOrder(isFirQry,pageNo,pageSize,lastPara,isopened){
	   debugger
		var param ;
		if (isFirQry){
			 param = $('#from_condition').serialize();
		}else{
			//if (!lastPara) {alert('未设置分页参数!');return;}
			param = lastPara;
		}
		
		 Mes.getJson2("/plan/mesPlanArrangeSheet/findStockList/1/5/index.do",param,function(result){
			   //Mes.sysAlert(JSON.stringify(result.data));
			   result.data.qryParam = param;
			   
			   
			   $.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT"),
						 Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=ORG&codeList="+getCommaStr(result.data.list,"workTeam"))
					).done(function(data1,data2){
						 result.data.shiftList = data1;
						 result.data.shiftStyleList = data2;
						 debugger
				   			var html = template('detailTab.tmp', result.data);
				   			if (!isopened){
					    			layer.open({
				        				 type: 1,
				        				 title :'查询',
				        				 area: ['80%', '80%'],
				        				 content:(html),
				        				 btn: ['返回'],
				        				 btn1: function(index, layero){
				           		      		layer.close(index);
				        				 }
				        				 });
				   			}else{
				    				$('#popupDiv2').html(html);
				    		}
					}) ;
		   },function(){
			   
		   });
	  };

	  function searchOrder3(isFirQry,pageNo,pageSize,lastPara,isopened){
	   
		var param ;
		if (isFirQry){
			 param = $('#from_condition').serialize();
		}else{
			//if (!lastPara) {alert('未设置分页参数!');return;}
			param = lastPara;
		}
		
		//dropdownDatas.shiftList
		//dropdownDatas.shiftStyleList
		
		 Mes.getJson2("/plan/mesPlanArrangeSheet/getCombineAmountDet/"+pageNo+"/"+pageSize+"/index.do",param,function(result){
			   //Mes.sysAlert(JSON.stringify(result.data));
			 debugger
			   result.data.qryParam = param;
			   result.data.shiftList = dropdownDatas.shiftList;
			   result.data.shiftStyleList = dropdownDatas.shiftStyleList;
	   			var html = template('detailTabPackage.tmp', result.data);
	   			if (!isopened){
		    			layer.open({
	        				 type: 1,
	        				 title :'查询',
	        				 area: ['80%', '80%'],
	        				 content:(html),
	        				 btn: ['返回'],
	        				 btn1: function(index, layero){
	           		      		layer.close(index);
	        				 }
	        				 });
	   			}else{
	    				$('#popupDiv2').html(html);
	    		}
	   			
		   },function(){
			   
		   });
	  };
	  function on_change_qty1(sender){
		  var leftQty = $(sender).parent().parent().find("[name='leftQty']").text().trim()||0;
		  var qty = $(sender).val()||0;
		  if (parseFloat(leftQty-qty)<0){
			  Mes.sysAlert("班次["+qty+"]数量大于库存数量["+leftQty+"]");
			  $(sender).focus();
			  $(sender).select();
			  $($(sender).parent().next().children()[0]).val(0);
		  }else{
			  $($(sender).parent().next().children()[0]).val(leftQty-qty);
		  }
		  
	  };
	  
	  function setShiftDefault(){
		  var thList = $("#tabHead").find(":input");
      	var element0 = $(thList[0]);
      	var element1 = $(thList[1]);
      	var element2 = $(thList[2]);
      	var element3 = $(thList[3]);
      	element0.val("W04");
  		//element1.val(result.data.mesPlanArrangeDetList[0].shiftStyle1);
  		element2.val("W05");
	  };
	  
	  
	  function searchErpOrder(isFirQry,pageNo,pageSize,lastPara,isopened){
		  debugger
			var param ;
			if (isFirQry){
				 param = $('#from_condition').serialize();
			}else{
				//if (!lastPara) {alert('未设置分页参数!');return;}
				param = lastPara;
			}
			Mes.loadJson2("/plan/mesPlanMonth/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
				function(result){
		    		$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=CUSTOM_INFO&codeList="+getCommaStr(result.data.list,"customerId")),
		    				Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00")
		    		).done(function(data1,data2){
		    			result.data.qryParam = param;
		    			dropdownDatas.workshopList = data2;
		    			result.data.dropdownDatas =  dropdownDatas;
		    			//result.data.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(result.data.list,"goodsId"));
		    			result.data.customerList = data1;
		    			if (!isopened){
		    				var bomGoodsId = $(clickA).parent().parent().parent().parent().find("[name='goodsId']").val();
		    				dropdownDatas.bomGoodsId=bomGoodsId;
		    			}
		    			var headhtml = template('detailErpTab2.tmp', dropdownDatas);
		    			var html = template('detailErpTab.tmp', result.data);
		    			//var render = template.compile(PAGETEMPLATE);  
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
		            		      		debugger
		            		      		$($(clickA).parent().prev().children()[0]).val(rowCode);
		            		      		//$(clickA).prev().val(rowCode);
		            		      		//$('#erpBillNo').val(rowCode);
		            		      		//onChangeErpBill('#erpBillNo');
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
		  function hideRowA(sender){
			  $($(sender).parent().parent().parent().parent().find("[name='checkDetA']")).hide()
		  };
		  
		  function onClickCheckBox(sender){
			  if ($(sender).prop("checked")){
				  $("#popupDiv2").find(":checked").removeAttr('checked');
				  $(sender).prop("checked","true");
			  }
		  }