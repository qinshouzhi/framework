package com.newtouch.lion.session.store.impl;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.session.config.SessionAttributeConfig;
import com.newtouch.lion.session.context.SessionRequestContext;
import com.newtouch.lion.session.store.DdsSessionStore;

/**
 * 
 * 〈default session store〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsDefaultSessionStore implements DdsSessionStore {

    /**
     * logger
     */
    private static final  Logger    LOGGER = LoggerFactory.getLogger(DdsDefaultSessionStore.class);
    
    /**
     * SessionAttributeConfig
     */
    private SessionAttributeConfig sessionAttributeConfig;

    @Override
    public void invalidate(SessionRequestContext requestContext) {
        requestContext.getOriginalRequest().getSession().invalidate();

    }

    @Override
    public Object getAttribute(SessionRequestContext requestContext, String key) {
        return requestContext.getOriginalRequest().getSession().getAttribute(key);
    }

    @Override
    public void setAttribute(SessionRequestContext requestContext, String key, Object value) {
        LOGGER.debug("Set-JVM: {}-{}", key, value);
        requestContext.getOriginalRequest().getSession().setAttribute(key, value);
    }

    @Override
    public void removeAttribute(SessionRequestContext requestContext, String key) {
        LOGGER.debug("Remove-JVM: {}", key);
        requestContext.getOriginalRequest().getSession().removeAttribute(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllAttributeNames(SessionRequestContext requestContext) {
        @SuppressWarnings("rawtypes")
        Enumeration attributeNames = requestContext.getOriginalRequest().getSession()
                .getAttributeNames();
        return Collections.list(attributeNames);
    }

    @Override
    public SessionAttributeConfig getSessionAttributeConfig() {
        return sessionAttributeConfig;
    }

    /**
     * 
     * 功能描述: setSessionAttributeConfig<br>
     * 〈功能详细描述〉
     *
     * @param sessionAttributeConfig SessionAttributeConfig
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSessionAttributeConfig(SessionAttributeConfig sessionAttributeConfig) {
        this.sessionAttributeConfig = sessionAttributeConfig;
    }

//    @Override
//    public void setMaxInactiveInterval(int maxInactiveInterval) {
//    }

}
