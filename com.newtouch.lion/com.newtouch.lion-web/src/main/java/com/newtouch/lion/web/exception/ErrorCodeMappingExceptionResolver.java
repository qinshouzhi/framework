/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: ErrorCodeMappingExceptionResolver.java 9552 2014年12月29日 上午10:37:04 WangLijun$
 */
package com.newtouch.lion.web.exception;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.newtouch.lion.excpetion.BaseException;

/**
 * <p>
 * Title: View层异常处理
 * </p>
 * <p>
 * Description: View层异常处理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ErrorCodeMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	public static final String DEFAULT_EXCEPTION_MESSAGE_KEY = "exception.defaultMessage";
	public static final String DEFAULT_EXCEPTION_MESSAGE = "";
	/** 日志 */
	private static Logger logger = LoggerFactory
			.getLogger(ErrorCodeMappingExceptionResolver.class);
	private Properties exceptionMappings;
	private Properties errorCodeMappings;
	private String defaultErrorView;

	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if ((request.getHeader("x-requested-with") != null)
				&& (request.getHeader("x-requested-with")
						.equalsIgnoreCase("XMLHttpRequest"))) {
			response.setHeader("sessionstatus", "timeout");
			return null;
		}

		BaseException bex=(BaseException) ex;
	 

		String viewName = determineViewName(bex, request);
		if (viewName != null) {
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response,
						statusCode.intValue());
			}
			return getModelAndView(viewName, bex, request);
		}
		return null;
	}

	protected String determineViewName(Exception ex, HttpServletRequest request) {
		String viewName = null;
		String codeViewName = null;
		String classViewName = null;

		if (this.exceptionMappings != null) {
			classViewName = findMatchingViewName(this.exceptionMappings, ex);
		}

		if (this.errorCodeMappings != null) {
			codeViewName = findMatchingViewNameByErrorCode(
					this.errorCodeMappings, ex);
		}

		if (classViewName != null)
			viewName = classViewName;
		else if (codeViewName != null) {
			viewName = codeViewName;
		}

		if ((viewName == null) && (this.defaultErrorView != null)) {

			logger.debug("Resolving to default view '" + this.defaultErrorView
					+ "' for exception of type [" + ex.getClass().getName()
					+ "]");

			viewName = this.defaultErrorView;
		}

		fillFriendlyExceptionMessage(ex, request);

		return viewName;
	}

	@SuppressWarnings("rawtypes")
	protected String findMatchingViewNameByErrorCode(
			Properties errorCodeMappings, Exception ex) {
		if (!(ex instanceof BaseException)) {
			return null;
		}

		String errorCode = ((BaseException) ex).getCode();
		if (errorCode == null) {
			return null;
		}

		for (Enumeration names = errorCodeMappings.propertyNames(); names
				.hasMoreElements();) {
			String codes = (String) names.nextElement();
			String viewName = errorCodeMappings.getProperty(codes);

			String[] codeArray = codes.split(",");
			for (String code : codeArray) {
				if (code.trim().equalsIgnoreCase(errorCode.trim())) {
					return viewName;
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	protected void fillFriendlyExceptionMessage(Exception ex,
			HttpServletRequest request) {
		BaseException bex;
		if (ex instanceof BaseException)
			bex = (BaseException) ex;
		else {
			bex = new BaseException(ex.getMessage(),ex);
		}

		//bex.setFriendlyMessage(bex.getFriendlyMessage());
	}

	protected String getFriendlyExceptionMessage(BaseException ex,
			Locale locale, HttpServletRequest request) {
		 return null;
	}

 

	public void setErrorCodeMappings(Properties errorCodeMappings) {
		this.errorCodeMappings = errorCodeMappings;
	}

	public void setExceptionMappings(Properties exceptionMappings) {
		this.exceptionMappings = exceptionMappings;
	}

	public void setDefaultErrorView(String defaultErrorView) {
		this.defaultErrorView = defaultErrorView;
	}
}
