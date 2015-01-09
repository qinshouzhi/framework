/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: LoginController.java 9552 2014年12月30日 上午10:24:10 WangLijun$
 */
package com.newtouch.lion.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;

/**
 * <p>
 * Title: 用户登录Controller
 * </p>
 * <p>
 * Description: 用户登录Controller
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
@Controller("adminLoginController")
public class LoginController extends AbstractController {
	/** 进入登录页面 */
	private static final String LOGIN_RETURN = "/login";
	/** 登录成功 */
	private static final String LOGIN_SUCCESS = "/index.htm";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("进入登录页面");
		User user = LoginSecurityUtil.getUser();
		if (user != null) {
			logger.info("用户名:{}，ID：{} 已经登录，重定向到首页", user.getUsername(),
					user.getId());
			return this.redirect(LOGIN_SUCCESS);
		}
		return LOGIN_RETURN;
	}
}
