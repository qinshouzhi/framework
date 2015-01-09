/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: IndexController.java 9552 2014年12月30日 上午10:10:29 WangLijun$
*/
package com.newtouch.lion.admin.web.controller; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newtouch.lion.web.controller.AbstractController;

/**
 * <p>
 * Title: 后台管理首页Controller
 * </p>
 * <p>
 * Description: 后台管理首页Controller
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
@Controller
@RequestMapping("/")
public class IndexController extends  AbstractController{
	/**首页模板路径*/
	private static final String INDEX_RETURN="/index";
	/**测试页面*/
	private static final String TEST_RETURN="/test";
	
	@RequestMapping("/index")
	public String index(){
		logger.info("进入首页.....");
		return INDEX_RETURN;
	}
	
	@RequestMapping("/test")
	public String test(){
		
		 
        logger.info(" request.getRequestURL():{}",this.getRequest().getRequestURL());
        logger.info(" request.getRequestURI():{}",this.getRequest().getRequestURI());
        logger.info(":{}",this.getRequest().getContextPath());
		logger.info("进入测试页面");
		return TEST_RETURN;
	}
}

	