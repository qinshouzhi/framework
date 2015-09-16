/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemoServiceImpl.java 9552 2015年9月16日 下午2:30:17 WangLijun$
*/
package com.newtouch.lion.service.impl; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.dao.demo.BasDemoDao;
import com.newtouch.lion.model.demo.BasDemo;
import com.newtouch.lion.service.BasDemoService;

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
public class BasDemoServiceImpl implements BasDemoService{
	
	private static final Logger logger=LoggerFactory.getLogger(BasDemoServiceImpl.class);
	
	//@Autowired
	//private BasDemoDao basDemoDao;

	@Override
	public BasDemo save(BasDemo basDemo) {
		 logger.info(basDemo.toString());
		 return basDemo;
	}
	
}

	