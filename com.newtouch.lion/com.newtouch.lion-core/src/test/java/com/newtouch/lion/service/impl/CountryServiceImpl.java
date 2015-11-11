/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CountryServiceImpl.java 9552 2015年10月9日 下午5:20:00 WangLijun$
*/
package com.newtouch.lion.service.impl; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.newtouch.lion.dao.CountryDao;
import com.newtouch.lion.model.Country;
import com.newtouch.lion.service.CountryService;

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
@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDao countryDao;

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.CountryService#findPage()
	 */
	@Override
	public PageInfo<Country> findPage() {
		return countryDao.findPage();
	}

}

	