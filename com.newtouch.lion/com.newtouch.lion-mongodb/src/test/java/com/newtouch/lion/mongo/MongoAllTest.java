/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: MongoAllTest.java 9552 2015年5月11日 下午1:48:19 WangLijun$
*/
package com.newtouch.lion.mongo; 

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * <p>
 * Title: Mongo测试数据库
 * </p>
 * <p>
 * Description: Mongo测试数据库
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
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(locations = { "classpath*:applicationContext-mongo.xml","classpath*:applicationContext-common.xml"})
public class MongoAllTest extends AbstractJUnit4SpringContextTests{
	/**日志*/
	protected  final Logger logger=LoggerFactory.getLogger(super.getClass());

}

	