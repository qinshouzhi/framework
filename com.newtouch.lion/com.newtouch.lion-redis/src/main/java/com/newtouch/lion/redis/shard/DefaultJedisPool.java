package com.newtouch.lion.redis.shard;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

/**
 * 
 * 
 * 功能描述： 支持选择dbIndex的Jedis连接池
 * 
 * @version 1.0.0
 */
public class DefaultJedisPool extends Pool<Jedis> {
    /**
     * log
     */
    @SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DefaultJedisPool.class);
    /**
     * host
     */
    private String host;
    /**
     * port
     */
    private int port;
    /**
     * dbIndex
     */
    private int dbIndex;
    /**
     * errorCount
     */
    private AtomicInteger errorCount = new AtomicInteger(0);

    /**
     * 是否是master
     */
    private boolean isMaster = true;

    /**
     * 构造方法
     * 
     * @param poolConfig 池配置
     * @param host 主机地址
     * @param port 端口
     * @param timeout 超时时间
     * @param password 密码
     * @param dbIndex 索引
     * @param isMaster 是否是master
     */
    public DefaultJedisPool(final JedisPoolConfig poolConfig, final String host, int port, int timeout, final String password,
            int dbIndex, boolean isMaster) {
        super(poolConfig,new JedisFactory(host,port,timeout,null,dbIndex));
        this.host = host;
        this.port = port;
        this.dbIndex = dbIndex;
        this.isMaster = isMaster;
    }

    /**
     * 
     * 功能描述：是否是master
     * 
     * @return boolean 返回值
     */
    public boolean isMaster() {
        return isMaster;
    }

    /**
     * 返回错误数量
     * 
     * @return AtomicInteger 错误数量
     */
    public AtomicInteger getErrorCount() {
        return errorCount;
    }

    /**
     * 
     * 功能描述：判断是否已经销毁
     * 
     * @return boolean 返回值
     * @throw 异常描述
     */
    @SuppressWarnings("rawtypes")
	public boolean isDestroyed() throws Exception {
        GenericObjectPool internalPool = getInternalPool();
        return internalPool.getNumActive() + internalPool.getNumIdle() == 0 ? true : false;
    }

    /**
     * 获取父类的成员变量
     * 
     * @return GenericObjectPool 父类的成员变量
     * @throws Exception 异常
     */
    @SuppressWarnings("rawtypes")
	public GenericObjectPool getInternalPool() throws Exception {
        Class<?> father = this.getClass().getSuperclass();
        Field f = father.getDeclaredField("internalPool");
        f.setAccessible(true);
        return (GenericObjectPool) f.get(this);
    }

    /**
     * toString 方法[host=" + host + ", port=" + port + ", dbIndex=" + dbIndex + "]
     * @return string
     */
    @Override
    public String toString() {
        return "[host=" + host + ", port=" + port + ", dbIndex=" + dbIndex + "]";
    }

    /**
     * 返回对象的hashcode
     * @return hash code
     */
    @SuppressWarnings("deprecation")
	@Override
    public int hashCode() {
        return ObjectUtils.hashCodeMulti(host, port, dbIndex);
    }

    /**
     * 对象比较
     * 
     * @param obj 被比较的对象
     * @return 比较结果
     */
    @SuppressWarnings("deprecation")
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof DefaultJedisPool) {
            DefaultJedisPool that = (DefaultJedisPool) obj;
            return ObjectUtils.equals(host, that.host) && ObjectUtils.equals(port, that.port)
                    && ObjectUtils.equals(dbIndex, that.dbIndex);
        } else {
            return false;
        }
    }

}
