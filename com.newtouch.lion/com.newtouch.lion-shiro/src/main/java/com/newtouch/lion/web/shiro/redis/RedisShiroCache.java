/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisShiroCache.java 9552 2015年8月4日 下午4:00:29 WangLijun$
*/
package com.newtouch.lion.web.shiro.redis; 

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.TypeReference;
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
public class RedisShiroCache<K, V> implements Cache<K, V> {
	/**日志*/
	@SuppressWarnings("unused")
	private static final Logger logger=LoggerFactory.getLogger(RedisShiroCache.class);
	/**Redis key 默认值*/
    public static final String DEFAULT_REDIS_SHIRO_CACHE = "shiro:cache:";
	/**Redis的接口*/
	private IBinaryRedisClient binaryRedisClient;
	/**Redis普通接口*/
	private IRedisClient redisClient;
	/**实体类*/
	private TypeReference<V> typeReference;
	/**KEY的前缀*/
	private String cachePrefixKey;
	/**前缀*/
	private String name;
	
 
 

	/**
	 * @param binaryRedisClient
	 * @param redisClient
	 * @param cachePrefixKey
	 */
	@SuppressWarnings("unchecked")
	public RedisShiroCache(String name,IBinaryRedisClient binaryRedisClient,
			IRedisClient redisClient,String cachePrefixKey) {
		this.name=name;
		this.binaryRedisClient = binaryRedisClient;
		this.redisClient = redisClient;
		this.cachePrefixKey = cachePrefixKey;
		Type[] actualTypeArguments = ((ParameterizedType) super.getClass()
				.getGenericSuperclass()).getActualTypeArguments();
		this.typeReference = (TypeReference<V>) actualTypeArguments[0];
	}

	@Override
	public V get(K key) throws CacheException {
		return binaryRedisClient.get(this.buildCacheKey(key),this.typeReference);
	}

	@Override
	public V put(K key, V value) throws CacheException {
		this.binaryRedisClient.setObject(this.buildCacheKey(key), value);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		V value=this.get(key);
		this.binaryRedisClient.remove(this.buildCacheKey(key));
		return value;
	}

	@Override
	public void clear() throws CacheException {
		
	}	

	@Override
	public int size() {
		Set<K> keys=this.keys();
		if(CollectionUtils.isEmpty(keys)){
			return 0;
		}
		return keys.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		return (Set<K>) this.redisClient.keys(this.cachePrefixKey+":*");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		return  (Collection<V>) this.redisClient.hvals(this.cachePrefixKey+":*");
	}


	/**
	 * @param cachePrefixKey the cachePrefixKey to set
	 */
	public void setCachePrefixKey(String cachePrefixKey) {
		this.cachePrefixKey = cachePrefixKey;
	}


	/**
	 * @return the cachePrefixKey
	 */
	public String getCachePrefixKey() {
		return cachePrefixKey;
	}
	
	private String buildCacheKey(Object key) {
		if(StringUtils.isEmpty(cachePrefixKey)){
			this.cachePrefixKey=DEFAULT_REDIS_SHIRO_CACHE;
		}
        return this.cachePrefixKey+this.getName()+":"+key;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		if(StringUtils.isEmpty(name)){
			return StringUtils.EMPTY;
		}
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}

	