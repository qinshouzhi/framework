/*
 * Copyright (c)  2012, Newtouch
 * All rights reserved. 
 *
 * $id: TsResourceDaoImpl.java 9552 2012-12-31 下午6:55:36 WangLijun$
 */
package com.newtouch.lion.dao.system.impl;

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.ResourceDao;
import com.newtouch.lion.model.system.Resource;

/**
 * <p>
 * Title: 资源管理数据存储实现类
 * </p>
 * <p>
 * Description: 资源管理数据存储实现类
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
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<Resource,Long> implements
		ResourceDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5311893761504147007L;

}
