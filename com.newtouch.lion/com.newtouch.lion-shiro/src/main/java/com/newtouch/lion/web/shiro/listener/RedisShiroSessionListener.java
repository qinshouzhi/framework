/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisShiroSessionListener.java 9552 2015年11月3日 下午9:37:00 WangLijun$
*/
package com.newtouch.lion.web.shiro.listener; 

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.web.shiro.session.RedisShiroSessionDao;
import com.newtouch.lion.web.shiro.session.ShiroSessionRepository;

/**
 * <p>
 * Title: 扩展Redis Shiro监听器
 * </p>
 * <p>
 * Description: 扩展Redis Shiro监听器
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
public class RedisShiroSessionListener implements SessionListener{
	/**日志*/
	/***
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(RedisShiroSessionDao.class);
	/**Session存储*/
	private   ShiroSessionRepository shiroSessionRepository;

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.SessionListener#onStart(org.apache.shiro.session.Session)
	 */
	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		logger.info("on start");
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.SessionListener#onStop(org.apache.shiro.session.Session)
	 */
	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		logger.info("on stop");
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.SessionListener#onExpiration(org.apache.shiro.session.Session)
	 */
	@Override
	public void onExpiration(Session session) {
		logger.info(" on expiration, delete session for redis");
		this.shiroSessionRepository.deleteSession(session.getId());
	}

	/**
	 * @return the shiroSessionRepository
	 */
	public ShiroSessionRepository getShiroSessionRepository() {
		return shiroSessionRepository;
	}

	/**
	 * @param shiroSessionRepository the shiroSessionRepository to set
	 */
	public void setShiroSessionRepository(
			ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}
	
	
	
}

	