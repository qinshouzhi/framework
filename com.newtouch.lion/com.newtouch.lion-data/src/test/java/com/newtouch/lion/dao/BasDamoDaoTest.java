/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: BasDamoDaoTest.java 9552 2015年9月16日 上午11:36:43 WangLijun$
 */
package com.newtouch.lion.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.AllMyBatisTest;
import com.newtouch.lion.dao.demo.BasDemoDao;
import com.newtouch.lion.model.demo.BasDemo;

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
public class BasDamoDaoTest extends AllMyBatisTest {
	
	@Autowired
	BasDemoDao basDemoDao;
	
	
	@Test
	public void find(){
		BasDemo basDemo=basDemoDao.find(1L);
		logger.info(basDemo.toString());
	}

}
