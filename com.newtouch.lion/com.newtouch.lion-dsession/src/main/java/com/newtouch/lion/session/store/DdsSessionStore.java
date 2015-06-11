package com.newtouch.lion.session.store;

import java.util.List;

import com.newtouch.lion.session.config.SessionAttributeConfig;
import com.newtouch.lion.session.context.SessionRequestContext;

/**
 * session存取的接口
 * 
 * @author
 * @date
 */
public interface DdsSessionStore {

    /**
     * 
     * 功能描述:清除相关数据 <br>
     * 〈功能详细描述〉
     * 
     * @param requestContext SessionRequestContext
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    void invalidate(SessionRequestContext requestContext);

    /**
     * 
     * 功能描述:getAttribute<br>
     * 〈功能详细描述〉
     * 
     * @param requestContext SessionRequestContext
     * @param key key
     * @return Object
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    Object getAttribute(SessionRequestContext requestContext, String key);
    

    /**
     * 
     * 功能描述: setAttribute<br>
     * 〈功能详细描述〉
     * 
     * @param requestContext SessionRequestContext
     * @param key key
     * @param value value
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    void setAttribute(SessionRequestContext requestContext, String key, Object value);

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
     * 功能描述: 只获取所有在config中配置的cookie的name<br>
     * 〈功能详细描述〉
     * 
     * @param requestContext SessionRequestContext
     * @return list
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<String> getAllAttributeNames(SessionRequestContext requestContext);

    /**
     * 
     * 功能描述: getSessionAttributeConfig<br>
     * 〈功能详细描述〉
     * 
     * @return SessionAttributeConfig
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    SessionAttributeConfig getSessionAttributeConfig();

}
