/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: DistributedContextUtil.java 9552 2015年5月21日 下午4:11:47 WangLijun$
 */
package com.newtouch.lion.dsession.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.newtouch.lion.dsession.config.DistributedCookieAttributeConfig;
import com.newtouch.lion.dsession.context.DistributedRequestContext;
import com.newtouch.lion.dsession.context.DistributedSessionContext;
import com.newtouch.lion.dsession.store.DistributedCookieStore;
import com.newtouch.lion.session.common.RequestContextUtils;
import com.newtouch.lion.session.common.SessionConstant;

/**
 * <p>
 * Title: 分布式会话上下文的工具类
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
public class DistributedContextUtil {
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(RequestContextUtils.class);
	
	 /**
     * 
     * 功能描述:根据requestContext写cookie <br>
     * 〈功能详细描述〉
     *
     * @param requestContext request context 
     * @param key key
     * @param value value
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void writeKeyValueToCookie(DistributedSessionContext context, String key, String value) {
    	DistributedCookieAttributeConfig cookieConfigModel = null;
        if (context.getDistributedCookieConfig()!= null) {
            DistributedCookieStore cookieStore = context.getDistributedCookieConfig().getCookieStore(key);
            if (cookieStore != null) {
                cookieConfigModel = cookieStore.getDistributedCookieAttributeConfig();
            }
        }
        Cookie cookie = getCookie(context.getRequest(), key);        
        logger.debug("get cookie from request and value is " + cookie);
        // 如果cookie不为null并且新值与老值不相等,则设置新的cookie
        if(cookie != null && cookie.getValue().equals(value)){
            return;
        } else {
            cookie = new Cookie(key, value);
        }        
        String cookieDomain = null;
        String cookiePath = null;
        int cookieMaxAge = -1;
        boolean secure = false;
        cookieDomain = cookieConfigModel.getDomain();
        // 根据HTTP请求的头域Host决定Cookie的域值
        String host = context.getRequest().getHeader("Host");
        // 提取HOST
        String decodedHost = decodeHostFromUrl(host);
        // 如果HOST为空,则使用默认的配置
        cookieDomain = StringUtils.isEmpty(decodedHost) ? cookieDomain : decodedHost;
        logger.info("real cookie domain is " + cookieDomain);
        cookiePath = cookieConfigModel.getPath();
        cookieMaxAge = cookieConfigModel.getMaxAge();
        secure = cookieConfigModel.isSecure();
        cookie.setDomain(cookieDomain);
        cookie.setPath(cookiePath);
        cookie.setMaxAge(cookieMaxAge);
        cookie.setSecure(secure);
        logger.info("Set-cookie: {}-{}", cookie.getName(), cookie.getValue());
        context.getResponse().addCookie(cookie);
    }
    
    
    /**
     * 
     * 功能描述: 根据requestContext读cookie<br>
     * @param requestContext request context
     * @param keys keys
     * @return map
     */
    public static Map<String, Cookie> getCookiesFromCookie(DistributedSessionContext context, List<String> keys) {
        Map<String, Cookie> result = new HashMap<String, Cookie>();
        Cookie[] cookies = context.getRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                for (String key : keys) {
                    if (cookie.getName().equals(key)) {
                        result.put(key, cookie);
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * 
     * 功能描述: <br>
     * 从URL中提取服务地址
     *
     * @param host
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static String decodeHostFromUrl(String host){
        String regexDomain = "(?!h|t|t|p|:|/)[a,z]*.*(com|net|org|info|cn)";
        Pattern pattern = Pattern.compile(regexDomain);
        Matcher matcher = pattern.matcher(host);
        while(matcher.find()){
            return matcher.group(0);            
        }
        String regexIP = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
        Pattern p = Pattern.compile(regexIP);
        Matcher m = p.matcher(host);
        while(m.find()){
            return m.group(0);
        }
        return null;
    }
    
    public static Cookie getCookie(HttpServletRequest request, String cname){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cname)){
                    return cookie;
                }
            }
        }        
        return null;
    }
	
	 /**
     * 
     * 功能描述: 获取域名 <br>
     * @param req request 
     * @return domain name
     */
    public static String getDomain(ServletRequest req) {
        String serverName = req.getServerName();
        if (serverName.lastIndexOf(".") != -1) {
            int lastDot = serverName.lastIndexOf(".");
            int last2Dot = serverName.substring(0, lastDot).lastIndexOf(".");
            return serverName.substring(last2Dot);
        }
        return serverName;
    }

	/***
	 * 从cookie中取得session ID
	 * @param distributedRequestContext 上下文根
	 * @return String sessionId
	 */
	public static String decodeSessionIdFromURL(
			DistributedRequestContext distributedRequestContext) {
		String uri = distributedRequestContext.getRequest().getRequestURI();
		String keyName = SessionConstant.SESSION_ID;
		int uriLength = uri.length();
		int keyNameLength = keyName.length();

		for (int keyBeginIndex = uri.indexOf(';'); keyBeginIndex >= 0; keyBeginIndex = uri
				.indexOf(';', keyBeginIndex + 1)) {
			keyBeginIndex++;

			if (uriLength - keyBeginIndex <= keyNameLength
					|| !uri.regionMatches(keyBeginIndex, keyName, 0,
							keyNameLength)
					|| uri.charAt(keyBeginIndex + keyNameLength) != '=') {
				continue;
			}

			int valueBeginIndex = keyBeginIndex + keyNameLength + 1;
			int valueEndIndex = uri.indexOf(';', valueBeginIndex);

			if (valueEndIndex < 0) {
				valueEndIndex = uriLength;
			}

			return uri.substring(valueBeginIndex, valueEndIndex);
		}

		return null;
	}

	/**
	 * 功能描述: 从cookie中取得session ID。<br> 
	 * @param DistributedRequestContext        distributedRequestContext
	 * @return 如果存在，则返回session ID，否则返回
	 */
	public static String decodeSessionIdFromCookie(DistributedRequestContext distributedRequestContext) {
		Cookie[] cookies = distributedRequestContext.getRequest().getCookies();

		if (cookies != null) {

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(SessionConstant.SESSION_ID)) {
					String sessionID = StringUtils.trimWhitespace(cookie
							.getValue());
					if (sessionID != null) {
						return sessionID;
					}
				}
			}
		}
		return null;
	}
}
