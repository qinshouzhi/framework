/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: UserService.java 9552 2015年7月17日 下午10:32:08 WangLijun$
 */
package com.newtouch.lion.cache.service;

import org.springframework.cache.annotation.Cacheable;

import com.newtouch.lion.cache.model.User;

/**
 * <p>
 * Title: 用户测试服务类
 * </p>
 * <p>
 * Description: 用户测试服务类
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
public interface UserService {

	
	public User find(Long id);
	
	public User find(User user);

}
