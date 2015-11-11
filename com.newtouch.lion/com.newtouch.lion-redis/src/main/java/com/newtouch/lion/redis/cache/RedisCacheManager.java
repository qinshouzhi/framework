/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheManager.java 9552 2015年8月12日 下午4:25:29 WangLijun$
*/
package com.newtouch.lion.redis.cache; 

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * <p>
 * Title:Redis Cache Manager 扩展Spring CacheManger
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
public class RedisCacheManager implements CacheManager,InitializingBean{
	/**日志*/
	private static final Logger Logger=LoggerFactory.getLogger(RedisCacheManager.class);
	/**Cache集合*/
	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>(16);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//初始化XML数据
	}

	@Override
	public Cache getCache(String name) {
		return  this.cacheMap.get(name);
	}

	@Override
	public Collection<String> getCacheNames() {
		
		return cacheMap.keySet();
	}

}

	