package com.bmsmart.mes.plan.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.bmsmart.mes.base.exception.MesBaseException;
import com.bmsmart.mes.base.util.StringUtil;
import com.bmsmart.mes.base.util.httpclient.HttpCommandProvider;
import com.bmsmart.mes.base.util.json.fastjson.JSONFormatter;
import com.bmsmart.mes.base.util.prop.PropertyReader;
import com.bmsmart.mes.base.util.web.WebUtil;
import com.bmsmart.mes.common.SessionUserInfo;
import com.bmsmart.mes.platform.auth.base.AuthHelper;
import com.bmsmart.mes.platform.auth.base.AuthResult;
import com.bmsmart.mes.platform.export.UserProvider;
import com.bmsmart.mes.platform.export.request.UserRequest;
import com.bmsmart.mes.platform.export.vo.PermissionInfo;
import com.bmsmart.mes.platform.export.vo.UserVo;

/**
 * 登录认证的拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserProvider userProvider;
	/**
	 * Handler执行完成之后调用这个方法
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc)
			throws Exception {

	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * Handler执行之前调用这个方法
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求的URL
		String url = request.getRequestURI();
		// URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
		if (url.indexOf("login.do") >= 0) {
			return true;
		}
		AttributePrincipal principal = null;
		Principal userPrincipal = request.getUserPrincipal();
		if (userPrincipal != null) {
			principal = (AttributePrincipal) request.getUserPrincipal();
		}
		if (principal != null && StringUtil.isNotBlank(principal.getName())) {
			String userName = principal.getName();
			// 判断登录信息如果已经登录不处理，如果当前登录信息不匹配重新更新session。
			dealUserSession(request, response, principal);
			// 判断权限信息
			Boolean flag = validate(request, response, userName);
			return true;
		} else {
			// 未登录处理
			sendLogin(request, response);
			return false;
		}
	}

	// 处理流程的session信息
	private void dealUserSession(HttpServletRequest request, HttpServletResponse response,
			AttributePrincipal principal) {
		if (principal == null) {
			try {
				sendLogin(request, response);
			} catch (IOException e) {
				throw new MesBaseException("error", e);
			}
			return;
		} else {
			String userSessionName = PropertyReader.getContextProperty(AuthHelper.SESSION_USER);
			HttpSession session = request.getSession();
			UserVo userVo = (UserVo) session.getAttribute(userSessionName);
			if (userVo == null || StringUtils.isEmpty(userVo.getId())
					|| !userVo.getName().equals(principal.getName())) {
				userVo = getUserVo(principal);
				//重新得到用户信息 zhouqz,有完整的用户信息
				userVo = userProvider.queryUserByUserId(userVo.getId());
				
				session.setAttribute(userSessionName, userVo);
				SessionUserInfo.SessionInfo.set(userVo);
			 
			}

		}

	}

	/**
	 * 验证权限
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws IOException
	 */
	private boolean validate(HttpServletRequest request, HttpServletResponse response, String userName)
			throws IOException {
		String permissionSessionName = PropertyReader.getContextProperty(AuthHelper.SESSION_PERMISSION);
		String url = WebUtil.getRequestUri(request);
		// String path = request.getContextPath();
		StringBuffer basePath = new StringBuffer(
				request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort());
		basePath.append(url);
		url = basePath.toString();
		HttpSession session = request.getSession();
		PermissionInfo permissionInfo = (PermissionInfo) session.getAttribute(permissionSessionName);
		if (permissionInfo == null || StringUtils.isEmpty(permissionInfo.getUserName())) {
			permissionInfo = getPermissions(userName);
			session.setAttribute(permissionSessionName, permissionInfo);
		}
//		boolean flag = false;
//		Map<String, String> sysMap = permissionInfo.getSysUrlMap();
//		Map<String, String> functionSet = permissionInfo.getPermissions();
//		for (Map.Entry<String, String> entry : functionSet.entrySet()) {
//			String key = entry.getKey();
//			String value = entry.getValue();
//			String[] arr = key.split(":");
//			StringBuffer sysUrl = new StringBuffer(sysMap.get(arr[0]));
//			String requestUrl = sysUrl.append(value).toString();
//			if (url.indexOf(requestUrl) != -1) {
//				flag = true;
//				break;
//			}
//		}
		boolean flag= true;
		if (flag) {
			return true;
		} else {
			return sendNoAuth(request, response);
		}
	}

	/**
	 * 未登录处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private Boolean sendLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String resultStr = JSONFormatter.newInstance().toJSONString(new AuthResult(false, "no login"));
			out.write(resultStr);
			out.flush();
			out.close();
		} else {
			String loginUrl = PropertyReader.getContextProperty(AuthHelper.CAS_SERVER_URL);
			response.sendRedirect(loginUrl);
		}
		return false;
	}

	/**
	 * 无权限处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */

	private Boolean sendNoAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String resultStr = JSONFormatter.newInstance().toJSONString(new AuthResult(false, "no permission"));
			out.write(resultStr);
			out.flush();
			out.close();
		} else {
			String noAuthUrl = PropertyReader.getContextProperty(AuthHelper.NO_AUTH_URL);
			response.sendRedirect(noAuthUrl);
		}
		return false;
	}

	/**
	 * 获取用户的权限
	 * 
	 * @param username
	 * @return
	 */
	private static PermissionInfo getPermissions(String username) {
		String url = PropertyReader.getContextProperty(AuthHelper.MES_AUTH_URL);
		HttpCommandProvider provider = new HttpCommandProvider();
		provider.addHeadParam("Content-Type", "application/json; charset=UTF-8");
		provider.setPostMethod();
		provider.setUrl(url);
		UserRequest req = new UserRequest();

		req.setUserName(username);
		String dd = JSONFormatter.newInstance().toJSONString(req);
		provider.setRequestBody(dd);
		provider.send();
		int responseCode = provider.getResponseCode();
		String content = provider.getResponseContent();
		PermissionInfo permissionInfo = JSONFormatter.newInstance().toObject(content, PermissionInfo.class);
		return permissionInfo;
	}

	/**
	 * 组装用户的基本信息
	 * 
	 * @param principal
	 * @return
	 */
	private static UserVo getUserVo(AttributePrincipal principal) {
		final Map attributes = principal.getAttributes();
		UserVo userVo = new UserVo();
		userVo.setName(principal.getName());
		if (attributes != null) {
			Iterator attributeNames = attributes.keySet().iterator();
			if (attributeNames.hasNext()) {
				for (; attributeNames.hasNext();) {
					String attributeName = (String) attributeNames.next();
					final Object attributeValue = attributes.get(attributeName);
					if (attributeName.equals("userId")) {
						userVo.setId(attributeValue.toString());
						break;
					}
				}
			}
		}
		return userVo;
	}
}