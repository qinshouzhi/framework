/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserTest.java 9552 2015年7月17日 下午10:38:36 WangLijun$
*/
package com.newtouch.lion.cache; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.cache.service.UserService;

/**
 * <p>
 * Title: User Cache 缓存测试
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
public class UserTest extends CacheAllTest {
	
	@Autowired
	private  UserService userService;
	
	@Test
	public void findById(){
		logger.info("第一次查询");
		userService.find(1L);
		logger.info("第二次查询");
		userService.find(1L);
		logger.info("------");
	}
}

	