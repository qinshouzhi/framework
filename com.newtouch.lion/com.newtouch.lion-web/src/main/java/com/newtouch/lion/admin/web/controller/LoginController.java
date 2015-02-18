/*
 * Copyright (c)  2014, Newtouch
 * All rights reserved. 
 *
 * $id: LoginController.java 9552 2014年12月30日 上午10:24:10 WangLijun$
 */
package com.newtouch.lion.admin.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.shiro.authc.CredentialExpiredException;
import com.newtouch.lion.web.shiro.authc.ExpiredAccountException;
import com.newtouch.lion.web.shiro.model.LoginUser;
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
@Controller("adminLoginController1")
public class LoginController extends AbstractController {
	/** 进入登录页面 */
	private static final String LOGIN_RETURN = "/login";
	/** 登录成功 */
	private static final String LOGIN_SUCCESS = "/index.htm";

	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(LoginUser loginUser,Model model) {
		logger.info("进入登录页面");
		User user = LoginSecurityUtil.getUser();
		//获取当前的Subject
		UsernamePasswordToken token=new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
		//获取当前的Subject
		Subject currentUser=SecurityUtils.getSubject();
		try{
			logger.info("验证用户和密码开始...");
			currentUser.login(token);
			logger.info("验证用户和密码结束...");
		}catch(UnknownAccountException e){
			model.addAttribute("login_error","用户或密码不正确.");
			logger.error(e.getMessage(),e);
		}catch(IncorrectCredentialsException e){
			model.addAttribute("login_error","用户或密码不正确.");
			logger.error(e.getMessage(),e);
		}catch(LockedAccountException e){
			logger.error(e.getMessage(),e);
			model.addAttribute("login_error","用户已锁定.");
		}catch(ExpiredAccountException e){
			logger.error(e.getMessage(),e);
			model.addAttribute("login_error","用户已过期，请联系管理员.");
		}catch(CredentialExpiredException e){
			logger.error(e.getMessage(),e);
			model.addAttribute("login_error","密码已过期，请联系管理员.");	
		}catch(AuthenticationException e){
			model.addAttribute("login_error","用户或密码不正确.");
			logger.error(e.getMessage(),e);
		}
		if(currentUser.isAuthenticated()){
			logger.info("用户名:{}，ID：{} 已经登录，重定向到首页", user.getUsername(),user.getId());
			return this.redirect(LOGIN_SUCCESS);
		}else{
			 token.clear(); 
		}
		return LOGIN_RETURN;
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String welcome() {
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
