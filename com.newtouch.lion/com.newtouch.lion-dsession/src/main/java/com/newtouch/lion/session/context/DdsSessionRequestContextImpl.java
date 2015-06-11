package com.newtouch.lion.session.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.newtouch.lion.session.DdsSessionImpl;
import com.newtouch.lion.session.common.RequestContextUtils;
import com.newtouch.lion.session.common.SessionConstant;
import com.newtouch.lion.session.common.UUIDGenerator;
import com.newtouch.lion.session.config.DdsCookieConfig;
import com.newtouch.lion.session.config.DdsSessionConfig;

/**
 * 
 * session封装后的request上下文<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsSessionRequestContextImpl extends AbstractRequestContex implements SessionRequestContext {

    /**
     * logger
     */
 
    private static final Logger LOGGER = LoggerFactory.getLogger(DdsSessionRequestContextImpl.class);

    /**
     * requestedSessionID
     */
    private String requestedSessionID;

    /**
     * requestedSessionIDFromCookie
     */
    private boolean requestedSessionIDFromCookie;

    /**
     * requestedSessionIDFromURL
     */
    private boolean requestedSessionIDFromURL;

    /**
     * sessionConfig
     */
    private DdsSessionConfig sessionConfig;

    /**
     * cookieConfig
     */
    private DdsCookieConfig cookieConfig;

    /**
     * sessionImpl
     */
    private DdsSessionImpl sessionImpl;

    /**
     * Constructor
     * @param servletContext ServletContext
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param sessionConfig DdsSessionConfig
     * @param cookieConfig DdsCookieConfig
     */
    public DdsSessionRequestContextImpl(ServletContext servletContext, HttpServletRequest request,
            HttpServletResponse response, DdsSessionConfig sessionConfig, DdsCookieConfig cookieConfig) {
        super(servletContext, request, response);
        HttpServletRequest requestWrapper = new SnfHttpServletRequestWrapper(request);
        HttpServletResponse responseWrapper = new SnfHttpServletResponseWrapper(response);
        setRequest(requestWrapper);
        setResponse(responseWrapper);
        this.sessionConfig = sessionConfig;
        this.cookieConfig = cookieConfig;
    }

    /**
     * 取得当前的session ID。
     * 
     * @return session ID
     */
    public String getRequestedSessionID() {
        if (StringUtils.hasLength(requestedSessionID)) {
            return this.requestedSessionID;
        }
        // 首先从URL中获取sessionId
        this.requestedSessionID = decodeSessionIDFromURL();
        this.requestedSessionIDFromURL = (this.requestedSessionID != null);
        // 如果未找到,从Cookie中检索sessionId
        if (this.requestedSessionID == null) {
            this.requestedSessionID = decodeSessionIDFromCookie();
            this.requestedSessionIDFromCookie = (this.requestedSessionID != null);
        }
        return this.requestedSessionID;
    }

    /**
     * 
     * 功能描述:将session ID编码到Cookie中去。 <br>
     * 〈功能详细描述〉
     *
     * @param sessionId String
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void encodeSessionIDIntoCookie(String sessionId) {
        writeSessionIDToCookie(sessionId);
    }

    /**
     * 
     * 功能描述:写cookie。 <br>
     * 〈功能详细描述〉
     *
     * @param cookieValue String
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private void writeSessionIDToCookie(String cookieValue) {
        RequestContextUtils.writeKeyValueToCookie(this, SessionConstant.SESSION_ID, cookieValue);
    }

    /**
     * 从cookie中取得session ID。
     * 
     * @return 如果存在，则返回session ID，否则返回<code>null</code>
     */
    public String decodeSessionIDFromCookie() {
        return RequestContextUtils.decodeSessionIDFromCookie(this);
    }
    
    /**
     * 
     * 功能描述:将session ID编码到URL中去。 <br>
     * 〈功能详细描述〉
     *
     * @param url String
     * @return 包含session ID的URL
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String encodeSessionIDIntoURL(String url) {
        return RequestContextUtils.encodeSessionIDIntoURL(this, url);
    }

    /**
     * 从URL中取得session ID。
     * 
     * @return 如果存在，则返回session ID，否则返回<code>null</code>
     */
    public String decodeSessionIDFromURL() {
        return RequestContextUtils.decodeSessionIDFromURL(this);
    }


    /**
     * 
     * 功能描述: 取得当前的session，如果不存在，则创建一个新的。<br>
     * 〈功能详细描述〉
     *
     * @param create boolean
     * @return 当前的session或新的session， 如果不存在则返回
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public HttpSession getSession(boolean create) {

        if (sessionImpl != null && sessionImpl.getModel().isExpired()) {
            LOGGER.info(sessionImpl.getModel().isExpired() ? "session has been expired" : "session is alive");
            // 如果session过期，则清空和重置session
            sessionImpl.invalidate();
        }
        // 如果getSession方法已经被执行过,且没有超出过期时间，那么直接返回
        if (sessionImpl != null) {
            sessionImpl.getModel().reset();            
            LOGGER.info(sessionImpl.getId() + "'s last access time updated");
            return sessionImpl;
        }

        // 创建session，尽管有可能创建却不返回
        if (sessionImpl == null) {
            // 从request中取得session ID
            boolean isNew = false;
            // 如果sessionID为空，则创建一个新的ID
            if (getRequestedSessionID() == null) {
                if (!create) {
                    return null; // 除了create=false，直接返回null
                }
                this.requestedSessionID = UUIDGenerator.getUUID();
                // 将sessionid编码到cookie中
                encodeSessionIDIntoCookie(this.requestedSessionID);
                isNew = true;
            }

            // 不管怎样，先创建一个session对象再说，但这个session有可能不存在或是过期的
            sessionImpl = new DdsSessionImpl(this.requestedSessionID, this, isNew);
        }

        return sessionImpl;
    }

    /**
     * 
     * 功能描述:isRequestedSessionIDFromCookie <br>
     * 〈功能详细描述〉
     *
     * @return requestedSessionIDFromCookie
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean isRequestedSessionIDFromCookie() {
        return requestedSessionIDFromCookie;
    }

    /**
     * 
     * 功能描述: setRequestedSessionIDFromCookie<br>
     * 〈功能详细描述〉
     *
     * @param requestedSessionIDFromCookie boolean
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setRequestedSessionIDFromCookie(boolean requestedSessionIDFromCookie) {
        this.requestedSessionIDFromCookie = requestedSessionIDFromCookie;
    }

    /**
     * 
     * 功能描述: isRequestedSessionIDFromURL<br>
     * 〈功能详细描述〉
     *
     * @return requestedSessionIDFromURL
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean isRequestedSessionIDFromURL() {
        return requestedSessionIDFromURL;
    }

    /**
     * 
     * 功能描述: setRequestedSessionIDFromURL<br>
     * 〈功能详细描述〉
     *
     * @param requestedSessionIDFromURL boolean
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setRequestedSessionIDFromURL(boolean requestedSessionIDFromURL) {
        this.requestedSessionIDFromURL = requestedSessionIDFromURL;
    }

    /**
     * 
     * 功能描述: setRequestedSessionID<br>
     * 〈功能详细描述〉
     *
     * @param requestedSessionID String
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setRequestedSessionID(String requestedSessionID) {
        this.requestedSessionID = requestedSessionID;
    }

    @Override
    public boolean isSessionInvalidated() {
        return sessionImpl == null ? false : sessionImpl.isInvalidated();
    }

    /**
     * 判断当前的session ID是否仍然合法。
     * 
     * @return 如果是，则返回<code>true</code>
     */
    public boolean isRequestedSessionIDValid() {
        HttpSession session = getSession(false);
        return session != null && session.getId().equals(requestedSessionID);
    }

    @Override
    public void clear() {
        
    }

    @Override
    public DdsSessionConfig getDdsSessionConfig() {
        return this.sessionConfig;
    }

    @Override
    public DdsCookieConfig getSnfCookieConfig() {
        return this.cookieConfig;
    }

    /**
     * 内部类 SnfHttpServletRequestWrapper
     */
    private class SnfHttpServletRequestWrapper extends HttpServletRequestWrapper {

        /**
         * Constructor
         * @param request HttpServletRequest
         */
        public SnfHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /**
         * 取得当前request中的session ID。
         * 
         * @return session ID
         */
        @Override
        public String getRequestedSessionId() {
            return DdsSessionRequestContextImpl.this.getRequestedSessionID();
        }

        /**
         * 当前的session ID是从cookie中取得的吗？
         * 
         * @return 如果是，则返回<code>true</code>
         */
        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return DdsSessionRequestContextImpl.this.isRequestedSessionIDFromCookie();
        }

        /**
         * 当前的session ID是从URL中取得的吗？
         * 
         * @return 如果是，则返回<code>true</code>
         */
        @Override
        public boolean isRequestedSessionIdFromURL() {
            return DdsSessionRequestContextImpl.this.isRequestedSessionIDFromURL();
        }

        /**
         * 判断当前的session ID是否仍然合法。
         * 
         * @return 如果是，则返回<code>true</code>
         */
        @Override
        public boolean isRequestedSessionIdValid() {
            return DdsSessionRequestContextImpl.this.isRequestedSessionIDValid();
        }

        @Override
        public HttpSession getSession() {
            return DdsSessionRequestContextImpl.this.getSession(true);
        }

        /**
         * 取得当前的session，如果不存在，且<code>create</code>为<code>true</code>，则创建一个新的。
         * 
         * @param create 必要时是否创建新的session
         * @return 当前的session或新的session，如果不存在，且<code>create</code>为 <code>false</code>，则返回<code>null</code>
         */
        @Override
        public HttpSession getSession(boolean create) {
            return DdsSessionRequestContextImpl.this.getSession(create);
        }

        /**
         * @return isRequestedSessionIdFromURL
         */
        @Override
        @Deprecated
        public boolean isRequestedSessionIdFromUrl() {
            return isRequestedSessionIdFromURL();
        }
    }

    /**
     * 内部类 SnfHttpServletResponseWrapper
     */
    private class SnfHttpServletResponseWrapper extends HttpServletResponseWrapper {

        /**
         * constructor 
         * @param response HttpServletResponse
         */
        public SnfHttpServletResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        /**
         * 将session ID编码到URL中。
         * 
         * @param url 要编码的URL
         * @return 包含session ID的URL
         */
        @Override
        public String encodeURL(String url) {
//            url = DdsSessionRequestContextImpl.this.encodeSessionIDIntoURL(url);
//            return url;
            return super.encodeURL(url);
        }

        /**
         * 将session ID编码到URL中。
         * 
         * @param url 要编码的URL
         * @return 包含session ID的URL
         */
        @Override
        public String encodeRedirectURL(String url) {
            url = DdsSessionRequestContextImpl.this.encodeSessionIDIntoURL(url);

            return url;
        }

        /**
         * @param url String
         * @return use encodeURL instead
         */
        @Override
        @Deprecated
        public String encodeUrl(String url) {
            return encodeURL(url);
        }

        /**
         * @param url String
         * @return encodeRedirectURL
         */
        @Override
        @Deprecated
        public String encodeRedirectUrl(String url) {
            return encodeRedirectURL(url);
        }
    }

}
