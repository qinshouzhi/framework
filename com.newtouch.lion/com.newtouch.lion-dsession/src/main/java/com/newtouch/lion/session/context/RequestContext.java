package com.newtouch.lion.session.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 〈RequestContext〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface RequestContext {

    /**
     * 取得被包装的context。
     * 
     * @return 被包装的<code>RequestContext</code>对象
     */
    RequestContext getRequestContext();

    /**
     * 取得servletContext对象。
     * 
     * @return <code>ServletContext</code>对象
     */
    ServletContext getServletContext();

    /**
     * 取得request对象。
     * 
     * @return <code>HttpServletRequest</code>对象
     */
    HttpServletRequest getRequest();

    /**
     * 取得response对象。
     * 
     * @return <code>HttpServletResponse</code>对象
     */
    HttpServletResponse getResponse();
    
    /**
     * 取得request对象。
     * 
     * @return <code>HttpServletRequest</code>对象
     */
    HttpServletRequest getOriginalRequest();

    /**
     * 取得response对象。
     * 
     * @return <code>HttpServletResponse</code>对象
     */
    HttpServletResponse getOriginalResponse();

}
