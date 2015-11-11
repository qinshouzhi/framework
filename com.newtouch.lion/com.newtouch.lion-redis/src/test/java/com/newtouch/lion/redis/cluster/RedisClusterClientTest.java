/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisClusterClientTest.java 9552 2015年7月11日 下午9:12:34 WangLijun$
*/
package com.newtouch.lion.redis.cluster; 

 

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.newtouch.lion.redis.RedisClusterAllTest;
import com.newtouch.lion.redis.client.IBinaryRedisClient;
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
	@Qualifier("redisClient")
	private IRedisClient redisClient;
	
	@Autowired
	@Qualifier("binaryRedisClient")
	private IBinaryRedisClient binaryRedisClient;
	
	@Test
	public void set(){
		for (int i = 1000; i < 2000; i++) {
			String key = "key:" + i;
			redisClient.set("watchmen:"+i,key);
		}
	}
	
	@Test
	public void setMap(){
		Map test = new HashMap();
		test .put("test", "ccc");
		test .put("test1", "ccc");
		binaryRedisClient.set("key:map1",test);
		Map  map=binaryRedisClient.get("key:map1", Map.class);
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

	