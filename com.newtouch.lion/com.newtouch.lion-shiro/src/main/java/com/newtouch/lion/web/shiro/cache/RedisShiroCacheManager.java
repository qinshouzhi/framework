/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisShiroCacheManager.java 9552 2015年8月4日 下午3:45:08 WangLijun$
*/
package com.newtouch.lion.web.shiro.cache; 

import org.apache.shiro.ShiroException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.redis.client.IBinaryRedisClient;
import com.newtouch.lion.redis.client.IRedisClient;
import com.newtouch.lion.web.shiro.redis.RedisShiroCache;

/**
 * <p>
 * Title: 将Shiro会话信息缓存到Redis
 * </p>
 * <p>
 * Description: 将Shiro会话信息缓存到Redis
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
public class RedisShiroCacheManager implements CacheManager, Initializable, Destroyable{
	/**
	 * 日志
	 * */
	private static final Logger logger=LoggerFactory.getLogger(RedisShiroCacheManager.class);
	
	/**Redis的接口*/
	private IBinaryRedisClient binaryRedisClient;
	/**Redis普通接口*/
	private IRedisClient redisClient;

	
	private String cachePrefixKey;
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
 		return new RedisShiroCache<K, V>(name,binaryRedisClient, redisClient,cachePrefixKey);
	}

	@Override
	public void destroy() throws Exception {
		logger.info("destory");
	}

	@Override
	public void init() throws ShiroException {
		logger.info("destory");
	}

	/**
	 * @param binaryRedisClient the binaryRedisClient to set
	 */
	public void setBinaryRedisClient(IBinaryRedisClient binaryRedisClient) {
		this.binaryRedisClient = binaryRedisClient;
	}

	/**
	 * @return the redisClient
	 */
	public IRedisClient getRedisClient() {
		return redisClient;
	}

	/**
	 * @param redisClient the redisClient to set
	 */
	public void setRedisClient(IRedisClient redisClient) {
		this.redisClient = redisClient;
	}

	/**
	 * @return the cachePrefixKey
	 */
	public String getCachePrefixKey() {
		return cachePrefixKey;
	}

	/**
	 * @param cachePrefixKey the cachePrefixKey to set
	 */
	public void setCachePrefixKey(String cachePrefixKey) {
		this.cachePrefixKey = cachePrefixKey;
	}

	/**
	 * @return the binaryRedisClient
	 */
	public IBinaryRedisClient getBinaryRedisClient() {
		return binaryRedisClient;
	}
	
	
	
	
}

	