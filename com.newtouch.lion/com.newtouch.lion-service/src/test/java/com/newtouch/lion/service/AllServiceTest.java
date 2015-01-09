/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AllServiceTest.java 9552 2015年1月5日 上午10:35:01 WangLijun$
*/
package com.newtouch.lion.service; 

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Title: Service层测试抽象类
 * </p>
 * <p>
 * Description:  Service层测试抽象类
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
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public abstract class AllServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	/**日志*/
	protected final Logger logger = LoggerFactory.getLogger(super.getClass());

}

	