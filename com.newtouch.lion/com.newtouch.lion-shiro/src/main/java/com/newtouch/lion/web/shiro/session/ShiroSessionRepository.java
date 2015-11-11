/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: ShiroSessionRepository.java 9552 2015年11月2日 上午11:58:18 WangLijun$
*/
package com.newtouch.lion.web.shiro.session; 

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * <p>
 * Title: Shiro Session
 * </p>
 * <p>
 * Description: Shiro Session 接口定义
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
public interface ShiroSessionRepository {
	/***
	 * 保存session
	 * @param session
	 */
	public void saveSession(Session session);
	/**
	 * 根据sessionId删除Session
	 * @param sessionId
	 */
	public void deleteSession(Serializable sessionId);
	/***
	 * 根据session获取session
	 * @param sessionId
	 * @return
	 */
	public Session getSession(Serializable sessionId);
	/***
	 * 获取所有会话信息
	 * @return
	 */
	public Collection<Session> getAllSessions();
}

	