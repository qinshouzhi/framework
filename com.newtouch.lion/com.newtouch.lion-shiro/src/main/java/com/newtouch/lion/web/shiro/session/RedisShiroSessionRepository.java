/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisShiroSessionRepository.java 9552 2015年11月3日 下午5:58:57 WangLijun$
*/
package com.newtouch.lion.web.shiro.session; 

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.redis.client.IBinaryRedisClient;
import com.newtouch.lion.web.shiro.constant.Constants;

/**
 * <p>
 * Title: Redis Shiro Session　实现
 * </p>
 * <p>
 * Description: Redis Shiro Session　实现
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
public class RedisShiroSessionRepository  implements ShiroSessionRepository {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(RedisShiroSessionRepository.class);
	/***
	 * Redis Key前缀
	 */
	private String redisKeyPrefix=Constants.REDIS_SHIRO_SESSION;
	/**Redis Key 有效时间*/
	private Integer expireTime=Constants.SESSION_VAL_TIME_SPAN;
	/**Redis 接口对象*/
	private IBinaryRedisClient binaryRedisClient;
	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.session.ShiroSessionRepository#saveSession(org.apache.shiro.session.Session)
	 */
	@Override
	public void saveSession(Session session) {
		if(session==null||session.getId()==null){
			logger.debug("session is emtpy");
			throw new NullPointerException("session is empty");
		}
		Long sessionTimeOut = session.getTimeout() / 1000;
		//key 超时
		int timeOut = (int) (sessionTimeOut + this.expireTime + (5 * 60));
		this.binaryRedisClient.setex(this.buildRedisKey(session.getId()), session, timeOut);
	}
	/***
	 * 构建session的KEY,规则为：前缀+key
	 * @param sessionId
	 * @return
	 */
	private String buildRedisKey(Serializable sessionId){
		return this.redisKeyPrefix+sessionId;
	}
	
	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.session.ShiroSessionRepository#deleteSession(java.io.Serializable)
	 */
	@Override
	public void deleteSession(Serializable sessionId) {
		if(sessionId==null){
			logger.debug("sessionId is emtpy");
			throw new NullPointerException("sessionId is emtpy");
		}
		this.binaryRedisClient.removeObject(this.buildRedisKey(sessionId));
	}
	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.session.ShiroSessionRepository#getSession(java.io.Serializable)
	 */
	@Override
	public Session getSession(Serializable sessionId) {
		if(sessionId==null){
			logger.debug("sessionId is emtpy");
			throw new NullPointerException("sessionId is emtpy");
		}
		return this.binaryRedisClient.get(this.buildRedisKey(sessionId), Session.class);
	}
	/* (non-Javadoc)
	 * @see com.newtouch.lion.web.shiro.session.ShiroSessionRepository#getAllSessions()
	 */
	@Override
	public Collection<Session> getAllSessions() {
		//TODO
		return null;
	}
	/**
	 * @return the redisKeyPrefix
	 */
	public String getRedisKeyPrefix() {
		return redisKeyPrefix;
	}
	/**
	 * @param redisKeyPrefix the redisKeyPrefix to set
	 */
	public void setRedisKeyPrefix(String redisKeyPrefix) {
		this.redisKeyPrefix = redisKeyPrefix;
	}
	/**
	 * @return the expireTime
	 */
	public Integer getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
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

	