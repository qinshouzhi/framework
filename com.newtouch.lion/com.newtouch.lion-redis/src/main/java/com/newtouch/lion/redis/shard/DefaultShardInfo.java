package com.newtouch.lion.redis.shard;

/**
 * 
 * 
 * 功能描述：分片工具类, 来自Jedis，为便于区分，重新取名
 * 
 * @version 1.0.0
 * @param <T> 参数说明
 */
public abstract class DefaultShardInfo<T> {
    /**
     * 该shard的权重
     */
    private int weight = 1;

    /**
     * 构造方法
     */
    public DefaultShardInfo() {
    }

    /**
     * 构造方法
     * 
     * @param weight 参数说明
     */
    public DefaultShardInfo(int weight) {
        this.weight = weight;
    }

    /**
     * 
     * 功能描述： 获取权重
     * 
     * @return int 返回值
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * 
     * 功能描述: 创建Resource<br>
     * 初始化一个Resource
     * 
     * @return T 返回值
     */
    protected abstract T createResource();

    /**
     * 
     * 功能描述：获取分片名字
     * 
     * @return String 返回值
     */
    public abstract String getName();
}
