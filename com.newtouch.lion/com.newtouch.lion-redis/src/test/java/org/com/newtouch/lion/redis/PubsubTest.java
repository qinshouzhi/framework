package org.com.newtouch.lion.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.redis.pubsub.Publisher;
import com.newtouch.lion.redis.pubsub.Subscriber;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PubsubTest {
	public static final String CHANNEL_NAME = "commonChannel";

    private static Logger logger = LoggerFactory.getLogger(PubsubTest.class);

    
    /**
     * 测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.202.120", 6379,0);
        //发布
        Jedis publisherJedis = jedisPool.getResource();
        //发布消息
        Publisher publisher= new Publisher(publisherJedis, CHANNEL_NAME);
        publisher.lpublish("上海");
        
        //订阅
        final Jedis subscriberJedis = jedisPool.getResource();
        
        final Subscriber subscriber = new Subscriber();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("Subscribing to \"channel\". This thread will be blocked.");
                    subscriberJedis.subscribe(subscriber,CHANNEL_NAME);
                    logger.info("Subscription ended.");
                } catch (Exception e) {
                    logger.error("Subscribing failed.", e);
                }
            }
        }).start();
       
        
        //指退订给定的
        jedisPool.returnResource(subscriberJedis);
        
        // 释放对象池 
        jedisPool.returnResource(publisherJedis);
        
        
        
    }
}
