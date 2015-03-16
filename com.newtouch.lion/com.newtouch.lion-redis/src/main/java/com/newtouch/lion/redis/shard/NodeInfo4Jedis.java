package com.newtouch.lion.redis.shard;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

/**
 * 
 * NodeInfo4Jedis
 * 
 * @author pinzhao
 */
public class NodeInfo4Jedis {

    /**
     * Redis服务器ip
     */
    private String ip;

    /**
     * Redis服务器端口号
     */
    private Integer port;

    /**
     * 　Redis服务器访问密码
     */
    private String password;

    /**
     * Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema
     */
    private int dbIndex;

    /**
     * 连接超时时间
     */
    private int timeOut;

    /**
     * 配置信息
     */
    private Config config;

    /**
     * 
     * NodeInfo4Jedis constructor
     */
    public NodeInfo4Jedis() {

    }

    /**
     * 构造方法
     * 
     * @param ip Redis服务器ip
     * @param port Redis服务器端口号
     * @param password Redis服务器访问密码
     * @param dbIndex Redis服务器数据库标识，Redis服务器内部使用，相当于数据库的schema
     * @param timeOut 连接超时时间
     * @param config 配置信息
     */
    public NodeInfo4Jedis(String ip, Integer port, String password, int dbIndex, int timeOut, Config config) {
        this.ip = ip;
        this.port = port;
        this.password = password;
        this.dbIndex = dbIndex;
        this.timeOut = timeOut;
        this.config = config;
    }

    /**
     * 获取超时时间
     * 
     * @return int 超时时间
     */
    public int getTimeOut() {
        return timeOut;
    }

    /**
     * 设置超时时间
     * 
     * @param timeOut 超时时间
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 设置配置信息
     * 
     * @param config 配置信息
     */
    public void setConfig(Config config) {
        this.config = config;
    }

    /**
     * 获取配置信息
     * 
     * @return Config 配置信息
     */
    public Config getConfig() {
        return config;
    }

    /**
     * 获取redis服务器地址
     * 
     * @return String redis服务器地址
     */
    public String getIp() {

        return ip;
    }

    /**
     * 设置redis服务器地址
     * 
     * @param ip 参数说明
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取redis服务器端口
     * 
     * @return Integer redis服务器端口
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置redis服务器端口
     * 
     * @param port redis服务器端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取redis服务器访问密码
     * 
     * @return String redis服务器访问密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置redis服务器访问密码
     * 
     * @param password redis服务器访问密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Redis服务器数据库标识
     * 
     * @return int 返回值
     */
    public int getDbIndex() {
        return dbIndex;
    }

    /**
     * 设置Redis服务器数据库标识
     * 
     * @param dbIndex Redis服务器数据库标识
     */
    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    /**
     * 获取url
     * 
     * @return String url
     */
    public String getUrl() {
        return ip + ":" + port;
    }

    /**
     * 获取hashcode
     * 
     * @return int hashcode
     */
    @SuppressWarnings("deprecation")
	@Override
    public int hashCode() {
        return ObjectUtils.hashCodeMulti(ip, port, dbIndex);
    }

    /**
     * 节点比较
     * 
     * @param obj 比较对象
     * @return boolean 比较结果
     */
    @SuppressWarnings("deprecation")
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof NodeInfo4Jedis) {
            NodeInfo4Jedis that = (NodeInfo4Jedis) obj;
            return ObjectUtils.equals(ip, that.ip) && ObjectUtils.equals(port, that.port)
                    && ObjectUtils.equals(dbIndex, that.dbIndex);
        } else {
            return false;
        }
    }

    /**
     * 
     * toString
     * 
     * @return String ip + port + password + dbIndex + timeOut + config
     */
    @Override
    public String toString() {
        return "NodeInfo4Jedis [ip=" + ip + ", port=" + port + ", password=" + password + ", dbIndex=" + dbIndex
                + ", timeOut=" + timeOut + ", config=" + config + "]";
    }
}
