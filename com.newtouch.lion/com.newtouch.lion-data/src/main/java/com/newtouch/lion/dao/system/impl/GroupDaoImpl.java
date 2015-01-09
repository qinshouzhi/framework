/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsGroupDaoImpl.java 9552 2012-12-31 下午7:03:24 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.GroupDao;
import com.newtouch.lion.model.system.Group;

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
@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<Group,Long> implements GroupDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7374221945938855081L;
	
}
