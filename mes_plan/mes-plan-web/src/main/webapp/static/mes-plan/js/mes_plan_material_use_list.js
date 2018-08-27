var dropdownDatas = {};
function customReady() {
	var urlPara = "types=use_sheet_status,use_type";
	dropdownDatas = Mes.getJsonSync("/plan/common/getdicts/index.do",urlPara);
	
	$("[name='status']").select2({
		data: dropdownDatas.use_sheet_status
	});
	$("[name='useType']").select2({
		data: dropdownDatas.use_type
	});
//	Mes.getJsonSync("/plan/common/getdicts/index.do",urlPara,		
//	function(result){
//		dropdownDatas = result.data;
//		$("[name='planStatus']").select2({
//			data: result.data.plan_order_status
//		});
//		$("[name='billType']").select2({
//			data: result.data.plan_order_type
//		});
//	},
//	function(data){
//	});
	
	$('#useDate1').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "zh-CN",//设置语言
	     autoclose: true//设置是否关闭
	});
	
	
	$('#useDate2').datepicker({
	    format: 'yyyy-mm-dd',
	    language: "zh-CN",//设置语言
	     autoclose: true//设置是否关闭
	});
	
	
	searchOrder(true,1,PAGESIZE);
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
    	Mes.loadJson2("/plan/materialUseHead/findPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
    		function(result){
    			debugger
    			result.data.qryParam = param;
    			result.data.use_sheet_status = dropdownDatas.use_sheet_status;
    			result.data.use_type = dropdownDatas.use_type;
    			var html = template('detailTab.tmp', result.data);
    			var render = template.compile(PAGETEMPLATE);  
    			var subhtml = render(result.data);  
            	$('#detailTab').html(html+subhtml);
	    		//alert(JSON.stringify(result.data))
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
     function onClickUser(){
    	 layer.open({
    		  type: 2, 
    		  title:'查找客户',
    		  content: '/mes-plan/production/production_plan_add.html' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
    		}); 
     };
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
     }