/*
* Copyright (c)  2014, Newtouch
* All rights reserved. 
*
* $id: UserServiceTest.java 9552 2014年12月30日 上午10:56:45 WangLijun$
*/
package com.newtouch.lion.data.service; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.data.AllTest;
import com.newtouch.lion.model.system.User;
import com.newtouch.lion.service.system.UserService;

/**
 * <p>
 * Title: 用户测试类
 * </p>
 * <p>
 * Description: 用户测试类
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
public class UserServiceTest extends AllTest{
	@Autowired
	private UserService userService;
	
	@Test
	public void doFindByUserNameTest(){
		String userName="wanglijun";
		User user=userService.doFindByUserName(userName);
		logger.info("user.username:{}",user.getUsername());
	}
	
	
}

	