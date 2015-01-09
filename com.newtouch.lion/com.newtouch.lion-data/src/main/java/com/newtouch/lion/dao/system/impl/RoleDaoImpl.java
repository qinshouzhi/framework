/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsRoleDaoImpl.java 9552 2012-12-31 下午5:30:44 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.RoleDao;
import com.newtouch.lion.model.system.Role;

/**
 * <p>
 * Title: 用户角色DaoImpl
 * </p>
 * <p>
 * Description: 用户角色DaoImpl
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
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role,Long> implements RoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 751517159442687213L;

}
