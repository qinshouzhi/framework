/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Constants.java 9552 2015年2月27日 下午8:14:25 WangLijun$
*/
package com.newtouch.lion.web.shiro.constant; 
/**
 * <p>
 * Title: Shiro扩展的常量
 * </p>
 * <p>
 * Description: 
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
public class Constants {
	/**登录错误代码*/
	public static final String LOGIN_ERROR_MSG="login_error"; 
	/***
	 * 登录错误参数
	 */
	public static final String FORCE_LOGOUT="forcelogout";
	/***
	 * 用户已登录的错误-2
	 */
	public static final String LOGIN_MAXS_ERROR="2";
	/**用户被强制退出*/
	public static final String LOGIN_FORCE_ERROR="1";
	/**控制并发缓存名称*/
	public static final String CACHE_SESSION_NAME="shiroSessionController";
}

	