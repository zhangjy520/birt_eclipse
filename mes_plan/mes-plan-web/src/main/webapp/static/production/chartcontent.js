		//  固定配置项
		this.option={
			           title : {
					        subtext: '',
					        x:'center'
						    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					    },
					    series : [
					        {
					            type: 'pie',
					            radius : '55%',
					            center: ['47%', '46%'],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor:'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
		 };
		
         //加载初始信息--ajax
		    function loadInitInfo(){
		    	$.ajax({
		    		url:'/mes-plan/production/data/data.txt',//暂时使用本地的数据
		    		type:'get',
		    		cache:true,
		    		datatype:'text',
		    		success:setClickInfo,
		    		error:function(response){
		    			alert("获取数据失败,原因:"+response.status);
		    		}
		    	});
		    }
            // 基于准备好的dom，初始化echarts实例
        	var myChart = echarts.init(document.getElementById('cost'));
            var obj =this.option;
             myChart.setOption(obj);
             
             var myChart1 = echarts.init(document.getElementById('weight'));
             var obj =this.option;
             myChart1.setOption(obj);
             
              var myChart2 = echarts.init(document.getElementById('volume'));
             var obj =this.option;
             myChart2.setOption(obj);
		   
		    //异步加载 设置回调函数 --图1
		    function setClickInfo(data){
	             var dataObj=eval("("+data+")"); //转换为json对象 
		           // 重新填充数据
				   myChart.setOption({
					   	title:{
					   		  text: '按成本',
					   	},
				   	  legend: {
                            data:dataObj.legendata
                      },
				       series: [{
				           	name: '成本:',
				            data:dataObj.seriesdata
				       }]
				    });
				    
				      myChart1.setOption({
					   	title:{
					   		  text: '按重量',
					   	},
				   	  legend: {
                            data:dataObj.legendata
                      },
				       series: [{
				           	name: '重量:',
				            data:dataObj.seriesdata
				       }]
				    });
				    
				      myChart2.setOption({
					   	title:{
					   		  text: '按体积',
					   	},
				   	  legend: {
                            data:dataObj.legendata
                      },
				       series: [{
				           	name: '体积:',
				            data:dataObj.seriesdata
				       }]
				    });
				    
		   }