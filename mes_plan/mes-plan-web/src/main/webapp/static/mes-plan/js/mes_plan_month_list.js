var dropdownDatas = {};
function customReady() {
		var urlPara = "types=plan_order_type,plan_order_status";
		$.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
				Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00") 
		).done(function(data1,data2){
			dropdownDatas = data1 ;
			dropdownDatas.workshop = data2;//changeCode2Name();
			ddwAddOptionCodeName($("[name='workshopId']"),dropdownDatas.workshop);
			ddwAddOption($("[name='planStatus']"),dropdownDatas.plan_order_status);
			ddwAddOption($("[name='billType']"),dropdownDatas.plan_order_type);
			$('#planBeginDate1').datepicker({
			    format: 'yyyy-mm-dd',
			    language: "zh-CN",//设置语言
			     autoclose: true//设置是否关闭
			});
			$('#planBeginDate2').datepicker({
			    format: 'yyyy-mm-dd',
			    language: "zh-CN",//设置语言
			     autoclose: true//设置是否关闭
			});
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
    	Mes.loadJson2("/plan/mesPlanMonth/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
    		function(result){
	    		$.when(Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=CUSTOM_INFO&codeList="+getCommaStr(result.data.list,"customerId"))
	    		).done(function(data1){
	    			result.data.qryParam = param;
	    			result.data.dropdownDatas =  dropdownDatas;
	    			//result.data.goodsList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=MATRIAL_INFO&codeList="+getCommaStr(result.data.list,"goodsId"));
	    			result.data.customerList = data1;
	    			var html = template('detailTab.tmp', result.data);
	    			var render = template.compile(PAGETEMPLATE);  
	    			var subhtml = render(result.data);  
	            	$('#detailTab').html(html+subhtml);
		    		//alert(JSON.stringify(result.data))
	    		});
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
     
     function onClickUser(isFirQry,pageNo,pageSize,lastPara,isOpened){
    	var colsList= [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'},{colCode:'simpleName',colName:'简称'}];
    	onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,'onClickUser','CUSTOM_INFO',function(index, layero){
    		 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
    		 var rowCode = $(rowHtml).find("[name=code]").text()||'';
    		 $('#customerId').val(rowCode);
    		 layer.close(index);
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
     
//     function onClickGoods(isFirQry,pageNo,pageSize,lastPara,isOpened){
//     	var param ;
//      	if (isFirQry){
//      		param = $('#popupForm').serialize();
//      		if (param){
//      			param = param+"&mdmType=MATRIAL_INFO";
//      		}else{
//      			param = "mdmType=MATRIAL_INFO";
//      		}
//      	}else{
//      		//if (!lastPara) {alert('未设置分页参数!');return;}
//      		param = lastPara;
//      	}
//      	Mes.loadJson2("/plan/dubbo/getQueryPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
//      		function(result){
//      		debugger
//      			result.data = JSON.parse(result.data);
//      			result.data.qryParam = param;
//      			//result.data.dropdownDatas =  dropdownDatas;
//      			//result.data.cols ="";
//      			//result.data.onPage ="";
//      			result.data.cols = [{colCode:'code',colName:'编码'},{colCode:'name',colName:'名称'}];
//      			result.data.onPage = 'onClickUser';
//              	//$('#detailTab').html(html+subhtml);
//      			if (!isOpened){
//      				var render = template.compile(PAGETEMPLATEPOPUP);  
//          			var subhtml = render(result.data);  
//          			
//          			var render = template.compile(PAGETEMPLATEPOPUPTAB);  
//          			var subhtml2 = render(result.data);  
// 	     			layer.open({
// 	     				 type: 1,
// 	     				 title :'客户查询',
// 	     				 area: ['50em', '32em'],
// 	     				 content: subhtml+"<div id='popupDiv' style='padding:10px 5px'>"+subhtml2+'</div>', //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
// 	     				 btn: ['确认', '返回'],
// 	     				 btn1: function(index, layero){
// 	     					 //$('#arrangeForm').submit();
// 	     					 var rowHtml = $('#popupTab').find("input:checked").parent().parent();
// 	     					 var customCode = $(rowHtml).find("[name=code]").text()||'';
// 	     					 $('#customerId').val(customCode);
// 	     					   layer.close(index);
// 	     					  }
// 	     				  ,btn2: function(index, layero){
// 	     					 // alert(2);
// 	     				  }
// 	     				 });
//      			}else{
//      				var render = template.compile(PAGETEMPLATEPOPUPTAB);  
//          			var subhtml2 = render(result.data);  
//      				$('#popupDiv').html(subhtml2);
//      			}
//  	    	},
//  	    	function(data){
//  	    	});
//      };
      
     function onClickAdd(){
    	 layer.open({
    		 btn: ['确认', '取消'],
    		 yes :function(index,dom){debugger; alert($(dom.find('iframe')[0]).contents().find('#createTime').val());layer.close(index);return false; },
    		 cancel :function(index){ alert('cancel'); return false; },
    		  area:['1200px', '600px'],
    		  type: 2, 
    		  title:'查找客户',
    		  content: '/mes-plan/production/production_plan_add.html' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    		}); 
     };
     
     function test(varl){
    	 alert(var1);
     };

     
     