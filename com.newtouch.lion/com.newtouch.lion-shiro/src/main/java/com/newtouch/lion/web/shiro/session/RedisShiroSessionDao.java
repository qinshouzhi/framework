/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisShiroSessionDao.java 9552 2015年11月3日 下午9:23:33 WangLijun$
*/
package com.newtouch.lion.web.shiro.session; 

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class RedisShiroSessionDao extends AbstractSessionDAO {
	/***
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(RedisShiroSessionDao.class);
	/**Session存储*/
	private   ShiroSessionRepository shiroSessionRepository;
	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.session.Session)
	 */
	@Override
	public void update(Session session) throws UnknownSessionException {
		 logger.debug("update session id:{}",session.getId());
		 this.shiroSessionRepository.saveSession(session);
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.session.Session)
	 */
	@Override
	public void delete(Session session) {
		if(session==null||session.getId()==null){
			logger.warn("delete session is empty");
			return;
		}
		logger.debug("delete session:{}",session.getId());
		this.shiroSessionRepository.deleteSession(session.getId());
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions()
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		logger.debug("get active sessions");
		return this.getShiroSessionRepository().getAllSessions();
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
	 */
	@Override
	protected Serializable doCreate(Session session) {
		logger.debug("do create session");
		Serializable sessionId=this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.shiroSessionRepository.saveSession(session);
		logger.debug("sessionId:{}",sessionId);
		return sessionId;
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable)
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.debug("do read session id:{}",sessionId);
		return this.shiroSessionRepository.getSession(sessionId);
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
	public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	}
}

	