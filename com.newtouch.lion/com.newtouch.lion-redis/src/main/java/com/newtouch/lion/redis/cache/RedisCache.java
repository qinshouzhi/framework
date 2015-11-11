/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCache.java 9552 2015年8月12日 下午3:21:22 WangLijun$
*/
package com.newtouch.lion.redis.cache; 

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.newtouch.lion.common.constant.Constants;
import com.newtouch.lion.redis.client.IBinaryRedisClient;

/**
 * <p>
 * Title:对Spring Cache 的扩展
 * </p>
 * <p>
 * Description: 对Spring Cache 的扩展
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
public class RedisCache  implements Cache{
	/**日志处理*/
	private static final Logger logger=LoggerFactory.getLogger(RedisCache.class);
	/**Redis的接口*/
	private IBinaryRedisClient binaryRedisClient;
	/**缓存名称*/
	private String name;
	/**key的前缀*/
	private String prefix;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.binaryRedisClient;
	}

	@Override
	public ValueWrapper get(Object key) {
		 Object object=this.binaryRedisClient.getObject(this.buildKey(key));
		 return (object != null ? new SimpleValueWrapper(object) : null); 
	}

	@Override
	public void put(Object key, Object value) {
		String tempKey=this.buildKey(key);
		this.binaryRedisClient.setObject(tempKey, value);
	}

	@Override
	public void evict(Object key) {
		 
	}

	@Override
	public void clear() {
		
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	
	private String buildKey(Object key) {
		if(StringUtils.isEmpty(this.prefix)){
			 return  this.getName()+Constants.COLON+key; 
		}
        return this.prefix+Constants.COLON+this.getName()+Constants.COLON+key;
    }

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * @return the binaryRedisClient
	 */
	public IBinaryRedisClient getBinaryRedisClient() {
		return binaryRedisClient;
	}

	/**
	 * @param binaryRedisClient the binaryRedisClient to set
	 */
	public void setBinaryRedisClient(IBinaryRedisClient binaryRedisClient) {
		this.binaryRedisClient = binaryRedisClient;
	}
}

	