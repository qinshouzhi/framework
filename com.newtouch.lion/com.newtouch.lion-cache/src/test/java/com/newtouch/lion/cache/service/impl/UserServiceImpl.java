/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserServiceImpl.java 9552 2015年7月17日 下午10:33:19 WangLijun$
*/
package com.newtouch.lion.cache.service.impl; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newtouch.lion.cache.model.User;
import com.newtouch.lion.cache.service.UserService;

/**
 * <p>
 * Title: 
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
@Service
public class UserServiceImpl   implements UserService{
	
	
	/**日志*/
	protected  final Logger logger=LoggerFactory.getLogger(super.getClass());
	/* (non-Javadoc)
	 * @see com.newtouch.lion.cache.service.UserService#find(java.lang.Integer)
	 */
	@Override
	public User find(Integer id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.cache.service.UserService#find(com.newtouch.lion.cache.model.User)
	 */
	@Override
	public User find(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

	