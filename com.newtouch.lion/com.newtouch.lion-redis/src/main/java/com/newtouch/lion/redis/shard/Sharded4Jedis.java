package com.newtouch.lion.redis.shard;

import java.util.List;

/**
 * 
 * 
 * 功能描述： 包括所有分片的连接池
 * 
 */
public class Sharded4Jedis extends DefaultSharded<ShardHandler, ShardInfo4Jedis> {

    /**
     * 构造方法
     * 
     * @param shards 分片列表
     */
    public Sharded4Jedis(List<ShardInfo4Jedis> shards) {
        super(shards);
    }

}
