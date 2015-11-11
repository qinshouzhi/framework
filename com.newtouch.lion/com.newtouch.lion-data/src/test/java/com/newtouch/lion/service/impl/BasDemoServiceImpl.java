/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: BasDemoSeviceImpl.java 9552 2015��9��16�� ����4:26:18 WangLijun$
*/
package com.newtouch.lion.service.impl; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newtouch.lion.dao.DemoDao;
import com.newtouch.lion.mapper.BasDemoDao;
import com.newtouch.lion.model.BasDemo;
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
public class BasDemoServiceImpl implements BasDemoService {
	
	@Autowired
	private BasDemoDao basDemoDao;
	
	@Autowired
	private DemoDao demoDao;

	/* (non-Javadoc)
	 * @see com.aoseala.test.service.BasDemoSevice#save()
	 */
	@Override
	public void insert(BasDemo basDemo) {
		basDemoDao.insert(basDemo);
	}

	/* (non-Javadoc)
	 * @see com.newtouch.lion.service.BasDemoService#findById(java.lang.Long)
	 */
	@Override
	public BasDemo findById(Long id) {
		 return this.demoDao.getById(id);
	}

	@Override
	public int deleteById(Long id) {
		 return this.demoDao.delete(id);
	}
}	

	