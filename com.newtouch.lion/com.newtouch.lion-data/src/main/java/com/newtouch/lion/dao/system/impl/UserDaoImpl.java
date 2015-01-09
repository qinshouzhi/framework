/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsUserDao.java 9552 2012-12-16 下午8:42:49 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.UserDao;
import com.newtouch.lion.model.system.User;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User,Long> implements UserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985783112082066534L;

}
