package com.newtouch.lion.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.newtouch.lion.redis.pubsub.RedisListener;
import com.newtouch.lion.redis.pubsub.Subscriber;

public class PubsubTest {
	public static final String CHANNEL_NAME = "watchmen:notify";

	private static Logger logger = LoggerFactory.getLogger(PubsubTest.class);

	public static void main(String[] args) {

//		 ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/data-source.xml");
//	     
//		 RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisClientTemplate");
		
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();

		JedisPool jedisPool = new JedisPool(poolConfig, "192.168.202.120",6379, 0);

		
		Jedis subRedisClient = jedisPool.getResource();
		Jedis pubRedisClient = jedisPool.getResource();
		
		RedisListener listener = new RedisListener();

		//Publisher pub = new Publisher(pubRedisClient,CHANNEL_NAME);

		//pub.publish("hello word"); // 发布一个频道
		
		//pub.lpublish("hello1 word");//发布到server
		
		Subscriber sub = new Subscriber(subRedisClient,CHANNEL_NAME);
		sub.psub(listener); // 订阅一个频道
		//pubRedisClient.del(CHANNEL_NAME);//删除
		
	}
	}
