/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisGetTest.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package org.com.newtouch.lion.redis; 

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
	
	
	 /**  二进制的redis. */
    @Autowired
    @Qualifier("springBinaryRedisClient")
    private IBinaryRedisClient springBinaryRedisClient;
    /** 缓存接口. */
    @Autowired
    private IRedisClient springRedisClient;
	
	@Test
	public void queryGet(){
		String key="watchmen:20150410";
		String str=springRedisClient.get(key);
		
		logger.info("1111111111111111:str:{}",str);
		 
	}
	
	@Test
	public void lpush(){
		springRedisClient.lpush("news.share1", "OK");
	}
	
	@Test
	public void push(){
		springRedisClient.push("watchmen:panel:notify:ok", "OK");
	}
	
	@Test
	public void pushNofityFail(){
		springRedisClient.push("watchmen:panel:notify:fail", "OK");
	}
}

	