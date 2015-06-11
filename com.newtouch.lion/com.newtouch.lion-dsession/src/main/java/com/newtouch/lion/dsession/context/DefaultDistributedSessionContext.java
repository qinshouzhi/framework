/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: DefaultDistributedContext.java 9552 2015年5月21日 下午3:02:58 WangLijun$
*/
package com.newtouch.lion.dsession.context; 

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.newtouch.lion.dsession.DistributedHttpSession;
import com.newtouch.lion.dsession.config.DistributedCookieConfig;
import com.newtouch.lion.dsession.config.DistributedSessionConfig;
import com.newtouch.lion.dsession.util.DistributedContextUtil;
import com.newtouch.lion.session.common.SessionConstant;

/**
 * <p>
 * Title: 分布式会话上下文默认实现
 * </p>
 * <p>
 * Description: 分布式会话上下文默认实现
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
public class DefaultDistributedSessionContext extends AbstractDistributedRequestContext  implements DistributedSessionContext {
	/**
	 * 日志
	 */
	private static final Logger logger=LoggerFactory.getLogger(DefaultDistributedSessionContext.class);
	
	/***会话ID*/
	private String sessionId;
	/**会话消息SessionIDFromCookie;*/
	private boolean sessionIdFromCookie;
	/**会话URL*/
	private boolean sessionIdFormURL;
	/**会话*/
	private DistributedSessionConfig distributedSessionConfig;
	/**Cookie*/
	private DistributedCookieConfig distributedCookieConfig;
	/**分布式Session*/
	private DistributedHttpSession distributedHttpSession;
	
	/***
	 * 默认构造函数
	 * @param distributedContext
	 */
	public DefaultDistributedSessionContext(DistributedRequestContext distributedContext) {
		 super(distributedContext);
	}
	
	/**
	 * @param servletContext
	 * @param request
	 * @param response
	 * @param sessionConfig
	 * @param distributedCookieConfig
	 */
	public DefaultDistributedSessionContext(ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response,
			DistributedSessionConfig distributedSessionConfig, DistributedCookieConfig distributedCookieConfig) {
		super(servletContext, request, response);		
		super.setRequest(new DistributedHttpServletRequestWrapper(request));
		super.setResponse(new DistributedHttpServletResponseWrapper(response));
		this.distributedSessionConfig = distributedSessionConfig;
		this.distributedCookieConfig = distributedCookieConfig;
	}

	
	 /**
     * 
     * 功能描述: 取得当前的session，如果不存在，则创建一个新的。<br>
     * 〈功能详细描述〉
     *
     * @param create boolean
     * @return 当前的session或新的session， 如果不存在则返回
     */
    public HttpSession getSession(boolean create) {
    	if(this.distributedHttpSession!=null&&this.distributedHttpSession.get){
    		
    	}
    }

	@Override
	public DistributedSessionConfig getDistributedSessionConfig() {
		return this.distributedSessionConfig;
	}

	@Override
	public DistributedCookieConfig getCookieConfig() {
		return this.distributedCookieConfig;
	}

	@Override
	public boolean isSessionInvalidated() {
		 return distributedHttpSession == null ? false : distributedHttpSession.isInvalidated();
	}



	@Override
	public DistributedRequestContext getDdistributedContext() {
		
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletRequest getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletRequest getOriginalRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse getOriginalResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionId() {
		if(StringUtils.hasLength(this.sessionId)){
			return this.sessionId;
		}
	     // 首先从URL中获取sessionId
        this.sessionId = decodeSessionIdFromURL();
        this.sessionIdFormURL=(this.sessionId!=null);
        if(StringUtils.isEmpty(this.sessionId)){
        	this.sessionId=this.decodeSessionIDFromCookie();
        	this.sessionIdFromCookie=(this.sessionId!=null);
        }
		return this.sessionId;
	}
	
	 /**
     * 功能描述:将session ID编码到Cookie中去。 <br>
     * @param sessionId String
     */
    public void encodeSessionIDIntoCookie(String sessionId) {
        writeSessionIDToCookie(sessionId);
    }
    
    
    

    /**
     * 
     * 功能描述:写cookie。 <br>
     *
     * @param cookieValue String

     */
    private void writeSessionIDToCookie(String cookieValue) {
    	DistributedContextUtil.writeKeyValueToCookie(this,SessionConstant.SESSION_ID, cookieValue);
    }
	
	 /**
     * 从URL中取得session ID。
     * @return 如果存在，则返回session ID，否则返回<code>null</code>
     */
    public String decodeSessionIdFromURL() {
        return  DistributedContextUtil.decodeSessionIdFromURL(this);
    }
    
    /**
     * 从cookie中取得session ID。
     * @return 如果存在，则返回session ID，否则返回<code>null</code>
     */
    public String decodeSessionIDFromCookie() {
        return DistributedContextUtil.decodeSessionIdFromCookie(this);
    }

	 

	@Override
	public void clear() {
		
	}
	/***
	 * 内部类－分布式请求处理
	 * @author wanglijun
	 *
	 */
	private class DistributedHttpServletRequestWrapper extends HttpServletRequestWrapper{
		/***
		 * 构造函数
		 * @param request
		 */
		public DistributedHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
			 
		}
		
		@Override
		public String getRequestedSessionId() {			 
			return  DefaultDistributedSessionContext.this.getSessionId();
		}
		
	}
	
	/***
	 * 内部类－分布式响应处理
	 * @author wanglijun
	 *
	 */
	private class DistributedHttpServletResponseWrapper  extends HttpServletResponseWrapper{
		/***
		 * 构造函数
		 * @param response
		 */
		public DistributedHttpServletResponseWrapper(
				HttpServletResponse response) {
			super(response);
			
		}
		
		
		@Override
		public String encodeUrl(String url){
			return super.encodeUrl(url);
		}
		
		@Override
		public String encodeURL(String url) {
			return super.encodeURL(url);
		}
		
		@Override
		public String encodeRedirectUrl(String url) {
			
			return super.encodeRedirectUrl(url);
		}
		
		 /**
         * 将session ID编码到URL中。
         * @param url 要编码的URL
         * @return 包含session ID的URL
         */
		@Override
		public String encodeRedirectURL(String url) {
			return super.encodeRedirectURL(url);
		}
		
	}

	
	
}

	