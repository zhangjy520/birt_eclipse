/**
 * select2插件简单的封装
 * 
 * 
 * */

var select2 = select2||{};

//进行绑定
select2.bindSelect = function(ctrName){
	var selector = $("."+ctrName);
		selector.select2({
			placeholder:"请选择",
			language: "zh-CN",
			 tags: true,            //可以手动添加，若限制手动添加，设置为false
			 allowClear: true
		});	
}

//绑定下拉框到指定的控件上--getJson 数据绑定
select2.bindSelect = function(ctrName,url){
	var selector = $("."+ctrName);
		//设置selet2的处理  简单的配置参数
		selector.select2({
			 placeholder:"请选择",
			 language: "zh-CN",
		     allowClear: true,//是否允许清除图标
		});
		//绑定getJson的内容
		$.getJSON(url,function(data){
			selector.empty();//清空下拉框
			$.each(data,function(i,item){//循环绑定
				selector.append("<option value='" + item.value + "'>&nbsp;" + item.text + "</option>");
			});
		});
}

//允许选择多个值
select2.bindSelectMultiple = function(ctrName){
	var selector = $("."+ctrName);
		selector.select2({
			multiple:true,
			placeholder:"请选择",
			language: "zh-CN",
			 tags: true            //可以手动添加，若限制手动添加，设置为false
		});
}

//允许选择多个值--getJson请求
select2.bindSelectMultiple = function(ctrName,url){
	var selector = $("."+ctrName);
		selector.select2({
			multiple:true,
			placeholder:"请选择",
			closeOnSelect:false,//是否收起下拉框
			language: "zh-CN",
			maximumInputLength:100,//限制最大字符
		});
		$.getJSON(url,function(data){
			selector.empty();//清空下拉框
			$.each(data,function(i,item){//循环绑定
				selector.append("<option value='" + item.value + "'>&nbsp;" + item.text + "</option>");
			});
		});
}

//使用 内置ajax对象 请求查询 数据 并返回
select2.bindSelectAjax = function(){
	var selector = $("."+ctrName);
		selector.select2({
		   ajax: {
		        type:'POST',
		        url: url,
		        dataType: 'json',
		       // contentType:'application/json;charset=UTF-8',
		        delay: 250,	
		        data: function (params) {
		          return {
		            q: params.term, // search term 请求参数  后台获取request获取查询输入的参数 
		            page: params.page
		          };
		        },
		        processResults: function (data, params) {
		          params.page = params.page || 1;
		         /* var itemList = [];//当数据对象不是{id:0,text:'ANTS'}这种形式的时候，可以使用类似此方法创建新的数组对象
		          var arr = data.result
		          for(item in arr){
		              itemList.push({id: item, text: arr[item]})
		          }*/
		          return {
		            results: data,//itemList 后台返回的数据集
		            pagination: {//分页
		              more: (params.page * 30) < data.total_count
		            }
		          };
		        },
		        cache: true
		      },
		      placeholder:'请选择',//默认文字提示
		      language: "zh-CN",
		      tags: true,//允许手动添加
		      allowClear: true,//允许清空
		      escapeMarkup: function (markup) { return markup; }, // 自定义格式化防止xss注入
		      minimumInputLength: 1,
		      templateResult: function formatRepo(repo){
		    	  if (repo.loading)
		    		  return repo.text; 
		      repo.text = repo.text  
		      repo.id = repo.id  
		      var markup = "<div>" + repo.text + "</div>";  
		      return markup;
		      }, // 函数用来渲染结果
		      templateSelection: function formatRepoSelection(repo){
		    	  repo.selected = true;   
		    	    repo.code = repo.id  
		    	    repo.name = repo.text  
		    	    if(repo.code == null || repo.code == ""){  
		    	        repo.text = '请选择'  
		    	        repo.name = repo.text  
		    	    }  
		    	    $(ctrName).val(repo.name);  
		    	    console.debug(repo);  
		    	    return repo.code ;  
		      } // 函数用于呈现当前的选择
		});
}

//ajax请求后并设置默认选中的值  
select2.setDefault = function(ctrName,defaultValue){
	var selector = $("."+ctrName);
	//设置selet2的处理  简单的配置参数
	var  $getSelect = selector.select2({
		 language: "zh-CN"
	   });
	$getSelect.val(defaultValue).trigger("change");
}

//使用数组  数组格式id和text属性是必需的每个对象 进行加载 data 为js定义的一个数组
select2.arrayDataLoad = function(ctrName,data){
	$("."+ctrName).select2({
		data:data,
		language: "zh-CN",
		placeholder:"请选择",
	});
}

//获取单行选中值 并返回一条数据对象	
select2.getSelectValue = function(ctrName) {
	return $("."+ctrName).select2("data")[0];
}

//获取多行选中值 并返回数组对象
select2.getSelectMulti = function(ctrName){
	return $("."+ctrName).select2("data");
}