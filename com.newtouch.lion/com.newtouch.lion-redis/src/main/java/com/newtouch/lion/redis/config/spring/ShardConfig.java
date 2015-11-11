package com.newtouch.lion.redis.config.spring;

import java.util.List;

import redis.clients.jedis.JedisPoolConfig;

import com.newtouch.lion.redis.shard.ShardInfo4Jedis;

/**
 * 分片配置
 * 
 */
public class ShardConfig {

    /**
     * shardConfigName
     */
    private String shardConfigName;

    /**
     * shards
     */
    private List<ShardInfo4Jedis> shards;
    /**
     * config
     */
    private JedisPoolConfig config;

    /**
     * 获取分片配置
     * 
     * @return Config 分片配置
     */
    public JedisPoolConfig getConfig() {
        return config;
    }

    /**
     * 设置分片配置
     * 
     * @param config 分片配置
     */
    public void setConfig(JedisPoolConfig config) {
        this.config = config;
    }

    /**
     * 获取分片列表
     * 
     * @return List<ShardInfo4Jedis> 分片列表
     */
    public List<ShardInfo4Jedis> getShards() {
        return shards;
    }

    /**
     * 设置分片列表
     * 
     * @param shards 设置分片列表
     */
    public void setShards(List<ShardInfo4Jedis> shards) {
        this.shards = shards;
    }

    /**
     * 获取分片配置名
     * 
     * @return String 分片配置名
     */
    public String getShardConfigName() {
        return shardConfigName;
    }

    /**
     * 设置分片配置名
     * 
     * @param shardConfigName 分片配置名
     */
    public void setShardConfigName(String shardConfigName) {
        this.shardConfigName = shardConfigName;
    }

}
