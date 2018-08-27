package com.bmsmart.mes.platform;

import java.util.HashMap;
import java.util.Map;


import com.bmsmart.mes.base.util.httpclient.HttpCommandProvider;



public class PlatformRestClient {

	public static void main(String[] args) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
    	map.put("type", "color");
		String url="http://10.0.43.42:9080/im_platform/services/dictService/getDictList";
		post(url,map);
	}
	public static String post(String url,Map<String,String> map) {
		HttpCommandProvider provider = new HttpCommandProvider();
		provider.setGetMethod();
		provider.addHeadParam("Content-Type", "application/json; charset=UTF-8");
		provider.setUrl(url);
		provider.setParams(map);
		provider.send();
	String dd=	provider.getResponseContent();
	int code=	provider.getResponseCode();
	System.out.println(code);
	System.out.println(dd);
	return dd;
	}
	public static String get(String url,Map<String,String> map) {
		HttpCommandProvider provider = new HttpCommandProvider();
			provider.setGetMethod();
			provider.addHeadParam("Content-Type", "application/json; charset=UTF-8");
			provider.setUrl(url);
			provider.setParams(map);
			provider.send();
		String dd=	provider.getRequestBody();
		System.out.println(dd);
		return dd;
	}
}
