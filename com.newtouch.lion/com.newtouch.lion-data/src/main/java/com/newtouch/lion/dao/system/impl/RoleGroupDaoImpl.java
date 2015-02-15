/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RoleGroupDaoImpl.java 9552 2015年2月15日 上午10:00:04 WangLijun$
*/
package com.newtouch.lion.dao.system.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.RoleGroupDao;
import com.newtouch.lion.model.system.RoleGroup;

/**
 * <p>
 * Title:  用户角色与用户、用户组查询对象(仅用于查询)
 * </p>
 * <p>
 * Description:  用户角色与用户、用户组查询对象
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
@Repository("roleGroupDao")
public class RoleGroupDaoImpl  extends BaseDaoImpl<RoleGroup,Long> implements RoleGroupDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 572376030513791675L;
	
}

	