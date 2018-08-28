//针对jQuery发出的ajax进行全局设置

var _basePath = "/mes-plan"; 
/*$.ajaxSetup({
	headers:{"ajax":"ajax"},
	contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	beforeSend:function(){
		//由于全部为ajax请求，对于页面加载过程中通过URL传递的search参数不能正常使用，在此处占用hash位进行处理
		if(this.url.indexOf(".do") != -1 || this.url.indexOf(".html") != -1){
			var params = this.url.substring(this.url.indexOf("?")+1 , this.url.length);
			if(this.data){
				params +="&"+this.data;
			}
			if(params && "" != $.trim(params)){
				window.location.hash = $.trim(params);
			}
		}
	},
	error:function(request, status, error){
		//alertMsg.error("与服务器交互发生错误！");
		alert("与服务器交互发生错误！");
	}
});
*/

var layerIndex = -1;//layer loading弹出层代码
var latCnt = 0;//ajax服务个数

var userDeptCode="";
var userDeptName="";
var loginUser="";
var loginUserName="";
var loginDataScope="";
var loginDataScopeName="";
var CacheData={};
const ARRANGEGROUPLIST = [{code:'JP14',name:'验布'},{code:'JP15',name:'分等分段'},{code:'JP16',name:'拼件'},{code:'JP17',name:'卷布'},{code:'JP18',name:'打包'}];
const WORKSHOP_ARRANGE='C14';
const WORKCENTER_PACKAGE='W17';
const  PAGESIZE  = 10;
const PAGETEMPLATE = "<nav style='text-align:right' aria-label=\"Page navigation\">"
+"<ul class=\"pagination\">"
+"<li><span style=\"color:#6d6e70\">显示第 {{startRow}} 项至 {{endRow}} 项结果,共 {{total}} 项</span></li>"
+"<li><span style=\"padding-top:5px;padding-bottom:4.5px;color:#6d6e70\">"
+"			每页<select type=\"text\" value=5 style=\"width:60px;text-align:center\" onchange=\"searchOrder(false,1,$(this).val(),'{{qryParam}}'); return;\">"
+"					<option value=\"10\" {{if(pageSize==10)}}selected {{/if}}>10</option>"
+"					<option value=\"25\" {{if(pageSize==25)}}selected {{/if}}>25</option>"
+"					<option value=\"50\" {{if(pageSize==50)}}selected {{/if}}>50</option>"
+"					<option value=\"100\" {{if(pageSize==100)}}selected {{/if}}>100</option>"
+"				</select>条数据</span></li>"
+"	<li {{if !hasPreviousPage}} class='disabled'{{/if}}>"
+"<a href='#' onclick=\"if(!$(this).parent().hasClass('disabled')) searchOrder(false,1,{{pageSize}},'{{qryParam}}'); return;\">	<span  aria-hidden=\"true\">首页</span></a>"
+"	</li>"
+"	<li {{if !hasPreviousPage}} class='disabled'{{/if}}> <a href='#' onclick=\"if(!$(this).parent().hasClass('disabled')) searchOrder(false,{{prePage}},{{pageSize}},'{{qryParam}}'); return;\"> <span  aria-hidden=\"true\">上页</span></a></li>"
+"<% for(var i = firstPage; i <= lastPage && i!=0; i++){ %>"
+"<li {{if pageNum == i}} class='active' {{/if}}> <a href='#' onclick=\"searchOrder(false,<%= i %>,{{pageSize}},'{{qryParam}}'); return;\"> <span><%= i %></span></a></li>"
+"<% }  %>"
+"	<li {{if !hasNextPage}} class='disabled' {{/if}}> <a href='#' onclick=\"debugger ;if(!$(this).parent().hasClass('disabled')) searchOrder(false,{{nextPage}},{{pageSize}},'{{qryParam}}'); return;\"> <span  aria-hidden=\"true\">下页</span></a></li>"
+"	<li {{if !hasNextPage}} class='disabled' {{/if}}>"
+ " 		<a href=\"#\" aria-label=\"Next\" onclick=\"if(!$(this).parent().hasClass('disabled')) searchOrder(false,{{pages}},{{pageSize}},'{{qryParam}}'); return;\">"
+ "   	<span  aria-hidden=\"true\">尾页</span>"
+ " 		</a>"
+"	</li>"

+"</ul>"
+"</nav>";

const PAGETEMPLATEPOPUP = 
"<div id='popupDivTop' style='padding:10px 5px'>"
+" <form id ='popupForm' class='form-horizontal' >  " 
+"编码<input type='text' name='code' style='height:28px;margin:0px 5px'/> 名称 <input type='text' name='name' style='height:28px;margin:0px 5px'/> "
+"<button class='btn btn-primary' style='margin-left:5px' onclick='{{onPage}}(true,1,PAGESIZE,\"\",true);return false;'><span class='glyphicon glyphicon-search'></span>查询</button>"
+"<button class='btn' type='reset' style='margin-left:5px'><span class='glyphicon glyphicon-search'></span>清空</button>"
+"</form>"
+"</div>";


const PAGETEMPLATEPOPUPTAB = 
"<table id='popupTab'  class='table table-condensed table-bordered'>"
+"<thead>"
	+"<tr>"
	+"<th class='thcls'></th>"
    +"	{{each cols as listValue2}}"
	+"		<th class='thcls'>{{listValue2.colName}}</th>"
	+"	{{/each}}"
	+"</tr>"
    +"</thead>"
+"<tbody>"
	+"{{each list as listValue index}}"
		+"<tr>"
		+"<td><input type='checkbox' name='selectCheckBox' onclick='onCheckBoxClick(this)'/> <input type='hidden'/ name='rowIndex' value='{{index+1}}'></td>"
		+"{{each cols as listValue2}}"
		+"	<td><span name='{{listValue2.colCode}}'>{{listValue[listValue2.colCode]}}</span></td>"
		+"{{/each}}"	
		+" </tr>"
	+"{{/each}}"
+"</tbody>"
+"</table>"
+"<nav style='text-align:right' aria-label=\"Page navigation\">"
+"<ul class=\"pagination\">"
+"<li><span style=\"color:#6d6e70\">显示第 {{startRow}} 项至{{endRow}}项结果,共 {{total}} 项</span></li>"
+"<li><span style=\"padding-top:5px;padding-bottom:4px;color:#6d6e70\">"
+"			每页<select type=\"text\" value=5 style=\"width:60px;text-align:center\" onchange=\"{{onPage}}(false,1,$(this).val(),'{{qryParam}}',true); return;\">"
+"					<option value=\"10\" {{if(pageSize==10)}}selected {{/if}}>10</option>"
+"					<option value=\"25\" {{if(pageSize==25)}}selected {{/if}}>25</option>"
+"					<option value=\"50\" {{if(pageSize==50)}}selected {{/if}}>50</option>"
+"					<option value=\"100\" {{if(pageSize==100)}}selected {{/if}}>100</option>"
+"				</select>条数据</span></li>"
+"	<li {{if !hasPreviousPage}} class='disabled'{{/if}}>"
+"<a href='#' onclick=\"if(!$(this).parent().hasClass('disabled')) {{onPage}}(false,1,{{pageSize}},'{{qryParam}}',true); return;\">	<span  aria-hidden=\"true\">首页</span></a>"
+"	</li>"
+"	<li {{if !hasPreviousPage}} class='disabled'{{/if}}> <a href='#' onclick=\"if(!$(this).parent().hasClass('disabled')) {{onPage}}(false,{{prePage}},{{pageSize}},'{{qryParam}}',true); return;\"> <span  aria-hidden=\"true\">上页</span></a></li>"
//+"<% for(var i = firstPage; i <= lastPage && i!=0; i++){ %>"
//+"<li {{if pageNum == i}} class='active' {{/if}}> <a href='#' onclick=\"{{onPage}}(false,<%= i %>,{{pageSize}},'{{qryParam}}',true); return;\"> <span><%= i %></span></a></li>"
//+"<% }%>"
+"	<li {{if !hasNextPage}} class='disabled' {{/if}}> <a href='#' onclick=\"debugger ;if(!$(this).parent().hasClass('disabled')) {{onPage}}(false,{{nextPage}},{{pageSize}},'{{qryParam}}',true); return;\"> <span  aria-hidden=\"true\">下页</span></a></li>"
+"	<li {{if !hasNextPage}} class='disabled' {{/if}}>"
+ " 		<a href=\"#\" aria-label=\"Next\" onclick=\"if(!$(this).parent().hasClass('disabled')) {{onPage}}(false,{{pages}},{{pageSize}},'{{qryParam}}',true); return;\">"
+ "   	<span  aria-hidden=\"true\">尾页</span>"
+ " 		</a>"
+"	</li>"

+"</ul>"
+"</nav>";


const FINISH_TEMPLATE= "<div id='finishWork_div' style='margin:8px'> "
+"完工说明:&nbsp;&nbsp;<textarea id='remarksTextarea' style='height:90px;overflow-x:hidden' cols='40' maxlength=100 placeholder='限100汉字'></textarea>"
+"</div>"

function rapRangeUrl(argUrl){
	if (!userDeptCode)  {
		return argUrl;
	}
	// var data ={'1':'所有数据','2':'所有数据','3':'所有数据','4':"部门数据",'5':"部门数据",'8':'个人数据','9':'按明细设置'};
	 if (loginDataScope == "1" || loginDataScope == "2" || loginDataScope == "3"){
		 return argUrl;
	 }
	if (loginDataScope == "4" || loginDataScope == "5" ){
		if (!argUrl){ return "&workshopId="+userDeptCode}
		var reg = new RegExp("(^|&)workshopId=([^&]*)(&|$)");
		var r = argUrl.match(reg);
		//修改中文url乱码
		var urlValue;
		if (r!=null) {
			urlValue = unescape(r[2]);
			return argUrl.replace("workshopId="+urlValue,"workshopId="+userDeptCode);
		}else{
			argUrl =argUrl.replace("workshopId=",'');
			return argUrl+"&workshopId="+userDeptCode;
		}
	}else if (loginDataScope == "8"){
		if (!argUrl){ return "&createUser="+loginUser}
		var reg = new RegExp("(^|&)createUser=([^&]*)(&|$)");
		var r = argUrl.match(reg);
		//修改中文url乱码
		var urlValue;
		if (r!=null) {
			urlValue = unescape(r[2]);
			return argUrl.replace("createUser="+urlValue,"createUser="+loginUser);
		}else{
			argUrl =argUrl.replace("createUser=",'');
			return argUrl+"&createUser="+loginUser;
		}
	}
}

var Mes = {
		//使用ajax的方式加载远程数据
		loadScript:function(argUrl,reqDatas,callback){
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT;
			reqDatas+="&_dt=script&_cb="+callback;
			jQuery.ajax({ url:url, type:"POST", async:true, dataType:"script",data:reqDatas});
		},
		loadJson:function(argUrl,reqDatas,succCallBack,errorCallback){
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT;
			reqDatas+="&_dt=json";
			jQuery.ajax({ url:url, type:"POST", async:true, dataType:"json",data:reqDatas,success:succCallBack,error:errorCallback});
		},
		
		getJson2:function(argUrl,urlData,succCallBack,errorCallback){
			//urlData = rapRangeUrl(urlData);
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url,contentType:"application/json",async:true, dataType:"json",
				success:function (result){
						if (!!result && result.succ)
						{
							succCallBack(result)
						}
						else if(!!result &&result.msg){
							layer.alert(result.msg);
							}
						else{
							alert("没有返回数据")
						}
					},
				error: function (msg){
					
					if (errorCallback){
						errorCallback(msg);
					}else{
						alert(msg.responseText|msg.statusText);
					}
					}
					});
		},
		
//		getCmdb:function(argUrl,urlData,succCallBack,errorCallback){
//			radomT="?_v="+(new Date()).getTime();
//			var url="http://127.0.0.1:8080/mes-plan/plan/common/getcmdb/index.do"+argUrl+radomT+"&"+urlData;
//			jQuery.ajax({ url:url,async:true,contentType:"application/json",dataType:"json",
//				success:function (result){
//					if (!!result && result.succ)
//					{
//						try{
//							var jsondata = $.parseJSON( result.data );
//	//						var rows = $.parseJSON( jsondata.rows );
//	//						jsondata.rows=rows;
//							jsondata.pageSize = jsondata["pager.pageSize"];
//							jsondata.totalRows = jsondata["pager.totalRows"];
//							jsondata.pageNo = jsondata["pager.pageNo"];
//							
//							result.data = jsondata;
//							
//							succCallBack(result);
//						}catch(error){
//							alert('调用cmdb出错,请检查cmdb是否正常提供服务!');
//						}finally{}
//					}
//					else if(!!result &&result.msg){
//							alert(result.msg)
//						}
//					else{
//						alert("没有返回数据")
//					}
//				},
//			error: function (msg){alert(msg.responseText|msg.statusText);errorCallback(msg);}});
//		},
		
		
		/*getJsonSync:function(argUrl,urlData,succCallBack,errorCallback){
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url,contentType:"application/json",async:false, dataType:"json",
				success:function (result){
						if (!!result && result.succ)
						{
							succCallBack(result)
						}
						else if(!!result &&result.msg){
								alert(result.msg)
							}
						else{
							alert("没有返回数据")
						}
					},
				error: function (msg){alert(msg.responseText|msg.statusText);errorCallback(msg);}});
		},*/
		
		getJsonSync:function(argUrl,urlData){
			var syncResult = {};
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url,contentType:"application/json",async:false, dataType:"json",
				success:function (result){
						if (!!result && result.succ)
						{
							syncResult = result.data;
						}
						else if(!!result &&result.msg){
								alert(result.msg)
							}
						else{
							alert("没有返回数据")
						}
					},
				error: function (msg){alert(msg.responseText|msg.statusText);errorCallback(msg);}});
			return syncResult;
		},
		
		getJsonDeferred:function(argUrl,urlData){
			 var defer = $.Deferred();
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url,contentType:"application/json",async:true, dataType:"json",
				success:function (result){
						if (!!result && result.succ)
						{
							defer.resolve(result.data);
						}
						else if(!!result &&result.msg){
								alert(result.msg);
								defer.reject() ;
							}
						else{
							alert("没有返回数据");
							defer.reject() ;
						}
					},
				error: function (msg){defer.reject();alert(msg.responseText|msg.statusText);errorCallback(msg);}});
			return defer.promise();
		},
		
		getJsonDeferred2:function(argUrl,urlData){
			 var defer = $.Deferred();
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url,contentType:"application/json",async:true, dataType:"json",
				success:function (result){
						if (!!result && result.succ)
						{
							defer.resolve(JSON.parse(result.data));
						}
						else if(!!result &&result.msg){
								alert(result.msg);
								defer.reject() ;
							}
						else{
							alert("没有返回数据");
							deferred.reject();
						}
					},
				error: function (msg){deferred.reject();alert(msg.responseText|msg.statusText);errorCallback(msg);}});
			return defer.promise();
		},
		
		getJsonSync2:function(argUrl,urlData){
			var syncResult = Mes.getJsonSync(argUrl,urlData);
			if (syncResult){
				return JSON.parse(syncResult);
			}else
			{
				return "";
			}
		},
		getJsonSyncAllData:function(argUrl,urlData){
			var syncResult = {};
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url,contentType:"application/json",async:false, dataType:"json",
				success:function (result){
						if (!!result && result.succ)
						{
							syncResult = result.data;
						}
						else if(!!result &&result.msg){
								alert(result.msg)
							}
						else{
							alert("没有返回数据")
						}
					},
				error: function (msg){alert(msg.responseText|msg.statusText);}});
			return syncResult;
		},
		
		loadJson2:function(argUrl,urlData,jsonData,succCallBack,errorCallback){
			//urlData = rapRangeUrl(urlData);
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT+"&"+urlData;
			jQuery.ajax({ url:url, type:"POST", contentType:"application/json",async:true, dataType:"json",data:jsonData,
				success:function (result){
						if (!!result && result.succ)
						{
							succCallBack(result)
						}
						else if(!!result &&result.msg){
								alert(result.msg)
							}
						else{
							alert("没有返回数据")
						}
					},
				error: function (msg){alert(msg.responseText|msg.statusText);errorCallback(msg);}});
		},
		
		loadModal:function(){ 
			 jQuery("<img src='"+_basePath+"/images/logo.jpg'>").modal({
                escClose:false,  
                close:true,
                overlayCss: {backgroundColor:"dimgrey"},
                zIndex:"9999999"
            });
        },
        closeModal:function(){
        	jQuery.modal.close();
        },
        //处理数据，加载jstemplate模板
		processTemplateData: function(show,templateId,data){
            jQuery(show).setTemplateElement(templateId).processTemplate(data); 
		},
		displayPager:function(continer,currPageNo,pageCount,clickHandler){
			$(continer).pager({ pagenumber: currPageNo, pagecount: pageCount, buttonClickCallback: clickHandler });
		},
		//获取url参数中指定名称的取值
        getUrlParam:function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			//修改中文url乱码
			if (r!=null) return unescape(decodeURI(r[2])); return null;
		} ,
		getHashParam : function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.hash.substr(1).match(reg);
			if (r!=null) return unescape(r[2]); return null;
		},
		//将key=val&key=val&...转为json对象
		params2Json : function(data){
			var ret = {};
		    data.substr(1).replace(/([\w.]+)=([\w.]+)/ig, function(a, b, c){ret[b] = unescape(c);console.log("a="+a+";b="+b+";c="+c)});  
		    return ret; 
		},
		//将url中的参数转为json对象
		getUrlParamsJson : function(){
			return Mes.params2Json(window.location.search);
		},
		getHashParamsJson : function(){
			return Mes.params2Json(window.location.hash);
		},
		/**
		 * 使用正则表达式验证是否匹配规则
		 * @param regexp ： 正则表达式
		 * @param data ： 待验证的数据
		 * @return true / false
		 */
		regexpTest : function(regexp , data){
			return regexp.test(data);
		},
		regexp : {
			mobile : /1[3|5|7|8|][0-9]{9}/ , 
			email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
		},
		
		form2Json:function(fromid){
			var textList = $(fromid).find('input');	
			var selectList = $(fromid).find('select');
			var queryText="";
			var errorMeg = "";
			$.each(textList,function(i,item){
				if (item && item.name)
				{
					if (item.value != "") 
					{
						queryText= queryText + "&" +item.name+"=" + item.value;
					}/*else if (item.hasClassName("requiredCus")){
						errorMeg = "参数【"+item.parentElement.previous().innerText+"】不能为空!";
					}*/
				}
				  
			});
			if (errorMeg) {
				alert(errorMeg);
				return false;
			}
			$.each(selectList,function(i,item){
				if (item && !item.name)
				{
					if (item.value != "") 
					{
						queryText= queryText + "&" +item.name+"=" + item.value;
					}/*else if (item.hasClassName("requiredCus")){
						errorMeg = "参数【"+item.parentElement.previous().innerText.replace('*','')+"】不能为空!";
					}*/
				}
				  
			});
			if (errorMeg) {
				alert(errorMeg);
				return false;
			}
			if (queryText){
				queryText= encodeURI(queryText);
			}
			alert(queryText);
			return false;
			
		},
		
		
		//加载框
		sysLoading:function sysLoading(msg,loadTime) {
		    if (typeof (layer) != 'undefined') {
		        //layer.closeAll();
		        if (msg == undefined || msg == null || msg == '') {
		            msg = '加载中...'
		        }
		        layer.msg(msg, { icon: 16, time: loadTime, shade: 0.1 });
		    }
		},

		//提示框
		sysAlert:function sysAlert(msg, fun) {
		    if (typeof (layer) != 'undefined') {
		        layer.closeAll();
		        if (typeof fun == 'function') {
		            layer.alert(msg,{closeBtn: 0}, fun);
		        } else {
		            layer.alert(msg,{closeBtn: 0});
		        }
		    }
		},

		//确认框
		sysConfirm:function sysConfirm(msg, fun) {
		    if (typeof (layer) != 'undefined') {
		        layer.closeAll();
		        if (typeof fun == 'function') {
		            layer.confirm(msg, fun);
		        }
		    }
		},
		//关闭弹出框
		closeAlert:function closeAlert() {
		    if (typeof (layer) != 'undefined') {
		        layer.closeAll();
		    }
		}
		
};

/**
 * 扩展JQuery表单数据搜集 将一个表单的数据返回成JSON对象
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name]
							.push(this.value === 0 ? 0 : (this.value || ''));
				} else {
					o[this.name] = this.value === 0 ? 0 : (this.value || '')
				}
			});
	return o;
};
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt))
fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o){
    if (new RegExp("(" + k + ")").test(fmt)) {
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
}
    }
    return fmt;
};

function getLoginDataName(loginDataScope){
//	1=所有数据
//	2=所在公司及以下数据
//	3=所在公司数据
//	4=所在部门及以下数据
//	5=所在部门数据
//	8=仅本人数据
//	9=按明细设置
	var data ={'1':'所有数据','2':'所有数据','3':'所有数据','4':userDeptName,'5':userDeptName,'8':'个人数据','9':'按明细设置'};
	return data[loginDataScope];
};

function setDataScope(result){
	if (!!result && result.succ)
	{
		//loginUser = result.data.user.no;
		loginUser = result.data.user.loginName;
		loginUserName = result.data.user.name;
		userDeptCode = result.data.user.pOfficeCode;
		userDeptName = result.data.user.pOffice;
		//得到最大角色的数据权限
		if (result.data.permission){
			$.each(result.data.permission.roles,function(index,item){
				if (item.dataScope < loginDataScope || loginDataScope ==""){
					loginDataScope = item.dataScope;
				}
			});
		}
		loginDataScopeName = getLoginDataName(loginDataScope);
		//alert(loginDataScopeName);
		$.ajaxSetup({
			//layerIndex:-1,
			beforeSend:function(){
				//this.layerIndex = layer.load("");
				 //alert(this.url);
				if (layerIndex == -1){
					var contentData = '<span style="margin-left:-50px"><strong>数据范围['+loginDataScopeName+']</strong></span>';
					//var contentData2 = '<span style="margin-left:-50px"><strong>加载中</strong></span>';
					layerIndex= layer.load(2,{content:contentData,time: 10*1000,success: function(layero){
						layero.find('.layui-layer-content').css('padding-top', '40px');
						layero.find('.layui-layer-content').css('width', '100px');
						}});
				}
				latCnt++;
				//alert("cntstart:"+latCnt);
			},
			complete: function () {
				window.setTimeout(function(){
					latCnt--;
					//alert("cntend:"+latCnt);
					if (latCnt == 0){
						layer.close(layerIndex);
						layerIndex = -1;
					}
				},500);
			},
			error:function(request, status, error){
				//alertMsg.error("与服务器交互发生错误！");
				layer.alert('部分数据加载失败，可能会导致页面显示异常，请刷新后重试', {
					skin: 'layui-layer-molv'
				   , closeBtn: 0
				   , shift: 4 //动画类型
				});
			}
		});
	}
	else if(!!result &&result.msg){
		alert("取登录信息出错"+result.msg);
		}
	else{
		alert("没有返回数据");
	}
}
// 页面初始化加载
$(document).ready(function() {
	$(".rightmenulink a").click(function(e){
		$(".rightmenu").addClass("rightmenushow");
		var v_id = $(e.target).attr('id');
		$("a.top").css("right","200px");
	})
	$(".topmenu a").click(function(){
		$(".topmenu a").removeClass("show");
		$(this).addClass("show");
	})
	$("frame").load(function () {
        var mainheight = $(this).contents().find("body").height();
        $(this).height(mainheight);
        $(this).contents().click(function () {
            $(parent.document).trigger('click');
        });
    });
	$(document).bind("click",function(e){ 
		var target = $(e.target); 
		if(target.closest(".rightmenu").length == 0){ 
			$(".rightmenu").removeClass("rightmenushow");
			$("a.top").css("right","0px");
		} 
	});
	
	//添加模板函数
	template.defaults.imports.str2DateAdd = function(value,intDays) {
		var date = new Date(value.replace(/-/,'/').replace(/-/,'/'));
		if (intDays>0){
			date.setDate(date.getDate()+intDays)
		}
	    return date.Format("yyyy-MM-dd");
	};
	
	template.defaults.imports.str2DateSub = function(value,intDays) {
		var date = new Date(value.replace(/-/,'/').replace(/-/,'/'));
		if (intDays>0){
			date.setDate(date.getDate()-intDays)
		}
	    return date.Format("yyyy-MM-dd");
	};
	
	
	template.defaults.imports.avgQty = function(value,beginDate,endDate) {
		var date1 = new Date(beginDate.replace(/-/,'/').replace(/-/,'/'));
		var date2 = new Date(endDate.replace(/-/,'/').replace(/-/,'/'));
		var dayCnt = (date2 -date1)/3600/24/1000 +1; 
		 
	    return  Math.round(value/dayCnt);
	};
	
	
	template.defaults.imports.getDdwName = function(valueIn,list) {
		var rtn;
		$.each(list,function(n,loopValue) {   
	            if (loopValue.id == valueIn)   {
	            	rtn = loopValue.text;
	            	return false;
	            }  
	           });  
		return rtn || valueIn;  
	};
	
	
	template.defaults.imports.getDdwNameList = function(valueIn,list) {
		var rtn='';
		var valueInArr = valueIn.split(',');
		
		$.each(valueInArr,function(index,itemValue){
			$.each(list,function(n,loopValue) {   
	            if (loopValue.id == itemValue)   {
	            	rtn = rtn + loopValue.text+',';
	            	return false;
	            }  
	           }); 
			
		});
		 if (rtn.length){
			 rtn= rtn.substr(0,rtn.length-1);
		 }
		return rtn ;  
	};
	
	
	
	
	
	template.defaults.imports.getMdmName = function(valueIn,list) { 
		var rtn;
		$.each(list,function(n,loopValue) {   
	            if (loopValue.code== valueIn)   {
	            	rtn = loopValue.name;
	            	return false;
	            }  
	           });  
		return rtn || valueIn;  
	};
	
//	template.defaults.imports.toFixed = function(valueIn,scaleValue) {
//		debugger
//		var rtn = valueIn || 0.00;
//		return parseFloat(rtn).toFixed(scaleValue);  
//	};
	template.defaults.imports.workshop3 = WORKSHOP_ARRANGE;
	
	
	
	jQuery.ajax(
			{ url:"/mes-plan/plan/mesPlanMonth/getPermissionInfo/index.do", 
				type:"POST", 
				async:false, 
				dataType:"json",
				success:function (result){
					//设置数据范围
					setDataScope(result);
					customReady();
				},
				error:function(data){
					//alert("取员工登录信息出错");
					//$('#permissionFrame').attr('src','/mes-plan/plan/mesPlanMonth/getPermissionInfo/index.do');
					var iframeError = document.createElement("iframe");
					iframeError.src = "/mes-plan/plan/mesPlanMonth/getPermissionInfo/index.do";
					iframeError.style="display:none";
					iframeError.onload=function(){
						var jsonStr = iframeError.contentWindow.document.body.innerText;
						
						var result = JSON.parse(jsonStr);
						 
						setDataScope(result);
						customReady();
					};
					document.body.appendChild(iframeError);
				}
		});
	 
	
	
});

function ddwClearOption(objSelect,canNull){
	if (canNull){
		objSelect.html("<option value=''></option>");
	}else{
		objSelect.html("");
	}
}

function ddwAddOption(objSelect,listValue){
	for(var i=0;i<listValue.length;i++)
	{
		//objSelect.options.add(new Option(listValue.text,listValue.id));
		objSelect.append("<option value='"+listValue[i].id+"'>"+listValue[i].text+"</option>"); 
	} 
};

function ddwAddOptionCodeName(objSelect,listValue){
	for(var i=0;i<listValue.length;i++)
	{
		//objSelect.options.add(new Option(listValue.text,listValue.id));
		objSelect.append("<option value='"+listValue[i].code+"'>"+listValue[i].name+"</option>"); 
	} 
};

 
function getCommaStr(listValue,colName){
	var rtn='';
	if (listValue && listValue.length){
		for(var i=0;i<listValue.length;i++)
		{
			rtn = rtn +"'"+listValue[i][colName]+"',"
		} 
	}
	if (rtn.length >0){
		rtn = rtn.substr(0,rtn.length-1);
	}
	return rtn||"''";
}
/**
 * 弹出框使用
 * @param sender
 */
function onCheckBoxClick(sender){
	if($(sender).prop('checked')){
		$(sender).parent().parent().parent().find("tr").each(function(){
		    if ($(this).find("[name=rowIndex]").val() != $(sender).next().val()){
		    	$(this).find("[name=selectCheckBox]").prop('checked',false);
		    }
		  });
	} 
 };
 
 
function changeCode2Name(workshopList){
	var rtn=[];
	for (i=0 ; i< workshopList.length;i++ ){
		rtn.push({'id':workshopList[i].code,'text':workshopList[i].name});
	}
	return rtn;
};


function changeCode2Name2(workshopList){
	var rtn=[];
	for (i=0 ; i< workshopList.length;i++ ){
		rtn.push({'id':workshopList[i].code,'text':'['+workshopList[i].code+']'+workshopList[i].name});
	}
	return rtn;
};


function onClickPopup(isFirQry,pageNo,pageSize,lastPara,isOpened,colsList,methodName,mdmType,callback){
	var param ;
 	if (isFirQry){
 		param = $('#popupForm').serialize();
 		if (param){
 			param = param+"&mdmType="+mdmType;
 		}else{
 			param = "mdmType="+mdmType;
 		}
 	}else{
 		//if (!lastPara) {alert('未设置分页参数!');return;}
 		param = lastPara;
 	}
 	Mes.loadJson2("/plan/dubbo/getQueryPage/"+pageNo+"/"+pageSize+"/index.do",param,"{}",		
 		function(result){ 
 			result.data = JSON.parse(result.data);
 			result.data.qryParam = param;
 			//result.data.dropdownDatas =  dropdownDatas;
 			//result.data.cols ="";
 			//result.data.onPage ="";
 			result.data.cols = colsList;
 			result.data.onPage = methodName;
         	//$('#detailTab').html(html+subhtml);
 			if (!isOpened){
 				var render = template.compile(PAGETEMPLATEPOPUP);  
     			var subhtml = render(result.data);  
     			
     			var render = template.compile(PAGETEMPLATEPOPUPTAB);  
     			var subhtml2 = render(result.data);  
     			layer.open({
     				 type: 1,
     				 title :'查询',
     				 area: ['48em', '26em'],
     				 content: subhtml+"<div id='popupDiv' style='padding:10px 5px'>"+subhtml2+'</div>',
     				 btn: ['确认', '返回'],
     				 btn1: callback,
     				 btn2: function(index, layero){
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
 };
