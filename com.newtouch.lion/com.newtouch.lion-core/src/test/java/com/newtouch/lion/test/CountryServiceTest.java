/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CountryServiceTest.java 9552 2015年10月9日 下午5:22:45 WangLijun$
*/
package com.newtouch.lion.test; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.newtouch.lion.MyBatisAllTest;
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
 
public class CountryServiceTest extends MyBatisAllTest{

	@Autowired
	private CountryService countryService;
	
	@Test
	public void findPage(){
		PageInfo<Country>  pageInfo=countryService.findPage();
		logger.info(pageInfo.toString());
	}
	
}


	