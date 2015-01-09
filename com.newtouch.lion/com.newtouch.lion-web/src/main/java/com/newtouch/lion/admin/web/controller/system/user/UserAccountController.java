/*
 * Copyright (c)  2014, NewTouch
 * All rights reserved. 
 *
 * $id: AccountController.java 9552 2014-4-8 下午08:19:49 WangLijun$
 */
package com.newtouch.lion.admin.web.controller.system.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;

/**
 * <p>s
 * Title: 用户账户管理
 * </p>
 * <p>
 * Description: 用户账户管理，用于管理重置密码、锁定、解锁
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: NewTouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Controller(value = "sysUserAccount")
@RequestMapping("/system/useraccount/")
public class UserAccountController {

	private String ACTIVEUSERS_RETURN = "/system/user/activeusers";

	@Autowired
	private UserService userService;
	//@Autowired
	//private AccountUserService accountUserService;

	@RequestMapping(value = "/checkusername")
	@ResponseBody
	public String checkIsExistByNameEn(HttpServletRequest servletRequest,
			@RequestParam(required = false) String username) {
		Boolean flag = this.isExistByNameEn(username) == true ? false : true;
		return flag.toString();
	}

	private Boolean isExistByNameEn(String username) {
		Boolean flag = false;
		if (StringUtils.isNotEmpty(username)) {
			User user = userService.doFindByUserName(username.trim());
			if (user != null && user.getUsername().equals(username)) {
				flag = true;
			}
		}
		return flag;
	}

	/** 显示激活 */
	@RequestMapping(value = "/activeusers")
	public String activeUsers(Model model) {
		//List<SessionUserInfo> sessionUserInfos = accountUserService.getActiveUsers();
		//model.addAttribute("activeusers", sessionUserInfos);
		return ACTIVEUSERS_RETURN;
	}
}
