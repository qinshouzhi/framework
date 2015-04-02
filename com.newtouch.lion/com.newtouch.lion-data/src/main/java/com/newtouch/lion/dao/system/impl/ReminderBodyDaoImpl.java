
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: ReminderBodyImpl.java 9552 2015年3月31日 下午3:52:24 ZhongMengDie$
*/
package com.newtouch.lion.dao.system.impl; 


import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.ReminderBodyDao;
import com.newtouch.lion.model.system.ReminderBody;


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
 * @author ZhongMengDie
 * @version 1.0
 */
@Repository("reminderDao")
public class ReminderBodyDaoImpl extends BaseDaoImpl<ReminderBody, Long> implements
		ReminderBodyDao {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1494476134711376183L;
}

	