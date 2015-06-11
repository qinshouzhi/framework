package com.newtouch.lion.session;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.session.config.DdsSessionConfig;

/**
 * 
 * 代表一个session本身的信息。该对象是可序列化的。<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SessionModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * logger
     */
    private static final  Logger LOGGER = LoggerFactory.getLogger(SessionModel.class);

    /**
     * 1000
     */
    private static final int THOUNSD =1000;
   
    /**
     * session config
     */
    private transient DdsSessionConfig sessionConfig;

    /**
     * session id
     */
    private String sessionID;

    /**
     * creation time
     */
    private long creationTime;

    /**
     * last access time
     */
    private long lastAccessedTime;

    /**
     * max inactive interval
     */
    private int maxInactiveInterval;

    /**
     * 新建的session中的SessionModel
     * @param session session
     */
    public SessionModel(DdsSessionImpl session) {
        setSession(session);
        reset();
    }

    /**
     * constructor 
     * @param sessionID session id
     * @param sessionConfig session config
     * @param creationTime create time
     * @param lastAccessedTime last access time
     * @param maxInactiveInterval max inactive interval
     */
    public SessionModel(String sessionID, DdsSessionConfig sessionConfig, long creationTime, long lastAccessedTime,
            int maxInactiveInterval) {
        this.sessionID = sessionID;
        this.sessionConfig = sessionConfig;
        this.creationTime = creationTime;
        this.lastAccessedTime = lastAccessedTime;
        this.maxInactiveInterval = maxInactiveInterval;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error("clone failed", e);
            return null;
        }
    }

    /**
     * 
     * 功能描述: get session config<br>
     * 〈功能详细描述〉
     *
     * @return session config
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private DdsSessionConfig getSessionConfig() {
        if (sessionConfig == null) {
            throw new NullPointerException("sessionConfig is null");
        }
        return sessionConfig;
    }

    /**
     * 
     * 功能描述: reset<br>
     * 〈功能详细描述〉
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void reset() {
        getSessionConfig();
        this.creationTime = System.currentTimeMillis();
        this.lastAccessedTime = creationTime;
    }

    /**
     * 
     * 功能描述:设置model所在的session。 <br>
     * 〈功能详细描述〉
     *
     * @param session session
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSession(DdsSessionImpl session) {
        this.sessionConfig = session.getSessionConfig();
        this.sessionID = session.getId();
        this.maxInactiveInterval = this.sessionConfig.getMaxInactiveInterval();
    }

    /**
     * 
     * 功能描述:取得session ID。 <br>
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
     * 取得session的创建时间。
     * 
     * @return 创建时间戮
     */
    public long getCreationTime() {
        return creationTime;
    }

    /**
     * 取得最近访问时间。
     * 
     * @return 最近访问时间戮
     */
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    /**
     * 取得session的最大不活动期限，超过此时间，session就会失效。
     * 
     * @return 不活动期限的秒数
     */
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    /**
     * 设置session的最大不活动期限，超过此时间，session就会失效。
     * 
     * @param maxInactiveInterval 不活动期限的秒数
     */
    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    /**
     * 判断session有没有过期。
     * 
     * @return 如果过期了，则返回<code>true</code>
     */
    public boolean isExpired() {

        int maxInactiveInterval = getMaxInactiveInterval();
        long current = System.currentTimeMillis();

        // 如果从上次访问时间算起，已经超过maxInactiveInterval没动静了，则作废。
        if (maxInactiveInterval > 0) {
            long expires = getLastAccessedTime() + maxInactiveInterval * THOUNSD;

            if (expires < current) {
                return true;
            }
        }

        return false;
    }

    // /**
    // * 更新session的访问时间。
    // */
    // public void touch(SessionRequestContext requestContext) {
    // lastAccessedTime = System.currentTimeMillis();
    // }

}
