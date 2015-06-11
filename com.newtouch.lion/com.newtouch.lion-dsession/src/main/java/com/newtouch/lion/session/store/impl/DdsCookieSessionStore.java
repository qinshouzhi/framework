package com.newtouch.lion.session.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.session.config.SessionAttributeConfig;
import com.newtouch.lion.session.context.SessionRequestContext;
import com.newtouch.lion.session.store.DdsCookieStore;
import com.newtouch.lion.session.store.DdsSessionStore;

/**
 * 
 * 〈cookie session store〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsCookieSessionStore implements DdsSessionStore {

    /**
     * SessionAttributeConfig
     */
    @Autowired
    private SessionAttributeConfig sessionAttributeConfig;

    /**
     * DdsCookieStore
     */
    @Autowired
    private DdsCookieStore cookieStore;

    @Override
    public void invalidate(SessionRequestContext requestContext) {
        cookieStore.invalidate(requestContext);
    }

    @Override
    public Object getAttribute(SessionRequestContext requestContext, String key) {
        Object value = cookieStore.getAttribute(requestContext, key);
        return value;
    }

    @Override
    public void setAttribute(SessionRequestContext requestContext, String key, Object value) {
        cookieStore.setAttribute(requestContext, key, value);
    }

    @Override
    public void removeAttribute(SessionRequestContext requestContext, String key) {
        cookieStore.removeAttribute(requestContext, key);
    }

    @Override
    public List<String> getAllAttributeNames(SessionRequestContext requestContext) {
        return cookieStore.getAllAttributeNames(requestContext);
    }

    @Override
    public SessionAttributeConfig getSessionAttributeConfig() {
        return sessionAttributeConfig;
    }

    /**
     * 
     * 功能描述:setSessionAttributeConfig <br>
     * 〈功能详细描述〉
     *
     * @param sessionAttributeConfig SessionAttributeConfig
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSessionAttributeConfig(SessionAttributeConfig sessionAttributeConfig) {
        this.sessionAttributeConfig = sessionAttributeConfig;
    }

    /**
     * 
     * 功能描述:getCookieStore <br>
     * 〈功能详细描述〉
     *
     * @return DdsCookieStore
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DdsCookieStore getCookieStore() {
        return cookieStore;
    }

    /**
     * 
     * 功能描述:setCookieStore <br>
     * 〈功能详细描述〉
     *
     * @param cookieStore DdsCookieStore
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCookieStore(DdsCookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    // @Override
    // public void setMaxInactiveInterval(int maxInactiveInterval) {
    // }

}
