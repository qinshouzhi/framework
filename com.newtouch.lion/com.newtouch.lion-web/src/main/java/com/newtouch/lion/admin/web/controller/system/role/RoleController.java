/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RoleController.java 9552 2015年1月7日 上午12:06:04 WangLijun$
*/
package com.newtouch.lion.admin.web.controller.system.role; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newtouch.lion.web.controller.AbstractController;

/**
 * <p>
 * Title: 角色控制器
 * </p>
 * <p>
 * Description: 包括角色查询、添加、删除、修改
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
@Controller
@RequestMapping("/system/role")
public class RoleController extends AbstractController{
	
	/**首页*/
	private final static String  INDEX_RETURN="/system/role/index";
	/**显示首页服务*/
	@RequestMapping("/index")
	public String index(){
		return INDEX_RETURN;
	}
}

	