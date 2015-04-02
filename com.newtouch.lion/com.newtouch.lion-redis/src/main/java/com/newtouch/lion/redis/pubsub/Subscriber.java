package com.newtouch.lion.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub{
	private static Logger logger = LoggerFactory.getLogger(Subscriber.class);

    @Override
    public void onMessage(String channel, String message) {
        logger.info("Message received. Channel: {}, Msg: {}", channel, message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
             System.out.println("onPMessage");
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
    	  System.out.println("onSubscribe");
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
    	System.out.println("onUnsubscribe");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    	System.out.println("onPUnsubscribe");
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
    	System.out.println("onPSubscribe");
    }
}
