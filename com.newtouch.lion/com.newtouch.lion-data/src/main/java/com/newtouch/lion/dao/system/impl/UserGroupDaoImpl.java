/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: UserGroupDaoImpl.java 9552 2015年2月15日 上午12:00:40 WangLijun$
*/
package com.newtouch.lion.dao.system.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.UserGroupDao;
import com.newtouch.lion.model.system.UserGroup;

/**
 * <p>
 * Title: 用户与用户组查询(仅用于查询)
 * </p>
 * <p>
 * Description: 用户与用户组查询(仅用于查询)
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
@Repository("userGroupDao")
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup,Long> implements UserGroupDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3056242994637897358L;
	
}

	