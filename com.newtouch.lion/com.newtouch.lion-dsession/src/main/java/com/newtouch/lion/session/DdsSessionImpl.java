package com.newtouch.lion.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.session.common.RequestContextUtils;
import com.newtouch.lion.session.common.SessionConstant;
import com.newtouch.lion.session.config.DdsSessionConfig;
import com.newtouch.lion.session.context.SessionRequestContext;
import com.newtouch.lion.session.store.DdsSessionStore;

/**
 * 
 * 〈session 实现类〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@SuppressWarnings("deprecation")
public class DdsSessionImpl implements HttpSession {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DdsSessionImpl.class);

    /**
     * model
     */
    private SessionModel model;

    /**
     * session id
     */
    private String sessionID;

    /**
     * request context
     */
    private SessionRequestContext requestContext;

    /**
     * session config
     */
    private DdsSessionConfig sessionConfig;

    /**
     * is new
     */
    private boolean isNew;

    /**
     * invalidated
     */
    private boolean invalidated = false;

    /**
     * constructor
     * 
     * @param sessionID session id
     * @param requestContext request context
     * @param isNew is new session
     */
    public DdsSessionImpl(String sessionID, SessionRequestContext requestContext, boolean isNew) {

        if (requestContext == null) {
            throw new NullPointerException("requestContext is null");
        }
        this.sessionID = sessionID;
        this.requestContext = requestContext;
        this.sessionConfig = requestContext.getDdsSessionConfig();

        if (isNew) {
            // 情况1：Requested sessionID为空,创建新的model，并保存之。
            LOGGER.info("No session ID was found in cookie or URL.  A new session will be created.");
            invalidate(false);
        } else {
            if (this.model == null) {                
                // 情况2：Requested sessionID不为空，但是model不存在（会话过程中新的请求）。
                // 获取cookie中的session 的创建时间和上次访问时间,若取不到则直接用当前时间
                long createTime = System.currentTimeMillis();
                long lastAccessTime = createTime;
                List<String> keys = new ArrayList<String>();
                keys.add(SessionConstant.SESSION_CREATE_TIME);
                keys.add(SessionConstant.SESSION_LASTACCESS_TIME);
                Map<String, Cookie> keyCookies = RequestContextUtils.getCookiesFromCookie(this.requestContext, keys);
                LOGGER.info(SessionConstant.SESSION_CREATE_TIME + " = " + keyCookies.get(SessionConstant.SESSION_CREATE_TIME));
                LOGGER.info(SessionConstant.SESSION_LASTACCESS_TIME + " = " + keyCookies.get(SessionConstant.SESSION_LASTACCESS_TIME));
                if (keyCookies.get(SessionConstant.SESSION_CREATE_TIME) != null
                        && keyCookies.get(SessionConstant.SESSION_LASTACCESS_TIME) != null) {
                    LOGGER.info("update session " + this.sessionID + " using cookie value");
                    createTime = Long.valueOf(keyCookies.get(SessionConstant.SESSION_CREATE_TIME).getValue());
                    lastAccessTime = Long.valueOf(keyCookies.get(SessionConstant.SESSION_LASTACCESS_TIME).getValue());
                } else {
                    createTime = System.currentTimeMillis();
                    lastAccessTime = System.currentTimeMillis();
                    LOGGER.info("update creattime and lastaccesstime to current time");
                }
                this.model = new SessionModel(this.sessionID, this.sessionConfig, createTime, lastAccessTime,
                        this.sessionConfig.getMaxInactiveInterval());

//                if (keyCookies.get(SessionConstant.SESSION_CREATE_TIME) == null
//                        || keyCookies.get(SessionConstant.SESSION_CREATE_TIME).getValue() == null) {
//
//                    RequestContextUtils.writeKeyValueToCookie(this.requestContext, SessionConstant.SESSION_CREATE_TIME,
//                            String.valueOf(lastAccessTime));
//                }

            } else {
                throw new IllegalArgumentException("真的出异常啦。。。。。。");
            }
        }
        this.isNew = isNew;
    }

    /**
     * 
     * 功能描述:get session id <br>
     * 〈功能详细描述〉
     * 
     * @return session id
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * 
     * 功能描述: get request context<br>
     * 〈功能详细描述〉
     * 
     * @return request context
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public SessionRequestContext getRequestContext() {
        return requestContext;
    }

    /**
     * 
     * 功能描述:get session config <br>
     * 〈功能详细描述〉
     * 
     * @return session config
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DdsSessionConfig getSessionConfig() {
        return sessionConfig;
    }

    @Override
    public Object getAttribute(String name) {
        if (isInvalidated()) {
            LOGGER.info(sessionID + " is invalidated.");
            return null;
        }
        DdsSessionStore store = getSessionConfig().getSessionStore(name);
        LOGGER.info("DdsSessionStore class " + store.getClass().getCanonicalName());
        SessionAttribute attr = new SessionAttribute(name, this, store, null);
        return attr.getValue();
    }

    @Override
    public void removeAttribute(String name) {
        if (isInvalidated()) {
            return;
        }
        DdsSessionStore store = getSessionConfig().getSessionStore(name);
        SessionAttribute attr = new SessionAttribute(name, this, store, null);
        attr.setValue(null);
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (isInvalidated()) {
            return;
        }
        DdsSessionStore store = getSessionConfig().getSessionStore(name);
        SessionAttribute attr = new SessionAttribute(name, this, store, null);
        attr.setValue(value);
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        model.setMaxInactiveInterval(interval);
    }

    /**
     * 作废
     */
    @Override
    public void invalidate() {
        invalidate(true);
    }

    /**
     * 
     * 功能描述: 假作废，重置<br>
     * 〈功能详细描述〉
     * 
     * @param invalidate invalidate
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void invalidate(boolean invalidate) {
        // 通知所有的store过期其数据
        List<DdsSessionStore> stores = getSessionConfig().getSessionStores();
        for (DdsSessionStore store : stores) {
            store.invalidate(getRequestContext());
        }
        // 清除model
        if (model == null) {
            model = new SessionModel(DdsSessionImpl.this);
        } else {
            model.reset();
        }
        RequestContextUtils.writeKeyValueToCookie(this.requestContext, SessionConstant.SESSION_CREATE_TIME,
                String.valueOf(model.getCreationTime()));
        RequestContextUtils.writeKeyValueToCookie(this.requestContext, SessionConstant.SESSION_LASTACCESS_TIME,
                String.valueOf(model.getLastAccessedTime()));
        invalidated = invalidate;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    /**
     * 
     * 功能描述: is invalidated<br>
     * 〈功能详细描述〉
     * 
     * @return boolean
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean isInvalidated() {
        return invalidated;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Enumeration getAttributeNames() {

        Set<String> attrNames = new HashSet<String>();
        if (!isInvalidated()) {
            List<DdsSessionStore> stores = getSessionConfig().getSessionStores();
            for (DdsSessionStore store : stores) {
                attrNames.addAll(store.getAllAttributeNames(getRequestContext()));
            }
        }
        final Iterator<String> i = attrNames.iterator();
        return new Enumeration<String>() {
            public boolean hasMoreElements() {
                return i.hasNext();
            }

            public String nextElement() {
                return i.next();
            }
        };
    }

    @Override
    public long getCreationTime() {
        return model == null ? 0 : model.getCreationTime();
    }

    @Override
    public String getId() {
        return sessionID;
    }

    @Override
    public long getLastAccessedTime() {
        return model == null ? 0 : model.getLastAccessedTime();
    }

    @Override
    public int getMaxInactiveInterval() {
        return model == null ? 0 : model.getMaxInactiveInterval();
    }

    @Override
    public ServletContext getServletContext() {
        return requestContext.getServletContext();
    }

    /**
     * 
     * 功能描述:get session model <br>
     * 〈功能详细描述〉
     * 
     * @return session model
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public SessionModel getModel() {
        return model;
    }

    /**
     * 
     * 功能描述:set session model <br>
     * 〈功能详细描述〉
     * 
     * @param model model
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setModel(SessionModel model) {
        this.model = model;
    }

    @Override
    @Deprecated
    public HttpSessionContext getSessionContext() {
        throw new UnsupportedOperationException("No longer supported method: getSessionContext");
    }

    @Override
    @Deprecated
    public Object getValue(String name) {
        return getAttribute(name);
    }

    @Override
    @Deprecated
    @SuppressWarnings("rawtypes")
    public String[] getValueNames() {

        Enumeration e = getAttributeNames();
        @SuppressWarnings("unchecked")
        ArrayList<Integer> list = Collections.list(e);
        String[] array = list.toArray(new String[0]);
        return array;
    }

    @Override
    @Deprecated
    public void putValue(String name, Object value) {
        setAttribute(name, value);
    }

    @Override
    @Deprecated
    public void removeValue(String name) {
        removeAttribute(name);
    }
}
