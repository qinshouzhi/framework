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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.newtouch.lion.common.date.DateUtil;
import com.newtouch.lion.json.JSONParser;
import com.newtouch.lion.web.servlet.view.support.BindMessage;
import com.newtouch.lion.web.servlet.view.support.BindResult;
import com.newtouch.lion.web.servlet.view.support.BindStatus;
import com.newtouch.lion.web.servlet.view.support.RequestContext;

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
public class JsonView extends AbstractView {
	/**日志分析*/
	private final static Logger logger=LoggerFactory.getLogger(JsonView.class);
	/**日期格式*/
	private String datePattern =DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS;
	
	private int responseStatus = HttpStatus.OK.value();
	
	private BindStatus bindStatus;

	private RequestContext requestContext;

	public JsonView() {
		super();
	}

	 

	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setStatus(getResponseStatus());
		response.setContentType(getContentType());
		Object object=model.get("modelAndView");
		if(object instanceof ModelAndView){
			ModelAndView modelAndView=(ModelAndView) object;
			String jsonStr=this.initBindStatus(request, response, modelAndView);
			response.getWriter().write(jsonStr);
		}else{
			logger.warn("dddd");
			String json=JSONParser.toJSONString(model);
			response.getWriter().write(json);
		}
		
	}
	/***
	 * 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 */
	protected String initBindStatus(HttpServletRequest request,HttpServletResponse response,ModelAndView  modelAndView){
		Errors errors=(Errors) modelAndView.getModelMap().get(BindMessage.ERRORS_MODEL_KEY);
		this.initRequestContext(request, null, modelAndView.getModel());
		String jsonStr=null;
		if(errors!=null&&errors.hasErrors()){			
			this.bindStatus = new BindStatus(requestContext,errors);
			BindMessage bindMessage = new BindMessage(this.getBindStatus().getObjectName(), this.getBindStatus().getGlobalErrorMessages(), this.getBindStatus().getFieldValidateMessages());
			jsonStr=JSONParser.toJSONString(bindMessage);
		}else{
			@SuppressWarnings("unchecked")
			Map<String,String>  params=(Map<String, String>)modelAndView.getModel().get(BindMessage.SUCCESS);
			String message=this.requestContext.getMessage(params.get(BindResult.SUCCESS));
			BindMessage bindMessage=new BindMessage(message);
			jsonStr=JSONParser.toJSONString(bindMessage,datePattern);
		}
		return jsonStr;
	}
	
	/***
	 * 初始化 RequestContext
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param Map
	 *            <String, Object>
	 */
	protected void initRequestContext(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		this.requestContext = new RequestContext(request, response, request
				.getSession().getServletContext(), model);
	}

 
	public void setDatePattern(String pattern) {
		this.datePattern = pattern;
	}
	
	/**
	 * @return the bindStatus
	 */
	public BindStatus getBindStatus() {
		return bindStatus;
	}

	/**
	 * @param bindStatus
	 *            the bindStatus to set
	 */
	public void setBindStatus(BindStatus bindStatus) {
		this.bindStatus = bindStatus;
	}

	/**
	 * @return the requestContext
	 */
	public RequestContext getRequestContext() {
		return requestContext;
	}

	/**
	 * @param requestContext
	 *            the requestContext to set
	 */
	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
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
