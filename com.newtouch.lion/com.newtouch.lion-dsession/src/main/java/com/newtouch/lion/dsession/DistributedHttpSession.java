/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DistributedSession.java 9552 2015年5月21日 下午2:01:30 WangLijun$
*/
package com.newtouch.lion.dsession; 

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.dsession.config.DistributedSessionConfig;
import com.newtouch.lion.dsession.context.DistributedRequestContext;

/**
 * <p>
 * Title: 分布式会话session
 * </p>
 * <p>
 * Description: 实现HttpSession
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
@SuppressWarnings("deprecation")
public class DistributedHttpSession  implements HttpSession{
	/***
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(DistributedHttpSession.class);
	  /**
     * sessionId
     */
    private String sessionId;
	 /**
     * 是否有效invalidated
     */
    private boolean invalidated = false;
    /**Session配置信息*/
    private DistributedSessionConfig distributedSessionConfig;
    /**请求上下文根*/
    private DistributedRequestContext distributedRequestContext;

	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxInactiveInterval() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putValue(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeValue(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the invalidated
	 */
	public boolean isInvalidated() {
		return invalidated;
	}

	/**
	 * @param invalidated the invalidated to set
	 */
	public void setInvalidated(boolean invalidated) {
		this.invalidated = invalidated;
	}
}

	