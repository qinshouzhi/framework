/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CountryService.java 9552 2015年10月9日 下午5:19:23 WangLijun$
*/
package com.newtouch.lion.service; 

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
public interface CountryService {
	public PageInfo<Country> findPage();
}

	