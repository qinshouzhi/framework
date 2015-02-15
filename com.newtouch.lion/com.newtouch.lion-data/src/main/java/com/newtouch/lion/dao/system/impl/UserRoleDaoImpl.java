
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: UserRoleDaoImpl.java 9552 Feb 15, 2015 11:48:10 AM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.UserRoleDao;
import com.newtouch.lion.model.system.UserRole;

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
 * Company: NewTouch
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole,Long> implements UserRoleDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8842508630699092859L;

}

	