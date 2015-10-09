/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DemoDao.java 9552 2015年10月9日 上午10:57:18 WangLijun$
*/
package com.newtouch.lion.service; 

import com.newtouch.lion.model.BasDemo;

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
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public interface DemoDao {
	
	public BasDemo getById(Long id);
	
	public int  delete(Long id);
}

	