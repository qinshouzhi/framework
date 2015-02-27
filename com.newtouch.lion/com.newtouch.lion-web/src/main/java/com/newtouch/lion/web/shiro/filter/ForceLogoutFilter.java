/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: ForceLogoutFilter.java 9552 2015年2月27日 下午2:54:10 WangLijun$
 */
package com.newtouch.lion.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.newtouch.lion.service.session.SessionService;

/**
 * <p>
 * Title: Shiro强制用户退出过滤器
 * </p>
 * <p>
 * Description: Shiro强制用户退出过滤器
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ForceLogoutFilter extends AccessControlFilter {
	
	/**后用户将强制退出的URL*/
	private String forceLogoutUrl;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(javax
	 * .servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Session session = getSubject(request, response).getSession(false);
		if (session == null) {
			return true;
		}
		return (session.getAttribute(SessionService.SESSION_FORCE_LOGOUT_KEY) == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.web.filter.AccessControlFilter#onAccessDenied(javax.
	 * servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		
		getSubject(request, response).logout();// 强制退出
		 
		StringBuilder sb=new StringBuilder();
		sb.append(this.forceLogoutUrl);
		sb.append(getLoginUrl().contains("?")?"&":"?");
		sb.append("forcelogout=1");
		
		WebUtils.issueRedirect(request, response, sb.toString());
		return false;
	}

	/**
	 * @return 后用户将强制退出的URL
	 */
	public String getForceLogoutUrl() {
		return forceLogoutUrl;
	}

	/**
	 * @param forceLogoutUrl 后用户将强制退出的URL
	 */
	public void setForceLogoutUrl(String forceLogoutUrl) {
		this.forceLogoutUrl = forceLogoutUrl;
	}

	
	
}
