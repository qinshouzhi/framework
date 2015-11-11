/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Country.java 9552 2015年10月9日 下午5:08:03 WangLijun$
*/
package com.newtouch.lion.dao; 

import com.github.pagehelper.PageInfo;
import com.newtouch.lion.model.Country;

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
public interface CountryDao {
	
	public PageInfo<Country> findPage();
}

	