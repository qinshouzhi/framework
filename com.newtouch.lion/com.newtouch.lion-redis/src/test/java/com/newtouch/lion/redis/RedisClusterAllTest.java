/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisClusterAllTest.java 9552 2015年7月11日 下午9:12:57 WangLijun$
*/
package com.newtouch.lion.redis; 

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Title: 集群测试抽像类
 * </p>
 * <p>
 * Description: 集群测试抽像类
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
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration(locations = {"classpath*:applicationContext-redis.xml","classpath*:applicationContext-rediscluster.xml","classpath*:applicationContext-common.xml"})
public class RedisClusterAllTest extends AbstractJUnit4SpringContextTests{
	/**日志*/
	protected  final Logger logger=LoggerFactory.getLogger(super.getClass());
}

	