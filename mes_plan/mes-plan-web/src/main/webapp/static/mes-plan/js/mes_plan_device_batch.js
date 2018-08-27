var mesPlanMonth;
var head;
 function customReady() {
		parId = Mes.getUrlParam("parId");
		erpId = $.cookie('erpId'+parId);
		if (erpId){
			$.when(Mes.getJsonDeferred("/plan/mesPlanDispatch/getDet/index.do","id="+parId),
					Mes.getJsonDeferred("/plan/mesPlanMonth/get/index.do","id="+erpId)
			).done(function(data1,data2){
				head = data1;
				mesPlanMonth = data2;
				$('#chgDeviceQty').text((head.deviceChangeQty||0)+'台/组)');
				$('#deviceQty').text((head.deviceQty||0)+'台/组)');
				searchOrder(!head.deviceChangeQty);	
				$('#goodsId').text(mesPlanMonth.goodsName||mesPlanMonth.goodsId);
				if (mesPlanMonth.workshopId != WORKSHOP_ARRANGE){
					$('#dispatchQty').text(head.dispatchQty+mesPlanMonth.qtyUnit);
				}else{
					$('#dispatchQty').text(head.dispatchQty+''+mesPlanMonth.qtyUnit);
				}
			});
		}
		else{
			Mes.sysAlert('找不到cookie参数，请关闭页面后重新打开',null);
			return;
		}
    };
    function searchOrder(isLoadDevice){
    	var param ;
    	var beginDate =  new Date(head.planBeginDate.replace(/-/,'/').replace(/-/,'/'));
    	var endDate =  new Date(head.planEndDate.replace(/-/,'/').replace(/-/,'/'));
    	var arrDate= [];
    	while (beginDate <= endDate)
    	  {
    		arrDate.push(beginDate.Format('yyyy-MM-dd'));
    		beginDate.setDate(beginDate.getDate()+1)
    	  }
    	param = "workDate1="+head.planBeginDate+"&workDate2="+head.planEndDate+"&workcenterId="+head.workcenterId;
    	param = rapRangeUrl(param);
    	Mes.loadJson2("/plan/mesPlanDeviceDay/findList/index.do",param,"{}",		
    		function(result){
    			result.data.head = head;
    			result.data.mesPlanMonth = mesPlanMonth;
    			result.data.arrDate = arrDate;
    			$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&codeList='"+head.workcenterId+"'"),
    					Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(result.data.mesPlanDeviceDayList,"goodsId"))
    			).done(function(data1,data2){
    				result.data.workcenterList = data1;
        			result.data.goodsList = data2;
                	var html = template('detailTab1.tmp', result.data);
                	$('#detailTab1').html(html);
                	
                	if (isLoadDevice){
                		var html = template('detailTab.tmp', result.data);
                		$('#detailTab').html(html);
                		Mes.sysLoading("正在加载设备信息",500);
                	}
                	if (!head.deviceChangeQty){
                		$('#folderA').click();
                	}
    			});
	    	},
	    	function(data){
	    		//alert(JSON.stringify(data))
	    	});
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
    	var selectedData = [];
    	debugger
 		var trList = $("#tabChgGoodsBody").children("tr")
 	    for (var i=0;i<trList.length;i++) {
 	    	var workcenterId = $(trList[i]).find("[name='workcenterId']").val();
 	    	var deviceId = $(trList[i]).find("[name='deviceId']").text().trim();
 	    	var deviceName = $(trList[i]).find("[name='deviceName']").text().trim();
 	    	var capacityInput = $(trList[i]).find("[name='capacityInput']").text();
 	    	var isChgGoods = 1;
 	    	var planBeginDate,planEndDate;
 	    	//$(":checkbox:checked",trList[i]).each(function(index){
 	    	var radioElement = $(":radio:checked",trList[i]);
 	    	if (radioElement && radioElement.length>0){
 	    		planBeginDate = $(radioElement).val();
 	    		planEndDate = 	planBeginDate;
 	    		var workCenterQty = capacityInput;
 	    		selectedData.push({"workcenterId":workcenterId,"deviceId":deviceId,"planBeginDate":planBeginDate,"planEndDate":planEndDate,"workCenterQty":workCenterQty,"isChgGoods":isChgGoods,"deviceRequire":mesPlanMonth.deviceRequire,"erpBillNo":mesPlanMonth.erpBillNo,"deviceName":deviceName});
 	    	}
 	    }
 		//Mes.sysAlert(JSON.stringify(selectedData,null));
 		//return;
 		if (selectedData.length>0){
 			Mes.loadJson2("/plan/device/batchsave/det.do","dispatchId="+parId,JSON.stringify(selectedData),		
              		function(result){
     	        	 	//Mes.sysAlert(result.msg, null);
         	     		Mes.sysAlert("指定设备(换产品)成功!",
         	     				//function(){self.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;
         	     				function(){
         	     			layer.closeAll();
                	     			searchOrder(true);
                	     			$('#folderA').trigger('click');
         	     		});
          	    	},
          	    	function(data){
          	    		Mes.sysAlert(JSON.stringify(data))
          	    });
 		}else{
 			searchOrder(true);
 			$('#folderA').trigger('click');
 		}
     };
     var fun_confirm = function(){
    		var selectedData = [];
    		var trList = $("#tabBody").children("tr")
    	    for (var i=0;i<trList.length;i++) {
    	    	var workcenterId = $(trList[i]).find("[name='workcenterId']").val();
    	    	var deviceId = $(trList[i]).find("[name='deviceId']").text().trim();
    	    	var deviceName = $(trList[i]).find("[name='deviceName']").text().trim();
    	    	var capacityInput = $(trList[i]).find("[name='capacityInput']").text();
    	    	
    	    	var planBeginDate,planEndDate;
    	    	//$(":checkbox:checked",trList[i]).each(function(index){
    	    	var priorIsChgGoods;
        		$(":checkbox",trList[i]).each(function(index){
        			if ($(this).prop('checked') && (!priorIsChgGoods || isChgGoods == priorIsChgGoods ) ){
        				if (!planBeginDate) 
        				{
        					planBeginDate = $(this).attr("name");
        				}
        				planEndDate = $(this).attr("name");
        			}else{
        				if (planBeginDate){
        					var beginDate =  new Date(planBeginDate.replace(/-/,'/').replace(/-/,'/'));
                	    	var endDate =  new Date(planEndDate.replace(/-/,'/').replace(/-/,'/'));
                	    	var workCenterQty = ((endDate-beginDate)/24/3600/1000 + 1)*capacityInput;
                	    	//var isChgGoods = $(this).val(); 
                			var isChgGoods = 0 ;
        					selectedData.push({"workcenterId":workcenterId,"deviceId":deviceId,"planBeginDate":planBeginDate,"planEndDate":planEndDate,"workCenterQty":workCenterQty,"isChgGoods":isChgGoods,"deviceRequire":mesPlanMonth.deviceRequire,"erpBillNo":mesPlanMonth.erpBillNo,"deviceName":deviceName});
                			planBeginDate = null;
                			planEndDate = null;
        				}
        			}
        			priorIsChgGoods = isChgGoods;
        		  //var name= tablerow.find("[name='p_name']").val();
        		});
        		if (planBeginDate){
					var beginDate =  new Date(planBeginDate.replace(/-/,'/').replace(/-/,'/'));
        	    	var endDate =  new Date(planEndDate.replace(/-/,'/').replace(/-/,'/'));
        	    	var workCenterQty = ((endDate-beginDate)/24/3600/1000 + 1)*capacityInput;
        	    	//var isChgGoods = $(this).val(); 
        	    	var isChgGoods = 0 ;
					selectedData.push({"workcenterId":workcenterId,"deviceId":deviceId,"planBeginDate":planBeginDate,"planEndDate":planEndDate,"workCenterQty":workCenterQty,"isChgGoods":isChgGoods,"deviceRequire":mesPlanMonth.deviceRequire,"erpBillNo":mesPlanMonth.erpBillNo,"deviceName":deviceName});
        			planBeginDate = null;
        			planEndDate = null;
				}
        		
    	    }
    		
    		if (selectedData.length>0){
    			Mes.loadJson2("/plan/device/batchsave/det.do","dispatchId="+parId,JSON.stringify(selectedData),		
                 		function(result){
        	        	 	//Mes.sysAlert(result.msg, null);
            	     		Mes.sysAlert("保存成功!",
            	     				function(){self.location.href = "/mes-plan/production/mes_plan_device_parallel.html?id="+parId;
            	     			
            	     		});
             	    	},
             	    	function(data){
             	    		Mes.sysAlert(JSON.stringify(data))
             	    });
    		}
     }
     
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