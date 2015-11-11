/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserServiceImpl.java 9552 2015年7月17日 下午10:33:19 WangLijun$
*/
package com.newtouch.lion.cache.service.impl; 

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
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
	static User user=new User(1L, "Even", "scwanglijun", 1, "上海", "200000", "上海");
	static User user1=new User(2L, "Even", "scwanglijun", 1, "上海", "200000", "上海");
	static User user2=new User(3L, "Even", "scwanglijun", 1, "上海", "200000", "上海");
	static Map<Long,User> params=new HashMap<Long,User>();
	static{
		params.put(1L,user);
		params.put(2L,user1);
		params.put(3L,user2);
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.cache.service.UserService#find(java.lang.Long)
	 */
	@Override
	@Cacheable(value="codeListEhCache",key="#id")
	public User find(Long id) {
		logger.info("从数据库查询");
		return params.get(id);
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

	