/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ZookeeperAllTest.java 9552 2015年5月25日 下午3:39:32 WangLijun$
*/
package com.newtouch.lion.zookeeper; 

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * Title: Zookeeper 测试抽像类
 * </p>
 * <p>
 * Description: Zookeeper 测试抽像类
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
@ContextConfiguration(locations = { "classpath*:applicationContext-zookeeper.xml","classpath*:applicationContext-common.xml"})
public class ZookeeperAllTest extends  AbstractJUnit4SpringContextTests{
	/***
	 * 日志
	 */
	protected final Logger logger=LoggerFactory.getLogger(super.getClass());
}

	