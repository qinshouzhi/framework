/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisClusterClientTest.java 9552 2015年7月11日 下午9:12:34 WangLijun$
*/
package com.newtouch.lion.redis.cluster; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.newtouch.lion.redis.RedisClusterAllTest;
import com.newtouch.lion.redis.client.IRedisClient;

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
public class RedisClusterClientTest extends RedisClusterAllTest{
	
	@Autowired
	@Qualifier("springRedisClusterClient")
	private IRedisClient redisClient;
	
	@Test
	public void set(){
		for (int i = 1000; i < 2000; i++) {
			String key = "key:" + i;
			redisClient.set("watchmen:"+i,key);
		}
	}
	
	@Test
	public void query()  {
	 
	 
		for (int i = 0; i < 2000; i++) {
			String key = "key:" + i;
			String value=redisClient.get("watchmen:"+i);
			logger.info("{},value:{}",key,value);
		}
	 
	}
}

	