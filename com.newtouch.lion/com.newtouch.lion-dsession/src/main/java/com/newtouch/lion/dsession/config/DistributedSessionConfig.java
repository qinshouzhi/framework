/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: SessionConfig.java 9552 2015年5月21日 下午2:22:20 WangLijun$
*/
package com.newtouch.lion.dsession.config; 

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionListener;

import com.newtouch.lion.dsession.store.SessionStore;

/**
 * <p>
 * Title: 会话存取
 * </p>
 * <p>
 * Description: 会话存取
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
public class DistributedSessionConfig {
	 /***
	  * 会话监控听器列表
	  */
	  private List<HttpSessionListener> listerners = new ArrayList<HttpSessionListener>();
	  /***
	   * 会话存取列表
	   */
	  private List<SessionStore> sessionStores = new ArrayList<SessionStore>();
	  
	   /**
	    * 会话保持时间，单位。秒
	    */
	   private int maxInactiveInterval;
}

	