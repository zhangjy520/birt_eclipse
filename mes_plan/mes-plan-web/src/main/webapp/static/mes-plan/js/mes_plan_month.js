var dropdownDatas = {};
function customReady(){
	var urlPara = "types=plan_order_type,plan_order_status";
	//dropdownDatas = Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara);
	var id = Mes.getUrlParam("id");
	var param;
	var workshopList;
	var worktypeList;
	$.when(Mes.getJsonDeferred("/plan/common/getdicts/index.do",urlPara),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00"),
			Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_TYPE")
	).done(function(data1,data2,data3){
		dropdownDatas = data1 ;
		workshopList = data2;
		worktypeList = data3;
		if (!!id){
			param = "id="+id;
		}else
		{
			alert("得到id参数出错");
			return;
		}
		Mes.getJson2("/plan/mesPlanMonth/getWholeErpBillNo/index.do",param,		
	    		function(result){
					result.data.dropdownDatas = dropdownDatas;
					result.data.workshopList = workshopList;
					result.data.worktypeList = worktypeList;
					$.when(!result.data.customerId?[]:Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=CUSTOM_INFO&codeList='"+result.data.customerId+"'"),
							Mes.getJsonDeferred2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_CENTER&parentCode="+result.data.workshopId)
					).done(function(data1,data2){
						result.data.customerList= data1;
						result.data.workcenterList = data2;
						var html = template('detailTab.tmp', result.data);
		            	$('#detailTab').html(html);
		            	
		            	var html2 = template('process_line.tmp', result.data);
		            	$('#plan_process_line').html(html2);
		            	
		            	if (result.data.mesPlanTechnicList && result.data.mesPlanTechnicList.length>0){
		            		var html3 = template('technic_line.tmp', result.data);
		                	$('#technic_line_div').html(html3);
		            	}
		            	
			    		//alert(JSON.stringify(result.data))
		            	if (result.data.planStatus == '98' || result.data.planStatus == '99' || result.data.planStatus == '100'){
				    		$('#checkBtn').hide();
				    	}
		            	$('#disPatchA').attr('href','/mes-plan/production/mes_plan_dispatch.html?erpId='+result.data.id);
		            	$('#stockA').attr('href','/mes-plan/production/mes_plan_erp_material_stock.html?goodsId='+result.data.goodsId+'&erpBillNo='+result.data.erpBillNo);
					});
		    	},
		    	function(data){
		    		//alert(JSON.stringify(data))
		    	});
		
	});
	
	
	
	
};
/*
function onFinish(sender){
	layer.confirm("确认订单已完成生产吗?",
	    {
	        btn: ['是', '否'],
	        btn1: function (index) {
	        	
	        },
	        btn2: function () {
	        }
	    });
 };*/
 
 function onFinish(sender){
	 var html2 = FINISH_TEMPLATE;
	 layer.open({
		 type: 1,
		 title :'完工说明',
		 area: ['30em', '15em'],
		 content: html2,
		 btn: ['完工', '异常完工','取消'],
		 btn1: function(index, layero){
			 var id = $('#id').val();
			 var remarks = $('#remarksTextarea').val();
			 var urlPara = "id="+id+"&planStatus=100&remarks="+remarks;
			 var erpBillNo = $('#erpBillNo').val();
			 var urlPara2 = "level=1&billNo="+erpBillNo;
				if (Mes.getJsonSyncAllData("/plan/mesPlanShift/validateFinish/index.do",urlPara2) ==0 ){
					finishInner("/plan/mesPlanMonth/finish/index.do",urlPara);
				}else{
					layer.msg('订单状态不正确或下级订单未完工!',{time: 3000});
				}
			 
		 },
		 btn2: function(index, layero){
			 var id = $('#id').val();
			 var remarks = $('#remarksTextarea').val().trim();
			 if (!remarks){
				 layer.msg('请输入异常完工说明!',{time: 1500});
				 return false;
			 }else{
				 var erpBillNo = $('#erpBillNo').val();
				 var urlPara = "id="+id+"&planStatus=98&remarks="+remarks;
				 var urlPara2 = "level=1&billNo="+erpBillNo;
				 if (Mes.getJsonSyncAllData("/plan/mesPlanShift/validateFinish/index.do",urlPara2) ==0 ){
					 finishInner("/plan/mesPlanMonth/finish/index.do",urlPara);
				 }else{
					 layer.msg('订单状态不正确或下级订单未完工!',{time: 3000});
					 return false;
				 }
			 }
			
		 },
		 btn3: function(index, layero){
			 // alert(2);
			 layer.close(index);
		 }
	});
 }
 
 function finishInner(url,urlPara){
	 Mes.loadJson(url,urlPara,		
		function(result){
			if (result.succ){
				location.reload() ;
			}else{
				Mes.sysAlert(result.msg);
			}
		},
		function(data){
			Mes.sysAlert(JSON.stringify(data))
	    });
 }

 function changeFolderFun(){
	debugger
		if ( $('#plan_process_Tab1').css('display')=='none')
		{	$('#folderBtn').removeClass('glyphicon-chevron-up');
			$('#folderBtn').addClass('glyphicon-chevron-down');
			 $('#plan_process_Tab1').css('display','');
			 $('#plan_process_Tab2').css('display','');
		}
		else{
			$('#folderBtn').removeClass('glyphicon-chevron-down');
			$('#folderBtn').addClass('glyphicon-chevron-up');
			$('#plan_process_Tab1').css('display','none');
			 $('#plan_process_Tab2').css('display','none');
		}
	};
function onOverview(){
	var erpBillNo = $('#erpBillNo').val();
	if (!erpBillNo) return;
		layer.open({
			 type: 2,
			 title :'订单调度明细',
			 area: ['750px', '600px'],
			 content: '/mes-plan/production/mes_plan_overview2.html?erpBillNo='+erpBillNo,
			 btn: ['返回'],
			   
		});
}
 