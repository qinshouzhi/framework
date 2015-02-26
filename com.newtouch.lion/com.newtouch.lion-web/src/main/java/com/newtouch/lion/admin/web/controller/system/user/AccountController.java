/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AccountController.java 9552 2015年2月25日 上午11:12:00 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.system.user; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newtouch.lion.model.system.User;
import com.newtouch.lion.web.controller.AbstractController;
import com.newtouch.lion.web.shiro.session.LoginSecurityUtil;

/**
 * <p>
 * Title: 用户账户信息管理
 * </p>
 * <p>
 * Description: 用户账户信息管理（用于密码修改、个人账户信息修改、）
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
@Controller(value = "sysAccount")
@RequestMapping("/system/account/")
public class AccountController extends AbstractController{
	
	/**个账户首页**/
	private static final String INDEX_RETURN="/system/account/index";
	/**日历首页*/
	private static final String CALENDAR_RETURN="/system/account/calendar";
	/**通知消息*/
	private static final String NOTIFICATIONS_RETURN="/system/account/notifications";
	/**个人主页*/
	@RequestMapping("index")
	public String index(Model model){
		User user = LoginSecurityUtil.getUser();
		model.addAttribute("user", user);
		return INDEX_RETURN;
	}
	/**日历*/
	@RequestMapping("calendar")
	public String calendar(Model model){
		return  CALENDAR_RETURN;
	}
	/**消息*/
	@RequestMapping("notifications")
	public String notifications(){
		return NOTIFICATIONS_RETURN;
	}
}

	