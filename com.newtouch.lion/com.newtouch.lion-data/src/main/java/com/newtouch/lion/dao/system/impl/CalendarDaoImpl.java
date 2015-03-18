
/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: CalendarDaoImpl.java 9552 Mar 14, 2015 10:48:32 PM MaoJiaWei$
*/
package com.newtouch.lion.dao.system.impl; 

import org.springframework.stereotype.Repository;

import com.newtouch.lion.dao.impl.BaseDaoImpl;
import com.newtouch.lion.dao.system.CalendarDao;
import com.newtouch.lion.model.system.Calendar;

/**
 * <p>
 * Title: 日历Dao实现类
 * </p>
 * <p>
 * Description: 日历Dao实现类
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
@Repository("calendarDao")
public class CalendarDaoImpl extends BaseDaoImpl<Calendar, Long> implements CalendarDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6915395255709654538L;

}

	