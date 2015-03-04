/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: CustomPremissonsFilter.java 9552 2015年3月4日 下午5:13:26 WangLijun$
 */
package com.newtouch.lion.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.web.util.LionWebUtils;

/**
 * <p>
 * Title: 自定义权限拦截器
 * </p>
 * <p>
 * Description: 自定义权限拦截器，估计Ajax请求和无权限，则返回JSON字符串
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
public class AjaxAccessAuthorizationFilter extends PermissionsAuthorizationFilter{

	/** 日志信息 */
	private static final Logger logger = LoggerFactory.getLogger(AjaxAccessAuthorizationFilter.class);

 

	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		
		
		//判断是否登录
		if(subject.getPrincipal()==null){
			if(LionWebUtils.isAjax(request)){
				logger.info("Ajax 请求未登录{}",mappedValue);
				WebUtils.toHttp(response).setHeader("islogined","false");
				WebUtils.toHttp(response).setStatus(998);
			}else{
			  this.saveRequestAndRedirectToLogin(request, response);
			}
		}else{
			if(LionWebUtils.isAjax(request)){
			 
				logger.info("Ajax 请求未登录:{},url:{}",mappedValue,this.getPathWithinApplication(request));
			     WebUtils.toHttp(response).setHeader("authorized","true");
			     WebUtils.toHttp(response).setStatus(999);
			}else{
				
	            String unauthorizedUrl = getUnauthorizedUrl();
	            //SHIRO-142 - ensure that redirect _or_ error code occurs - both cannot happen due to response commit:
	            if (StringUtils.hasText(unauthorizedUrl)) {
	            	
	                WebUtils.issueRedirect(request, response, unauthorizedUrl);
	            } else {
	                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
	            }
			}
		}
		
		
		return false;
	}
}
