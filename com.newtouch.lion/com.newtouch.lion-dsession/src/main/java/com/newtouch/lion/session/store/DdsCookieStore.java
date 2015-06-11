package com.newtouch.lion.session.store;

import java.util.List;

import com.newtouch.lion.session.config.CookieAttributeConfig;
import com.newtouch.lion.session.context.RequestContext;
import com.newtouch.lion.session.context.SessionRequestContext;

/**
 * 
 * cookie存取的接口<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface DdsCookieStore {

    /**
     * 
     * 功能描述: 清除相关数据<br>
     * 〈功能详细描述〉
     * 
     * @param requestContext RequestContext
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    void invalidate(RequestContext requestContext);

    /**
     * 
     * 功能描述:getAttribute <br>
     * 〈功能详细描述〉
     * 
     * @param requestContext RequestContext
     * @param key String
     * @return Object
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    Object getAttribute(RequestContext requestContext, String key);

    /**
     * 
     * 功能描述:setAttribute <br>
     * 〈功能详细描述〉
     * 
     * @param requestContext RequestContext
     * @param key key
     * @param value value
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    void setAttribute(RequestContext requestContext, String key, Object value);

    /**
     * 
     * 功能描述: removeAttribute<br>
     * 〈功能详细描述〉
     * 
     * @param requestContext SessionRequestContext
     * @param key key
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    void removeAttribute(SessionRequestContext requestContext, String key);

    /**
     * 
     * 功能描述: getAllAttributeNames<br>
     * 〈功能详细描述〉
     * 
     * @param requestContext RequestContext
     * @return list
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<String> getAllAttributeNames(RequestContext requestContext);

    /**
     * 
     * 功能描述: getCookieAttributeConfig<br>
     * 〈功能详细描述〉
     * 
     * @return CookieAttributeConfig
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    CookieAttributeConfig getCookieAttributeConfig();

}
