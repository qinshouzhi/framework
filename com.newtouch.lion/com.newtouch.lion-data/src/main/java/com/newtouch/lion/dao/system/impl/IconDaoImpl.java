
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: IconDaoImpl.java 9552 Mar 4, 2015 11:30:43 AM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.IconDao;
import com.newtouch.lion.model.system.Icon;

/**
 * <p>
 * Title: 图标Icon的Dao实现层
 * </p>
 * <p>
 * Description: 图标Icon的Dao实现层
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
@Repository("iconDao")
public class IconDaoImpl extends BaseDaoImpl<Icon,Long> implements IconDao{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1494476134711376183L;

}

	