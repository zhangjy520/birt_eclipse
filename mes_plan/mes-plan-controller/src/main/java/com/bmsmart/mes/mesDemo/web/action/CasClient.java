/** 
 * (C) Copyright 2017 BBMSmart(beijing) Co., Ltd  
 * All Rights Reserved. 
 **/
package com.bmsmart.mes.mesDemo.web.action;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bmsmart.mes.base.util.httpclient.HttpCommandProvider;

/**
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @author Malan
 * @date 2017年6月5日 下午2:56:27
 **/
public class CasClient {
	public static String getTicket(final String server, final String username, final String password,
			final String service) {
		notNull(server, "server must not be null");
		notNull(username, "username must not be null");
		notNull(password, "password must not be null");
		notNull(service, "service must not be null");

		return getServiceTicket(server, getTicketGrantingTicket(server, username, password), service);
	}

	/**
	 * 取得ST
	 * 
	 * @param server
	 * @param ticketGrantingTicket
	 * @param service
	 */
	private static String getServiceTicket(final String server, final String ticketGrantingTicket,
			final String service) {
		if (ticketGrantingTicket == null)
			return null;

		HttpCommandProvider provider = new HttpCommandProvider();
		provider.setPostMethod();
		provider.addParam("service", service);
		provider.setUrl(server + "/" + ticketGrantingTicket);
		provider.send();

		try {
			switch (provider.getResponseCode()) {
			case 200:
				return provider.getResponseContent();

			default:
				warning("Invalid response code (" + provider.getResponseCode() + ") from CAS server!");
				info("Response (1k): " + provider.getResponseContent().substring(0,
						Math.min(1024, provider.getResponseContent().length())));
				break;
			}
		}

		finally {
			// post.releaseConnection();
		}

		return null;
	}

	/**
	 * @param server
	 * @param username
	 * @param password
	 */
	private static String getTicketGrantingTicket(final String server, final String username, final String password) {

		HttpCommandProvider provider = new HttpCommandProvider();
		provider.setPostMethod();
		provider.setContentType("test/html");
		provider.addParam("username", username);
		provider.addParam("password", password);
		provider.setUrl(server);
		provider.send();

		final String response = provider.getRequestBody();
		final String content = provider.getResponseContent();
		info("TGT=" + content);
		switch (provider.getResponseCode()) {
		case 201: {
			final Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*").matcher(content);

			if (matcher.matches())
				return matcher.group(1);

			warning("Successful ticket granting request, but no ticket found!");
			info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
			break;
		}

		default:
			warning("Invalid response code (" + provider.getResponseCode() + ") from CAS server!");
			info("Response (1k): " + response.substring(0, Math.min(1024, response.length())));
			break;
		}
		return null;
	}

	public static void ticketValidate(String serverValidate, String serviceTicket, String service) {
		notNull(serviceTicket, "paramter 'serviceTicket' is not null");
		notNull(service, "paramter 'service' is not null");

		try {
			String url = serverValidate + "?" + "ticket=" + serviceTicket + "&service="
					+ URLEncoder.encode(service, "UTF-8");

			HttpCommandProvider provider = new HttpCommandProvider();
			provider.setPostMethod();
			provider.setContentType("test/html");
			provider.addParam("username", "test1");
			provider.addParam("password", "admin");
			provider.setUrl(url);
			provider.send();
int code=provider.getResponseCode();
			switch (code) {
			case 200: {
				final String response = provider.getResponseContent();
				System.out.println(response);
				info("成功取得用户数据");
			}
			default: {

			}
			}

		} catch (Exception e) {
			warning(e.getMessage());
		} finally {
			// 释放资源

		}

	}

	private static void notNull(final Object object, final String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	public static void main(final String[] args) throws Exception {
		final String server = "http://192.168.4.211:8080/cas/v1/tickets";
		final String username = "test1";
		final String password = "admin";
		final String service = "http://www.baidu.com"; // 随意写
		final String proxyValidate = "http://192.168.4.211:8080/cas/proxyValidate";
		String ticketStr = getTicket(server, username, password, service);
	//	ticketValidate(proxyValidate, ticketStr, service);
System.out.println(ticketStr);
	}

	private static void warning(String msg) {
		System.out.println(msg);
	}

	private static void info(String msg) {
		System.out.println(msg);
	}
}
