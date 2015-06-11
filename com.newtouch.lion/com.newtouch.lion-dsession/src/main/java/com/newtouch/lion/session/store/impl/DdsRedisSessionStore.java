package com.newtouch.lion.session.store.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.redis.client.IBinaryRedisClient;
import com.newtouch.lion.session.config.SessionAttributeConfig;
import com.newtouch.lion.session.context.SessionRequestContext;
import com.newtouch.lion.session.store.DdsSessionStore;

/**
 * 
 * 〈redis session store〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsRedisSessionStore implements DdsSessionStore {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DdsRedisSessionStore.class);

    /**
     * SessionAttributeConfig
     */
    @Autowired
    private SessionAttributeConfig sessionAttributeConfig;

    /**
     * redis client interface
     */
    @Autowired
    private IBinaryRedisClient springBinaryRedisClient;

    /**
     * 会话保持时间，单位。秒
     */
    private int maxInactiveInterval;

    /**
     * constructor
     */
    public DdsRedisSessionStore() {
        super();
    }

    /**
     * 
     * 功能描述: getMaxInactiveInterval<br>
     * 〈功能详细描述〉
     * 
     * @return maxInactiveInterval
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * 
     * 功能描述:setMaxInactiveInterval <br>
     * 〈功能详细描述〉
     * 
     * @param maxInactiveInterval int
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    @Override
    public void invalidate(SessionRequestContext requestContext) {
        if (requestContext.getRequestedSessionID() != null) {
            LOGGER.info("invalidate redis store");
            springBinaryRedisClient.removeObject(requestContext.getRequestedSessionID());
        }
    }

    @Override
    public Object getAttribute(SessionRequestContext requestContext, String key) {
        if (requestContext.getRequestedSessionID() != null) {
            String id = requestContext.getRequestedSessionID();
            LOGGER.info("jsessionid=" + id);
            Object redisvalue = springBinaryRedisClient.hgetObject(id, key);  
            LOGGER.info("session attr's " + key + "=" + redisvalue + ",expired after " + maxInactiveInterval + " seconds");
            springBinaryRedisClient.expire(id, maxInactiveInterval);            
            return redisvalue;
        } else {
            return null;
        }  
    }

    @Override
    public void setAttribute(SessionRequestContext requestContext, String key, Object value) {
        if (requestContext.getRequestedSessionID() != null) {
            String id = requestContext.getRequestedSessionID();
            springBinaryRedisClient.hsetObject(id, key, value);
            springBinaryRedisClient.expire(id, maxInactiveInterval);
        }

    }

    @Override
    public void removeAttribute(SessionRequestContext requestContext, String key) {
        if (key != null && requestContext.getRequestedSessionID() != null) {
            String id = requestContext.getRequestedSessionID();
            springBinaryRedisClient.hdelObject(id, key);
            springBinaryRedisClient.expire(id, maxInactiveInterval);
        }
    }

    @Override
    public List<String> getAllAttributeNames(SessionRequestContext requestContext) {

        List<String> result = new ArrayList<String>();
        if (requestContext.getRequestedSessionID() != null) {
            String id = requestContext.getRequestedSessionID();
            Map<String, Object> attributes = springBinaryRedisClient.hgetAllObjects(id);
            for (String attr : attributes.keySet()) {
                result.add(attr);
            }
            springBinaryRedisClient.expire(id, maxInactiveInterval);
        }
        return result;
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
}
