 
/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisGetTest.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis; 

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
public class RedisGetTest  extends RedisAllTest{
	
	 /*
	*  二进制的redis. */
    @Autowired
    private IBinaryRedisClient binaryRedisClient;
    /** 缓存接口. */
    @Autowired
    private IRedisClient redisClient;
	
	@Test
	public void queryGet(){
		String key="watchmen:20150410";
		String str=redisClient.get(key);
		
		logger.info("1111111111111111:str:{}",str);
		 
	}
	

	@Test
	public void  set(){
		String key="watchmen:20150410";
		redisClient.set(key,"test Cluster");
		//logger.info("1111111111111111:str:{}",str);
		 
	}
	
	
	@Test
	public void lpush(){
		redisClient.lpush("news.share1", "OK");
	}
	
	@Test
	public void push(){
		redisClient.push("watchmen:panel:notify:ok", "OK");
	}
	
	@Test
	public void pushNofityFail(){
		redisClient.push("watchmen:panel:notify:fail", "OK");
	}
}
	
