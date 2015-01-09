
/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: AbstractController.java 9552 2014年12月29日 上午11:43:57 WangLijun$
*/
package com.newtouch.lion.web.controller; 

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.newtouch.lion.web.support.editor.StringEscapeEditor;

/**
 * <p>
 * Title: Controller抽像类
 * </p>
 * <p>
 * Description: Controller抽像类
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
public class AbstractController {
	
	/**日志*/
	protected Logger logger=LoggerFactory.getLogger(super.getClass());
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));
	}

	/**
	 * 用户跳转view页面
	 * @param folder   路径
	 * @param view 名称(不加后缀)
	 * @return 指定view页面
	 */
	@RequestMapping("/{folder}/{jspName}")
	public String redirectJsp(@PathVariable String folder,
			@PathVariable String jspName) {
		return "/" + folder + "/" + jspName;
	}
	
	/***
	 * 转发到URL
	 * @param url 转发到的URL
	 * @return 转发路径
	 */
	public String forward(String url){
		StringBuilder sb=new StringBuilder();
		sb.append("forward:");
		sb.append(url);
		return sb.toString();
	}
	
	/***
	 * 重定向到相关页面
	 * @param url
	 * @return
	 */
	protected String redirect(String url){
		StringBuilder sb=new StringBuilder();
		sb.append("redirect:");
		sb.append(url);
		return sb.toString();
	}
	/***
	 * 获取Request对象
	 * @return  HttpServletRequest
	 */
	protected HttpServletRequest  getRequest(){
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        return servletRequestAttributes.getRequest();     
	}
}

	