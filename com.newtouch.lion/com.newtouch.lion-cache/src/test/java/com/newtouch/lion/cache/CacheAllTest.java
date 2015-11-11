/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CacheAllTest.java 9552 2015年7月17日 下午10:21:30 WangLijun$
*/
package com.newtouch.lion.cache; 

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-cache.xml"})
public class CacheAllTest extends AbstractJUnit4SpringContextTests{
	/**日志*/
	protected  final Logger logger=LoggerFactory.getLogger(super.getClass());
}

	