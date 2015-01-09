/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: GsonView.java 9552 2014年12月29日 上午10:24:23 WangLijun$
 */
package com.newtouch.lion.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newtouch.lion.common.date.DateUtil;

/**
 * <p>
 * Title: GsonView
 * </p>
 * <p>
 * Description: GsonView
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
public class GsonView extends AbstractView {
	
	private String datePattern =DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS;
	
	private String jsonObjectName;
	
	private int responseStatus = HttpStatus.OK.value();
	
	private ExclusionStrategy excludeStrategy;

	public GsonView() {
		super();
	}

	public GsonView(String jsonObjectName, ExclusionStrategy excludeStrategy) {
		this.jsonObjectName = jsonObjectName;
		this.excludeStrategy = excludeStrategy;
	}

	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setStatus(getResponseStatus());
		response.setContentType(getContentType());
		GsonBuilder gsonBuilder = new GsonBuilder()
				.setDateFormat(this.datePattern);
		if (this.excludeStrategy != null) {
			gsonBuilder
					.setExclusionStrategies(new ExclusionStrategy[] { this.excludeStrategy });
		}
		Gson gson = gsonBuilder.create();

		if ((model.keySet() != null) && (model.keySet().size() == 1))
			gson.toJson(model.values().iterator().next(), response.getWriter());
		else
			gson.toJson(
					(this.jsonObjectName == null) ? model : model
							.get(this.jsonObjectName), response.getWriter());
	}

	public String getJsonObjectName() {
		return this.jsonObjectName;
	}

	public void setDatePattern(String pattern) {
		this.datePattern = pattern;
	}

	public void setJsonObjectName(String objectName) {
		this.jsonObjectName = objectName;
	}

	public ExclusionStrategy getExcludeStrategy() {
		return this.excludeStrategy;
	}

	public void setExcludeStrategy(ExclusionStrategy excludeStrategy) {
		this.excludeStrategy = excludeStrategy;
	}

	public String getContentType() {
		return "text/html;charset=utf-8";
	}

	public int getResponseStatus() {
		return this.responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
}
