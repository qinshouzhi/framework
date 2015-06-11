package com.newtouch.lion.session.store.impl.cookie;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.newtouch.lion.session.config.CookieAttributeConfig;
import com.newtouch.lion.session.context.RequestContext;
import com.newtouch.lion.session.context.SessionRequestContext;
import com.newtouch.lion.session.store.DdsCookieStore;

/**
 * 
 * 〈default cookie store〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsDefaultCookieStore implements DdsCookieStore {

    /**
     * logger
     */
    private static final  Logger    LOGGER = LoggerFactory.getLogger(DdsDefaultCookieStore.class);

    /**
     * CookieAttributeConfig
     */
    @Autowired
    private CookieAttributeConfig cookieAttributeConfig;

    @Override
    public void invalidate(RequestContext requestContext) {
        Cookie[] cookies = requestContext.getRequest().getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                String cookieName = cookie.getName();
                String decodeCookieName = cookieName;
                if(cookieAttributeConfig.getEncoder() != null){
                    decodeCookieName = cookieAttributeConfig.getEncoder().encodeName(cookieName);
                }
                if(cookieAttributeConfig.isMatch(decodeCookieName)){
                    cookie.setDomain(cookieAttributeConfig.getDomain());
                    cookie.setPath(cookieAttributeConfig.getPath());
                    cookie.setSecure(cookieAttributeConfig.isSecure());
                    cookie.setMaxAge(0);
                    requestContext.getResponse().addCookie(cookie);
                }
            }
        }
    }

    @Override
    public Object getAttribute(RequestContext requestContext, String key) {
        if(!StringUtils.hasLength(key)){
            return null;
        }
        Cookie[] cookies = requestContext.getRequest().getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                String cookieName = cookie.getName();
                String decodeCookieName = cookieName;
                if(cookieAttributeConfig.getEncoder() != null){
                    decodeCookieName = cookieAttributeConfig.getEncoder().encodeName(cookieName);
                }
                if(key.equals(decodeCookieName)){
                    String value = cookie.getValue();
                    if(cookieAttributeConfig.getEncoder() != null){
                        value = (String) cookieAttributeConfig.getEncoder().decodeValue(value);
                    }
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public void setAttribute(RequestContext requestContext, String key, Object value) {
        
        if(cookieAttributeConfig.getEncoder() != null){
            key = cookieAttributeConfig.getEncoder().encodeName(key);
            value = cookieAttributeConfig.getEncoder().encodeValue(value);
        }
        Cookie cookie = new Cookie(key, value.toString());
        cookie.setDomain(cookieAttributeConfig.getDomain());
        cookie.setMaxAge(cookieAttributeConfig.getMaxAge());
        cookie.setPath(cookieAttributeConfig.getPath());
        cookie.setSecure(cookieAttributeConfig.isSecure());
        LOGGER.debug("Set-cookie: {}-{}", key, value);
        requestContext.getResponse().addCookie(cookie);
    }
    
    @Override
    public void removeAttribute(SessionRequestContext requestContext, String key) {
        if(key == null){
            return;
        }
        
        String decodeCookieName = key;
        if(cookieAttributeConfig.getEncoder() != null){
            decodeCookieName = cookieAttributeConfig.getEncoder().encodeName(decodeCookieName);
        }
        Cookie cookie = new Cookie(decodeCookieName, null);
        cookie.setDomain(cookieAttributeConfig.getDomain());
        cookie.setPath(cookieAttributeConfig.getPath());
        cookie.setSecure(cookieAttributeConfig.isSecure());
        cookie.setMaxAge(0);
        LOGGER.debug("Remove-cookie: {}", key);
        requestContext.getResponse().addCookie(cookie);
        
    }

    @Override
    public List<String> getAllAttributeNames(RequestContext requestContext) {
        List<String> attributeNames = new ArrayList<String>();
        Cookie[] cookies = requestContext.getRequest().getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                String cookieName = cookie.getName();
                String decodeCookieName = cookieName;
                if(cookieAttributeConfig.getEncoder() != null){
                    decodeCookieName = cookieAttributeConfig.getEncoder().decodeName(cookieName);
                }
                if(cookieAttributeConfig.isMatch(decodeCookieName)){
                    attributeNames.add(decodeCookieName);
                }
            }
        }
        return attributeNames;
    }

    /**
     * getCookieAttributeConfig
     * @return CookieAttributeConfig
     */
    public CookieAttributeConfig getCookieAttributeConfig() {
        return cookieAttributeConfig;
    }

    /**
     * 
     * 功能描述: setCookieAttributeConfig<br>
     * 〈功能详细描述〉
     *
     * @param cookieAttributeConfig CookieAttributeConfig
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCookieAttributeConfig(CookieAttributeConfig cookieAttributeConfig) {
        this.cookieAttributeConfig = cookieAttributeConfig;
    }

}
