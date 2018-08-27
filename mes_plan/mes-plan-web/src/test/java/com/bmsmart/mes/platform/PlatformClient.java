/** 
 * (C) Copyright 2017 BBMSmart(beijing) Co., Ltd  
 * All Rights Reserved. 
 **/
package com.bmsmart.mes.platform;

import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bmsmart.mes.platform.export.DictProvider;
import com.bmsmart.mes.platform.export.UserProvider;
import com.bmsmart.mes.platform.export.request.DictRequest;
import com.bmsmart.mes.platform.export.request.UserRequest;
import com.bmsmart.mes.platform.export.response.DictListResponse;
import com.bmsmart.mes.platform.export.vo.PermissionInfo;

/** 
 * TODO(这里用一句话描述这个类的作用) 
 * @author Malan
 * @date 2017年6月9日 下午4:05:55 
 **/
public class PlatformClient {
	public static void main(String[] args) throws Exception {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] { "spring-dubbo-consumer.xml" });
	context.start();
	testDict(context);
	//getUser(context);
	}
	public static void testDict(ClassPathXmlApplicationContext context) {
	DictProvider provider = (DictProvider) context.getBean("dictProvider");
	DictRequest request= new DictRequest();
	request.setType("color");
	DictListResponse dd = provider.getDictList(request);
	System.out.println("==============="+dd.getDictList().size());
	}
	public static void getUser(ClassPathXmlApplicationContext context) {
		UserProvider userProvider = (UserProvider) context.getBean("userProvider");
		String appKey="test";
		String username="admin";
		UserRequest request=new UserRequest();
		request.setReqSysCode(appKey);
		request.setUserName(username);
		PermissionInfo permissionInfo=userProvider.getPermissions(request);//(appKey, username);
Set<String> menuSet=		permissionInfo.getOrgs();
System.out.println(menuSet.size());
	}
}
