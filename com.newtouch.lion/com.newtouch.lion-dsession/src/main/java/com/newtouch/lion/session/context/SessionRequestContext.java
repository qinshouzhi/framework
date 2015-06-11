package com.newtouch.lion.session.context;

import com.newtouch.lion.session.config.DdsCookieConfig;
import com.newtouch.lion.session.config.DdsSessionConfig;

/**
 * 
 * 支持session的<code>RequestContext</code>实现。<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface SessionRequestContext extends RequestContext {
    
    /**
     * 取得Request中的SessionId。
     * @return String
     */
    String getRequestedSessionID();
    
    /**
     * 取得<code>SnfSessionModule</code>实例。
     * 
     * @return <code>SnfSessionModule</code>实例
     */
    DdsSessionConfig getDdsSessionConfig();
    
    /**
     * 取得<code>SnfCookieModule</code>实例。
     * 
     * @return <code>SnfCookieModule</code>实例
     */
    DdsCookieConfig getSnfCookieConfig();

    /**
     * 判断session是否已经作废。
     * 
     * @return 如已作废，则返回<code>true</code>
     */
    boolean isSessionInvalidated();

    /**
     * 清除session。类似<code>invalidate()</code>方法，但支持后续操作，而不会抛出
     * <code>IllegalStateException</code>。
     */
    void clear();
}