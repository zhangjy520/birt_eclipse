var dropdownDatas = {};
function customReady(){
	var data=[{},{}];
	
	
	
//	var urlPara = "types=plan_order_type,plan_order_status";
//	dropdownDatas = Mes.getJsonSync("/plan/common/getdicts/index.do",urlPara);
//	
//	var id = Mes.getUrlParam("id");
//	var param;
//	var workshopList = Mes.getJsonSync2("/plan/dubbo/getMdmListByParent/index.do","mdmType=ORG&parentCode=C00");
//	var worktypeList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_TYPE");
//	
//	//var worktypeList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=WORK_TYPE");
//	
//	if (!!id){
//		param = "id="+id;
//	}else
//	{
//		alert("得到id参数出错");
//		return;
//	}
	var erpBillNo = Mes.getUrlParam("erpBillNo");
	var param = 'erpBillNo='+erpBillNo;
	Mes.getJson2("/plan/mesPlanShift/getErpBillDetail/index.do",param,		
    		function(result){
				var shiftList = Mes.getJsonSync2("/plan/dubbo/getMdmList/index.do","mdmType=SHIFT");
				result.data.shiftList = shiftList;
				var html = template('detailTab.tmp', result.data);
				$('#detailTab').html(html);
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
	
 
 